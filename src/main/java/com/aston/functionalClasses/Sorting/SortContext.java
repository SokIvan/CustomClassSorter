package com.aston.functionalClasses.Sorting;

import java.util.Comparator;
import java.util.List;
import java.util.function.ToIntFunction;

public class SortContext<T> {
    private SortStrategy<T> strategy;

    public void setStrategy(SortStrategy<T> strategy) {
        this.strategy = strategy;
    }

    public void executeSort(List<T> list, ToIntFunction<T> keyExtractor, Comparator<? super T> comparator) {
        if (strategy == null) {
            throw new IllegalStateException("Стратегия сортировки не выбрана!");
        }
        strategy.sort(list, keyExtractor, comparator);
    }
}

