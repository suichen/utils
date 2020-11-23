package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class L056 {

    List<int[]> ans = new ArrayList<>();

    private void findEdge(int index, int[][] intervals) {
        int length = intervals.length;

        for (int i = index; i < length; i++) {
            int start = intervals[i][0], end = intervals[i][1];
            int[] temp = new int[2];

           if (ans.isEmpty() || (ans.get(ans.size()-1)[1] < start)){
                temp[0] = start;
                temp[1] = end;
                ans.add(temp);
            }else {
               ans.get(ans.size()-1)[1] = Math.max(end, ans.get(ans.size()-1)[1]);
           }
        }
    }
    public int[][] merge(int[][] intervals) {

        int row = intervals.length;

        //按第一个元素已经排好序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        findEdge(0, intervals);

        return ans.toArray(new int[ans.size()][]);
    }
    public static void main(String[] args) {

        L056 l056 = new L056();
        int[][] intervals = new int[][] {
                {
                    1,4
                },
                {
                    4,5
                },
                {
                    2,6
                }
        };

        l056.merge(intervals);
    }
}
