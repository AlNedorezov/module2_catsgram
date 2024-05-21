create table if not exists posts (
    post_id  integer not null primary key auto_increment,
    author   varchar(255) not null,
    creation_date date not null,
    description varchar(255) not null,
    photo_url varchar(255) not null,
    constraint post_pk primary key (post_id)
);
