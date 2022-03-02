package com.hhovhann.statisticservice.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

@Slf4j
@Service
public class FileTransformationService {

    public List<String> readFrom(String fileName) {
        List<String> lines = new ArrayList<>();
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            URL resource = classLoader.getResource(fileName);
            if (resource == null) {
                throw new IllegalArgumentException("file not found! " + fileName);
            } else {
                lines = FileUtils.readLines( new File(resource.getFile()), UTF_8);
            }
            log.info("Statistics data successfully read from file.");
        } catch (IOException e) {
            log.error(" An I/O error occurs.", e);
        }
        return lines;
    }

    public void writeTo(String filePath, List<Object> statistics) {
        try {
            FileUtils.writeLines(new File(filePath), statistics);
            log.info("Statistics data successfully added to file.");
        } catch (IOException e) {
            log.error(" An I/O error occurs.", e);
        }
    }
}
