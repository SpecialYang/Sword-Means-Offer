package SwordMeansOffer;

import java.util.Scanner;

/**
 * Created by Special on 2018/9/5 17:06
 */
public class Pro54 {

    /**
     * 第一次的做法，仅过了一半，看看就行了
     * @param str
     * @param pattern
     * @return
     */
    public static boolean match(char[] str, char[] pattern) {
        if(str == null || pattern == null) {
            return false;
        }
        int i = 0, j = 0;
        while (i < str.length && j < pattern.length) {
            char ch1 = str[i];
            char ch2 = pattern[j];
            if(ch1 == ch2 || ch2 == '.') {
                i++;
                j++;
            } else if(ch2 == '*') {
                if(j == 0 || (pattern[j - 1] != ch1 && pattern[j - 1] != '.')) {
                    return false;
                } else {
                    i++;
                    if(i == str.length) {
                        return true;
                    }
                }
            } else {
                if ((j + 1) < pattern.length && pattern[j + 1] == '*') {
                    if(ch1 != pattern[j - 1]) {
                        j += 2;
                    }else {
                        i++;
                    }
                } else {
                    return false;
                }
            }
        }
        return i == str.length && j == pattern.length;
    }


    /**
     * 有限状态自动机做法
     * @param str
     * @param pattern
     * @return
     */
    public static boolean match2(char[] str, char[] pattern) {
        if(str == null || pattern == null) {
            return false;
        }
        return matchCore(str, 0, pattern, 0);
    }

    public static boolean matchCore(char[] str, int index1, char[] pattern, int index2) {
        if(index1 == str.length && index2 == pattern.length) {
            return true;
        }
        if(index1 != str.length && index2 == pattern.length) {
            return false;
        }
        //若待匹配的下一位为'*'
        if((index2 + 1) < pattern.length && pattern[index2 + 1] == '*') {
            if(index1 < str.length && (str[index1] == pattern[index2] || pattern[index2] == '.')) {
                return matchCore(str, index1 + 1, pattern, index2)
                        || matchCore(str, index1, pattern, index2 + 2);
            }else {
                return matchCore(str, index1, pattern, index2 + 2);
            }
        }
        //若待匹配的下一位不为'*'
        else {
            if(index1 < str.length && (str[index1] == pattern[index2] || pattern[index2] == '.')) {
                return matchCore(str, index1 + 1, pattern, index2 + 1);
            } else {
                return false;
            }
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while(input.hasNext()) {
            String str1 = input.next();
            String str2 = input.next();
            System.out.println(match2(str1.toCharArray(), str2.toCharArray()));
        }
    }
}
