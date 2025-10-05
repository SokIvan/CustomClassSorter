package com.aston.customClasses;


import com.aston.customClasses.Car;
import org.junit.jupiter.api.Test;

public class CarTest {

    @Test
    void testMissingRequiredFields() {
        Car.CarBuilder builder = Car.builder();
        builder.setGosNumber("A123BC");
        // Не устанавливаем модель и год выпуска
        try {
            Car car = builder.build();
        } catch (RuntimeException e) {
            System.out.println("Ошибка создания машины: " + e.getMessage());
        }
    }

    @Test
    void testValidCar() {
        Car.CarBuilder builder = Car.builder();
        builder.setGosNumber("A123BC");
        builder.setModel("Toyota Corolla");
        builder.setDate(2020);
        builder.setCost(15000);
        builder.setLastOwner("Ivan");

        try {
            Car car = builder.build();
            System.out.println("Машина успешно создана: " + car);
        } catch (RuntimeException e) {
            System.out.println("Ошибка при создании машины: " + e.getMessage());
        }
    }
}
