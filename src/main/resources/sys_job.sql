/*
 Navicat Premium Data Transfer

 Source Server         : 本地postgres
 Source Server Type    : PostgreSQL
 Source Server Version : 110008
 Source Host           : localhost:5432
 Source Catalog        : dynamic_timing
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 110008
 File Encoding         : 65001

 Date: 05/11/2020 15:02:45
*/


-- ----------------------------
-- Table structure for sys_job
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_job";
CREATE TABLE "public"."sys_job" (
  "job_id" varchar(128) COLLATE "pg_catalog"."default" NOT NULL,
  "bean_name" varchar(255) COLLATE "pg_catalog"."default",
  "method_name" varchar(255) COLLATE "pg_catalog"."default",
  "method_params" varchar(255) COLLATE "pg_catalog"."default",
  "cron_expression" varchar(255) COLLATE "pg_catalog"."default",
  "job_status" int4,
  "remark" varchar(255) COLLATE "pg_catalog"."default",
  "create_time" date,
  "update_time" date
)
;

-- ----------------------------
-- Primary Key structure for table sys_job
-- ----------------------------
ALTER TABLE "public"."sys_job" ADD CONSTRAINT "sys_job_pkey" PRIMARY KEY ("job_id");
