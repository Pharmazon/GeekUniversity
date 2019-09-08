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
import org.testng.annotations.Test;

public class TaskFirstTest extends Assert {

    private int num = 4;

    @Test(expectedExceptions = RuntimeException.class)
    public void test1() {
        final int[] target = new int[]{-1, -2, 7, 15, 67, -34, -2, 6, 7, 8, 9, 56};
        TaskFirst.subArray(target, num);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void test2() {
        final int[] target = null;
        TaskFirst.subArray(target, num);
    }

    @Test
    public void test3() {
        final int[] target = new int[]{};
        assertNull(TaskFirst.subArray(target, num));
    }

    @Test
    public void test4() {
        final int[] target = new int[]{1, 2, 4, 4, 2, 3, 4, 1, 7, 8, 9, 10};
        final int[] expected = new int[]{1, 7, 8, 9, 10};
        assertEquals(TaskFirst.subArray(target, num), expected);
    }

    @Test
    public void test5() {
        final int[] target = new int[]{4, 0, -1, -4, 1, 5, 6};
        final int[] expected = new int[]{0, -1, -4, 1, 5, 6};
        assertEquals(TaskFirst.subArray(target, num), expected);
    }

    @Test
    public void test6() {
        final int[] target = new int[]{4, 0, -1, -4, 1, 5, 4};
        final int[] expected = new int[]{};
        assertEquals(TaskFirst.subArray(target, num), expected);
    }

    @Test
    public void test7() {
        final int[] target = new int[]{4, 0, -1, -4, 1, 5, 4};
        assertNotNull(TaskFirst.subArray(target, num));
    }
}
