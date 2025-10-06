package com.aston.controller;

import com.aston.customClasses.Car;
import com.aston.customClasses.Driver;
import com.aston.customClasses.Route;
import com.aston.functionalClasses.StreamArrayList.MyArrayList;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Controller {

    public static List<Car> createCars(Scanner scanner) {
        System.out.print("Введите количество машин: ");
        int count = scanner.nextInt();
        scanner.nextLine();

        List<Car> cars = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            System.out.println("=== Машина " + (i + 1) + " ===");
            try{
                cars.add(getCarInstance(scanner));
            }
            catch (RuntimeException e){
                System.out.println(e.getMessage());
            }
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
            try {
                drivers.add(getDriverInstance(scanner));
            }
            catch (RuntimeException e){
                System.out.println(e.getMessage());
            }
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
            try {
                routes.add(getRouteInstance(scanner));
            }
            catch (RuntimeException e){
                System.out.println(e.getMessage());
            }
        }

        System.out.println("Создано " + routes.size() + " маршрутов.");
        return routes;
    }

    public static List<Car> createCarsWithStream(Scanner scanner){
        System.out.print("Введите количество машин: ");
        int count = scanner.nextInt();
        scanner.nextLine();

        List<Car> cars = new MyArrayList<>();
        try {
            Stream.generate(() -> getCarInstance(scanner))
                    .limit(count)
                    .forEach(cars::add);
        }
        catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
        System.out.println("Создано " + cars.size() + " машин.");
        return cars;
    }

    public static List<Driver> createDriverWithStream(Scanner scanner){
        System.out.print("Введите количество водителей: ");
        int count = scanner.nextInt();
        scanner.nextLine();

        List<Driver> drivers = new MyArrayList<>();
        try {
            Stream.generate(() -> getDriverInstance(scanner))
                    .limit(count)
                    .forEach(drivers::add);
        }
        catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
        System.out.println("Создано " + drivers.size() + " водителей.");
        return drivers;
    }

    public static List<Route> createRouteWithStream(Scanner scanner){
        System.out.print("Введите количество маршрутов: ");
        int count = scanner.nextInt();
        scanner.nextLine();

        List<Route> routes = new MyArrayList<>();
        try {
            Stream.generate(() -> getRouteInstance(scanner))
                    .limit(count)
                    .forEach(routes::add);
        }
        catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
        System.out.println("Создано " + routes.size() + " маршрутов.");
        return routes;
    }

    public static Car getCarInstance(Scanner scanner){
        Car.CarBuilder builder = new Car.CarBuilder();

        System.out.print("\nВведите госномер: ");
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
        return builder.build();
    }
    public static Driver getDriverInstance(Scanner scanner){
        Driver.DriverBuilder builder = new Driver.DriverBuilder();

        System.out.print("\nВведите имя: ");
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
        return builder.build();
    }

    public static Route getRouteInstance(Scanner scanner){
        Route.RouteBuilder builder = new Route.RouteBuilder();

        System.out.print("\nВведите имя водителя: ");
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

        return builder.build();
    }
}
