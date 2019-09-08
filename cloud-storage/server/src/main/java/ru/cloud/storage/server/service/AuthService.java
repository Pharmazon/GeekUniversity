package ru.cloud.storage.server.service;

import com.lambdaworks.crypto.SCryptUtil;
import ru.cloud.storage.server.config.SQLQuery;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.*;
import java.util.Properties;

public class AuthService {
    private Connection connection;
    private Statement statement;

    private PreparedStatement psLogin;
    private PreparedStatement psLoginPass;
    private PreparedStatement psUserRegister;
    private PreparedStatement psUpdateAccount;
    private PreparedStatement psUpdatePassword;

    private String propDbLogin;
    private String propDbPassword;
    private String dbUrl;

    private void readPropertiesFromFile() {
        try (Reader in = new InputStreamReader(this.getClass().getResourceAsStream("/server.properties"))) {
            Properties pr = new Properties();
            pr.load(in);
            propDbLogin = pr.getProperty("db_login");
            propDbPassword = pr.getProperty("db_password");
            String propDbName = pr.getProperty("db_name");
            String propDbHost = pr.getProperty("db_host");
            int propDbPort = Integer.parseInt(pr.getProperty("db_port"));
            dbUrl = "jdbc:postgresql://" + propDbHost + ":" + propDbPort + "/" + propDbName;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void connect() {
        try {
            readPropertiesFromFile();
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(dbUrl, propDbLogin, propDbPassword);
            this.statement = connection.createStatement();
            checkSchema();
            checkTable();
            this.psLogin = connection.prepareStatement(SQLQuery.SELECT_USER_LOGIN);
            this.psLoginPass = connection.prepareStatement(SQLQuery.SELECT_USER_LOGIN_PASS);
            this.psUserRegister = connection.prepareStatement(SQLQuery.USER_REGISTER);
            this.psUpdateAccount = connection.prepareStatement(SQLQuery.UPDATE_ACTIVE);
            this.psUpdatePassword = connection.prepareStatement(SQLQuery.UPDATE_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isConnected() {
        try {
            if (!connection.isClosed()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void checkConnection() {
        if (!this.isConnected()) {
            this.connect();
        }
    }

    public void disconnect() {
        try {
            statement.close();
            psLogin.close();
            psLoginPass.close();
            psUserRegister.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void addTestAccountsToDb() {
        for (int i = 1; i <= 100; i++) {
            this.userRegister("login" + i + "@example.com", "Login" + i, "Vasiliy Ivanovich" + i);
        }
        this.userRegister("test@test.com", "test", "Test");
        this.deactivateAccount("login99@example.com");
        this.deactivateAccount("login97@example.com");
    }

    public boolean isLoginBusy(String login) {
        if (login.isEmpty()) return false;
        login = login.trim();
        try {
            psLogin.setString(1, login);
            ResultSet rs = psLogin.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isAccountActive(String login) {
        if (login.isEmpty()) return false;
        login = login.trim();
        try {
            psLogin.setString(1, login);
            ResultSet rs = psLogin.executeQuery();
            while (rs.next()) {
                return rs.getBoolean("active");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean activateAccount(String login) {
        if (login.isEmpty()) return false;
        login = login.trim();
        if (isAccountActive(login)) return false;
        try {
            psUpdateAccount.setBoolean(1, true);
            psUpdateAccount.setString(2, login);
            psUpdateAccount.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deactivateAccount(String login) {
        if (login.isEmpty()) return false;
        login = login.trim();
        if (!isAccountActive(login)) return false;
        try {
            psUpdateAccount.setBoolean(1, false);
            psUpdateAccount.setString(2, login);
            psUpdateAccount.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void checkTable() {
        try {
            statement.execute(SQLQuery.CREATE_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void checkSchema() {
        try {
            statement.execute(SQLQuery.CREATE_SCHEMA);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean userRegister(String login, String password, String name) {
        if (!isLoginValid(login)) return false;
        if (login.isEmpty() || password.isEmpty() || name.isEmpty()) return false;
        login = login.toLowerCase().trim();
        password = password.trim();
        name = name.trim();
        String hash = SCryptUtil.scrypt(password, 16384, 8, 1);
        try {
            psUserRegister.setString(1, login);
            psUserRegister.setString(2, hash);
            psUserRegister.setString(3, name);
            psUserRegister.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String authOK(String login, String password) {
        if (login.isEmpty() || password.isEmpty()) return null;
        login = login.trim();
        password = password.trim();
        try {
            psLogin.setString(1, login);
            ResultSet rs = psLogin.executeQuery();
            while (rs.next()) {
                String hash = rs.getString("password");
                boolean active = rs.getBoolean("active");
                String name = rs.getString("name");
                if (SCryptUtil.check(password, hash) && active)
                    return name;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean changePassword(String login, String oldPassword, String newPassword) {
        if (login.isEmpty() || oldPassword.isEmpty() || newPassword.isEmpty()) return false;
        login = login.trim();
        oldPassword = oldPassword.trim();
        newPassword = newPassword.trim();
        if (authOK(login, oldPassword) == null) return false;

        String hash = SCryptUtil.scrypt(newPassword, 16384, 8, 1);
        try {
            psUpdatePassword.setString(1, hash);
            psUpdatePassword.setString(2, login);
            psUpdatePassword.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isLoginValid(String login) {
        if (login == null) return false;
        if (login.isEmpty()) return false;
        if (!login.contains("@")) return false;
        String[] line = login.split("@");
        if (line.length > 2 || line.length <= 1) return false;

        String sub = login.substring(login.indexOf("@") + 1, login.length());
        if (sub.isEmpty()) return false;
        if (!sub.contains(".")) return false;
        String[] line2 = sub.split("\\.");
        if (line2.length > 2 || line2.length <= 1) return false;
        if (line2[0].equals("")) return false;

        return true;
    }
}
