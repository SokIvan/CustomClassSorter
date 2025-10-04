package com.aston.functionalClasses.Sorting;

import java.util.Comparator;
import java.util.List;
import java.util.function.ToIntFunction;

public class ParallelEvenBubbleSortStrategy<T> implements SortStrategy<T> {
    @Override
    public void sort(List<T> list, ToIntFunction<T> keyExtractor, Comparator<? super T> comparator) {
        ParallelEvenBubbleSort.parallelEvenBubbleSort(list, keyExtractor, comparator);
    }
}
