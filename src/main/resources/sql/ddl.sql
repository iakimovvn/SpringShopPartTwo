create schema public;

comment on schema public is 'standard public schema';

alter schema public owner to postgres;

create table if not exists image
(
    id uuid default uuid_generate_v4() not null
        constraint image_pk
            primary key,
    name varchar(255) not null
);

alter table image owner to postgres;

create table if not exists product
(
    id uuid default uuid_generate_v4() not null
        constraint product_pk
            primary key,
    title varchar(255) not null,
    description varchar(255),
    price double precision default 0 not null,
    added timestamp default now() not null,
    available boolean default false,
    image uuid
        constraint fk_product_image
            references image
            on update cascade on delete cascade,
    category integer not null
);

alter table product owner to postgres;

create unique index product_id_uindex
    on product (id);

create unique index product_title_uindex
    on product (title);

create unique index image_id_uindex
    on image (id);

create unique index image_name_uindex
    on image (name);

create table if not exists role
(
    id serial not null
        constraint role_pk
            primary key,
    name varchar(255) not null,
    description varchar(255)
);

alter table role owner to postgres;

create unique index role_id_uindex
    on role (id);

create unique index role_name_uindex
    on role (name);

create table if not exists shopuser
(
    id uuid default uuid_generate_v4() not null
        constraint user_pk
            primary key,
    phone varchar(255) not null,
    password varchar(255) not null,
    email varchar(255) default NULL::character varying,
    first_name varchar(255) not null,
    last_name varchar(255)
);

alter table shopuser owner to postgres;

create table if not exists purchase
(
    id uuid not null
        constraint purchase_pk
            primary key,
    price double precision default 0.0 not null,
    address varchar(255) not null,
    phone varchar(255) not null,
    shopuser uuid not null
        constraint fk_purchase_users
            references shopuser
);

alter table purchase owner to postgres;

create unique index purchase_id_uindex
    on purchase (id);

create table if not exists cart_record
(
    id uuid default uuid_generate_v4() not null
        constraint cart_pk
            primary key,
    quantity integer default 0 not null,
    price double precision default 0.0 not null,
    product uuid not null
        constraint fk_cart_product
            references product
            on update cascade on delete cascade,
    purchase uuid not null
        constraint fk_cart_purchase
            references purchase
            on update cascade on delete cascade
);

alter table cart_record owner to postgres;

create unique index cart_id_uindex
    on cart_record (id);

create unique index user_email_uindex
    on shopuser (email);

create unique index user_id_uindex
    on shopuser (id);

create unique index user_phone_uindex
    on shopuser (phone);

create table if not exists shopuser_role
(
    shopuser uuid not null
        constraint fk_users_role_users
            references shopuser,
    role integer not null
        constraint fk_users_role_role
            references role
);

alter table shopuser_role owner to postgres;

create or replace function uuid_nil()
    immutable
    strict
    language c
as -- missing source code
;

alter function uuid_nil() owner to postgres;

create or replace function uuid_ns_dns()
    immutable
    strict
    language c
as -- missing source code
;

alter function uuid_ns_dns() owner to postgres;

create or replace function uuid_ns_url()
    immutable
    strict
    language c
as -- missing source code
;

alter function uuid_ns_url() owner to postgres;

create or replace function uuid_ns_oid()
    immutable
    strict
    language c
as -- missing source code
;

alter function uuid_ns_oid() owner to postgres;

create or replace function uuid_ns_x500()
    immutable
    strict
    language c
as -- missing source code
;

alter function uuid_ns_x500() owner to postgres;

create or replace function uuid_generate_v1()
    strict
    language c
as -- missing source code
;

alter function uuid_generate_v1() owner to postgres;

create or replace function uuid_generate_v1mc()
    strict
    language c
as -- missing source code
;

alter function uuid_generate_v1mc() owner to postgres;

create or replace function uuid_generate_v3(namespace uuid, name text)
    immutable
    strict
    language c
as -- missing source code
;

alter function uuid_generate_v3(uuid, text) owner to postgres;

create or replace function uuid_generate_v4()
    strict
    language c
as -- missing source code
;

alter function uuid_generate_v4() owner to postgres;

create or replace function uuid_generate_v5(namespace uuid, name text)
    immutable
    strict
    language c
as -- missing source code
;

alter function uuid_generate_v5(uuid, text) owner to postgres;