package ru.shcheglov.integration;

import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Transformer;
import ru.shcheglov.dto.SimpleMessage;
import ru.shcheglov.dto.TextMessage;

@MessageEndpoint
public class SimpleTextMessageTransformer {

    @Transformer(inputChannel = SimpleMessageGateway.CHANNEL, outputChannel = TextMessageGateway.CHANNEL)
    public TextMessage transform(SimpleMessage simpleMessage) {
        System.out.println("SimpleTextMessageTransformer");
        return new TextMessage(simpleMessage.getDate().toString());
    }
}
