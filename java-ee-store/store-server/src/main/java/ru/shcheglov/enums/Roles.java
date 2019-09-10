package ru.shcheglov.enums;

/**
 * @author Alexey Shcheglov
 * @version dated 09.02.2019
 */

public enum Roles {

    USER("User"),
    ADMIN("Login");

    private String name;

    Roles(final String name) {
        this.name = name;
    }
}
