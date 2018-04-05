package me.javaroad.mcloud.oauth.util;

import java.util.UUID;

/**
 * @author heyx
 */
public class IdUtils {

    public static String uuid() {
        return UUID.randomUUID().toString();
    }
}
