package com.aston;


import com.aston.customClasses.Car;
import com.aston.customClasses.Driver;
import com.aston.customClasses.Route;
import com.aston.functionalClasses.FileWorker;
import com.aston.functionalClasses.RandomAssembly.RandomCarList;
import com.aston.functionalClasses.RandomAssembly.RandomDriverList;
import com.aston.functionalClasses.RandomAssembly.RandomRouteArray;
import com.aston.functionalClasses.SortCreator;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] configuration){
        List<Car> cars = RandomCarList.create();
        System.out.println(cars);

        List<Driver> drivers = RandomDriverList.create();
        System.out.println(drivers);

        List<Route> routes  = RandomRouteArray.create();
        System.out.println(routes);
        //debug_main();
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
