/*******************************************************************************
 * Java Core 2.
 * Homework 2.
 * Database mySQL operations testing.
 * Class: SqlQueries
 *
 * @author Alexey Shcheglov
 * @link https://github.com/Pharmazon
 * @version dated Feb 25, 2018
 ******************************************************************************/

package ru.shcheglov.Homework2.utils;

import ru.shcheglov.Homework2.conf.Settings;
import ru.shcheglov.Homework2.tables.Goods;

public class SqlQueries {

    public static final String SQL_RANGE_GOODS = "SELECT * FROM " +
            Settings.DB_NAME + "." +
            Settings.DB_TABLE_GOODS_NAME +
            " WHERE COST >= ? AND COST <= ?;";

    public static final String SQL_SELECT_BY_TITLE = "SELECT * FROM `" +
            Settings.DB_NAME + "`.`" +
            Settings.DB_TABLE_GOODS_NAME +
            "` WHERE TITLE=? ;";

    public static final String SQL_ADD_ENTRY = "INSERT INTO `" +
            Settings.DB_NAME + "`.`" +
            Settings.DB_TABLE_GOODS_NAME + "` " +
            "(`PRODID`, `TITLE`, `COST`) VALUES (?, ?, ?);";

    public static final String SQL_DELETE_ENTRY = "DELETE FROM " +
            Settings.DB_NAME + "." +
            Settings.DB_TABLE_GOODS_NAME + " WHERE `ID`=?;";

    public static final String SQL_CHANGE_COST = "UPDATE `" +
            Settings.DB_NAME + "`.`" +
            Settings.DB_TABLE_GOODS_NAME + "` " +
            "SET `COST`=? WHERE `ID`=?;";

    public static final String SQL_CLEAR_DB = "TRUNCATE `" +
            Settings.DB_NAME + "`.`" +
            Settings.DB_TABLE_GOODS_NAME + "`;";

    public static final String SQL_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS `" +
            Settings.DB_NAME + "`.`" +
            Settings.DB_TABLE_GOODS_NAME + "` (" +
            "`" + Goods.ID + "` INT NOT NULL AUTO_INCREMENT," +
            "`" + Goods.PROD_ID + "` INT NOT NULL," +
            "`" + Goods.TITLE + "` VARCHAR(128) NOT NULL," +
            "`" + Goods.COST + "` DECIMAL(10,2) NOT NULL," +
            "PRIMARY KEY (`" + Goods.ID + "`)," +
            "UNIQUE INDEX `" + Goods.ID + "_UNIQUE` (`" + Goods.ID + "` ASC)," +
            "UNIQUE INDEX `" + Goods.PROD_ID + "_UNIQUE` (`"+ Goods.PROD_ID +"` ASC));";
}