package SwordMeansOffer;

import java.util.*;

/**
 * Created by Special on 2018/7/20 21:10
 */
public class Pro29 {

    //------------------方法一----------------
    /**
     * 带重复字符的全排列问题
     * 利用set来去重
     * @param str
     * @return
     */
    public static ArrayList<String> Permutation(String str) {
        ArrayList<String> result = new ArrayList<>();
        if(str == null || str.equals("")) {
            return result;
        }
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        Set<String> set = new LinkedHashSet<>();
        boolean[] vis = new boolean[chars.length];
        char[] temp = new char[chars.length];
        permutation(0, temp, chars, set, vis);
        result.addAll(set);
        return result;
    }

    /**
     * dfs形式遍历字符数组
     * 利用访问数组来确定某一位置的元素是否已经访问
     * @param index
     * @param temp
     * @param chars
     * @param set
     * @param vis
     */
    public static void permutation(int index, char[] temp, char[] chars, Set<String> set, boolean[] vis) {
        if(index == chars.length) {
            set.add(new String(temp));
            return;
        }
        for(int i = 0; i < chars.length; i++) {
            if(!vis[i]) {
                temp[index] = chars[i];
                vis[i] = true;
                permutation(index + 1, temp, chars, set, vis);
                vis[i] = false;
            }
        }
    }

    //------------------方法二--------------------
    public static ArrayList<String> Permutation2(String str) {
        ArrayList<String> result = new ArrayList<>();
        if(str == null || str.equals("")) {
            return result;
        }
        char[] chars = str.toCharArray();
        Set<String> set = new TreeSet<>();
        permutation2(0, chars, set);
        result.addAll(set);
        return result;
    }

    public static void permutation2(int index, char[] chars, Set<String> set) {
        if(index == chars.length - 1) {
            set.add(new String(chars));
            return;
        }
        for(int i = index; i < chars.length; i++) {
            swap(chars, index, i);
            permutation2(index + 1, chars, set);
            swap(chars, index, i);
        }
    }

    public static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    //--------------方法三
    public static ArrayList<String> Permutation3(String str) {
        ArrayList<String> result = new ArrayList<>();
        if(str != null && str.length() > 0) {
            permutation3(0, str.toCharArray(), result);
            Collections.sort(result);
        }
        return result;
    }

    public static void permutation3(int index, char[] chars, ArrayList<String> result) {
        if(index == chars.length - 1) {
            result.add(new String(chars));
            return;
        }
        //记录该位置已出现过的元素
        Set<Character> characters = new HashSet<>();
        for(int i = index; i < chars.length; i++) {
            if(!(i != index && characters.contains(chars[i]))) {
                characters.add(chars[i]);
                swap(chars, index, i);
                permutation3(index + 1, chars, result);
                swap(chars, index, i);
            }
        }
    }

    //-----------方法四  字典序算法---------
    public static ArrayList<String> Permutation4(String str) {
        ArrayList<String> result = new ArrayList<>();
        if(str != null && str.length() > 0) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            result.add(new String(chars));
            while(true) {
                int firstPositiveOrder = chars.length - 2;
                while(firstPositiveOrder >= 0
                        && chars[firstPositiveOrder] >= chars[firstPositiveOrder + 1]) {
                    firstPositiveOrder--;
                }
                if(firstPositiveOrder == -1) {
                    break;
                }
                int index = chars.length - 1;
                while(chars[index] <= chars[firstPositiveOrder]) {
                    index--;
                }
                swap(chars, firstPositiveOrder, index);
                reverse(chars, firstPositiveOrder + 1, chars.length);
                result.add(new String(chars));
            }
        }
        return result;
    }

    public static void reverse(char[] chars, int begin, int end) {
        for (int i = begin, j = end - 1; i < j; i++, j--) {
            swap(chars, i, j);
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while(input.hasNext()) {
            String str = input.next();
            List<String> result = Permutation4(str);
            for(String item : result) {
                System.out.print(item + " ");
            }
            System.out.println();
        }
    }
}
