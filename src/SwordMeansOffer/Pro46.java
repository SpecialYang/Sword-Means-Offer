package SwordMeansOffer;

import java.util.Arrays;

/**
 * Created by Special on 2018/8/20 21:36
 */
public class Pro46 {

    public static String ReverseSentence(String str) {
        return str.lastIndexOf(" ") == -1 ? str : str.substring(str.lastIndexOf(" ") + 1)
                + " " + ReverseSentence(str.substring(0, str.lastIndexOf(" ")));
    }

    public static String ReverseSentence2(String str) {
        if(str == null || str.equals("")) {
            return str;
        }
        char[] chars = str.toCharArray();
        reverse(chars, 0, str.length() - 1);
        int start = 0;
        for(int i = 0; i < chars.length; i++) {
            if(chars[i] != ' ') {
                start = i;
                while((++i) < chars.length && chars[i] != ' ');
                reverse(chars, start, i - 1);
            }
        }
        return new String(chars);
    }

    public static void reverse(char[] chars, int start, int end) {
        for(int i = start, j = end; i < j; i++, j--) {
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
        }
    }

    public static String ReverseSentence3(String str) {
        if(str == null || str.equals("")) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        StringBuilder temp = new StringBuilder();
        for(int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == ' ') {
                sb.insert(0, temp.toString());
                sb.insert(0, " ");
                temp.delete(0, temp.length());
            } else {
                temp.append(str.charAt(i));
            }
        }
        sb.insert(0, temp.toString());
        return sb.toString();
    }

    public static void main(String[] args) {
        String str1 = " 23       4";
        System.out.println(Arrays.toString(str1.split(" ")));
        String str = "I am a student.";
        System.out.println(ReverseSentence3(str));
    }
}
