package com.aston.functionalClasses;

import com.aston.functionalClasses.ParallelBubbleSort;

import java.util.Comparator;
import java.util.List;
import java.util.function.ToIntFunction;

public class ParallelBubbleSortStrategy<T> implements SortStrategy<T> {
    @Override
    public void sort(List<T> list, ToIntFunction<T> keyExtractor, Comparator<? super T> comparator) {
        ParallelBubbleSort.parallelBubbleSort(list, comparator);
    }
}
