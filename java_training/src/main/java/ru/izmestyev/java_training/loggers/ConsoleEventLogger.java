package ru.izmestyev.java_training.loggers;


import ru.izmestyev.java_training.events.Event;

public class ConsoleEventLogger implements EventLogger {
    public void logEvent(Event event) {
        System.out.println(event);
    }
}
