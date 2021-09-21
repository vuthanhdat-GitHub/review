/*
 Navicat Premium Data Transfer

 Source Server         : Local-MySQL
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3306
 Source Schema         : tradedoc

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 06/12/2020 13:17:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `createdby` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `createddate` timestamp(0) NULL DEFAULT NULL,
  `modifiedby` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `modifieddate` timestamp(0) NULL DEFAULT NULL,
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, NULL, '2020-11-29 04:24:27', NULL, '2020-11-29 04:24:29', 'MT4', 'MT4');
INSERT INTO `category` VALUES (2, NULL, '2020-11-29 04:24:41', NULL, '2020-11-29 04:24:42', 'MT5', 'MT5');

-- ----------------------------
-- Table structure for code_signup
-- ----------------------------
DROP TABLE IF EXISTS `code_signup`;
CREATE TABLE `code_signup`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `createdby` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `createddate` timestamp(0) NULL DEFAULT NULL,
  `modifiedby` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `modifieddate` timestamp(0) NULL DEFAULT NULL,
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of code_signup
-- ----------------------------

-- ----------------------------
-- Table structure for comments
-- ----------------------------
DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `createdby` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `createddate` timestamp(0) NULL DEFAULT NULL,
  `modifiedby` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `modifieddate` timestamp(0) NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `usernamecomment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `productid` bigint(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKdpyej8iigc8ml84qxns9mwvda`(`productid`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comments
-- ----------------------------

-- ----------------------------
-- Table structure for customer_product
-- ----------------------------
DROP TABLE IF EXISTS `customer_product`;
CREATE TABLE `customer_product`  (
  `customer_id` bigint(0) NOT NULL,
  `product_id` bigint(0) NOT NULL,
  INDEX `FK32b9pljo1co74d8bpwh2a15e1`(`product_id`) USING BTREE,
  INDEX `FKk1iv7jvt9xfpfiy72j8422vv7`(`customer_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of customer_product
-- ----------------------------

-- ----------------------------
-- Table structure for customers
-- ----------------------------
DROP TABLE IF EXISTS `customers`;
CREATE TABLE `customers`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `createdby` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `createddate` timestamp(0) NULL DEFAULT NULL,
  `modifiedby` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `modifieddate` timestamp(0) NULL DEFAULT NULL,
  `customername` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phonenumber` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customers
-- ----------------------------
INSERT INTO `customers` VALUES (2, NULL, NULL, NULL, NULL, 'dang van dat', 'dangdat.09071997@gmail.com', '0912542090');

-- ----------------------------
-- Table structure for history_payment
-- ----------------------------
DROP TABLE IF EXISTS `history_payment`;
CREATE TABLE `history_payment`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `createdby` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `createddate` timestamp(0) NULL DEFAULT NULL,
  `modifiedby` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `modifieddate` timestamp(0) NULL DEFAULT NULL,
  `deleted` int(0) NULL DEFAULT NULL,
  `paymenttype` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `status` int(0) NULL DEFAULT NULL,
  `total` bigint(0) NULL DEFAULT NULL,
  `customerid` bigint(0) NULL DEFAULT NULL,
  `productid` bigint(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKnp8qi045ogovmkscahym3juqq`(`customerid`) USING BTREE,
  INDEX `FKqxvp7xhwercvg97fcxh3exmj9`(`productid`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of history_payment
-- ----------------------------
INSERT INTO `history_payment` VALUES (5, NULL, '2020-12-01 16:59:06', NULL, '2020-12-01 16:59:06', 1, 'PAY_PAL', 1, 500, 2, 2);

-- ----------------------------
-- Table structure for images
-- ----------------------------
DROP TABLE IF EXISTS `images`;
CREATE TABLE `images`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `createdby` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `createddate` timestamp(0) NULL DEFAULT NULL,
  `modifiedby` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `modifieddate` timestamp(0) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `pathfile` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `productid` bigint(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKtmpcqvrplwcthq9jb3las9xy0`(`productid`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of images
-- ----------------------------
INSERT INTO `images` VALUES (1, NULL, NULL, NULL, NULL, '1_20201129140608138.jpg', '/thumbnail/1_20201129140608138.jpg', 2);
INSERT INTO `images` VALUES (2, NULL, NULL, NULL, NULL, '2_20201129140608165.jpg', '/thumbnail/2_20201129140608165.jpg', 2);
INSERT INTO `images` VALUES (3, NULL, NULL, NULL, NULL, '1_20201201173511425.jpg', '/thumbnail/1_20201201173511425.jpg', 3);
INSERT INTO `images` VALUES (4, NULL, NULL, NULL, NULL, '2_20201201173511578.jpg', '/thumbnail/2_20201201173511578.jpg', 3);
INSERT INTO `images` VALUES (5, NULL, NULL, NULL, NULL, '1_20201201174041493.jpg', '/thumbnail/1_20201201174041493.jpg', 4);
INSERT INTO `images` VALUES (6, NULL, NULL, NULL, NULL, '2_20201201174041586.jpg', '/thumbnail/2_20201201174041586.jpg', 4);

-- ----------------------------
-- Table structure for product_category
-- ----------------------------
DROP TABLE IF EXISTS `product_category`;
CREATE TABLE `product_category`  (
  `productid` bigint(0) NOT NULL,
  `categoryid` bigint(0) NOT NULL,
  INDEX `FKa9jknept9whwvd0v65rglqbu8`(`categoryid`) USING BTREE,
  INDEX `FKfk7krq3x2x826doffrbf3rsd5`(`productid`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of product_category
-- ----------------------------
INSERT INTO `product_category` VALUES (1, 1);
INSERT INTO `product_category` VALUES (2, 1);
INSERT INTO `product_category` VALUES (3, 1);
INSERT INTO `product_category` VALUES (4, 1);

-- ----------------------------
-- Table structure for products
-- ----------------------------
DROP TABLE IF EXISTS `products`;
CREATE TABLE `products`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `createdby` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `createddate` timestamp(0) NULL DEFAULT NULL,
  `modifiedby` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `modifieddate` timestamp(0) NULL DEFAULT NULL,
  `description` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `pathfile` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `price` int(0) NULL DEFAULT NULL,
  `productname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of products
-- ----------------------------
INSERT INTO `products` VALUES (1, NULL, '2020-11-29 04:24:54', NULL, '2020-11-29 04:24:56', 'aaaa', 'aaaa', 122, 'asd', '1asd', NULL, NULL);
INSERT INTO `products` VALUES (2, NULL, '2020-12-01 16:58:51', NULL, '2020-12-01 16:58:53', 'asdasdasdasdasdasdasdasdasdasdasdasdasd', '/fileproducts/3_20201129140607921.jpg', 2000, 'ona-test', 'title', NULL, NULL);
INSERT INTO `products` VALUES (3, NULL, NULL, NULL, NULL, 'asdasdasdasdasdasdasdasdasdasdasdasdasd', '/fileproducts/3_20201201173511203.jpg', 2000, 'ona-test', 'title', '/avatar_product1_20201201173511212.jpg', NULL);
INSERT INTO `products` VALUES (4, NULL, NULL, NULL, NULL, 'asdasdasdasdasdasdasdasdasdasdasdasdasd', '/fileproducts/3_20201201174041231.jpg', 2000, 'ona-test', 'title', '/avatar_product/1_20201201174041242.jpg', NULL);

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `createdby` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `createddate` timestamp(0) NULL DEFAULT NULL,
  `modifiedby` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `modifieddate` timestamp(0) NULL DEFAULT NULL,
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES (1, '', '2020-08-09 14:56:30', '', '2020-08-09 14:56:30', 'ROLE_MANAGER', 'Quản trị');
INSERT INTO `roles` VALUES (2, '', '2020-08-09 14:56:30', '', '2020-08-09 14:56:30', 'STAFF', 'Người dùng');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `userid` bigint(0) NOT NULL,
  `roleid` bigint(0) NOT NULL,
  INDEX `FKss07htsrasc17qsq2o9422nyh`(`roleid`) USING BTREE,
  INDEX `FKl4qqtaxj0v5ikodha3v2pmfl`(`userid`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1, 1);
INSERT INTO `user_role` VALUES (1, 2);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `createdby` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `createddate` timestamp(0) NULL DEFAULT NULL,
  `modifiedby` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `modifieddate` timestamp(0) NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `fullname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `numberphone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `status` int(0) NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, NULL, NULL, NULL, NULL, 'dangdat.09071997@gmail.com', NULL, NULL, '$2a$10$BoelM51ciwxBhZf5VLD0rOtzXOj3KXx8wMuyztvPoaA8koZrcYo8W', 1, 'dangdat.09071997@gmail.com');

SET FOREIGN_KEY_CHECKS = 1;
