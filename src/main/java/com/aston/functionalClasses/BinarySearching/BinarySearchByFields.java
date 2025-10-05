package com.aston.functionalClasses.BinarySearching;

import com.aston.functionalClasses.StreamArrayList.MyArrayList;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class BinarySearchByFields<T, K>{

    public int binarySearch(MyArrayList<T> values, Field fieldWhereFind, Comparable<K> valueToFind, int l, int r) throws IllegalAccessException {
        fieldWhereFind.setAccessible(true);
        if (l == r) {
            if(valueToFind.compareTo((K) (fieldWhereFind.get(values.get(l)))) == 0)
                return l;
            else
                return -1;
        }
        int m = l + (r - l) / 2;
        if (valueToFind.compareTo((K) fieldWhereFind.get(values.get(m))) > 0) {
            return binarySearch(values, fieldWhereFind, valueToFind, m + 1, r);
        } else if (valueToFind.compareTo((K) fieldWhereFind.get(values.get(m))) < 0) {
            return binarySearch(values, fieldWhereFind, valueToFind, l, m);
        }
        return m;
    }
}