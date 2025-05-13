package com.soukenka.cardealer.core.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.lang.NonNull;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * A unit test class for testing methods in the {@link StringUtils} utility class.
 *
 * @author Ing. Bc. Daniel Soukenka
 * @version 1.0
 * @created 12.05.2025
 */
class StringUtilsTest {

    @NonNull
    private static Stream<Arguments> testRemoveSuffix() {
        return Stream.of(
                Arguments.of("helloWorld", "World", "hello", "The suffix should be removed correctly from the string"),
                Arguments.of("helloWorld", "world", "helloWorld", "The method should be case-sensitive and not remove mismatched suffixes"),
                Arguments.of("helloWorld", "", "helloWorld", "An empty suffix should not alter the string"),
                Arguments.of("helloWorld", "helloWorld", "", "The entire string should be removed if it matches the suffix exactly"),
                Arguments.of("helloWorld", "Test", "helloWorld", "If the suffix is not present, the string should remain unchanged"),
                Arguments.of("", "Test", "", "An empty string should remain empty regardless of the suffix"),
                Arguments.of("", "", "", "Both an empty string and an empty suffix should result in an empty string"),
                Arguments.of(null, "Test", null, "A null string should return null regardless of the suffix"),
                Arguments.of("helloWorld", null, "helloWorld", "A null suffix should leave the string unchanged"),
                Arguments.of(null, null, null, "Both a null string and a null suffix should result in null")
        );
    }

    @NonNull
    private static Stream<Arguments> testConvertCamelToSnake() {
        return Stream.of(
                Arguments.of("camelCase", "camel_case", "The simple camelCase string should be converted to snake_case"),
                Arguments.of("thisIsATest", "this_is_a_test", "The camelCase string with multiple words should be converted to snake_case"),
                Arguments.of("ALLCaps", "all_caps", "The method should handle camelCase strings that start with uppercase letters"),
                Arguments.of("camelCaseWithMultipleTransitions", "camel_case_with_multiple_transitions", "The method should handle strings with multiple camel-case transitions"),
                Arguments.of("", "", "An empty string should return an empty string"),
                Arguments.of("word", "word", "A single word without camel-case transitions should return the same word"),
                Arguments.of("already_snake_case", "already_snake_case", "The method should not modify strings that are already in snake_case"),
                Arguments.of("Case123TestWITHNumbers", "case_123_test_with_numbers", "The method should correctly handle strings containing numbers and uppercase letters"),
                Arguments.of(null, null, "A null input should return null")
        );
    }

    @DisplayName("Test camelCase to snake_case conversion")
    @ParameterizedTest
    @MethodSource
    void testConvertCamelToSnake(String input, String expected, String message) {
        String result = StringUtils.convertCamelToSnake(input);
        assertEquals(expected, result, message);
    }

    @DisplayName("Test suffix removal from string")
    @ParameterizedTest
    @MethodSource
    void testRemoveSuffix(String input, String suffix, String expected, String message) {
        String result = StringUtils.removeSuffix(input, suffix);
        assertEquals(expected, result, message);
    }
}
