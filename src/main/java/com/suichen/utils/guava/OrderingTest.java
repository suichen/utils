package com.suichen.utils.guava;

import com.google.common.collect.Ordering;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class OrderingTest {

    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(new Integer(5));
        numbers.add(new Integer(2));
        numbers.add(new Integer(15));
        numbers.add(new Integer(51));
        numbers.add(new Integer(53));
        numbers.add(new Integer(35));
        numbers.add(new Integer(45));
        numbers.add(new Integer(32));
        numbers.add(new Integer(43));
        numbers.add(new Integer(16));

        Ordering ordering = Ordering.natural();
        System.out.println("Input List:");
        System.out.println(numbers);

        Collections.sort(numbers, ordering);
        System.out.println("Sorted List:");
        System.out.println(numbers);

        System.out.println("===================");
        System.out.println("List is sorted: "+ordering.isOrdered(numbers));
        System.out.println("Minimum: "+ordering.min(numbers));
        System.out.println("Maxium: "+ordering.max(numbers));

        Collections.sort(numbers, ordering.reverse());
        System.out.println("Reverse: "+numbers);

        numbers.add(null);
        System.out.println("Null added to Sorted List");
        System.out.println(numbers);

        Collections.sort(numbers, ordering.nullsFirst());
        System.out.println("Null first Sorted List:");
        System.out.println(numbers);
        System.out.println("=========================");


    }
}
