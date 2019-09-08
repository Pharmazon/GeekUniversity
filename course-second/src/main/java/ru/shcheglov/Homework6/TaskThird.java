/*
 *  Java Core 2.
 *  Homework 6. TestNG framework
 *
 *  @author Alexey Shcheglov
 *  @link https://github.com/Pharmazon
 *  @version dated Mar 10, 2018
 */

package ru.shcheglov.Homework6;

import ru.shcheglov.Homework6.dao.StudentsDAO;

import java.util.Arrays;

public class TaskThird {

    public static void main(String[] args) {
        final StudentsDAO dao = new StudentsDAO();
        dao.connect();
        int[] id = dao.addExampleEntries(10000);
        System.out.println(Arrays.toString(id));
        dao.disconnect();
    }
}
