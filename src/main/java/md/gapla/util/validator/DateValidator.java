package md.gapla.util.validator;


import md.gapla.exception.CustomParseException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateValidator {

    private DateValidator() {
    }

    public static Date validateDateOrThrowException(final String date, final String format) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            return dateFormat.parse(date);
        } catch (ParseException ex) {
            throw new CustomParseException(date);
        }
    }

}
