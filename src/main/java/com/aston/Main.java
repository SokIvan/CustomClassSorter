package com.aston;
import com.aston.customClasses.Car;
import com.aston.functionalClasses.BinarySearch.BinarySearch;
import com.aston.functionalClasses.BinarySearch.BinarySearchByFields;
import com.aston.functionalClasses.FileWorker;
import com.aston.functionalClasses.RandomAssembly.RandomCarList;
import com.aston.functionalClasses.SortCreator;

import java.lang.reflect.Field;
import java.util.*;

public class Main {
    public static void main(String[] configuration) throws NoSuchFieldException, NoSuchMethodException, IllegalAccessException {

        Field nameField = Car.class.getDeclaredField("model");
        System.out.println(nameField.getType());
        System.out.println("dummy".getClass() == nameField.getType());
        Object obj = new String("str");
        if(obj instanceof String) {
            String typedValueToFind = (String) obj;
        }

        List<Car> cars = RandomCarList.create();
        System.out.println(cars);

        Collections.sort(cars, Car.compare());
        System.out.println("\nКоллекция, отсортированная по hashCode: ");
        for(Car item: cars)
            System.out.println(item.toStringWithHash());

        Random random = new Random();
        Car car = cars.get(random.nextInt(cars.size()));
        System.out.println("\nРандомная машина:\n" + car.toStringWithHash());
        BinarySearch binarySearch = new BinarySearch<Car>();
        int i = binarySearch.binarySearch((ArrayList) cars, car, 0, cars.size()-1, Car.compare());
        System.out.println("\nМашина найдена под индексом: " + i);



        Collections.sort(cars, Car.compareByDate());
        System.out.println("\nКоллекция, отсортированная по date: ");
        System.out.println(cars);
        Car car1 = cars.get(random.nextInt(cars.size()));
        Integer year = car1.getDate();
        System.out.println("\nРандомная дата: " + year);
        BinarySearchByFields<Car, Integer> binarySearchByFields= new BinarySearchByFields<>();
        int j = binarySearchByFields.binarySearch((ArrayList) cars, Car.class.getDeclaredField("date"), year, 0, cars.size() - 1);
        System.out.println("\nМашина найдена под индексом: " + j);

        /*
        List<Driver> drivers = RandomDriverList.create();
        System.out.println(drivers);

        List<Route> routes  = RandomRouteArray.create();
        System.out.println(routes);
        //debug_main();*/


    }


    public static void debug_main(){

        SortCreator sorter;
        FileWorker fileworker;
        
        /*while (true){
            System.out.println("Ты знаешь что такое безумие?");//очень быстро кстати стек заполнился😥
            debug_main();
            break;
        }*/
    }
}
