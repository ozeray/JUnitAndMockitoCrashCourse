package com.ahmet;

import com.ahmet.helpers.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.*;

import java.time.LocalDate;
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

    @ParameterizedTest
    @CsvSource({"2018/12/25,2018", "2019/02/11,2019"})
    public void getYear_ShouldWorkAsExpected(@ConvertWith(SlashyDateConverter.class) LocalDate date, int expected) {
        assertEquals(expected, date.getYear());
    }

    @ParameterizedTest
    @CsvSource({"Isaac,,Newton,Isaac Newton", "Charles,Robert,Darwin,Charles Robert Darwin"})
    public void fullName_ShouldGenerateTheExpectedFullName(ArgumentsAccessor accessor) {
        String firstName = accessor.getString(0);
        String middleName = accessor.getString(1);
        String lastName = accessor.getString(2);
        String expected = accessor.getString(3);

        Person person = new Person(firstName, middleName, lastName);
        assertEquals(expected, person.fullName());
    }

    @ParameterizedTest(name = "{index} - {arguments} - Expected:{0}, First Name:{1}, Middle:{2}, Last Name:{3}")
    @CsvSource({"Isaac Newton,Isaac,,Newton", "Charles Robert Darwin,Charles,Robert,Darwin"})
    public void fullName_ShouldGenerateTheExpectedFullName_UsingAggregator(String expected, @AggregateWith(PersonAggregator.class) Person person) {
        assertEquals(expected, person.fullName());
    }

}
