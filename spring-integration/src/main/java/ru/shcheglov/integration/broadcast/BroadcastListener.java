package ru.shcheglov.integration.broadcast;

import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;

import java.util.logging.Logger;

@MessageEndpoint
public class BroadcastListener {

    private static final Logger LOGGER = Logger.getLogger(BroadcastListener.class.getSimpleName());

    @ServiceActivator(inputChannel = BroadcastGateway.CHANNEL)
    public void handler(final String message) {
        LOGGER.info("Broadcast1: " + message);
    }

}
