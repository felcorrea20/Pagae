create database PAGAE;

use PAGAE;

create table tb_user (
	id bigint primary key auto_increment,
    name varchar (50) not null,
    email varchar (60) not null unique,
    password varchar (50) not null,
    phone varchar (15),
    created_at datetime,
    updated_at datetime,
    is_active boolean default true
);

create table tb_group_members (
	id bigint primary key auto_increment,
    user_id bigint,
    group_id bigint,
    foreign key (user_id) references tb_user (id)
);

create table tb_group (
	id bigint primary key auto_increment,
    name varchar (60)
);

create table tb_expenses_splits (
	id bigint primary key auto_increment,
    user_id bigint,
    expense_id bigint,
    amount decimal,
    foreign key (user_id) references tb_user (id)
);

create table tb_expenses (
	id bigint primary key auto_increment,
    description text,
    amount decimal,
    group_id bigint,
    payer_id bigint,
    foreign key (group_id) references tb_group (id) on delete cascade,
    foreign key (payer_id) references tb_user (id) on delete cascade
);

alter table tb_group_members add constraint foreign key (group_id) references tb_group (id) on delete cascade;
alter table tb_expenses_splits add constraint foreign key (expense_id) references tb_expenses (id) on delete cascade;