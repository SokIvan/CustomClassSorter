package com.aston.customClasses;


import java.util.Comparator;
import java.util.Objects;

public class Driver {
    private final String name;//имя водителя, можно ник, можно фио
    private final String category;//категория прав
    private final int experience;//стаж водителя
    private final int age; // возраст водителя          ---аккуратнее с рандомом, а то лет 40, а стаж 25 нехорошо---
    private final double rate; // рейтинг водителя

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Driver driver = (Driver) obj;
        return experience == driver.experience &&
                age == driver.age &&
                Double.compare(driver.rate, rate) == 0 &&
                Objects.equals(name, driver.name) &&
                Objects.equals(category, driver.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, category, experience, age, rate);
    }


    public static Comparator<Driver> compareByName() {
        return Comparator.comparing(Driver::getName);
    }
    public static Comparator<Driver> compareByCategory() {
        return Comparator.comparing(Driver::getCategory);
    }
    public static Comparator<Driver> compareByExperience() {
        return Comparator.comparingInt(Driver::getExperience);
    }
    public static Comparator<Driver> compareByRateAndExperienceCustom() {
        // Создаем анонимный класс Comparator
        return new Comparator<Driver>() {
            @Override
            public int compare(Driver driver1, Driver driver2) {
                return Double.compare(calculate(driver1),calculate(driver2));
            }
            public Double calculate(Driver driver){
                return driver.getRate()*((1+driver.getExperience())/driver.getExperience());
            }
        };
    }







    private Driver(DriverBuilder driverBuilder){
        this.name = driverBuilder.name;
        this.category = driverBuilder.category;
        this.experience = driverBuilder.experience;
        this.age = driverBuilder.age;
        this.rate = driverBuilder.rate;
    }

    public static DriverBuilder builder(){
        return new DriverBuilder();
    }


    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public int getExperience() {
        return experience;
    }

    @Override
    public String toString(){
        String age = getAge()!=-1?(getAge()+""):"-Неизвестно-";
        String rate = getRate()!=-1?(getRate()+""):"-Неизвестно-";
        return String.format("\n%s\nВозраст:%s\nКатегория:%s\nСтаж:%s лет\nРейтинг:%s⭐\n",getName(),getAge(),getCategory(),getExperience(),getRate());
    }


    public int getAge() {
        return age;
    }

    public double getRate() {
        return rate;
    }


    public static class DriverBuilder{
        private String name;
        private String category;
        private int experience;
        private int age = -1;
        private double rate = -1;

        public DriverBuilder setName(String name){
            this.name = name;
            return this;
        }
        public DriverBuilder setCategory(String category){
            this.category = category;
            return this;
        }
        public DriverBuilder setExperience(int experience){
            this.experience = experience;
            return this;
        }
        public Driver build(){
            return new Driver(this);
        }

        public DriverBuilder setAge(int age) {
            this.age = age;
            return this;
        }

        public DriverBuilder setRate(double rate) {
            this.rate = rate;
            return this;
        }
    }

}
