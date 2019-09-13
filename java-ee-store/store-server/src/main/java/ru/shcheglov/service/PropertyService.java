package ru.shcheglov.service;

import lombok.Getter;
import ru.shcheglov.enums.Property;
import ru.shcheglov.interceptor.LogInterceptor;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.interceptor.Interceptors;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * @author Alexey Shcheglov
 * @version dated 10.02.2019
 */

@Getter
@ManagedBean
@ApplicationScoped
@Interceptors({LogInterceptor.class})
public class PropertyService {

    private static final Logger LOGGER = Logger.getLogger(PropertyService.class.getSimpleName());

    private String serverHost;

    private Integer serverPort;

    private Integer sessionCycle;

    private String sessionSalt;

    public PropertyService() {
        getProperties();
//        final Properties properties = read();
//        this.serverHost = properties.getProperty(Property.SERVER_HOST.toString());
//        this.serverPort = Integer.valueOf(properties.getProperty(Property.SERVER_PORT.toString()));
//        this.sessionCycle = Integer.valueOf(properties.getProperty(Property.SESSION_CYCLE.toString()));
//        this.sessionSalt = properties.getProperty(Property.SESSION_SALT.toString());
    }

    private Properties read() {
        final Properties properties = new Properties();
        final File file = new File("application.properties");
        FileReader reader = null;
        try {
            reader = new FileReader(file);
            properties.load(reader);
            return properties;
        } catch (IOException e) {
            LOGGER.severe("Ошибка открытии файла свойств: " + e);
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                LOGGER.severe("Ошибка при закрытии потока чтения: " + e);
            }
        }
        return null;
    }

    private void getProperties() {
        final Properties properties = new Properties();
        try (final InputStream is = Thread.currentThread()
             .getContextClassLoader()
             .getResourceAsStream("application.properties")
        ) {
            properties.load(is);
            this.serverHost = properties.getProperty(Property.SERVER_HOST.toString());
            this.serverPort = Integer.valueOf(properties.getProperty(Property.SERVER_PORT.toString()));
            this.sessionCycle = Integer.valueOf(properties.getProperty(Property.SESSION_CYCLE.toString()));
            this.sessionSalt = properties.getProperty(Property.SESSION_SALT.toString());
        } catch (IOException e) {
            LOGGER.severe("Ошибка при чтении файла свойств: " + e);
        }
    }
}
