package com.aston.functionalClasses.ThreadСounter;
import java.util.*;
import java.util.concurrent.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class MultiThreadedCounter {

    /**
     * ПЕРВЫЙ МЕТОД: Поиск по точному совпадению объекта через equals()
     */
    public static <T> long countOccurrences(List<T> collection, T target)
            throws InterruptedException, ExecutionException {

        if (collection == null || collection.isEmpty()) {
            return 0;
        }

        int availableProcessors = Runtime.getRuntime().availableProcessors();
        int chunkSize = Math.max(1, collection.size() / availableProcessors);

        ExecutorService executor = Executors.newFixedThreadPool(availableProcessors);

        try {
            List<Future<Long>> futures = new ArrayList<>();

            for (int i = 0; i < collection.size(); i += chunkSize) {
                int end = Math.min(i + chunkSize, collection.size());
                List<T> subList = collection.subList(i, end);

                Callable<Long> task = () -> {
                    long count = 0;
                    for (T element : subList) {
                        if (safeEquals(element, target)) {
                            count++;
                        }
                    }
                    return count;
                };

                futures.add(executor.submit(task));
            }

            long totalCount = 0;
            for (Future<Long> future : futures) {
                totalCount += future.get();
            }

            return totalCount;

        } finally {
            executor.shutdown();
        }
    }

    /**
     * ВТОРОЙ МЕТОД: Поиск по имени поля и его значению через рефлексию
     */
    public static <T> long countOccurrencesByField(List<T> collection,
                                                   String fieldName,
                                                   String fieldValue)
            throws InterruptedException, ExecutionException {

        if (collection == null || collection.isEmpty()) {
            return 0;
        }

        int availableProcessors = Runtime.getRuntime().availableProcessors();
        int chunkSize = Math.max(1, collection.size() / availableProcessors);

        ExecutorService executor = Executors.newFixedThreadPool(availableProcessors);

        try {
            List<Future<Long>> futures = new ArrayList<>();

            for (int i = 0; i < collection.size(); i += chunkSize) {
                int end = Math.min(i + chunkSize, collection.size());
                List<T> subList = collection.subList(i, end);

                Callable<Long> task = () -> {
                    long count = 0;
                    for (T element : subList) {
                        if (hasFieldWithValue(element, fieldName, fieldValue)) {
                            count++;
                        }
                    }
                    return count;
                };

                futures.add(executor.submit(task));
            }

            long totalCount = 0;
            for (Future<Long> future : futures) {
                totalCount += future.get();
            }

            return totalCount;

        } finally {
            executor.shutdown();
        }
    }

    /**
     * Безопасное сравнение объектов
     */
    private static <T> boolean safeEquals(T obj1, T obj2) {
        if (obj1 == null && obj2 == null) return true;
        if (obj1 == null || obj2 == null) return false;
        return obj1.equals(obj2);
    }

    /**
     * Проверка поля через рефлексию (ищет геттер, если нет - прямое поле)
     */
    private static <T> boolean hasFieldWithValue(T object, String fieldName, String expectedValue) {
        if (object == null) return false;

        try {
            // Сначала пробуем найти геттер
            String getterName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            try {
                Method getter = object.getClass().getMethod(getterName);
                Object actualValue = getter.invoke(object);
                return actualValue != null && actualValue.toString().equals(expectedValue);
            } catch (NoSuchMethodException e) {
                // Если геттера нет, ищем поле напрямую
                Field field = object.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);
                Object actualValue = field.get(object);
                return actualValue != null && actualValue.toString().equals(expectedValue);
            }

        } catch (Exception e) {
            return false;
        }
    }
}