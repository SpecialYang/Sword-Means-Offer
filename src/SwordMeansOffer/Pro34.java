package SwordMeansOffer;

import java.util.*;

/**
 * Created by Special on 2018/7/27 23:03
 */
public class Pro34 {

    public static String PrintMinNumber(int [] numbers) {
        if(numbers == null || numbers.length == 0) {
            return "";
        }
        List<String> result = new ArrayList<>();
        getAllCombine(0, numbers, result);
        Collections.sort(result);
        return result.get(0);
    }

    private static void getAllCombine(int index, int[] numbers, List<String> result) {
        if(index == numbers.length - 1) {
            StringBuilder sb = new StringBuilder();
            for(int num : numbers) {
                sb.append(num);
            }
            result.add(sb.toString());
            return;
        }
        Set<Integer> ocur = new HashSet<>();
        for(int i = index; i < numbers.length; i++) {
            if(!(i != index && ocur.contains(numbers[i]))) {
                ocur.add(numbers[i]);
                swap(numbers, i, index);
                getAllCombine(index + 1, numbers, result);
                swap(numbers, i, index);
            }
        }
    }

    public static void swap(int[] temp, int i, int j) {
        if(i != j) {
            int str = temp[i];
            temp[i] = temp[j];
            temp[j] = str;
        }
    }

    public static String PrintMinNumber2(int [] numbers) {
        if(numbers == null || numbers.length == 0) {
            return "";
        }
        String[] strNumbers = new String[numbers.length];
        for(int i = 0; i < numbers.length; i++) {
            strNumbers[i] = String.valueOf(numbers[i]);
        }
        Arrays.sort(strNumbers, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o1 + o2).compareTo(o2 + o1);
            }
        });
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < strNumbers.length; i++) {
            sb.append(strNumbers[i]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while(input.hasNext()) {
            int n = input.nextInt();
            int[] num = new int[n];
            for(int i = 0; i < n; i++) {
                num[i] = input.nextInt();
            }
            System.out.println(PrintMinNumber(num));
        }
    }
}
