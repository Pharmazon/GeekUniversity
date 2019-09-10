package ru.shcheglov.interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import java.io.Serializable;
import java.util.logging.Logger;

/**
 * @author Alexey Shcheglov
 * @version dated 02.02.2019
 */

public class LogInterceptor implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(LogInterceptor.class.getSimpleName());

    @AroundInvoke
    public Object log(final InvocationContext context) throws Exception {
        LOGGER.info("Вызван метод: " + context.getMethod().getName());
        return context.proceed();
    }

}
