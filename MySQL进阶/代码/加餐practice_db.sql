-- 创建数据库
create database if not exists practice_db CHARACTER SET utf8mb4 collate utf8mb4_0900_ai_ci;

use practice_db;

# 在账户实体中添加对用户实体的关联
drop table if exists users;
create table users (
  id bigint primary key auto_increment,
  name varchar(20) not null, 
  nickname varchar(20),
  phone_num varchar(11), 
  email varchar(50),
  gender tinyint(1)
);

drop table if exists account;
create table account (
  id bigint primary key auto_increment,
  username varchar(20) not null,
  password varchar(32) not null,
  users_id bigint
);


# 班级表
drop table if exists class;
create table class (
  id bigint primary key auto_increment,
  name varchar(20)
);

# 学生表
drop table if exists student;
create table student (
  id bigint primary key auto_increment,
  name varchar(20) not null, 
  sno varchar(10) not null,
  age int default 18,
  gender tinyint(1), 
  enroll_date date,
  class_id bigint,
  foreign key (class_id) references class(id)
);

# 课程表
drop table if exists course;
create table course (
  id bigint primary key auto_increment,
  name varchar(20)
);

# 分数表
drop table if exists score;
create table score (
  id bigint primary key auto_increment,
  score float,
  student_id bigint,
  course_id bigint,
  foreign key (student_id) references student(id),
  foreign key (course_id) references course(id)
);
























