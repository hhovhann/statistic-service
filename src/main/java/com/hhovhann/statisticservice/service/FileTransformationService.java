package com.hhovhann.statisticservice.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

@Slf4j
@Component
public class FileTransformationService {

    public List<String> readFrom(String filePath) {
        List<String> lines = new ArrayList<>();
        try {
            lines = FileUtils.readLines(new File(filePath), UTF_8);
        } catch (IOException e) {
            log.error(" An I/O error occurs.", e);
        }
        return lines;
    }

    public void writeTo(String filePath, List<Object> statistics) {
        try {
            FileUtils.writeLines(new File(filePath), statistics);
        } catch (IOException e) {
            log.error(" An I/O error occurs.", e);
        }
    }
}
