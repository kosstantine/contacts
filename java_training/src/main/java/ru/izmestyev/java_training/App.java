package ru.izmestyev.java_training;


import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.izmestyev.java_training.events.Event;
import ru.izmestyev.java_training.events.EventType;
import ru.izmestyev.java_training.loggers.EventLogger;
import java.util.Map;

public class App {
    private Client client;
    private EventLogger defaultLogger;
    private Map<EventType, EventLogger> loggers;

    public void logEvent (Event event, EventType eventType, String msg) {
        String message = msg.replaceAll(client.getId(), client.getFullName());
        message += ", " + client.greeting;
        event.setMsg(message);

        EventLogger logger = loggers.get(eventType);
        if (logger == null) {
            logger = defaultLogger;
        }
        logger.logEvent(event);
    }

    public App(Client client, EventLogger defaultLogger, Map<EventType, EventLogger> loggers) {
        this.client = client;
        this.defaultLogger = defaultLogger;
        this.loggers = loggers;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");

        App app = (App) ctx.getBean("app");

        app.logEvent((Event) ctx.getBean("event"), EventType.INFO, "For User 1");
        app.logEvent((Event) ctx.getBean("event"), EventType.ERROR, "For User 1");
        app.logEvent((Event) ctx.getBean("event"), null, "For User 1");

        ctx.close();
    }
}
