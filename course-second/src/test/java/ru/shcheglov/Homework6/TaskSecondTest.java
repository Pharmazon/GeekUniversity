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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TaskSecondTest extends Assert {

    private int num1 = 1;
    private int num2 = 4;

    @DataProvider(name = "basic")
    public Object[][] createTestData() {
        return new Object[][] {
                {new int[]{1, 4, 1, 1, 4}, true},
                {new int[]{}, false},
                {new int[]{4, 4, 4, 4, 4}, true},
                {new int[]{1, 2, 1, 1, 2}, false},
                {new int[]{0, 4, 1, 1, 4}, false},
                {new int[]{1, 4, 1}, true},
                {new int[]{-1, -5, 4}, false},
                {new int[]{0, 0, 0}, false},
                {new int[]{-1, -100, -10}, false}
        };
    }

    @Test(dataProvider = "basic")
    public void testData(final int[] target, final boolean expected) {
        assertEquals(TaskSecond.isArrayContainsOnlyNumbers(target, num1, num2), expected);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testException() {
        int[] target = null;
        TaskSecond.isArrayContainsOnlyNumbers(target, num1, num2);
    }
}
