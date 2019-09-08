/*
 * Java Core 2.
 * Homework 7. Own TestSAS Framework
 *
 * @author Alexey Shcheglov
 * @link https://github.com/Pharmazon
 * @version dated Mar 15, 2018
 */

package ru.shcheglov.Homework7.exception;

public class MyRuntimeException extends Exception {

    public MyRuntimeException(String description) {
        super("Class contains more than one method annotated with " +
                "@" + description + ". Expected only one.");
    }
}
