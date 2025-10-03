package com.aston.functionalClasses;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.ToIntFunction;

public class ParallelEvenBubbleSort {

    private static final int THREADS = 2; // минимум 2 потока

    /**
     * Многопоточная пузырьковая сортировка:
     * - элементы с чётным значением keyExtractor сортируются по comparator
     * - элементы с нечётным значением остаются на своих местах
     */
    public static <T> void parallelEvenBubbleSort(
            List<T> list,
            ToIntFunction<T> keyExtractor,
            Comparator<? super T> comparator
    ) {
        if (list == null || list.size() < 2) return;

        int n = list.size();
        int mid = n / 2;

        // Разбиваем список на две половины
        List<T> left = new ArrayList<>(list.subList(0, mid));
        List<T> right = new ArrayList<>(list.subList(mid, n));

        ExecutorService executor = Executors.newFixedThreadPool(THREADS);

        try {
            // Запускаем сортировки в потоках
            Future<?> f1 = executor.submit(() -> bubbleSortEvenOnly(left, keyExtractor, comparator));
            Future<?> f2 = executor.submit(() -> bubbleSortEvenOnly(right, keyExtractor, comparator));

            // ждём завершения
            f1.get();
            f2.get();

            // Сливаем обратно
            List<T> merged = merge(left, right, keyExtractor, comparator);

            for (int i = 0; i < n; i++) {
                list.set(i, merged.get(i));
            }

        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        } finally {
            executor.shutdown();
        }
    }

    /**
     * Обычная пузырьковая сортировка только по чётным значениям
     */
    private static <T> void bubbleSortEvenOnly(
            List<T> list,
            ToIntFunction<T> keyExtractor,
            Comparator<? super T> comparator
    ) {
        int n = list.size();
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                T a = list.get(j);
                T b = list.get(j + 1);

                int keyA = keyExtractor.applyAsInt(a);
                int keyB = keyExtractor.applyAsInt(b);

                // сравниваем только чётные
                if (keyA % 2 == 0 && keyB % 2 == 0) {
                    if (comparator.compare(a, b) > 0) {
                        list.set(j, b);
                        list.set(j + 1, a);
                        swapped = true;
                    }
                }
            }
            if (!swapped) break;
        }
    }

    /**
     * Слияние двух отсортированных частей (учитываем чётность)
     */
    private static <T> List<T> merge(
            List<T> left,
            List<T> right,
            ToIntFunction<T> keyExtractor,
            Comparator<? super T> comparator
    ) {
        List<T> result = new ArrayList<>();
        int i = 0, j = 0;

        while (i < left.size() && j < right.size()) {
            T a = left.get(i);
            T b = right.get(j);

            int keyA = keyExtractor.applyAsInt(a);
            int keyB = keyExtractor.applyAsInt(b);

            if (keyA % 2 != 0) { // нечётный → остаётся на месте
                result.add(a);
                i++;
            } else if (keyB % 2 != 0) { // нечётный → остаётся на месте
                result.add(b);
                j++;
            } else {
                if (comparator.compare(a, b) <= 0) {
                    result.add(a);
                    i++;
                } else {
                    result.add(b);
                    j++;
                }
            }
        }

        while (i < left.size()) result.add(left.get(i++));
        while (j < right.size()) result.add(right.get(j++));
        return result;
    }
}
