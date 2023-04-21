package org.booking.entity;

import org.booking.helpers.Utm;

public enum Airport {
    KBP("Ukraine", "Kyiv", "36U"),
    LHR("United Kingdom", "London", "30U"),
    BUD("Hungary", "Budapest", "34T"),
    KSC("Slovakia", "Kosice", "34U"),
    FRA("Germany", "Frankfurt", "32U"),
    GYD("Azerbaijan", "Baku", "39T"),
    DEN("USA", "Denver", "13S"),
    BOS("USA", "Boston", "19T"),
    VIE("Austria", "Vienna", "33U"),
    MAD("Spain", "Madrid", "30T"),
    MRS("France", "Marseille", "31T"),
    IST("Turkey", "Istanbul", "35T");

    public final String code;
    public final String country;
    public final String city;
    public final Utm utm;

    Airport(String country, String city, String utm) {
        this.code = this.name();
        this.country = country;
        this.city = city;
        this.utm = new Utm(utm);
    }

    @Override
    public String toString() {
        return String.format("%s: %s %s %s", code, country, city, utm);
    }
}
