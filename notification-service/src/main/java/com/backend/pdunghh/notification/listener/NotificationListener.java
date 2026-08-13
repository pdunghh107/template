package com.backend.pdunghh.notification.listener;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.backend.pdunghh.shared.event.UserRegisteredEvent;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class NotificationListener {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "notification.user.registered.queue", durable = "true"),
            exchange = @Exchange(value = "notification.events", type = "topic", durable = "true"),
            key = "user.registered"
    ))
    public void handleUserRegisteredEvent(UserRegisteredEvent event) {
        log.info("Received UserRegisteredEvent from RabbitMQ: Sending Welcome Email to {}", event.email());
        
        // TODO: Implement actual email sending logic here (e.g. JavaMailSender)
    }
}
