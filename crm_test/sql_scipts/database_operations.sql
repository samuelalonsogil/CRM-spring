INSERT INTO `role` (name) VALUES  ('ROLE_USER'),('ROLE_ADMIN');

UPDATE `user` SET `password` = '$2a$12$O3olF8cRev2SmkwIHm/CTuQu6WFavV3ScRX1oaNrvtk0qDcmlQIHq' WHERE `username` = 'noa';

/* mantenimiento */
DELETE FROM `users_roles` WHERE `user_id` IS NOT NULL; 
DELETE FROM `user` WHERE `username` IS NOT NULL; 

/* texto to bcrypt -> https://bcrypt-generator.com/ */

/* INSERT USERS */
/* password -> 1234 */
INSERT INTO `user` (`username`,`password`,`enabled`, `first_name`, `last_name`, `email`)
VALUES ('samu','$2a$12$qgO.mwOkkfGeai4IeTNis.pqC6cumOqevbtxNYxL.wykWSCGYFR.q',1,'Samuel', 'Alonso Gil', 'samuelalonsogil@gmail.com')

;

INSERT INTO `user` (`username`,`password`,`enabled`, `first_name`, `last_name`, `email`)
VALUES 
('zoe','$2a$12$qgO.mwOkkfGeai4IeTNis.pqC6cumOqevbtxNYxL.wykWSCGYFR.q',1,'Zoe', 'Costas Cabeiro', 'zoe@gmail.com') 

;

/* INSERT USER_ROLES */
INSERT INTO `users_roles` (`user_id`,`role_id`)
VALUES 
(6, 1),
(6, 2)
;

INSERT INTO `users_roles` (`user_id`,`role_id`)
VALUES 
(7, 1)
;