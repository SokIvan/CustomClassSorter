package com.aston;

import com.aston.controller.Controller;
import com.aston.customClasses.Car;
import com.aston.customClasses.Driver;
import com.aston.customClasses.Route;
import com.aston.fileworker.ControllerFile;
import com.aston.fileworker.FileReaderUtil;
import com.aston.fileworker.FileWriterUtil;
import com.aston.functionalClasses.BinarySearching.BinarySearch;
import com.aston.functionalClasses.BinarySearching.BinarySearchByFields;
import com.aston.functionalClasses.RandomAssembly.RandomCarList;
import com.aston.functionalClasses.RandomAssembly.RandomDriverList;
import com.aston.functionalClasses.RandomAssembly.RandomRouteList;
import com.aston.functionalClasses.Sorting.*;
import com.aston.functionalClasses.StreamArrayList.MyArrayList;
import com.aston.functionalClasses.ThreadСounter.MultiThreadedCounter;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class Main {

    public static MyArrayList<Object> cars = new MyArrayList<>();
    public static MyArrayList<Object> drivers = new MyArrayList<>();
    public static MyArrayList<Object> routes = new MyArrayList<>();

    public static MyArrayList<Object> curCollection = new MyArrayList<>();


    static Scanner scanner = new Scanner(System.in);
    private static MainInputControl inputController = new MainInputControl();





    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException, NoSuchFieldException, IllegalAccessException {

//        List<Driver> cars1 = RandomDriverList.create();
//        System.out.println(cars1);
//        SortContext<Driver> sortContext = new SortContext<>();
//        Comparator<Driver> carComparator = Driver.compareByExperience();
//
//        sortContext.setStrategy(new QuickSortStrategy<>());
//
//        sortContext.executeSort(cars1,Driver::getExperience,carComparator);
//
//        System.out.println("Sorted");
//        System.out.println(cars1);
//        String fieldName = "name";
//        String  value = "Simon";
//        Field field = Driver.class.getDeclaredField(fieldName);
//
//
//        BinarySearchByFields<Driver,String> searcher = new BinarySearchByFields<>();
//
//
//
//        int index = searcher.binarySearch(cars1,field,value,0,cars1.size()-1);
//        System.out.println(index);
//
//        System.out.println(field.getType());

        boolean running = true;
        int i;
        while (running) {
            switch (showStartMenu()) {
                case 1:
                    showInstructions();
                    break;
                case 2:
                    i = -1;
                    while(i==-1){
                        i = handleMethodInput();
                    }
                    if (i==1){
                        System.out.println("Метод поменял!");
                        inputController.changeStreamMethod();
                    }
                    handleDataInput();
                    if (i == 1){
                        System.out.println("Метод вернул!");
                        inputController.changeStreamMethod();
                    }
                    break;
                case 3:
                    i = -1;
                    while(i==-1){
                        i = handleChooseCollectionInput();
                    }
                    if (i == -2){
                        System.out.println("Коллекция пуста!");
                        break;
                    }
                    inputController.showCollections = true;
                    handleCollectionOperations();
                    inputController.showCollections = false;
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

    private static int showChooseStreamMethod(){
        System.out.println("\n=== МЕТОД ВВОДА ДАННЫХ ===");
        System.out.println("1) Через стримы");
        System.out.println("2) Обычный");
        System.out.print("Выберите опцию: ");

        return getIntInput();
    }

    private static int showChooseCollectionMethod(){
        System.out.println("\n=== ВЫБЕРИТЕ КОЛЛЕКЦИЮ ДЛЯ РАБОТЫ ДАННЫХ ===");
        System.out.println("1) Машины");
        System.out.println("2) Водители");
        System.out.println("3) Маршруты");
        System.out.print("Выберите опцию: ");

        return getIntInput();
    }


    public static Car HandleInputCar(){
        System.out.println("Введите название модели машины:");
        String model = scanner.nextLine();
        System.out.println("Введите госномер машины:");
        String gosnum = scanner.nextLine();
        System.out.println("Введите прошлого владельца машины:");
        String lastOwner = scanner.nextLine();
        System.out.println("Введите цену машины:");
        int cost = scanner.nextInt();
        System.out.println("Введите год выпуска машины:");
        int date = scanner.nextInt();
        return new Car.CarBuilder()
                .setModel(model)
                .setGosNumber(gosnum)
                .setDate(date)
                .setCost(cost)
                .setLastOwner(lastOwner)
                .build();
    }
    public static Driver HandleInputDriver(){
        System.out.println("Введите имя водителя:");
        String driverName = scanner.nextLine();
        System.out.println("Введите категорию прав водителя:");
        String category = scanner.nextLine();
        System.out.println("Введите рейтинг водителя:");
        String rate = scanner.nextLine();
        System.out.println("Введите возраст водителя:");
        int age = scanner.nextInt();
        System.out.println("Введите стаж водителя:");
        int experience = scanner.nextInt();


        return new Driver.DriverBuilder()
                .setName(driverName)
                .setCategory(category)
                .setAge(age)
                .setExperience(experience)
                .setRate(Double.valueOf(rate))
                .build();
    }
    public static Route HandleInputRoute(){
        System.out.println("Введите название маршрута:");
        String roadName = scanner.nextLine();
        System.out.println("Введите имя водителя:");
        String driverName = scanner.nextLine();
        System.out.println("Введите модель машины:");
        String carName = scanner.nextLine();
        System.out.println("Введите количество пассажиров:");
        int passangers = scanner.nextInt();
        System.out.println("Введите протяженность маршрута:");
        int distance = scanner.nextInt();


        return new Route.RouteBuilder()
                .setRoadName(roadName)
                .setDriverName(driverName)
                .setCarName(carName)
                .setDistanse(distance)
                .setPassengers(passangers)
                .build();
    }

    public static List<String> HandleInputFieldValue(){
        System.out.println("Введите имя поля класса:");
        String fieldName = scanner.nextLine();
        System.out.println("Введите значение поля:");
        String fieldValue = scanner.nextLine();
        List<String> list = new ArrayList<>();
        list.add(fieldName);
        list.add(fieldValue);
        System.out.print(fieldName);


        return list;
    }



    // Ввод данных
    private static int handleMethodInput() {
        return switch (showChooseStreamMethod()) {
            case 1 -> 1;
            case 2 -> 2;
            default -> -1;
        };
    }

    // Ввод данных
    private static int handleChooseCollectionInput() {
        switch (showChooseCollectionMethod()) {
            case 1:
                if (Main.cars==null || Main.cars.isEmpty()) {
                    return -2;
                }
                Main.curCollection = Main.cars;
                return 1;
            case 2:
                if (Main.drivers==null || Main.drivers.isEmpty()) {
                    return -2;
                }
                Main.curCollection = Main.drivers;
                return 2;
            case 3:
                if (Main.routes==null || Main.routes.isEmpty()) {
                    return -2;
                }
                Main.curCollection = Main.routes;
                return 3;
            default:
                return -1;
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
    private static void handleDataInput() throws IOException, ExecutionException, InterruptedException, NoSuchFieldException, IllegalAccessException {

        int i = 0;


        switch (showInputMethodMenu()) {
            case 1:
                showInputInstructions();
                break;
            case 2:
                i = handleFileInput();
                break;
            case 3:
                i = handleRandomInput();
                break;
            case 4:
                i = handleManualInput();
                break;
            case 5:
                return; // Выход в главное меню
            default:
                System.out.println("Неверный выбор.");
        }
        if (i==1){
            handleCollectionOperations();
        }
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
        System.out.println("При загрузке данных из файла убедитесь, что файл имеет следующий формат:\n" +
                "— Каждая запись — на новой строке.\n" +
                "— Поля разделены точкой с запятой (;).\n" +
                "— Обязательные поля: первые 3. Остальные могут отсутствовать.\n" +
                "Форматы данных в файлах:\n" +
                "МАШИНЫ: Госномер;Модель;Год выпуска;Цена;Имя прошлого хозяина\n" +
                "Пример: А123ВС777;Toyota Camry;2020;1500000;Иванов И.И.\n" +
                "ВОДИТЕЛИ: Имя;Категория;Стаж;Возраст;Рейтинг\n" +
                "Пример: Петров А.С.;B;5;30;4.75\n" +
                "МАРШРУТЫ: Водитель;Машина;Маршрут;Дистанция;Число пассажиров\n" +
                "Пример: Иванов И.И.;А123ВС777;Москва->Ярославль;250;3\n" +
                "Важно:Числа записываются с точкой (например: 4.5).");

    }

    // Ввод из файла
    private static int  handleFileInput() throws IOException {

        switch (showCollectionTypeMenu()) {
            case 1:
                showCollectionInstructions();
                return 0;
            case 2:
                inputController.loadCarsFromFile();
                break;
            case 3:
                inputController.loadDriversFromFile();
                break;
            case 4:
                inputController.loadRoutesFromFile();
                break;
            case 5:
                return 0;
            default:
                System.out.println("Неверный выбор типа коллекции.");
                return 0;
        }
        return 1;
    }


    // Меню выбора способа ввода
    private static int showRandomInputMenu() {
        System.out.println("\n=== ВЫБОР ТИПА КОЛЛЕКЦИИ ===");
        System.out.println("1) Машины");
        System.out.println("2) Водители");
        System.out.println("3) Маршруты");
        System.out.println("4) Выход");
        System.out.print("Выберите коллекцию: ");

        return getIntInput();
    }

    // Ввод из файла
    private static int handleRandomInput() throws IOException {
        switch (showRandomInputMenu()) {
            case 1:
                inputController.createRandomInput(1);
                break;
            case 2:
                inputController.createRandomInput(2);
                break;
            case 3:
                inputController.createRandomInput(3);
                break;
            case 4:
                return 0;
            default:
                System.out.println("Неверный выбор типа коллекции.");
                return 0;
        }
        return 1;
    }


    // Ручной ввод
    private static int handleManualInput() {
        switch (showCollectionTypeMenu()) {
            case 1:
                showCollectionInstructions();
                return 0;
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
                return 0;
            default:
                System.out.println("Неверный выбор типа коллекции.");
                return 0;
        }
        return 1;
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
        System.out.println("Машины - коллекция объектов типа Car(поля - gosNumber,model,lastOwner,cost,date)");
        System.out.println("Водители - коллекция объектов типа Driver(поля - name,category,experience,age,rate");
        System.out.println("Маршруты - коллекция объектов типа Route(поля - driverName,carName,roadName,distanse,passengers)");
        System.out.println("Для каждого типа доступны свои компараторы");
    }

    // Действия с коллекциями
    private static void handleCollectionOperations() throws ExecutionException, InterruptedException, NoSuchFieldException, IllegalAccessException, IOException {

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

        if (curCollection!=null && !curCollection.isEmpty() && !inputController.showCollections){
            if (curCollection.get(0) instanceof Car){

                cars.addAll(inputController.copyToCarArray(curCollection));
            } else if (curCollection.get(0) instanceof Driver){

                drivers.addAll(inputController.copyToDriverArray(curCollection));
            }else if (curCollection.get(0) instanceof Route){

                routes.addAll(inputController.copyToRouteArray(curCollection));
            }

        }
        curCollection = new MyArrayList<>();

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


    private static void showSortInstruction() {
        System.out.println("\n=== ИНСТРУКЦИЯ ПО СОРТИРОВКЕ ===");
        System.out.println("Выбор сортировки.\n" +
                "По умолчанию сортируется через equals и hashcode\n" +
                " Пузырьковая\n" +
                "Пузырьковая для четных полей date,experience,distance\n" +
                "Быстрая\n" +
                "Быстрая для четных полей date,experience,distance\n");
    }

    // Сортировка
    private static void handleSorting() throws IOException {
        int sortMethod = showSortMenu();
        if (sortMethod == 1){
            showSortInstruction();
            return;
        }
        if (sortMethod == 6) return;

        int comparatorChoice = showComparatorMenu();
        if (comparatorChoice == 6) return;

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
        if (Main.curCollection.get(0) instanceof Car){
            System.out.println("2)По названию модели");
            System.out.println("3)По гос номеру");
            System.out.println("4)По году выпуска");
            System.out.println("5)По популярности");
        } else if (Main.curCollection.get(0) instanceof Driver){
            System.out.println("2)По имени водителя");
            System.out.println("3)По категории прав");
            System.out.println("4)По стажу водителя");
            System.out.println("5)По надежности");
        } else if (Main.curCollection.get(0) instanceof Route){
            System.out.println("2)По имени водителя");
            System.out.println("3)По модели машины");
            System.out.println("4)По названию маршрута");
            System.out.println("5)По стоимости поездки");
        }



        System.out.println("6) Выход");
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
    private static void handleSearch() throws NoSuchFieldException, IllegalAccessException {

        int searchMethod = showSearchMenu();
        if (searchMethod == 1) {
            showSearchInstruction();
            return;
        }
        if (searchMethod == 4) return;

        inputController.performSearch(searchMethod);
    }

    private static void showSearchInstruction() {
        System.out.println("\n=== ИНСТРУКЦИЯ ПО ПОИСКУ ===");
        System.out.println("При поиске объекта важно учитывать реальные характеристики объекта\n"+
                "так как для некоторых тестов была добавлена валидация. Например возраст не может быть ниже 0\n"+
                "или выше 100, так как мы говорим о водителях; стаж не может быть больше возраста и так далее.\n"+
                "При неправильно введенных данных есть риск получить исключение.\n\n"+
                "Также важным моментом является сортировка. Бинарный поиск работает только в случае отсортированного\n"+
                "массива. Для поиска по целому объекту используйте сортировку по умолчанию. Для поиска по полю объекта\n"+
                "используйте соответствующую сортировку. Список сортировок ограничен.");
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
    private static void handleCounting() throws ExecutionException, InterruptedException {


        int searchMethod = showSearchMenu();
        if (searchMethod == 1) {
            showSearchInstruction();
            return;
        }
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

    public boolean showCollections = false;
    public boolean isStreamMethod = false;
    Comparator<Car> carComparator;
    Comparator<Driver> driverComparator;
    Comparator<Route> routeComparator;
    SortContext<Car> carSortContext = new SortContext<>();
    SortContext<Driver> driverSortContext = new SortContext<>();
    SortContext<Route> routeSortContext = new SortContext<>();

    public MyArrayList<Car> copyToCarArray(MyArrayList<Object> oldcars){
        return oldcars.stream()
                .filter(Car.class::isInstance)
                .map(Car.class::cast)
                .collect(Collectors.toCollection(MyArrayList::new));
    }
    public MyArrayList<Driver> copyToDriverArray(MyArrayList<Object> olddrivers){
        return olddrivers.stream()
                .filter(Driver.class::isInstance)
                .map(Driver.class::cast)
                .collect(Collectors.toCollection(MyArrayList::new));
    }
    public MyArrayList<Route> copyToRouteArray(MyArrayList<Object> oldroutes){
        return oldroutes.stream()
                .filter(Route.class::isInstance)
                .map(Route.class::cast)
                .collect(Collectors.toCollection(MyArrayList::new));
    }



    public void changeStreamMethod(){this.isStreamMethod = !this.isStreamMethod;}

    public void loadCarsFromFile() throws IOException {
        if (!Main.curCollection.isEmpty()){
            Main.curCollection = new MyArrayList<>();
        }
        Main.curCollection.addAll(ControllerFile.loadCarsFromFile(Main.scanner,isStreamMethod));
        System.out.println("Загрузка машин успешна!");
        System.out.println(Main.curCollection);
    }

    public void loadDriversFromFile() {
        if (!Main.curCollection.isEmpty()){
            Main.curCollection = new MyArrayList<>();
        }
        Main.curCollection.addAll(ControllerFile.loadDriversFromFile(Main.scanner,isStreamMethod));
        System.out.println("Загрузка водителей успешна!");
        System.out.println(Main.curCollection);
    }

    public void loadRoutesFromFile() {
        if (!Main.curCollection.isEmpty()){
            Main.curCollection = new MyArrayList<>();
        }
        Main.curCollection.addAll(ControllerFile.loadRoutesFromFile(Main.scanner,isStreamMethod));
        System.out.println("Загрузка маршрутов успешна!");
        System.out.println(Main.curCollection);
    }

    public void createCarsManually() {
        if (!Main.curCollection.isEmpty()){
            Main.curCollection = new MyArrayList<>();
        }
        if (isStreamMethod){
            Main.curCollection.addAll(Controller.createCarsWithStream(Main.scanner));
        }
        else {
            Main.curCollection.addAll(Controller.createCars(Main.scanner));
        }
    }

    public void createDriversManually() {
        if (!Main.curCollection.isEmpty()){
            Main.curCollection = new MyArrayList<>();
        }
        if (isStreamMethod){
            Main.curCollection.addAll(Controller.createDriverWithStream(Main.scanner));
        }
        else {
            Main.curCollection.addAll(Controller.createDrivers(Main.scanner));
        }
    }

    public void createRoutesManually() {
        if (!Main.curCollection.isEmpty()){
            Main.curCollection = new MyArrayList<>();
        }
        if (isStreamMethod){
            Main.curCollection.addAll(Controller.createRouteWithStream(Main.scanner));
        }
        else {
            Main.curCollection.addAll(Controller.createRoutes(Main.scanner));
        }
    }

    public void displayCurrentCollection() {
        System.out.println("Отображение текущей коллекции");
        System.out.println(Main.curCollection);
    }

    public void createRandomInput(int type){
        if (!Main.curCollection.isEmpty()){
            Main.curCollection = new MyArrayList<>();
        }
        if (type == 1){
            if (isStreamMethod){
                Main.curCollection.addAll(RandomCarList.createWithStream());
            }else{
                Main.curCollection.addAll(RandomCarList.create());
            }


        } else if (type == 2){
            if (isStreamMethod){
                Main.curCollection.addAll(RandomDriverList.createWithStream());
            }else{
                Main.curCollection.addAll(RandomDriverList.create());
            }
        } else if (type == 3){
            if (isStreamMethod){
                Main.curCollection.addAll(RandomRouteList.createWithStream());
            }else{
                Main.curCollection.addAll(RandomRouteList.create());
            }
        }

        System.out.println("Массив создан!");
        System.out.println(Main.curCollection);


    }

    public void sortAndDisplay(int sortMethod, int comparatorChoice, int outputMethod) throws IOException {


        if (Main.curCollection.get(0) instanceof Car){

            switch (sortMethod-1) {
                case 1 -> carSortContext.setStrategy(new ParallelBubbleSortStrategy<>());
                case 2 -> carSortContext.setStrategy(new ParallelEvenBubbleSortStrategy<>());
                case 3 -> carSortContext.setStrategy(new QuickSortStrategy<>());
                case 4 -> carSortContext.setStrategy(new QuickSortEvenStrategy<>());
            }
            switch (comparatorChoice) {
                case 1 ->carComparator = Car.compare();
                case 2 -> carComparator = Car.compareByModel();
                case 3 -> carComparator = Car.compareByGosNumber();
                case 4 -> carComparator = Car.compareByDate();
                case 5 -> carComparator = Car.compareByModelCustom();
            }


            MyArrayList<Car> newcars = copyToCarArray(Main.curCollection);
            carSortContext.executeSort(newcars,Car::getDate,carComparator);
            Main.curCollection = new MyArrayList<>(newcars);


        }else if (Main.curCollection.get(0) instanceof Driver){

            switch (sortMethod-1) {
                case 1 -> driverSortContext.setStrategy(new ParallelBubbleSortStrategy<>());
                case 2 -> driverSortContext.setStrategy(new ParallelEvenBubbleSortStrategy<>());
                case 3 -> driverSortContext.setStrategy(new QuickSortStrategy<>());
                case 4 -> driverSortContext.setStrategy(new QuickSortEvenStrategy<>());

            }
            switch (comparatorChoice) {
                case 1 ->driverComparator = Driver.compare();
                case 2 -> driverComparator = Driver.compareByName();
                case 3 -> driverComparator = Driver.compareByCategory();
                case 4 -> driverComparator = Driver.compareByExperience();
                case 5 -> driverComparator = Driver.compareByRateAndExperienceCustom();
            }


            MyArrayList<Driver> newdrivers = copyToDriverArray(Main.curCollection);
            driverSortContext.executeSort(newdrivers,Driver::getExperience,driverComparator);
            Main.curCollection = new MyArrayList<>(newdrivers);


        } else if (Main.curCollection.get(0) instanceof Route){

            switch (sortMethod-1) {
                case 1 -> routeSortContext.setStrategy(new ParallelBubbleSortStrategy<>());
                case 2 -> routeSortContext.setStrategy(new ParallelEvenBubbleSortStrategy<>());
                case 3 -> routeSortContext.setStrategy(new QuickSortStrategy<>());
                case 4 -> routeSortContext.setStrategy(new QuickSortEvenStrategy<>());
            }
            switch (comparatorChoice) {
                case 1 ->routeComparator = Route.compare();
                case 4 -> routeComparator = Route.compareByRoadName();
                case 2 -> routeComparator = Route.compareByDriverName();
                case 3 -> routeComparator = Route.compareByCarName();
                case 5 -> routeComparator = Route.compareByDistanceAndPassengersCustom();
            }


            MyArrayList<Route> newroutes = copyToRouteArray(Main.curCollection);
            routeSortContext.executeSort(newroutes,Route::getDistanse,routeComparator);
            Main.curCollection = new MyArrayList<>(newroutes);


        }
        if (outputMethod == 1 || outputMethod == 3) {
            System.out.println("Массив отсортирован!");
            System.out.println(Main.curCollection);
        }
        if (outputMethod == 2 || outputMethod == 3){
            if (Main.curCollection.get(0) instanceof Car){
                FileWriterUtil.writeCarsToFile("src/main/resources/Output/CarFileOutput.txt",copyToCarArray(Main.curCollection));
            } else if (Main.curCollection.get(0) instanceof Driver){
                FileWriterUtil.writeDriversToFile("src/main/resources/Output/DriverFileOutput.txt",copyToDriverArray(Main.curCollection));
            } else if (Main.curCollection.get(0) instanceof Route){
                FileWriterUtil.writeRoutesToFile("src/main/resources/Output/RouteFileOutput.txt",copyToRouteArray(Main.curCollection));
            }
        }
    }

    public void performSearch(int searchMethod) throws NoSuchFieldException, IllegalAccessException {
        System.out.println("Выполнение поиска...");
        int index = 0;
        if (searchMethod == 2){
            if (Main.curCollection.get(0) instanceof Car){
                Car car = Main.HandleInputCar();
                BinarySearch<Car> searcher= new BinarySearch<>();
                index = searcher.binarySearch(copyToCarArray(Main.curCollection),car,0,Main.curCollection.size()-1,Car.compare());
            }
            if (Main.curCollection.get(0) instanceof Driver){
                Driver driver = Main.HandleInputDriver();
                BinarySearch<Driver> searcher= new BinarySearch<>();
                index = searcher.binarySearch(copyToDriverArray(Main.curCollection),driver,0,Main.curCollection.size()-1,Driver.compare());
            }
            if (Main.curCollection.get(0) instanceof Route){
                Route route = Main.HandleInputRoute();
                BinarySearch<Route> searcher= new BinarySearch<>();
                index = searcher.binarySearch(copyToRouteArray(Main.curCollection),route,0,Main.curCollection.size()-1,Route.compare());
            }
        } else if (searchMethod == 3){
            if (Main.curCollection.get(0) instanceof Car){
                List<String> list = Main.HandleInputFieldValue();
                Field field = Car.class.getDeclaredField(list.get(0));
                System.out.println(field.getType());

                if (field.getType().toString().equals("double")){
                    BinarySearchByFields<Car,Double> searcher = new BinarySearchByFields<>();
                    index = searcher.binarySearch(copyToCarArray(Main.curCollection),field,Double.valueOf(list.get(1)),0,Main.curCollection.size()-1);
                } else if (field.getType().toString().equals("int")){
                    BinarySearchByFields<Car,Integer> searcher = new BinarySearchByFields<>();

                    index = searcher.binarySearch(copyToCarArray(Main.curCollection),field,Integer.valueOf(list.get(1)),0,Main.curCollection.size()-1);
                }else{
                    BinarySearchByFields<Car,String> searcher = new BinarySearchByFields<>();
                    index = searcher.binarySearch(copyToCarArray(Main.curCollection),field,list.get(1),0,Main.curCollection.size()-1);
                }


            }
            if (Main.curCollection.get(0) instanceof Driver){
                List<String> list = Main.HandleInputFieldValue();
                Field field = Driver.class.getDeclaredField(list.get(0));
                System.out.println(field.getType());
                if (field.getType().toString().equals("double")){
                    BinarySearchByFields<Driver,Double> searcher = new BinarySearchByFields<>();
                    index = searcher.binarySearch(copyToDriverArray(Main.curCollection),field,Double.valueOf(list.get(1)),0,Main.curCollection.size()-1);
                } else if (field.getType().toString().equals("int")){
                    BinarySearchByFields<Driver,Integer> searcher = new BinarySearchByFields<>();

                    index = searcher.binarySearch(copyToDriverArray(Main.curCollection),field,Integer.valueOf(list.get(1)),0,Main.curCollection.size()-1);
                }else{
                    BinarySearchByFields<Driver,String> searcher = new BinarySearchByFields<>();
                    index = searcher.binarySearch(copyToDriverArray(Main.curCollection),field,list.get(1),0,Main.curCollection.size()-1);
                }
            }
            if (Main.curCollection.get(0) instanceof Route){
                List<String> list = Main.HandleInputFieldValue();
                Field field = Route.class.getDeclaredField(list.get(0));
                System.out.println(field.getType());
                if (field.getType().toString().equals("double")){
                    BinarySearchByFields<Route,Double> searcher = new BinarySearchByFields<>();
                    index = searcher.binarySearch(copyToRouteArray(Main.curCollection),field,Double.valueOf(list.get(1)),0,Main.curCollection.size()-1);
                } else if (field.getType().toString().equals("int")){
                    BinarySearchByFields<Route,Integer> searcher = new BinarySearchByFields<>();

                    index = searcher.binarySearch(copyToRouteArray(Main.curCollection),field,Integer.valueOf(list.get(1)),0,Main.curCollection.size()-1);
                }else{
                    BinarySearchByFields<Route,String> searcher = new BinarySearchByFields<>();
                    index = searcher.binarySearch(copyToRouteArray(Main.curCollection),field,list.get(1),0,Main.curCollection.size()-1);
                }
            }
        }
        System.out.println("Index: "+index+"\n");

    }

    public void countOccurrences(int searchMethod) throws ExecutionException, InterruptedException {

        System.out.println("Выполнение поиска...");
        long times = 0;
        if (searchMethod == 2){
            if (Main.curCollection.get(0) instanceof Car){
                Car car = Main.HandleInputCar();
                times = MultiThreadedCounter.countOccurrences(copyToCarArray(Main.curCollection),car);

            }
            if (Main.curCollection.get(0) instanceof Driver){
                Driver driver = Main.HandleInputDriver();
                times = MultiThreadedCounter.countOccurrences(copyToDriverArray(Main.curCollection),driver);
            }
            if (Main.curCollection.get(0) instanceof Route){
                Route route = Main.HandleInputRoute();
                times = MultiThreadedCounter.countOccurrences(copyToRouteArray(Main.curCollection),route);
            }
        } else if (searchMethod == 3){
            if (Main.curCollection.get(0) instanceof Car){
                List<String> list = Main.HandleInputFieldValue();
                times = MultiThreadedCounter.countOccurrencesByField(Main.curCollection, list.get(0),list.get(1));
            }
            if (Main.curCollection.get(0) instanceof Driver){
                List<String> list = Main.HandleInputFieldValue();
                times = MultiThreadedCounter.countOccurrencesByField(Main.curCollection, list.get(0),list.get(1));
            }
            if (Main.curCollection.get(0) instanceof Route){
                List<String> list = Main.HandleInputFieldValue();
                times = MultiThreadedCounter.countOccurrencesByField(Main.curCollection, list.get(0),list.get(1));
            }
        }

        System.out.println("Times: "+times+"\n");
        System.out.println("Подсчет вхождений...");
    }

}