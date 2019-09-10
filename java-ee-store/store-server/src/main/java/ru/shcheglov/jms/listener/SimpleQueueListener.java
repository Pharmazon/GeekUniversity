package ru.shcheglov.jms.listener;

import lombok.SneakyThrows;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

/**
 * @author Alexey Shcheglov
 * @version dated 02.02.2019
 */

@MessageDriven(
        activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:/queue/simple-queue"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class SimpleQueueListener implements MessageListener {

    @Override
    @SneakyThrows
    public void onMessage(Message message) {
        if (message instanceof ObjectMessage) {
            final ObjectMessage msg = (ObjectMessage) message;
            final Object object= msg.getObject();
        }
        if (message instanceof TextMessage) {
            final TextMessage msg = (TextMessage) message;
            System.out.println("JMS: " + Thread.currentThread().getId());
            System.out.println("JMS Queue: " + msg.getText());
            System.out.println();
        }
    }

}
