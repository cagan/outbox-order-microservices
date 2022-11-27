create table outbox
(
    id             varchar(255) not null primary key,
    aggregate_id   varchar(255),
    aggregate_name varchar(255),
    event_name     varchar(255),
    event_type     varchar(255),
    payload        jsonb        not null,
    created_on     timestamp
);

-- auto-generated definition
create table orders
(
    id         varchar(255) not null primary key,
    product_id varchar(255)
        constraint uk_306w8sghgvp5hmjrqo21dscv7 unique,
    status     varchar(255),
    created_on timestamp,
    updated_on timestamp
);

alter table orders
    owner to postgresuser;

