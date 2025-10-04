package com.aston.functionalClasses;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.ToIntFunction;

public class QuickSortEven {

    /**
     * Реализация интерфейса SortStrategy для быстрой сортировки элементов с четным числовым значением поля
     * @param list Список объектов для сортировки. Не должен быть Immutable
     * @param keyExtractor Геттер для поля класса T, по которому будет проводиться проверка на четность
     * @param comparator Компаратор для сравнения объектов
     * @param <T> Тип объектов в списке
     * @author Lomkin
     */
    public static <T> void quickSortEven(List<T> list, ToIntFunction<T> keyExtractor, Comparator<? super T> comparator){

        List<T> evens = new ArrayList<>();

        for(T el: list){
            if((keyExtractor.applyAsInt(el) & 1) == 0)
                evens.add(el);
        }

        QuickSort.quickSort(evens, comparator);

        int idx = 0;
        for(int i = 0; i < list.size(); i++){
            if((keyExtractor.applyAsInt(list.get(i)) & 1) == 0)
                list.set(i, evens.get(idx++));
        }
    }
}
