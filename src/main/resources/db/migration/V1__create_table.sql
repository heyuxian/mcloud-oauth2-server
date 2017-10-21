CREATE TABLE client_approval
(
  client_id   BIGINT NOT NULL,
  approval_id BIGINT NOT NULL,
  PRIMARY KEY (client_id, approval_id)
);

CREATE INDEX FK6d9wf63mapnddid2pwyogxadu
  ON client_approval (approval_id);

CREATE TABLE client_authority
(
  client_id    BIGINT NOT NULL,
  authority_id BIGINT NOT NULL,
  PRIMARY KEY (client_id, authority_id)
);

CREATE INDEX FKawkrs586vlrjc6bvfd1d43dws
  ON client_authority (authority_id);

CREATE TABLE client_resource
(
  client_id   BIGINT NOT NULL,
  resource_id BIGINT NOT NULL,
  PRIMARY KEY (client_id, resource_id)
);

CREATE INDEX FKshvh31pn9vjgyln9goppbe8up
  ON client_resource (resource_id);

CREATE TABLE client_scope
(
  client_id BIGINT NOT NULL,
  scope_id  BIGINT NOT NULL,
  PRIMARY KEY (client_id, scope_id)
);

CREATE INDEX FKj6mxw6pvppit3ehruqk0fbwpe
  ON client_scope (scope_id);

CREATE TABLE oauth_approval
(
  id            BIGINT AUTO_INCREMENT
    PRIMARY KEY,
  create_date   DATETIME     NULL,
  modified_date DATETIME     NULL,
  expires_at    DATETIME     NULL,
  name          VARCHAR(255) NULL,
  status        VARCHAR(255) NULL,
  client_id     BIGINT       NULL,
  user_id       BIGINT       NULL,
  created_date  DATETIME     NULL,
  CONSTRAINT UK_tojwelh71b4fdjjrqvoljqfqv
  UNIQUE (name)
);

CREATE INDEX FK3bjrqua90d1ej3heecowgoa2c
  ON oauth_approval (user_id);

CREATE INDEX FKf0vchujwiv8ax7hpjgec7g6mc
  ON oauth_approval (client_id);

CREATE TABLE oauth_approval_scope
(
  oauth_approval_id BIGINT NOT NULL,
  scope_id          BIGINT NOT NULL,
  approval_id       BIGINT NOT NULL,
  PRIMARY KEY (oauth_approval_id, scope_id)
);

CREATE INDEX FK65aeg11208kapv2x2t9qr5d4y
  ON oauth_approval_scope (approval_id);

CREATE INDEX FKd9d351ii75r7vtbg2m1dcwnsg
  ON oauth_approval_scope (scope_id);

CREATE TABLE oauth_authority
(
  id            BIGINT AUTO_INCREMENT
    PRIMARY KEY,
  create_date   DATETIME     NULL,
  modified_date DATETIME     NULL,
  name          VARCHAR(255) NULL,
  created_date  DATETIME     NULL
);

CREATE TABLE oauth_client
(
  id                     BIGINT AUTO_INCREMENT
    PRIMARY KEY,
  modified_date          DATETIME     NULL,
  access_token_validity  INT          NULL,
  additional_information TEXT         NULL,
  client_id              VARCHAR(255) NULL,
  client_secret          VARCHAR(255) NULL,
  grant_types            VARCHAR(255) NULL,
  redirect_uri           VARCHAR(255) NULL,
  refresh_token_validity INT          NULL,
  created_date           DATETIME     NULL,
  CONSTRAINT UK_6tiq1b1cchcusg2t6oe5mv8qw
  UNIQUE (client_id)
);

CREATE TABLE oauth_resources
(
  id            BIGINT AUTO_INCREMENT
    PRIMARY KEY,
  create_date   DATETIME     NULL,
  modified_date DATETIME     NULL,
  name          VARCHAR(255) NULL,
  created_date  DATETIME     NULL,
  CONSTRAINT UK_ce9njkq29gsvi07cnu71gbbn1
  UNIQUE (name)
);

CREATE TABLE oauth_scope
(
  id            BIGINT AUTO_INCREMENT
    PRIMARY KEY,
  create_date   DATETIME     NULL,
  modified_date DATETIME     NULL,
  name          VARCHAR(255) NULL,
  created_date  DATETIME     NULL,
  CONSTRAINT UK_7hoilwb3g67jnwsab08oe4hyn
  UNIQUE (name)
);

CREATE TABLE oauth_user
(
  id            BIGINT AUTO_INCREMENT
    PRIMARY KEY,
  modified_date DATETIME      NULL,
  password      VARCHAR(1024) NULL,
  username      VARCHAR(255)  NULL,
  created_date  DATETIME      NULL,
  CONSTRAINT UK_7prjgk5n8h0qbu5yggg933swc
  UNIQUE (username)
);

CREATE TABLE user_authority
(
  user_id      BIGINT NOT NULL,
  authority_id BIGINT NOT NULL,
  PRIMARY KEY (user_id, authority_id)
);

CREATE INDEX FKibm0ptairmvui7tm2icpdpwmq
  ON user_authority (authority_id);

