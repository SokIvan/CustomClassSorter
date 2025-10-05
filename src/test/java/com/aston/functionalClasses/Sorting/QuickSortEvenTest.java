package com.aston.functionalClasses.Sorting;

import com.aston.customClasses.Car;
import com.aston.functionalClasses.Sorting.QuickSortEven;
import com.aston.functionalClasses.Sorting.QuickSort;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuickSortEvenTest {

    @Test
    void testQuickSortEvenByCost() {
        // Создаем список машин с разными ценами
        List<Car> cars = new ArrayList<>();
        cars.add(Car.builder().setGosNumber("C001").setModel("BMW").setDate(2020).setCost(50000).setLastOwner("Ivan").build());
        cars.add(Car.builder().setGosNumber("C002").setModel("Toyota").setDate(2019).setCost(30001).setLastOwner("Maria").build()); // нечет
        cars.add(Car.builder().setGosNumber("C003").setModel("Audi").setDate(2021).setCost(70000).setLastOwner("Sergey").build());
        cars.add(Car.builder().setGosNumber("C004").setModel("Honda").setDate(2022).setCost(40002).setLastOwner("Anna").build());

        // Создаем ожидаемый список: сортируем только чётные стоимости
        List<Car> expected = new ArrayList<>(cars);

        List<Car> evenCars = expected.stream()
                .filter(c -> c.getCost() % 2 == 0)
                .sorted(Comparator.comparingInt(Car::getCost))
                .collect(Collectors.toList());

        int idx = 0;
        for (int i = 0; i < expected.size(); i++) {
            if (expected.get(i).getCost() % 2 == 0) {
                expected.set(i, evenCars.get(idx++));
            }
        }

        // Сортируем с помощью QuickSortEven
        QuickSortEven.quickSortEven(cars, Car::getCost, Comparator.comparingInt(Car::getCost));

        // Проверяем, что результат совпадает с ожидаемым
        assertEquals(expected, cars, "Списки должны совпадать после сортировки чётных значений по стоимости");
    }

    @Test
    void testQuickSortEvenByDate() {
        List<Car> cars = new ArrayList<>();
        cars.add(Car.builder().setGosNumber("D001").setModel("BMW").setDate(2019).setCost(50000).setLastOwner("Ivan").build());
        cars.add(Car.builder().setGosNumber("D002").setModel("Toyota").setDate(2020).setCost(30001).setLastOwner("Maria").build()); // нечет
        cars.add(Car.builder().setGosNumber("D003").setModel("Audi").setDate(2022).setCost(70000).setLastOwner("Sergey").build());
        cars.add(Car.builder().setGosNumber("D004").setModel("Honda").setDate(2021).setCost(40002).setLastOwner("Anna").build());

        List<Car> expected = new ArrayList<>(cars);

        List<Car> evenCars = expected.stream()
                .filter(c -> c.getDate() % 2 == 0)
                .sorted(Comparator.comparingInt(Car::getDate))
                .collect(Collectors.toList());

        int idx = 0;
        for (int i = 0; i < expected.size(); i++) {
            if (expected.get(i).getDate() % 2 == 0) {
                expected.set(i, evenCars.get(idx++));
            }
        }

        QuickSortEven.quickSortEven(cars, Car::getDate, Comparator.comparingInt(Car::getDate));

        assertEquals(expected, cars, "Списки должны совпадать после сортировки чётных значений по дате");
    }
}
