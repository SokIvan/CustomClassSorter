package com.aston.functionalClasses;

import com.aston.customClasses.Car;

import com.aston.functionalClasses.Sorting.ParallelBubbleSort;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParallelBubbleSortTest {

    @Test
    void testParallelBubbleSortByCost() {
        List<Car> cars = new ArrayList<>();
        cars.add(Car.builder().setGosNumber("A001AA").setModel("BMW").setDate(2020).setCost(50000).setLastOwner("Ivan").build());
        cars.add(Car.builder().setGosNumber("A002AA").setModel("Toyota").setDate(2019).setCost(30000).setLastOwner("Maria").build());
        cars.add(Car.builder().setGosNumber("A003AA").setModel("Audi").setDate(2021).setCost(70000).setLastOwner("Sergey").build());

        try {
            ParallelBubbleSort.parallelBubbleSort(cars, Comparator.comparingInt(Car::getCost));
        } catch (RuntimeException e) {
            System.out.println("Ошибка при сортировке: " + e.getMessage());
        }

        // Проверяем, что список отсортирован по цене
        assertEquals(30000, cars.get(0).getCost());
        assertEquals(50000, cars.get(1).getCost());
        assertEquals(70000, cars.get(2).getCost());
    }

    @Test
    void testParallelBubbleSortByDate() {
        List<Car> cars = new ArrayList<>();
        cars.add(Car.builder().setGosNumber("B001BB").setModel("Mercedes").setDate(2018).setCost(60000).setLastOwner("Alex").build());
        cars.add(Car.builder().setGosNumber("B002BB").setModel("Toyota").setDate(2021).setCost(40000).setLastOwner("Anna").build());
        cars.add(Car.builder().setGosNumber("B003BB").setModel("BMW").setDate(2019).setCost(45000).setLastOwner("John").build());

        try {
            ParallelBubbleSort.parallelBubbleSort(cars, Comparator.comparingInt(Car::getDate));
        } catch (RuntimeException e) {
            System.out.println("Ошибка при сортировке: " + e.getMessage());
        }

        // Проверяем, что список отсортирован по году выпуска
        assertEquals(2018, cars.get(0).getDate());
        assertEquals(2019, cars.get(1).getDate());
        assertEquals(2021, cars.get(2).getDate());
    }
}
