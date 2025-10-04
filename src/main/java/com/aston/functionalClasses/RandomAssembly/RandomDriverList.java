package com.aston.functionalClasses.RandomAssembly;

import com.aston.customClasses.Driver;

import java.util.ArrayList;
import java.util.List;

public class RandomDriverList extends RandomList {

    private static final String[] CATEGORIES = {"B", "B,C", "B,D", "B,C,D"};
    private static final int LEGAL_AGE = 18;
    private static final int MIN_EXPERIENCE = 3;
    private static final int MIN_AGE = LEGAL_AGE + MIN_EXPERIENCE;
    private static final int MAX_AGE = 65;
    private static final int MAX_EXPERIENCE = MAX_AGE - LEGAL_AGE;
    private static final double MIN_RATE = 3.0d;
    private static final double MAX_RATE = 5.0d;

    public static List<Driver> create() {
        int length = getIntInRange(MIN_LENGTH, MAX_LENGTH);
        List<Driver> drivers = new ArrayList<>();
        for (int i = 0; i < length; i++)
            drivers.add(getInstance());
        return drivers;
    }

    public static Driver getInstance(){
        Driver.DriverBuilder builder = new Driver.DriverBuilder()
                .setName(getName())
                .setCategory(getCategory());
        int age = getAge();
        builder.setAge(age);
        builder.setExperience(getExperienceWithValidation(age));
        if (Math.random() > 0.5) {
            builder.setRate(getRate());
        }
        return builder.build();
    }

    private static String getCategory(){
         return getRandomConst(CATEGORIES);
    }

    private static int getAge(){
        return getIntInRange(MIN_AGE, MAX_AGE);
    }

    private static int getExperienceWithValidation(int age){
        int experience = getIntInRange(MIN_EXPERIENCE, MAX_EXPERIENCE);
        int difference = age - experience;
        if (difference < LEGAL_AGE){
            return experience - (LEGAL_AGE - difference);
        }
        return experience;
    }
    private static double getRate(){
        return Math.round(getDoubleInRange(MIN_RATE, MAX_RATE)*100.0) / 100.0;
    }
}

