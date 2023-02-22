INSERT INTO `account_type`(`id`,`name`,`descriptor`) VALUES
(1,'Ingreso',NULL),
(2,'Egreso',NULL),
(3,'IngresoEgreso',NULL);

INSERT INTO `user`(`id`,`name`,`lastname`) VALUES
(1,'External','External'),
(NULL,'Marco','Valdez'),
(NULL,'Raul','Paredes');

INSERT INTO `account`(`user_id`,`id`,`name`,`type_id`) VALUES
(1,1,'External',3),
(1001,NULL,'Nomina',1),
(1001,NULL,'Banco',3),
(1001,NULL,'Universidad',2),
(1002,NULL,'Nomina',1),
(1002,NULL,'Banco',3),
(1002,NULL,'Universidad',2);

INSERT INTO `movement`(`user_id`,`sender_id`,`recipient_id`,`id`,`concept`,`datetime`,`ammount`) VALUES 
(1001,1,1001,NULL,'Salario','2020-01-01T12:00',400.00),
(1001,1001,1002,NULL,'Transferencia','2020-01-01T13:00',200.00),
(1001,1002,1003,NULL,'Matricula','2020-01-01T16:00',100.00);

INSERT INTO `statement`(`user_id`,`id`,`start_datetime`,`end_datetime`) VALUES
(1001,NULL,'2020-01-01T00:00','2020-01-02T00:00');
