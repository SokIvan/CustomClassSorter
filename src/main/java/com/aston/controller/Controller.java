package com.aston.controller;

import com.aston.customClasses.Car;
import com.aston.customClasses.Driver;
import com.aston.customClasses.Route;

import java.util.Scanner;

public class Controller {
    private final Scanner scanner = new Scanner(System.in);

    // поля для хранения массивов
    private Car[] cars;
    private Driver[] drivers;
    private Route[] routes;

    public void start() {
        boolean running = true;

        while (running) {
            System.out.println("=== Главное меню ===");
            System.out.println("1 - Ввести данные о машинах");
            System.out.println("2 - Ввести данные о маршрутах");
            System.out.println("3 - Ввести данные о водителях");
            System.out.println("4 - Показать все данные");
            System.out.println("0 - Выход");
            System.out.print("Выберите действие: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> createCars();
                case 2 -> createRoutes();
                case 3 -> createDrivers();
                case 4 -> showAll();
                case 0 -> running = false;
                default -> System.out.println("Некорректный ввод!");
            }
        }
    }

    private void createCars() {
        System.out.print("Введите количество машин: ");
        int count = scanner.nextInt();
        scanner.nextLine();

        cars = new Car[count];

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

            cars[i] = builder.build();
        }

        System.out.println("Создано " + cars.length + " машин.");
    }

    private void createDrivers() {
        System.out.print("Введите количество водителей: ");
        int count = scanner.nextInt();
        scanner.nextLine();

        drivers = new Driver[count];

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

            drivers[i] = builder.build();
        }

        System.out.println("Создано " + drivers.length + " водителей.");
    }

    private void createRoutes() {
        System.out.print("Введите количество маршрутов: ");
        int count = scanner.nextInt();
        scanner.nextLine();

        routes = new Route[count];

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

            routes[i] = builder.build();
        }

        System.out.println("Создано " + routes.length + " маршрутов.");
    }

    private void showAll() {
        System.out.println("=== Текущие данные ===");
        if (cars != null) {
            System.out.println("Машины:");
            for (Car c : cars) {
                System.out.println(c);
            }
        }
        if (drivers != null) {
            System.out.println("Водители:");
            for (Driver d : drivers) {
                System.out.println(d);
            }
        }
        if (routes != null) {
            System.out.println("Маршруты:");
            for (Route r : routes) {
                System.out.println(r);
            }
        }
    }
}
