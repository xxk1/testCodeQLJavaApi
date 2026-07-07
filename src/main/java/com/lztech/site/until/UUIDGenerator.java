package com.lztech.site.until;

import java.util.UUID;

public class UUIDGenerator {
    public static String generateUUIDWithoutHyphens() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
