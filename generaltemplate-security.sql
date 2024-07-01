-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.7.24-log - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  10.2.0.5704
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出  表 generaltemplate-security.attributes 结构
DROP TABLE IF EXISTS `attributes`;
CREATE TABLE IF NOT EXISTS `attributes` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `attr_name` varchar(100) DEFAULT NULL COMMENT '属性名称',
  `category_id` int(11) DEFAULT NULL COMMENT '分类ID',
  `category_level` int(11) DEFAULT NULL COMMENT '分类级别',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='商品属性表';

-- 正在导出表  generaltemplate-security.attributes 的数据：~3 rows (大约)
DELETE FROM `attributes`;
/*!40000 ALTER TABLE `attributes` DISABLE KEYS */;
INSERT INTO `attributes` (`id`, `attr_name`, `category_id`, `category_level`, `create_time`, `update_time`) VALUES
	(1, '打算', 19, 3, '2023-06-30 13:25:58', '2023-06-30 13:38:26'),
	(3, '大萨达', 15, 3, '2023-06-30 13:39:34', NULL),
	(4, '大打算2', 15, 3, '2023-06-30 13:39:59', NULL);
/*!40000 ALTER TABLE `attributes` ENABLE KEYS */;

-- 导出  表 generaltemplate-security.attributes_value 结构
DROP TABLE IF EXISTS `attributes_value`;
CREATE TABLE IF NOT EXISTS `attributes_value` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `attr_id` int(11) DEFAULT NULL COMMENT '属性ID',
  `value_name` varchar(50) DEFAULT NULL COMMENT '属性名称',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='商品属性值集合';

-- 正在导出表  generaltemplate-security.attributes_value 的数据：~8 rows (大约)
DELETE FROM `attributes_value`;
/*!40000 ALTER TABLE `attributes_value` DISABLE KEYS */;
INSERT INTO `attributes_value` (`id`, `attr_id`, `value_name`, `create_time`, `update_time`) VALUES
	(1, 1, '888大叔大婶', '2023-06-30 13:25:58', '2023-06-30 13:26:35'),
	(3, 1, '76576', '2023-06-30 13:29:16', NULL),
	(4, 1, '567567', '2023-06-30 13:29:16', NULL),
	(6, 1, '打算', '2023-06-30 13:38:26', NULL),
	(7, 3, '大打算1', '2023-06-30 13:39:34', NULL),
	(8, 3, '大打算2', '2023-06-30 13:39:34', NULL),
	(9, 3, '大打算3', '2023-06-30 13:39:34', NULL),
	(10, 4, '大打算2', '2023-06-30 13:39:59', NULL);
/*!40000 ALTER TABLE `attributes_value` ENABLE KEYS */;

-- 导出  表 generaltemplate-security.category 结构
DROP TABLE IF EXISTS `category`;
CREATE TABLE IF NOT EXISTS `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `pid` int(11) NOT NULL DEFAULT '0' COMMENT '父级ID',
  `name` varchar(100) DEFAULT NULL COMMENT '类别名称',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 COMMENT='商品分类';

-- 正在导出表  generaltemplate-security.category 的数据：~20 rows (大约)
DELETE FROM `category`;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` (`id`, `pid`, `name`, `create_time`, `update_time`) VALUES
	(1, 0, '图书/音像/电子书刊', '2023-06-30 12:20:11', '2023-06-30 12:20:35'),
	(2, 0, '手机', '2023-06-30 12:20:43', '2023-06-30 12:20:51'),
	(3, 0, '家用电器', '2023-06-30 12:20:55', '2023-06-30 12:21:06'),
	(4, 0, '数码', '2023-06-30 12:30:44', '2023-06-30 12:30:46'),
	(5, 0, '家居家装', '2023-06-30 12:31:00', NULL),
	(6, 0, '电脑办公', '2023-06-30 12:31:13', '2023-06-30 12:31:15'),
	(7, 0, '厨具', '2023-06-30 12:31:19', '2023-06-30 12:31:28'),
	(8, 0, '个护化妆', '2023-06-30 12:31:32', '2023-06-30 12:31:39'),
	(9, 0, '服饰内衣', '2023-06-30 12:31:48', '2023-06-30 12:31:53'),
	(10, 0, '钟表', '2023-06-30 12:32:07', '2023-06-30 12:32:09'),
	(11, 1, '电子书刊', '2023-06-30 12:37:02', '2023-06-30 12:37:05'),
	(12, 1, '音像', '2023-06-30 12:37:10', '2023-06-30 12:37:20'),
	(13, 1, '英文原版', '2023-06-30 12:37:24', '2023-06-30 12:37:34'),
	(14, 1, '文艺', '2023-06-30 12:37:49', '2023-06-30 12:37:58'),
	(15, 11, '电子书', '2023-06-30 12:39:56', '2023-06-30 12:40:01'),
	(16, 11, '网络原创', '2023-06-30 12:40:06', '2023-06-30 12:40:17'),
	(17, 11, '数字杂志', '2023-06-30 12:40:22', '2023-06-30 12:40:33'),
	(18, 11, '多媒体图书', '2023-06-30 12:40:37', '2023-06-30 12:40:47'),
	(19, 12, '音乐', '2023-06-30 12:40:53', '2023-06-30 12:41:11'),
	(20, 12, '影视', '2023-06-30 12:41:16', '2023-06-30 12:41:26');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;

