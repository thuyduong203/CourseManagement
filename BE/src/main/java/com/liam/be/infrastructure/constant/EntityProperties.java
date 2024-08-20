package com.liam.be.infrastructure.constant;

public class EntityProperties {
    public static final byte LENGTH_ID = 36;
    public static final short LENGTH_NAME = 100;
    public static final byte LENGTH_CODE = 30;
    public static final short LENGTH_ADDRESS = 255;
    public static final byte LENGTH_PASSWORD = 100;
    public static final byte LENGTH_EMAIL = 50;
    public static final byte LENGTH_PHONE = 20;
    public static final short LENGTH_DESCRIPTION = 1000;
    public static final String DEFINITION_DESCRIPTION = "VARCHAR(" + EntityProperties.LENGTH_DESCRIPTION + ") CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci";
    public static final String DEFINITION_NAME = "VARCHAR(" + EntityProperties.LENGTH_NAME + ") CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci";
    public static final String DEFINITION_ADDRESS = "VARCHAR(" + EntityProperties.LENGTH_ADDRESS + ") CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci";

}
