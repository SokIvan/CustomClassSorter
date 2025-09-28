package com.aston.functionalClasses;

import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class QuickSort {

    /**
     * Кастомная быстрая сортировка для списка объектов с явным указанием компаратора
     * @param list Список объектов для сортировки. Не должен быть Immutable
     * @param comparator Компаратор для сравнения объектов
     * @param <T> Тип объектов в списке
     * @author Lomkin
     */
    public static <T> void quickSort(List<T> list, Comparator<? super T> comparator){
        quickSort(list, comparator, 0, list.size()-1);
    }

    /**
     * Кастомная быстрая сортировка для части списка объектов с явным указанием компаратора
     * @param list Список объектов для сортировки. Не должен быть Immutable
     * @param comparator Компаратор для сравнения объектов
     * @param low Нижняя граница подсписка
     * @param high Верхняя граница подсписка
     * @param <T> Тип объектов в списке
     * @author Lomkin
     */
    public static <T> void quickSort(List<T> list, Comparator<? super T> comparator, int low, int high){

        if(low >= high) return;

        int pivotIndex = new Random().nextInt(high - low) + low;
        T pivot = list.get(pivotIndex);
        swap(list, pivotIndex, high);

        int leftPointer = partition(list, comparator, low, high, pivot);

        quickSort(list, comparator, low, leftPointer-1);
        quickSort(list, comparator, leftPointer+1, high);

    }

    /**
     * Меняет местами объекты в списке
     * @param list Список объектов. Не должен быть Immutable
     * @param index1 Индекс первого элемента
     * @param index2 Индекс второго элемента
     * @param <T> Тип объектов в списке
     * @author Lomkin
     */
    private static <T> void swap(List<T> list, int index1, int index2){
        if(index1 == index2) return;

        T temp = list.get(index1);
        list.set(index1, list.get(index2));
        list.set(index2, temp);
    }

    /**
     * Проходится по подсписку, переставляя элементы <strong>&lt;pivot</strong> в начало, <strong>&gt;pivot</strong> в конец
     * @param list Список объектов. Не должен быть Immutable
     * @param comparator Компаратор для сравнения объектов
     * @param low Нижняя граница подсписка
     * @param high Верхняя граница подсписка
     * @param pivot Опорный элемент
     * @param <T> Тип объектов в списке
     * @return Индекс опорного элемента
     * @author Lomkin
     */
    private static <T> int partition(List<T> list, Comparator<? super T> comparator, int low, int high, T pivot){
        int leftPointer = low;
        int rightPointer = high;

        while(leftPointer<rightPointer){
           while(comparator.compare(list.get(leftPointer), pivot) <= 0 && leftPointer < rightPointer)
                leftPointer++;
            while(comparator.compare(list.get(rightPointer), pivot) >= 0 && leftPointer < rightPointer)
                rightPointer--;

            swap(list, leftPointer, rightPointer);
        }
        swap(list, leftPointer, high);

        return leftPointer;
    }

}