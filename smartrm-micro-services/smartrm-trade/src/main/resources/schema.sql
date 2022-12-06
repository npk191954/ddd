SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`
(
    `id`   bigint(20) NOT NULL AUTO_INCREMENT,
    `name` varchar(50) NOT NULL DEFAULT '',
    `age`  int(11) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY    `name_index` (`name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

DROP TABLE IF EXISTS `user_info_backup`;
CREATE TABLE `user_info_backup`
(
    `id`   bigint(20) NOT NULL AUTO_INCREMENT,
    `name` varchar(50) NOT NULL DEFAULT '',
    `age`  int(11) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY    `uk_name` (`name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

DROP TABLE IF EXISTS `trade_slot_vending_machine`;
CREATE TABLE `trade_slot_vending_machine`
(
    `machine_id`   bigint(20) NOT NULL AUTO_INCREMENT,
    `cur_order_id` bigint(20) NOT NULL,
    `version`      bigint(20),
    `state`        tinyint(1) DEFAULT NULL,
    PRIMARY KEY (`machine_id`),
    UNIQUE KEY `uk_cur_order_id` (`cur_order_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

DROP TABLE IF EXISTS `msg_send_fail_record`;
CREATE TABLE `msg_send_fail_record`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT,
    `topic`       varchar(100) NOT NULL DEFAULT '',
    `tags`        varchar(100) NOT NULL DEFAULT '',
    `keys`        varchar(100) NOT NULL DEFAULT '',
    `body`        varchar(500) NOT NULL DEFAULT '',
    `state`       tinyint(1) NOT NULL DEFAULT 0,
    `create_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

DROP TABLE IF EXISTS `msg_consume_fail_record`;
CREATE TABLE `msg_consume_fail_record`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT,
    `topic`       varchar(100) NOT NULL DEFAULT '',
    `tags`        varchar(100) NOT NULL DEFAULT '',
    `keys`        varchar(100) NOT NULL DEFAULT '',
    `body`        varchar(500) NOT NULL DEFAULT '',
    `state`       tinyint(1) NOT NULL DEFAULT 0,
    `create_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

DROP TABLE IF EXISTS `unique_id_generator`;
CREATE TABLE `unique_id_generator`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT,
    `create_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
DEFAULT CHARSET = utf8mb4;

