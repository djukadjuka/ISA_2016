

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(25) DEFAULT NULL,
  `password` varchar(25) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `firstName` varchar(15) DEFAULT NULL,
  `lastName` varchar(15) DEFAULT NULL,
  `profilePicture` varchar(260) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO users VALUES(1,"user1","pass1","email1@gmail.com","Dzajo","Dzajic","NA");
INSERT INTO users VALUES(2,"user2","pass2","email2@gmail.com","Nikola","Tesla","NA");
INSERT INTO users VALUES(3,"user3","pass3","email3@gmail.com","Milutin","Milankovic","NA");
INSERT INTO users VALUES(4,"user4","pass4","email4@gmail.com","Tomas","Edison","NA");
INSERT INTO users VALUES(5,"user5","pass5","email5@gmail.com","Albert","Ajnstajn","NA");
INSERT INTO users VALUES(6,"user6","pass6","email6@gmail.com","Graham","Bell","NA");
INSERT INTO users VALUES(7,"user7","pass7","email7@gmail.com","Edvard","Dzenner","NA");
INSERT INTO users VALUES(8,"user8","pass8","email8@gmail.com","Bil","Gejts","NA");
INSERT INTO users VALUES(9,"user9","pass9","email9@gmail.com","Zvonko","Bogdan","NA");

select * from users;