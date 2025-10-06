package com.aston.fileworker;

import com.aston.customClasses.Car;
import com.aston.customClasses.Driver;
import com.aston.customClasses.Route;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class FileWriterUtil {

    private static final Locale LOCALE = Locale.US;

    public static int writeCarsToFile(String filename, List<Car> cars) throws IOException {
        Set<String> existingLines = readExistingLines(filename);
        int addedCount = 0;

        try (PrintWriter pw = new PrintWriter(new FileWriter(filename, true))) {
            for (Car car : cars) {
                String newLine = String.format(LOCALE, "%s;%s;%d;%d;%s",
                        car.getGosNumber(),
                        car.getModel(),
                        car.getDate(),
                        car.getCost(),
                        car.getLastOwner());


                    pw.println(newLine);
                    existingLines.add(newLine);
                    addedCount++;

            }
        }
        return addedCount;
    }

    public static int writeDriversToFile(String filename, List<Driver> drivers) throws IOException {
        Set<String> existingLines = readExistingLines(filename);
        int addedCount = 0;

        try (PrintWriter pw = new PrintWriter(new FileWriter(filename, true))) {
            for (Driver driver : drivers) {
                String newLine = String.format(LOCALE, "%s;%s;%d;%d;%.2f",
                        driver.getName(),
                        driver.getCategory(),
                        driver.getExperience(),
                        driver.getAge(),
                        driver.getRate());
                    pw.println(newLine);
                    existingLines.add(newLine);
                    addedCount++;
            }
        }
        return addedCount;
    }

    public static int writeRoutesToFile(String filename, List<Route> routes) throws IOException {
        Set<String> existingLines = readExistingLines(filename);
        int addedCount = 0;

        try (PrintWriter pw = new PrintWriter(new FileWriter(filename, true))) {
            for (Route route : routes) {
                String newLine = String.format(LOCALE, "%s;%s;%s;%d;%d",
                        route.getDriverName(),
                        route.getCarName(),
                        route.getRoadName(),
                        route.getDistanse(),
                        route.getPassengers());


                    pw.println(newLine);
                    existingLines.add(newLine);
                    addedCount++;

            }
        }
        return addedCount;
    }

    public static void writeSearchResultToFile(String filename, String result) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            pw.println(result);
        }
    }

    private static Set<String> readExistingLines(String filename) throws IOException {
        Set<String> lines = new HashSet<>();
        if (Files.exists(Paths.get(filename))) {
            try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    line = line.trim();
                    if (!line.isEmpty()) {
                        lines.add(line);
                    }
                }
            }
        }
        return lines;
    }
}
