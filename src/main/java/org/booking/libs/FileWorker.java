package org.booking.libs;

import org.booking.enums.FilePath;
import org.booking.libs.file.ObjectReadStream;
import org.booking.libs.file.ObjectWriteStream;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class FileWorker {
    /**
     * Check if file exist
     *
     * @param filePath enum path to file
     * @return file.exist() boolean
     */
    public static boolean exist(FilePath filePath) {
        File file = new File(filePath.path);
        return file.exists();
    }

    /**
     * Write binary file.
     *
     * @param filePath enum path to file
     * @param <T>      type of data, extends List.
     * @throws IOException other exception from file write
     */
    public static <T extends List> void writeBinary(FilePath filePath, T data) throws IOException {
        File file = new File(filePath.path);
        if (!file.exists()) {
            file.createNewFile();
        }
        try (ObjectWriteStream ows = new ObjectWriteStream(file)) {
            ows.write(data);
        }
    }

    /**
     * Read binary file.
     *
     * @param filePath enum path to file
     * @param <T>      type of data, extends List.
     * @return array data T
     * @throws FileNotFoundException  if the file from file path is not found
     * @throws ClassNotFoundException if the data in file can`t parsed to object type T
     * @throws IOException            other exception from file read
     */
    public static <T extends List> T readBinary(FilePath filePath) throws ClassNotFoundException, IOException {
        File file = new File(filePath.path);
        if (!file.exists()) {
            throw new FileNotFoundException(String.format("File %s is not exists", file.getAbsolutePath()));
        }
        try (ObjectReadStream ors = new ObjectReadStream(file)) {
            return ors.read();
        }
    }
}
