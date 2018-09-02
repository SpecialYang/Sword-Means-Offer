package SwordMeansOffer;

/**
 * Created by Special on 2018/8/28 22:05
 */
public class Pro52 {

    public static boolean duplicate(int numbers[],int length,int [] duplication) {
        boolean[] visited = new boolean[length];
        for(int i = 0; i < length; i++) {
            if(visited[numbers[i]]) {
                duplication[0] = numbers[i];
                return true;
            }else {
                visited[numbers[i]] = true;
            }
        }
        return false;
    }

    /**
     * 利用不重复的数组必然数组索引与元素值必然相等的结论
     * @param numbers
     * @param length
     * @param duplication
     * @return
     */
    public static boolean duplicate2(int numbers[],int length,int [] duplication) {
        if(numbers == null) {
            return false;
        }
        for(int i = 0; i < length; i++) {
            while(i != numbers[i]) {
                if(numbers[i] != numbers[numbers[i]]) {
                    int temp = numbers[i];
                    numbers[i] = numbers[temp];
                    numbers[temp] = temp;
                }else {
                    duplication[0] = numbers[i];
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] numbers = {2,3,1,0,2,5,3};
        int[] duplicaiton = new int[1];
        if(duplicate2(numbers, numbers.length, duplicaiton)) {
            System.out.println(duplicaiton[0]);
        }
    }
}
