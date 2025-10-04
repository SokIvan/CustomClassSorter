package com.aston.customClasses;


import com.aston.customClasses.Driver;
import org.junit.jupiter.api.Test;

public class DriverTest {

    @Test
    void testMissingRequiredFields() {
        Driver.DriverBuilder builder = Driver.builder();
        builder.setName("Ivan");
        // Не устанавливаем категорию и стаж
        try {
            Driver driver = builder.build();
        } catch (RuntimeException e) {
            System.out.println("Ошибка создания водителя: " + e.getMessage());
        }
    }

    @Test
    void testInvalidAge() {
        Driver.DriverBuilder builder = Driver.builder();
        builder.setName("Ivan");
        builder.setCategory("B");
        builder.setExperience(5);
        builder.setAge(15); // слишком малый возраст
        try {
            Driver driver = builder.build();
        } catch (RuntimeException e) {
            System.out.println("Ошибка возраста: " + e.getMessage());
        }
    }

    @Test
    void testInvalidExperience() {
        Driver.DriverBuilder builder = Driver.builder();
        builder.setName("Ivan");
        builder.setCategory("B");
        builder.setExperience(5);
        builder.setAge(20); // возраст меньше 18+стаж
        try {
            Driver driver = builder.build();
        } catch (RuntimeException e) {
            System.out.println("Ошибка стажа: " + e.getMessage());
        }
    }

    @Test
    void testValidDriver() {
        Driver.DriverBuilder builder = Driver.builder();
        builder.setName("Ivan");
        builder.setCategory("B");
        builder.setExperience(5);
        builder.setAge(30);
        builder.setRate(4.5);

        try {
            Driver driver = builder.build();
            System.out.println("Водитель успешно создан: " + driver);
        } catch (RuntimeException e) {
            System.out.println("Ошибка при создании водителя: " + e.getMessage());
        }
    }
}
