package leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class L020 {
    public boolean isValid1(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }

        s = s.trim();

        if (s.length() == 0) {
            return true;
        }

        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            }else if (ch == ')' || ch == ']' || ch == '}') {
                char resposing = map.get(ch);
                if (stack.isEmpty() || stack.pop() != resposing) {
                    return false;
                }
            }
        }

        if (stack.isEmpty()) {
            return true;
        }
        return false;
    }

    public boolean isValid(String s) {
        char[] stack = new char[s.length()+1];
        int index = 0;
        stack[index++] = '$';
        for(char c: s.toCharArray()){
            if(stack[index-1] == c-2)index--;
            else if(stack[index-1] == c-1) index--;
            else stack[index++] = c;
        }
        return index == 1;
    }
    public static void main(String[] args) {
        L020 l020 = new L020();
        int a = '(', b = ')', c = '[', d = ']', e = '{', f = '}';
        System.out.println(a+" "+b+" "+c+" "+d+" "+e+" "+f);
        String s = "()[]{}";
        boolean res = l020.isValid(s);
        System.out.println(res);
    }
}
