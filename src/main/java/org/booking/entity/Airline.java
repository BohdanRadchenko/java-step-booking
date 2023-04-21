package org.booking.entity;

public enum Airline {
    UKRAINE_INTERNATIONAL("Ukraine International Airlines", "AUI", "566", "PS", "Europe"),
    QATAR_AIRWAYS("Qatar Airways Company", "QTR", "157", "QR", "Africa & Middle East"),
    SINGAPORE_AIRLINES("Singapore Airlines Limited", "SIA", "618", "SQ", "Asia Pacific"),
    EMIRATES("Emirates", "UAE", "176", "EK", "Asia Pacific"),
    JAPAN_AIRLINES("Japan Airlines Co.", "JAL", "131", "JL", "Africa & Middle East"),
    AIR_FRANCE("Société Air France", "AFR", "057", "AF", "Europe"),
    KOREAN_AIR("Korean Air Lines Co.", "KAL", "180", "KE", "Asia Pacific"),
    AMERICAN_AIRLINES("American Airlines", "AAL", "001", "AA", "The Americas"),
    BRITISH_AIRWAYS("British Airways Plc.", "BAW", "125", "BA", "Europe"),
    TURKISH_AIRLINES("Turkish Airlines Inc.", "THY", "235", "TK", "Europe"),
    CHARTER_FLY("NULL", "CHR", "000", "CF", "NULL");

    public final String name;
    public final String legalName;
    public final String cao;
    public final String airline;
    public final String code; //Код для номера рейса
    public final String region;

    Airline(String legalName, String cao, String airline, String code, String region) {
        this.name = this.name();
        this.legalName = legalName;
        this.cao = cao;
        this.airline = airline;
        this.code = code;
        this.region = region;
    }

    @Override
    public String toString() {
        return String.format("%s[code: %s; airline: %s; region: %s]", cao, code, airline, region);
    }
}
