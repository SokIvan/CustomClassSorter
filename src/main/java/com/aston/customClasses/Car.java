package com.aston.customClasses;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.Random;

public class Car {
    private final String gosNumber;//Номер машины
    private final String model;//Марка, модель авто
    private final String lastOwner;//Имя прошлого хозяина, общее наименование
    private final int cost;//Цена машины
    private final int date;//Год выпуска



    public static Comparator<Car> compareByGosNumber() {
        return Comparator.comparing(Car::getGosNumber);
    }
    public static Comparator<Car> compareByModel() {
        return Comparator.comparing(Car::getModel);
    }
    public static Comparator<Car> compareByDate() {
        return Comparator.comparingInt(Car::getDate);
    }
    public static Comparator<Car> compareByModelCustom() {
        // Создаем анонимный класс Comparator
        return new Comparator<Car>() {
            @Override
            public int compare(Car car1, Car car2) {
                // Логика сравнения двух машин
                int priority1 = getModelPriority(car1.getModel());
                int priority2 = getModelPriority(car2.getModel());
                if (priority1 != -1 && priority2 != -1 && priority2!=priority1) {
                    return Integer.compare(priority2, priority1);
                }
                return Integer.compare(car1.getCost(),car2.getCost());
            }

            private static int getModelPriority(String model) {
                if (model == null) return -1;

                String modelLower = model.toLowerCase();

                if (modelLower.contains("mercedes") || modelLower.contains("мерседес")) return 4;
                if (modelLower.contains("bmw") || modelLower.contains("бмв")) return 3;
                if (modelLower.contains("toyota") || modelLower.contains("тойота")) return 2;
                if (modelLower.contains("audi") || modelLower.contains("ауди")) return 1;

                return -1;
            }
        };
    }
    public static Comparator<Car> compare() {
        // Создаем анонимный класс Comparator
        return new Comparator<Car>() {
            @Override
            public int compare(Car car1, Car car2) {
                if(car1.equals(car2))
                    return 0;
                else if (car1.hashCode() > car2.hashCode())
                    return 1;
                else
                    return -1;
            }
        };
    }

    private Car(CarBuilder carBuilder){
        this.gosNumber = carBuilder.gosNumber;
        this.model = carBuilder.model;
        this.date = carBuilder.date;
        this.cost = carBuilder.cost;
        this.lastOwner = carBuilder.lastOwner;
    }

    public static CarBuilder builder(){
        return new CarBuilder();
    }

    public String getGosNumber() {
        return gosNumber;
    }

    public String getModel() {
        return model;
    }

    public int getDate() {
        return date;
    }
    public int getCost(){
        return cost;
    }

    // У каждого свое понятие вывода. Сделаете как вам удобнее через Геттеры
    @Override
    public String toString(){
        String cost = getCost()!=-1?(getCost()+""):"-Неизвестно-";
        String lastOwner = getLastOwner()!=null?getLastOwner():"-Неизвестно-";
        return String.format("\nМашина %s\nГос. номер:%s\nГод выпуска:%s год\nПрошлый владелец:%s\nЦена:%s ₽\n",getModel(),getGosNumber(),getDate(),lastOwner,cost);
    }

    @Override
    public int hashCode() {
        int result = gosNumber != null ? gosNumber.hashCode() : 0;
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (lastOwner != null ? lastOwner.hashCode() : 0);
        result = 31 * result + cost;
        result = 31 * result + date;
        return result;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return cost == car.cost &&
                date == car.date &&
                Objects.equals(gosNumber, car.gosNumber) &&
                Objects.equals(model, car.model) &&
                Objects.equals(lastOwner, car.lastOwner);
    }

    public String toStringWithHash(){
        String cost = getCost()!=-1?(getCost()+""):"-Неизвестно-";
        String lastOwner = getLastOwner()!=null?getLastOwner():"-Неизвестно-";
        return String.format("\nМашина %s\nГос. номер:%s\nГод выпуска:%s год\nПрошлый владелец:%s\nЦена:%s ₽\nHashCode:%s\n",getModel(),getGosNumber(),getDate(),lastOwner,cost, hashCode());
    }

    public String getLastOwner() {
        return lastOwner;
    }


    public static class CarBuilder{
        private String gosNumber;//Номер машины
        private String model;//Марка, модель авто
        private String lastOwner;//Имя прошлого хозяина, общее наименование
        private int cost = -1;//Цена машины
        private int date = -1;//Год выпуска

        public CarBuilder setGosNumber(String gos){
            this.gosNumber = gos;
            return this;
        }
        public CarBuilder setModel(String mod){
            this.model = mod;
            return this;
        }
        public CarBuilder setDate(int dat){
            this.date = dat;
            return this;
        }
        public CarBuilder setLastOwner(String lastOwner){
            this.lastOwner = lastOwner;
            return this;
        }
        public CarBuilder setCost(int cost){
            this.cost = cost;
            return this;
        }

        public Car build(){
            if (this.gosNumber!=null && this.model!=null && this.date!=-1){//Кто то просил int, int не проверяется на null
                //Допустим -1 недопустимое число, не позволяйте пользователям его вводить
                return new Car(this);
            }
            else
                throw new RuntimeException("Зполните все обязательные параметры: Гос. номер, модель и год выпуска!");
        }

    }
}