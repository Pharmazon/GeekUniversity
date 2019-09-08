/*
 * Java Core 2.
 * Homework 7. Own TestSAS Framework
 *
 * @author Alexey Shcheglov
 * @link https://github.com/Pharmazon
 * @version dated Mar 15, 2018
 */

package ru.shcheglov.Homework7.util;

import ru.shcheglov.Homework7.TestSAS;
import ru.shcheglov.Homework7.annotation.AfterSuite;
import ru.shcheglov.Homework7.annotation.BeforeSuite;
import ru.shcheglov.Homework7.annotation.Test;
import ru.shcheglov.Homework7.exception.MyRuntimeException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestSASFrame {

    private static final Logger LOGGER = Logger.getLogger(TestSAS.class.getName());

    private static final Method[] METHODS = TestSAS.getClassName().getDeclaredMethods();

    private static final Class<? extends Test> test = Test.class;

    private static final Class<? extends Annotation> beforeSuite = BeforeSuite.class;

    private static final Class<? extends Annotation> afterSuite = AfterSuite.class;

    private static Object instance;

    static {
        try {
            instance = TestSAS.getClassName().newInstance();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Exception:" + e);
        }
    }

    public static Logger getLOGGER() {
        return LOGGER;
    }

    public static void start(final Class className) {
        executeSuite(className, beforeSuite);
        for (int i = 0; i <= maxPriority(test); i++) {
            for (Method method : METHODS) {
                final boolean check = method.getAnnotation(test) != null &&
                        method.getAnnotation(test).priority() == i;
                if (check) executeMethod(className, method);
            }
        }
        executeSuite(className, afterSuite);
        LOGGER.info("-----------------------------------------------------------------");
        LOGGER.info("Total PASSED - " + Reconcile.getCountPassed() +
                "; Total FAILED - " + Reconcile.getCountFailed());
    }

    private static void executeMethod(final Class className, final Method method) {
        try {
            Reconcile.setClassMethod(className.getSimpleName(), method.getName());
            final int mods = method.getModifiers();
            final boolean staticMethod = Modifier.isStatic(mods);
            if (staticMethod) method.invoke(null, (Object[]) null);
            if (!staticMethod) method.invoke(instance, (Object[]) null);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Exception: " + e);
        }
    }

    private static int checkSuite(Class<? extends Annotation> annotate) throws MyRuntimeException {
        int count = 0;
        for (Method method : METHODS) {
            if (method.getAnnotation(annotate) != null) count++;
        }
        if (count > 1) throw new MyRuntimeException(annotate.getSimpleName());
        return count;
    }

    private static int maxPriority(final Class<? extends Test> annotation) {
        int max = 0;
        for (Method method : METHODS) {
            final boolean check = method.getAnnotation(annotation) != null &&
                    method.getAnnotation(annotation).priority() > max;
            if (check) max = method.getAnnotation(annotation).priority();
        }
        return max;
    }

    private static void executeSuite(final Class className, final Class<? extends Annotation> annotation) {
        try {
            for (Method method : METHODS) {
                final boolean check = method.getAnnotation(annotation) != null &&
                        checkSuite(annotation) <= 1;
                if (check) executeMethod(className, method);
            }
        } catch (MyRuntimeException e) {
            LOGGER.log(Level.SEVERE, "Exception: " + e);
        }
    }
}
