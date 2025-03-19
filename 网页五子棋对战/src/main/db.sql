create database if not exists java_gobang charset utf8;
use java_gobang;

drop table if exists user;
create table user (
    userId int primary key auto_increment,
    username varchar(50) unique,
    password varchar(50),
    score int,       -- 天梯积分
    totalCount int,  -- 比赛总场数
    winCount int     -- 获胜场数
);

insert into user values(null, '张三', '123', 1000, 0, 0);
insert into user values(null, '李四', '123', 1000, 0, 0);
insert into user values(null, '王五', '123', 1000, 0, 0);
