package com.ahmet;

public enum Month {
    JANUARY(1),
    FEBRUARY(2),
    MARCH(3),
    APRIL(4),
    MAY(5),
    JUNE(6),
    JULY(7),
    AUGUST(8),
    SEPTEMBER(9),
    OCTOBER(10),
    NOVEMBER(11),
    DECEMBER(12);

    private final int value;

    Month(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public int getLength(boolean isLeapYear) {
        return switch (this) {
            case JANUARY, MARCH, MAY, JULY, AUGUST, OCTOBER, DECEMBER -> 31;
            case FEBRUARY -> isLeapYear ? 29 : 28;
            case APRIL, JUNE, SEPTEMBER, NOVEMBER -> 30;
        };
    }
}
