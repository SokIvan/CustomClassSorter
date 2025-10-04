package com.aston.customClasses;


import com.aston.customClasses.Route;
import org.junit.jupiter.api.Test;

public class RouteTest {

    @Test
    void testMissingRequiredFields() {
        Route.RouteBuilder builder = Route.builder();
        builder.setDriverName("Ivan");
        builder.setCarName("Toyota");
        // Не устанавливаем roadName
        try {
            Route route = builder.build();
        } catch (RuntimeException e) {
            System.out.println("Ошибка создания маршрута: " + e.getMessage());
        }
    }

    @Test
    void testNegativeDistance() {
        Route.RouteBuilder builder = Route.builder();
        builder.setDriverName("Ivan");
        builder.setCarName("Toyota");
        builder.setRoadName("Moscow-Yaroslavl");
        builder.setDistanse(-10);
        builder.setPassengers(3);
        try {
            Route route = builder.build();
        } catch (RuntimeException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    @Test
    void testValidRoute() {
        Route.RouteBuilder builder = Route.builder();
        builder.setDriverName("Ivan");
        builder.setCarName("Toyota");
        builder.setRoadName("Moscow-Yaroslavl");
        builder.setDistanse(100);
        builder.setPassengers(3);
        try {
            Route route = builder.build();
            System.out.println("Маршрут успешно создан: " + route);
        } catch (RuntimeException e) {
            System.out.println("Ошибка при создании маршрута: " + e.getMessage());
        }
    }
}
