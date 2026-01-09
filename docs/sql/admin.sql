CREATE TABLE `sys_user` (
    `id` int(11) NOT NULL COMMENT '主键Id',
    `sort` tinyint(3) NOT NULL COMMENT '排序',
    `username` varchar(63) unique NOT NULL COMMENT '管理员名称',
    `password` varchar(63) NOT NULL COMMENT '管理员密码',
    `avatar` varchar(127) NOT NULL COMMENT '头像',
    `mobile` varchar(15) NOT NULL COMMENT '手机号',
    `email` varchar(31) NOT NULL COMMENT '邮箱',
    `deleted` tinyint(1) DEFAULT '1' COMMENT '0禁用 1可用',
    `role_ids` varchar(127) DEFAULT '[]' COMMENT '角色列表',
    `add_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_time` datetime comment '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='管理员表';

CREATE TABLE `sys_role` (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
    `name` varchar(63) unique NOT NULL COMMENT '角色名称',
    `encoding` varchar(15) unique NOT NULL COMMENT '角色编码,资源字符串通配符',
    `desc` varchar(127) NOT NULL COMMENT '角色描述',
    `menu_ids` varchar(127) NOT NULL DEFAULT '[]' COMMENT '所拥有的菜单',
    `menuhalf_ids` varchar(127) NOT NULL DEFAULT '[]' COMMENT '所拥有的菜单,父节点半选中',
    `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0false 1true',
    `add_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

CREATE TABLE `sys_menu` (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
    `pid` int(11) NOT NULL COMMENT '父节点id',
    `sort` tinyint(3) NOT NULL COMMENT '排序',
    `path` varchar(63) NOT NULL COMMENT '路由地址',
    `url` varchar(63) NOT NULL unique COMMENT 'vue动态路由 path -> xx/:id; url -> xx/1',
    `name` varchar(15) NOT NULL unique COMMENT '组件名称',
    `component` varchar(31) NOT NULL COMMENT '所映射的组件',
    `title` varchar(15) NOT NULL COMMENT '菜单名称',
    `icon` varchar(15) NOT NULL COMMENT '菜单图标',
    `type` tinyint(3) NOT NULL DEFAULT '1' COMMENT '0目录 1 菜单 2 按钮',
    `hidden` tinyint(1) NOT NULL COMMENT '是否显示菜单,0false不显示,1true显示',
    `keep_alive` tinyint(1) NOT NULL COMMENT '该页面是否缓存',
    `permission` varchar(255) NOT NULL DEFAULT '[]' COMMENT '资源编码,资源字符串通配符',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统菜单表';

CREATE TABLE `sys_article` (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
    `user_id` int(11) NOT NULL,
    `title` varchar(63) NOT NULL,
    `a_content` text NULL COMMENT '公告内容',
    `add_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='富文本';
