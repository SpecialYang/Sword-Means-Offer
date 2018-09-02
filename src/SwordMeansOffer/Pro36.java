package SwordMeansOffer;

import java.util.Scanner;

/**
 * Created by Special on 2018/8/8 20:12
 */
public class Pro36 {

    public static int FirstNotRepeatingChar(String str) {
        if(str != null) {
            int length = 'z' - 'A' + 1;
            int[] count = new int[length];
             for(int i = 0; i < str.length(); i++) {
                count[str.charAt(i) - 'A']++;
             }
             for(int i = 0; i < str.length(); i++) {
                 if(count[str.charAt(i) - 'A'] == 1) {
                     return i;
                 }
             }
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while(input.hasNext()) {
            String str = input.next();
            System.out.println(FirstNotRepeatingChar(str));
        }
    }
}
