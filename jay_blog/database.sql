USE jay_blog;
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `userName` varchar(32) DEFAULT NULL COMMENT '用户名',
  `passWord` varchar(32) DEFAULT NULL COMMENT '密码',
  `user_sex` varchar(32) DEFAULT NULL,
  `nick_name` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('28', '测试', '123', 'MAN', '2323');

DROP TABLE IF EXISTS mto_channels;
CREATE TABLE `mto_channels`(
  id int(11) NOT NULL auto_increment COMMENT '主键id',
  key_   VARCHAR(255) DEFAULT NULL COMMENT '唯一关键字',
  `name` VARCHAR(255) DEFAULT NULL COMMENT '名称',
  `status` int(11) NOT NULL COMMENT '开启状态 0 开启，1 关闭',
  PRIMARY KEY(`id`),
  UNIQUE KEY key_index(key_)
) ENGINE=INNODB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT '模块/内容分组表';
BEGIN;
INSERT INTO `mto_channels` VALUES ('1', 'banner', 'banner', '1'), ('2', 'blog', '博客', '0'), ('3', 'questions', '问答', '0'), ('4', 'share', '分享', '0');
COMMIT;

DROP TABLE IF EXISTS mto_comments;
CREATE TABLE mto_comments(
  id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  author_id BIGINT(20) DEFAULT NULL COMMENT '评论人id',
  content VARCHAR(255) DEFAULT NULL COMMENT '评论内容',
  created datetime DEFAULT NULL  COMMENT '评论时间',
  pid BIGINT(20) NOT NULL COMMENT '父评论ID',
  status INT(11) NOT NULL COMMENT '评论状态',
  to_id BIGINT(20) NOT NULL COMMENT '所属内容ID',
  PRIMARY KEY(`id`)
)ENGINE=INNODB DEFAULT charset=utf8 COMMENT '评论表';

DROP TABLE IF EXISTS mto_config;
CREATE TABLE mto_config(
  id BIGINT(20) NOT NULL auto_increment COMMENT '主键id',
  key_ VARCHAR(255) DEFAULT NULL COMMENT '唯一关键字',
  type VARCHAR(255) DEFAULT NULL COMMENT '配置类型',
  value VARCHAR(255) DEFAULT NULL COMMENT '配置值',
  PRIMARY KEY(id),
  UNIQUE KEY key_index(`key_`)
)ENGINE=INNODB AUTO_INCREMENT=17 DEFAULT CHARSET=UTF8 COMMENT '系统配置表';
BEGIN;
INSERT INTO `mto_config` VALUES (1, 'site_name', '0', 'Mtons');
INSERT INTO `mto_config` VALUES (3, 'site_domain', '0', 'http://mtons.com');
INSERT INTO `mto_config` VALUES (4, 'site_keywords', '0', 'mtons,博客,社区');
INSERT INTO `mto_config` VALUES (5, 'site_description', '0', 'Mtons, 做一个有内涵的技术社区');
INSERT INTO `mto_config` VALUES (6, 'site_metas', '0', '');
INSERT INTO `mto_config` VALUES (7, 'site_copyright', '0', 'Copyright © Mtons');
INSERT INTO `mto_config` VALUES (8, 'site_icp', '0', '');
INSERT INTO `mto_config` VALUES (11, 'site_oauth_qq', '0', '');
INSERT INTO `mto_config` VALUES (12, 'qq_app_id', '0', '');
INSERT INTO `mto_config` VALUES (13, 'qq_app_key', '0', '');
INSERT INTO `mto_config` VALUES (14, 'site_oauth_weibo', '0', '');
INSERT INTO `mto_config` VALUES (15, 'weibo_client_id', '0', '');
INSERT INTO `mto_config` VALUES (16, 'weibo_client_sercret', '0', '');
COMMIT;
DROP TABLE IF EXISTS mto_favors;
CREATE TABLE mto_favors(
  id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  created datetime DEFAULT NULL COMMENT '创建时间',
  own_id BIGINT(20) DEFAULT NULL COMMENT '所属用户',
  post_id BIGINT(20) DEFAULT NULL COMMENT '内容ID',
  PRIMARY KEY(`id`)
)ENGINE=INNODB DEFAULT charset=utf8 COMMENT '喜欢/收藏表';

