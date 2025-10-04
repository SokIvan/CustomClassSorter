package com.aston;

import com.aston.controller.Controller;
import com.aston.customClasses.Car;
import com.aston.customClasses.Driver;
import com.aston.customClasses.Route;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.util.*;

public class Main {

    public Collection<Car> cars;
    public Collection<Driver> drivers;
    public Collection<Route> routes;

    public Controller manualInputController;

    public Object[] curCollection;



    private static Scanner scanner = new Scanner(System.in);
    private static MainInputControl inputController = new MainInputControl();

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            switch (showStartMenu()) {
                case 1:
                    showInstructions();
                    break;
                case 2:
                    handleDataInput();
                    break;
                case 3:
                    handleCollectionOperations();
                    break;
                case 4:
                    running = false;
                    System.out.println("Выход из программы. До свидания!");
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    // Главное меню
    private static int showStartMenu() {
        System.out.println("\n=== ГЛАВНОЕ МЕНЮ ===");
        System.out.println("1) Инструкция");
        System.out.println("2) Ввести данные");
        System.out.println("3) Действия с коллекциями");
        System.out.println("4) Выйти");
        System.out.print("Выберите опцию: ");

        return getIntInput();
    }

    // Инструкция
    private static void showInstructions() {
        System.out.println("\n=== ИНСТРУКЦИЯ ===");
        System.out.println("1) Ввести данные - загрузка данных в коллекцию");
        System.out.println("2) Действия с коллекциями - операции с уже загруженными данными");
        System.out.println("3) Для навигации используйте цифровые клавиши");
        System.out.println("4) Для выхода на любом этапе используйте соответствующую опцию");
    }

    // Ввод данных
    private static void handleDataInput() {
        switch (showInputMethodMenu()) {
            case 1:
                showInputInstructions();
                break;
            case 2:
                handleFileInput();
                break;
            case 3:
                handleRandomInput();
                break;
            case 4:
                handleManualInput();
                break;
            case 5:
                return; // Выход в главное меню
            default:
                System.out.println("Неверный выбор.");
        }
        handleCurCollectionOperations();
    }

    // Меню выбора способа ввода
    private static int showInputMethodMenu() {
        System.out.println("\n=== ВВОД ДАННЫХ ===");
        System.out.println("1) Инструкция");
        System.out.println("2) Файл");
        System.out.println("3) Random");
        System.out.println("4) Вручную");
        System.out.println("5) Выход");
        System.out.print("Выберите способ ввода: ");

        return getIntInput();
    }

    // Инструкция по вводу
    private static void showInputInstructions() {
        System.out.println("\n=== ИНСТРУКЦИЯ ПО ВВОДУ ===");
        System.out.println("1) Файл - загрузка данных из файла");
        System.out.println("2) Раздел - загрузка данных из определенного раздела");
        System.out.println("3) Вручную - ручной ввод данных");
        System.out.println("4) Убедитесь что данные в правильном формате");
    }

    // Ввод из файла
    private static void handleFileInput() {
        System.out.print("Введите имя файла: ");
        String fileName = scanner.nextLine();

        switch (showCollectionTypeMenu()) {
            case 1:
                showCollectionInstructions();
                break;
            case 2:
                inputController.loadRandomCollectionFromFile(fileName);
                break;
            case 3:
                inputController.loadCarsFromFile(fileName);
                break;
            case 4:
                inputController.loadDriversFromFile(fileName);
                break;
            case 5:
                inputController.loadRoutesFromFile(fileName);
                break;
            case 6:
                return;
            default:
                System.out.println("Неверный выбор типа коллекции.");
        }
    }

    // Ввод Random
    private static void handleRandomInput() {
        System.out.println("Ввод Random...");
    }

    // Ручной ввод
    private static void handleManualInput() {
        switch (showCollectionTypeMenu()) {
            case 1:
                showCollectionInstructions();
                break;
            case 2:
                inputController.createCarsManually();
                break;
            case 3:
                inputController.createDriversManually();
                break;
            case 4:
                inputController.createRoutesManually();
                break;
            case 5:
                return;
            default:
                System.out.println("Неверный выбор типа коллекции.");
        }
    }

    // Меню выбора типа коллекции
    private static int showCollectionTypeMenu() {
        System.out.println("\n=== ВЫБОР ТИПА КОЛЛЕКЦИИ ===");
        System.out.println("1) Инструкция");
        System.out.println("2) Машины");
        System.out.println("3) Водители");
        System.out.println("4) Маршруты");
        System.out.println("5) Выход");
        System.out.print("Выберите тип коллекции: ");

        return getIntInput();
    }

    // Инструкция по типам коллекций
    private static void showCollectionInstructions() {
        System.out.println("\n=== ИНСТРУКЦИЯ ПО ТИПАМ КОЛЛЕКЦИЙ ===");
        System.out.println("1) Случайная - смешанная коллекция объектов");
        System.out.println("2) Машины - коллекция объектов типа Car");
        System.out.println("3) Водители - коллекция объектов типа Driver");
        System.out.println("4) Маршруты - коллекция объектов типа Route");
        System.out.println("5) Для каждого типа доступны свои компараторы");
    }

    // Действия с коллекциями
    private static void handleCollectionOperations() {
        if (!inputController.hasData()) {
            System.out.println("Сначала загрузите данные!");
            return;
        }

        boolean inCollectionMenu = true;
        while (inCollectionMenu) {
            switch (showCollectionOperationsMenu()) {
                case 1:
                    handleSorting();
                    break;
                case 2:
                    handleSearch();
                    break;
                case 3:
                    handleCounting();
                    break;
                case 4:
                    inputController.displayCurrentCollection();
                    break;
                case 5:
                    inCollectionMenu = false;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    // Действия с текущими коллекциями
    private static void handleCurCollectionOperations() {
        if (!inputController.hasData()) {
            System.out.println("Сначала загрузите данные!");
            return;
        }

        boolean inCollectionMenu = true;
        while (inCollectionMenu) {
            switch (showCollectionOperationsMenu()) {
                case 1:
                    handleSorting();
                    break;
                case 2:
                    handleSearch();
                    break;
                case 3:
                    handleCounting();
                    break;
                case 4:
                    inputController.displayCurrentCollection();
                    break;
                case 5:
                    inCollectionMenu = false;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }



    // Меню операций с коллекциями
    private static int showCollectionOperationsMenu() {
        System.out.println("\n=== ДЕЙСТВИЯ С КОЛЛЕКЦИЯМИ ===");
        System.out.println("1) Сортировка");
        System.out.println("2) Поиск");
        System.out.println("3) Подсчет вхождений");
        System.out.println("4) Показать коллекцию");
        System.out.println("5) Выход");
        System.out.print("Выберите операцию: ");

        return getIntInput();
    }

    // Сортировка
    private static void handleSorting() {
        int sortMethod = showSortMenu();
        if (sortMethod == 6) return;

        int comparatorChoice = showComparatorMenu();
        if (comparatorChoice == 7) return;

        int outputMethod = showOutputMenu();
        if (outputMethod == 4) return;

        inputController.sortAndDisplay(sortMethod, comparatorChoice, outputMethod);
    }

    // Меню сортировки
    private static int showSortMenu() {
        System.out.println("\n=== ВЫБОР СПОСОБА СОРТИРОВКИ ===");
        System.out.println("1) Инструкция");
        System.out.println("2) Пузырьковая");
        System.out.println("3) Пузырьковая(для четных)");
        System.out.println("4) Быстрая");
        System.out.println("5) Быстрая(для четных)");
        System.out.println("6) Выход");
        System.out.print("Выберите способ сортировки: ");

        return getIntInput();
    }

    // Меню компараторов
    private static int showComparatorMenu() {
        System.out.println("\n=== ВЫБОР КОМПАРАТОРА ===");
        // Потом
        System.out.println("1)По умолчанию");
        System.out.println("7) Выход");
        System.out.print("Выберите компаратор: ");

        return getIntInput();
    }

    // Меню вывода
    private static int showOutputMenu() {
        System.out.println("\n=== ВЫБОР СПОСОБА ВЫВОДА ===");
        System.out.println("1) В консоль");
        System.out.println("2) В файл");
        System.out.println("3) И консоль и файл");
        System.out.println("4) Выход");
        System.out.print("Выберите способ вывода: ");

        return getIntInput();
    }

    // Поиск
    private static void handleSearch() {
        int searchMethod = showSearchMenu();
        if (searchMethod == 4) return;

        inputController.performSearch(searchMethod);
    }

    // Меню поиска
    private static int showSearchMenu() {
        System.out.println("\n=== ВЫБОР СПОСОБА ПОИСКА ===");
        System.out.println("1) Инструкция");
        System.out.println("2) Целый объект");
        System.out.println("3) По полю и значению");
        System.out.println("4) Выход");
        System.out.print("Выберите способ поиска: ");

        return getIntInput();
    }

    // Подсчет вхождений
    private static void handleCounting() {

        int searchMethod = showSearchMenu();
        if (searchMethod == 4) return;

        inputController.countOccurrences(searchMethod);
    }

    // Вспомогательный метод для получения целочисленного ввода
    private static int getIntInput() {
        try {
            int input = scanner.nextInt();
            scanner.nextLine(); // очистка буфера
            return input;
        } catch (InputMismatchException e) {
            scanner.nextLine(); // очистка неверного ввода
            return -1;
        }
    }
}

// Заглушка для контроллера коллекций (ты заменишь на свою реализацию)
class MainInputControl {
    public boolean hasData() {
        return true; // Заглушка
    }

    public void loadRandomCollectionFromFile(String fileName) {
        System.out.println("Загрузка случайной коллекции из файла: " + fileName);
    }

    public void loadCarsFromFile(String fileName) {
        System.out.println("Загрузка машин из файла: " + fileName);
    }

    public void loadDriversFromFile(String fileName) {
        System.out.println("Загрузка водителей из файла: " + fileName);
    }

    public void loadRoutesFromFile(String fileName) {
        System.out.println("Загрузка маршрутов из файла: " + fileName);
    }

    public void createCarsManually() {
        System.out.println("Ручное создание коллекции машин");
    }

    public void createDriversManually() {
        System.out.println("Ручное создание коллекции водителей");
    }

    public void createRoutesManually() {
        System.out.println("Ручное создание коллекции маршрутов");
    }

    public void displayCurrentCollection() {
        System.out.println("Отображение текущей коллекции");
    }

    public void sortAndDisplay(int sortMethod, int comparatorChoice, int outputMethod) {
        System.out.println("Сортировка и вывод...");
    }

    public void performSearch(int searchMethod) {
        System.out.println("Выполнение поиска...");
    }

    public void countOccurrences(int searchMethod) {
        System.out.println("Подсчет вхождений...");
    }
}