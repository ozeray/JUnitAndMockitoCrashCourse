package com.ahmet.junit5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

public class ListTest {

    @Spy
    private List<String> spyList = new ArrayList<>();

    @Mock
    private ArrayList<String> mockList = new ArrayList<>();

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void test() {
        spyList.add("asda");
        spyList.add("asda2");

        doReturn(3).when(spyList).size();
        assertSame(3, spyList.size());
    }

    @Test
    public void spyRealMethodCalled() {
        assertThrows(IndexOutOfBoundsException.class, () -> spyList.get(0));
    }

    @Test
    public void betterWayOfUsingSpy() {
        when(mockList.get(0)).thenReturn("Demo");
        when(mockList.size()).thenCallRealMethod();

        mockList.add("Deneme");

        assertSame("Demo", mockList.get(0)); // verified

        assertSame(0, mockList.size()); //add method not effective...
    }

}
