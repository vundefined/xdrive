INSERT INTO `sys_menu` VALUES
(51,0,1,'/system','/system','system','layout/Layout','系统管理','AddLocation',0,0,0,'[\"add\",\"edit\"]'),
(52,51,2,'menu','menu','menu','system/menus','菜单','Aim',1,0,0,'[\"download\",\"del\"]'),
(53,51,3,'role','role','role','system/role','角色','Avatar',1,0,0,'[\"edit\"]'),
(54,51,4,'user','user','user','system/user','用户','Box',1,0,0,'[\"del\"]'),
(55,0,5,'/iframemm','/iframemm','iframemm','layout/Layout','iframemm','Apple',0,0,0,'[\"add\"]'),
(58,55,6,'iframe01/:id','iframe01/1','iframe01','iframemm/iframe01','iframe01','Bowl',1,0,0,'[\"add\",\"del\"]'),
(59,55,7,'iframe01/:id','iframe01/2','iframe02','iframemm/iframe01','iframe02','Bowl',1,0,0,'[]'),
(61,55,8,'iframe03','iframe03','iframe03','iframemm/iframe03','iframe03','Bowl',1,0,0,'[\"addd\"]');

INSERT INTO `sys_role` VALUES
(25,'admin','admin','admin-desc','[51,52,53,54,55,58,59,61]','[51]',1,'2022-08-20 19:40:09');

INSERT INTO `sys_user` VALUES
(13110713,4,'admin','e0b141de1c8091be350d3fc80de66528','admin_avatar/1675917824369_968.jpg','13276605900','9947033242@qq.com',0,'[25]','2025-11-13 13:47:38',NULL),
(48351015,0,'wy','f4f70c0520c372d98f32261b010cce97','','13276605800','',0,'[]','2026-01-05 16:16:21',NULL);

INSERT INTO `drive_license_class` VALUES
(1,0,'A1','大型客车'),
(2,0,'A2','重型牵引挂车'),
(3,0,'B1','中型客车'),
(4,0,'B2','大型货车'),
(5,0,'C1','小型手动挡'),
(6,0,'C2','小型自动挡'),
(7,0,'C3','低速载货手动汽车'),
(8,0,'C4','三轮汽车'),
(9,0,'C5','残疾人小型自动挡载客汽车'),
(10,0,'C6','轻型牵引挂车'),
(15,1,'科一',NULL),
(16,1,'科二',NULL),
(17,1,'科三',NULL),
(18,1,'科四',NULL),
(19,2,'科一',NULL),
(20,2,'科二',NULL),
(21,2,'科三',NULL),
(22,2,'科四',NULL),
(23,3,'科一',NULL),
(24,3,'科二',NULL),
(25,3,'科三',NULL),
(26,3,'科四',NULL),
(27,4,'科一',NULL),
(28,4,'科二',NULL),
(29,4,'科三',NULL),
(30,4,'科四',NULL),
(31,5,'科一',NULL),
(32,5,'科二',NULL),
(33,5,'科三',NULL),
(34,5,'科四',NULL),
(35,6,'科一',NULL),
(36,6,'科二',NULL),
(37,6,'科三',NULL),
(38,6,'科四',NULL),
(39,7,'科一',NULL),
(40,7,'科二',NULL),
(41,7,'科三',NULL),
(42,7,'科四',NULL),
(43,8,'科一',NULL),
(44,8,'科二',NULL),
(45,8,'科三',NULL),
(46,8,'科四',NULL),
(47,9,'科一',NULL),
(48,9,'科二',NULL),
(49,9,'科三',NULL),
(50,9,'科四',NULL),
(51,10,'科一',NULL),
(52,10,'科二',NULL),
(53,10,'科三',NULL),
(54,10,'科四',NULL),
(55,1,'testaaa','testaa'),
(56,2,'bbbb','bbbb'),
(57,3,'cccc','cccc'),
(58,4,'dddd','dddd'),
(59,5,'eeee','eeee'),
(60,6,'ffff','ffff'),
(61,7,'gggg','gggg'),
(62,8,'hhhh','hhhh'),
(63,9,'iiii','iiii'),
(65,1,'kkkk','kkkk'),
(66,1,'mmmm','mmmm'),
(67,1,'llll','llll'),
(68,1,'nnnn','nnnn');






