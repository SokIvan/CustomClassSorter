package com.aston.functionalClasses.RandomAssembly;

import java.util.Random;

public abstract class RandomList {
    protected static int MIN_LENGTH = 3;
    protected static int MAX_LENGTH = 10;
    protected static final String[] NAMES = {"Соловьева И.П.", "Иванов И.И.", "Петров П.А.", "Сидоров Н.Н.", "Кузнецов А.А", "Калинина М.И.", "Николаева Т.А.", "Миронов Е.И.", "Ким А.А.", "Королев П.Г.", "Амарян Г.Г.", "Антонова И.И."};
    protected static final String[] MODELS = {"Mercedes", "BMW", "Toyota", "Audi", "Volvo", "Volkswagen", "Skoda", "KIA", "Alfa Romeo", "Opel"};
    protected static Random random = new Random();

    protected static String getModel(){
        return MODELS[(int)(Math.random() * MODELS.length)];
    }
    protected static String getName(){
        return getRandomConst(NAMES);
    }

    protected static int getIntInRange(int minVal, int maxVal){
        return random.nextInt(maxVal - minVal + 1) + minVal;
    }
    protected static String getRandomConst(String[] CONSTANTS){
        return CONSTANTS[random.nextInt(CONSTANTS.length)];
    }
    protected static char getRandomConst(char[] CONSTANTS){
        return CONSTANTS[random.nextInt(CONSTANTS.length)];
    }
    protected static double getDoubleInRange(double minValue, double maxValue){
        return minValue + Math.random() * (maxValue - minValue);
    }
}
