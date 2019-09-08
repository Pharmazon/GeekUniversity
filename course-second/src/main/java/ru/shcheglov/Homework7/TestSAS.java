/*
 * Java Core 2.
 * Homework 7. Own TestSAS Framework
 *
 * @author Alexey Shcheglov
 * @link https://github.com/Pharmazon
 * @version dated Mar 15, 2018
 */

package ru.shcheglov.Homework7;

import ru.shcheglov.Homework7.test.TestData;
import ru.shcheglov.Homework7.util.TestSASFrame;

public class TestSAS {

    private static final Class CLASS_NAME = TestData.class;

    public static void main(String[] args) {
        TestSASFrame.start(CLASS_NAME);
    }

    public static Class getClassName() {
        return CLASS_NAME;
    }
}
