package ru.shcheglov.bootstrap;

import ru.shcheglov.service.UserService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.logging.Logger;

/**
 * @author Alexey Shcheglov
 * @version dated 08.02.2019
 */

@Startup
@Singleton
public class Bootstrap {

    private static final Logger LOGGER = Logger.getLogger(Bootstrap.class.getSimpleName());

    @Inject
    private UserService userService;

    @PostConstruct
    private void start() {
        LOGGER.info("***** APPLICATION START *****");
        userService.register("admin", "admin");
        userService.register("test", "test");
    }

    @PreDestroy
    private void stop() {
        LOGGER.info("***** APPLICATION STOP *****");
    }

}
