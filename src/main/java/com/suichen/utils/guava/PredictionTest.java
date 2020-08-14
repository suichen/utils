package com.suichen.utils.guava;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicates;

public class PredictionTest {
    public double sqrt(double input) {
        Preconditions.checkArgument(input > 0.0, "Illegal Argument passed." +
                "Negative value %s.", input);
        return Math.sqrt(input);
    }

    public int sum(Integer a, Integer b) {
        Preconditions.checkNotNull(a, "Illegal Argument passed. First parameter is null.");
        Preconditions.checkNotNull(b, "Illegal Argument passed. Second parameter is null.");
        return a+b;
    }

    public int getValue(int index) {
        int[] data = {1,2,3,4,5};
        Preconditions.checkElementIndex(index, data.length, "Illegal Argument passed. Invalid index.");
        return data[index];
    }

    public static void main(String[] args) {

    }
}
