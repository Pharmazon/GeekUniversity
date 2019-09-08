package ru.shcheglov.HW8.server;

import java.sql.*;

public class AuthService {

    private Connection connection;
    private Statement stmt;
    private PreparedStatement psNick;
    private PreparedStatement psUserRegister;

    public void connect() throws SQLException, ClassNotFoundException{
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:main.db");
        stmt = connection.createStatement();
//        checkTable();
        psNick = connection.prepareStatement("SELECT nickname FROM users WHERE login = ? AND password = ?;");
        psUserRegister = connection.prepareStatement("INSERT INTO users (login, password, nickname) VALUES (?,?,?);");
//        userRegistration("login1", "pass1", "Avatar");
//        userRegistration("login2", "pass2", "Ace");
//        userRegistration("login3", "pass3", "Wheel");
//        userRegistration("login4", "pass4", "Queen");
//        userRegistration("login5", "pass5", "Star");
    }

    public void checkTable() throws SQLException {
        stmt.execute("CREATE TABLE IF NOT EXISTS users (\n" +
                        "    id       INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                        "    login    TEXT    UNIQUE,\n" +
                        "    password INTEGER,\n" +
                        "    nickname TEXT    UNIQUE\n" +
                        ");");
    }

    public boolean userRegistration(String login, String password, String nickname) throws SQLException{
        try {
            int passHash = password.hashCode();
            psUserRegister.setString(1, login);
            psUserRegister.setInt(2, passHash);
            psUserRegister.setString(3, nickname);
            return psUserRegister.executeUpdate() == 1;
        } catch (SQLException e){
            throw new SQLException("User registration error");
        }
    }

    public String getNickByLoginAndPass(String login, String password) {
        try {
            psNick.setString(1, login);
            int passHash = password.hashCode();
            psNick.setInt(2, passHash);
            //stmt.executeQuery("SELECT nickname FROM users WHERE
            //      login = '" + login + "' AND password = '" + password + "';");
            ResultSet rs = psNick.executeQuery();
            if (rs.next()) {
                return rs.getString("nickname");
            }
        } catch (SQLException e) {
            System.out.println("Couldn't execute the query to select 'nickname' from database 'main.db'");
        }
        return null;
    }

    public void disconnect(){
        try {
            stmt.close();
            psNick.close();
            psUserRegister.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Couldn't close the connection with database 'main.db'");
        }
    }
}
