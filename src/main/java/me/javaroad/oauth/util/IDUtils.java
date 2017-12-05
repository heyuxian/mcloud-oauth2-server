package me.javaroad.oauth.util;

import java.util.UUID;

/**
 * @author heyx
 */
public class IDUtils {

    public static String uuid() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
