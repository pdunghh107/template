# Onboarding Flow Architecture & Implementation Plan

## 1. Phân tích Kiến trúc
Luồng Onboarding của hệ thống Core Banking bao gồm nhiều bước phức tạp và cần gọi chéo qua nhiều Microservices:
`eKYC -> Check Risk -> Tạo CIF -> Tạo User Auth -> Tạo Account -> Phát hành Card -> Gửi SMS`

### Quyết định Kiến trúc:
- **Tách biệt Service:** Phân tách rõ ràng các service: `risk-service`, `customer-service`, `auth-service`, `account-service`, `card-service`, và `notification-service`.
- **Saga Orchestration với Temporal:** Không sử dụng Saga Choreography (nhả Event qua Kafka/RabbitMQ) để tránh hiện tượng Spaghetti Code và Lost Events. Sử dụng **Temporal.io** làm Workflow Engine đóng vai trò "Nhạc trưởng" điều phối toàn bộ luồng. Temporal cung cấp cơ chế tự động Retry, State Machine và Rollback (Compensation) tuyệt vời.
- **Asynchronous API Pattern:** Frontend (Mobile App) sẽ không bị block (timeout) khi chờ toàn bộ quy trình hoàn tất. `customer-service` nhận request sẽ start Temporal Workflow và lập tức trả về `workflowId` kèm status `202 ACCEPTED`. Frontend sẽ polling hoặc dùng SSE/WebSocket để nhận kết quả cuối cùng.

## 2. Chi tiết Luồng Đi (Step 1 -> 4)

**Toàn bộ luồng đăng ký được định nghĩa bởi một `OnboardingWorkflow` đặt tại `customer-service`.**

*   **Step 1: Định danh & Kiểm tra rủi ro (eKYC & Risk)**
    *   **Action:** API Gateway nhận request từ FE, route vào `customer-service`. `customer-service` khởi chạy `OnboardingWorkflow` và trả mã theo dõi về cho FE ngay lập tức.
    *   **Temporal Activity:** Workflow gọi `CheckRiskActivity` (nằm ở Worker của `risk-service`).
    *   **Logic:** Kiểm tra CIC/AML. Nếu fail, Workflow kết thúc sớm.

*   **Step 2: Tạo Hồ sơ Khách hàng (Customer Information File - CIF)**
    *   **Temporal Activity:** Workflow gọi `CreateCifActivity` (nằm ở Worker của `customer-service`).
    *   **Logic:** Lưu thông tin khách hàng, cấp mã `cifNo`.

*   **Step 3: Cấp phát Tài khoản Đăng nhập (Auth & Credentials)**
    *   **Temporal Activity:** Workflow gọi `CreateUserAuthActivity` (nằm ở Worker của `auth-service`).
    *   **Logic:** Tạo User, hash mật khẩu.
    *   **Rollback (Compensation):** Nếu bước này thất bại, Temporal tự động gọi `DeleteCifActivity` để dọn rác dữ liệu ở Step 2.

*   **Step 4: Khởi tạo Dịch vụ Tài chính (Account, Card, Loyalty) & Thông báo**
    *   **Thực thi:** Chạy **SONG SONG (Parallel)** các Activity để tối ưu tốc độ.
    *   **Temporal Activities:**
        *   `CreateAccountActivity` (`account-service`)
        *   `IssueCardActivity` (`card-service`)
        *   `RewardLoyaltyActivity` (`loyalty-service`)
    *   **Final Activity:** Khi toàn bộ nhánh song song hoàn tất, gọi `SendSmsActivity` (`notification-service`) để thông báo cho khách hàng.

## 3. Kiến trúc Bảo mật Nội bộ
Để đảm bảo các Microservice không bị gọi trái phép từ bên ngoài (bypass Gateway), hoặc để chống giả mạo request, các giao tiếp nội bộ (REST/Feign) sẽ được bảo vệ bởi **Internal Service Key**.
- Header: `X-Internal-Key`.
- Triển khai: `InternalServiceAuthUtils` tại `shared-kernel` để validate. (Tuy nhiên, với Temporal gRPC, việc bảo mật sẽ nằm ở mTLS hoặc cấu hình mạng nội bộ của Docker/K8s).
