package me.javaroad.oauth.controller;

/**
 * @author heyx
 */
public class OAuthConstants {

    public static final String API_VERSION = "v1";
    public static final String ADMIN_PREFIX = "admin";
    public static final String CONSOLE_PREFIX = "console";
    public static final String CONSOLE_API_PREFIX = "console-api";
    public static final String ADMIN_API_PREFIX = "admin-api";
    public static final String API_PREFIX = "api/" + API_VERSION;

    public static class Default {
        public static final String DEFAULT_USER_AUTHORITY = "ROLE_USER";
        public static final String DEFAULT_DEVELOPER_AUTHORITY = "ROLE_DEVELOPER";
        public static final String DEFAULT_ADMIN_AUTHORITY = "ROLE_ADMIN";
    }
}
