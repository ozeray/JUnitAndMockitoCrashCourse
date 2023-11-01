package com.ahmet.helpers;

import com.ahmet.Strings;

public class Person {
    private String firstName;
    private String middleName;
    private String lastName;

    public Person(String firstName, String middleName, String lastName) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    public String fullName() {
        if (Strings.isBlank(middleName)) {
            return String.format("%s %s", firstName, lastName);
        }
        return String.format("%s %s %s", firstName, middleName, lastName);
    }
}
