package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtils {
    public static String dayOfWeekJapanese(int dayOfWeek) {
        String[] days = {"月", "火", "水", "木", "金", "土", "日"};
        return days[dayOfWeek - 1];
    }

    public static boolean isValidDate(String dateStr) {
        try {
            LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
