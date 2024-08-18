package org.abondar.experimental.travel.model.batch;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class FormatUtil {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy H:mm:ss z", Locale.ENGLISH);


    private FormatUtil() {}
}
