
CREATE TABLE IF NOT EXISTS `hugin`.`client`(
   `id` INT NOT NULL AUTO_INCREMENT,
   `first_name` VARCHAR(64) NOT NULL,
    `last_name` VARCHAR(64) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `hugin`.`account`(
    `id` INT NOT NULL AUTO_INCREMENT,
    `account_type` INT NOT NULL,
    `balance` DOUBLE NOT NULL,
    `client_id` INT NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`client_id`) REFERENCES `hugin`.`client`(`id`)
);

CREATE TABLE IF NOT EXISTS `hugin`.`payment`(
    `id` INT NOT NULL AUTO_INCREMENT,
    `amount` double precision NOT NULL,
    `create_date_time` datetime(6) NOT NULL,
    `dest_acc_id` INT NOT NULL,
    `reason` varchar (255) NOT NULL,
    `source_acc_id` INT NOT NULL,
    PRIMARY KEY (`id`)
);