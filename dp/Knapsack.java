package dp;

/**
 * dp
 * 动态规划问题
 * Created by wangpeiyu on 2017/9/4.
 */
public class Knapsack {

    /**
     * 01背包问题
     * 有n个物品，每个物品的重量为weight[i]，每个物品的价值为value[i]。现在有一个背包，
     * 它所能容纳的重量为total，问：当你面对这么多有价值的物品时，你的背包所能带走的最大价值是多少？
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
        return curWeightMaxValue[n - 1][weightPkg];//右下角那个 new int[n][weightPkg + 1];
    }

    /**
     * 完全背包问题
     * 有n种物品，每种物品有无限个，每个物品的重量为weight[i]，每个物品的价值为value[i]。
     * 现在有一个背包，它所能容纳的重量为total，问：当你面对这么多有价值的物品时，你的背包所能带走的最大价值是多少？
     *
     * @param n
     * @param weightPkg
     * @param weight
     * @param value
     * @return
     */
    public static int knapsackComplete(int n, int weightPkg, int[] weight, int[] value) {
        int[] curWeightMaxValue = new int[weightPkg + 1];
        /**
         * 初始化数组
         */
        for (int i = 0; i <= weightPkg; ++i) {
            curWeightMaxValue[i] = 0;
        }
        /**
         * 01背包和完全背包的不同点是：
         * 01背包问题 每种物品只有一个，也就是每个物品只能放一次
         * 完全背包 每种物品有无限多个， 也就是说每个物品可以放多次
         * 那放多次体现在那里呢？
         * 体现在下面的第二层循环中，循环从重量为0 到 weightPkg重量
         * 求解当前重量的时候，都是只根据当前重量减去weight[i]后重量对应的最大价值加上当前价值，和当前的价值比较，得到最大的价值
         * 也就是说，这个时候可能存在，一个物品在前面的重量求解最大价值时已经加了一次，在后面求解最大价值时又加了，这就体现了一个物品放多次
         * 而01背包都是使用 i - 1行的数据进行和当前的处理，所以保证了每个物品只放了一次
         */
        for (int i = 0; i < n; ++i) {//遍历所有物品
            for (int j = weight[i]; j <= weightPkg; ++j) {
                int maxValue = Math.max(curWeightMaxValue[j - weight[i]] + value[i], curWeightMaxValue[j]);
                curWeightMaxValue[j] = maxValue;
            }
        }
        return curWeightMaxValue[weightPkg];
    }

    /**
     * 多重背包问题
     * 有n种物品，每种物品有amount[i]个，每个物品的重量为weight[i]，
     * 每个物品的价值为value[i]。现在有一个背包，它所能容纳的重量为total，
     * 问：当你面对这么多有价值的物品时，你的背包所能带走的最大价值是多少？
     * <p>
     * 多重和完全更接近，多了数量的限制，用一个count[n]计数数组来限制物品i的数量。当放入第i个物品是较优值的时候，
     * count[i]=count[j-weight[i]]+1（j 的含义请查看代码）;这样做是因为，放入第i个物品的操作是基于count[j-weight[i]]放入的，
     * 所以当count[i-weight[i]]>=amount[i]时，就要阻止放入即便放入第i个物品是较优值。
     *
     * @param n
     * @param weightPkg
     * @param weights
     * @param value
     * @param counts
     * @return
     */
    public static int knapsackMany(int n, int weightPkg, int[] weights, int[] value, int[] counts) {

        int[] curWightMaxValue = new int[weightPkg + 1];
        /**
         * 初始化每种重量下的最大值
         */
        for (int i = 0; i <= weightPkg; ++i) {
            curWightMaxValue[i] = 0;
        }
        /**
         * 遍历所有物品
         */
        for (int i = 0; i < n; ++i) {
            int[] amounts = new int[weightPkg + 1];//用于保存每种物品对于每个重量下使用了多少次
            //初始化值
            for (int j = 0; j <= weightPkg; ++j) {
                amounts[j] = 0;
            }
            for (int weight = weights[i]; weight <= weightPkg; ++weight) {
                int maxValue = Math.max(curWightMaxValue[weight - weights[i]] + value[i], curWightMaxValue[weight]);
                if (maxValue != curWightMaxValue[weight]) {//表明是加入当前物品的价值比较大，这时候就要判断物品放的次数是否还有剩余
                    /**
                     * 如果amount[weight - weights[i]] < counts[i],说明还可以放
                     */
                    if (amounts[weight - weights[i]] < counts[i]) {
                        //说明maxValue不用修改
                        //并修改当前重量的物品使用次数值
                        amounts[weight] = amounts[weight - weights[i]] + 1;
                    } else {//说明已经不能放了，物品放的次数已经打到了上限,所以此时maxValue应该是原来的值
                        /**
                         * 这时又存在两个情况，就是第一次放物品时 和 不是第一次放物品
                         * 第一次放物品时，如果在前面的重量已经达到了上限，那么后面的重量的最大值还是0，这时不对的，所以应该是前一个重量的最大值
                         * 不是第一次放物品，就不存在这种错误的情况
                         */
                        if (curWightMaxValue[weight] == 0) {//说明是第一次放物品
                            maxValue = curWightMaxValue[weight - 1];
                        } else {//不是第一次放，那么就是说maxValue就是原来的值
                            maxValue = curWightMaxValue[weight];
                        }
                    }
                }
                curWightMaxValue[weight] = maxValue;
            }
        }
        return curWightMaxValue[weightPkg];
    }


    /**
     * 打印二维数组
     *
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
     *
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
        //多重背包时使用的数量
        int[] count = new int[]{2, 3, 4, 5, 6};
        System.out.println("01背包的最大值" + knapsack01(n, weightPkg, weight, value));
        System.out.println("完全背包的最大值" + knapsackComplete(n, weightPkg, weight, value));
        System.out.println("多重背包的最大值" + knapsackMany(n, weightPkg, weight, value, count));
    }


}
