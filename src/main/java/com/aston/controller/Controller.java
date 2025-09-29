package com.aston.controller;

import com.aston.customClasses.Car;
import com.aston.customClasses.Driver;
import com.aston.customClasses.Route;

import java.util.Scanner;

public class Controller {

    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        boolean running = true;

        while (running) {
            System.out.println("=== Главное меню ===");
            System.out.println("1 - Ввести данные о машине");
            System.out.println("2 - Ввести данные о маршруте");
            System.out.println("3 - Ввести данные о водителе");
            System.out.println("0 - Выход");
            System.out.print("Выберите действие: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // убрать \n после числа

            switch (choice) {
                case 1 -> createCar();
                case 2 -> createRoute();
                case 3 -> createDriver();
                case 0 -> {
                    System.out.println("Выход из программы...");
                    running = false;
                }
                default -> System.out.println("Некорректный ввод!");
            }
        }
    }

    private void createCar() {
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

        Car car = builder.build();
        if (car != null) {
            System.out.println("Машина создана: " + car);
        } else {
            System.out.println("Ошибка: не все обязательные поля заполнены!");
        }
    }

    private void createRoute() {
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

        Route route = builder.build();
        System.out.println("Маршрут создан: " + route);
    }

    private void createDriver() {
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

        Driver driver = builder.build();
        System.out.println("Водитель создан: " + driver);
    }
}

