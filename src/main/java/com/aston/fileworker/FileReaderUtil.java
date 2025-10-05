// src/main/java/com/aston/fileworker/FileReaderUtil.java
package com.aston.fileworker;

import com.aston.customClasses.Car;
import com.aston.customClasses.Driver;
import com.aston.customClasses.Route;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileReaderUtil {

    public static List<Car> readCarsFromFile(String filename) throws IOException {
        List<Car> cars = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                cars.add(readCarInstance(line));
            }
        }
        return cars;
    }

    public static List<Car> readCarsFromFileWithStream(String filename) throws IOException {
        List<Car> cars;
        try(Stream<String> lines = Files.lines(Paths.get(filename), StandardCharsets.UTF_8)) {
            cars = lines.map(i -> readCarInstance(i))
                    .collect(Collectors.toList());
        }
        return cars;
    }
    private static Car readCarInstance(String line){
        String[] parts = line.split(";");
        Car.CarBuilder builder = new Car.CarBuilder()
                .setGosNumber(parts[0])
                .setModel(parts[1])
                .setDate(Integer.parseInt(parts[2]));

        if (parts.length > 3 && !parts[3].isEmpty()) {
            builder.setCost(Integer.parseInt(parts[3]));
        }
        if (parts.length > 4 && !parts[4].isEmpty()) {
            builder.setLastOwner(parts[4]);
        }
        return builder.build();
    }

    public static List<Driver> readDriversFromFile(String filename) throws IOException {
        List<Driver> drivers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                drivers.add(readDriverInstance(line));
            }
        }
        return drivers;
    }

    public static List<Driver> readDriversFromFileWithStream(String filename) throws IOException {
        List<Driver> drivers;
        try(Stream<String> lines = Files.lines(Paths.get(filename), StandardCharsets.UTF_8)) {
            drivers = lines
                    .map(i -> readDriverInstance(i))
                    .collect(Collectors.toList());
        }
        return drivers;
    }

    private static Driver readDriverInstance(String line){
        String[] parts = line.split(";");

        Driver.DriverBuilder builder = new Driver.DriverBuilder()
                .setName(parts[0])
                .setCategory(parts[1])
                .setExperience(Integer.parseInt(parts[2]));

        if (parts.length > 3 && !parts[3].isEmpty()) {
            builder.setAge(Integer.parseInt(parts[3]));
        }
        if (parts.length > 4 && !parts[4].isEmpty()) {
            builder.setRate(Double.parseDouble(parts[4]));
        }
        return builder.build();
    }

    public static List<Route> readRoutesFromFile(String filename) throws IOException {
        List<Route> routes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                routes.add(readRouteInstance(line));
            }
        }
        return routes;
    }

    public static List<Route> readRoutesFromFileWithStream(String filename) throws IOException {
        List<Route> routes;
        try(Stream<String> lines = Files.lines(Paths.get(filename), StandardCharsets.UTF_8)){
            routes = lines
                    .map(i -> readRouteInstance(i))
                    .collect(Collectors.toList());
        }
        return routes;
    }
    private static Route readRouteInstance(String line){
        String[] parts = line.split(";");
        Route.RouteBuilder builder = new Route.RouteBuilder()
                .setDriverName(parts[0])
                .setCarName(parts[1])
                .setRoadName(parts[2]);

        if (parts.length > 3 && !parts[3].isEmpty()) {
            builder.setDistanse(Integer.parseInt(parts[3]));
        }
        if (parts.length > 4 && !parts[4].isEmpty()) {
            builder.setPassengers(Integer.parseInt(parts[4]));
        }
        return builder.build();
    }
}