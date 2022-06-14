package net.eternalcode.eternalparkour.util;

import java.nio.charset.StandardCharsets;

public final class StringUtils {

    public static String format(String context){
        byte[] bytes = context.getBytes(StandardCharsets.UTF_8);

        return new String(bytes, StandardCharsets.UTF_8);
    }
}
