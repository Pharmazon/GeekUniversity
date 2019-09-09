package ru.shcheglov.integration;

import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import ru.shcheglov.dto.WelcomeMessage;

import java.util.logging.Logger;

@MessageEndpoint
public class WelcomeNotNullListener {

    private static final Logger LOGGER = Logger.getLogger(WelcomeNotNullListener.class.getSimpleName());

    @ServiceActivator(inputChannel = "welcomeNotNullChannel")
    public void handler(WelcomeMessage message) {
        LOGGER.info("HELLO: " + message.getName());
    }

}
