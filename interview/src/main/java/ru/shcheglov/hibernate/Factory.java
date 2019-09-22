package ru.shcheglov.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Factory {

    public static SessionFactory getSessionFactory() {
        return new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
    }
}
