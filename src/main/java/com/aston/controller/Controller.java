package com.aston.controller;

import com.aston.customClasses.Car;
import com.aston.customClasses.Driver;
import com.aston.customClasses.Route;
import com.aston.fileworker.FileReaderUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {

    public static List<Car> createCars(Scanner scanner) {
        System.out.print("Введите количество машин: ");
        int count = scanner.nextInt();
        scanner.nextLine();

        List<Car> cars = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            System.out.println("=== Машина " + (i + 1) + " ===");
            Car.CarBuilder builder = new Car.CarBuilder();

            System.out.print("Введите госномер: ");
            builder.setGosNumber(scanner.nextLine());

            System.out.print("Введите модель: ");
            builder.setModel(scanner.nextLine());

            System.out.print("Введите владельца: ");
            builder.setLastOwner(scanner.nextLine());

            System.out.print("Введите цену: ");
            builder.setCost(scanner.nextInt());

            System.out.print("Введите год выпуска: ");
            builder.setDate(scanner.nextInt());
            scanner.nextLine();

            cars.add(builder.build());
        }

        System.out.println("Создано " + cars.size() + " машин.");
        return cars;
    }

    public static List<Driver> createDrivers(Scanner scanner) {
        System.out.print("Введите количество водителей: ");
        int count = scanner.nextInt();
        scanner.nextLine();

        List<Driver> drivers = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            System.out.println("=== Водитель " + (i + 1) + " ===");
            Driver.DriverBuilder builder = new Driver.DriverBuilder();

            System.out.print("Введите имя: ");
            builder.setName(scanner.nextLine());

            System.out.print("Введите категорию прав: ");
            builder.setCategory(scanner.nextLine());

            System.out.print("Введите стаж: ");
            builder.setExperience(scanner.nextInt());

            System.out.print("Введите возраст: ");
            builder.setAge(scanner.nextInt());

            System.out.print("Введите рейтинг: ");
            builder.setRate(scanner.nextDouble());
            scanner.nextLine();

            drivers.add(builder.build());
        }

        System.out.println("Создано " + drivers.size() + " водителей.");
        return drivers;
    }

    public static List<Route> createRoutes(Scanner scanner) {
        System.out.print("Введите количество маршрутов: ");
        int count = scanner.nextInt();
        scanner.nextLine();

        List<Route> routes = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            System.out.println("=== Маршрут " + (i + 1) + " ===");
            Route.RouteBuilder builder = new Route.RouteBuilder();

            System.out.print("Введите имя водителя: ");
            builder.setDriverName(scanner.nextLine());

            System.out.print("Введите название машины: ");
            builder.setCarName(scanner.nextLine());

            System.out.print("Введите маршрут (пример Москва->Ярославль): ");
            builder.setRoadName(scanner.nextLine());

            System.out.print("Введите дистанцию: ");
            builder.setDistanse(scanner.nextInt());

            System.out.print("Введите количество пассажиров: ");
            builder.setPassengers(scanner.nextInt());
            scanner.nextLine();

            routes.add(builder.build());
        }

        System.out.println("Создано " + routes.size() + " маршрутов.");
        return routes;
    }
    public static List<Car> loadCarsFromFile(Scanner scanner) {
 System.out.print("Введите полный путь к файлу для загрузки машин (например, C:\\data\\cars.txt или /home/user/cars.txt): ");
    String filename = scanner.nextLine();
    try {
        List<Car> cars = FileReaderUtil.readCarsFromFile(filename);
        System.out.println("Загружено " + cars.size() + " машин из файла " + filename);
        return cars;
    } catch (IOException e) {
        System.out.println("Ошибка чтения файла: " + e.getMessage());
        return new ArrayList<>();
    }
}

public static List<Driver> loadDriversFromFile(Scanner scanner) {
   System.out.print("Введите полный путь к файлу для загрузки водителей (например, C:\\data\\cars.txt или /home/user/cars.txt): ");
    String filename = scanner.nextLine();
    try {
        List<Driver> drivers = FileReaderUtil.readDriversFromFile(filename);
        System.out.println("Загружено " + drivers.size() + " водителей из файла " + filename);
        return drivers;
    } catch (IOException e) {
        System.out.println("Ошибка чтения файла: " + e.getMessage());
        return new ArrayList<>();
    }
}

public static List<Route> loadRoutesFromFile(Scanner scanner) {
   System.out.print("Введите полный путь к файлу для загрузки маршрутов (например, C:\\data\\cars.txt или /home/user/cars.txt): ");
    String filename = scanner.nextLine();
    try {
        List<Route> routes = FileReaderUtil.readRoutesFromFile(filename);
        System.out.println("Загружено " + routes.size() + " маршрутов из файла " + filename);
        return routes;
    } catch (IOException e) {
        System.out.println("Ошибка чтения файла: " + e.getMessage());
        return new ArrayList<>();
    }
}
}
