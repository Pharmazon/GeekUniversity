package ru.shcheglov.integration.broadcast;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.shcheglov.dto.ResultDTO;

@RestController
@RequestMapping("/api/broadcast")
public class BroadcastController {

    @Autowired
    private BroadcastGateway broadcastGateway;

    public ResultDTO broadcast() {
        for (int i = 0; i < 10; i++) {
            broadcastGateway.fire(MessageBuilder
                    .withPayload("HELLO!")
                    .build());
        }
        return new ResultDTO();
    }
}
