package com.aston.controller;


import com.aston.customClasses.Car;
import com.aston.customClasses.Driver;
import com.aston.customClasses.Route;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class ControllerTest {

    @Test
    void testCreateCars() {
        // подготавливаем ввод (2 машины)
        String input = "2\n" +
                "А123ВС\nToyota\nИванов\n1000000\n2020\n" +
                "В456ОР\nBMW\nПетров\n2000000\n2018\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        List<Car> cars = Controller.createCars();

        assertEquals(2, cars.size());
        assertEquals("А123ВС", cars.get(0).getGosNumber());
        assertEquals("BMW", cars.get(1).getModel());
    }

    @Test
    void testCreateDrivers() {
        // подготавливаем ввод (1 водитель)
        String input = "1\n" +
                "Алексей\nB\n5\n30\n4.5\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        List<Driver> drivers = Controller.createDrivers();

        assertEquals(1, drivers.size());
        assertEquals("Алексей", drivers.get(0).getName());
        assertEquals(30, drivers.get(0).getAge());
    }

    @Test
    void testCreateRoutes() {
        // подготавливаем ввод (1 маршрут)
        String input = "1\n" +
                "Алексей\nToyota\nМосква->Казань\n800\n3\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        List<Route> routes = Controller.createRoutes();

        assertEquals(1, routes.size());
        assertEquals("Алексей", routes.get(0).getDriverName());
        assertEquals("Москва->Казань", routes.get(0).getRoadName());
    }
}
