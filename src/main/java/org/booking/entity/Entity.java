package org.booking.entity;

import org.booking.interfaces.IEntity;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

public abstract class Entity implements IEntity, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final String id;
    public static long bookingCounter = 0;

    {
        String className = this.getClass().getSimpleName();
        if (className.equals("Booking")) {
            bookingCounter++;
        }
        id = UUID.randomUUID().toString();
    }

    @Override
    public String getId() {
        return id;
    }
}
