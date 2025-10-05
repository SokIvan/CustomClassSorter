package com.aston.fileworker;

import com.aston.customClasses.Car;
import com.aston.customClasses.Driver;
import com.aston.customClasses.Route;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ControllerFile {

    public static List<Car> loadCarsFromFile(Scanner scanner, boolean usingStream) {
        System.out.print("Введите полный путь к файлу для загрузки машин (например, C:\\data\\cars.txt или /home/user/cars.txt): ");
        String filename = scanner.nextLine();
        List<Car> cars;
        try {
            if(usingStream)
                cars = FileReaderUtil.readCarsFromFileWithStream(filename);
            else
                cars = FileReaderUtil.readCarsFromFile(filename);
            System.out.println("Загружено " + cars.size() + " машин из файла " + filename);
            return cars;
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static List<Driver> loadDriversFromFile(Scanner scanner, boolean usingStream) {
        System.out.print("Введите полный путь к файлу для загрузки водителей (например, C:\\data\\drivers.txt или /home/user/drivers.txt): ");
        String filename = scanner.nextLine();
        List<Driver> drivers;
        try {
            if(usingStream)
                drivers = FileReaderUtil.readDriversFromFileWithStream(filename);
            else
                drivers = FileReaderUtil.readDriversFromFile(filename);
            System.out.println("Загружено " + drivers.size() + " водителей из файла " + filename);
            return drivers;
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static List<Route> loadRoutesFromFile(Scanner scanner, boolean usingStream) {
        System.out.print("Введите полный путь к файлу для загрузки маршрутов (например, C:\\data\\routes.txt или /home/user/routes.txt): ");
        String filename = scanner.nextLine();
        List<Route> routes;
        try {
            if(usingStream)
                routes = FileReaderUtil.readRoutesFromFileWithStream(filename);
            else
                routes = FileReaderUtil.readRoutesFromFile(filename);
            System.out.println("Загружено " + routes.size() + " маршрутов из файла " + filename);
            return routes;
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}