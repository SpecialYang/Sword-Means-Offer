package SwordMeansOffer;

/**
 * Created by Special on 2018/8/19 21:48
 */
public class Pro45 {

    /**
     * 拼接 + 截取
     * @param str
     * @param n
     * @return
     */
    public String LeftRotateString(String str,int n) {
        if(str != null) {
            int length = str.length();
            if(n > 0 && n < length) {
                str = str + str;
                str = str.substring(n, length + n);
            }
        }
        return str;
    }


    public String LeftRotateString2(String str,int n) {
        if(str != null) {
            char[] chars = str.toCharArray();
            int length = str.length();
            if (n > 0 && n < length) {
                reverse(chars, 0, n - 1);
                reverse(chars, n, str.length() - 1);
                reverse(chars, 0, str.length() - 1);
            }
            str = new String(chars);
        }
        return str;
    }

    /**
     * 翻转数组
     * @param chars
     * @param start
     * @param end
     */
    public void reverse(char[] chars, int start, int end) {
        for(int i = start, j = end; i < j; i++, j--) {
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
        }
    }
}
