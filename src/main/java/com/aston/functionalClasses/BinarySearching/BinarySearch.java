package com.aston.functionalClasses.BinarySearching;
import com.aston.functionalClasses.StreamArrayList.MyArrayList;

import java.util.ArrayList;
import java.util.Comparator;

public class BinarySearch<T>{

    public int binarySearch(MyArrayList<T> values, T valueToFind, int l, int r, Comparator<? super T> comparator) {
        if (l == r) {
            if(comparator.compare(values.get(l), valueToFind) == 0)
                return l;
            else
                return -1;
        }
        int m = l + (r - l) / 2;
        if (comparator.compare(valueToFind, values.get(m)) > 0) {
            return binarySearch(values, valueToFind, m + 1, r, comparator);
        } else if (comparator.compare(valueToFind, values.get(m)) < 0) {
            return binarySearch(values, valueToFind, l, m, comparator);
        }
        return m;
    }
}
