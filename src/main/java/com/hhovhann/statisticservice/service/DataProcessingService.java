package com.hhovhann.statisticservice.service;

import com.hhovhann.statisticservice.data.Statistic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    public List<Object> doStatisticProcess(CommandLineParserService clp) {
        List<Object> outputLines = new ArrayList<>();
        // Step 1: Run fixed thread pool based on threads CLI argument
        executorService = Executors.newFixedThreadPool(Integer.parseInt(clp.getArgumentValues("threads")));
        // Step 2: Read input file
        List<String> inputLines = fileTransformationService.readFrom("input" + "/" + clp.getArgumentValues("input"));
        for (String currentLine : inputLines) {
            executorService.execute(() -> {
                Object resultData = null;
                String[] operations = clp.getArgumentValues("operations").split(QUOTA_SEPARATOR);
                for (String operation : operations) {
                    // Step 3: Transform each line
                    resultData = dataTransformationService.transformData(new Statistic(currentLine, operation, clp.getArgumentValues("inputtype")));
                }
                outputLines.add(resultData);
                log.info(String.format("Starting expensive task with current statistic: %s, thread id: %s.", currentLine, Thread.currentThread().getId()));
            });
        }
        executorService.shutdown();
        return outputLines;
    }
}
