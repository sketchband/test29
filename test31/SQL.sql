CREATE TABLE MEMBER12(
zipcode char(6),
address char(50)
)engine=innodb charset=utf8;

select * from MEMBER12;
drop table MEMBER12;

create table tblZipcode2(
zipcode char(8) not null,
area1 char(10) default null,
area2 char(20) default null,
area3 char(40) default null,
area4 char(20) default null
)engine=innodb charset=utf8;

select count(*) from tblZipcode2;
select * from tblZipcode2;
drop table tblZipcode2;
drop table zipcode;