DROP TABLE IF EXISTS mto_feeds;
CREATE TABLE mto_feeds(
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  author_id BIGINT(20) DEFAULT NULL COMMENT '作者',
  created datetime DEFAULT NULL COMMENT '创建时间',
  own_id BIGINT(20) DEFAULT NULL COMMENT '所属用户id',
  post_id BIGINT(20) DEFAULT NULL COMMENT '目标id',
  type INT(11) NOT NULL COMMENT '',
  PRIMARY KEY(id)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT '动态表';

DROP TABLE IF EXISTS mto_follows;
CREATE TABLE mto_follows(
  id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  created datetime DEFAULT NULL COMMENT '创建时间',
  follow_id BIGINT(20) NOT NULL COMMENT '关注用户Id',
  user_id BIGINT(20) NOT NULL COMMENT '所属用户Id',
  PRIMARY KEY (`id`),
  KEY `FKlbcc3hcj1cikyow8cvlk1eupe` (`follow_id`),
  KEY `FKso66aluvvri4r5a5x3lh31t8s` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '我的关注';
DROP TABLE IF EXISTS mto_notify;
CREATE TABLE mto_notify(
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  created datetime DEFAULT NULL COMMENT '创建时间',
  event INT(11) NOT NULL COMMENT '事件',
  from_id BIGINT(20) DEFAULT NULL COMMENT '从',
  own_id BIGINT(20) DEFAULT NULL COMMENT '所属用户id',
  status INT(11) NOT NULL COMMENT '阅读状态',
  post_id BIGINT(20) NOT NULL COMMENT '关联文章id',
  PRIMARY KEY(`id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT '通知表';
DROP TABLE IF EXISTS mto_posts;
CREATE TABLE mto_posts(
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  author_id INT(11) DEFAULT NULL COMMENT '作者id',
  channel_id INT(11) DEFAULT NULL COMMENT '分组/模块id',
  comments INT(11) NOT NULL COMMENT '评论数',
  created datetime DEFAULT NULL COMMENT '创建时间',
  favors INT(11) NOT NULL COMMENT '喜欢数',
  featured INT(11) NOT NULL COMMENT '推荐状态',
  status INT(11) NOT NULL COMMENT '文章状态',
  summary VARCHAR(255) DEFAULT NULL COMMENT '摘要',
  tags VARCHAR(255) DEFAULT NULL COMMENT '标签，多个用逗号隔开',
  thumbnail VARCHAR(64) DEFAULT NULL COMMENT '预览图',
  title VARCHAR(64) DEFAULT NULL COMMENT '标题',
  views INT(11) NOT NULL COMMENT '阅读数',
  weight INT(11) NOT NULL COMMENT '置顶状态',
  PRIMARY KEY(`id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT '内容表';
DROP TABLE IF EXISTS mto_posts_attribute;
CREATE TABLE mto_posts_attribute(
  id BIGINT(20) NOT NULL,
  content LONGTEXT COMMENT '内容'
)ENGINE=InnoDB DEFAULT charset=utf8;

DROP TABLE IF EXISTS mto_users;
CREATE TABLE mto_users(
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  created VARCHAR(128) DEFAULT NULL COMMENT '注册时间',
  email VARCHAR(128) DEFAULT NULL COMMENT '邮箱',
  last_login datetime DEFAULT NULL COMMENT '最近登录',
  mobile VARCHAR(11) DEFAULT NULL COMMENT '手机号',
  password VARCHAR(32) DEFAULT NULL COMMENT '密码',
  status INT(11) NOT NULL COMMENT '用户状态',
  username VARCHAR(64) DEFAULT NULL COMMENT '用户名',
  name VARCHAR(64) DEFAULT NULL COMMENT '用户姓名',
  avatar VARCHAR(255) DEFAULT '/assets/images/ava/default.png' COMMENT '头像',
  updated datetime DEFAULT NULL COMMENT '更新时间',
  gender INT(11) NOT NULL COMMENT '性别',
  role_id INT(11) DEFAULT NULL COMMENT '角色id',
  source INT(11) not NULL COMMENT '注册来源，主要用于区分第三方登录',
  active_email INT(11) NOT NULL COMMENT '邮箱激活状态',
  comments INT(11) NOT NULL COMMENT '发布评论数',
  fans INT(11) NOT NULL COMMENT '粉丝数',
  favors INT(11) NOT NULL COMMENT '收到的喜欢数',
  follows INT(11) NOT NULL COMMENT '关注人数',
  posts INT(11) not NULL COMMENT '文章数',
  signature VARCHAR(255) DEFAULT NULL COMMENT '个性签名',
  PRIMARY KEY(id)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT '用户表';
BEGIN;
INSERT INTO `mto_users` VALUES ('1', '2017-08-06 17:52:41', 'example@mtons.com', '2017-10-17 13:24:13', null, '3TGCQF25BLHU9R7IQUITN0FCC5', '0', 'admin', '小豆丁', '/dist/images/ava/default.png', '2017-07-26 11:08:36', '0', '1', '0', '0', '6', '-2', '0', '-2', '19', '');
COMMIT;


DROP TABLE if EXISTS mto_users_open_oauth;
CREATE TABLE mto_users_open_oauth(
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  access_token VARCHAR(255) DEFAULT NULL  COMMENT '访问令牌',
  expire_in VARCHAR(255) DEFAULT NULL COMMENT '',
  oauth_code VARCHAR(255) DEFAULT NULL COMMENT '第三方返回的code',
  oauth_type VARCHAR(255) DEFAULT NULL COMMENT '认证类型：QQ、新浪',
  oauth_user_id VARCHAR(255) DEFAULT NULL COMMENT '对应第三方用户ID',
  refresh_token VARCHAR(255) DEFAULT NULL COMMENT '',
  user_id BIGINT(20) DEFAULT NULL COMMENT '系统中的用户ID',
  PRIMARY KEY(id)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT '第三方开发授权登录';
DROP TABLE IF EXISTS mto_verify;
CREATE TABLE mto_verify(
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  code VARCHAR(60) NOT NULL COMMENT '验证码',
  created datetime NOT NULL COMMENT '创建时间',
  expired datetime NOT NULL COMMENT '过期时间',
  status INT(11) DEFAULT NULL COMMENT '状态  0正常，1关闭',
  target VARCHAR(96) DEFAULT NULL COMMENT '目标：邮箱',
  token  VARCHAR(255) DEFAULT NULL COMMENT '',
  type INT(11) DEFAULT NULL COMMENT '验证类型：注册验证，找回密码验证',
  user_id BIGINT(20) DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY(id),
  UNIQUE KEY `UK_m7p0b526c4xlgjn787t22om2g` (`user_id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT '验证码表';
DROP TABLE IF EXISTS shiro_permission;
CREATE TABLE shiro_permission(
  id BIGINT(20)  NOT NULL AUTO_INCREMENT,
  description VARCHAR(255) DEFAULT NULL COMMENT '',
  name VARCHAR(255)  NOT NULL COMMENT '权限值名称',
  parent_id BIGINT(20) DEFAULT NULL COMMENT '父id',
  version INT(11) DEFAULT NULL COMMENT '',
  weight INT(11) NOT NULL COMMENT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_89ve8ffuihnryt1nw4o2t1feu` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT '权限值表';


INSERT INTO `shiro_permission` VALUES ('1', '进入后台', 'admin', '0', '0', '0');
INSERT INTO `shiro_permission` VALUES ('2', '栏目管理', 'channel:list', '0', '0', '0');
INSERT INTO `shiro_permission` VALUES ('3', '编辑栏目', 'channel:update', '2', '0', '0');
INSERT INTO `shiro_permission` VALUES ('4', '删除栏目', 'channel:delete', '2', '0', '0');
INSERT INTO `shiro_permission` VALUES ('5', '文章管理', 'post:list', '0', '0', '0');
INSERT INTO `shiro_permission` VALUES ('6', '编辑文章', 'post:update', '5', '0', '0');
INSERT INTO `shiro_permission` VALUES ('7', '删除文章', 'post:delete', '5', '0', '0');
INSERT INTO `shiro_permission` VALUES ('8', '评论管理', 'comment:list', '0', '0', '0');
INSERT INTO `shiro_permission` VALUES ('10', '删除评论', 'comment:delete', '8', '0', '0');
INSERT INTO `shiro_permission` VALUES ('11', '用户管理', 'test:list', '0', '0', '0');
INSERT INTO `shiro_permission` VALUES ('12', '用户授权', 'test:role', '11', '0', '0');
INSERT INTO `shiro_permission` VALUES ('13', '修改密码', 'test:pwd', '11', '0', '0');
INSERT INTO `shiro_permission` VALUES ('14', '激活用户', 'test:open', '11', '0', '0');
INSERT INTO `shiro_permission` VALUES ('15', '关闭用户', 'test:close', '11', '0', '0');
INSERT INTO `shiro_permission` VALUES ('16', '角色管理', 'role:list', '0', '0', '0');
INSERT INTO `shiro_permission` VALUES ('17', '修改角色', 'role:update', '16', '0', '0');
INSERT INTO `shiro_permission` VALUES ('18', '删除角色', 'role:delete', '16', '0', '0');
INSERT INTO `shiro_permission` VALUES ('19', '系统配置', 'config:list', '0', '0', '0');
INSERT INTO `shiro_permission` VALUES ('20', '修改配置', 'config:update', '19', '0', '0');



DROP TABLE IF EXISTS shiro_role;
CREATE TABLE shiro_role(
  id BIGINT(20)  NOT NULL AUTO_INCREMENT,
  description VARCHAR(255) DEFAULT NULL COMMENT '角色描述',
  name VARCHAR(255) NOT NULL COMMENT '角色名称',
  status INT(11) NOT NULL COMMENT '角色状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT '角色表';

DROP TABLE IF EXISTS shiro_role_permission;
CREATE TABLE `shiro_role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `permission_id` bigint(20) DEFAULT NULL COMMENT '权限值id',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT '角色-权限值';

INSERT INTO `shiro_role_permission` VALUES ('1', '10', '1');
INSERT INTO `shiro_role_permission` VALUES ('2', '4', '1');
INSERT INTO `shiro_role_permission` VALUES ('3', '16', '1');
INSERT INTO `shiro_role_permission` VALUES ('4', '1', '1');
INSERT INTO `shiro_role_permission` VALUES ('5', '14', '1');
INSERT INTO `shiro_role_permission` VALUES ('6', '17', '1');
INSERT INTO `shiro_role_permission` VALUES ('7', '3', '1');
INSERT INTO `shiro_role_permission` VALUES ('8', '12', '1');
INSERT INTO `shiro_role_permission` VALUES ('9', '6', '1');
INSERT INTO `shiro_role_permission` VALUES ('10', '2', '1');
INSERT INTO `shiro_role_permission` VALUES ('11', '5', '1');
INSERT INTO `shiro_role_permission` VALUES ('12', '18', '1');
INSERT INTO `shiro_role_permission` VALUES ('13', '15', '1');
INSERT INTO `shiro_role_permission` VALUES ('14', '19', '1');
INSERT INTO `shiro_role_permission` VALUES ('15', '13', '1');
INSERT INTO `shiro_role_permission` VALUES ('16', '7', '1');
INSERT INTO `shiro_role_permission` VALUES ('17', '20', '1');
INSERT INTO `shiro_role_permission` VALUES ('18', '8', '1');
INSERT INTO `shiro_role_permission` VALUES ('19', '11', '1');

-- 用户角色表
DROP TABLE IF EXISTS shiro_user_role;
CREATE TABLE shiro_user_role(
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT '用户角色表';
INSERT INTO `shiro_user_role` VALUES ('1', '1', '1');
-- 系统日志
DROP TABLE IF EXISTS mto_log;
CREATE TABLE `mto_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) COMMENT '用户名',
  `operation` varchar(50) COMMENT '用户操作',
  `method` varchar(200) COMMENT '请求方法',
  `params` varchar(5000) COMMENT '请求参数',
  `time` bigint NOT NULL COMMENT '执行时长(毫秒)',
  `ip` varchar(64) COMMENT 'IP地址',
  `create_date` datetime COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=`InnoDB` DEFAULT CHARACTER SET utf8 COMMENT='系统日志';

