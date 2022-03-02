package com.hhovhann.statisticservice.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FileTransformationServiceTest {

    @Autowired FileTransformationService fileTransformationService;

    @Test
    void should_read_from_input_directory() {
        // given
        var fileName = "input/input1.txt";
        // when
        List<String> statistics = fileTransformationService.readFrom(fileName);
        // then
        assertEquals(statistics.size(), 3);
    }

    @Test
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