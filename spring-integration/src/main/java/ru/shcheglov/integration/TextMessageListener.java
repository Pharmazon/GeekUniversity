package ru.shcheglov.integration;

import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import ru.shcheglov.dto.TextMessage;

import java.util.logging.Logger;

@MessageEndpoint
public class TextMessageListener {

    private static final Logger LOGGER = Logger.getLogger(TextMessageListener.class.getSimpleName());

    @ServiceActivator(inputChannel = TextMessageGateway.CHANNEL)
    public void handler(final TextMessage message) {
        LOGGER.info("TextMessage: " + message.getMessage());
    }
}
