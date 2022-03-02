package com.hhovhann.statisticservice.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

@Slf4j
@Service
public class FileTransformationService {

    public List<String> readFrom(String fileName) {
        List<String> lines = new ArrayList<>();
        try {
            File file = new File("input" + "/" + fileName);
            if (file.exists()) {
                lines = FileUtils.readLines(file, UTF_8);
            } else {
                throw new IllegalArgumentException("File " + fileName + " doesn't exist!");
            }
            log.info("Statistics data successfully read from file.");
        } catch (IOException e) {
            log.error(" An I/O error occurs.", e);
        }
        return lines;
    }


    public void writeTo(String filePath, List<Object> statistics) {
        try {
            FileUtils.writeLines(new File("output" + "/" + filePath), statistics);
            log.info("Statistics data successfully added to file.");
        } catch (IOException e) {
            log.error(" An I/O error occurs.", e);
        }
    }
}
