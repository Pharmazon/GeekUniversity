package ru.shcheglov.jms.definition;

import javax.ejb.Startup;
import javax.inject.Singleton;
import javax.jms.JMSDestinationDefinition;

/**
 * @author Alexey Shcheglov
 * @version dated 02.02.2019
 */

@Startup
@Singleton
@JMSDestinationDefinition(
        name = "java:/queue/simple-queue",
        interfaceName = "javax.jms.Queue",
        destinationName = "simple"
)
public class SimpleQueueDefinition {
}
