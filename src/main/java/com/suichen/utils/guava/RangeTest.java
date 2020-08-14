package com.suichen.utils.guava;

import com.google.common.collect.ContiguousSet;
import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.Range;
import com.google.common.primitives.Ints;

public class RangeTest {
    private void printRange(Range<Integer> range) {
        System.out.println("[");
        for (int grade: ContiguousSet.create(range, DiscreteDomain.integers())) {
            System.out.println(grade+" ");
        }
        System.out.println("]");
    }

    private void testRange() {
        Range<Integer> range1 = Range.closed(0,9);
        System.out.println("[0,9]: ");
        printRange(range1);

        System.out.println("5 is present: "+range1.contains(5));
        System.out.println("(1,2,3) is present: "+range1.containsAll(Ints.asList(1,2,3)));
        System.out.println("Low Bound: "+range1.lowerEndpoint());
        System.out.println("Upper Bound: "+range1.upperEndpoint());


        Range<Integer> range2 = Range.open(0,9);
        Range<Integer> range3 = Range.openClosed(0, 9);
        Range<Integer> range4 = Range.closedOpen(0, 9);

        Range<Integer> range5 = Range.greaterThan(9);
        System.out.println("(9, infinity):");
        System.out.println("Low Bound: "+range5.lowerEndpoint());
        System.out.println("Upper Bound present: "+range5.hasUpperBound());

        Range<Integer> range6 = Range.closed(3,5);
        printRange(range6);

        System.out.println("[0,9] encloses [3,5]: "+range1.encloses(range6));

    }
    public static void main(String[] args) {

    }
}
