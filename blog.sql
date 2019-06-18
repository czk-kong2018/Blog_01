/*
 Navicat MySQL Data Transfer

 Source Server         : test
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : blog

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 18/06/2019 15:31:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `article_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文章标题',
  `article_profile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章简介',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文章url地址',
  `clik_like` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '点赞',
  `clik_unlike` int(10) UNSIGNED NOT NULL DEFAULT 0,
  `watch` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '浏览人次',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `own_id` int(10) UNSIGNED NOT NULL COMMENT '文章归属用户id,关联user_message表的user_id字段',
  PRIMARY KEY (`article_id`) USING BTREE,
  INDEX `fk_article_own_id`(`own_id`) USING BTREE,
  CONSTRAINT `fk_article_own_id` FOREIGN KEY (`own_id`) REFERENCES `user_message` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES (1, 'java反射机制', '对java反射的理解', 'http://localhost:8081/source/markdown/java_1.md', 2, 0, 0, '2019-05-15 12:59:17', 1);
INSERT INTO `article` VALUES (3, 'Servlet入门二', 'Servlet入门', 'http://localhost:8081/source/markdown/Servlet入门二_3.md', 0, 0, 0, '2019-05-14 18:14:08', 1);
INSERT INTO `article` VALUES (4, 'final用法', 'final用法', 'http://localhost:8081/source/markdown/final用法_4.md', 0, 0, 0, '2019-05-14 18:14:04', 1);
INSERT INTO `article` VALUES (5, 'mysql存储引擎', '回顾mysql的存储引擎', 'tmpnull', 0, 0, 0, '2019-05-09 22:08:50', 2);
INSERT INTO `article` VALUES (6, '深入理解java虚拟机', '深入理解java虚拟机', 'http://localhost:8081/source/markdown/深入理解Java虚拟机.md', 0, 0, 0, '2019-05-17 08:14:51', 2);
INSERT INTO `article` VALUES (7, '谈谈java类加载机制', '类加载机制', 'http://localhost:8081/source/markdown/谈谈Java类加载机制.md', 0, 0, 0, '2019-05-17 08:16:42', 2);
INSERT INTO `article` VALUES (8, '谈谈Tomcat', '# 谈谈 Tomcat 请求处理流程\n\n《谈谈 Tomcat 架构及启动过程[含部署]》已重新修订!（与本文在 GitHub 同一目录下）包括架构和 Tomcat Start 过程中的 `Mapper', 'http://localhost:8081/source/markdown/11560417592314.md', 0, 0, 0, '2019-06-16 15:25:48', 1);
INSERT INTO `article` VALUES (9, '文件系统', '# Ext4 文件系统简要总结\n\n最近在看操作系统的文件系统这块，感觉只有理论还是不行，所以就选了一种文件系统作一下拓展。\n\n选择 Ext4 的原因是： 他是 Linux 的主流文件系统 `ext*`', 'http://localhost:8081/source/markdown/41560441355961.md', 0, 0, 0, '2019-06-16 15:25:49', 4);
INSERT INTO `article` VALUES (10, '啊啊啊', '的撒多撒奥所爱仕达多撒奥所爱仕达多撒奥所爱仕达多撒奥所爱仕达多撒奥所爱仕达多撒奥所爱仕达多撒奥所爱仕达多撒奥所爱仕达多撒奥所爱仕达多撒奥所爱仕达多撒奥所爱仕达多撒奥所爱仕达多撒奥所爱仕达多撒奥所爱仕达', 'http://localhost:8081/source/markdown/201560741766949.md', 0, 0, 0, '2019-06-17 11:22:46', 20);

-- ----------------------------
-- Table structure for article_tag
-- ----------------------------
DROP TABLE IF EXISTS `article_tag`;
CREATE TABLE `article_tag`  (
  `article_id` int(10) UNSIGNED NOT NULL,
  `tag_id` int(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`article_id`, `tag_id`) USING BTREE,
  INDEX `fk_articleTag_tag_id`(`tag_id`) USING BTREE,
  CONSTRAINT `fk_articleTag_article_id` FOREIGN KEY (`article_id`) REFERENCES `article` (`article_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_articleTag_tag_id` FOREIGN KEY (`tag_id`) REFERENCES `tag` (`tag_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article_tag
-- ----------------------------
INSERT INTO `article_tag` VALUES (9, 1);
INSERT INTO `article_tag` VALUES (4, 2);
INSERT INTO `article_tag` VALUES (6, 2);
INSERT INTO `article_tag` VALUES (7, 2);
INSERT INTO `article_tag` VALUES (1, 8);
INSERT INTO `article_tag` VALUES (8, 8);
INSERT INTO `article_tag` VALUES (5, 10);

-- ----------------------------
-- Table structure for child_comment
-- ----------------------------
DROP TABLE IF EXISTS `child_comment`;
CREATE TABLE `child_comment`  (
  `child_comment_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '子评论自增id主键',
  `article_id` int(10) UNSIGNED NOT NULL COMMENT '做一个冗余方便查询',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '子评论内容',
  `response_user` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '回复的人',
  `author_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '写子评论的人',
  `comment_id` int(10) UNSIGNED NOT NULL COMMENT '所属父评论id',
  PRIMARY KEY (`child_comment_id`) USING BTREE,
  INDEX `author_name`(`author_name`) USING BTREE,
  INDEX `article_id`(`article_id`) USING BTREE,
  INDEX `comment_id`(`comment_id`) USING BTREE,
  INDEX `response_user`(`response_user`) USING BTREE,
  CONSTRAINT `child_comment_ibfk_1` FOREIGN KEY (`author_name`) REFERENCES `user_message` (`user_name`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `child_comment_ibfk_2` FOREIGN KEY (`article_id`) REFERENCES `article` (`article_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `child_comment_ibfk_3` FOREIGN KEY (`comment_id`) REFERENCES `comment_on` (`comment_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `child_comment_ibfk_4` FOREIGN KEY (`response_user`) REFERENCES `user_message` (`user_name`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 79 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of child_comment
-- ----------------------------
INSERT INTO `child_comment` VALUES (1, 6, '2019-06-03 22:06:42', '子评论测试1', '武球王', '阿扎尔', 4);
INSERT INTO `child_comment` VALUES (2, 6, '2019-06-03 22:06:40', '子评论测试2', 'czk', '阿扎尔', 4);
INSERT INTO `child_comment` VALUES (3, 6, '2019-06-03 22:20:10', '子评论3', '梅老板', 'czk', 4);
INSERT INTO `child_comment` VALUES (4, 3, '2019-06-04 15:13:21', '听说你是加泰球王', '武球王', '梅老板', 62);
INSERT INTO `child_comment` VALUES (21, 6, '2019-06-04 21:26:26', '我又来了', '阿扎尔', '梅老板', 4);
INSERT INTO `child_comment` VALUES (30, 6, '2019-06-04 22:43:32', '大家好', '阿扎尔', '梅老板', 67);
INSERT INTO `child_comment` VALUES (32, 6, '2019-06-04 22:45:43', '弟弟行为', 'czk', '梅老板', 4);
INSERT INTO `child_comment` VALUES (34, 6, '2019-06-05 14:04:39', '我确实牛逼', '阿扎尔', '梅老板', 48);
INSERT INTO `child_comment` VALUES (35, 6, '2019-06-05 14:05:03', '!!!', '阿扎尔', '梅老板', 43);
INSERT INTO `child_comment` VALUES (36, 6, '2019-06-05 14:06:43', '自己艾特自己干嘛\n', '阿扎尔', '梅老板', 13);
INSERT INTO `child_comment` VALUES (39, 6, '2019-06-05 21:23:35', '', '阿扎尔', '阿扎尔', 44);
INSERT INTO `child_comment` VALUES (40, 6, '2019-06-05 21:31:00', '笑什么弟弟', '阿扎尔', '武球王', 45);
INSERT INTO `child_comment` VALUES (41, 6, '2019-06-05 21:31:22', '我更牛逼', '梅老板', '武球王', 48);
INSERT INTO `child_comment` VALUES (42, 6, '2019-06-05 21:31:58', '是把 渣渣尔', '阿扎尔', '武球王', 48);
INSERT INTO `child_comment` VALUES (43, 6, '2019-06-05 21:32:40', '测试个毛线', '阿扎尔', '武球王', 70);
INSERT INTO `child_comment` VALUES (44, 6, '2019-06-05 21:33:55', '大佬们好', '阿扎尔', 'czk', 48);
INSERT INTO `child_comment` VALUES (45, 6, '2019-06-05 21:34:39', '哦豁', '武球王', 'czk', 70);
INSERT INTO `child_comment` VALUES (46, 6, '2019-06-05 21:38:10', '你好啊', '武球王', 'czk', 71);
INSERT INTO `child_comment` VALUES (47, 6, '2019-06-05 21:40:10', '梅老板真乃神人', '梅老板', 'czk', 65);
INSERT INTO `child_comment` VALUES (48, 6, '2019-06-05 21:40:58', '金球奖稳了', 'czk', 'czk', 65);
INSERT INTO `child_comment` VALUES (49, 6, '2019-06-05 21:43:28', '你没有梅老板牛逼', '阿扎尔', 'czk', 49);
INSERT INTO `child_comment` VALUES (50, 6, '2019-06-05 21:48:30', '金靴已到手', '梅老板', 'czk', 65);
INSERT INTO `child_comment` VALUES (51, 6, '2019-06-05 21:58:27', '哇哈', '阿扎尔', 'czk', 49);
INSERT INTO `child_comment` VALUES (52, 6, '2019-06-05 22:00:48', '出现在才', '阿扎尔', 'czk', 47);
INSERT INTO `child_comment` VALUES (53, 6, '2019-06-05 22:01:12', '浮点数', 'czk', 'czk', 72);
INSERT INTO `child_comment` VALUES (54, 6, '2019-06-05 22:08:05', '能不能局部刷新呢', '阿扎尔', 'czk', 47);
INSERT INTO `child_comment` VALUES (55, 6, '2019-06-05 22:08:26', 'nice!!!! 小老弟', '阿扎尔', 'czk', 46);
INSERT INTO `child_comment` VALUES (56, 6, '2019-06-05 22:08:54', '怎么..又', 'czk', 'czk', 46);
INSERT INTO `child_comment` VALUES (57, 6, '2019-06-05 22:09:09', '啊啊啊啊?', '阿扎尔', 'czk', 46);
INSERT INTO `child_comment` VALUES (58, 6, '2019-06-05 22:16:56', '局部刷新成功 yesh!', '阿扎尔', 'czk', 38);
INSERT INTO `child_comment` VALUES (59, 3, '2019-06-05 22:22:42', '梅老板才是球王', '梅老板', 'czk', 62);
INSERT INTO `child_comment` VALUES (60, 3, '2019-06-05 22:23:00', '梅老板牛逼', '阿扎尔', 'czk', 16);
INSERT INTO `child_comment` VALUES (61, 3, '2019-06-05 22:23:12', '梅老板牛逼', '阿扎尔', 'czk', 16);
INSERT INTO `child_comment` VALUES (62, 3, '2019-06-05 22:23:22', '刚刚脑抽了?', 'czk', 'czk', 16);
INSERT INTO `child_comment` VALUES (63, 3, '2019-06-05 22:27:13', '怎么费事', 'czk', 'czk', 16);
INSERT INTO `child_comment` VALUES (64, 3, '2019-06-05 22:29:06', '没bug了  ', 'czk', 'czk', 52);
INSERT INTO `child_comment` VALUES (65, 3, '2019-06-07 14:39:39', '啊啊啊 ', '梅老板', 'czk', 62);
INSERT INTO `child_comment` VALUES (68, 1, '2019-06-08 21:56:23', '不踢也得踢', '梅老板', '梅老板', 3);
INSERT INTO `child_comment` VALUES (69, 6, '2019-06-08 21:57:12', '雅蠛蝶', 'czk', '梅老板', 48);
INSERT INTO `child_comment` VALUES (70, 6, '2019-06-11 15:42:18', '拦截器 评论测试', '梅老板', '梅老板', 69);
INSERT INTO `child_comment` VALUES (71, 3, '2019-06-11 23:50:22', '呀呀呀', '梅老板', '梅老板', 99);
INSERT INTO `child_comment` VALUES (72, 4, '2019-06-12 14:19:36', '啊啊啊', '小萌', '小萌', 100);
INSERT INTO `child_comment` VALUES (73, 5, '2019-06-12 14:31:41', '文章不存在！！', '小萌', '小萌', 101);
INSERT INTO `child_comment` VALUES (74, 6, '2019-06-13 15:38:20', '对K', '小萌', '小萌', 103);
INSERT INTO `child_comment` VALUES (75, 6, '2019-06-16 13:49:34', '对A', '小萌', '小萌', 103);
INSERT INTO `child_comment` VALUES (76, 9, '2019-06-16 16:11:41', '什么！！！！', '武球王', '武球王', 105);
INSERT INTO `child_comment` VALUES (77, 9, '2019-06-17 11:06:07', '的撒奥所多', '小萌', '小萌', 107);
INSERT INTO `child_comment` VALUES (78, 9, '2019-06-17 11:06:30', '的撒多撒sad', '武球王', '小萌', 106);

-- ----------------------------
-- Table structure for comment_on
-- ----------------------------
DROP TABLE IF EXISTS `comment_on`;
CREATE TABLE `comment_on`  (
  `comment_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '评论自增id主键',
  `article_id` int(10) UNSIGNED NOT NULL,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评论内容',
  `click_like` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '点赞',
  `click_unlike` int(10) UNSIGNED NOT NULL DEFAULT 0,
  `response_user` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '@的人',
  `author_id` int(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`comment_id`) USING BTREE,
  INDEX `fk_commentOn_response_user`(`response_user`) USING BTREE,
  INDEX `article_id`(`article_id`) USING BTREE,
  INDEX `fk_commentOn_author_id`(`author_id`) USING BTREE,
  CONSTRAINT `fk_commentOn_article_id` FOREIGN KEY (`article_id`) REFERENCES `article` (`article_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_commentOn_author_id` FOREIGN KEY (`author_id`) REFERENCES `user_message` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_commentOn_response_user` FOREIGN KEY (`response_user`) REFERENCES `user_message` (`user_name`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 108 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment_on
-- ----------------------------
INSERT INTO `comment_on` VALUES (1, 1, '2019-04-27 11:14:40', '渣渣尔啊 我写的文章怎么样', 0, 0, '阿扎尔', 1);
INSERT INTO `comment_on` VALUES (2, 1, '2019-04-27 11:16:24', '写得不错,出来踢球', 0, 0, '梅老板', 2);
INSERT INTO `comment_on` VALUES (3, 1, '2019-05-03 14:58:44', '不踢了  要睡觉', 0, 0, '阿扎尔', 1);
INSERT INTO `comment_on` VALUES (4, 6, '2019-05-17 08:59:33', '写得不错哦　扎扎尔', 0, 0, '阿扎尔', 1);
INSERT INTO `comment_on` VALUES (5, 6, '2019-05-17 09:01:19', '测试评论功能', 0, 0, '阿扎尔', 1);
INSERT INTO `comment_on` VALUES (6, 6, '2019-05-17 09:05:42', '出来踢球', 0, 0, '梅老板', 2);
INSERT INTO `comment_on` VALUES (11, 6, '2019-05-17 09:17:05', '我评论我自己?', 0, 0, '阿扎尔', 2);
INSERT INTO `comment_on` VALUES (12, 6, '2019-05-17 09:17:17', '我继续评论我自己', 0, 0, '阿扎尔', 2);
INSERT INTO `comment_on` VALUES (13, 6, '2019-05-17 09:19:44', '又出问题了??', 0, 0, '阿扎尔', 2);
INSERT INTO `comment_on` VALUES (16, 3, '2019-05-17 09:32:50', '测试评论', 0, 0, '梅老板', 2);
INSERT INTO `comment_on` VALUES (28, 6, '2019-05-17 10:19:19', '为什么点击确定没有刷新啊！', 0, 0, '梅老板', 2);
INSERT INTO `comment_on` VALUES (29, 1, '2019-05-17 10:20:31', '梅老板我来测试评论了', 0, 0, '梅老板', 2);
INSERT INTO `comment_on` VALUES (33, 6, '2019-05-17 21:48:34', '继续测试', 0, 0, '阿扎尔', 1);
INSERT INTO `comment_on` VALUES (34, 6, '2019-05-17 21:49:08', '明白', 0, 0, '阿扎尔', 1);
INSERT INTO `comment_on` VALUES (35, 6, '2019-05-17 21:53:37', '老子又来测试评论了', 0, 0, '阿扎尔', 2);
INSERT INTO `comment_on` VALUES (36, 6, '2019-05-17 21:54:05', '奇了个怪了', 0, 0, '梅老板', 2);
INSERT INTO `comment_on` VALUES (37, 6, '2019-05-17 21:54:21', '这又特么成功了?', 0, 0, '阿扎尔', 2);
INSERT INTO `comment_on` VALUES (38, 6, '2019-05-17 21:54:50', '每次总是出现点小问题', 0, 0, '阿扎尔', 2);
INSERT INTO `comment_on` VALUES (39, 6, '2019-05-17 21:55:21', '怎么肥事', 0, 0, '梅老板', 2);
INSERT INTO `comment_on` VALUES (40, 6, '2019-05-17 21:55:43', '怎么不会自动刷新', 0, 0, '阿扎尔', 2);
INSERT INTO `comment_on` VALUES (41, 6, '2019-05-17 21:57:41', '继续测试评论', 0, 0, '阿扎尔', 1);
INSERT INTO `comment_on` VALUES (42, 6, '2019-05-17 21:58:03', '继续测试', 0, 0, '梅老板', 2);
INSERT INTO `comment_on` VALUES (43, 6, '2019-05-17 21:58:13', '测试个鬼', 0, 0, '阿扎尔', 2);
INSERT INTO `comment_on` VALUES (44, 6, '2019-05-17 21:58:36', '怎么回事', 0, 0, '阿扎尔', 1);
INSERT INTO `comment_on` VALUES (45, 6, '2019-05-20 09:32:43', '哈哈哈哈哈', 0, 0, '阿扎尔', 2);
INSERT INTO `comment_on` VALUES (46, 6, '2019-05-20 09:33:18', '莫名其妙', 0, 0, '阿扎尔', 2);
INSERT INTO `comment_on` VALUES (47, 6, '2019-05-20 09:34:03', '怎么老是有奇怪的问题\n', 0, 0, '阿扎尔', 2);
INSERT INTO `comment_on` VALUES (48, 6, '2019-05-20 09:34:31', '梅老板　牛逼', 0, 0, '梅老板', 2);
INSERT INTO `comment_on` VALUES (49, 6, '2019-05-20 09:34:44', '我也牛逼', 0, 0, '阿扎尔', 2);
INSERT INTO `comment_on` VALUES (50, 7, '2019-05-20 09:35:17', '大家好　我是渣渣灰', 0, 0, '阿扎尔', 2);
INSERT INTO `comment_on` VALUES (51, 3, '2019-05-20 10:40:08', '继续测试', 0, 0, '梅老板', 2);
INSERT INTO `comment_on` VALUES (52, 3, '2019-05-20 10:40:31', '老是有点小bug', 0, 0, '梅老板', 2);
INSERT INTO `comment_on` VALUES (58, 1, '2019-05-20 11:32:34', '测试通知消息', 0, 0, '梅老板', 2);
INSERT INTO `comment_on` VALUES (59, 1, '2019-05-20 11:33:09', '继续测试一下　通知', 0, 0, '梅老板', 2);
INSERT INTO `comment_on` VALUES (60, 4, '2019-05-20 11:33:41', '换一篇文章测试', 0, 0, '梅老板', 2);
INSERT INTO `comment_on` VALUES (61, 4, '2019-05-20 11:34:38', '看看bug的原因\n', 0, 0, '梅老板', 2);
INSERT INTO `comment_on` VALUES (62, 3, '2019-05-20 11:35:46', '测试+1', 0, 0, '梅老板', 2);
INSERT INTO `comment_on` VALUES (63, 7, '2019-05-20 11:36:07', '测试+2', 0, 0, '阿扎尔', 2);
INSERT INTO `comment_on` VALUES (64, 7, '2019-05-20 21:05:15', '测试+3', 0, 0, '阿扎尔', 2);
INSERT INTO `comment_on` VALUES (65, 6, '2019-05-20 21:51:12', '你牛逼个头', 0, 0, '阿扎尔', 1);
INSERT INTO `comment_on` VALUES (67, 6, '2019-05-23 23:55:27', '测试+5\n', 0, 0, '阿扎尔', 2);
INSERT INTO `comment_on` VALUES (68, 4, '2019-05-31 10:48:03', 'dsad ', 0, 0, '梅老板', 2);
INSERT INTO `comment_on` VALUES (69, 6, '2019-06-04 18:36:17', '加了子评论 父评论还能用吗', 0, 0, '阿扎尔', 1);
INSERT INTO `comment_on` VALUES (70, 6, '2019-06-05 14:05:21', '哈喽', 0, 0, '阿扎尔', 1);
INSERT INTO `comment_on` VALUES (71, 6, '2019-06-05 21:32:17', '你好啊', 0, 0, '阿扎尔', 4);
INSERT INTO `comment_on` VALUES (72, 6, '2019-06-05 21:34:26', '天外飞仙', 0, 0, '阿扎尔', 6);
INSERT INTO `comment_on` VALUES (73, 3, '2019-06-05 22:24:07', '啊哈?', 0, 0, '梅老板', 6);
INSERT INTO `comment_on` VALUES (74, 3, '2019-06-05 22:24:15', '嗯哼?', 0, 0, '梅老板', 6);
INSERT INTO `comment_on` VALUES (75, 3, '2019-06-05 22:24:27', '我擦?', 0, 0, '梅老板', 6);
INSERT INTO `comment_on` VALUES (76, 3, '2019-06-05 22:25:57', '我??', 0, 0, '梅老板', 6);
INSERT INTO `comment_on` VALUES (77, 3, '2019-06-05 22:26:45', '好了好了', 0, 0, '梅老板', 6);
INSERT INTO `comment_on` VALUES (78, 3, '2019-06-05 22:28:20', '来了来了 是那个男人', 0, 0, '梅老板', 6);
INSERT INTO `comment_on` VALUES (83, 3, '2019-06-07 14:23:30', '梅老板牛逼', 0, 0, '梅老板', 6);
INSERT INTO `comment_on` VALUES (84, 3, '2019-06-07 14:31:49', 'very good', 0, 0, '梅老板', 6);
INSERT INTO `comment_on` VALUES (85, 3, '2019-06-07 14:34:16', '测试滚动', 0, 0, '梅老板', 6);
INSERT INTO `comment_on` VALUES (86, 3, '2019-06-07 14:35:02', '继续测试滚动', 0, 0, '梅老板', 6);
INSERT INTO `comment_on` VALUES (87, 3, '2019-06-07 14:36:39', '000000', 0, 0, '梅老板', 6);
INSERT INTO `comment_on` VALUES (88, 3, '2019-06-07 14:36:48', '....', 0, 0, '梅老板', 6);
INSERT INTO `comment_on` VALUES (89, 3, '2019-06-07 14:37:08', '....', 0, 0, '梅老板', 6);
INSERT INTO `comment_on` VALUES (90, 3, '2019-06-07 14:37:25', '2312123', 0, 0, '梅老板', 6);
INSERT INTO `comment_on` VALUES (91, 3, '2019-06-07 14:37:50', 'vcxv', 0, 0, '梅老板', 6);
INSERT INTO `comment_on` VALUES (92, 3, '2019-06-07 14:38:21', '啊啊啊啊', 0, 0, '梅老板', 6);
INSERT INTO `comment_on` VALUES (93, 3, '2019-06-07 14:39:12', 'vcv', 0, 0, '梅老板', 6);
INSERT INTO `comment_on` VALUES (98, 3, '2019-06-11 23:36:27', '啊啊 啊', 0, 0, '梅老板', 1);
INSERT INTO `comment_on` VALUES (99, 3, '2019-06-11 23:39:27', '呀抱歉', 0, 0, '梅老板', 1);
INSERT INTO `comment_on` VALUES (100, 4, '2019-06-12 14:19:31', '测试成功！', 0, 0, '梅老板', 20);
INSERT INTO `comment_on` VALUES (101, 5, '2019-06-12 14:31:27', 'cxzcz', 0, 0, '阿扎尔', 20);
INSERT INTO `comment_on` VALUES (102, 6, '2019-06-12 23:01:44', 'xxx', 0, 0, '阿扎尔', 20);
INSERT INTO `comment_on` VALUES (103, 6, '2019-06-13 15:32:35', 'a a a', 0, 0, '阿扎尔', 20);
INSERT INTO `comment_on` VALUES (105, 9, '2019-06-16 16:11:31', '我来也！', 0, 0, '武球王', 4);
INSERT INTO `comment_on` VALUES (106, 9, '2019-06-16 16:11:50', '武球王是也！', 0, 0, '武球王', 4);
INSERT INTO `comment_on` VALUES (107, 9, '2019-06-17 11:04:45', 'xx', 0, 0, '武球王', 20);

-- ----------------------------
-- Table structure for focus
-- ----------------------------
DROP TABLE IF EXISTS `focus`;
CREATE TABLE `focus`  (
  `author_id` int(10) UNSIGNED NOT NULL COMMENT '作者id',
  `focus_person_id` int(10) UNSIGNED NOT NULL COMMENT '作者关注的人的id',
  PRIMARY KEY (`author_id`, `focus_person_id`) USING BTREE,
  INDEX `fk_focus_	focusPersonId`(`focus_person_id`) USING BTREE,
  CONSTRAINT `fk_focus_	focusPersonId` FOREIGN KEY (`focus_person_id`) REFERENCES `user_message` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_focus_authorId` FOREIGN KEY (`author_id`) REFERENCES `user_message` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of focus
-- ----------------------------
INSERT INTO `focus` VALUES (1, 2);

-- ----------------------------
-- Table structure for login
-- ----------------------------
DROP TABLE IF EXISTS `login`;
CREATE TABLE `login`  (
  `login_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `pwd` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`login_id`) USING BTREE,
  UNIQUE INDEX `email_unique`(`email`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of login
-- ----------------------------
INSERT INTO `login` VALUES (1, '123', '149@qq.com');
INSERT INTO `login` VALUES (2, '123', '123@qq.com');
INSERT INTO `login` VALUES (4, '123', '456@qq.com');
INSERT INTO `login` VALUES (6, '123', '15@qq.com');
INSERT INTO `login` VALUES (20, '123123', '1493@qq.com');
INSERT INTO `login` VALUES (21, '123123', '925063786@qq.com');
INSERT INTO `login` VALUES (22, '123123', '1493773835@qq.com');

-- ----------------------------
-- Table structure for notify
-- ----------------------------
DROP TABLE IF EXISTS `notify`;
CREATE TABLE `notify`  (
  `notify_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `notify_person` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '通知者',
  `send_notify_person` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '被通知者',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `isread` enum('1','0') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '1表已读,0表未读,默认0',
  `article_id` int(10) UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`notify_id`) USING BTREE,
  INDEX `article_id`(`article_id`) USING BTREE,
  CONSTRAINT `article_id` FOREIGN KEY (`article_id`) REFERENCES `article` (`article_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 91 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notify
-- ----------------------------
INSERT INTO `notify` VALUES (5, '梅老板', '阿扎尔', '阿扎尔@评论了你:换一篇文章测试', '0', 4);
INSERT INTO `notify` VALUES (6, '梅老板', '阿扎尔', '阿扎尔@评论了你:看看bug的原因\n', '0', 4);
INSERT INTO `notify` VALUES (7, '梅老板', '阿扎尔', '阿扎尔@评论了你:测试+1', '0', 3);
INSERT INTO `notify` VALUES (8, '阿扎尔', '梅老板', '梅老板@评论了你:你牛逼个头', '0', 6);
INSERT INTO `notify` VALUES (9, '梅老板', '阿扎尔', '阿扎尔@评论了你:dsad ', '0', 4);
INSERT INTO `notify` VALUES (10, '阿扎尔', '梅老板', '梅老板@评论了你:加了子评论 父评论还能用吗', '0', 6);
INSERT INTO `notify` VALUES (11, '阿扎尔', '梅老板', '梅老板@评论了你:我又来了', '0', 6);
INSERT INTO `notify` VALUES (12, '阿扎尔', '梅老板', '梅老板@评论了你:大家好', '0', 6);
INSERT INTO `notify` VALUES (13, '', '梅老板', '梅老板@评论了你:我真帅', '0', 6);
INSERT INTO `notify` VALUES (14, 'czk', '梅老板', '梅老板@评论了你:弟弟行为', '0', 6);
INSERT INTO `notify` VALUES (15, '', '梅老板', '梅老板@评论了你:????', '0', 6);
INSERT INTO `notify` VALUES (16, '阿扎尔', '梅老板', '梅老板@评论了你:我确实牛逼', '0', 6);
INSERT INTO `notify` VALUES (17, '阿扎尔', '梅老板', '梅老板@评论了你:!!!', '0', 6);
INSERT INTO `notify` VALUES (18, '阿扎尔', '梅老板', '梅老板@评论了你:哈喽', '0', 6);
INSERT INTO `notify` VALUES (19, '阿扎尔', '梅老板', '梅老板@评论了你:自己艾特自己干嘛\n', '0', 6);
INSERT INTO `notify` VALUES (20, '', '阿扎尔', '阿扎尔@评论了你:测试+1', '0', 6);
INSERT INTO `notify` VALUES (21, '', '阿扎尔', '阿扎尔@评论了你:', '0', 6);
INSERT INTO `notify` VALUES (22, '阿扎尔', '阿扎尔', '阿扎尔@评论了你:', '0', 6);
INSERT INTO `notify` VALUES (23, '阿扎尔', '武球王', '武球王@评论了你:笑什么弟弟', '0', 6);
INSERT INTO `notify` VALUES (24, '梅老板', '武球王', '武球王@评论了你:我更牛逼', '0', 6);
INSERT INTO `notify` VALUES (25, '阿扎尔', '武球王', '武球王@评论了你:是把 渣渣尔', '0', 6);
INSERT INTO `notify` VALUES (26, '阿扎尔', '武球王', '武球王@评论了你:你好啊', '0', 6);
INSERT INTO `notify` VALUES (27, '阿扎尔', '武球王', '武球王@评论了你:测试个毛线', '0', 6);
INSERT INTO `notify` VALUES (28, '阿扎尔', 'czk', 'czk@评论了你:大佬们好', '0', 6);
INSERT INTO `notify` VALUES (29, '阿扎尔', 'czk', 'czk@评论了你:天外飞仙', '0', 6);
INSERT INTO `notify` VALUES (30, '武球王', 'czk', 'czk@评论了你:哦豁', '0', 6);
INSERT INTO `notify` VALUES (31, '武球王', 'czk', 'czk@评论了你:你好啊', '0', 6);
INSERT INTO `notify` VALUES (32, '梅老板', 'czk', 'czk@评论了你:梅老板真乃神人', '1', 6);
INSERT INTO `notify` VALUES (33, 'czk', 'czk', 'czk@评论了你:金球奖稳了', '0', 6);
INSERT INTO `notify` VALUES (34, '阿扎尔', 'czk', 'czk@评论了你:你没有梅老板牛逼', '0', 6);
INSERT INTO `notify` VALUES (35, '梅老板', 'czk', 'czk@评论了你:金靴已到手', '0', 6);
INSERT INTO `notify` VALUES (36, '阿扎尔', 'czk', 'czk@评论了你:哇哈', '0', 6);
INSERT INTO `notify` VALUES (37, '阿扎尔', 'czk', 'czk@评论了你:出现在才', '0', 6);
INSERT INTO `notify` VALUES (38, 'czk', 'czk', 'czk@评论了你:浮点数', '0', 6);
INSERT INTO `notify` VALUES (39, '阿扎尔', 'czk', 'czk@评论了你:能不能局部刷新呢', '0', 6);
INSERT INTO `notify` VALUES (40, '阿扎尔', 'czk', 'czk@评论了你:nice!!!! 小老弟', '0', 6);
INSERT INTO `notify` VALUES (41, 'czk', 'czk', 'czk@评论了你:怎么..又', '0', 6);
INSERT INTO `notify` VALUES (42, '阿扎尔', 'czk', 'czk@评论了你:啊啊啊啊?', '0', 6);
INSERT INTO `notify` VALUES (43, '阿扎尔', 'czk', 'czk@评论了你:局部刷新成功 yesh!', '0', 6);
INSERT INTO `notify` VALUES (44, '梅老板', 'czk', 'czk@评论了你:梅老板才是球王', '0', 3);
INSERT INTO `notify` VALUES (45, '阿扎尔', 'czk', 'czk@评论了你:梅老板牛逼', '0', 3);
INSERT INTO `notify` VALUES (46, '阿扎尔', 'czk', 'czk@评论了你:梅老板牛逼', '0', 3);
INSERT INTO `notify` VALUES (47, 'czk', 'czk', 'czk@评论了你:刚刚脑抽了?', '0', 3);
INSERT INTO `notify` VALUES (48, '梅老板', 'czk', 'czk@评论了你:啊哈?', '0', 3);
INSERT INTO `notify` VALUES (49, '梅老板', 'czk', 'czk@评论了你:嗯哼?', '0', 3);
INSERT INTO `notify` VALUES (50, '梅老板', 'czk', 'czk@评论了你:我擦?', '0', 3);
INSERT INTO `notify` VALUES (51, '梅老板', 'czk', 'czk@评论了你:我??', '0', 3);
INSERT INTO `notify` VALUES (52, '梅老板', 'czk', 'czk@评论了你:好了好了', '1', 3);
INSERT INTO `notify` VALUES (53, 'czk', 'czk', 'czk@评论了你:怎么费事', '0', 3);
INSERT INTO `notify` VALUES (54, '梅老板', 'czk', 'czk@评论了你:来了来了 是那个男人', '0', 3);
INSERT INTO `notify` VALUES (55, 'czk', 'czk', 'czk@评论了你:没bug了  ', '0', 3);
INSERT INTO `notify` VALUES (56, '梅老板', 'czk', 'czk@评论了你:梅老板牛逼', '0', 3);
INSERT INTO `notify` VALUES (57, '梅老板', 'czk', 'czk@评论了你:very good', '0', 3);
INSERT INTO `notify` VALUES (58, '梅老板', 'czk', 'czk@评论了你:测试滚动', '1', 3);
INSERT INTO `notify` VALUES (59, '梅老板', 'czk', 'czk@评论了你:继续测试滚动', '0', 3);
INSERT INTO `notify` VALUES (60, '梅老板', 'czk', 'czk@评论了你:000000', '0', 3);
INSERT INTO `notify` VALUES (61, '梅老板', 'czk', 'czk@评论了你:....', '0', 3);
INSERT INTO `notify` VALUES (62, '梅老板', 'czk', 'czk@评论了你:....', '0', 3);
INSERT INTO `notify` VALUES (63, '梅老板', 'czk', 'czk@评论了你:2312123', '0', 3);
INSERT INTO `notify` VALUES (64, '梅老板', 'czk', 'czk@评论了你:vcxv', '0', 3);
INSERT INTO `notify` VALUES (65, '梅老板', 'czk', 'czk@评论了你:啊啊啊啊', '0', 3);
INSERT INTO `notify` VALUES (66, '梅老板', 'czk', 'czk@评论了你:vcv', '0', 3);
INSERT INTO `notify` VALUES (67, '梅老板', 'czk', 'czk@评论了你:通天塔', '0', 3);
INSERT INTO `notify` VALUES (68, '梅老板', 'czk', 'czk@评论了你:啊啊啊 ', '0', 3);
INSERT INTO `notify` VALUES (75, 'czk', '梅老板', '梅老板@评论了你:雅蠛蝶', '0', 6);
INSERT INTO `notify` VALUES (76, '梅老板', '梅老板', '梅老板@评论了你:拦截器 评论测试', '0', 6);
INSERT INTO `notify` VALUES (77, '梅老板', '梅老板', '梅老板@评论了你:呀呀呀', '0', 3);
INSERT INTO `notify` VALUES (78, '梅老板', '小萌', '小萌@评论了你:测试成功！', '1', 4);
INSERT INTO `notify` VALUES (79, '小萌', '小萌', '小萌@评论了你:啊啊啊', '0', 4);
INSERT INTO `notify` VALUES (80, '阿扎尔', '小萌', '小萌@评论了你:cxzcz', '0', 5);
INSERT INTO `notify` VALUES (81, '小萌', '小萌', '小萌@评论了你:文章不存在！！', '0', 5);
INSERT INTO `notify` VALUES (84, '小萌', '小萌', '小萌@评论了你:对K', '0', 6);
INSERT INTO `notify` VALUES (86, '小萌', '小萌', '小萌@评论了你:对A', '0', 6);

-- ----------------------------
-- Table structure for report_article
-- ----------------------------
DROP TABLE IF EXISTS `report_article`;
CREATE TABLE `report_article`  (
  `article_id` int(10) UNSIGNED NOT NULL COMMENT '举报的文章id,关联article表',
  `reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '举报理由,允许为空',
  `report_user_id` int(10) UNSIGNED NOT NULL COMMENT '举报人id',
  PRIMARY KEY (`article_id`, `report_user_id`) USING BTREE,
  INDEX `reportArticle_reportUserId`(`report_user_id`) USING BTREE,
  CONSTRAINT `reportArticle_articleId` FOREIGN KEY (`article_id`) REFERENCES `article` (`article_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `reportArticle_reportUserId` FOREIGN KEY (`report_user_id`) REFERENCES `user_message` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for report_common
-- ----------------------------
DROP TABLE IF EXISTS `report_common`;
CREATE TABLE `report_common`  (
  `comment_id` int(10) UNSIGNED NOT NULL COMMENT '举报的评论id,关联comment_on表',
  `report_user_id` int(10) UNSIGNED NOT NULL COMMENT '举报人,关联user_message表',
  `reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '举报理由,允许为空',
  PRIMARY KEY (`comment_id`, `report_user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for root_login
-- ----------------------------
DROP TABLE IF EXISTS `root_login`;
CREATE TABLE `root_login`  (
  `root_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `login_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `pwd` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`root_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag`  (
  `tag_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `tag_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`tag_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tag
-- ----------------------------
INSERT INTO `tag` VALUES (1, '操作系统');
INSERT INTO `tag` VALUES (2, '编程语言');
INSERT INTO `tag` VALUES (3, '前端');
INSERT INTO `tag` VALUES (4, '移动开发');
INSERT INTO `tag` VALUES (5, '大数据');
INSERT INTO `tag` VALUES (6, '计算机网络');
INSERT INTO `tag` VALUES (7, '架构');
INSERT INTO `tag` VALUES (8, '后端');
INSERT INTO `tag` VALUES (9, '游戏开发');
INSERT INTO `tag` VALUES (10, '数据库');
INSERT INTO `tag` VALUES (11, '其他');
INSERT INTO `tag` VALUES (12, '算法');

-- ----------------------------
-- Table structure for user_message
-- ----------------------------
DROP TABLE IF EXISTS `user_message`;
CREATE TABLE `user_message`  (
  `user_id` int(10) UNSIGNED NOT NULL,
  `user_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `head_portrait` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户头像',
  `user_profile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '个人简介',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `user_name_unique`(`user_name`) USING BTREE COMMENT '用户名不可重复',
  CONSTRAINT `fk_userMessage_user_id` FOREIGN KEY (`user_id`) REFERENCES `login` (`login_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_message
-- ----------------------------
INSERT INTO `user_message` VALUES (1, '梅老板', 'http://localhost:8081/source/image/meixi.jpg', '我是梅西 现在慌得一笔');
INSERT INTO `user_message` VALUES (2, '阿扎尔', 'http://localhost:8081/source/image/azhaer.jpg', '我是阿扎尔');
INSERT INTO `user_message` VALUES (4, '武球王', 'http://localhost:8081/source/image/wulei.jpg', '我是武磊');
INSERT INTO `user_message` VALUES (6, 'czk', 'http://localhost:8081/source/image/default.jpg', '');
INSERT INTO `user_message` VALUES (20, '小萌', 'http://localhost:8081/source/image/xx.png', '');
INSERT INTO `user_message` VALUES (21, '21', 'http://localhost:8081/source/image/default.jpg', '');
INSERT INTO `user_message` VALUES (22, '119bb620', 'http://localhost:8081/source/image/default.jpg', '');

-- ----------------------------
-- View structure for v_article
-- ----------------------------
DROP VIEW IF EXISTS `v_article`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_article` AS select `article`.`article_id` AS `article_id`,`article`.`title` AS `title`,`article`.`article_profile` AS `article_profile`,`user_message`.`user_name` AS `user_name`,`user_message`.`head_portrait` AS `head_portrait`,`article`.`watch` AS `watch`,`article`.`create_time` AS `create_time`,`article`.`clik_like` AS `clik_like`,`tag`.`tag_name` AS `tag_name` from (((`article` join `article_tag`) join `tag`) join `user_message`) where ((`article`.`article_id` = `article_tag`.`article_id`) and (`tag`.`tag_id` = `article_tag`.`tag_id`) and (`article`.`own_id` = `user_message`.`user_id`)) order by `article`.`create_time` desc;

SET FOREIGN_KEY_CHECKS = 1;
