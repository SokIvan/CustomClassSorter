package com.aston.functionalClasses.Sorting;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.ToIntFunction;

/**
 * Параллельная пузырьковая сортировка только для чётных элементов списка.
 * Нечётные остаются на своих местах.
 */
public class ParallelEvenBubbleSort {

    private static final int THREAD_COUNT = 2;

    public static <T> void parallelEvenBubbleSort(List<T> list,
                                                  ToIntFunction<T> keyExtractor,
                                                  Comparator<? super T> comparator) {
        if (list == null || list.size() < 2) return;

        // 1️⃣ Собираем чётные элементы
        List<T> evens = new ArrayList<>();
        for (T el : list) {
            if ((keyExtractor.applyAsInt(el) & 1) == 0) {
                evens.add(el);
            }
        }
        if (evens.size() < 2) return;

        // 2️⃣ Разбиваем чётные на подсписки и сортируем в потоках
        int chunkSize = Math.max(1, evens.size() / THREAD_COUNT);
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
        List<Future<List<T>>> futures = new ArrayList<>();

        for (int i = 0; i < evens.size(); i += chunkSize) {
            int from = i;
            int to = Math.min(i + chunkSize, evens.size());
            List<T> sub = new ArrayList<>(evens.subList(from, to));

            futures.add(executor.submit(() -> {
                bubbleSort(sub, comparator);
                return sub;
            }));
        }

        // 3️⃣ Собираем отсортированные части
        List<T> mergedEvens = new ArrayList<>();
        for (Future<List<T>> future : futures) {
            try {
                mergedEvens.addAll(future.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();

        // 4️⃣ Финальная глобальная сортировка всех чётных
        bubbleSort(mergedEvens, comparator);

        // ⚠️ 5️⃣ Правильная вставка: заменяем чётные элементы ПО ПОЗИЦИЯМ появления чётных
        int evenPos = 0;
        for (int i = 0; i < list.size(); i++) {
            int value = keyExtractor.applyAsInt(list.get(i));
            if ((value & 1) == 0 && evenPos < mergedEvens.size()) {
                list.set(i, mergedEvens.get(evenPos++));
            }
        }
    }

    // 🔹 Вспомогательная пузырьковая сортировка
    private static <T> void bubbleSort(List<T> list, Comparator<? super T> comparator) {
        int n = list.size();
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (comparator.compare(list.get(j), list.get(j + 1)) > 0) {
                    Collections.swap(list, j, j + 1);
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }
}
