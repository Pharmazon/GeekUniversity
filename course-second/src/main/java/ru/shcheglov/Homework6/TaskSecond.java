/*
 *  Java Core 2.
 *  Homework 6. TestNG framework
 *
 *  @author Alexey Shcheglov
 *  @link https://github.com/Pharmazon
 *  @version dated Mar 10, 2018
 */

package ru.shcheglov.Homework6;

public class TaskSecond {

    public static boolean isArrayContainsOnlyNumbers(final int[] array, final int num1, final int num2) {
        if (array.length == 0) return false;
        int countNum1 = 0;
        int countNum2 = 0;
        for (int element : array) {
            if (element == num1) countNum1++;
            if (element == num2) countNum2++;
        }
        return ((countNum1 + countNum2) == array.length);
    }
}
