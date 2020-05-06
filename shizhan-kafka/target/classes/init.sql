#mysql数据库

#建库
CREATE DATABASE IF NOT EXISTS shizhan_kafka
     CHARACTER SET utf8mb4
     COLLATE utf8mb4_bin;

use shizhan_kafka;

#订单表
create table t_order
(
  order_id bigint(20) primary key,
  order_name varchar(50),
  order_num varchar(20) not null,
  create_date datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  update_date datetime ON UPDATE CURRENT_TIMESTAMP,
  status_cd int(5) comment '1 新建、2 在途、 3 完工、 4 撤单'
);


#订单项表
create table t_order_item
(
  order_item_id bigint(20) primary key ,
  order_id bigint(20) not null,
  order_item_type int(5),
  status_cd int(5) comment '1 新建、2 在途、 3 完工、 4 撤单',
  create_date datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  update_date datetime ON UPDATE CURRENT_TIMESTAMP
);

#订单异常表
create table t_order_error
(
  error_id bigint(20) primary key,
  order_num varchar(20) not null,
  order_name varchar(50),
  create_date datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  update_date datetime ON UPDATE CURRENT_TIMESTAMP,
  error_type int(5) comment '1 校验不通过、 2 网络异常',
  status_cd int(5) comment '1 待处理、2 已处理',
  order_value varchar(1000)
);