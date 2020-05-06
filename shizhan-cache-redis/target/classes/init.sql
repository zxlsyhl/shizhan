#mysql数据库

#建库
CREATE DATABASE IF NOT EXISTS cache_redis
     CHARACTER SET utf8mb4
     COLLATE utf8mb4_bin;



#部门表
create table t_deptartment
(
  dept_id int(10) primary key,
  dept_name varchar(50) not null,
  create_date datetime,
  update_date datetime
)

#渠道表
create table t_channel
(
  channel_id int(10) primary key,
  channel_name varchar(50) not null,
  create_date datetime,
  update_date datetime
)

#员工表
create table t_staff
(
  staff_id int(10) primary key comment '主键',
  staff_no varchar(30) not null comment '员工编号',
  dept_id int(10),
  channel_id int(10),
  sex int(5) comment '性别 1 男、2 女',
  age int(5) comment '年龄',
  create_date datetime,
  update_date datetime
)