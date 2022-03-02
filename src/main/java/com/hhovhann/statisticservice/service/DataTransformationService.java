package com.hhovhann.statisticservice.service;

import org.springframework.stereotype.Component;
import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.stream.Collectors;

@Component
public class DataTransformationService {
    private final static String CHAR_SEPARATOR = " ";

    public Number reverse(String data, String inputType) {
        return switch (inputType) {
            case "integer" -> reverseInteger(NumberUtils.parseNumber(data, Integer.class));
            case "double" -> reverseDouble(NumberUtils.parseNumber(data, Double.class));
            default -> throw new RuntimeException("Type not supported ...");
        };
    }

    private static int reverseInteger(Number number) {
        int n = number.intValue();
        int reversedNumber = 0;
        int remainder;
        while (n > 0) {
            remainder = n % 10;
            reversedNumber = (reversedNumber * 10) + remainder;
            n = n / 10;
        }
        return reversedNumber;
    }

    private static double reverseDouble(Number number) {
        String reversed = new StringBuffer(number.toString()).reverse().toString();
        return Double.parseDouble(reversed);
    }

    public Number negate(String data, String inputType) {
        return switch (inputType) {
            case "integer" -> Math.negateExact(NumberUtils.parseNumber(data, Integer.class));
            case "double" -> -NumberUtils.parseNumber(data, Double.class);
            default -> throw new RuntimeException("Type not supported ...");
        };
    }

    public String capitalize(String data) {
        return Arrays
                .stream(data.split(CHAR_SEPARATOR))
                .map(StringUtils::capitalize)
                .collect(Collectors.joining(CHAR_SEPARATOR));
    }
}
