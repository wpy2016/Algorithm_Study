package dp;

/**
 * dp
 * 动态规划问题
 * Created by wangpeiyu on 2017/9/4.
 */
public class Knapsack {

    /**
     * 01背包问题
     *
     * @param n
     * @param weightPkg
     * @param weight
     * @param value
     * @return
     */
    public static int knapsack01(int n, int weightPkg, int[] weight, int[] value) {
        //保存的是增加相应物品时相应weight下的最大价值
        int[][] curWeightMaxValue = new int[n][weightPkg + 1];
        //初始化第一行的值为,从下面的for循环中抽取出来，主要是为了统一化
        for (int i = 0; i <= weightPkg; ++i) {
            if (i < weight[0]) {
                curWeightMaxValue[0][i] = 0;
            } else {
                curWeightMaxValue[0][i] = value[0];
            }
        }
        for (int i = 1; i < n; ++i) {//遍历物品,此时从1开始，因为第一个物品已经处理了
            for (int j = 0; j <= weightPkg; ++j) {
                if (j < weight[i]) {//如果当前物品的重量比当前重量包j还大，那只能是这个物品放不了，当前的重量和未放之前一样
                    curWeightMaxValue[i][j] = curWeightMaxValue[i - 1][j];
                } else {
                    int maxValue = Math.max(curWeightMaxValue[i - 1][j], curWeightMaxValue[i - 1][j - weight[i]] + value[i]);
                    curWeightMaxValue[i][j] = maxValue;
                }
            }
        }
        return curWeightMaxValue[n - 1][weightPkg - 1];
    }

    /**
     * 打印二维数组
     * @param result
     */
    private static void printTwo(int[][] result) {
        int row = result.length;
        int column = result[0].length;
        //构造结构
        for (int i = 0; i < column; ++i) {
            System.out.print(i + 1 + "\t");
        }
        System.out.println();
        //开始打印数据
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < column; ++j) {
                System.out.print(result[i][j] + "\t");
            }
            System.out.println();
        }
    }


    /**
     * 测试入口
     * @param args
     */
    public static void main(String[] args) {
        //对应物品的个数
        int n = 5;
        //对应背包可以承受的重量
        int weightPkg = 10;
        //每个物品的重量
        int[] weight = new int[]{5, 4, 7, 2, 6};
        //对应每个物品的价值
        int[] value = new int[]{12, 3, 10, 3, 6};
        System.out.println(knapsack01(n, weightPkg, weight, value));
    }


}
