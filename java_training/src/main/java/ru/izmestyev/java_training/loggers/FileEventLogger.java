package ru.izmestyev.java_training.loggers;


import org.apache.commons.io.FileUtils;
import ru.izmestyev.java_training.events.Event;
import java.io.File;
import java.io.IOException;

public class FileEventLogger implements EventLogger {
    private String fileName;
    private File file;

    public FileEventLogger(String fileName) {
        this.fileName = fileName;
    }

    public void logEvent(Event event) {
        try {
            FileUtils.writeStringToFile(file, event.toString() + "\n", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void init() throws IOException {
        this.file = new File(fileName);
        if (!file.canWrite() && file.exists()) {
            throw new IllegalArgumentException("Can't write to file" + fileName);
        } else if (!file.exists()) {
            file.createNewFile();
        }
    }
}
