package com.centricgateway.transtatservice.utility;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Utils {
    //VALIDATES JSON STRING
    public static boolean isJsonValid(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY);
        JsonFactory factory = mapper.getFactory();
        JsonParser parser = factory.createParser(json);
        JsonNode jsonObj = mapper.readTree(parser);
        System.out.println(jsonObj.toString());
        return true;
    }

    //VALIDATES THE DATE STRING IF IT IS IN ISO 8601 FORMAT
    public static boolean isValidDate(String dateString){
        TemporalAccessor ta = DateTimeFormatter.ISO_INSTANT.parse(dateString);
        Instant i = Instant.from(ta);
        Date date = Date.from(i);
        return new Date().after(date);
    }

    //COMVERTS AMOUNT IN BIGDECIMAL TO STRING
    public static String formatToString(BigDecimal input) {
        final NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
        numberFormat.setGroupingUsed(true);
        numberFormat.setMaximumFractionDigits(2);
        numberFormat.setMinimumFractionDigits(2);
        return numberFormat.format(input);
    }

    public static boolean has60SecsPassed(String dateString)
    {
        TemporalAccessor ta = DateTimeFormatter.ISO_INSTANT.parse(dateString);
        Instant i = Instant.from(ta);
        Date date = Date.from(i);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Long numberOfMilliSec = Calendar.getInstance().getTimeInMillis() - calendar.getTimeInMillis();
        double numberOfSecondsPassed = numberOfMilliSec / 1000.0;
        System.out.println("seconds passed: " + numberOfSecondsPassed);
        return numberOfSecondsPassed >= 60;
    }

}
