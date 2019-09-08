/*
 *  Java Core 2.
 *  Homework 6. TestNG framework
 *
 *  @author Alexey Shcheglov
 *  @link https://github.com/Pharmazon
 *  @version dated Mar 10, 2018
 */

package ru.shcheglov.Homework6.query;

public class SqlQueries {

    public static final String SQL_ADD_ENTRY =
            "INSERT INTO university.students (name, score) VALUES (?, ?) RETURNING id;";

    public static final String SQL_CLEAR = "TRUNCATE university.students;";

    public static final String SQL_SELECT = "SELECT * FROM university.students WHERE id=?;";

    public static final String SQL_MODIFY = "UPDATE university.students SET score=? WHERE id=?;";

    public static final String SQL_ID = "SELECT id FROM university.students WHERE name=?;";

    public static final String SQL_DELETE = "DELETE FROM university.students WHERE id=?;";
}
