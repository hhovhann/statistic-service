package com.hhovhann.statisticservice.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(properties = {"input=input1.txt", "inputtype=string", "operations=cap", "threads=1", "output=output1.txt"})
class DataTransformationServiceTest {

    @Autowired
    DataTransformationService dataTransformationService;

    @Test
    @DisplayName("Should reverse integer value")
    void should_reverse_integer() {
        // given
        var inputType = "integer";
        var givenNumber = "5890";
        var expectedNumber = 985;
        // when
        var actualResult = dataTransformationService.reverse(givenNumber, inputType);
        // then
        assertEquals(expectedNumber, actualResult);
    }

    @Test
    @DisplayName("Should reverse double value")
    void should_reverse_double() {
        // given
        var inputType = "double";
        var givenNumber = "589.540";
        var expectedNumber = 45.985;
        // when
        var actualResult = dataTransformationService.reverse(givenNumber, inputType);
        // then
        assertEquals(expectedNumber, actualResult);
    }

    @Test
    @DisplayName("Should negate integer value")
    void should_negate_integer_number() {
        // given
        var inputType = "integer";
        var givenNumber = "589";
        var expectedNumber = -589;
        // when
        var actualResult = dataTransformationService.negate(givenNumber, inputType);
        // then
        assertEquals(expectedNumber, actualResult);
    }

    @Test
    @DisplayName("Should negate double value")
    void should_negate_double_number() {
        // given
        var inputType = "double";
        var givenNumber = "589.54";
        var expectedNumber = -589.54;
        // when
        var actualResult = dataTransformationService.negate(givenNumber, inputType);
        // then
        assertEquals(expectedNumber, actualResult);
    }

    @Test
    @DisplayName("Should capitalize one given world")
    void should_capitalize_one_word() {
        // given
        String givenWord = "one";
        String expectedWord = "One";
        // when
        String actualResult = dataTransformationService.capitalize(givenWord);
        // then
        assertEquals(expectedWord, actualResult);
    }

    @Test
    @DisplayName("Should capitalize two given worlds")
    void should_capitalize_two_words() {
        // given
        String givenWord = "hello world";
        String expectedWord = "Hello World";
        // when
        String actualResult = dataTransformationService.capitalize(givenWord);
        // then
        assertEquals(expectedWord, actualResult);
    }
}