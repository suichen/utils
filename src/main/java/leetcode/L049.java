package leetcode;

import java.util.*;

public class L049 {
    public List<List<String>> groupAnagrams(String[] strs) {

        if (strs == null) {
            return new ArrayList<>();
        }

        int len = strs.length;

        Map<String, List<String>> map = new HashMap<>();

        for (int i = 0; i < len; i++) {
            char[] chars = strs[i].toCharArray();
            Arrays.sort(chars);
            String str = String.valueOf(chars);
            if (!map.containsKey(str)) {
                map.put(str, new ArrayList<>());
            }
            map.get(str).add(strs[i]);
        }

        return new ArrayList<>(map.values());
    }
    public static void main(String[] args) {
        L049 l049 = new L049();
        String[] strs = new String[] {
                "eat", "tea", "tan", "ate", "nat", "bat"
        };

        List<List<String>> res = l049.groupAnagrams(strs);
        System.out.println();
    }
}
