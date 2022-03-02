package com.hhovhann.statisticservice.service;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FileTransformationServiceTest {

    @Autowired FileTransformationService fileTransformationService;

    @Test
    @DisplayName("Should read from the input text file with provided path")
    void should_read_from_input_directory() {
        // given
        var fileName = "input/input1.txt";
        // when
        List<String> statistics = fileTransformationService.readFrom(fileName);
        // then
        assertEquals(statistics.size(), 3);
    }

    @Test
    @DisplayName("Should write to the output text file with provided path")
    void should_write_to_output_directory() {
        // given
        var fileName = "output/output1.txt";
        // when
        fileTransformationService.writeTo(fileName, List.of("Hello World","Foo","Bye"));
        // then
        List<String> statistics = fileTransformationService.readFrom(fileName);
        assertEquals(statistics.size(), 3);
    }
}