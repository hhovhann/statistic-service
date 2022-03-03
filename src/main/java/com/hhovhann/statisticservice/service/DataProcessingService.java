package com.hhovhann.statisticservice.service;

import com.hhovhann.statisticservice.data.Statistic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Service
public class DataProcessingService {

    private final static String QUOTA_SEPARATOR = ",";
    private final DataTransformationService dataTransformationService;
    private final FileTransformationService fileTransformationService;
    private static ExecutorService executorService;

    public DataProcessingService(DataTransformationService dataTransformationService, FileTransformationService fileTransformationService) {
        this.dataTransformationService = dataTransformationService;
        this.fileTransformationService = fileTransformationService;
    }

    public void doStatisticProcess(CommandLineParserService clp) {
        List<Object> statistics = new ArrayList<>();
        // Step 1: Run fixed thread pool based on threads CLI argument
        executorService = Executors.newFixedThreadPool(Integer.parseInt(clp.getArgumentValues("threads")));
        // Step 2: Execute read and transformation async task
        executorService.execute(() -> {
            // Step 3: Read statistic data from file
            List<String> inputLines = fileTransformationService.readFrom("input" + "/" + clp.getArgumentValues("input"));
            for (String currentLine : inputLines) {
                Object resultData = null;
                String[] operations = clp.getArgumentValues("operations").split(QUOTA_SEPARATOR);
                for (String operation : operations) {
                    // Step 4: Transform each statistic line
                    resultData = dataTransformationService.transformData(new Statistic(currentLine, operation, clp.getArgumentValues("inputtype")));
                }
                statistics.add(resultData);
                log.info(String.format("Starting expensive task with current statistic: %s, thread id: %s.", currentLine, Thread.currentThread().getId()));
            }
            // Write statistic data to output file
            if (Objects.isNull(clp.getArgumentValues("output"))) {
                statistics.forEach(current -> log.info("Output Statistic Data: {}", current));
            } else {
                fileTransformationService.writeTo("output" + "/" + clp.getArgumentValues("output"), statistics);
            }
        });
        executorService.shutdown();
    }
}
