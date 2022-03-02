package com.hhovhann.statisticservice.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
public class CommandLineParserService {
    private static final String EMPTY_STRING = "";
    private static final String LINE_SEPARATOR = "-";

    private final List<String> args;
    private final HashMap<String, List<String>> map = new HashMap<>();

    public CommandLineParserService(String[] arguments) {
        this.args = Arrays.asList(arguments);
        map();
    }

    /***
     *  Return argument currentLine for particular argument name
     */
    public String getArgumentValues(String argumentName) {
        return map.getOrDefault(argumentName, null).get(0);
    }

    /***
     * Map the argument names with the values
     */
    public void map() {
        for (String arg : args) {
            if (arg.startsWith(LINE_SEPARATOR)) {
                List<String> argumentValues = new ArrayList<>();
                int index = 1;
                while (args.indexOf(arg) + index != args.size() && !args.get(args.indexOf(arg) + index).startsWith(LINE_SEPARATOR)) {
                    argumentValues.add(args.get(args.indexOf(arg) + index));
                    index++;
                }
                map.put(arg.replace(LINE_SEPARATOR, EMPTY_STRING), argumentValues);
            }
        }
    }
}
