package leetcode;

import java.util.ArrayList;
import java.util.List;

public class L093 {
    int[] segmentIp = new int[4];
    List<String> ans = new ArrayList<>();

    private void findIp(String s, int index, int startIndex) {

        if (index == 4 && startIndex == s.length()) {
            StringBuffer ipBuffer = new StringBuffer();
            for (int i = 0; i < 4; i++) {
                ipBuffer.append(segmentIp[i]+".");
            }
            ans.add(ipBuffer.substring(0, ipBuffer.length()-1));
            return;
        }

        if (startIndex >= s.length() || index == 4) {
            return;
        }

        if (s.charAt(startIndex) == '0') {
            segmentIp[index] = 0;
            findIp(s, index+1, startIndex+1);
            return;
        }

        for (int endIndex = startIndex; endIndex < s.length(); endIndex++) {
            int segIp = Integer.valueOf(s.substring(startIndex, endIndex+1));
            if (segIp > 0 && segIp <= 255) {
                segmentIp[index] = segIp;
                findIp(s, index+1, endIndex+1);
            }else {
                break;
            }
        }

    }
    public List<String> restoreIpAddresses(String s) {
        if (s == null || s == "") {
            return new ArrayList<>();
        }

        findIp(s, 0, 0);

        return ans;
    }
    public static void main(String[] args) {
        L093 l093 = new L093();

        String s = "101023";

        List<String> ans = l093.restoreIpAddresses(s);
        System.out.println();
    }
}
