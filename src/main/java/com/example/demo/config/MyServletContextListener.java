package com.example.demo.config;

import javax.servlet.ServletContextListener;
import javax.servlet.ServletContextEvent;
import org.springframework.stereotype.Component;
import com.example.demo.websocket.WebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

@Component
public class MyServletContextListener implements ServletContextListener {

    private static ApplicationContext context;

    @Autowired
    public MyServletContextListener(ApplicationContext ctx) {
        context = ctx;
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        WebSocket.setApplicationContext(context);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }

    public static ApplicationContext getApplicationContext() {
        return context;
    }
}
