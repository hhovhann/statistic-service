package com.hhovhann.statisticservice.service;

import com.hhovhann.statisticservice.data.Statistic;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataProcessingService {

    private final static String QUOTA_SEPARATOR = ",";
    private final DataTransformationService dataTransformationService;
    private final FileTransformationService fileTransformationService;

    public DataProcessingService(DataTransformationService dataTransformationService, FileTransformationService fileTransformationService) {
        this.dataTransformationService = dataTransformationService;
        this.fileTransformationService = fileTransformationService;
    }

    public List<Object> doStatisticProcess(CommandLineParserService clp) {
        // Step 1: Read input file
        List<Object> outputLines = new ArrayList<>();
        List<String> inputLines = fileTransformationService.readFrom("input" + "/" + clp.getArgumentValues("input"));
        for (String currentLine : inputLines) {
            // Step 2: Transform each line
            Object resultData = null;
            String[] operations = clp.getArgumentValues("operations").split(QUOTA_SEPARATOR);
            for (String operation : operations) {
                resultData = dataTransformationService.transformData(new Statistic(currentLine, operation, clp.getArgumentValues("inputtype")));
            }
            outputLines.add(resultData);
        }
        return outputLines;
    }
}
