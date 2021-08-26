INSERT INTO oauth_client_details (client_id, client_secret, web_server_redirect_uri, scope, access_token_validity, refresh_token_validity, resource_ids, authorized_grant_types, additional_information) VALUES ('mobile', '{bcrypt}$2a$10$gPhlXZfms0EpNHX0.HHptOhoFD1AoxSr/yUIdTqA8vtjeP4zi0DDu', 'http://localhost:8080/login', 'READ,WRITE', '3600', '10000', 'inventory,payment', 'authorization_code,password,refresh_token,implicit', '{}');

INSERT INTO PERMISSION (NAME) VALUES
('create_profile'),
('read_profile'),
('update_profile'),
('delete_profile');

INSERT INTO role (NAME) VALUES
('ROLE_admin'),('ROLE_operator');

insert into user (id, username,password, email, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked) VALUES ('1', 'demo','{bcrypt}$2a$10$b1iVB9IEJEvH0IanmERFneAVKdn03gdNEE5bc9eXdU3jZi1xV9u9e', 'demo@demo.com', '1', '1', '1', '1');
insert into  user (id, username,password, email, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked) VALUES ('2', 'joy', '{bcrypt}$2a$10$b1iVB9IEJEvH0IanmERFneAVKdn03gdNEE5bc9eXdU3jZi1xV9u9e','joy@joy.com', '1', '1', '1', '1');


INSERT INTO PERMISSION_ROLE (PERMISSION_ID, ROLE_ID) VALUES
(1,1), /*create-> admin */
(2,1), /* read admin */
(3,1), /* update admin */
(4,1), /* delete admin */
(2,2),  /* read operator */
(3,2);  /* update operator */

INSERT INTO ROLE_USER (ROLE_ID, USER_ID)
VALUES
(1, 1) /* demo-admin */,
(2, 2) /* joy-operatorr */ ;