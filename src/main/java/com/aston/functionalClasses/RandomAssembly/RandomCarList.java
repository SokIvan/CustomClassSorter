package com.aston.functionalClasses.RandomAssembly;

import com.aston.customClasses.Car;
import com.aston.functionalClasses.StreamArrayList.MyArrayList;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class RandomCarList extends RandomList {
    private static final int MIN_RELEASE_YEAR = 1980;
    private static final int MAX_RELEASE_YEAR = 2025;
    private static final int MIN_COST = 400000;
    private static final int MAX_COST = 3000000;
    private static final char[] STATE_NUM_LETTERS = {'А', 'В', 'Е', 'К', 'М', 'Н', 'О', 'Р', 'С', 'Т', 'У', 'Х'};

    public static List<Car> create(){
        int length = getIntInRange(MIN_LENGTH, MAX_LENGTH);
        List<Car> cars = new ArrayList<>();
        for(int i = 0; i < length; i ++)
            cars.add(getInstance());
        return cars;
    }

    public static Car getInstance(){
        Car.CarBuilder builder = new Car.CarBuilder()
                .setModel(getModel())
                .setGosNumber(getStateNum())
                .setDate(getReleaseYear());
        if(Math.random() > 0.5){
            builder.setCost(getCost()/1000*1000);
        }
        if(Math.random() > 0.5){
            builder.setLastOwner(getName());
        }
        return builder.build();
    }

    public static List<Car> createWithStream(){
        List<Car> cars = new MyArrayList<>();
        Stream.generate(() -> getInstance())
                .limit(getIntInRange(MIN_LENGTH, MAX_LENGTH))
                .forEach(cars::add);
        return cars;
    }

    private static String getStateNum(){
        char [] stateNum = new char[6];
        for(int i = 0; i < stateNum.length; i++){
         if (i < 1 || i > 3)
             stateNum[i] = getRandomConst(STATE_NUM_LETTERS);
         else
             stateNum[i] = (char)(getIntInRange(0,9) + '0');
        }
        return new String(stateNum);
    }

    private static int getReleaseYear(){
        return getIntInRange(MIN_RELEASE_YEAR, MAX_RELEASE_YEAR);
    }

    private static int getCost(){
        return getIntInRange(MIN_COST, MAX_COST);
    }

}
