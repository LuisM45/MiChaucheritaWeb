USE `mysql`;
DROP DATABASE `mi_chaucherita`;
CREATE DATABASE `mi_chaucherita`;
USE `mi_chaucherita`;

CREATE TABLE `user`(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    `lastname` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
);


CREATE TABLE `account`(
    `user_id` BIGINT NOT NULL,
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `name` varchar(255) NOT NULL,
    `type_id` BIGINT NOT NULL,
    PRIMARY KEY (`id`,`user_id`)
);

CREATE TABLE `account_type`(
    `id` BIGINT NOT NULL,
    `name` CHAR(31) NOT NULL,
    `descriptor` VARCHAR(255) NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `movement`(
    `user_id` BIGINT NOT NULL,
    `sender_id` BIGINT,
    `recipient_id` BIGINT,
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `concept` VARCHAR(255) NOT NULL,
    `datetime` DATETIME NOT NULL,
    `ammount` DECIMAL(8, 2) NOT NULL,
    PRIMARY KEY (`id`,`user_id`,`sender_id`,`recipient_id`)
);

CREATE TABLE `statement`(
    `user_id` BIGINT NOT NULL,
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `start_datetime` DATETIME NOT NULL,
    `end_datetime` DATETIME NOT NULL,
    PRIMARY KEY (`id`,`user_id`)
);

ALTER TABLE `user` AUTO_INCREMENT=1001;
ALTER TABLE `account` AUTO_INCREMENT=1001;
ALTER TABLE `movement` AUTO_INCREMENT=1001;
ALTER TABLE `statement` AUTO_INCREMENT=1001;

ALTER TABLE `account` 
    ADD CONSTRAINT `account_fk_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`);

ALTER TABLE `account` 
    ADD CONSTRAINT `account_fk_type`
    FOREIGN KEY (`type_id`)
    REFERENCES `account_type` (`id`);

ALTER TABLE `movement`
    ADD CONSTRAINT `movement_fk_user`
    FOREIGN KEY (`user_id`) 
    REFERENCES `user`(`id`);

ALTER TABLE `movement`
    ADD CONSTRAINT `movement_fk_sender`
    FOREIGN KEY (`sender_id`) 
    REFERENCES `account`(`id`);

ALTER TABLE `movement`
    ADD CONSTRAINT `movement_fk_recipient`
    FOREIGN KEY (`recipient_id`) 
    REFERENCES `account`(`id`);

ALTER TABLE `statement`
    ADD CONSTRAINT `statement_foreign`
    FOREIGN KEY (`user_id`)
    REFERENCES `user`(`id`);

