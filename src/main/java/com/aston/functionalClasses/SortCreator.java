package com.aston.functionalClasses;


//interface SortStrategy{
//    Collection<?> sort(Collection<?> collection);
//}

// class SortCarStrategy implements SortStrategy{

//     @Override
//     public Collection<Car> sort(Collection<?> collection){
//         return null;
//     }
// }

// class SortDriverStrategy implements SortStrategy{

//     public Collection<Driver> sort(Collection<?> collection){
//         return null;
//     }
// }

// class SortRoadStrategy implements SortStrategy{

//     public Collection<Route> sort(Collection<?> collection){
//         return null;
//     }
// }










public class SortCreator {

    SortStrategy brain;


    public SortCreator() {
    }




    // public Collection<?> sort(Collection<?> collection){
    //     if (collection == null || collection.isEmpty()) return null;

    //     Object firstElement = collection.toArray()[0];
    //     if (firstElement instanceof Car){
    //         return null;
    //         //return (new SortCarStrategy()).sort(collection);
    //     }
    //     if (firstElement instanceof Driver){
    //         return null;
    //         //return (new SortDriverStrategy).sort(collection);
    //     }
    //     if (firstElement instanceof Car){
    //         return null;
    //         //return (new SortCarStrategy).sort(collection);
    //     }
    //     return collection;
    // }
}
