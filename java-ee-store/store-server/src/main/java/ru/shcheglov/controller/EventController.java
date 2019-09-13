package ru.shcheglov.controller;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import ru.shcheglov.event.AsyncEvent;
import ru.shcheglov.event.SyncEvent;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.jms.Topic;
import java.util.logging.Logger;

/**
 * @author Alexey Shcheglov
 * @version dated 02.02.2019
 */

@Named
@RequestScoped
@URLMapping(
        id = "event",
        pattern = "/event",
        viewId = "/WEB-INF/faces/event.xhtml"
)
public class EventController {

    private static final Logger LOGGER = Logger.getLogger(EventController.class.getSimpleName());

    @Inject
    private Event<AsyncEvent> asyncEvent;

    @Inject
    private Event<SyncEvent> syncEvent;

    @Inject
    private JMSContext context;

    @Resource(lookup = "java:/queue/simple-queue")
    private Queue queue;

    @Resource(lookup = "java:/topic/simple-topic")
    private Topic topic;

    public void fireAsyncEvent() {
        showMessage("Fire Async Event");
        LOGGER.info("fireAsyncEvent: " + Thread.currentThread().getId());
        asyncEvent.fire(new AsyncEvent("77777"));
    }

    public void fireSyncEvent() {
        showMessage("Fire Sync Event");
        LOGGER.info("fireSyncEvent: " + Thread.currentThread().getId());
        syncEvent.fire(new SyncEvent());
    }

    public void fireQueue() {
        LOGGER.info("fireQueue: " + Thread.currentThread().getId());
        context.createProducer().send(queue, "fireQueue");
        showMessage("Fire JMS Queue Event");
    }

    public void fireTopic() {
        LOGGER.info("fireTopic: " + Thread.currentThread().getId());
        context.createProducer().send(topic, "fireTopic");
        showMessage("Fire JMS Topic Event");
    }

    private void showMessage(final String message) {
        final String detail = Long.toString(Thread.currentThread().getId());
        final FacesContext facesContext = FacesContext.getCurrentInstance();
        final FacesMessage facesMessage = new FacesMessage(message, detail);
        facesContext.addMessage(null, facesMessage);
    }
}
