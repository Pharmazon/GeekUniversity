package ru.shcheglov.listener;

import ru.shcheglov.event.SyncEvent;

import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import java.util.logging.Logger;

/**
 * @author Alexey Shcheglov
 * @version dated 02.02.2019
 */

@Stateless
public class SyncListener {

    private static final Logger LOGGER = Logger.getLogger(AsyncListener.class.getSimpleName());

    public void observe(@Observes final SyncEvent event) {
        LOGGER.info("[SYNC] OBSERVE THREAD ID: " + Thread.currentThread().getId());
    }
}
