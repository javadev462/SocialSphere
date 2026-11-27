package com.socialsphere.auth.enums;

/**Enums provide type safety by restricting a variable to a predefined set of constants.
In our application, we use an enum for user roles such as USER and ADMIN to avoid
invalid role values. With JPA's EnumType.STRING, we persist meaningful role names
instead of ordinal numbers**/

public enum Role {
    USER,
    ADMIN
}
