package ru.shcheglov.integration;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;
import ru.shcheglov.dto.WelcomeMessage;

@MessagingGateway(
        name = WelcomeMessageGateway.CHANNEL,
        defaultRequestChannel = WelcomeMessageGateway.GATEWAY
)
public interface WelcomeMessageGateway {

    String CHANNEL = "welcomeMessageChannel";

    String GATEWAY = "welcomeMessageGateway";

    @Gateway
    void fire(Message<WelcomeMessage> message);

}
