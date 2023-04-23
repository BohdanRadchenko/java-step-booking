package org.booking.entity;

public enum Aircraft {
    A320("Airbus", "A320", 840, 100),
    A321("Airbus", "A321", 880, 200),
    A380("Airbus", "A380", 1150, 300),
    B737("Boeing", "737", 820, 300),
    B777("Boeing", "777", 900, 300),
    B787("Boeing", "787", 1050, 300),
    PC24("Pilatus", "PC-24", 815, 10),
    CSBR("Cessna", "Bravo", 750, 8),
    EMPH3("Embraer", "Phenom 300", 840, 10),
    BMGE("Bombardier", "Global Express", 910, 10);

    public final String code;
    public final String company;
    public final String model;
    public final int speed;
    public final int seats;

    Aircraft(String company, String model, int speed, int passenger) {
        this.code = this.name();
        this.company = company;
        this.model = model;
        this.speed = speed;
        this.seats = passenger;

    }

    @Override
    public String toString() {
        return String.format("%s: %s - %s, %d. %d;", code, company, model, seats, speed);
    }
}
