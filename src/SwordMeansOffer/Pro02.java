package SwordMeansOffer;

import java.util.Scanner;

/**
 * Created by Special on 2018/6/7 8:58
 */
public class Pro02 {
    static final String REP = "%20";

    /**
     * 常规解法，开辟一个新的空间进行处理结果，最后再copy到原空间上。
     * @param str
     * @return
     */
    public static String replaceSpace1(StringBuffer str) {
        String s = str.toString();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == ' '){
                sb.append(REP);
            }else {
                sb.append(s.charAt(i));
            }
        }
        str.delete(0, str.length());
        str.append(sb);
        return str.toString();
    }

    public static String replaceSpace2(StringBuffer str) {
        int spaceNum = 0;
        for(int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == ' ') {
                spaceNum++;
            }
        }
        int oldLength = str.length();
        str.setLength(oldLength + spaceNum * 2);
        for(int i = oldLength - 1; i >= 0; i--){
            if(str.charAt(i) != ' ') {
                str.setCharAt(i + 2 * spaceNum, str.charAt(i));
            }else {
                spaceNum--;
                int length = i + 2 * spaceNum;
                str.setCharAt(length, '%');
                str.setCharAt(length + 1, '2');
                str.setCharAt(length + 2, '0');
            }
        }
        return str.toString();
    }

    /**
     * 剑指Offer上的标准解法
     * @param str
     * @return
     */
    public static String replaceSpace3(StringBuffer str) {
        int spaceNum = 0;
        for(int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == ' ') {
                spaceNum++;
            }
        }
        int oldLength = str.length();
        str.setLength(oldLength + spaceNum * 2);
        int newLength = str.length();
        for(oldLength--, newLength--; oldLength >= 0 && oldLength < newLength; oldLength--){
            if(str.charAt(oldLength) != ' ') {
                str.setCharAt(newLength--, str.charAt(oldLength));
            }else {
                str.setCharAt(newLength--, '0');
                str.setCharAt(newLength--, '2');
                str.setCharAt(newLength--, '%');
            }
        }
        return str.toString();
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while(input.hasNext()){
            StringBuffer str = new StringBuffer(input.nextLine());
//            System.out.println(replaceSpace1(str));
//            System.out.println(str.toString());
//            System.out.println(replaceSpace2(new StringBuffer(str)));
//            System.out.println(replaceSpace2(str));
//            System.out.println(str.toString());
            System.out.println(replaceSpace3(str));
            System.out.println(str.toString());
        }
    }
}
