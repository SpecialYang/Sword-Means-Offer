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
     * 前提允许修改数组
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

    //-----------方法三，对应剑指Offer的变形题目
    /**
     * 这个不适合，这个只使用数组大小为n, 元素范围0到n - 2
     * @param numbers
     * @param length
     * @param duplication
     * @return
     */
    public static boolean duplicate3(int numbers[],int length,int [] duplication) {
        if(numbers == null || numbers.length == 0) {
            return false;
        }
        int left = 0, right = length - 1, mid;
        while(left <= right) {
            mid = left + (right - left >> 1);
            int count = countOfRange(numbers, length, left, mid);
            if(count > (mid - left + 1)) {
                if(left == right) {
                    duplication[0] = left;
                    return true;
                }
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }

    public static int countOfRange(int[] numbers, int length, int left, int right) {
        int count = 0;
        for(int i = 0; i < length; i++) {
            if(numbers[i] >= left && numbers[i] <= right) {
                count++;
            }
        }
        return count;
    }


    /**
     * 不需要额外空间，只需自身即可
     * @param numbers
     * @param length
     * @param duplication
     * @return
     */
    public static boolean duplicate4(int numbers[],int length,int [] duplication) {
        if(numbers == null || numbers.length == 0) {
            return false;
        }
        for(int i = 0; i < length; i++) {
            //下标可能作为元素值出现过，所以要恢复到非负
            int index = numbers[i] < 0 ? numbers[i] + length : numbers[i];
            if(numbers[index] < 0) {
                duplication[0] = index;
                return true;
            }
            numbers[index] -= length;
        }
        return false;
    }

    public static void main(String[] args) {
        int[] numbers = {1,0,1};
        int[] duplicaiton = new int[1];
        if(duplicate4(numbers, numbers.length, duplicaiton)) {
            System.out.println(duplicaiton[0]);
        }
    }
}
