package com.aston.functionalClasses.Sorting;

import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class QuickSortTest {

    @Test
    void testQuickSortMatchesCollectionsSort() {
        // Создаем случайный список чисел
        List<Integer> original = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < 50; i++) {
            original.add(rand.nextInt(100)); // числа от 0 до 99
        }

        // Копируем список и сортируем стандартно
        List<Integer> expected = new ArrayList<>(original);
        Collections.sort(expected);

        // Сортируем первый список нашей быстрой сортировкой
        QuickSort.quickSort(original, Integer::compareTo);

        // Проверяем, что отсортированные списки совпадают
        assertEquals(expected, original, "Списки должны совпадать после сортировки");
    }
}