-- 导出  表 generaltemplate-security.sku_data 结构
DROP TABLE IF EXISTS `sku_data`;
CREATE TABLE IF NOT EXISTS `sku_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `category3_id` int(11) DEFAULT NULL COMMENT '分类ID',
  `spu_id` int(11) DEFAULT NULL COMMENT '已有的SPU的ID',
  `tm_id` int(11) DEFAULT NULL COMMENT 'SPU品牌的ID',
  `sku_name` varchar(255) DEFAULT NULL COMMENT 'sku名字',
  `price` varchar(100) DEFAULT NULL COMMENT 'sku价格',
  `weight` varchar(100) DEFAULT NULL COMMENT 'sku重量',
  `sku_desc` text COMMENT 'sku的描述',
  `sku_default_img` varchar(255) DEFAULT NULL COMMENT 'sku图片地址',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='sku商品信息表';

-- 正在导出表  generaltemplate-security.sku_data 的数据：~0 rows (大约)
DELETE FROM `sku_data`;
/*!40000 ALTER TABLE `sku_data` DISABLE KEYS */;
INSERT INTO `sku_data` (`id`, `category3_id`, `spu_id`, `tm_id`, `sku_name`, `price`, `weight`, `sku_desc`, `sku_default_img`, `create_time`, `update_time`) VALUES
	(1, 15, 2, 2, 'dsad', '6', '6', '6', '', '2023-06-30 17:10:26', NULL);
/*!40000 ALTER TABLE `sku_data` ENABLE KEYS */;

-- 导出  表 generaltemplate-security.spu_data 结构
DROP TABLE IF EXISTS `spu_data`;
CREATE TABLE IF NOT EXISTS `spu_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `category3_id` int(11) DEFAULT NULL COMMENT '收集三级分类的ID',
  `tm_id` int(11) DEFAULT NULL COMMENT '品牌的ID',
  `spu_name` varchar(255) DEFAULT NULL COMMENT 'spu名字',
  `description` text COMMENT 'spu的描述',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='spu商品信息表';

-- 正在导出表  generaltemplate-security.spu_data 的数据：~2 rows (大约)
DELETE FROM `spu_data`;
/*!40000 ALTER TABLE `spu_data` DISABLE KEYS */;
INSERT INTO `spu_data` (`id`, `category3_id`, `tm_id`, `spu_name`, `description`, `create_time`, `update_time`) VALUES
	(1, 15, 2, '1', '1', '2023-06-30 16:41:42', NULL),
	(2, 15, 2, '5', '55', '2023-06-30 16:51:01', NULL);
/*!40000 ALTER TABLE `spu_data` ENABLE KEYS */;

-- 导出  表 generaltemplate-security.spu_image 结构
DROP TABLE IF EXISTS `spu_image`;
CREATE TABLE IF NOT EXISTS `spu_image` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `spu_id` int(11) DEFAULT NULL COMMENT 'SPU的ID',
  `img_name` varchar(255) DEFAULT NULL COMMENT '图片名称',
  `img_url` varchar(255) DEFAULT NULL COMMENT '图片路径',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='spu图片表';

