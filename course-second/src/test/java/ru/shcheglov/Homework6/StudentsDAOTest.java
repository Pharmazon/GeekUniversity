/*
 *  Java Core 2.
 *  Homework 6. TestNG framework
 *
 *  @author Alexey Shcheglov
 *  @link https://github.com/Pharmazon
 *  @version dated Mar 10, 2018
 */

package ru.shcheglov.Homework6;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import ru.shcheglov.Homework6.dao.StudentsDAO;

public class StudentsDAOTest extends Assert {

    private StudentsDAO dao = new StudentsDAO();
    private int id;
    private String testName = "Ivan Petrovich";
    private double testScore = 12345.88;
    private double testNewScore = 54321.44;

    @Test(expectedExceptions = NullPointerException.class)
    public void testIsStudentAddedDisconnected() {
        id = dao.addEntry(testName, testScore);
    }

    @Test(dependsOnMethods = {"testIsStudentAddedDisconnected"})
    public void connect() {
        assertTrue(dao.connect());
    }

    @Test(dependsOnMethods = {"connect"})
    public void testIsStudentAdded() {
        id = dao.addEntry(testName, testScore);
        assertNotNull(id);
    }

    @Test(dependsOnMethods = {"testIsStudentAdded"})
    public void testAddedStudentName() {
        String expectedName = dao.getNameById(id);
        assertEquals(testName, expectedName);
    }

    @Test(dependsOnMethods = {"testIsStudentAdded"})
    public void testAddedStudentScore() {
        double expectedScore = dao.getScoreById(id);
        assertEquals(testScore, expectedScore);
    }

    @Test(dependsOnMethods = {"testIsStudentAdded"})
    public void testScoreModified() {
        dao.changeScoreById(id, testNewScore);
        double expectedScore = dao.getScoreById(id);
        assertEquals(testNewScore, expectedScore);
    }

    @AfterClass
    public void deleteStudentAndDisconnect() {
        dao.deleteEntry(id);
        dao.disconnect();
    }
}
