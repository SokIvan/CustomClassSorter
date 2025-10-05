package com.aston.controller;

import com.aston.customClasses.Car;
import com.aston.customClasses.Driver;
import com.aston.customClasses.Route;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    @Test
    void testCreateCars() {
        String input = "2\n" +
                "А123ВС\nToyota\nИванов\n1000000\n2020\n" +
                "B456CD\nBMW\nПетров\n2000000\n2018\n";

        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        scanner.useLocale(Locale.US); // важная строка

        List<Car> cars = Controller.createCars(scanner);

        assertNotNull(cars);
        assertEquals(2, cars.size());

        assertEquals("А123ВС", cars.get(0).getGosNumber());
        assertEquals("Toyota", cars.get(0).getModel());

        assertEquals("B456CD", cars.get(1).getGosNumber());
        assertEquals("BMW", cars.get(1).getModel());
    }

    @Test
    void testCreateDrivers() {
        String input = "1\nАлексей\nB\n5\n30\n4.5\n";

        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        scanner.useLocale(Locale.US); // важная строка

        List<Driver> drivers = Controller.createDrivers(scanner);

        assertNotNull(drivers);
        assertEquals(1, drivers.size());

        Driver d = drivers.get(0);
        assertEquals("Алексей", d.getName());
        assertEquals("B", d.getCategory());
        assertEquals(5, d.getExperience());
        assertEquals(30, d.getAge());
        assertEquals(4.5, d.getRate());
    }

    @Test
    void testCreateRoutes() {
        String input = "1\nАлексей\nToyota\nМосква->Казань\n800\n3\n";

        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        scanner.useLocale(Locale.US); // важная строка

        List<Route> routes = Controller.createRoutes(scanner);

        assertNotNull(routes);
        assertEquals(1, routes.size());

        Route r = routes.get(0);
        assertEquals("Алексей", r.getDriverName());
        assertEquals("Toyota", r.getCarName());
        assertEquals("Москва->Казань", r.getRoadName());
        assertEquals(800, r.getDistanse());
        assertEquals(3, r.getPassengers());
    }
}