-- 正在导出表  generaltemplate-security.spu_image 的数据：~6 rows (大约)
DELETE FROM `spu_image`;
/*!40000 ALTER TABLE `spu_image` DISABLE KEYS */;
INSERT INTO `spu_image` (`id`, `spu_id`, `img_name`, `img_url`, `create_time`, `update_time`) VALUES
	(1, 1, '头像.png', 'http://127.0.0.1:5174/bcc17d6d-e532-47e9-9d77-24b8038175e4', '2023-06-30 16:41:42', NULL),
	(2, 2, '头像.png', 'http://localhost:9090/file/5f49984a484f4bcdae49fe0fa5519d35.png', '2023-06-30 16:51:01', NULL),
	(3, 2, '头像.png', 'http://localhost:9090/file/5f49984a484f4bcdae49fe0fa5519d35.png', '2023-06-30 16:57:10', NULL),
	(4, 1, '头像.png', 'http://localhost:9090/file/efd994e5644d46ccb0d611e8329364af.png', '2023-06-30 16:57:19', NULL),
	(5, 1, '头像.png', 'http://127.0.0.1:5174/bcc17d6d-e532-47e9-9d77-24b8038175e4', '2023-06-30 16:57:26', NULL),
	(6, 1, '头像.png', 'http://localhost:9090/file/efd994e5644d46ccb0d611e8329364af.png', '2023-06-30 16:57:26', NULL);
/*!40000 ALTER TABLE `spu_image` ENABLE KEYS */;

-- 导出  表 generaltemplate-security.spu_sale_attr 结构
DROP TABLE IF EXISTS `spu_sale_attr`;
CREATE TABLE IF NOT EXISTS `spu_sale_attr` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `spu_id` int(11) DEFAULT NULL COMMENT '已有的SPU的ID',
  `base_sale_attr_id` int(11) DEFAULT NULL COMMENT '已有的属性的ID',
  `sale_attr_name` varchar(255) DEFAULT NULL COMMENT '已有的属性的名称',
  `is_checked` varchar(100) DEFAULT NULL COMMENT '是否选中',
  `sale_attr_value_name` varchar(100) DEFAULT NULL COMMENT '值名称',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='已有的销售属性值';

-- 正在导出表  generaltemplate-security.spu_sale_attr 的数据：~2 rows (大约)
DELETE FROM `spu_sale_attr`;
/*!40000 ALTER TABLE `spu_sale_attr` DISABLE KEYS */;
INSERT INTO `spu_sale_attr` (`id`, `spu_id`, `base_sale_attr_id`, `sale_attr_name`, `is_checked`, `sale_attr_value_name`, `create_time`, `update_time`) VALUES
	(1, 1, 1, '打算', '0', NULL, '2023-06-30 16:41:42', NULL),
	(2, 2, 1, '打算', '0', NULL, '2023-06-30 16:51:01', NULL);
/*!40000 ALTER TABLE `spu_sale_attr` ENABLE KEYS */;

