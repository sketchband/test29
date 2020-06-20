create table board6(
num int not null,
writer varchar(10) not null,
email varchar(30) default null,
subject varchar(50) not null,
passwd varchar(12) not null,
reg_date date not null,
readcount int default 0,
ref int not null,
pos int not null,
depth int not null,
content varchar(2000) not null,
ip varchar(20) not null,
primary key(num));

drop table board6;

create sequence board_num_seq6;

select * from board6;