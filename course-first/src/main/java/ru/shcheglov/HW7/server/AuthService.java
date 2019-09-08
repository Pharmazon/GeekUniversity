package ru.shcheglov.HW7.server;

import java.sql.*;

public class AuthService {

    private Connection connection;
    private Statement stmt;

    public void connect() throws SQLException, ClassNotFoundException{
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:main.db");
        stmt = connection.createStatement();
    }

    public String getNickByLoginAndPass(String login, String password) {
        try {
            ResultSet rs = stmt.executeQuery("SELECT nickname FROM users WHERE login = '" +
                    login + "' AND password = '" + password + "';");
            if (rs.next()){
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
            connection.close();
        } catch (SQLException e) {
            System.out.println("Couldn't close the connection with database 'main.db'");
        }
    }
}
