package org.booking.entity;

import org.booking.interfaces.IEntity;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

public abstract class Entity implements IEntity, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final String id;
    public long bookingCounter = 0;

    {
        id = UUID.randomUUID().toString();
    }

    @Override
    public String getId() {
        return id;
    }
}
