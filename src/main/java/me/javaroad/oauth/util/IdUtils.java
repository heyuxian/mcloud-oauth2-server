package me.javaroad.oauth.util;

import java.util.UUID;

/**
 * @author heyx
 */
public class IdUtils {

    public static String uuid() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
