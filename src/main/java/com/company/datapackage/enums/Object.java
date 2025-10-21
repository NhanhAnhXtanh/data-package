package com.company.datapackage.enums;

import io.jmix.core.metamodel.datatype.EnumClass;

import org.springframework.lang.Nullable;


public enum Object implements EnumClass<String> {

    UNIT("Đơn vị"),
    ROLE("Vai trò"),
    SPECIFIC_USER("Người dùng cụ thể");

    private final String id;

    Object(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static Object fromId(String id) {
        for (Object at : Object.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}