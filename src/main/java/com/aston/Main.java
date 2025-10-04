package com.aston;

import com.aston.controller.Controller;
import com.aston.customClasses.Car;
import com.aston.customClasses.Driver;
import com.aston.customClasses.Route;

import com.aston.fileworker.FileWriterUtil;
import java.io.IOException;
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
    System.out.println("\n=== Главное меню ===");
    System.out.println("1 - Ввести данные о машинах");
    System.out.println("2 - Ввести данные о маршрутах");
    System.out.println("3 - Ввести данные о водителях");
    System.out.println("4 - Показать все данные");
    System.out.println("5 - Сохранить данные в файл (полная перезапись)");
    System.out.println("0 - Выход");
    System.out.print("Выберите действие: ");
    int choice = scanner.nextInt();
    scanner.nextLine();

    switch (choice) {
        case 1 -> {
            System.out.println("1 - Ввести вручную\n2 - Загрузить из файла");
            int subChoice = scanner.nextInt();
            scanner.nextLine();
            if (subChoice == 1) {
                cars = Controller.createCars(scanner);
            } else if (subChoice == 2) {
                cars = Controller.loadCarsFromFile(scanner);
            } else {
                System.out.println("Неверный выбор.");
            }
        }
        case 2 -> {
            System.out.println("1 - Ввести вручную\n2 - Загрузить из файла");
            int subChoice = scanner.nextInt();
            scanner.nextLine();
            if (subChoice == 1) {
                routes = Controller.createRoutes(scanner);
            } else if (subChoice == 2) {
                routes = Controller.loadRoutesFromFile(scanner);
            } else {
                System.out.println("Неверный выбор.");
            }
        }
        case 3 -> {
            System.out.println("1 - Ввести вручную\n2 - Загрузить из файла");
            int subChoice = scanner.nextInt();
            scanner.nextLine();
            if (subChoice == 1) {
                drivers = Controller.createDrivers(scanner);
            } else if (subChoice == 2) {
                drivers = Controller.loadDriversFromFile(scanner);
            } else {
                System.out.println("Неверный выбор.");
            }
        }
        case 4 -> {
            System.out.println("\n=== Текущие данные ===");
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
        case 5 -> {
            System.out.println("Выберите, что записать в файл:");
            System.out.println("1 - Машины\n2 - Водители\n3 - Маршруты");
            int subChoice = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Введите имя файла: ");
            String filename = scanner.nextLine();
            try {
    if (subChoice == 1 && !cars.isEmpty()) {
        FileWriterUtil.writeCarsToFile(filename, cars);
        System.out.println("Машины записаны в файл " + filename + " (старое содержимое удалено)");
    } else if (subChoice == 2 && !drivers.isEmpty()) {
        FileWriterUtil.writeDriversToFile(filename, drivers);
        System.out.println("Водители записаны в файл " + filename + " (старое содержимое удалено)");
    } else if (subChoice == 3 && !routes.isEmpty()) {
        FileWriterUtil.writeRoutesToFile(filename, routes);
        System.out.println("Маршруты записаны в файл " + filename + " (старое содержимое удалено)");
    } else {
        System.out.println("Коллекция пуста или выбор неверен.");
    }
} catch (IOException e) {
    System.out.println("Ошибка записи: " + e.getMessage());
}
        }
        case 0 -> running = false;
        default -> System.out.println("Некорректный ввод!");
    }
}
    
    }}

