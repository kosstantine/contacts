package ru.izmestyev.java_training.loggers;


import org.springframework.jdbc.core.JdbcTemplate;
import ru.izmestyev.java_training.events.Event;

public class DbLogger implements EventLogger {
    private JdbcTemplate jdbcTemplate;
    private String schema;

    public DbLogger(JdbcTemplate jdbcTemplate, String schema) {
        this.jdbcTemplate = jdbcTemplate;
        this.schema = schema;
    }

    public void logEvent(Event event) {
        jdbcTemplate.update(
                "INSERT INTO events (id, date, msg) VALUES (?,?,?)",
                event.getId(),
                event.getDate(),
                event.toString());
    }

    public void init() {
        jdbcTemplate.update(
                "CREATE TABLE IF NOT EXISTS events " +
                        "(id INT NOT NULL PRIMARY KEY , date TIMESTAMP, msg VARCHAR(255))"
        );

    }
}