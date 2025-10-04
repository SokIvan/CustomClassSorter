package com.aston.functionalClasses.RandomAssembly;
import com.aston.customClasses.Route;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RandomRouteArray extends RandomList{
    private static final int MIN_PASSENGERS = 1;
    private static final int MAX_PASSENGERS = 8;
    private static final Map<String, Integer> ROUTES = Map.of(
            "Москва -> Тверь", 181,
            "Москва -> Ярославль", 276,
            "Москва -> Санкт-Петербург", 705,
            "Москва -> Тула", 199,
            "Москва -> Дубна", 131,
            "Москва -> Домодедово", 15,
            "Тверь -> Москва", 181,
            "Тверь -> Великий Новгород", 394,
            "Тверь -> Ярославль", 220,
            "Тверь -> Рязань", 392
    );

    public static List<Route> create() {
        int length = getIntInRange(MIN_LENGTH, MAX_LENGTH);
        List<Route> routes = new ArrayList<>();
        for (int i = 0; i < length; i++)
            routes.add(getInstance());
        return routes;
    }

    public static Route getInstance(){
        Route.RouteBuilder builder = new Route.RouteBuilder()
                .setDriverName(getName())
                .setCarName(getModel());
        String [] roadKeyArray = ROUTES.keySet().toArray(new String[0]);
        String roadName = getRandomConst(roadKeyArray);
        builder.setRoadName(roadName);
        builder.setDistanse(ROUTES.get(roadName));
        if (Math.random() > 0.5) {
            builder.setPassengers(getIntInRange(MIN_PASSENGERS, MAX_PASSENGERS));
        }
        return builder.build();
    }
}
