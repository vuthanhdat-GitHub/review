/*
 Navicat Premium Data Transfer

 Source Server         : LocalPGSQL
 Source Server Type    : PostgreSQL
 Source Server Version : 90520
 Source Host           : localhost:5432
 Source Catalog        : tradedoc
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 90520
 File Encoding         : 65001

 Date: 02/01/2021 08:44:15
*/


-- ----------------------------
-- Sequence structure for category_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."category_id_seq";
CREATE SEQUENCE "public"."category_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for code_signup_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."code_signup_id_seq";
CREATE SEQUENCE "public"."code_signup_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for history_payment_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."history_payment_id_seq";
CREATE SEQUENCE "public"."history_payment_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for images_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."images_id_seq";
CREATE SEQUENCE "public"."images_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for products_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."products_id_seq";
CREATE SEQUENCE "public"."products_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for roles_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."roles_id_seq";
CREATE SEQUENCE "public"."roles_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for users_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."users_id_seq";
CREATE SEQUENCE "public"."users_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS "public"."category";
CREATE TABLE "public"."category" (
  "id" int8 NOT NULL DEFAULT nextval('category_id_seq'::regclass),
  "createdby" varchar(255) COLLATE "pg_catalog"."default",
  "createddate" timestamp(6),
  "modifiedby" varchar(255) COLLATE "pg_catalog"."default",
  "modifieddate" timestamp(6),
  "code" varchar(255) COLLATE "pg_catalog"."default",
  "name" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Table structure for code_signup
-- ----------------------------
DROP TABLE IF EXISTS "public"."code_signup";
CREATE TABLE "public"."code_signup" (
  "id" int8 NOT NULL DEFAULT nextval('code_signup_id_seq'::regclass),
  "createdby" varchar(255) COLLATE "pg_catalog"."default",
  "createddate" timestamp(6),
  "modifiedby" varchar(255) COLLATE "pg_catalog"."default",
  "modifieddate" timestamp(6),
  "code" varchar(255) COLLATE "pg_catalog"."default",
  "email" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Table structure for history_payment
-- ----------------------------
DROP TABLE IF EXISTS "public"."history_payment";
CREATE TABLE "public"."history_payment" (
  "id" int8 NOT NULL DEFAULT nextval('history_payment_id_seq'::regclass),
  "createdby" varchar(255) COLLATE "pg_catalog"."default",
  "createddate" timestamp(6),
  "modifiedby" varchar(255) COLLATE "pg_catalog"."default",
  "modifieddate" timestamp(6),
  "deleted" int4,
  "paymenttype" varchar(255) COLLATE "pg_catalog"."default",
  "status" int4,
  "total" int8,
  "customerid" int8,
  "productid" int8
)
;

-- ----------------------------
-- Table structure for images
-- ----------------------------
DROP TABLE IF EXISTS "public"."images";
CREATE TABLE "public"."images" (
  "id" int8 NOT NULL DEFAULT nextval('images_id_seq'::regclass),
  "createdby" varchar(255) COLLATE "pg_catalog"."default",
  "createddate" timestamp(6),
  "modifiedby" varchar(255) COLLATE "pg_catalog"."default",
  "modifieddate" timestamp(6),
  "name" varchar(255) COLLATE "pg_catalog"."default",
  "pathfile" varchar(255) COLLATE "pg_catalog"."default",
  "productid" int8
)
;

-- ----------------------------
-- Table structure for product_category
-- ----------------------------
DROP TABLE IF EXISTS "public"."product_category";
CREATE TABLE "public"."product_category" (
  "productid" int8 NOT NULL,
  "categoryid" int8 NOT NULL
)
;

-- ----------------------------
-- Table structure for products
-- ----------------------------
DROP TABLE IF EXISTS "public"."products";
CREATE TABLE "public"."products" (
  "id" int8 NOT NULL DEFAULT nextval('products_id_seq'::regclass),
  "createdby" varchar(255) COLLATE "pg_catalog"."default",
  "createddate" timestamp(6),
  "modifiedby" varchar(255) COLLATE "pg_catalog"."default",
  "modifieddate" timestamp(6),
  "avatar" varchar(255) COLLATE "pg_catalog"."default",
  "description" text COLLATE "pg_catalog"."default",
  "pathfile" varchar(255) COLLATE "pg_catalog"."default",
  "price" int4,
  "productname" varchar(255) COLLATE "pg_catalog"."default",
  "title" varchar(255) COLLATE "pg_catalog"."default",
  "type" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS "public"."roles";
CREATE TABLE "public"."roles" (
  "id" int8 NOT NULL DEFAULT nextval('roles_id_seq'::regclass),
  "createdby" varchar(255) COLLATE "pg_catalog"."default",
  "createddate" timestamp(6),
  "modifiedby" varchar(255) COLLATE "pg_catalog"."default",
  "modifieddate" timestamp(6),
  "code" varchar(255) COLLATE "pg_catalog"."default",
  "name" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS "public"."user_role";
CREATE TABLE "public"."user_role" (
  "userid" int8 NOT NULL,
  "roleid" int8 NOT NULL
)
;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS "public"."users";
CREATE TABLE "public"."users" (
  "id" int8 NOT NULL DEFAULT nextval('users_id_seq'::regclass),
  "createdby" varchar(255) COLLATE "pg_catalog"."default",
  "createddate" timestamp(6),
  "modifiedby" varchar(255) COLLATE "pg_catalog"."default",
  "modifieddate" timestamp(6),
  "email" varchar(255) COLLATE "pg_catalog"."default",
  "fullname" varchar(255) COLLATE "pg_catalog"."default",
  "numberphone" varchar(255) COLLATE "pg_catalog"."default",
  "password" varchar(255) COLLATE "pg_catalog"."default",
  "status" int4,
  "username" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."category_id_seq"
OWNED BY "public"."category"."id";
SELECT setval('"public"."category_id_seq"', 2, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."code_signup_id_seq"
OWNED BY "public"."code_signup"."id";
SELECT setval('"public"."code_signup_id_seq"', 11, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."history_payment_id_seq"
OWNED BY "public"."history_payment"."id";
SELECT setval('"public"."history_payment_id_seq"', 5, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."images_id_seq"
OWNED BY "public"."images"."id";
SELECT setval('"public"."images_id_seq"', 5, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."products_id_seq"
OWNED BY "public"."products"."id";
SELECT setval('"public"."products_id_seq"', 3, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."roles_id_seq"
OWNED BY "public"."roles"."id";
SELECT setval('"public"."roles_id_seq"', 2, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."users_id_seq"
OWNED BY "public"."users"."id";
SELECT setval('"public"."users_id_seq"', 7, true);

-- ----------------------------
-- Primary Key structure for table category
-- ----------------------------
ALTER TABLE "public"."category" ADD CONSTRAINT "category_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table code_signup
-- ----------------------------
ALTER TABLE "public"."code_signup" ADD CONSTRAINT "code_signup_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table history_payment
-- ----------------------------
ALTER TABLE "public"."history_payment" ADD CONSTRAINT "history_payment_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table images
-- ----------------------------
ALTER TABLE "public"."images" ADD CONSTRAINT "images_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table products
-- ----------------------------
ALTER TABLE "public"."products" ADD CONSTRAINT "products_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table roles
-- ----------------------------
ALTER TABLE "public"."roles" ADD CONSTRAINT "roles_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table users
-- ----------------------------
ALTER TABLE "public"."users" ADD CONSTRAINT "users_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Foreign Keys structure for table history_payment
-- ----------------------------
ALTER TABLE "public"."history_payment" ADD CONSTRAINT "fkpi8b7h7516x05k8yjvlk75xy1" FOREIGN KEY ("customerid") REFERENCES "public"."users" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."history_payment" ADD CONSTRAINT "fkqxvp7xhwercvg97fcxh3exmj9" FOREIGN KEY ("productid") REFERENCES "public"."products" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table images
-- ----------------------------
ALTER TABLE "public"."images" ADD CONSTRAINT "fktmpcqvrplwcthq9jb3las9xy0" FOREIGN KEY ("productid") REFERENCES "public"."products" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table product_category
-- ----------------------------
ALTER TABLE "public"."product_category" ADD CONSTRAINT "fka9jknept9whwvd0v65rglqbu8" FOREIGN KEY ("categoryid") REFERENCES "public"."category" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."product_category" ADD CONSTRAINT "fkfk7krq3x2x826doffrbf3rsd5" FOREIGN KEY ("productid") REFERENCES "public"."products" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table user_role
-- ----------------------------
ALTER TABLE "public"."user_role" ADD CONSTRAINT "fkl4qqtaxj0v5ikodha3v2pmfl" FOREIGN KEY ("userid") REFERENCES "public"."users" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."user_role" ADD CONSTRAINT "fkss07htsrasc17qsq2o9422nyh" FOREIGN KEY ("roleid") REFERENCES "public"."roles" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
