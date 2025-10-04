// com/aston/fileworker/FileWriterUtil.java
package com.aston.fileworker;

import com.aston.customClasses.Car;
import com.aston.customClasses.Driver;
import com.aston.customClasses.Route;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class FileWriterUtil {

    // Перезапись файла (не добавление!)
    public static void writeCarsToFile(String filename, List<Car> cars) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) { // false по умолчанию
            for (Car car : cars) {
                pw.printf("%s;%s;%d;%d;%s%n",
                        car.getGosNumber(),
                        car.getModel(),
                        car.getDate(),
                        car.getCost(),
                        car.getLastOwner());
            }
        }
    }

    public static void writeDriversToFile(String filename, List<Driver> drivers) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            for (Driver driver : drivers) {
                pw.printf("%s;%s;%d;%d;%.2f%n",
                        driver.getName(),
                        driver.getCategory(),
                        driver.getExperience(),
                        driver.getAge(),
                        driver.getRate());
            }
        }
    }

    public static void writeRoutesToFile(String filename, List<Route> routes) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            for (Route route : routes) {
                pw.printf("%s;%s;%s;%d;%d%n",
                        route.getDriverName(),
                        route.getCarName(),
                        route.getRoadName(),
                        route.getDistanse(),
                        route.getPassengers());
            }
        }
    }

    // Для записи результата поиска — тоже перезапись 
    public static void writeSearchResultToFile(String filename, String result) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            pw.println(result);
        }
    }
}