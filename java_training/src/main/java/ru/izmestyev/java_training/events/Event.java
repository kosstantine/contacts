package ru.izmestyev.java_training.events;


import java.text.DateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.Random;

public class Event {
    private int id;
    private String msg;
    private Date date;
    private DateFormat df;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Event(Date date, DateFormat df) {
        this.date = date;
        this.df = df;
        Random random = new Random();
        id = random.nextInt(100);
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return String.format("id = %d; msg = %s; date = %s", id, msg, df.format(date));
    }

    public static boolean isDay(int start, int end) {
        LocalTime time = LocalTime.now();
        return time.getHour() > start && time.getHour() < end;
    }
}
