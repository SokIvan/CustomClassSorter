package com.aston.functionalClasses.BinarySearching;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTest {

    @Test
    void testBinarySearchFoundCarWithPrint() {
        BinarySearch<String> search = new BinarySearch<>();
        ArrayList<String> cars = new ArrayList<>();

        // создаём список машинок
        cars.add("BMW");
        cars.add("Audi");
        cars.add("Mercedes");
        cars.add("Toyota");
        cars.add("Honda");

        // сортируем список
        Collections.sort(cars);

        // выводим весь отсортированный список
        System.out.println("Отсортированный список машинок: " + cars);

        // берём элемент по индексу 2
        int testIndex = 2;
        String carToFind = cars.get(testIndex);
        System.out.println("Элемент для поиска: " + carToFind + " (индекс " + testIndex + ")");

        // вызываем бинарный поиск
        int foundIndex = search.binarySearch(cars, carToFind, 0, cars.size() - 1, String::compareTo);

        // выводим результат поиска
        System.out.println("Индекс, найденный бинарным поиском: " + foundIndex);

        // проверяем, что найденный индекс совпадает с ожидаемым
        assertEquals(testIndex, foundIndex);
    }
}
