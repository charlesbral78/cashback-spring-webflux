package com.cashback.constants;

import java.text.ParseException;
import java.util.Date;

public final class DateUtils {

    public static final Date parseDate(String dateAsString) throws ParseException {
        return org.apache.commons.lang3.time.DateUtils.parseDate(dateAsString, Constants.DATE_TIME_PATTERN);
    }
}
