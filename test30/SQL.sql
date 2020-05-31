CREATE TABLE Board14(
num int primary key auto_increment,
ref int,
pos int,
depth int,
count int,
subject char(30),
name char(20),
pw char(20),
email char(50),
content text,
regdate date
)engine=innodb charset=utf8;

select * from Board14;
drop table Board14;

create table MEMBER5(
id char(20) primary key,
pw char(30),
name char(10),
email char(40),
job char(5),
hobby char(20)
)engine=innodb charset=utf8;
select * from member5;