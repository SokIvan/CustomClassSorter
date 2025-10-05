package com.aston.functionalClasses.Sorting;

import java.util.*;
import java.util.concurrent.*;

public class ParallelBubbleSort {

    // Основной метод: сортировка списка в несколько потоков
    public static <T> void parallelBubbleSort(List<T> list, Comparator<? super T> comparator) {
        if (list == null || list.size() < 2) return;

        int n = list.size();
        int processors = Runtime.getRuntime().availableProcessors(); // количество доступных ядер
        int threadCount = Math.min(processors, n); // не больше элементов
        if (threadCount < 2) threadCount = 2; // минимум 2 потока

        ExecutorService executor = Executors.newFixedThreadPool(threadCount);

        try {
            // Определяем размер "куска" для одного потока
            int chunkSize = (int) Math.ceil((double) n / threadCount);
            List<Future<List<T>>> futures = new ArrayList<>();

            // Разделяем список на подсписки и сортируем их в отдельных потоках
            for (int i = 0; i < n; i += chunkSize) {
                int from = i;
                int to = Math.min(i + chunkSize, n);

                List<T> subList = new ArrayList<>(list.subList(from, to));
                futures.add(executor.submit(() -> {
                    bubbleSort(subList, comparator);
                    return subList;
                }));
            }

            // Собираем результаты
            List<List<T>> sortedParts = new ArrayList<>();
            for (Future<List<T>> f : futures) {
                sortedParts.add(f.get());
            }

            // Сливаем отсортированные части
            List<T> merged = mergeAll(sortedParts, comparator);

            // Копируем обратно
            for (int i = 0; i < n; i++) {
                list.set(i, merged.get(i));
            }

        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        } finally {
            executor.shutdown();
        }
    }

    // Обычная пузырьковая сортировка
    private static <T> void bubbleSort(List<T> list, Comparator<? super T> comparator) {
        boolean swapped;
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (comparator.compare(list.get(j), list.get(j + 1)) > 0) {
                    T tmp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, tmp);
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    // Слияние всех отсортированных частей
    private static <T> List<T> mergeAll(List<List<T>> parts, Comparator<? super T> comparator) {
        if (parts.isEmpty()) return Collections.emptyList();

        // Поочередное слияние всех частей
        List<T> merged = parts.get(0);
        for (int i = 1; i < parts.size(); i++) {
            merged = merge(merged, parts.get(i), comparator);
        }
        return merged;
    }

    // Слияние двух списков (аналог merge sort)
    private static <T> List<T> merge(List<T> left, List<T> right, Comparator<? super T> comparator) {
        List<T> result = new ArrayList<>();
        int i = 0, j = 0;

        while (i < left.size() && j < right.size()) {
            if (comparator.compare(left.get(i), right.get(j)) <= 0) {
                result.add(left.get(i++));
            } else {
                result.add(right.get(j++));
            }
        }
        while (i < left.size()) result.add(left.get(i++));
        while (j < right.size()) result.add(right.get(j++));
        return result;
    }
}


