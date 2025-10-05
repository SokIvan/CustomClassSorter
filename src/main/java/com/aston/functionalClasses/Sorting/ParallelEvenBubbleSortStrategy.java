package com.aston.functionalClasses.Sorting;

import java.util.Comparator;
import java.util.List;
import java.util.function.ToIntFunction;

/**
 * Стратегия сортировки пузырьком только для чётных элементов списка.
 * Нечётные элементы остаются на своих местах.
 * Работает через ThreadPool (минимум 2 потока).
 *
 * Используется в SortContext для динамического выбора алгоритма сортировки.
 *
 * @param <T> тип объектов списка
 * @author
 */
public class ParallelEvenBubbleSortStrategy<T> implements SortStrategy<T> {

    @Override
    public void sort(List<T> list, ToIntFunction<T> keyExtractor, Comparator<? super T> comparator) {
        ParallelEvenBubbleSort.parallelEvenBubbleSort(list, keyExtractor, comparator);
    }
}
