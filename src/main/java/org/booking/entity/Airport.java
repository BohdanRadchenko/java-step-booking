package org.booking.entity;

import org.booking.helpers.Utm;
import org.booking.utils.Parser;
import org.booking.utils.StringWorker;

import java.util.HashSet;
import java.util.Set;

public enum Airport {
    KBP("Ukraine", "UA", "Kyiv", "36U"),
    HRK("Ukraine", "UA", "Kharkiv", "36U"),
    LHR("United Kingdom", "UK", "London", "30U"),
    BST("United Kingdom", "UK", "Bristol", "30U"),
    BUD("Hungary", "HU", "Budapest", "34T"),
    KSC("Slovakia", "SK", "Kosice", "34U"),
    FRA("Germany", "DE", "Frankfurt", "32U"),
    GYD("Azerbaijan", "AZ", "Baku", "39T"),
    DEN("USA", "AM", "Denver", "13S"),
    BOS("USA", "AM", "Boston", "19T"),
    VIE("Austria", "AT", "Vienna", "33U"),
    MAD("Spain", "ES", "Madrid", "30T"),
    MRS("France", "FR", "Marseille", "31T"),
    IST("Turkey", "TR", "Istanbul", "35T");

    public final String code;
    public final String country;
    public final String countryShort;
    public final String city;
    public final Utm utm;

    public Set<String> contains = new HashSet<>();

    Airport(String country, String countryShort, String city, String utm) {
        this.code = this.name();
        this.country = country;
        this.countryShort = countryShort;
        this.city = city;
        this.utm = new Utm(utm);

        contains.add(StringWorker.toLowerCase(name()));
        contains.add(StringWorker.toLowerCase(country));
        contains.add(StringWorker.toLowerCase(countryShort));
        contains.add(StringWorker.toLowerCase(city));
        contains.add(StringWorker.toLowerCase(utm));
    }

    public boolean equals(String string) {
        string = StringWorker.toLowerCase(string);
        return Parser.containsWords(string, contains);
    }

    @Override
    public String toString() {
        return String.format("%s: %s %s", code, country, city);
    }
}
