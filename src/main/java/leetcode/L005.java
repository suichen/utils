package leetcode;

public class L005 {

    //TODO
    public String longestPalindrome(String s) {

        int n = s.length();
        int[][] dp = new int[s.length()][s.length()];
        String ans = "";

        for (int length = 0; length < n; length++) {
            for (int beginIndex = 0; beginIndex + length < n; beginIndex++) {
                int endIndex = beginIndex + length;
                if (length == 0) {
                    dp[beginIndex][endIndex] = 1;
                } else if (length == 1) {
                    dp[beginIndex][endIndex] = s.charAt(beginIndex) == s.charAt(endIndex) ? 1 : 0;
                } else {
                    dp[beginIndex][endIndex] = dp[beginIndex+1][endIndex-1]
                            * (s.charAt(beginIndex) == s.charAt(endIndex) ? 1 : 0);
                }

                if (dp[beginIndex][endIndex] == 1)         {
                    if (length+1 > ans.length()) {
                        ans = s.substring(beginIndex, beginIndex+length+1);
                    }
                }
            }
        }
        return ans;

    }


    public String longestPalindrome2(String s){
        if(s.length()==1||s.length()==0) return s;
        String maxstring=s.substring(0,1);
        int[] arg=new int[2];
        arg[0]=0;
        arg[1]=1;
        for(int i=0;i<s.length();){
            i=find(i,arg,s);
        }
        return s.substring(arg[0],arg[1]);
    }
    public int find(int i,int[] arg,String s){
        int same=1;
        for(;i+same<s.length();same++){
            if(s.charAt(i)!=s.charAt(i+same))
                break;
        }
        int result=i+same;
        int high=i+same;
        int low=i-1;
        for(;high<s.length()&&low>=0;high++,low--){
            if(s.charAt(high)!=s.charAt(low))
                break;
        }
        if(arg[1]-arg[0]<high-low-1){
            arg[1]=high;
            arg[0]=low+1;
        }
        return result;

    }
    public static void main(String[] args) {
        L005 l005 = new L005();
        String res = l005.longestPalindrome("acdaaaaaddcdd");
        System.out.println(res);
    }
}
