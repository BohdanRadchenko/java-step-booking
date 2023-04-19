package org.booking.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlightList {
    Flight warsaw = new Flight(1," A", "14/02/2022","05:40",     "Warsaw          ", "LOT         ", "LO756 ", 378);
    Flight larnaca = new Flight(2, " A", "10/05/2022", "06:10", "Larnaca         ", "Wizz Air    ", "W66259", 180);
    Flight bratislava = new Flight(3, " A", "24/12/2022", "06:20", "Bratislava      ", "Wizz Air    ", "W66275", 180);
    Flight athens = new Flight(4, " A", "15/09/2022", "06:30", "Athens          ", "Wizz Air    ", "W66267", 180);
    Flight оdesa = new Flight(5, " D", "14/12/2022", "07:00", "Odesa           ", "Bees Airline", "7B103 ", 189);
    Flight tbilisi = new Flight(6, " A", "01/02/2022", "07:05", "Tbilisi         ", "Bees Airline", "7B331 ", 189);
    Flight budapest = new Flight(7, " A", "28/06/2022", "09:45", "Budapest        ", "Wizz Air    ", "W62474", 150);
    Flight lisbon = new Flight(8, " A", "22/10/2022", "16:05", "Lisbon          ", "Wizz Air    ", "W66191", 180);
    Flight zaporizhzhia = new Flight(9, " D", "15/02/2022", "21:15", "Zaporizhzhia    ", "Motor Sich  ", "M9202 ", 100);
    Flight warsaw1 = new Flight(10, "A", "14/02/2022", "05:40", "Larnaca         ", "LOT         ", "LO756 ", 378);
    Flight larnaca1 = new Flight(11, "A", "10/05/2022", "06:10", "Warsaw          ", "Wizz Air    ", "W66259", 210);
    Flight bratislava1 = new Flight(12, "A", "24/12/2022", "06:20", "Athens          ", "Wizz Air    ", "W66275", 180);
    Flight athens1 = new Flight(13, "A", "15/09/2022", "06:30", "Helsinki        ", "Wizz Air    ", "W66267", 180);
    Flight оdesa1 = new Flight(14, "D", "14/12/2022", "07:00", "Tbilisi         ", "Bees Airline", "7B103 ", 189);
    Flight tbilisi1 = new Flight(15, "A", "01/02/2022", "07:05", "Odesa           ", "Bees Airline", "7B331 ", 189);
    Flight budapest1 = new Flight(16, "A", "28/06/2022", "09:45", "Lisbon          ", "Wizz Air    ", "W62474", 150);
    Flight lisbon1 = new Flight(17, "A", "22/10/2022", "16:05", "Budapest        ", "Wizz Air    ", "W66191", 180);
    Flight zaporizhzhia1 = new Flight(18, "D", "15/02/2022", "21:15", "Budapest        ", "Motor Sich  ", "M9202 ", 100);
    Flight warsaw2 = new Flight(19, "A", "14/02/2022", "05:40", "Hungary         ", "LOT         ", "LO756 ", 378);
    Flight larnaca2 = new Flight(20, "A", "10/05/2022", "06:10", "Amsterdam       ", "Wizz Air    ", "W66259", 180);
    Flight bratislava2 = new Flight(21, "A", "24/12/2022", "06:20", "Madrid          ", "Wizz Air    ", "W66275", 180);
    Flight athens2 = new Flight(22, "A", "15/09/2022", "06:30", "Riga            ", "Wizz Air    ", "W66267", 180);
    Flight оdesa2 = new Flight(23, "D", "14/12/2022", "07:00", "Luxembourg      ", "Bees Airline", "7B103 ", 189);
    Flight tbilisi2 = new Flight(24, "A", "01/02/2022", "07:05", "Macedonia       ", "Bees Airline", "7B331 ", 189);
    Flight budapest2 = new Flight(25, "A", "28/06/2022", "09:45", "Malta           ", "Wizz Air    ", "W62474", 150);
    Flight lisbon2 = new Flight(26, "A", "22/10/2022", "16:05", "Moscow          ", "Wizz Air    ", "W66191", 180);
    Flight zaporizhzhia2 = new Flight(27, "D", "15/02/2022", "21:15", "Kharkov         ", "Motor Sich  ", "M9202 ", 100);
    Flight warsaw3 = new Flight(28, "A", "14/02/2022", "16:40", "Warsaw          ", "LOT         ", "LO756 ", 378);
    Flight larnaca3 = new Flight(28, "A", "10/05/2022", "16:10", "Larnaca         ", "Wizz Air    ", "W66259", 180);
    Flight bratislava3 = new Flight(30, "A", "24/12/2022", "16:20", "Bratislava      ", "Wizz Air    ", "W66275", 180);
    Flight athens3 = new Flight(31, "A", "15/09/2022", "16:30", "Athens          ", "Wizz Air    ", "W66267", 180);
    Flight оdesa3 = new Flight(32, "D", "14/12/2022", "17:00", "Odesa           ", "Bees Airline", "7B103 ", 189);
    Flight tbilisi3 = new Flight(33, "A", "01/02/2022", "17:05", "Tbilisi         ", "Bees Airline", "7B331 ", 189);
    Flight budapest3 = new Flight(34, "A", "28/06/2022", "19:45", "Budapest        ", "Wizz Air    ", "W62474", 150);
    Flight lisbon3 = new Flight(35, "A", "22/10/2022", "22:05", "Lisbon          ", "Wizz Air    ", "W66191", 180);
    Flight zaporizhzhia3 = new Flight(36, "D", "15/02/2022", "23:15", "Zaporizhzhia    ", "Motor Sich  ", "M9202 ", 100);
    Flight warsaw4 = new Flight(37, "A", "14/02/2022", "15:40", "Larnaca         ", "LOT         ", "LO756 ", 378);
    Flight larnaca4 = new Flight(38, "A", "10/05/2022", "16:10", "Warsaw          ", "Wizz Air    ", "W66259", 180);
    Flight bratislava4 = new Flight(39, "A", "24/12/2022", "16:20", "Athens          ", "Wizz Air    ", "W66275", 180);
    Flight athens4 = new Flight(40, "A", "15/09/2022", "16:30", "Helsinki        ", "Wizz Air    ", "W66267", 180);
    Flight оdesa4 = new Flight(41, "D", "14/12/2022", "17:00", "Tbilisi         ", "Bees Airline", "7B103 ", 189);
    Flight tbilisi4 = new Flight(42, "A", "01/02/2022", "17:05", "Odesa           ", "Bees Airline", "7B331 ", 189);
    Flight budapest4 = new Flight(43, "A", "28/06/2022", "19:45", "Lisbon          ", "Wizz Air    ", "W62474", 150);
    Flight lisbon4 = new Flight(44, "A", "22/10/2022", "23:05", "Budapest        ", "Wizz Air    ", "W66191", 180);
    Flight zaporizhzhia4 = new Flight(45, "D", "15/02/2022", "23:15", "Budapest        ", "Motor Sich  ", "M9202 ", 100);
    Flight warsaw5 = new Flight(46, "A", "14/02/2022", "15:40", "Hungary         ", "LOT         ", "LO756 ", 378);
    Flight larnaca5 = new Flight(47, "A", "10/05/2022", "16:10", "Amsterdam       ", "Wizz Air    ", "W66259", 180);
    Flight bratislava5 = new Flight(47, "A", "24/12/2022", "16:20", "Madrid          ", "Wizz Air    ", "W66275", 180);
    Flight athens5 = new Flight(49, "A", "15/09/2022", "11:30", "Riga            ", "Wizz Air    ", "W66267", 180);
    Flight оdesa5 = new Flight(50, "D", "14/12/2022", "17:00", "Luxembourg      ", "Bees Airline", "7B103 ", 189);
    Flight tbilisi5 = new Flight(51, "A", "01/02/2022", "17:05", "Macedonia       ", "Bees Airline", "7B331 ", 189);
    Flight budapest5 = new Flight(52, "A", "28/06/2022", "19:45", "Malta           ", "Wizz Air    ", "W62474", 150);
    Flight lisbon5 = new Flight(53, "A", "22/10/2022", "21:05", "Moscow          ", "Wizz Air    ", "W66191", 180);
    Flight zaporizhzhia5 = new Flight(54, "D", "15/02/2022", "22:15", "Kharkov         ", "Motor Sich  ", "M9202 ", 100);
    List<Flight> allFlight = new ArrayList<>();
    public List<Flight> getAllFlight() {
        allFlight.add(warsaw);allFlight.add(larnaca);allFlight.add(bratislava);allFlight.add(athens);allFlight.add(оdesa);
        allFlight.add(tbilisi);allFlight.add(budapest);allFlight.add(lisbon);allFlight.add(zaporizhzhia);
        allFlight.add(warsaw1);allFlight.add(larnaca1);allFlight.add(bratislava1);allFlight.add(athens1);allFlight.add(оdesa1);
        allFlight.add(tbilisi1);allFlight.add(budapest1);allFlight.add(lisbon1);allFlight.add(zaporizhzhia1);
        allFlight.add(warsaw2);allFlight.add(larnaca2);allFlight.add(bratislava2);allFlight.add(athens2);allFlight.add(оdesa2);
        allFlight.add(tbilisi2);allFlight.add(budapest2);allFlight.add(lisbon2);allFlight.add(zaporizhzhia2);
        allFlight.add(warsaw3);allFlight.add(larnaca3);allFlight.add(bratislava3);allFlight.add(athens3);allFlight.add(оdesa3);
        allFlight.add(tbilisi3);allFlight.add(budapest3);allFlight.add(lisbon3);allFlight.add(zaporizhzhia3);
        allFlight.add(warsaw4);allFlight.add(larnaca4);allFlight.add(bratislava4);allFlight.add(athens4);allFlight.add(оdesa4);
        allFlight.add(tbilisi4);allFlight.add(budapest4);allFlight.add(lisbon4);allFlight.add(zaporizhzhia4);
        allFlight.add(warsaw5);allFlight.add(larnaca5);allFlight.add(bratislava5);allFlight.add(athens5);allFlight.add(оdesa5);
        allFlight.add(tbilisi5);allFlight.add(budapest5);allFlight.add(lisbon5);allFlight.add(zaporizhzhia5);
        return allFlight;
    }
    public Map<Integer, Flight> getFlightList(){
        Map<Integer, Flight> flyList = new HashMap<>();
        flyList.put(1, warsaw);flyList.put(2, larnaca);flyList.put(3, bratislava);flyList.put(4, athens);flyList.put(5, оdesa);
        flyList.put(6, tbilisi);flyList.put(7, budapest);flyList.put(8, lisbon);flyList.put(9, zaporizhzhia);flyList.put(10, warsaw1);
        flyList.put(11, larnaca1);flyList.put(12, bratislava1);flyList.put(13, athens1);flyList.put(14, оdesa1);flyList.put(15, tbilisi1);flyList.put(16, budapest1);
        flyList.put(17, lisbon1);flyList.put(18, zaporizhzhia1);flyList.put(19, warsaw2);flyList.put(20, larnaca2);flyList.put(21, bratislava2);
        flyList.put(22, athens2);flyList.put(23, оdesa2);flyList.put(24, tbilisi2);flyList.put(25, budapest2);flyList.put(26, lisbon2);flyList.put(27, zaporizhzhia2);
        flyList.put(28, warsaw3);flyList.put(29, larnaca3);flyList.put(30, bratislava3);flyList.put(31, athens3);flyList.put(32, оdesa3);
        flyList.put(33, tbilisi3);flyList.put(34, budapest3);flyList.put(35, lisbon3);flyList.put(36, zaporizhzhia3);
        flyList.put(37, warsaw4);flyList.put(38, larnaca4);flyList.put(39, bratislava4);flyList.put(40, athens4);flyList.put(41, оdesa4);
        flyList.put(42, tbilisi4);flyList.put(43, budapest4);flyList.put(44, lisbon4);flyList.put(45, zaporizhzhia4);
        flyList.put(46, warsaw5);flyList.put(47, larnaca5);flyList.put(48, bratislava5);flyList.put(49, athens5);flyList.put(50, оdesa5);
        flyList.put(51, tbilisi5);flyList.put(52, budapest5);flyList.put(53, lisbon5);flyList.put(54, zaporizhzhia5);
        return flyList;
    }

}
