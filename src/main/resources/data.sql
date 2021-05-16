INSERT IGNORE INTO `hugin`.`client` (`id`,`first_name`,`last_name`)
    VALUES ('1','Vladislav', 'Hasporian');
INSERT IGNORE INTO `hugin`.`client` (`id`,`first_name`,`last_name`)
    VALUES ('2','Gaga', 'Kiknadze');

INSERT IGNORE INTO `hugin`.`account` (`id`,`account_type`,`balance`, `client_id`)
    VALUES ('1','0', '2000', '1');
INSERT IGNORE INTO `hugin`.`account` (`id`,`account_type`,`balance`, `client_id`)
    VALUES ('2','1', '2000', '1');
INSERT IGNORE INTO `hugin`.`account` (`id`,`account_type`,`balance`, `client_id`)
    VALUES ('3','0', '2000', '2');
INSERT IGNORE INTO `hugin`.`account` (`id`,`account_type`,`balance`, `client_id`)
    VALUES ('4','1', '2000', '2');

