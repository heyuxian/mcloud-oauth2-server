-- ----------------------------
-- Table structure for approval_scope
-- ----------------------------
DROP TABLE IF EXISTS `approval_scope`;
CREATE TABLE `approval_scope` (
  `approve_id` bigint(20) NOT NULL,
  `scope_id` bigint(20) NOT NULL,
  PRIMARY KEY (`approve_id`,`scope_id`),
  KEY `idx_scope_id` (`scope_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for client_approval
-- ----------------------------
DROP TABLE IF EXISTS `client_approval`;
CREATE TABLE `client_approval` (
  `client_id` bigint(20) NOT NULL,
  `approval_id` bigint(20) NOT NULL,
  PRIMARY KEY (`client_id`,`approval_id`),
  KEY `idx_approval_id` (`approval_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for client_authority
-- ----------------------------
DROP TABLE IF EXISTS `client_authority`;
CREATE TABLE `client_authority` (
  `client_id` bigint(20) NOT NULL,
  `authority_id` bigint(20) NOT NULL,
  PRIMARY KEY (`client_id`,`authority_id`),
  KEY `idx_authority_id` (`authority_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for client_resource
-- ----------------------------
DROP TABLE IF EXISTS `client_resource`;
CREATE TABLE `client_resource` (
  `client_id` bigint(20) NOT NULL,
  `resource_id` bigint(20) NOT NULL,
  PRIMARY KEY (`client_id`,`resource_id`),
  KEY `idx_resource_id` (`resource_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for client_scope
-- ----------------------------
DROP TABLE IF EXISTS `client_scope`;
CREATE TABLE `client_scope` (
  `client_id` bigint(20) NOT NULL,
  `scope_id` bigint(20) NOT NULL,
  PRIMARY KEY (`client_id`,`scope_id`),
  KEY `idx_scope_id` (`scope_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for oauth_approval
-- ----------------------------
DROP TABLE IF EXISTS `oauth_approval`;
CREATE TABLE `oauth_approval` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status` varchar(255) NOT NULL,
  `client_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `expires_at` datetime NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_name` (`name`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_client_id` (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for oauth_authority
-- ----------------------------
DROP TABLE IF EXISTS `oauth_authority`;
CREATE TABLE `oauth_authority` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `created_date` datetime NOT NULL,
  `modified_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_name` (`name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for oauth_client
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client`;
CREATE TABLE `oauth_client` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `client_id` varchar(255) NOT NULL,
  `client_secret` varchar(255) NOT NULL,
  `name` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `redirect_uri` varchar(255) NOT NULL,
  `grant_types` varchar(255) NOT NULL,
  `access_token_validity` int(11) NOT NULL DEFAULT '0',
  `refresh_token_validity` int(11) NOT NULL DEFAULT '0',
  `additional_information` text,
  `status` varchar(20) NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_client_id` (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for oauth_resource
-- ----------------------------
DROP TABLE IF EXISTS `oauth_resource`;
CREATE TABLE `oauth_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `created_date` datetime NOT NULL,
  `modified_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for oauth_scope
-- ----------------------------
DROP TABLE IF EXISTS `oauth_scope`;
CREATE TABLE `oauth_scope` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `created_date` datetime NOT NULL,
  `modified_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for oauth_user
-- ----------------------------
DROP TABLE IF EXISTS `oauth_user`;
CREATE TABLE `oauth_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `user_type` varchar(20) NOT NULL,
  `password` varchar(255) NOT NULL,
  `nick_name` varchar(50) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `created_date` datetime NOT NULL,
  `modified_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_authority
-- ----------------------------
DROP TABLE IF EXISTS `user_authority`;
CREATE TABLE `user_authority` (
  `user_id` bigint(20) NOT NULL,
  `authority_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`authority_id`),
  KEY `idx_authority_id` (`authority_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for developer_info
-- ----------------------------
DROP TABLE IF EXISTS `developer_info`;
CREATE TABLE `developer_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `type` varchar(30) NOT NULL,
  `domain` varchar(50) NOT NULL,
  `protocol` varchar(20) NOT NULL,
  `real_name` varchar(20) DEFAULT NULL,
  `id_card_img` varchar(255) DEFAULT NULL,
  `corp_license_img` varchar(255) DEFAULT NULL,
  `corp_license_number` varchar(100) DEFAULT NULL,
  `corp_name` varchar(50) DEFAULT NULL,
  `legal_person_id_card_img` varchar(255) DEFAULT NULL,
  `legal_person_name` varchar(20) DEFAULT NULL,
  `tax_img` varchar(255) DEFAULT NULL,
  `status` varchar(20) NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

