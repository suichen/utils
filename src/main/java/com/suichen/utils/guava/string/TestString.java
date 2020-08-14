package com.suichen.utils.guava.string;

import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

public class TestString {
    public static void main(String[] args) {
        Joiner joiner = Joiner.on(";").skipNulls();
        System.out.println(joiner.join("Harry", null, "Ron", "Hermione"));
        Joiner joiner1 = Joiner.on(";").useForNull("null");

        String[] temps = ",a,,b,".split(",");
        for (int i = 0; i < temps.length; i++) {
            System.out.print(temps[i]);
        }
        System.out.println();

        Splitter.on(",").trimResults().omitEmptyStrings()
                .split("foo,bat,,   qux");


    }
}
