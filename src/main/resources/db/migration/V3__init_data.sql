INSERT INTO oauth_user (username, user_type, password, nick_name, email, avatar, phone, created_date, modified_date) VALUES ('mcloud-admin', 'ADMIN', '$2a$08$3ZgwOx1R5MXRM7KgM4oUcej2uSXH95axHQ0cO9eeJ3kEEItBRS4ym', 'MCloud Admin', 'admin@mcloud.com', '', '', '2017-12-02 19:11:46', '2017-12-02 19:11:46');
INSERT INTO oauth_user (username, user_type, password, nick_name, email, avatar, phone, created_date, modified_date) VALUES ('mcloud-user', 'USER', '$2a$08$kUqRkqTWbpuWCJijmzo3Fe.Pf/PpUntPeX9uGsRX4s2hiXTRSQJQq', 'MCloud Test User', 'test@mcloud.com', '', '', '2017-12-02 19:11:46', '2017-12-02 19:11:46');

INSERT INTO oauth_client (client_id, user_id, client_secret, name, description, redirect_uri, grant_types, access_token_validity, refresh_token_validity, additional_information, status, created_date, modified_date) VALUES ('mcloud-blog', 2, '$2a$08$1oZmlN50GkSBQIAy/RK6mO6aG76ANU5cCGkM9bWsiSIVC6UAQYxRC', 'MCloud Blog', null, 'https://www.getpostman.com/oauth2/callback', 'CODE', 0, 0, '0', 'NORMAL','2017-12-02 19:17:24', '2017-12-02 19:17:24');

INSERT INTO oauth_resource (name, description, created_date, modified_date) VALUES ('ADMIN', null, '2017-12-02 19:05:19', '2017-12-02 19:05:22');
INSERT INTO oauth_scope (name, description, created_date, modified_date) VALUES ('USER_INFO', null, '2017-12-02 19:05:39', '2017-12-02 19:05:42');

INSERT INTO oauth_authority (name, description, created_date, modified_date) VALUES ('ROLE_ADMIN', null, '2017-12-02 18:57:32', '2017-12-02 18:57:37');
INSERT INTO oauth_authority (name, description, created_date, modified_date) VALUES ('ROLE_USER', null, '2017-12-02 18:58:23', '2017-12-02 18:58:26');
INSERT INTO oauth_authority (name, description, created_date, modified_date) VALUES ('ROLE_DEVELOPER', null, '2017-12-02 18:58:23', '2017-12-02 18:58:26');

INSERT INTO client_authority (client_id, authority_id) VALUES (1, 3);
INSERT INTO client_resource (client_id, resource_id) VALUES (1, 1);
INSERT INTO client_scope (client_id, scope_id) VALUES (1, 1);
INSERT INTO user_authority (user_id, authority_id) VALUES (1, 1);
INSERT INTO user_authority (user_id, authority_id) VALUES (2, 2);