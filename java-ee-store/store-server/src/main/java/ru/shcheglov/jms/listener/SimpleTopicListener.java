package ru.shcheglov.jms.listener;

import lombok.SneakyThrows;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @author Alexey Shcheglov
 * @version dated 02.02.2019
 */

@MessageDriven(
        activationConfig = {
                @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:/topic/simple-topic"),
                @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
                @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
        })
public class SimpleTopicListener implements MessageListener {

    @Override
    @SneakyThrows
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            final TextMessage msg = (TextMessage) message;
            System.out.println("JMS: " + Thread.currentThread().getId());
            System.out.println("JMS TOPIC: " + msg.getText());
            System.out.println();
        }
    }


}
