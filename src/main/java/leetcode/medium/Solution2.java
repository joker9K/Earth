package leetcode.medium;

import java.time.LocalDate;

/**
 * Created by zhangwt n 2018/8/29.
 */
public class Solution2 {
    public int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        String temp = "";
        int max = 0;
        for (char ch:chars){
            temp = temp+String.valueOf(ch);
            if (!temp.contains(String.valueOf(ch))){
                if (temp.length()>max) {
                    max = temp.length();
                }
            }else {
                int index = temp.indexOf(String.valueOf(ch));
                temp = temp.substring(index+1);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(LocalDate.now().getDayOfMonth());
    }
}
