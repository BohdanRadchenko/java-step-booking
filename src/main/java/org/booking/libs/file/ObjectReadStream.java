package org.booking.libs.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ObjectReadStream implements AutoCloseable {
    private final ObjectInputStream objectInputStream;

    public ObjectReadStream(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        objectInputStream = new ObjectInputStream(fis);
    }

    public <T> T read() throws IOException, ClassNotFoundException {
        return (T) objectInputStream.readObject();
    }

    @Override
    public void close() throws IOException {
        objectInputStream.close();
    }
}
