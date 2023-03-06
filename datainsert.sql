INSERT INTO `account_type`(`id`,`name`) VALUES
(1,'INGRESO'),
(2,'EGRESO'),
(3,'INGRESO_EGRESO);

INSERT INTO `user`(`id`,`name`) VALUES
(NULL,'Pepe'),

INSERT INTO `account`(`user_id`,`id`,`name`,`type_id`) VALUES
(1001,NULL,'Nomina',1),
(1001,NULL,'Banco_A',2),
(1001,NULL,'Banco_B',2),
(1001,NULL,'Universidad',3),

INSERT INTO `movement`(`sender_id`,`recipient_id`,`id`,`concept`,`date`,`ammount`) VALUES 
(1001,1002,NULL,'Salario','2020-01-01',400.00),
(1001,1002,NULL,'Transferencia','2020-01-02',200.00),
(1002,1003,NULL,'Matricula','2020-01-03',100.00);

INSERT INTO `statement`(`user_id`,`id`,`start_datetime`,`end_datetime`) VALUES
(1001,NULL,'2020-01-01','2020-01-03');
