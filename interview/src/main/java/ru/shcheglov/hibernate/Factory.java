package ru.shcheglov.hibernate;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Factory {

    private static Factory ourInstance;

    private SessionFactory factory;

    public static Factory getInstance() {
        if (ourInstance == null) {
            ourInstance = new Factory();
        }
        return ourInstance;
    }

    public SessionFactory getSessionFactory() {
        if (factory == null) {
            synchronized (Factory.class) {
                factory = new Configuration()
                        .configure("hibernate.cfg.xml")
                        .buildSessionFactory();
            }
        }
        return factory;
    }

    public void closeFactory() {
        if (factory != null) {
            factory.close();
        }
    }
}
