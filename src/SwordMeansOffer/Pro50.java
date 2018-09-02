package SwordMeansOffer;

/**
 * Created by Special on 2018/8/26 10:52
 */
public class Pro50 {

    /**
     * 我的初始做法
     * @param num1
     * @param num2
     * @return
     */
    public static int Add(int num1,int num2) {
        int result = 0, carry = 0, bitResult = 1;
        do {
            int bit1 = num1 & 1;
            int bit2 = num2 & 1;
            int bitValue = bit1 ^ bit2;
            boolean flag = false;
            if(bit1 == 1 && bit2 == 1) {
                flag = true;
            }
            int temp = bitValue ^ carry;
            if(bitValue == 1 && carry == 1) {
                flag = true;
            }
            if(temp == 1) {
                result |= bitResult;
            }
            carry = flag ? 1 : 0;
            bitResult <<= 1;
            num1 >>>= 1;
            num2 >>>= 1;
        } while(num1 != 0 || num2 != 0);
        if(carry == 1) {
            result |= bitResult;
        }
        return result;
    }

    /**
     * 支持所有整数
     * @param num1
     * @param num2
     * @return
     */
    public static int Add2(int num1,int num2) {
        int result = 0, carry = 0;
        do {
            result = num1 ^ num2;
            carry = (num1 & num2) << 1;
            num1 = result;
            num2 = carry;
            //直到不产生进位为止
        } while(num2 != 0);
        return result;
    }

    //--------拓展-----------
    //不使用新的变量，交换2个值
    public static void exchange1(int[] num, int i, int j) {
        num[i] = num[i] + num[j];
        num[j] = num[i] - num[j];
        num[i] = num[i] - num[j];
    }

    public static void exchange2(int[] num, int i, int j) {
        num[i] = num[i] ^ num[j];
        num[j] = num[i] ^ num[j];
        num[j] = num[i] ^ num[j];
    }

    public static void main(String[] args) {
//        System.out.println(Add(0, 1));
//        System.out.println(Add(993443, 99343344));
//        System.out.println(Add(34, 45));
//        System.out.println(Add(-3, -1));
        int[] num = {1, 2};
        exchange1(num, 0, 1);
        for(int i : num) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
