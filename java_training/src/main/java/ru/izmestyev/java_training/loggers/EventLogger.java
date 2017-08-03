package ru.izmestyev.java_training.loggers;


import ru.izmestyev.java_training.events.Event;

public interface EventLogger {
    void logEvent(Event event);
}

