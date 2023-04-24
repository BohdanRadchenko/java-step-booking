package org.booking.helpers;

import org.booking.entity.Airport;
import org.booking.utils.Parser;
import org.booking.utils.StringWorker;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Utm {
    private static final int ONE_UTM_DISTANCE = 200;

    private static class ParsedUtm {
        private final String[] ws
                = {"x", "w", "v", "u", "t", "s", "r", "q", "p", "n", "m", "l", "k", "j", "h", "g", "f", "e", "d", "c"};
        public final int utmCoordX;
        public final int utmCoordY;

        ParsedUtm(String utm) {
            Pattern pattern = Pattern.compile("^(\\d+)(\\w+)");
            Matcher matcher = pattern.matcher(utm);
            if (!matcher.find()) {
                throw new RuntimeException("Invalid utm");
            }
            this.utmCoordX = Parser.parseInt(matcher.group(1));
            this.utmCoordY = toCode(matcher.group(2));
        }

        private int toCode(String str) {
            return IntStream.range(0, ws.length)
                    .boxed()
                    .collect(Collectors.toMap(i -> ws[i], i -> i + 1, (a, b) -> b))
                    .get(StringWorker.toLowerCase(str));
        }
    }

    private final ParsedUtm parsedUtm;

    public Utm(String utm) {
        this.parsedUtm = new ParsedUtm(utm);
    }

    public ParsedUtm getUtm() {
        return parsedUtm;
    }

    public static int distance(Utm u1, Utm u2) {
        if (u1.getUtm() == null || u2.getUtm() == null) return 0;
        int a = Math.abs(u1.parsedUtm.utmCoordX - u2.parsedUtm.utmCoordX);
        int b = Math.abs(u1.parsedUtm.utmCoordY - u2.parsedUtm.utmCoordY);
        return (int) Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2)) * ONE_UTM_DISTANCE;
    }

    public static int distance(Airport a1, Airport a2) {
        return distance(a1.utm, a2.utm);
    }
}
