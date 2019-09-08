/*
 * Java Core 2.
 * Homework 7. Own TestSAS Framework
 *
 * @author Alexey Shcheglov
 * @link https://github.com/Pharmazon
 * @version dated Mar 15, 2018
 */

package ru.shcheglov.Homework7.util;

import java.util.Arrays;

public class Reconcile {

    private static String className;

    private static String methodName;

    private static int countPassed = 0;

    private static int countFailed = 0;

    private static String passedMsg;

    private static String failedMsg;

    private static void printPassedMsg() {
        TestSASFrame.getLOGGER().info(passedMsg);
        countPassed++;
    }

    private static void printFailedMsg() {
        TestSASFrame.getLOGGER().info(failedMsg);
        countFailed++;
    }

    public static int getCountPassed() {
        return countPassed;
    }

    public static int getCountFailed() {
        return countFailed;
    }

    public static void setClassMethod(String className, String methodName) {
        Reconcile.className = className;
        Reconcile.methodName = methodName;
        passedMsg = className + " : " +  methodName + " : TEST PASSED!";
        failedMsg = className + " : " +  methodName + " : TEST FAILED!";
    }

    public static void isEquals(final int actual, final int expected) {
        if (actual == expected)
            printPassedMsg();
        if (actual != expected)
            printFailedMsg();
    }

    public static void isEquals(final int[] actual, final int[] expected) {
        if (Arrays.equals(actual, expected))
            printPassedMsg();
        if (!Arrays.equals(actual, expected))
            printFailedMsg();
    }

    public static void isNotEquals(final int[] actual, final int[] expected) {
        if (Arrays.equals(actual, expected))
            printFailedMsg();
        if (!Arrays.equals(actual, expected))
            printPassedMsg();
    }

    public static void isEquals(final String actual, final String expected) {
        if (actual.equals(expected))
            printPassedMsg();
        if (!actual.equals(expected))
            printFailedMsg();
    }

    public static void isTrue(final boolean expected) {
        if (expected)
            printPassedMsg();
        if (!expected)
            printFailedMsg();
    }

    public static void isFalse(final boolean expected) {
        if (expected)
            printFailedMsg();
        if (!expected)
            printPassedMsg();
    }

    public static void isNull(final Object expected) {
        if (expected == null)
            printPassedMsg();
        if (expected != null)
            printFailedMsg();
    }

    public static void isNotNull(final Object expected) {
        if (expected == null)
            printFailedMsg();
        if (expected != null)
            printPassedMsg();
    }

    public static void isSame(final Object actual, final Object expected) {
        if (actual.equals(expected))
            printPassedMsg();
        if (!actual.equals(expected))
            printFailedMsg();
    }

    public static void isNotSame(final Object actual, final Object expected) {
        if (actual.equals(expected))
            printFailedMsg();
        if (!actual.equals(expected))
            printPassedMsg();
    }
}
