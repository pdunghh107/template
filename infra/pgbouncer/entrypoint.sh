#!/bin/sh
set -eu

: "${POSTGRES_HOST:=postgres}"
: "${POSTGRES_PORT:=5432}"
: "${POSTGRES_DB:=zcomini}"
: "${POSTGRES_USER:=zcomini}"
: "${POSTGRES_PASSWORD:=zcomini}"
: "${PGBOUNCER_PORT:=6432}"
: "${PGBOUNCER_POOL_MODE:=transaction}"
: "${PGBOUNCER_MAX_CLIENT_CONN:=300}"
: "${PGBOUNCER_DEFAULT_POOL_SIZE:=32}"
: "${PGBOUNCER_MIN_POOL_SIZE:=8}"
: "${PGBOUNCER_RESERVE_POOL_SIZE:=8}"
: "${PGBOUNCER_MAX_DB_CONNECTIONS:=48}"
: "${PGBOUNCER_SERVER_IDLE_TIMEOUT:=30}"

config_dir="/tmp/pgbouncer"
mkdir -p "$config_dir" /var/log/pgbouncer /var/run/pgbouncer

cat > "$config_dir/userlist.txt" <<EOF
"${POSTGRES_USER}" "${POSTGRES_PASSWORD}"
EOF

cat > "$config_dir/pgbouncer.ini" <<EOF
[databases]
${POSTGRES_DB} = host=${POSTGRES_HOST} port=${POSTGRES_PORT} dbname=${POSTGRES_DB} user=${POSTGRES_USER} password=${POSTGRES_PASSWORD}

[pgbouncer]
listen_addr = 0.0.0.0
listen_port = ${PGBOUNCER_PORT}
auth_type = plain
auth_file = ${config_dir}/userlist.txt
admin_users = ${POSTGRES_USER}
stats_users = ${POSTGRES_USER}
pool_mode = ${PGBOUNCER_POOL_MODE}
max_client_conn = ${PGBOUNCER_MAX_CLIENT_CONN}
default_pool_size = ${PGBOUNCER_DEFAULT_POOL_SIZE}
min_pool_size = ${PGBOUNCER_MIN_POOL_SIZE}
reserve_pool_size = ${PGBOUNCER_RESERVE_POOL_SIZE}
max_db_connections = ${PGBOUNCER_MAX_DB_CONNECTIONS}
server_idle_timeout = ${PGBOUNCER_SERVER_IDLE_TIMEOUT}
server_reset_query = DISCARD ALL
server_reset_query_always = 1
ignore_startup_parameters = extra_float_digits
max_prepared_statements = 0
log_connections = 1
log_disconnections = 1
log_pooler_errors = 1
EOF

exec pgbouncer "$config_dir/pgbouncer.ini"
