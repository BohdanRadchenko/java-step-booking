package org.booking.entity;

import org.booking.interfaces.IEntity;

import java.io.Serializable;
import java.util.UUID;

public abstract class Entity implements IEntity, Serializable {
    private final String id;

    {
        id = UUID.randomUUID().toString();
    }

    @Override
    public String getId() {
        return id;
    }
}