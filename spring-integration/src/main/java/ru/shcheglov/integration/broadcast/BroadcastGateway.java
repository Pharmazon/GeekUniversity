package ru.shcheglov.integration.broadcast;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;

@MessagingGateway(
        name = BroadcastGateway.CHANNEL,
        defaultRequestChannel = BroadcastGateway.GATEWAY
)
public interface BroadcastGateway {

    String CHANNEL = "broadcastChannel";

    String GATEWAY = "broadcastGateway";

    @Gateway
    void fire(Message<String> message);

}
