package com.hhovhann.statisticservice.service;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Component
public class CommandLineParserService {
    List<String> args;
    HashMap<String, List<String>> map = new HashMap<>();

    public CommandLineParserService(String[] arguments) {
        this.args = Arrays.asList(arguments);
        map();
    }

    // Return argument names
    public Set<String> getArgumentNames() {
        Set<String> argumentNames = new HashSet<>();
        argumentNames.addAll(map.keySet());
        return argumentNames;
    }


    // Return argument value for particular argument name
    public List<String> getArgumentValues(String argumentName) {
        if (map.containsKey(argumentName))
            return map.get(argumentName);
        else
            return null; // not good code :) this is not mine ;)
    }

    // Return argument value for particular argument name
    public String getArgumentValue(String argumentName) {
        return getArgumentValues(argumentName).get(0);
    }

    // Map the flags and argument names with the values
    public void map() {
        for (String arg : args) {
            if (arg.startsWith("-")) {
                //List of values (can be multiple)
                List<String> argumentValues = new ArrayList<>();
                int i = 1;
                while (args.indexOf(arg) + i != args.size() && !args.get(args.indexOf(arg) + i).startsWith("-")) {
                    argumentValues.add(args.get(args.indexOf(arg) + i));
                    i++;
                }
                map.put(arg.replace("-", ""), argumentValues);
            }
        }
    }
}
