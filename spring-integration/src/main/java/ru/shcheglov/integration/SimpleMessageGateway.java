package ru.shcheglov.integration;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;
import ru.shcheglov.dto.SimpleMessage;

@MessagingGateway(
        name = SimpleMessageGateway.CHANNEL,
        defaultRequestChannel = SimpleMessageGateway.GATEWAY
)
public interface SimpleMessageGateway {

    String CHANNEL = "simpleMessageChannel";

    String GATEWAY = "simpleMessageGateway";

    @Gateway
    void fire(Message<SimpleMessage> message);

}
