package com.aston;

import com.aston.customClasses.Car;
import com.aston.customClasses.Driver;
import com.aston.customClasses.Route;
import com.aston.functionalClasses.ParallelBubbleSortStrategy;
import com.aston.functionalClasses.ParallelEvenBubbleSortStrategy;
import com.aston.functionalClasses.QuickSortStrategy;
import com.aston.functionalClasses.SortContext;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // --- Cars ---
        List<Car> cars = new ArrayList<>();
        cars.add(Car.builder().setGosNumber("A111AA").setModel("bmw").setLastOwner("Иванов").setCost(30000).setDate(2015).build());
        cars.add(Car.builder().setGosNumber("B222BB").setModel("audi").setLastOwner("Петров").setCost(25000).setDate(2012).build());
        cars.add(Car.builder().setGosNumber("C333CC").setModel("mercedes").setLastOwner("Сидоров").setCost(10000).setDate(2018).build());

        System.out.println("До сортировки (машины):");
        cars.forEach(System.out::println);

        SortContext<Car> carSortContext = new SortContext<>();
        carSortContext.setStrategy(new ParallelBubbleSortStrategy<>()); // проверка сортировки пузырьком
        carSortContext.executeSort(cars, c -> 0, Car.compareByModelCustom());

        System.out.println("\nПосле сортировки по модели машины:");
        cars.forEach(System.out::println);

        // --- Drivers ---
        List<Driver> drivers = new ArrayList<>();
        drivers.add(Driver.builder().setName("Иван").setCategory("B").setExperience(15).setAge(40).setRate(4.5).build());
        drivers.add(Driver.builder().setName("Петр").setCategory("C").setExperience(10).setAge(35).setRate(4.0).build());
        drivers.add(Driver.builder().setName("Алексей").setCategory("B").setExperience(5).setAge(28).setRate(3.8).build());

        System.out.println("\nДо сортировки (водители):");
        drivers.forEach(System.out::println);

        SortContext<Driver> driverSortContext = new SortContext<>();
        driverSortContext.setStrategy(new QuickSortStrategy<>()); // Проверка быстрой сортировки
        driverSortContext.executeSort(drivers, d -> 0, Driver.compareByExperience());

        System.out.println("\nПосле сортировки водителей по стажу:");
        drivers.forEach(System.out::println);


        // --- Routes ---
        List<Route> routes = new ArrayList<>();

        Route.RouteBuilder r1 = Route.builder();
        r1.setDriverName("Иван");
        r1.setCarName("BMW");
        r1.setRoadName("Москва->СПб");
        r1.setDistanse(700);   // чётное → сортируется
        r1.setPassengers(3);
        routes.add(r1.build());

        Route.RouteBuilder r2 = Route.builder();
        r2.setDriverName("Петр");
        r2.setCarName("Audi");
        r2.setRoadName("Казань->Уфа");
        r2.setDistanse(500);   // чётное → сортируется
        r2.setPassengers(2);
        routes.add(r2.build());

        Route.RouteBuilder r3 = Route.builder();
        r3.setDriverName("Олег");
        r3.setCarName("Жигули");
        r3.setRoadName("Астана->Владивосток");
        r3.setDistanse(1101);  // нечётное → остаётся на месте
        r3.setPassengers(2);
        routes.add(r3.build());

        Route.RouteBuilder r4 = Route.builder();
        r4.setDriverName("Вадим");
        r4.setCarName("Волга");
        r4.setRoadName("Екб->Челябинск");
        r4.setDistanse(233);   // нечётное → остаётся на месте
        r4.setPassengers(2);
        routes.add(r4.build());

        System.out.println("\nДо сортировки (маршруты):");
        routes.forEach(System.out::println);

        SortContext<Route> routeSortContext = new SortContext<>();
        routeSortContext.setStrategy(new ParallelEvenBubbleSortStrategy<>()); // проверка сортировки пузырьком для четных
        routeSortContext.executeSort(routes, Route::getDistanse, Route.compareByDistanceAndPassengersCustom());

        System.out.println("\nПосле сортировки маршрутов (чётные сортируются, нечётные остаются на месте):");
        routes.forEach(System.out::println);
    }
}

