package ru.cloud.storage.server.config;

public final class SQLQuery {
    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS cloud.users (" +
            "id serial NOT NULL, " +
            "login character varying(50) NOT NULL, " +
            "password text NOT NULL, " +
            "name character varying(50) NOT NULL, " +
            "active boolean NOT NULL, " +
            "CONSTRAINT users_pkey PRIMARY KEY (id))";

    public static final String CREATE_SCHEMA = "CREATE SCHEMA IF NOT EXISTS cloud";

    public static final String USER_REGISTER = "INSERT INTO cloud.users (login, password, name, active) " +
            "VALUES (?, ?, ?, true)";

    public static final String UPDATE_ACTIVE = "UPDATE cloud.users SET active = ? WHERE login = ?";

    public static final String SELECT_USER_LOGIN = "SELECT * FROM cloud.users WHERE login = ?";

    public static final String SELECT_USER_LOGIN_PASS = "SELECT * FROM cloud.users WHERE " +
            "login = ? AND password = ?";

    public static final String UPDATE_PASSWORD = "UPDATE cloud.users SET password = ? WHERE login = ?";
}
