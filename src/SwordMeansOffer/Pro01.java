package SwordMeansOffer;

import java.util.Scanner;

/**
 * Created by Special on 2018/6/6 11:18
 */
public class Pro01 {

    /**
     * 曼哈顿问题变形的解法
     * 复杂度为O(m + n)
     * @param target
     * @param array
     * @return
     */
    public static boolean Find1(int target, int [][] array) {
        //得到数组的行的大小
        int row = array.length;
        //得到数组的列的大小
        int col = array[0].length;
        for(int i = 0, j = col - 1; i < row && j >= 0;) {
            if(target == array[i][j]) {
                return true;
            }
            //小于当前值，则左移
            if(target < array[i][j]) {
                j--;
            } else if(target > array[i][j]) { //大于当前值，则下移
                i++;
            }
        }
        return false;
    }

    public static boolean Find2(int target, int [][] array) {
        int row = array.length;
        int col = array[0].length;
        if(row == 0 || col == 0) {
            return false;
        }
        int r = 0;
        /**
         * 先找到目标元素所在的行范围，
         * 因为每一行的最后一个元素是该行的最大值
         * 那么我们只需判断最后一个元素，即快速定位到目标元素所在范围
         */
        for(; r < row && array[r][col - 1] < target; r++);
        //对于每一行的元素用二分法查找是否存在
        for(; r < row; r++) {
            int low = 0;
            int high = col - 1;
            while(low <= high){
                int mid = low + (high - low) / 2;
                if(target == array[r][mid]){
                    return true;
                }else if(target < array[r][mid]){
                    high = mid - 1;
                }else {
                    low = mid + 1;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while(input.hasNext()){
            int n = input.nextInt();
            int[][] array = new int[n][n];
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++) {
                    array[i][j] = input.nextInt();
                }
            }
            int target = input.nextInt();
            System.out.println(Find1(target, array));
            System.out.println(Find2(target, array));
//            System.out.println(Find3(target, array));
        }
    }
}
