create table tm_singer
(
    id                 bigint auto_increment comment '主键',
    uuid               varchar(200)                       null comment '歌手UUID',
    singer_name        varchar(200)                       not null comment '歌手姓名',
    singer_other_name  varchar(200)                       null comment '歌手别名',
    singer_name_index  varchar(20)                        null comment '姓名首字母',
    third_id           bigint                             null comment '外部系统歌手Id',
    third_uuid         varchar(200)                       null comment '外部系统标记Id',
    singer_area        varchar(200)                       null comment '地区',
    singer_area_code   varchar(200)                       null comment '地区编号',
    singer_genre       varchar(200)                       null comment '流派',
    singer_genre_code  varchar(200)                       null comment '流派编号',
    singer_gender      varchar(200)                       null comment '性别',
    singer_gender_code varchar(200)                       null comment '性别编号',
    is_del             int      default 0                 null comment '逻辑删除标志 0-未删除 1-已删除',
    create_time        datetime default current_timestamp null comment '创建时间',
    update_time        datetime                           null comment '更新时间',
    constraint tm_singer_pk
        primary key (id)
)
    comment '歌手主数据表';

