// src/main/java/com/aston/fileworker/FileReaderUtil.java
package com.aston.fileworker;

import com.aston.customClasses.Car;
import com.aston.customClasses.Driver;
import com.aston.customClasses.Route;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReaderUtil {

    public static List<Car> readCarsFromFile(String filename) throws IOException {
        List<Car> cars = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length < 3) continue;

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

                cars.add(builder.build());
            }
        }
        return cars;
    }

    public static List<Driver> readDriversFromFile(String filename) throws IOException {
        List<Driver> drivers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length < 3) continue;

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

                drivers.add(builder.build());
            }
        }
        return drivers;
    }

    public static List<Route> readRoutesFromFile(String filename) throws IOException {
        List<Route> routes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length < 3) continue;

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

                routes.add(builder.build());
            }
        }
        return routes;
    }
}