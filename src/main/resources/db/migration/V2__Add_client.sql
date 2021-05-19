INSERT into client (id,first_name,last_name,password) VALUES ('1','Vladislav', 'Hasporian','123456');
INSERT into client (id,first_name,last_name,password) VALUES ('2','Gaga', 'Kiknadze','12345');

INSERT INTO account (id,account_type,balance,client_id) VALUES ('1','0', '2000', '1');
INSERT INTO account (id,account_type,balance,client_id) VALUES ('2','1', '2000', '1');
INSERT INTO account (id,account_type,balance,client_id) VALUES ('3','0', '2000', '2');
INSERT INTO account (id,account_type,balance,client_id) VALUES ('4','1', '2000', '2');

insert into client_role(client_id, role_set) values (1, 'ADMIN');
insert into client_role(client_id, role_set) values (2, 'CLIENT');
