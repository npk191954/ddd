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
