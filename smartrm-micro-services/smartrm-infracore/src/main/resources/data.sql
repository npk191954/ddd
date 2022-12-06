-- ----------------------------
-- Records of user_info
-- ----------------------------
BEGIN;
INSERT INTO `user_info`
VALUES (1, 'a', 21);
INSERT INTO `user_info`
VALUES (2, 'b', 23);
INSERT INTO `user_info`
VALUES (3, 'c', 50);
INSERT INTO `user_info`
VALUES (4, 'd', 15);
INSERT INTO `user_info`
VALUES (5, 'e', 20);
COMMIT;

BEGIN;
INSERT INTO `user_info_backup`
VALUES (1, 'a', 21);
INSERT INTO `user_info_backup`
VALUES (2, 'b', 23);
INSERT INTO `user_info_backup`
VALUES (3, 'c', 50);
INSERT INTO `user_info_backup`
VALUES (4, 'd', 15);
INSERT INTO `user_info_backup`
VALUES (5, 'e', 20);
COMMIT;

BEGIN;
INSERT INTO `trade_slot_vending_machine`
VALUES (1, 10, 1, 1);
INSERT INTO `trade_slot_vending_machine`
VALUES (2, 11, 1, 1);
INSERT INTO `trade_slot_vending_machine`
VALUES (3, 12, 1, 1);
COMMIT;

BEGIN;
INSERT INTO `msg_send_fail_record`(`id`, `topic`, `tags`, `keys`, `body`)
VALUES (1, 'DeviceFailureEvent', '1', '1', 'msg str');
INSERT INTO `msg_send_fail_record`(`id`, `topic`, `tags`, `keys`, `body`)
VALUES (2, 'DeviceFailureEvent', '1', '1', 'msg str');
INSERT INTO `msg_send_fail_record`(`id`, `topic`, `tags`, `keys`, `body`)
VALUES (3, 'DeviceFailureEvent', '1', '1', 'msg str');
COMMIT;

BEGIN;
INSERT INTO `msg_consume_fail_record`(`id`, `topic`, `tags`, `keys`, `body`)
VALUES (1, 'DeviceFailureEvent', '1', '1', 'msg str');
INSERT INTO `msg_consume_fail_record`(`id`, `topic`, `tags`, `keys`, `body`)
VALUES (2, 'DeviceFailureEvent', '1', '1', 'msg str');
INSERT INTO `msg_consume_fail_record`(`id`, `topic`, `tags`, `keys`, `body`)
VALUES (3, 'DeviceFailureEvent', '1', '1', 'msg str');
COMMIT;


