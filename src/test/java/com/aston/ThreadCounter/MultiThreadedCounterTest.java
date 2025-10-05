package com.aston.ThreadCounter;

import com.aston.customClasses.Driver;
import com.aston.functionalClasses.ThreadСounter.MultiThreadedCounter;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MultiThreadedCounterTest {

    @Test
    void testCountOccurrencesAndByField() {
        // Создаем коллекцию водителей
        List<Driver> drivers = new ArrayList<>();
        drivers.add(Driver.builder().setName("Ivan").setCategory("B").setExperience(5).setAge(25).setRate(4.5).build());
        drivers.add(Driver.builder().setName("Maria").setCategory("C").setExperience(3).setAge(24).setRate(4.0).build());
        drivers.add(Driver.builder().setName("Ivan").setCategory("B").setExperience(5).setAge(25).setRate(4.5).build());

        long countByObject = 0;
        long countByField = 0;

        // Ловим исключения для countOccurrences
        try {
            countByObject = MultiThreadedCounter.countOccurrences(
                    drivers,
                    Driver.builder().setName("Ivan").setCategory("B").setExperience(5).setAge(25).setRate(4.5).build()
            );
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Ошибка при подсчете по объекту: " + e.getMessage());
        }

        // Ловим исключения для countOccurrencesByField
        try {
            countByField = MultiThreadedCounter.countOccurrencesByField(drivers, "name", "Ivan");
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Ошибка при подсчете по полю: " + e.getMessage());
        }

        // Проверяем результаты
        assertEquals(2, countByObject, "Неверное количество совпадений по объекту");
        assertEquals(2, countByField, "Неверное количество совпадений по полю");
    }
}
