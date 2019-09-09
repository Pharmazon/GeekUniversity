package ru.shcheglov.integration.broadcast;

import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;

import java.util.logging.Logger;

@MessageEndpoint
public class BroadcastSubListener {

    private static final Logger LOGGER = Logger.getLogger(BroadcastSubListener.class.getSimpleName());

    @ServiceActivator(inputChannel = BroadcastGateway.CHANNEL)
    public void handler(final String message) {
        LOGGER.info("Broadcast2: " + message);
    }

}
