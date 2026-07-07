package com.lztech.site.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Access {
    public static String signKey;

    @Value("${signKey}")
    public void setSignKey(String signKey) {
        Access.signKey = signKey;
    }
}
