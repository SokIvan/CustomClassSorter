package com.aston;

import com.aston.controller.Controller;
import com.aston.customClasses.Car;
import com.aston.customClasses.Driver;
import com.aston.customClasses.Route;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Car> cars = new ArrayList<>();
        List<Driver> drivers = new ArrayList<>();
        List<Route> routes = new ArrayList<>();

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
                case 1 -> cars = Controller.createCars();
                case 2 -> routes = Controller.createRoutes();
                case 3 -> drivers = Controller.createDrivers();
                case 4 -> {
                    System.out.println("=== Текущие данные ===");
                    if (!cars.isEmpty()) {
                        System.out.println("Машины:");
                        cars.forEach(System.out::println);
                    }
                    if (!drivers.isEmpty()) {
                        System.out.println("Водители:");
                        drivers.forEach(System.out::println);
                    }
                    if (!routes.isEmpty()) {
                        System.out.println("Маршруты:");
                        routes.forEach(System.out::println);
                    }
                }
                case 0 -> running = false;
                default -> System.out.println("Некорректный ввод!");
            }
        }
    }
}