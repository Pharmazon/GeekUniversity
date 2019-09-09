package ru.shcheglov.integration;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;
import ru.shcheglov.dto.TextMessage;

@MessagingGateway(
        name = TextMessageGateway.CHANNEL,
        defaultRequestChannel = TextMessageGateway.GATEWAY
)
public interface TextMessageGateway {

    String CHANNEL = "textMessageChannel";

    String GATEWAY = "textMessageGateway";

    @Gateway
    void fire(Message<TextMessage> message);

}
