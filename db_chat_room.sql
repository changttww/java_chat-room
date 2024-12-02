/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80200 (8.2.0)
 Source Host           : localhost:3306
 Source Schema         : db_chat_room

 Target Server Type    : MySQL
 Target Server Version : 80200 (8.2.0)
 File Encoding         : 65001

 Date: 13/01/2024 16:23:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for chat_record
-- ----------------------------
DROP TABLE IF EXISTS `chat_record`;
CREATE TABLE `chat_record` (
  `id` int NOT NULL AUTO_INCREMENT,
  `community` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `message` text COLLATE utf8mb4_unicode_ci,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=206 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of chat_record
-- ----------------------------
BEGIN;
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (4, 'huanleshequ', '{\"type\":\"UserMessage\",\"message\":\"44432432\",\"userId\":5,\"head\":\"🥵\",\"name\":\"倪鹏程\",\"time\":\"2024/01/11 14:29:30\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (5, 'huanleshequ', '{\"type\":\"UserMessage\",\"message\":\"432432432342\",\"userId\":9,\"head\":\"😄\",\"name\":\"小家伙\",\"time\":\"2024/01/11 14:29:33\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (6, 'huanleshequ', '{\"type\":\"UserMessage\",\"message\":\"444\",\"userId\":9,\"head\":\"😄\",\"name\":\"小家伙\",\"time\":\"2024/01/11 15:02:23\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (113, 'qiqu', '{\"type\":\"UserMessage\",\"message\":\"标记爸爸就积极布局\",\"userId\":5,\"head\":\"🥵\",\"name\":\"倪鹏程\",\"time\":\"2024/01/11 16:37:12\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (114, 'huanleshequ', '{\"type\":\"UserMessage\",\"message\":\"哈哈哈\",\"userId\":7,\"head\":\"😄\",\"name\":\"哈哈倪鹏程\",\"time\":\"2024/01/11 17:29:29\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (115, 'huanleshequ', '{\"type\":\"UserMessage\",\"message\":\"就啊纠结啊\",\"userId\":7,\"head\":\"😄\",\"name\":\"哈哈倪鹏程\",\"time\":\"2024/01/11 17:29:31\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (116, 'aixin', '{\"type\":\"UserMessage\",\"message\":\"发发发\",\"userId\":7,\"head\":\"😄\",\"name\":\"哈哈倪鹏程\",\"time\":\"2024/01/11 17:39:13\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (117, 'zhihuishequ', '{\"type\":\"UserMessage\",\"message\":\"4444\",\"userId\":10,\"head\":\"😄\",\"name\":\"倪鹏程001\",\"time\":\"2024/01/11 17:58:59\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (118, 'zhihuishequ', '{\"type\":\"UserMessage\",\"userId\":10,\"head\":\"😄\",\"name\":\"倪鹏程001\",\"time\":\"2024/01/11 17:58:59\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (119, 'zhihuishequ', '{\"type\":\"UserMessage\",\"message\":\"432\",\"userId\":7,\"head\":\"😄\",\"name\":\"哈哈倪鹏程\",\"time\":\"2024/01/11 17:59:06\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (120, 'zhihuishequ', '{\"type\":\"UserMessage\",\"message\":\"4444\",\"userId\":7,\"head\":\"😄\",\"name\":\"哈哈倪鹏程\",\"time\":\"2024/01/11 17:59:09\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (121, 'kuxuan', '{\"type\":\"UserMessage\",\"message\":\"倪鹏程\",\"userId\":7,\"head\":\"😄\",\"name\":\"哈哈倪鹏程\",\"time\":\"2024/01/11 20:59:27\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (122, 'kuxuan', '{\"type\":\"UserMessage\",\"message\":\"1233333\",\"userId\":7,\"head\":\"😄\",\"name\":\"哈哈倪鹏程\",\"time\":\"2024/01/11 20:59:31\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (123, 'kuxuan', '{\"type\":\"UserMessage\",\"message\":\"4432432\",\"userId\":7,\"head\":\"😄\",\"name\":\"哈哈倪鹏程\",\"time\":\"2024/01/11 20:59:32\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (124, 'kuxuan', '{\"type\":\"UserMessage\",\"message\":\"4324324\",\"userId\":7,\"head\":\"😄\",\"name\":\"哈哈倪鹏程\",\"time\":\"2024/01/11 20:59:34\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (125, 'huanleshequ', '{\"type\":\"UserMessage\",\"message\":\"你的撒哈哈\",\"userId\":7,\"head\":\"😄\",\"name\":\"哈哈倪鹏程\",\"time\":\"2024/01/11 21:01:20\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (126, 'huanleshequ', '{\"type\":\"UserMessage\",\"message\":\"发的撒上点饭\",\"userId\":7,\"head\":\"😄\",\"name\":\"哈哈倪鹏程\",\"time\":\"2024/01/11 21:01:23\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (127, 'huanleshequ', '{\"type\":\"UserMessage\",\"message\":\"发的撒风\",\"userId\":7,\"head\":\"😄\",\"name\":\"哈哈倪鹏程\",\"time\":\"2024/01/11 21:01:25\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (128, 'huanleshequ', '{\"type\":\"UserMessage\",\"message\":\"发的撒\",\"userId\":7,\"head\":\"😄\",\"name\":\"哈哈倪鹏程\",\"time\":\"2024/01/11 21:04:34\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (129, 'huanleshequ', '{\"type\":\"UserMessage\",\"message\":\"发的撒\",\"userId\":7,\"head\":\"😄\",\"name\":\"哈哈倪鹏程\",\"time\":\"2024/01/11 21:04:37\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (130, 'huanleshequ', '{\"type\":\"UserMessage\",\"message\":\"发的撒\",\"userId\":7,\"head\":\"😄\",\"name\":\"哈哈倪鹏程\",\"time\":\"2024/01/11 21:04:39\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (131, 'huanleshequ', '{\"type\":\"UserMessage\",\"message\":\"发的撒\",\"userId\":7,\"head\":\"😄\",\"name\":\"哈哈倪鹏程\",\"time\":\"2024/01/11 21:04:40\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (132, 'huanleshequ', '{\"type\":\"UserMessage\",\"message\":\"321321\",\"userId\":7,\"head\":\"😄\",\"name\":\"哈哈倪鹏程\",\"time\":\"2024/01/11 21:04:45\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (133, 'kuxuan', '{\"type\":\"UserMessage\",\"message\":\"dd\",\"userId\":7,\"head\":\"😄\",\"name\":\"哈哈倪鹏程\",\"time\":\"2024/01/11 21:05:34\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (134, 'kuxuan', '{\"type\":\"UserMessage\",\"message\":\"ddd\",\"userId\":7,\"head\":\"😄\",\"name\":\"哈哈倪鹏程\",\"time\":\"2024/01/11 21:05:35\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (135, 'kuxuan', '{\"type\":\"UserMessage\",\"message\":\"ddd\",\"userId\":7,\"head\":\"😄\",\"name\":\"哈哈倪鹏程\",\"time\":\"2024/01/11 21:05:36\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (136, 'kuxuan', '{\"type\":\"UserMessage\",\"message\":\"你啊好\",\"userId\":7,\"head\":\"😄\",\"name\":\"哈哈倪鹏程\",\"time\":\"2024/01/11 21:05:38\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (137, 'kuxuan', '{\"type\":\"UserMessage\",\"message\":\"你说什么\",\"userId\":7,\"head\":\"😄\",\"name\":\"哈哈倪鹏程\",\"time\":\"2024/01/11 21:05:39\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (138, 'kuxuan', '{\"type\":\"UserMessage\",\"message\":\"福地啊时间\",\"userId\":7,\"head\":\"😄\",\"name\":\"哈哈倪鹏程\",\"time\":\"2024/01/11 21:05:41\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (139, 'kuxuan', '{\"type\":\"UserMessage\",\"message\":\"我丢\",\"userId\":7,\"head\":\"😄\",\"name\":\"哈哈倪鹏程\",\"time\":\"2024/01/11 21:05:42\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (140, 'kuxuan', '{\"type\":\"UserMessage\",\"message\":\"我勒个豆\",\"userId\":7,\"head\":\"😄\",\"name\":\"哈哈倪鹏程\",\"time\":\"2024/01/11 21:05:45\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (141, 'kuxuan', '{\"type\":\"UserMessage\",\"message\":\"哈哈哈哈哈\",\"userId\":7,\"head\":\"😄\",\"name\":\"哈哈倪鹏程\",\"time\":\"2024/01/11 21:05:47\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (142, 'kuxuan', '{\"type\":\"UserMessage\",\"message\":\"测试一下 50元钱的消息\",\"userId\":7,\"head\":\"😄\",\"name\":\"哈哈倪鹏程\",\"time\":\"2024/01/11 21:06:06\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (143, 'huanleshequ', '{\"type\":\"UserMessage\",\"message\":\"你好\",\"userId\":7,\"head\":\"😄\",\"name\":\"哈哈倪鹏程\",\"time\":\"2024/01/11 21:08:30\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (144, 'huanleshequ', '{\"type\":\"UserMessage\",\"message\":\"你好\",\"userId\":7,\"head\":\"😄\",\"name\":\"哈哈倪鹏程\",\"time\":\"2024/01/11 21:08:33\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (145, 'huanleshequ', '{\"type\":\"UserMessage\",\"message\":\"你好\",\"userId\":7,\"head\":\"😄\",\"name\":\"哈哈倪鹏程\",\"time\":\"2024/01/11 21:08:37\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (146, 'huanleshequ', '{\"type\":\"UserMessage\",\"message\":\"敏镐\",\"userId\":7,\"head\":\"😄\",\"name\":\"哈哈倪鹏程\",\"time\":\"2024/01/11 22:40:35\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (147, 'huanleshequ', '{\"type\":\"UserMessage\",\"message\":\"你迷惑\",\"userId\":7,\"head\":\"😄\",\"name\":\"哈哈倪鹏程\",\"time\":\"2024/01/11 22:40:39\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (148, 'huanleshequ', '{\"type\":\"UserMessage\",\"message\":\"你好\",\"userId\":7,\"head\":\"😄\",\"name\":\"哈哈倪鹏程\",\"time\":\"2024/01/11 22:40:41\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (149, 'huanleshequ', '{\"type\":\"UserMessage\",\"message\":\"还丢啊说\",\"userId\":7,\"head\":\"😄\",\"name\":\"哈哈倪鹏程\",\"time\":\"2024/01/11 22:40:42\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (150, 'huanleshequ', '{\"type\":\"UserMessage\",\"message\":\"姐弟仨\",\"userId\":7,\"head\":\"😄\",\"name\":\"哈哈倪鹏程\",\"time\":\"2024/01/11 22:40:43\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (151, 'huanleshequ', '{\"type\":\"UserMessage\",\"message\":\"姐弟仨\",\"userId\":7,\"head\":\"😄\",\"name\":\"哈哈倪鹏程\",\"time\":\"2024/01/11 22:40:43\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (152, 'huanleshequ', '{\"type\":\"UserMessage\",\"message\":\"姐弟仨\",\"userId\":7,\"head\":\"😄\",\"name\":\"哈哈倪鹏程\",\"time\":\"2024/01/11 22:40:44\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (153, 'huanleshequ', '{\"type\":\"UserMessage\",\"message\":\"姐弟仨\",\"userId\":7,\"head\":\"😄\",\"name\":\"哈哈倪鹏程\",\"time\":\"2024/01/11 22:40:45\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (154, 'huanleshequ', '{\"type\":\"UserMessage\",\"message\":\"极大\",\"userId\":7,\"head\":\"😄\",\"name\":\"哈哈倪鹏程\",\"time\":\"2024/01/11 22:40:45\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (155, 'huanleshequ', '{\"type\":\"UserMessage\",\"message\":\"极大\",\"userId\":7,\"head\":\"😄\",\"name\":\"哈哈倪鹏程\",\"time\":\"2024/01/11 22:40:46\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (156, 'huanleshequ', '{\"type\":\"UserMessage\",\"message\":\"牛大号还记得撒\",\"userId\":7,\"head\":\"😄\",\"name\":\"哈哈倪鹏程\",\"time\":\"2024/01/11 22:40:54\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (157, 'huanleshequ', '{\"type\":\"UserMessage\",\"message\":\"奴家\",\"userId\":7,\"head\":\"😄\",\"name\":\"哈哈倪鹏程\",\"time\":\"2024/01/11 22:40:57\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (158, 'zhihuishequ', '{\"type\":\"UserMessage\",\"message\":\"机哦大事\",\"userId\":7,\"head\":\"😄\",\"name\":\"哈哈倪鹏程\",\"time\":\"2024/01/11 22:41:03\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (159, 'jianshe', '{\"type\":\"UserMessage\",\"message\":\"敏镐\",\"userId\":7,\"head\":\"😄\",\"name\":\"哈哈倪鹏程\",\"time\":\"2024/01/11 22:41:10\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (160, 'jianshe', '{\"type\":\"UserMessage\",\"message\":\"你好\",\"userId\":7,\"head\":\"😄\",\"name\":\"哈哈倪鹏程\",\"time\":\"2024/01/11 22:41:13\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (161, 'zhihuishequ', '{\"type\":\"UserMessage\",\"message\":\"你好\",\"userId\":7,\"head\":\"😄\",\"name\":\"哈哈倪鹏程\",\"time\":\"2024/01/11 22:41:18\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (162, 'zhihuishequ', '{\"type\":\"UserMessage\",\"message\":\"你好\",\"userId\":7,\"head\":\"😄\",\"name\":\"哈哈倪鹏程\",\"time\":\"2024/01/11 22:41:24\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (163, 'huanleshequ', '{\"type\":\"UserMessage\",\"message\":\"123\",\"userId\":7,\"head\":\"😄\",\"name\":\"哈哈倪鹏程\",\"time\":\"2024/01/11 22:42:26\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (164, 'huanleshequ', '{\"type\":\"UserMessage\",\"message\":\"432\",\"userId\":7,\"head\":\"😄\",\"name\":\"哈哈倪鹏程\",\"time\":\"2024/01/11 22:42:28\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (165, 'zhihuishequ', '{\"type\":\"UserMessage\",\"message\":\"432\",\"userId\":7,\"head\":\"😄\",\"name\":\"哈哈倪鹏程\",\"time\":\"2024/01/11 22:42:32\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (166, 'zhihuishequ', '{\"type\":\"UserMessage\",\"userId\":7,\"head\":\"😄\",\"name\":\"哈哈倪鹏程\",\"time\":\"2024/01/11 22:42:33\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (167, 'zhihuishequ', '{\"type\":\"UserMessage\",\"message\":\"432\",\"userId\":7,\"head\":\"😄\",\"name\":\"哈哈倪鹏程\",\"time\":\"2024/01/11 22:42:34\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (168, 'zhihuishequ', '{\"type\":\"UserMessage\",\"message\":\"432\",\"userId\":7,\"head\":\"😄\",\"name\":\"哈哈倪鹏程\",\"time\":\"2024/01/11 22:42:35\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (169, 'zhihuishequ', '{\"type\":\"UserMessage\",\"message\":\"32\",\"userId\":7,\"head\":\"😄\",\"name\":\"哈哈倪鹏程\",\"time\":\"2024/01/11 22:42:38\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (170, 'zhihuishequ', '{\"type\":\"UserMessage\",\"message\":\"432432432432\",\"userId\":7,\"head\":\"😄\",\"name\":\"哈哈倪鹏程\",\"time\":\"2024/01/11 22:42:40\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (171, 'zhihuishequ', '{\"type\":\"UserMessage\",\"message\":\"432432432\",\"userId\":7,\"head\":\"😄\",\"name\":\"哈哈倪鹏程\",\"time\":\"2024/01/11 22:42:41\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (172, 'qiqu', '{\"type\":\"UserMessage\",\"message\":\"432432432\",\"userId\":7,\"head\":\"😄\",\"name\":\"哈哈倪鹏程\",\"time\":\"2024/01/11 22:42:49\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (173, 'qiqu', '{\"type\":\"UserMessage\",\"message\":\"2222\",\"userId\":7,\"head\":\"😄\",\"name\":\"哈哈倪鹏程\",\"time\":\"2024/01/11 22:42:52\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (174, 'qiqu', '{\"type\":\"UserMessage\",\"message\":\"4444\",\"userId\":7,\"head\":\"😄\",\"name\":\"哈哈倪鹏程\",\"time\":\"2024/01/11 22:42:54\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (175, 'qiqu', '{\"type\":\"UserMessage\",\"message\":\"你好\",\"userId\":7,\"head\":\"😄\",\"name\":\"哈哈倪鹏程\",\"time\":\"2024/01/11 22:42:56\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (176, 'qiqu', '{\"type\":\"UserMessage\",\"message\":\"机会发的撒接口\",\"userId\":7,\"head\":\"😄\",\"name\":\"哈哈倪鹏程\",\"time\":\"2024/01/11 22:42:58\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (177, 'qiqu', '{\"type\":\"UserMessage\",\"message\":\"你好\",\"userId\":7,\"head\":\"😄\",\"name\":\"哈哈倪鹏程\",\"time\":\"2024/01/11 22:43:08\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (178, 'qiqu', '{\"type\":\"UserMessage\",\"message\":\"话哦\",\"userId\":7,\"head\":\"😄\",\"name\":\"哈哈倪鹏程\",\"time\":\"2024/01/11 22:43:12\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (179, 'tianshiguangchang', '{\"type\":\"UserMessage\",\"message\":\"123444\",\"userId\":5,\"head\":\"🥵\",\"name\":\"倪鹏程\",\"time\":\"2024/01/11 22:43:34\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (180, 'tianshiguangchang', '{\"type\":\"UserMessage\",\"message\":\"43241\",\"userId\":5,\"head\":\"🥵\",\"name\":\"倪鹏程\",\"time\":\"2024/01/11 22:43:39\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (181, 'zhihuishequ', '{\"type\":\"UserMessage\",\"message\":\"432\",\"userId\":7,\"head\":\"😄\",\"name\":\"哈哈倪鹏程\",\"time\":\"2024/01/11 22:43:45\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (182, 'tianshiguangchang', '{\"type\":\"UserMessage\",\"message\":\"432432\",\"userId\":7,\"head\":\"😄\",\"name\":\"哈哈倪鹏程\",\"time\":\"2024/01/11 22:43:51\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (183, 'tianshiguangchang', '{\"type\":\"UserMessage\",\"message\":\"你好\",\"userId\":7,\"head\":\"😄\",\"name\":\"哈哈倪鹏程\",\"time\":\"2024/01/11 22:43:55\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (184, 'tianshiguangchang', '{\"type\":\"UserMessage\",\"message\":\"测\",\"userId\":7,\"head\":\"😄\",\"name\":\"哈哈倪鹏程\",\"time\":\"2024/01/11 22:43:58\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (185, 'tianshiguangchang', '{\"type\":\"UserMessage\",\"message\":\"123321321\",\"userId\":5,\"head\":\"🥵\",\"name\":\"倪鹏程\",\"time\":\"2024/01/11 22:44:02\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (186, 'tianshiguangchang', '{\"type\":\"UserMessage\",\"message\":\"123432\",\"userId\":7,\"head\":\"😄\",\"name\":\"刘德华\",\"time\":\"2024/01/11 22:44:44\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (187, 'tianshiguangchang', '{\"type\":\"UserMessage\",\"message\":\"321\",\"userId\":7,\"head\":\"😄\",\"name\":\"刘德华\",\"time\":\"2024/01/11 22:44:45\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (188, 'tianshiguangchang', '{\"type\":\"UserMessage\",\"message\":\"432432\",\"userId\":7,\"head\":\"😄\",\"name\":\"刘德华\",\"time\":\"2024/01/11 22:45:01\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (189, 'huanleshequ', '{\"type\":\"UserMessage\",\"message\":\"你好\",\"userId\":7,\"head\":\"😄\",\"name\":\"刘德华\",\"time\":\"2024/01/11 22:49:42\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (190, 'huanleshequ', '{\"type\":\"UserMessage\",\"message\":\"倪鹏程\",\"userId\":7,\"head\":\"😄\",\"name\":\"刘德华\",\"time\":\"2024/01/11 22:57:58\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (191, 'huanleshequ', '{\"type\":\"UserMessage\",\"message\":\"测试\",\"userId\":7,\"head\":\"😄\",\"name\":\"刘德华\",\"time\":\"2024/01/11 22:58:02\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (192, 'huanleshequ', '{\"type\":\"UserMessage\",\"message\":\"连接\",\"userId\":5,\"head\":\" 🤵\",\"name\":\"倪鹏程\",\"time\":\"2024/01/11 22:58:05\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (193, 'huanleshequ', '{\"type\":\"UserMessage\",\"message\":\"收到消息\",\"userId\":5,\"head\":\" 🤵\",\"name\":\"倪鹏程\",\"time\":\"2024/01/11 22:58:09\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (194, 'huanleshequ', '{\"type\":\"UserMessage\",\"message\":\"规范化的健康更好肌肤的撒\",\"userId\":7,\"head\":\"😄\",\"name\":\"刘德华\",\"time\":\"2024/01/11 23:07:19\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (195, 'huanleshequ', '{\"type\":\"UserMessage\",\"message\":\"发货的时间啊飞\",\"userId\":5,\"head\":\" 🤵\",\"name\":\"倪鹏程\",\"time\":\"2024/01/11 23:07:22\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (196, 'huanleshequ', '{\"type\":\"UserMessage\",\"message\":\"飞多久能看撒恢复健康\",\"userId\":5,\"head\":\" 🤵\",\"name\":\"倪鹏程\",\"time\":\"2024/01/11 23:07:23\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (197, 'huanleshequ', '{\"type\":\"UserMessage\",\"message\":\"后尽快发来的撒回家看撒\",\"userId\":5,\"head\":\" 🤵\",\"name\":\"倪鹏程\",\"time\":\"2024/01/11 23:07:24\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (198, 'huanleshequ', '{\"type\":\"UserMessage\",\"message\":\"京津冀\",\"userId\":5,\"head\":\" 🤵\",\"name\":\"倪鹏程\",\"time\":\"2024/01/11 23:07:37\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (199, 'huanleshequ', '{\"type\":\"UserMessage\",\"message\":\"多少啊\",\"userId\":5,\"head\":\" 🤵\",\"name\":\"倪鹏程\",\"time\":\"2024/01/11 23:20:43\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (200, 'huanleshequ', '{\"type\":\"UserMessage\",\"message\":\"123\",\"userId\":5,\"head\":\" 🤵\",\"name\":\"倪鹏程\",\"time\":\"2024/01/11 23:20:47\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (201, 'zhihuishequ', '{\"type\":\"UserMessage\",\"message\":\"das \",\"userId\":5,\"head\":\" 🤵\",\"name\":\"倪鹏程\",\"time\":\"2024/01/11 23:43:48\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (202, 'zhihuishequ', '{\"type\":\"UserMessage\",\"message\":\"sa \",\"userId\":5,\"head\":\" 🤵\",\"name\":\"倪鹏程\",\"time\":\"2024/01/11 23:43:50\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (203, 'zhihuishequ', '{\"type\":\"UserMessage\",\"message\":\"3213213\",\"userId\":5,\"head\":\" 🤵\",\"name\":\"倪鹏程\",\"time\":\"2024/01/11 23:44:43\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (204, 'zhihuishequ', '{\"type\":\"UserMessage\",\"message\":\"321\",\"userId\":5,\"head\":\" 🤵\",\"name\":\"倪鹏程\",\"time\":\"2024/01/11 23:44:51\"}');
INSERT INTO `chat_record` (`id`, `community`, `message`) VALUES (205, 'zhihuishequ', '{\"type\":\"UserMessage\",\"message\":\"321\",\"userId\":5,\"head\":\" 🤵\",\"name\":\"倪鹏程\",\"time\":\"2024/01/11 23:44:55\"}');
COMMIT;

-- ----------------------------
-- Table structure for chat_user
-- ----------------------------
DROP TABLE IF EXISTS `chat_user`;
CREATE TABLE `chat_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `head` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `chat_user_pk` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of chat_user
-- ----------------------------
BEGIN;
INSERT INTO `chat_user` (`id`, `username`, `password`, `name`, `head`) VALUES (5, 'nipengcheng', '123456', '倪鹏程', ' 🤵');
INSERT INTO `chat_user` (`id`, `username`, `password`, `name`, `head`) VALUES (7, 'liudehua', '123456', '刘德华', '😄');
INSERT INTO `chat_user` (`id`, `username`, `password`, `name`, `head`) VALUES (9, 'nikel', '123456', '小家伙', '😄');
INSERT INTO `chat_user` (`id`, `username`, `password`, `name`, `head`) VALUES (10, 'nipengcheng123', '123456', '倪鹏程001', '😄');
COMMIT;

-- ----------------------------
-- Table structure for chat_user_community_record
-- ----------------------------
DROP TABLE IF EXISTS `chat_user_community_record`;
CREATE TABLE `chat_user_community_record` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `community` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`,`user_id`,`community`) USING BTREE,
  KEY `id` (`user_id`),
  CONSTRAINT `id` FOREIGN KEY (`user_id`) REFERENCES `chat_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of chat_user_community_record
-- ----------------------------
BEGIN;
INSERT INTO `chat_user_community_record` (`id`, `user_id`, `community`) VALUES (3, 9, 'huanleshequ');
INSERT INTO `chat_user_community_record` (`id`, `user_id`, `community`) VALUES (4, 9, 'huanleshequ');
INSERT INTO `chat_user_community_record` (`id`, `user_id`, `community`) VALUES (5, 9, 'huanleshequ');
INSERT INTO `chat_user_community_record` (`id`, `user_id`, `community`) VALUES (6, 9, 'huanleshequ');
INSERT INTO `chat_user_community_record` (`id`, `user_id`, `community`) VALUES (7, 9, 'huanleshequ');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
