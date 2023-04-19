package org.booking.entity;

import java.io.Serializable;

public class AbstractEntity implements Serializable {
    public long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public AbstractEntity(long id) {
        this.id = id;
    }
}
