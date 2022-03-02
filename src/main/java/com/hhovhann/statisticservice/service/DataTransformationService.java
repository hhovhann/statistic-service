package com.hhovhann.statisticservice.service;

import org.springframework.stereotype.Service;
import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class DataTransformationService {
    private final static String CHAR_SEPARATOR = " ";
    private final static String DASH_SEPARATOR = "-";

    public Object transformData(String currentLine, String operation, String inputtype) {
        return switch (operation) {
            case "cap" -> capitalize(currentLine);
            case "rev" -> reverse(currentLine, inputtype);
            case "neg" -> negate(currentLine, inputtype);
            default -> throw new RuntimeException("Operation not supported ...");
        };
    }

    Number reverse(String data, String inputType) {
        return switch (inputType) {
            case "integer" -> reverseInteger(NumberUtils.parseNumber(data, Integer.class));
            case "double" -> reverseDouble(NumberUtils.parseNumber(data, Double.class));
            default -> throw new RuntimeException("Type not supported ...");
        };
    }

    int reverseInteger(Number number) {
        String reversed;
        if (number.toString().startsWith(DASH_SEPARATOR)) {
            reversed = DASH_SEPARATOR + new StringBuffer(negate(number.toString(), "integer").toString()).reverse();
        } else {
            reversed = new StringBuffer(number.toString()).reverse().toString();
        }
        return Integer.parseInt(reversed);
    }

    double reverseDouble(Number number) {
        String reversed;
        if (number.toString().startsWith(DASH_SEPARATOR)) {
            reversed = DASH_SEPARATOR + new StringBuffer(negate(number.toString(), "double").toString()).reverse();
        } else {
            reversed = new StringBuffer(number.toString()).reverse().toString();
        }
        return Double.parseDouble(reversed);
    }

    Number negate(String data, String inputType) {
        return switch (inputType) {
            case "integer" -> Math.negateExact(NumberUtils.parseNumber(data, Integer.class));
            case "double" -> -NumberUtils.parseNumber(data, Double.class);
            default -> throw new RuntimeException("Type not supported ...");
        };
    }

    String capitalize(String data) {
        return Arrays
                .stream(data.split(CHAR_SEPARATOR))
                .map(StringUtils::capitalize)
                .collect(Collectors.joining(CHAR_SEPARATOR));
    }
}