-- 导出  表 generaltemplate-security.sys_dict 结构
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE IF NOT EXISTS `sys_dict` (
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '名称',
  `value` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '内容',
  `type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '类型'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- 正在导出表  generaltemplate-security.sys_dict 的数据：~14 rows (大约)
DELETE FROM `sys_dict`;
/*!40000 ALTER TABLE `sys_dict` DISABLE KEYS */;
INSERT INTO `sys_dict` (`name`, `value`, `type`) VALUES
	('user', 'el-icon-user', 'icon'),
	('menu', 'el-icon-menu', 'icon'),
	('document', 'el-icon-document', 'icon'),
	('home', 'el-icon-s-home', 'icon'),
	('setting', 'el-icon-setting', 'icon'),
	('outlin', 'el-icon-more-outline', 'icon'),
	('upload', 'el-icon-upload', 'icon'),
	('picutre', 'el-icon-picture\r\n', 'icon'),
	('files', 'el-icon-files', 'icon'),
	('custom', 'el-icon-s-custom', 'icon'),
	('edit', 'el-icon-edit-outline', 'icon'),
	('date', 'el-icon-date', 'icon'),
	('document-add', 'el-icon-document-add', 'icon'),
	('document-list', 'el-icon-document-checked', 'icon');
/*!40000 ALTER TABLE `sys_dict` ENABLE KEYS */;

-- 导出  表 generaltemplate-security.sys_file 结构
DROP TABLE IF EXISTS `sys_file`;
CREATE TABLE IF NOT EXISTS `sys_file` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '文件名称',
  `type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '文件类型',
  `size` bigint(20) DEFAULT NULL COMMENT '文件大小kb',
  `url` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '下载链接',
  `is_delete` tinyint(1) DEFAULT '0' COMMENT '是否删除',
  `enable` tinyint(1) DEFAULT '0' COMMENT '是否禁用',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- 正在导出表  generaltemplate-security.sys_file 的数据：~8 rows (大约)
DELETE FROM `sys_file`;
/*!40000 ALTER TABLE `sys_file` DISABLE KEYS */;
INSERT INTO `sys_file` (`id`, `name`, `type`, `size`, `url`, `is_delete`, `enable`, `create_time`) VALUES
	(38, 'QQ图片20220525205125.jpg', 'jpg', 33, 'http://localhost:9090/file/1ce0734dc2304d02806ba290b3596734.jpg', 0, 0, '2022-05-25 20:51:35'),
	(39, '头像.png', 'png', 404, 'http://localhost:9090/file/4a7b39afbc734ee5b85e77f6fdab2d3a.png', 0, 0, '2023-06-30 11:31:26'),
	(40, '头像.png', 'png', 404, 'http://localhost:9090/file/aec51aa796f84d9a8f5d7296fbae997e.png', 0, 0, '2023-06-30 11:33:17'),
	(41, '头像.png', 'png', 404, 'http://localhost:9090/file/f923945a460e4359bdb37e78fd576d3b.png', 0, 0, '2023-06-30 11:35:17'),
	(42, '安全上岗合格证.png', 'png', 1744, 'http://localhost:9090/file/f220d497e5054e34b27862dfd10b5ba3.png', 0, 0, '2023-06-30 11:35:47'),
	(43, '头像.png', 'png', 404, 'http://localhost:9090/file/864c902c72614f2b9747bd655ca4096b.png', 0, 0, '2023-06-30 15:07:29'),
	(44, '头像.png', 'png', 404, 'http://localhost:9090/file/5f49984a484f4bcdae49fe0fa5519d35.png', 0, 0, '2023-06-30 16:50:36'),
	(45, '头像.png', 'png', 404, 'http://localhost:9090/file/efd994e5644d46ccb0d611e8329364af.png', 0, 0, '2023-06-30 16:57:18');
/*!40000 ALTER TABLE `sys_file` ENABLE KEYS */;

-- 导出  表 generaltemplate-security.sys_menu 结构
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE IF NOT EXISTS `sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '菜单名',
  `path` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'url',
  `icon` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '图标',
  `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述',
  `pid` int(11) DEFAULT NULL COMMENT '父级id',
  `view_path` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '页面路径',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- 正在导出表  generaltemplate-security.sys_menu 的数据：~9 rows (大约)
DELETE FROM `sys_menu`;
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
INSERT INTO `sys_menu` (`id`, `name`, `path`, `icon`, `description`, `pid`, `view_path`) VALUES
	(3, '主页', '/home', 'el-icon-s-home', '主页', NULL, 'Home'),
	(4, '系统管理', NULL, 'el-icon-menu', NULL, NULL, NULL),
	(5, '用户管理', '/user', 'el-icon-user', '用户', 4, 'User'),
	(6, '文件管理', '/file', 'el-icon-document', '文件', 4, 'File'),
	(7, '权限管理', '/role', 'el-icon-s-custom', '权限', 4, 'Role'),
	(8, '菜单管理', '/menus', 'el-icon-menu', '菜单', 4, 'Menus'),
	(9, '修改信息', '/person', 'el-icon-edit-outline', '修改个人信息', 10, 'Person'),
	(10, '个人信息', NULL, 'el-icon-user', '个人信息', NULL, NULL),
	(11, '修改密码', '/pwd', 'el-icon-edit-outline', '修改密码', 10, 'EditPwd');
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;

-- 导出  表 generaltemplate-security.sys_menu_info 结构
DROP TABLE IF EXISTS `sys_menu_info`;
CREATE TABLE IF NOT EXISTS `sys_menu_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '菜单名',
  `path` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'url',
  `icon` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '图标',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述',
  `pid` int(11) DEFAULT NULL COMMENT '父级id',
  `view_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '页面路径',
  `create_time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='菜单信息表';

-- 正在导出表  generaltemplate-security.sys_menu_info 的数据：~9 rows (大约)
DELETE FROM `sys_menu_info`;
/*!40000 ALTER TABLE `sys_menu_info` DISABLE KEYS */;
INSERT INTO `sys_menu_info` (`id`, `name`, `path`, `icon`, `description`, `pid`, `view_path`, `create_time`) VALUES
	(3, '主页', '/home', 'el-icon-s-home', '主页', NULL, 'Home', NULL),
	(4, '系统管理', NULL, 'el-icon-menu', NULL, NULL, NULL, NULL),
	(5, '用户管理', '/user', 'el-icon-user', '用户', 4, 'User', NULL),
	(6, '文件管理', '/file', 'el-icon-document', '文件', 4, 'File', NULL),
	(7, '权限管理', '/role', 'el-icon-s-custom', '权限', 4, 'Role', NULL),
	(8, '菜单管理', '/menus', 'el-icon-menu', '菜单', 4, 'Menus', NULL),
	(9, '修改信息', '/person', 'el-icon-edit-outline', '修改个人信息', 10, 'Person', NULL),
	(10, '个人信息', NULL, 'el-icon-user', '个人信息', NULL, NULL, NULL),
	(11, '修改密码', '/pwd', 'el-icon-edit-outline', '修改密码', 10, 'EditPwd', NULL);
/*!40000 ALTER TABLE `sys_menu_info` ENABLE KEYS */;

-- 导出  表 generaltemplate-security.sys_permission 结构
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE IF NOT EXISTS `sys_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `pid` int(11) DEFAULT NULL COMMENT '父级ID',
  `name` varchar(50) DEFAULT NULL COMMENT '功能名称',
  `code` varchar(50) DEFAULT NULL COMMENT '功能按钮名称',
  `path` varchar(50) DEFAULT NULL COMMENT '资源路径',
  `comment` varchar(255) DEFAULT NULL COMMENT '备注',
  `icon` varchar(255) DEFAULT NULL COMMENT '图标',
  `level` varchar(255) DEFAULT NULL COMMENT '层级',
  `select` varchar(50) DEFAULT NULL COMMENT '是否显示',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `to_code` varchar(50) DEFAULT NULL COMMENT 'code值',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='菜单信息';

-- 正在导出表  generaltemplate-security.sys_permission 的数据：~5 rows (大约)
DELETE FROM `sys_permission`;
/*!40000 ALTER TABLE `sys_permission` DISABLE KEYS */;
INSERT INTO `sys_permission` (`id`, `pid`, `name`, `code`, `path`, `comment`, `icon`, `level`, `select`, `status`, `to_code`, `create_time`, `update_time`) VALUES
	(1, 0, '全部数据', NULL, NULL, NULL, NULL, '1', 'false', NULL, NULL, '2023-06-29 16:04:02', '2023-06-29 16:05:02'),
	(2, 1, '测试1', '测试1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2023-06-29 20:31:56', '2023-06-29 20:32:37'),
	(3, 1, '测试2', '测试2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2023-06-29 21:13:18', '2023-06-29 21:47:15'),
	(4, 3, '测试2-1', '测试2-1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2023-06-29 21:14:04', NULL),
	(6, 3, '测试2-2', '测试2-2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2023-06-29 22:16:27', NULL),
	(7, 6, '测试2-2-1', '测试2-2-1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2023-06-29 22:16:35', NULL);
/*!40000 ALTER TABLE `sys_permission` ENABLE KEYS */;

-- 导出  表 generaltemplate-security.sys_role 结构
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE IF NOT EXISTS `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '职位名称',
  `role_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- 正在导出表  generaltemplate-security.sys_role 的数据：~3 rows (大约)
DELETE FROM `sys_role`;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` (`id`, `name`, `role_name`, `create_time`, `update_time`) VALUES
	(1, 'ROLE_ADMIN', '管理员', '2023-06-30 10:19:36', '2023-06-30 10:19:37'),
	(2, 'ROLE_USER', '普通用户', '2023-06-30 10:19:38', '2023-06-30 10:19:38'),
	(4, 'ROLE_TEST', '婚纱店', '2023-06-30 10:19:38', '2023-06-30 10:19:39');
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;

-- 导出  表 generaltemplate-security.sys_role_menu 结构
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE IF NOT EXISTS `sys_role_menu` (
  `role_id` int(11) NOT NULL COMMENT '权限id',
  `menu_id` int(11) NOT NULL COMMENT '菜单id',
  PRIMARY KEY (`role_id`,`menu_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- 正在导出表  generaltemplate-security.sys_role_menu 的数据：~18 rows (大约)
DELETE FROM `sys_role_menu`;
/*!40000 ALTER TABLE `sys_role_menu` DISABLE KEYS */;
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES
	(1, 3),
	(1, 4),
	(1, 5),
	(1, 6),
	(1, 7),
	(1, 8),
	(1, 9),
	(1, 10),
	(1, 11),
	(2, 3),
	(2, 9),
	(2, 10),
	(2, 11),
	(3, 3),
	(3, 10),
	(5, 3),
	(5, 19),
	(5, 20);
/*!40000 ALTER TABLE `sys_role_menu` ENABLE KEYS */;

-- 导出  表 generaltemplate-security.sys_user 结构
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE IF NOT EXISTS `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户名',
  `name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户姓名',
  `password` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密码',
  `role_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '角色名称',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- 正在导出表  generaltemplate-security.sys_user 的数据：~6 rows (大约)
DELETE FROM `sys_user`;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` (`id`, `username`, `name`, `password`, `role_name`, `create_time`, `update_time`) VALUES
	(1, 'admin', '超级管理员', '$2a$10$QD0IJwMCHObc75Rr.GGJ.uk3Pr6c5KNWKKtULBKrIGMQcteoUDrKy', NULL, '2022-03-01 21:22:11', '2023-06-30 02:22:55'),
	(25, 'testAdmin', '软件测试工程师', '$2a$10$GEkA0uU4T5KVZSYOcCPc/.8Aye5DasliNOZHFszBTWbVcyxlAOYqe', NULL, '2022-05-25 21:47:09', '2023-06-30 02:25:35'),
	(26, 'guest', NULL, '$2a$10$q1i5uOxvUlDv2KrYWb3MEetp802WNxEkvt.e7EL50KdmsfW4iQLsK', NULL, '2022-05-25 21:52:46', NULL),
	(28, 'abc123', NULL, '$2a$10$RhSzeSTQPnyTpen8zBNo1eeBPUEnShYhgEnNzXxvQilf4azR0BU2e', NULL, '2022-06-09 19:52:57', NULL),
	(29, 'yangAdmin', NULL, '$2a$10$QD0IJwMCHObc75Rr.GGJ.uk3Pr6c5KNWKKtULBKrIGMQcteoUDrKy', NULL, '2023-06-28 23:30:00', NULL);
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;

-- 导出  表 generaltemplate-security.sys_user_role 结构
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE IF NOT EXISTS `sys_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `role_id` int(11) NOT NULL COMMENT '权限id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- 正在导出表  generaltemplate-security.sys_user_role 的数据：~7 rows (大约)
DELETE FROM `sys_user_role`;
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`) VALUES
	(7, 25, 2),
	(14, 26, 2),
	(15, 26, 3),
	(16, 28, 3),
	(17, 28, 2),
	(18, 1, 1),
	(19, 1, 2),
	(20, 29, 2),
	(21, 30, 2);
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;

-- 导出  表 generaltemplate-security.trademark 结构
DROP TABLE IF EXISTS `trademark`;
CREATE TABLE IF NOT EXISTS `trademark` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `tm_name` varchar(100) DEFAULT NULL COMMENT '品牌名称',
  `logo_url` varchar(255) DEFAULT NULL COMMENT '品牌LOGO',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='商品品牌表';

-- 正在导出表  generaltemplate-security.trademark 的数据：~2 rows (大约)
DELETE FROM `trademark`;
/*!40000 ALTER TABLE `trademark` DISABLE KEYS */;
INSERT INTO `trademark` (`id`, `tm_name`, `logo_url`, `create_time`, `update_time`) VALUES
	(2, '擦拭大发大水发大水', 'http://localhost:9090/file/f220d497e5054e34b27862dfd10b5ba3.png', '2023-06-30 11:35:23', NULL),
	(3, '大叔大婶', 'http://localhost:9090/file/864c902c72614f2b9747bd655ca4096b.png', '2023-06-30 15:07:30', NULL);
/*!40000 ALTER TABLE `trademark` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
