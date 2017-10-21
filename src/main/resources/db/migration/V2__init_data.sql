INSERT INTO client_approval (client_id, approval_id) VALUES (9, 1);

INSERT INTO client_authority (client_id, authority_id) VALUES (1, 1);
INSERT INTO client_authority (client_id, authority_id) VALUES (9, 1);

INSERT INTO client_resource (client_id, resource_id) VALUES (1, 1);
INSERT INTO client_resource (client_id, resource_id) VALUES (9, 1);

INSERT INTO client_scope (client_id, scope_id) VALUES (1, 1);
INSERT INTO client_scope (client_id, scope_id) VALUES (9, 1);

INSERT INTO oauth_approval (create_date, modified_date, expires_at, name, status, client_id, user_id, created_date) VALUES ('2017-09-04 22:44:37', '2017-09-04 22:44:37', null, 'string', 'APPROVED', null, null, null);

INSERT INTO oauth_authority (create_date, modified_date, name, created_date) VALUES ('2017-09-02 21:36:58', '2017-09-02 21:36:58', 'ROLE_USER', null);

INSERT INTO oauth_client (modified_date, access_token_validity, additional_information, client_id, client_secret, grant_types, redirect_uri, refresh_token_validity, created_date) VALUES ('2017-09-09 19:44:48', 1000000, '{}', 'mcloud-blog', '$2a$08$zW51jdV8.3xcbyBEQQWtaOZsewbPmTqSUIzrkfCSB3PEYA9hmQ.3y', 'CODE,CLIENT_CREDENTIALS,REFRESH_TOKEN,IMPLICIT,PASSWORD', 'https://www.getpostman.com/oauth2/callback,http://localhost:8081/blog/webjars/springfox-swagger-ui/o2c.html,http://localhost:8443', 1000000, null);

INSERT INTO oauth_resources (create_date, modified_date, name, created_date) VALUES ('2017-09-02 21:36:46', '2017-09-02 21:36:46', 'userinfo', null);

INSERT INTO oauth_scope (create_date, modified_date, name, created_date) VALUES ('2017-09-02 21:35:52', '2017-09-02 21:35:52', 'read', null);

INSERT INTO oauth_user (modified_date, password, username, created_date) VALUES ('2017-10-10 23:09:23', '$2a$08$SuFiBd.szqVhlq3nkTO6dOGf9E9G/SieJFoTln7Z/Y5YgB8f9tP1i', 'user', '2017-10-10 23:09:23');

INSERT INTO user_authority (user_id, authority_id) VALUES (1, 1);