create table if not exists posts (
    post_id         serial          not null primary key,
    author          varchar(255)    not null,
    creation_date   date            not null,
    description     varchar(255)    not null,
    photo_url       varchar(255)    not null
);
