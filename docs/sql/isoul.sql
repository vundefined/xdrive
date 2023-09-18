DROP TABLE IF EXISTS `isoul_user`;
CREATE TABLE `isoul_user` (
  	`id` int(11) NOT NULL AUTO_INCREMENT,
  	`username` varchar(63) NOT NULL COMMENT '用户名称',
  	`password` varchar(63) NOT NULL COMMENT '用户密码',
  	`avatar` varchar(255) NOT NULL COMMENT '头像',
  	`nickname` varchar(63) NOT NULL COMMENT '昵称',
  	`mobile` varchar(20) NOT NULL COMMENT '用户手机号码',
    `email` varchar(20) NOT NULL COMMENT '邮箱',
  	`weixin_openid` varchar(63) NOT NULL COMMENT '微信登录openid',
  	`session_key` varchar(100) NOT NULL COMMENT '微信登录会话KEY',
  	`gender` tinyint(3) NOT NULL COMMENT '性别：0 未知， 1男， 1 女',
  	`user_level` tinyint(3) NOT NULL COMMENT '0 普通用户，1 VIP用户，2 高级VIP用户',
  	`status` tinyint(3) NOT NULL COMMENT '0 可用, 1 禁用, 2 注销',
  	`deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  	`birthday` date COMMENT '生日',
  	`add_time` datetime COMMENT '创建时间',
    `update_time` datetime COMMENT '更新时间',
    `last_login_time` datetime COMMENT '最近一次登录时间',
    `last_login_ip` varchar(63) NOT NULL DEFAULT '' COMMENT '最近一次登录IP地址',
    PRIMARY KEY (`id`),
    UNIQUE KEY `user_name` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表信息';

DROP TABLE IF EXISTS `isoul_article`;
CREATE TABLE `isoul_article` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `user_id` int(11) NOT NULL COMMENT '用户表id',
    `content` text NOT NULL COMMENT '文本内容',
    `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
    `status` tinyint(3) NOT NULL COMMENT '0 正常, 1 被举报,',
    `add_time` datetime COMMENT '创建时间',
    `update_time` datetime COMMENT '更新时间',
    `delete_time` datetime COMMENT '删除时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章表';

DROP TABLE IF EXISTS `article_view`;
CREATE TABLE `article_view` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `user_id` int(11) NOT NULL COMMENT '用户表的用户ID',
    `value_id` int(11) NOT NULL COMMENT '内容id',
    `desc` varchar(1023) DEFAULT '' COMMENT '分享描述',
    `type` tinyint(3) NOT NULL COMMENT '0被查看, 1赞, 2分享',
    `add_time` datetime COMMENT '创建时间',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='查看 赞 分享';