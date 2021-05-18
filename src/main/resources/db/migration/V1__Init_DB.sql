create table account (
    id integer not null auto_increment,
    account_type integer,
    balance double precision,
    client_id integer, primary key (id)
);

create table client (
    id integer not null auto_increment,
    first_name varchar(255),
    last_name varchar(255),
    Primary key (id)
);



create table payment
(
    id               integer not null auto_increment,
    amount           double precision,
    create_date_time datetime(6),
    dest_acc_id      integer,
    payer_id         integer,
    reason           varchar(255),
    recipient_id     integer,
    source_acc_id    integer,
    key (id)
);

alter table account add constraint account_client_kk foreign key (client_id) references client (id);