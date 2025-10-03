package com.aston.customClasses;

import java.util.Comparator;
import java.util.Objects;

public class Route {
    private final String driverName;//Имя водителя
    private final String carName;//Имя машинки, или модель
    private final String roadName;//Наименование маршрута, пример Москва->Ярославль
    private final int distanse;//протяженность маршрута
    private final int passengers;//число пассажиров


    private Route(RouteBuilder routeBuilder){
        this.driverName = routeBuilder.driverName;
        this.carName = routeBuilder.carName;
        this.roadName = routeBuilder.roadName;
        this.distanse = routeBuilder.distanse;
        this.passengers = routeBuilder.passengers;
    }

    public static Comparator<Route> compareByDriverName() {
        return Comparator.comparing(Route::getDriverName);
    }
    public static Comparator<Route> compareByCarName() {
        return Comparator.comparing(Route::getCarName);
    }
    public static Comparator<Route> compareByRoadName() {
        return Comparator.comparing(Route::getRoadName);
    }
    public static Comparator<Route> compareByDistanceAndPassengersCustom() {
        // Создаем анонимный класс Comparator
        return new Comparator<Route>() {
            @Override
            public int compare(Route road1, Route road2) {
                return Double.compare(calculate(road1),calculate(road2));
            }
            public Double calculate(Route road){
                return (double) (road.getPassengers()*(road.getDistanse()*10));
            }
        };
    }
    public static Comparator<Route> compare() {
        return new Comparator<Route>() {
            @Override
            public int compare(Route route1, Route route2) {
                if(route1.equals(route2))
                    return 0;
                else if (route1.hashCode() > route2.hashCode())
                    return 1;
                else
                    return -1;
            }
        };
    }


    public static RouteBuilder builder(){
        return new RouteBuilder();
    }

    public String getDriverName() {
        return driverName;
    }

    public String getCarName() {
        return carName;
    }

    public String getRoadName() {
        return roadName;
    }


    @Override
    public String toString(){
        String distanse = getDistanse()!=-1?(getDistanse()+""):"-Неизвестно-";
        String passengers = getPassengers()!=-1?(getPassengers()+""):"-Неизвестно-";
        return String.format("\nМаршрут %s\nВодитель: %s\nМашина: %s\nПассажиров: %s\nРасстояние: %s км\n",getRoadName(),getDriverName(),getCarName(),getPassengers(),getDistanse());
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(driverName);
        result = 31 * result + Objects.hashCode(carName);
        result = 31 * result + Objects.hashCode(roadName);
        result = 31 * result + distanse;
        result = 31 * result + passengers;
        return result;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Route route = (Route) o;

        return distanse == route.distanse &&
                passengers == route.passengers &&
                Objects.equals(driverName, route.driverName) &&
                Objects.equals(carName, route.carName) &&
                Objects.equals(roadName, route.roadName);
    }

    public int getDistanse() {
        return distanse;
    }

    public int getPassengers() {
        return passengers;
    }


    public static class RouteBuilder{
        private String driverName;
        private String carName;
        private String roadName;
        private int distanse = -1;
        private int passengers = -1;

        public RouteBuilder setDriverName(String driverName){
            this.driverName = driverName;
            return this;
        }
        public RouteBuilder setCarName(String carName){
            this.carName = carName;
            return this;
        }
        public RouteBuilder setRoadName(String roadName){
            this.roadName = roadName;
            return this;
        }
        public Route build(){
            return new Route(this);
        }

        public void setDistanse(int distanse) {
            this.distanse = distanse;
        }

        public void setPassengers(int passengers) {
            this.passengers = passengers;
        }
    }

}
