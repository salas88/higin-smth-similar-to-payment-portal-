create table client
(
    id          integer not null auto_increment,
    first_name  varchar(255),
    last_name   varchar(255),
    password    varchar(255),
    primary key (id)
);

create table account
(
    id           integer not null auto_increment,
    account_type integer,
    balance      double precision,
    client_id    integer, primary key (id),
    foreign key (client_id) references client (id)
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

create table client_role(
  client_id integer,
  role_set     varchar(255),
  foreign key (client_id) references client(id)
);
