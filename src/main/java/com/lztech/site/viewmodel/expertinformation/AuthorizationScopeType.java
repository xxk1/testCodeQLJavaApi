package com.lztech.site.viewmodel.expertinformation;

import java.util.Objects;

/**
 * 授权范围类型
 */
public enum AuthorizationScopeType {

    All_COURSES(0, "全部"),
    LIVE_COURSES(1, "直播课程"),
    ON_DEMAND_COURSES(2, "点播课程");
    private final Integer value;

    private final String name;

    AuthorizationScopeType(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public static AuthorizationScopeType getAuthorizationScopeType(Integer value) {
        if (Objects.isNull(value)) {
            return null;
        }
        for (AuthorizationScopeType authorizationScopeType : AuthorizationScopeType.values()) {
            if (value == authorizationScopeType.getValue()) {
                return authorizationScopeType;
            }
        }
        return null;
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
