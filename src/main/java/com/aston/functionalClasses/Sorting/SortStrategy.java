package com.aston.functionalClasses.Sorting;

import java.util.Comparator;
import java.util.List;
import java.util.function.ToIntFunction;

public interface SortStrategy<T> {
    void sort(List<T> list, ToIntFunction<T> keyExtractor, Comparator<? super T> comparator);
}