package com.aston.functionalClasses;

import java.util.List;
import java.util.Comparator;

public class BubbleSort {

    /**
     * Универсальная пузырьковая сортировка для любого списка объектов с Comparator
     * @param list список объектов
     * @param comparator компаратор для сравнения объектов
     * @param <T> тип объектов
     */
    public static <T> void bubbleSort(List<T> list, Comparator<? super T> comparator) {
        if (list == null || list.size() < 2) return;

        boolean swapped;
        int n = list.size();

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                T a = list.get(j);
                T b = list.get(j + 1);

                if (comparator.compare(a, b) > 0) {
                    list.set(j, b);
                    list.set(j + 1, a);
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }
}

