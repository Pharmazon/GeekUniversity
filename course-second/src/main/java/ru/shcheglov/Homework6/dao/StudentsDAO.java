/*
 *  Java Core 2.
 *  Homework 6. TestNG framework
 *
 *  @author Alexey Shcheglov
 *  @link https://github.com/Pharmazon
 *  @version dated Mar 10, 2018
 */

package ru.shcheglov.Homework6.dao;

import ru.shcheglov.Homework6.conf.Settings;
import ru.shcheglov.Homework6.conf.URL;
import ru.shcheglov.Homework6.query.SqlQueries;

import java.sql.*;

public class StudentsDAO {

    private Connection connection;

    private Statement stmt;

    public boolean connect() {
        try {
            Class.forName(Settings.DRIVER_PATH);
            connection = DriverManager.getConnection(URL.DB_URL, Settings.DB_LOGIN, Settings.DB_PASSWORD);
            stmt = connection.createStatement();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void disconnect() {
        try {
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int addEntry(final String name, final double score) {
        if (score <= 0 || name.equals(" ")) return 0;
        final PreparedStatement prepAddEntry;
        try {
            prepAddEntry = connection.prepareStatement(SqlQueries.SQL_ADD_ENTRY);
            prepAddEntry.setString(1, name);
            prepAddEntry.setDouble(2, score);
            ResultSet rs = prepAddEntry.executeQuery();
            while (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int[] addExampleEntries(final int count) {
        if (count <= 0) return null;
        int[] array = new int[count];
        for (int i = 1; i <= count; i++) {
            double score = 25 + Math.random() * Math.random() * 100;
            array[i] = addEntry("StudentName" + i, Math.ceil(score));
        }
        return array;
    }

    public void clearTable() {
        try {
            stmt.execute(SqlQueries.SQL_CLEAR);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public double getScoreById(final int id) {
        if (id <= 0) return 0.0;
        final PreparedStatement getScoreById;
        try {
            getScoreById = connection.prepareStatement(SqlQueries.SQL_SELECT);
            getScoreById.setInt(1, id);
            final ResultSet rs = getScoreById.executeQuery();
            while (rs.next()) {
                return rs.getDouble("score");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public String getNameById(final int id) {
        if (id <= 0) return " ";
        final PreparedStatement getNameById;
        try {
            getNameById = connection.prepareStatement(SqlQueries.SQL_SELECT);
            getNameById.setInt(1, id);
            final ResultSet rs = getNameById.executeQuery();
            while (rs.next()) {
                return rs.getString("name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return " ";
    }

    public int getIdByName(final String name) {
        if (name.equals(" ")) return 0;
        final PreparedStatement getIdByName;
        try {
            getIdByName = connection.prepareStatement(SqlQueries.SQL_ID);
            getIdByName.setString(1, name);
            final ResultSet rs = getIdByName.executeQuery();
            while (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void changeScoreById(final int id, final double newScore) {
        if (id <= 0) return;
        final PreparedStatement changeScoreById;
        try {
            changeScoreById = connection.prepareStatement(SqlQueries.SQL_MODIFY);
            changeScoreById.setDouble(1, newScore);
            changeScoreById.setInt(2, id);
            changeScoreById.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean deleteEntry(int id) {
        if (id <= 0) return false;
        final PreparedStatement deleteEntry;
        try {
            deleteEntry = connection.prepareStatement(SqlQueries.SQL_DELETE);
            deleteEntry.setInt(1, id);
            return deleteEntry.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
