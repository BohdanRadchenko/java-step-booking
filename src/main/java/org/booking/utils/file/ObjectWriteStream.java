package org.booking.utils.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ObjectWriteStream implements AutoCloseable {
    private final ObjectOutputStream objectOutputStream;

    public ObjectWriteStream(File file) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        objectOutputStream = new ObjectOutputStream(fos);
    }

    public void write(Object instance) throws IOException {
        objectOutputStream.writeObject(instance);
    }

    @Override
    public void close() throws IOException {
        objectOutputStream.close();
    }
}