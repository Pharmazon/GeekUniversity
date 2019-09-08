/*******************************************************************************
 * Java Core 2.
 * Homework 2. Java+mySQL CRUDe.
 * Class: GoodsDAO
 *
 * @author Alexey Shcheglov
 * @link https://github.com/Pharmazon
 * @version dated Feb 25, 2018
 ******************************************************************************/

package ru.shcheglov.Homework2.dao;

import ru.shcheglov.Homework2.conf.Settings;
import ru.shcheglov.Homework2.conf.URL;
import ru.shcheglov.Homework2.tables.Goods;
import ru.shcheglov.Homework2.utils.SqlQueries;

import java.sql.*;

public class GoodsDAO {

    private Connection connection;

    private Statement stmt;

    public GoodsDAO() {
        this.connect();
        this.checkTable();
    }

    private void connect() {
        try {
            Class.forName(Settings.DRIVER_PATH);
            connection = DriverManager.getConnection(URL.DB_URL, Settings.DB_LOGIN, Settings.DB_PASSWORD);
            stmt = connection.createStatement();
            connection.setAutoCommit(false);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            connection.commit();
            connection.setAutoCommit(true);
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addEntry(int prodid, String title, float cost) {
        if (prodid <= 0 || title.equals(" ") || cost <= 0) return;
        PreparedStatement prepAddEntry;
        try {
            prepAddEntry = connection.prepareStatement(SqlQueries.SQL_ADD_ENTRY);
            prepAddEntry.setInt(1, prodid);
            prepAddEntry.setString(2, title);
            prepAddEntry.setFloat(3, cost);
            prepAddEntry.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void checkTable() {
        try {
            stmt.execute(SqlQueries.SQL_CREATE_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clearTable() {
        try {
            stmt.execute(SqlQueries.SQL_CLEAR_DB);
            System.out.println("Все записи из таблицы '" + Settings.DB_TABLE_GOODS_NAME + "' были удалены.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public float getCostByTitle(String title) {
        if (title.equals(" ")) return 0.0f;
        PreparedStatement prepCostByTitle;
        try {
            prepCostByTitle = connection.prepareStatement(SqlQueries.SQL_SELECT_BY_TITLE);
            prepCostByTitle.setString(1, title);
            ResultSet rs = prepCostByTitle.executeQuery();
            while (rs.next()) {
                return rs.getFloat(Goods.COST);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0f;
    }

    public void changeCost(String title, float newcost) {
        if (title.equals(" ") || newcost <= 0) return;
        PreparedStatement prepChangeCost;
        try {
            prepChangeCost = connection.prepareStatement(SqlQueries.SQL_CHANGE_COST);
            prepChangeCost.setFloat(1, newcost);
            prepChangeCost.setInt(2, this.getIdByTitle(title));
            prepChangeCost.execute();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getRangeOfGoodsByCosts(float from, float to) {
        if (from < 0 || to <= 0 || to < from) return;
        int i = 0;
        PreparedStatement prepRangeGoods;
        try {
            prepRangeGoods = connection.prepareStatement(SqlQueries.SQL_RANGE_GOODS);
            prepRangeGoods.setFloat(1, from);
            prepRangeGoods.setFloat(2, to);
            ResultSet rs = prepRangeGoods.executeQuery();
            while (rs.next()) {
                i++;
                this.printResultSet(rs);
            }
            System.out.println("Всего найдено " + i + " записей в БД.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getGoodsByTitle(String title) {
        int i = 0;
        PreparedStatement prepGetGood;
        try {
            prepGetGood = connection.prepareStatement(SqlQueries.SQL_SELECT_BY_TITLE);
            prepGetGood.setString(1, title);
            ResultSet rs = prepGetGood.executeQuery();
            while (rs.next()) {
                i++;
                this.printResultSet(rs);
            }
            System.out.println("Всего найдено " + i + " записей в БД.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteEntry(String title) {
        if (title.equals(" ")) return;
        PreparedStatement prepDeleteEntry;
        try {
            prepDeleteEntry = connection.prepareStatement(SqlQueries.SQL_DELETE_ENTRY);
            prepDeleteEntry.setInt(1, this.getIdByTitle(title));
            prepDeleteEntry.execute();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Запись '" + title + "' успешно удалена.");
    }

    private int getIdByTitle(String title) {
        if (title.equals(" ")) return 0;
        PreparedStatement prepGetId;
        try {
            prepGetId = connection.prepareStatement(SqlQueries.SQL_SELECT_BY_TITLE);
            prepGetId.setString(1, title);
            ResultSet rs = prepGetId.executeQuery();
            while (rs.next()) {
                return rs.getInt(Goods.ID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private void printResultSet(ResultSet rs) {
        try {
            System.out.println("[ID=" + rs.getInt("ID") + "; " +
                    "PRODID=" + rs.getInt("PRODID") + "; " +
                    "TITLE=" + rs.getString("TITLE") + "; " +
                    "COST=" + rs.getFloat("COST") + "]");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
