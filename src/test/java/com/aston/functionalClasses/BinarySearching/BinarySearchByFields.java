package com.aston.functionalClasses.BinarySearching;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchByFieldsTest {

    static class Person {
        public String name;
        public int age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }

    @Test
    void testSearchByFieldFound() {
        BinarySearchByFields<Person, String> search = new BinarySearchByFields<>();
        ArrayList<Person> list = new ArrayList<>();
        list.add(new Person("Alice", 30));
        list.add(new Person("Bob", 25));

        try {
            Field field = Person.class.getField("name");
            int index = search.binarySearch(list, field, "Bob", 0, list.size() - 1);
            assertEquals(1, index);
        } catch (IllegalAccessException e) {
            fail("Не должно было быть IllegalAccessException");
        } catch (NoSuchFieldException e) {
            fail("Поле name должно существовать");
        }
    }

    @Test
    void testSearchByFieldNotFound() {
        BinarySearchByFields<Person, String> search = new BinarySearchByFields<>();
        ArrayList<Person> list = new ArrayList<>();
        list.add(new Person("Alice", 30));

        try {
            Field field = Person.class.getField("name");
            int index = search.binarySearch(list, field, "Bob", 0, list.size() - 1);
            assertEquals(-1, index);
        } catch (IllegalAccessException e) {
            fail("Не должно было быть IllegalAccessException");
        } catch (NoSuchFieldException e) {
            fail("Поле name должно существовать");
        }
    }
}
