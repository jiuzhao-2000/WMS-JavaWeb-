drop database test;
create database test;
use test;
#用户表
create table UserTable(
	 uid int primary key auto_increment,
	 ucode nvarchar(10) not null unique,
	 upwd nvarchar(16) not null,
	 upower int not null
)auto_increment=0 engine=innoDB default charset=utf8;
#类别表
create table CommodityClass(
	id int primary key auto_increment,
    name nvarchar(10) not null
)auto_increment=0 engine=innoDB default charset=utf8;
#仓库
create table Warehouse(
	id int primary key auto_increment,
    name nvarchar(10) not null unique,
    number int not null,
    classId int not null,
    constraint cid_fk foreign key(classId) references CommodityClass(id)
)auto_increment=0 engine=innoDB default charset=utf8;
#仓库进出日志
create table WarehouseLog(
	id int primary key auto_increment,
    name nvarchar(10) not null,
    time date not null,
    isAdd bit not null,
    number int not null,
    constraint name_fk foreign key(name) references Warehouse(name)
)auto_increment=0 engine=innoDB default charset=utf8;

#插入四个用户
insert into UserTable(ucode,upwd,upower) values('root','123456',15),('jiuzhao','1234567',7),('yun','12345678',3),('jack','123456789',1),('Linus','123456',1),('Turing','123456',1);
#插入三个物品类别
insert into CommodityClass(name) values('食品'),('日用品'),('其他');
#插入物品
insert into Warehouse(name,number,classId) values('小米',100,1),('护肤品',300,2),('TNT',600,3),('大麦',1000,1),('洗发水',800,2),('加特林',900,3);
insert into Warehouse(name,number,classId) values('沐浴液',100,2),('冬瓜',300,1),('M4A1',600,3),('墩布',1000,2),('苦瓜',800,1),('AK47',900,3);
insert into Warehouse(name,number,classId) values('羊肝',100,1),('C4',300,3),('扫把',600,2),('鸭肉',1000,1),('带和链',800,3),('空气清新剂',900,2);
insert into Warehouse(name,number,classId) values('滚动轴承',100,3),('洗涤灵',300,2),('鸭血',600,1),('紧固件',1000,3),('牙刷',800,2),('兔肉',900,1);
#
insert into WarehouseLog(name,time,isAdd,number) values('小米',now(),true,100),('护肤品',now(),false,200),('TNT',now(),true,300),('加特林',now(),false,400),('小米',now(),false,500),('AK47',now(),true,600);

#test
select * from UserTable;
select * from CommodityClass;
select * from Warehouse;
select * from WarehouseLog;