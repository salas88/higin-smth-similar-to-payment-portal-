INSERT into client (id,first_name,last_name,password)
 VALUES ('1','Vladislav', 'Hasporian','$2y$12$KwyO.pfTaSKn3bgsWAXmm.5ZAfahf6xVZXqbkHXXdfqpB55S9DZoG');
INSERT into client (id,first_name,last_name,password)
VALUES ('2','Gaga', 'Kiknadze','$2y$12$hjQ/P0DI4cs.DIzgesh6c.HzF0/BFOk9GshUrA/yc6IphugGFFX2e');

INSERT INTO account (id,account_type,balance,client_id) VALUES ('1','0', '2000', '1');
INSERT INTO account (id,account_type,balance,client_id) VALUES ('2','1', '2000', '2');
INSERT INTO account (id,account_type,balance,client_id) VALUES ('3','0', '2000', '1');
INSERT INTO account (id,account_type,balance,client_id) VALUES ('4','1', '2000', '2');

insert into client_role(client_id, role_set) values (1, 'ADMIN');
insert into client_role(client_id, role_set) values (1, 'CLIENT');
insert into client_role(client_id, role_set) values (2, 'CLIENT');
