package ru.shcheglov.listener;

import org.jetbrains.annotations.Async;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import java.util.logging.Logger;

/**
 * @author Alexey Shcheglov
 * @version dated 02.02.2019
 */

@Stateless
public class AsyncListener {

    private static final Logger LOGGER = Logger.getLogger(AsyncListener.class.getSimpleName());

    @Asynchronous
    public void observe(@Observes final Async event) {
        LOGGER.info("[ASYNC] OBSERVE THREAD ID: " + Thread.currentThread().getId());
    }

}
