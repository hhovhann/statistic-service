package com.hhovhann.statisticservice.service;

import com.hhovhann.statisticservice.data.Statistic;
import org.springframework.stereotype.Service;
import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class DataTransformationService {
    private final static String CHAR_SEPARATOR = " ";
    private final static String DASH_SEPARATOR = "-";

    /***
     * Transform statistic data against provided operations: cap -> capitalize, rev-> reverse, neg -> negate,
     */
    public Object transformData(Statistic statistic) {
        return switch (statistic.operation()) {
            case "cap" -> capitalize(statistic.currentLine());
            case "rev" -> reverse(statistic.currentLine(), statistic.inputType());
            case "neg" -> negate(statistic.currentLine(), statistic.inputType());
            default -> throw new RuntimeException("Operation not supported ...");
        };
    }
    /***
     * Reverse statistic value backwards
     */
    Number reverse(String data, String inputType) {
        return switch (inputType) {
            case "integer" -> reverseInteger(NumberUtils.parseNumber(data, Integer.class));
            case "double" -> reverseDouble(NumberUtils.parseNumber(data, Double.class));
            default -> throw new RuntimeException("Type not supported ...");
        };
    }

    /***
     * Reverse statistic integer value backwards
     * Example: 786 ->687
     */
    int reverseInteger(Number number) {
        String reversed;
        if (number.toString().startsWith(DASH_SEPARATOR)) {
            reversed = DASH_SEPARATOR + new StringBuffer(negate(number.toString(), "integer").toString()).reverse();
        } else {
            reversed = new StringBuffer(number.toString()).reverse().toString();
        }
        return Integer.parseInt(reversed);
    }

    /***
     * Reverse statistic double value backwards.
     * * Example: 786,98 -> 98.687
     */
    double reverseDouble(Number number) {
        String reversed;
        if (number.toString().startsWith(DASH_SEPARATOR)) {
            reversed = DASH_SEPARATOR + new StringBuffer(negate(number.toString(), "double").toString()).reverse();
        } else {
            reversed = new StringBuffer(number.toString()).reverse().toString();
        }
        return Double.parseDouble(reversed);
    }

    /***
     * Negate statistic integer/double values.
     * Example: 987 -> -789, 786,98 -> 98.687
     */
    Number negate(String data, String inputType) {
        return switch (inputType) {
            case "integer" -> Math.negateExact(NumberUtils.parseNumber(data, Integer.class));
            case "double" -> -NumberUtils.parseNumber(data, Double.class);
            default -> throw new RuntimeException("Type not supported ...");
        };
    }

    /***
     * Capitalize statistic string values.
     * Example: hello -> Hello, hello world -> Hello World
     */
    String capitalize(String data) {
        return Arrays
                .stream(data.split(CHAR_SEPARATOR))
                .map(StringUtils::capitalize)
                .collect(Collectors.joining(CHAR_SEPARATOR));
    }
}
