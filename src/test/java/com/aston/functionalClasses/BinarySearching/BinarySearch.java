package com.aston.functionalClasses.BinarySearching;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTest {

    @Test
    void testBinarySearchFound() {
        BinarySearch<Integer> search = new BinarySearch<>();
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(5);
        list.add(7);

        int index = search.binarySearch(list, 5, 0, list.size() - 1, Integer::compareTo);
        assertEquals(2, index);
    }

    @Test
    void testBinarySearchNotFound() {
        BinarySearch<Integer> search = new BinarySearch<>();
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(5);

        int index = search.binarySearch(list, 10, 0, list.size() - 1, Integer::compareTo);
        assertEquals(-1, index);
    }

    @Test
    void testBinarySearchEmptyList() {
        BinarySearch<Integer> search = new BinarySearch<>();
        ArrayList<Integer> list = new ArrayList<>();

        assertThrows(IndexOutOfBoundsException.class, () -> {
            search.binarySearch(list, 1, 0, list.size() - 1, Integer::compareTo);
        });
    }
}
