/*
 *  Java Core 2.
 *  Homework 6. TestNG framework
 *
 *  @author Alexey Shcheglov
 *  @link https://github.com/Pharmazon
 *  @version dated Mar 10, 2018
 */

package ru.shcheglov.Homework6;

import java.util.Arrays;

public class TaskFirst {

    public static int[] subArray(final int[] array, final int num) {
        if (array.length == 0) return null;
        boolean isExist = false;
        for (int element : array) {
            if (element == num) isExist = true;
        }
        if (isExist) {
            for (int i = array.length - 1; i >= 0; i--) {
                if (array[i] == num) {
                    return Arrays.copyOfRange(array, i + 1, array.length);
                }
            }
        } else {
            throw new RuntimeException("Value of '" + num + "' wasn't found in array!");
        }
        return null;
    }
}
