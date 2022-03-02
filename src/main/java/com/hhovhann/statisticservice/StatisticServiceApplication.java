package com.hhovhann.statisticservice;

import com.hhovhann.statisticservice.service.CommandLineParserService;
import com.hhovhann.statisticservice.service.DataTransformationService;
import com.hhovhann.statisticservice.service.FileTransformationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@SpringBootApplication
public class StatisticServiceApplication implements CommandLineRunner {

    @Autowired
    DataTransformationService dataTransformationService;
    @Autowired
    FileTransformationService fileTransformationService;

    ExecutorService executorService;

    public static void main(String[] args) {
        log.info("Statistic Service Application Started");
        SpringApplication.run(StatisticServiceApplication.class, args);
        log.info("Statistic Service Application Finished");
    }

    @Override
    public void run(String... args) {
        if (args.length == 0)
            return;
        CommandLineParserService clp = new CommandLineParserService(args);
        executorService = Executors.newFixedThreadPool(Integer.parseInt(clp.getArgumentValue("threads")));

        // Step 1: Read input file
        List<Object> outputLines = new ArrayList<>();
        List<String> inputLines = fileTransformationService.readFrom(clp.getArgumentValue("input"));
        for (String currentLine : inputLines) {
            executorService.submit(() -> {
                // Step 2: Transform each line
                Object resultData = null;
                for (String operation : clp.getArgumentValues("operations")) {
                    String inputtype = clp.getArgumentValue("inputtype");
                    resultData = switch (operation) {
                        case "cap" -> dataTransformationService.capitalize(currentLine);
                        case "rev" -> dataTransformationService.reverse(currentLine, inputtype);
                        case "neg" -> dataTransformationService.negate(currentLine, inputtype);
                        default -> throw new RuntimeException("Operation not supported ...");
                    };
                }

                // Step 3: add to list
                outputLines.add(resultData);
            });
        }

        // Step 4: Write result
        fileTransformationService.writeTo(clp.getArgumentValue("output"), outputLines);

        // Wait for tasks to complete.
        executorService.shutdown();
    }
}
