create table [IF NOT EXISTS] items (
    id primary key,
    displayname varchar(40) not null,
    description varchar(255) not null
);