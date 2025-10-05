
package com.aston.fileworker;

import com.aston.customClasses.Car;
import com.aston.customClasses.Driver;
import com.aston.customClasses.Route;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileReaderUtilTest {

    @Test
    void testReadCarsFromFile() throws IOException {
        // Создаём временный файл с тестовыми данными для машин
        File tempFile = File.createTempFile("cars", ".txt");
        tempFile.deleteOnExit(); // удаляем после теста

        // Записываем данные в файл
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write("ABC123;Toyota;2010;5000;John Doe\n"); // машина с полными данными
            writer.write("XYZ789;Honda;2015;0;Unknown\n"); // машина с минимальными допустимыми значениями
        }

        // Читаем машины с помощью нашего метода
        List<Car> cars = FileReaderUtil.readCarsFromFile(tempFile.getAbsolutePath());
        assertEquals(2, cars.size()); // проверяем количество считанных машин

        // Проверяем первую машину
        Car car1 = cars.get(0);
        assertEquals("ABC123", car1.getGosNumber());
        assertEquals("Toyota", car1.getModel());
        assertEquals(2010, car1.getDate());
        assertEquals(5000, car1.getCost());
        assertEquals("John Doe", car1.getLastOwner());

        // Проверяем вторую машину (с минимальными значениями)
        Car car2 = cars.get(1);
        assertEquals("XYZ789", car2.getGosNumber());
        assertEquals("Honda", car2.getModel());
        assertEquals(2015, car2.getDate());
        assertEquals(0, car2.getCost());
        assertEquals("Unknown", car2.getLastOwner());
    }

    @Test
    void testReadDriversFromFile() throws IOException {
        // Временный файл с тестовыми данными для водителей
        File tempFile = File.createTempFile("drivers", ".txt");
        tempFile.deleteOnExit();

        // Записываем валидные данные для водителей
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write("Alice;B;5;30;4.5\n"); // все поля заполнены
            writer.write("Bob;C;2;25;3.0\n"); // возраст ≥ 21, рейтинг ≥ 0
        }

        // Читаем водителей с помощью нашего метода
        List<Driver> drivers = FileReaderUtil.readDriversFromFile(tempFile.getAbsolutePath());
        assertEquals(2, drivers.size()); // проверяем количество считанных водителей

        // Проверяем первого водителя
        Driver driver1 = drivers.get(0);
        assertEquals("Alice", driver1.getName());
        assertEquals("B", driver1.getCategory());
        assertEquals(5, driver1.getExperience());
        assertEquals(30, driver1.getAge());
        assertEquals(4.5, driver1.getRate());

        // Проверяем второго водителя
        Driver driver2 = drivers.get(1);
        assertEquals("Bob", driver2.getName());
        assertEquals("C", driver2.getCategory());
        assertEquals(2, driver2.getExperience());
        assertEquals(25, driver2.getAge());
        assertEquals(3.0, driver2.getRate());
    }

    @Test
    void testReadRoutesFromFile() throws IOException {
        // Временный файл с тестовыми данными для маршрутов
        File tempFile = File.createTempFile("routes", ".txt");
        tempFile.deleteOnExit();

        // Записываем валидные маршруты
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write("Alice;Toyota;Highway;100;4\n"); // все поля заполнены
            writer.write("Bob;Honda;MainStreet;0;0\n"); // минимально допустимые значения для дистанции и пассажиров
        }

        // Читаем маршруты с помощью метода
        List<Route> routes = FileReaderUtil.readRoutesFromFile(tempFile.getAbsolutePath());
        assertEquals(2, routes.size()); // проверяем количество маршрутов

        // Проверяем первый маршрут
        Route route1 = routes.get(0);
        assertEquals("Alice", route1.getDriverName());
        assertEquals("Toyota", route1.getCarName());
        assertEquals("Highway", route1.getRoadName());
        assertEquals(100, route1.getDistanse());
        assertEquals(4, route1.getPassengers());

        // Проверяем второй маршрут
        Route route2 = routes.get(1);
        assertEquals("Bob", route2.getDriverName());
        assertEquals("Honda", route2.getCarName());
        assertEquals("MainStreet", route2.getRoadName());
        assertEquals(0, route2.getDistanse()); // минимально допустимое значение
        assertEquals(0, route2.getPassengers());
    }
}
