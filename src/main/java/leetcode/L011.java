package leetcode;

public class L011 {
    public int maxArea(int[] height) {
        int area = 0;

        int n = height.length;
        int left = 0, right = n - 1;

        for (; left < right; ){
            int wid = (right - left);
            int top = Math.min(height[left], height[right]);
            if (wid*top > area) {
                area = wid*top;
            }

            if (height[left] <= height[right]) {
                while (left < right) {
                    if (height[left+1] > height[left]) {
                        left += 1;
                        break;
                    }
                    left++;
                }
            }else if (height[right] < height[left]) {
                while (right > left) {
                    if (height[right-1] > height[right]) {
                        right--;
                        break;
                    }
                    right--;
                }
            }
        }

        return area;
    }
    public static void main(String[] args) {
        L011 l011 = new L011();
        int[] num = new int[]{
                1,8,6,2,5,4,8,9,7
        };
        int area = l011.maxArea(num);
        System.out.println(area);
    }
}
