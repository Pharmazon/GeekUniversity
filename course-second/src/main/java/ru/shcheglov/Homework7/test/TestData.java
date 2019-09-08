package ru.shcheglov.Homework7.test;

import ru.shcheglov.Homework7.annotation.AfterSuite;
import ru.shcheglov.Homework7.annotation.BeforeSuite;
import ru.shcheglov.Homework7.annotation.Test;
import ru.shcheglov.Homework7.util.Reconcile;

public class TestData {

    private int num = 4;

    @AfterSuite
    public void postTestMethod() {
        System.out.println("All the tests completed!");
    }

    @BeforeSuite
    public static void preTestMethod() {
        System.out.println("Welcome to SAS Test Framework!");
    }

    @Test(priority = 3)
    public void testPositiveForNullFirst() {
        final int[] target = new int[]{};
        Reconcile.isNull(OwnArray.subArray(target, num));
    }

    @Test(priority = 4)
    public void testPositiveForEqualsFirst() {
        final int[] target = new int[]{1, 2, 4, 4, 2, 3, 4, 1, 7, 8, 9, 10};
        final int[] expected = new int[]{1, 7, 8, 9, 10};
        Reconcile.isEquals(OwnArray.subArray(target, num), expected);
    }

    @Test(priority = 0)
    public void testNegativeForEquals() {
        final int[] target = new int[]{4, 0, -1, -4, 1, 5, 6};
        final int[] expected = new int[]{0, -1, -4, 1, 5, 6};
        Reconcile.isNotEquals(OwnArray.subArray(target, num), expected);
    }

    @Test(priority = 1)
    public void testPositiveForEqualsSecond() {
        final int[] target = new int[]{4, 0, -1, -4, 1, 5, 4};
        final int[] expected = new int[]{};
        Reconcile.isEquals(OwnArray.subArray(target, num), expected);
    }

    @Test(priority = 2)
    public void testPositiveForNullSecond() {
        final int[] target = new int[]{4, 0, -1, -4, 1, 5, 4};
        Reconcile.isNull(OwnArray.subArray(target, num));
    }

    @Test(priority = 1)
    public void testPositiveForEqualsThird() {
        final int[] target = new int[]{4, 0, -1, -4, 1, 5, 4};
        final int[] expected = new int[]{};
        Reconcile.isEquals(OwnArray.subArray(target, num), expected);
    }
}
