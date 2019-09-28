create schema if not exists movietheater;
set search_path to movietheater;

create table movies
(
    id       serial       not null,
    name     varchar(200) not null,
    duration integer      not null,
    constraint movies_pk primary key (id)
);
alter table movies
    owner to postgres;

create table timetable
(
    id         serial    not null,
    begin_time timestamp not null,
    price      money     not null,
    movie_id   integer   not null,
    constraint timetable_pk primary key (id)
);
alter table timetable
    owner to postgres;

create table orders
(
    id           serial      not null,
    ticket_num   varchar(10) not null,
    timetable_id integer     not null,
    constraint order_pk primary key (id)
);
alter table orders
    owner to postgres;

alter table orders
    add constraint "fk_order_timetable" foreign key ("timetable_id") references timetable ("id") on delete no action on update no action;
alter table timetable
    add constraint "fk_timetable_movies" foreign key ("movie_id") references movies ("id") on delete no action on update no action;