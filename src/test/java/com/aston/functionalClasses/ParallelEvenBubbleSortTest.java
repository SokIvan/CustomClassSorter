package com.aston.functionalClasses;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.ToIntFunction;

import static org.junit.jupiter.api.Assertions.*;

class ParallelEvenBubbleSortTest {

    static class Person {
        String name;
        int age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return name + "(" + age + ")";
        }
    }

    @Test
    void testParallelEvenBubbleSort_basic() {
        List<Person> people = new ArrayList<>(Arrays.asList(
                new Person("A", 4),
                new Person("B", 2),
                new Person("C", 3),
                new Person("D", 6),
                new Person("E", 1)
        ));

        ToIntFunction<Person> keyExtractor = (Person p) -> p.age;

        try {
            ParallelEvenBubbleSort.parallelEvenBubbleSort(
                    people,
                    keyExtractor,
                    Comparator.comparingInt((Person p) -> p.age)
            );
        } catch (Exception e) {
            fail("Сортировка не должна выбрасывать исключение: " + e.getMessage());
        }

        // Проверяем чётные (4, 2, 6) должны быть отсортированы → 2,4,6
        List<Integer> evenAges = new ArrayList<>();
        for (Person p : people) {
            if (p.age % 2 == 0) {
                evenAges.add(p.age);
            }
        }

        assertEquals(Arrays.asList(2, 4, 6), evenAges);
    }

    @Test
    void testParallelEvenBubbleSort_withNullList() {
        try {
            ParallelEvenBubbleSort.parallelEvenBubbleSort(
                    null,
                    (Person p) -> p.age,
                    Comparator.comparingInt((Person p) -> p.age)
            );
        } catch (Exception e) {
            fail("Не должно быть исключения на null-списке: " + e.getMessage());
        }
    }

    @Test
    void testParallelEvenBubbleSort_withSingleElement() {
        List<Person> people = new ArrayList<>(Collections.singletonList(new Person("Solo", 10)));

        try {
            ParallelEvenBubbleSort.parallelEvenBubbleSort(
                    people,
                    (Person p) -> p.age,
                    Comparator.comparingInt((Person p) -> p.age)
            );
        } catch (Exception e) {
            fail("Не должно быть исключения для одного элемента: " + e.getMessage());
        }

        assertEquals(1, people.size());
        assertEquals(10, people.get(0).age);
    }
}
