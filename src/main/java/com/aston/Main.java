package com.aston;


import com.aston.customClasses.Car;
import com.aston.customClasses.Driver;
import com.aston.customClasses.Route;
import com.aston.functionalClasses.FileWorker;
import com.aston.functionalClasses.RandomAssembly.RandomCarArray;
import com.aston.functionalClasses.RandomAssembly.RandomDriverArray;
import com.aston.functionalClasses.RandomAssembly.RandomRouteArray;
import com.aston.functionalClasses.SortCreator;
import java.util.Arrays;

public class Main {
    public static void main(String[] configuration){
        Car[] cars = RandomCarArray.create();
        System.out.println(Arrays.toString(cars));

        Driver[] drivers  = RandomDriverArray.create();
        System.out.println(Arrays.toString(drivers));

        Route[] routes  = RandomRouteArray.create();
        System.out.println(Arrays.toString(routes));
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
