CREATE TABLE `wx_user` (
    `id` int(11) NOT NULL COMMENT '主键Id',
    `avatar` varchar(127) COMMENT '头像',
    `nickname` varchar(63) COMMENT '昵称',
    `reallname` varchar(63) COMMENT '真实姓名',
    `gender` tinyint(3) COMMENT '性别',
    `mobile` varchar(15) COMMENT '用户手机号码',
    `openid` varchar(63) unique NOT NULL COMMENT 'openid',
    `session_key` varchar(63) COMMENT 'session_key',
    `role` tinyint(3) NOT NULL COMMENT '用户角色',
    `permission` tinyint(3) DEFAULT '0' COMMENT '用户权限',
    `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除,0可用,1禁用',
    `add_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_time` datetime null comment '更新时间',
    `receivers` tinyint(1) DEFAULT '0' COMMENT '微信支付后台是否已添加了分账接收方记录标识,0false,1true',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='微信用户表';

CREATE TABLE `teacher_job` (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
    `wx_user_id` int(11) NOT NULL COMMENT 'wx_user_id',
    `time` date NOT NULL COMMENT '从业时间',
    `encode` varchar(63) NOT NULL COMMENT '教练证编号',
    `company_name` varchar(127) COMMENT '驾校名称',
    `company_address` varchar(255) COMMENT '驾校地址',
    `img` varchar(127) COMMENT '教练证图片',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='教练职业信息';

CREATE TABLE `teacher_car` (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
    `wx_user_id` int(11) NOT NULL COMMENT 'wx_user_id',
    `name` varchar(63) NOT NULL COMMENT '车辆型号',
    `encode` varchar(63) NOT NULL COMMENT '车牌号',
    `img` varchar(127) NOT NULL COMMENT '车辆画像',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='教练教学车辆管理';

CREATE TABLE `drive_license_class` (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
    `p_id` int(11) NOT NULL COMMENT '父节点id',
    `c_name` varchar(15) NOT NULL COMMENT '科目项目名称',
    `c_desc` varchar(1023) COMMENT '科目项目说明',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='驾驶证类型';

CREATE TABLE `order_teacher` (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
    `teacher_id` int(11) NOT NULL COMMENT '教练id 收款人',
    `student_id` int(11) NOT NULL COMMENT '学员id 交款人',
    `class_id` int(11) NOT NULL COMMENT 'drive_license_class id 交易的商品',
    `transaction_id` varchar(63) COMMENT '微信订单号',
    `out_order_no` varchar(127) COMMENT '商户分账单号',
    `order_id` varchar(127) COMMENT '微信分账单号',
    `state` varchar(63) COMMENT '分账单状态',
    `type` varchar(63) COMMENT '分账接收方类型',
    `account` varchar(127) COMMENT '分账接收方账号',
    `amount` int(7) COMMENT '分账金额',
    `description` varchar(127) COMMENT '分账描述',
    `result` varchar(63) COMMENT '分账结果',
    `fail_reason` varchar(127) COMMENT '分账失败原因',
    `detail_id` varchar(127) COMMENT '分账明细单号',
    `create_time` varchar(127) COMMENT '分账创建时间',
    `finish_time` varchar(127) COMMENT '分账完成时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='与教练分账';

CREATE TABLE `order_student` (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
    `student_id` int(11) NOT NULL COMMENT '学员id 付款人',
    `teacher_id` int(11) NOT NULL COMMENT '教练id 收款人',
    `class_id` int(11) NOT NULL COMMENT 'drive_license_class id 交易的商品',
    `out_trade_no` varchar(63) COMMENT '商户订单号',
    `transaction_id` varchar(63) COMMENT '微信支付订单号',
    `trade_state` varchar(63) COMMENT '交易状态',
    `trade_state_desc` varchar(255) COMMENT '交易状态描述',
    `success_time` varchar(127) COMMENT '支付完成时间',
    `total` int(7) COMMENT '总金额(单位分)',
    `payer_total` int(7) COMMENT '用户支付金额(单位分)',
    `currency` varchar(31) COMMENT '货币类型',
    `payer_currency` varchar(31) COMMENT '用户支付币种',
    `is_profitsharing` tinyint(1) DEFAULT '0' COMMENT '是否完成分账, 0false, 1true',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='学员培训费';

CREATE TABLE `teacher_of_student` (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
    `student_id` int(11) NOT NULL COMMENT '学员id',
    `teacher_id` int(11) NOT NULL COMMENT '教练id',
    `recommend` int(11) NOT NULL COMMENT '推荐人',
    `qr_type` tinyint(3) NOT NULL COMMENT '学员与教练绑定来源',
    `add_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '与教练绑定时间',
    `deleted` tinyint(1) DEFAULT '0' COMMENT '0false绑定，1true解绑',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='教练与学员关联关系表';

CREATE TABLE `qr_recommend` (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
    `teacher_id` int(11) NOT NULL COMMENT 'teacherId',
    `class_id` int(11) NOT NULL COMMENT 'drive_license_class id',
    `price` int(7) NOT NULL COMMENT '报名费用(单位分)',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='推广码报名费';

CREATE TABLE `qr_invite` (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
    `teacher_id` int(11) NOT NULL COMMENT 'teacherId',
    `qr_time` tinyint(3) NOT NULL COMMENT '二维码过期时间',
    `qr_count` tinyint(3) NOT NULL COMMENT '允许加入数量',
    `qr_img` varchar(127) NOT NULL COMMENT '二维码图片',
    `update_time` datetime NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='教练邀请学员加入, 二维码配置信息';

CREATE TABLE `student_job` (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
    `student_id` int(11) NOT NULL COMMENT 'student_id',
    `class_id` int(11) NOT NULL COMMENT 'drive_license_class id',
    `class_index` varchar(127) DEFAULT '[]' COMMENT '前端渲染角标',
    `desc` varchar(255) COMMENT '教练为学员备注信息',
    `add_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='学员职业信息';

CREATE TABLE `appointment_record` (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
    `student_id` int(11) NOT NULL COMMENT '学员id',
    `teacher_id` int(11) NOT NULL COMMENT '教练id',
    `class_id` int(11) NOT NULL COMMENT 'drive_license_class id',
    `class_index` varchar(127) DEFAULT '[]' COMMENT '前端渲染角标',
    `during_id` int(11) NOT NULL COMMENT 'appointment_during id',
    `during_start_time` time NOT NULL COMMENT 'appointment_during预约时间段的开始时间',
    `during_end_time` time NOT NULL COMMENT 'appointment_during预约时间段的结束时间',
    `status` tinyint(3) NOT NULL COMMENT '预约状态',
    `add_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='学员提交预约的信息';

CREATE TABLE `student_settings` (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
    `student_id` int(11) NOT NULL COMMENT '学员id',
    `common_id` int(11) NOT NULL COMMENT '根据类型设置值',
    `stype` tinyint(3) NOT NULL COMMENT '类型',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='学员默认设置项';

CREATE TABLE `teacher_settings` (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
    `teacher_id` int(11) COMMENT '教练id',
    `delay_time` int(11) NOT NULL COMMENT '多长时间之后执行分账操作, 单位为毫秒',
    `percentage` tinyint(3) NOT NULL COMMENT '执行分账的比例',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='微信支付分账操作';

CREATE TABLE `appointment_rule` (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
    `teacher_id` int(11) NOT NULL COMMENT 'wx_user_id',
    `rest` varchar(31) DEFAULT '[]' COMMENT '每周休息日',
    `rest_index` varchar(31) DEFAULT '[]' COMMENT '前端渲染角标',
    `start_time` time NOT NULL COMMENT '开始时间',
    `end_time` time NOT NULL COMMENT '结束时间',
    `rule_txt` text COMMENT '预约规则说明',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='每位教练的预约规则';

CREATE TABLE appointment_class (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
    `wx_user_id` int(11) NOT NULL COMMENT 'teacherId',
    `class_id` int(11) NOT NULL COMMENT 'drive_license_class id',
    `class_index` varchar(127) DEFAULT '[]' COMMENT '前端渲染角标',
    `checked` tinyint(1) DEFAULT '0' COMMENT '前端多选删除用',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='每位教练的预约规则,驾驶证类型';

CREATE TABLE `appointment_during` (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
    `wx_user_id` int(11) NOT NULL COMMENT 'teacherId',
    `start_time` time NOT NULL COMMENT '开始时间',
    `end_time` time NOT NULL COMMENT '结束时间',
    `checked` tinyint(1) DEFAULT '0' COMMENT '前端多选删除用',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='每位教练的预约规则,可预约驾驶时间段配置';

CREATE TABLE appointment_class_during (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
    `wx_user_id` int(11) NOT NULL COMMENT 'teacherId',
    `class_id` int(11) NOT NULL COMMENT 'drive_license_class id',
    `class_index` varchar(127) DEFAULT '[]' COMMENT '前端渲染角标',
    `during_ids` varchar(127) DEFAULT '[]' COMMENT 'appointment_during id',
    `checked` tinyint(1) DEFAULT '0' COMMENT '前端多选删除用',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='每位教练的预约规则,驾驶证类型与可预约驾驶时间段绑定';

CREATE TABLE `feedback` (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
    `wx_user_id` int(11) NOT NULL COMMENT 'wx_user_id',
    `content` varchar(255) NOT NULL COMMENT '文本内容',
    `images` varchar(255) DEFAULT '[]' COMMENT '图片内容',
    `add_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='意见反馈';

CREATE TABLE `notification` (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
    `user_id` int(11) DEFAULT NULL COMMENT 'user_id',
    `n_type` tinyint(3) NOT NULL COMMENT '公告类型',
    `n_content` text COMMENT '公告内容',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统公告和私信通知';

