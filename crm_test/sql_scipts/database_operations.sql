INSERT INTO `role` (name) VALUES  ('ROLE_USER'),('ROLE_ADMIN');

INSERT INTO `user` (`username`,`password`,`enabled`, `first_name`, `last_name`, `email`)
VALUES 
('samu','$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K',1,'Samuel', 'Alonso Gil', 'samuelalonsogil@gmail.com'),
('guille','$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K',1,'Guille', 'Alonso Gil', 'guille@gmail.com');

INSERT INTO `users_roles` (`user_id`,`role_id`)
VALUES 
(3, 1),
(3, 2)

;

INSERT INTO `user` (`username`,`password`,`enabled`, `first_name`, `last_name`, `email`)
VALUES 
('noa','1234',1,'Noa', 'Costas Cabeiro', 'noa@gmail.com') 

;

UPDATE `user` SET `password` = '$2a$12$O3olF8cRev2SmkwIHm/CTuQu6WFavV3ScRX1oaNrvtk0qDcmlQIHq' WHERE `username` = 'noa';