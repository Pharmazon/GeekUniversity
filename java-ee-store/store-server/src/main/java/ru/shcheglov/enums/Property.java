package ru.shcheglov.enums;

/**
 * @author Alexey Shcheglov
 * @version dated 09.02.2019
 */

public enum Property {

    SERVER_HOST("server.host"),
    SERVER_PORT("server.port"),
    SESSION_CYCLE("session.cycle"),
    SESSION_SALT("session.salt");

    private String name;

    Property(final String name) {
        this.name = name;
    }
}
