# 资产管理系统
注：大二下接到老师的油橄榄基地项目，借此背景，自己先尝试实现一个Demo


## 技术栈：

1. springboot
2. mybatis
3. mysql
4. lombok
5. html + css
6. bootstrap

## 运行效果：
![image](https://github.com/user-attachments/assets/65cc41e9-4d08-45d8-9097-ebcb6b413441)
![image](https://github.com/user-attachments/assets/a49cd8b3-0969-4f86-bcac-7ea8816c4e21)
![image](https://github.com/user-attachments/assets/fdd46874-f8ff-44e4-9cfc-25d0d741b9e9)
![image](https://github.com/user-attachments/assets/e2b37c87-ea17-478f-b606-b1a6232edbd9)

## 建表语句：

```sql
create table borrow_return
(
    id           int unsigned auto_increment comment 'ID'
        primary key,
    machine_name varchar(20) not null comment '机器',
    username     varchar(20) not null comment '借取人',
    create_time  datetime    not null comment '创建时间',
    update_time  datetime    not null comment '更新时间',
    constraint borrow_return_pk
        unique (machine_name)
)
    comment '借还表';

create index borrow_return_machine_name_index
    on borrow_return (machine_name);

create table current_location
(
    id          int unsigned auto_increment comment 'ID'
        primary key,
    machine_id  int unsigned not null comment '机器id',
    location    varchar(100) not null comment '当前位置',
    create_time datetime     not null comment '创建时间',
    update_time datetime     not null comment '更新时间'
)
    comment '位置表';

create table machine
(
    id           int unsigned auto_increment comment 'ID'
        primary key,
    machine_name varchar(20)  not null comment '机器名',
    type         varchar(100) not null comment '机器类型',
    amount       int          not null comment '现有数量',
    location     varchar(300) null comment '安装位置',
    factory      varchar(300) not null comment '生产厂家',
    link         varchar(300) null comment '参考链接',
    create_time  datetime     null comment '创建时间',
    update_time  datetime     null comment '更新时间',
    constraint machine_pk
        unique (machine_name)
)
    comment '机器表';

create index machine_machine_name_index
    on machine (machine_name);

create table repair
(
    id            int unsigned auto_increment comment 'ID'
        primary key,
    device_name   varchar(100)                  null comment '报修的设备',
    reporter_name varchar(50)                   null comment '报修人',
    repairer_name varchar(50)                   null comment '维修人',
    status        varchar(50) default 'Pending' null comment '维修状态'
)
    comment '报修表';

create table scrapped
(
    id           int unsigned auto_increment comment 'ID'
        primary key,
    machine_name varchar(50) not null comment '报废机器'
)
    comment '报废表';

create table status
(
    id             int unsigned auto_increment
        primary key,
    device_name    varchar(100)                        not null,
    current_status varchar(50)                         not null,
    last_updated   timestamp default CURRENT_TIMESTAMP null
);

create table user
(
    id          int unsigned auto_increment comment 'ID'
        primary key,
    username    varchar(20) not null comment '用户名',
    gender      varchar(20) null comment '性别：1 男，2 女',
    password    varchar(32) not null comment '密码',
    type        varchar(20) null comment '用户类型：1 管理员，2 操作员，3 维修员',
    phone       varchar(20) null comment '电话号码',
    create_time datetime    null comment '创建时间',
    update_time datetime    null comment '更新时间',
    constraint username_pk
        unique (username)
)
    comment '用户表';

create index username__index
    on user (username);
```

