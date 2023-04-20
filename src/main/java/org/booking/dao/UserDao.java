package org.booking.dao;

import org.booking.entity.User;
import org.booking.enums.FilePath;
import org.booking.utils.FileWorker;

import java.io.IOException;
import java.util.List;

public class UserDao extends Dao<User> {
    @Override
    public void save() throws RuntimeException {
        try {
            FileWorker.writeBinary(FilePath.USER, getAll());
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public List<User> read() throws RuntimeException {
        try {
            return FileWorker.readBinary(FilePath.USER);
        } catch (IOException | ClassNotFoundException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
}
