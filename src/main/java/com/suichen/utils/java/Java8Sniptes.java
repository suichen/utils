package com.suichen.utils.java;

import java.util.Arrays;
import java.util.OptionalInt;

public class Java8Sniptes {

    public static OptionalInt arrayMax(int[] numbers) {
        return Arrays.stream(numbers).max();
    }
}
