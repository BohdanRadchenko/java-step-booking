package org.booking.utils;

import org.booking.entity.Entity;
import org.booking.enums.FilePath;
import org.booking.utils.file.ObjectReadStream;
import org.booking.utils.file.ObjectWriteStream;

import java.io.*;
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
     * @param <E>      entity object extends Entity
     * @param <L>      List of E
     * @throws IOException other exception from file write
     */
    public static <E extends Entity, L extends List<E>> void writeBinary(FilePath filePath, L data) throws IOException {
        File file = new File(filePath.path);
        if (!exist(filePath)) {
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
     * @param <E>      entity object extends Entity
     * @param <L>      List of E
     * @return array data T
     * @throws FileNotFoundException  if the file from file path is not found
     * @throws ClassNotFoundException if the data in file can`t parsed to object type T
     * @throws IOException            other exception from file read
     */
    public static <E extends Entity, L extends List<E>> L readBinary(FilePath filePath)
            throws ClassNotFoundException, IOException {
        File file = new File(filePath.path);
        if (!file.exists()) {
            throw new FileNotFoundException(String.format("File %s is not exists", file.getAbsolutePath()));
        }
        try (ObjectReadStream ors = new ObjectReadStream(file)) {
            return ors.read();
        }
    }

    /**
     * Read text from file.
     *
     * @param filePath enum path to file
     * @return String
     * @throws IOException other exception from file read
     */
    public static String readText(FilePath filePath) throws IOException {
        File file = new File(filePath.path);
        if (!exist(filePath)) {
            file.createNewFile();
        }
        StringBuilder sb = new StringBuilder();
        try {
            try (BufferedReader in = new BufferedReader(new FileReader(file.getAbsoluteFile()))) {
                String s;
                while ((s = in.readLine()) != null) {
                    sb.append(s);
                    sb.append("\n");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        return sb.toString();
    }

    /**
     * Write text file.
     *
     * @param filePath enum path to file
     * @param text     string
     * @throws IOException other exception from file read
     */
    public static void writeText(FilePath filePath, String text) throws IOException {
        File file = new File(filePath.path);
        if (!exist(filePath)) {
            file.createNewFile();
        }

        try (PrintWriter out = new PrintWriter(file.getAbsoluteFile())) {
            out.print(text);
        }
    }

    /**
     * Update text file. Write readText + text
     *
     * @param filePath enum path to file
     * @param text     string
     * @throws IOException other exception from file read
     */
    public static void updateText(FilePath filePath, String text) throws IOException {
        if (!exist(filePath)) {
            writeText(filePath, text);
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(readText(filePath));
        sb.append(text);
        writeText(filePath, new String(sb));
    }
}
