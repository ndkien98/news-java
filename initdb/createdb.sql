create database 24h;

create table category
(
    id                int auto_increment
        primary key,
    name              varchar(100) charset utf8 null,
    parentsCategoryId int                       null,
    constraint parentsCategory_fr
        foreign key (parentsCategoryId) references category (id)
);

create table news
(
    id               int auto_increment
        primary key,
    title            varchar(500) charset utf8 null,
    content          text                      null,
    createDate       timestamp                 null,
    updateDate       timestamp                 null,
    avatar           varchar(500)              null,
    creatorId        int                       null,
    editorId         int                       null,
    author           varchar(500) charset utf8 null,
    categoryId       int                       null,
    originalResource varchar(500)              null,
    numberAccess     int                       null,
    censor           int                       null,
    constraint category_fr
        foreign key (categoryId) references category (id)
);

create table role
(
    id   int auto_increment
        primary key,
    name varchar(50) charset utf8 null
);

create table user
(
    id         int auto_increment
        primary key,
    username   varchar(50)               not null,
    password   varchar(200)              not null,
    fullName   varchar(100) charset utf8 null,
    address    varchar(100) charset utf8 null,
    createDate timestamp                 null,
    updateDate timestamp                 null,
    creatorId  int                       null,
    editorId   int                       null,
    email      varchar(30)               null
);

create table user_role
(
    id     int auto_increment
        primary key,
    userId int not null,
    roleId int not null,
    constraint roleId_fr
        foreign key (roleId) references role (id),
    constraint userId_fr
        foreign key (userId) references user (id)
);

