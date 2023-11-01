package com.ahmet;

import com.ahmet.helpers.BlankStringsArgumentProvider;
import com.ahmet.helpers.VariableSource;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class StringsTest {

    @ParameterizedTest(name = "{index} - {0} is blank")
    @ValueSource(strings = {"   ", " ", "\t", "\n", "\r"})
//  @NullAndEmptySource =  @NullSource + @EmptySource
    @NullAndEmptySource
    public void isBlank_ShouldReturnTrueForNullOrBlankStrings(String input) {
        assertTrue(Strings.isBlank(input));
    }

    @ParameterizedTest
    @CsvSource({"text,TEXT", "teST,TEST", "Java,JAVA"})
    public void toUpperCase_ShouldGenerateExpectedUppercaseValue(String input, String expected) {
        assertEquals(expected, input.toUpperCase());
    }

    @ParameterizedTest
    @CsvSource(value = {"text:TEXT", "teST:TEST", "Java:JAVA"}, delimiter = ':')
    public void toUpperCase_ShouldGenerateExpectedUppercaseValue_WithDelimiter(String input, String expected) {
        assertEquals(expected, input.toUpperCase());
    }

    @ParameterizedTest
    @CsvFileSource(files = "./src/test/resources/data.csv", numLinesToSkip = 1)
    public void toUpperCase_ShouldGenerateExpectedUppercaseValue_WithCsvFile(String input, String expected) {
        assertEquals(expected, input.toUpperCase());
    }

    private static Stream<Arguments> stringsForBlank() {
        return Stream.of(Arguments.of(null, true), Arguments.of("", true),
                         Arguments.of(" ", true), Arguments.of("sadasd", false));
    }

    @ParameterizedTest
    @MethodSource("stringsForBlank")
    public void isBlank_ShouldReturnTrueForNullOrBlankStringsAndFalseForNonBlankStrings(String input, boolean expected) {
        assertEquals(expected, Strings.isBlank(input));
    }

    private static Stream<String> isBlank_ShouldReturnTrueForNullOrBlankStrings_FromMethod() {
        return Stream.of(null, "", "\t");
    }

    @ParameterizedTest
    @MethodSource
    public void isBlank_ShouldReturnTrueForNullOrBlankStrings_FromMethod(String input) {
        assertTrue(Strings.isBlank(input));
    }

    @ParameterizedTest
    @MethodSource("com.ahmet.StringParams#blankStrings")
    public void isBlank_ShouldReturnTrueForNullOrBlankStrings_FromExternalMethod(String input) {
        assertTrue(Strings.isBlank(input));
    }

    @ParameterizedTest
    @ArgumentsSource(BlankStringsArgumentProvider.class)
    public void isBlank_ShouldReturnTrueForNullOrBlankStringsAndFalseForNonBlankStrings_ByArgumentsProvider(String input) {
        assertTrue(Strings.isBlank(input));
    }

    private static Stream<Arguments> arguments = Stream.of(
            Arguments.of("", true),
                    Arguments.of(" ", true),
                    Arguments.of("\t", true),
                    Arguments.of("sada", false));

    @ParameterizedTest
    @VariableSource("arguments")
    public void isBlank_ShouldReturnTrueForNullOrBlankStringsAndFalseForNonBlankStrings_FromAnnotation
            (String input, boolean expected) {
        assertEquals(expected, Strings.isBlank(input));
    }


}
