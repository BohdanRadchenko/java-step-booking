package org.booking.enums;

public enum Aircraft {
    A320("Airbus", "A320", 7, 100),
    A321("Airbus", "A321", 7, 200),
    A380("Airbus", "A380", 8, 300),
    B737("Boeing", "737", 7, 300),
    B777("Boeing", "777", 7, 300),
    B787("Boeing", "787", 8, 300),
    PC24("Pilatus", "PC-24", 9, 10),
    CSBR("Cessna", "Bravo", 7, 8),
    EMPH3("Embraer", "Phenom 300", 7, 10),
    BMGE("Bombardier", "Global Express", 8, 10),
    HVJ("Honda", "Vision Jet", 7, 7);

    private final String code;
    private final String company;
    private final String model;
    public final int coefficient;
    public final int passenger;

    Aircraft(String company, String model, int coefficient, int passenger) {
        this.code = this.name();
        this.company = company;
        this.model = model;
        this.coefficient = coefficient;
        this.passenger = passenger;

    }

    @Override
    public String toString() {
        return String.format("%s: %s - %s, %d. %d;", code, company, model, passenger, coefficient);
    }
}
