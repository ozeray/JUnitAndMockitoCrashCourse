package com.ahmet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.EnumSet;

import static org.junit.jupiter.api.Assertions.*;

public class MonthTest {

    @ParameterizedTest
    @EnumSource(Month.class)
    public void getValueForAMonth_IsAlwaysBetweenOneAndTwelve(Month month) {
        int monthNumber = month.getValue();
        assertTrue(monthNumber <= 12 && monthNumber >=1);
    }

    @ParameterizedTest
    @EnumSource(value = Month.class, names = {"APRIL", "JUNE", "SEPTEMBER", "NOVEMBER"})
    public void someMonths_Are30DaysLong(Month month) {
        assertEquals(30, month.getLength(false));
    }

    @ParameterizedTest
    @CsvSource({"APRIL", "JUNE", "SEPTEMBER", "NOVEMBER"}) //Implicit conversion to Enum.
    // Types that will be implicitly converted by CsvSource:
    // UUID,
    // Locale,
    // LocalDate, LocalTime, LocalDateTime, Year, Month, etc.,
    // File, Path,
    // URL, URI,
    // Enum subclasses
    public void someMonths_Are30DaysLong_Csv(Month month) {
        assertEquals(30, month.getLength(false));
    }

    @ParameterizedTest
    @EnumSource(value = Month.class, names = {"FEBRUARY", "APRIL", "JUNE", "SEPTEMBER", "NOVEMBER"}, mode = EnumSource.Mode.EXCLUDE)
    public void exceptFourMonths_OthersAre31DaysLong(Month month) {
        assertEquals(31, month.getLength(false));
    }

    @ParameterizedTest
    @EnumSource(value = Month.class, names = ".+BER", mode = EnumSource.Mode.MATCH_ANY)
    public void fourMonths_AreEndingWithBer(Month month) {
        EnumSet<Month> months = EnumSet.of(Month.SEPTEMBER, Month.OCTOBER, Month.NOVEMBER, Month.DECEMBER);
        assertTrue(months.contains(month));
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    public void february_NumberOfDaysDependOnLeapYear(boolean isLeapYear) {
        assertEquals(isLeapYear ? 29 : 28, Month.FEBRUARY.getLength(isLeapYear));
    }

}
