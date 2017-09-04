package dp;

/**
 * dp
 * 动态规划问题
 * Created by wangpeiyu on 2017/9/4.
 */
public class Knapsack {

    /**
     * 01背包问题
     *有n个物品，每个物品的重量为weight[i]，每个物品的价值为value[i]。现在有一个背包，
     * 它所能容纳的重量为total，问：当你面对这么多有价值的物品时，你的背包所能带走的最大价值是多少？
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
     * 多重背包问题
     * 有n种物品，每种物品有无限个，每个物品的重量为weight[i]，每个物品的价值为value[i]。
     * 现在有一个背包，它所能容纳的重量为total，问：当你面对这么多有价值的物品时，你的背包所能带走的最大价值是多少？
     * @param n
     * @param weightPkg
     * @param weiht
     * @param value
     * @return
     */
    public static int knapsackComplete(int n, int weightPkg, int[] weiht, int[] value){
        int[] curWeightMaxValue = new int[weightPkg + 1];
        /**
         * 初始化数组
         */
        for(int i = 0;i <= weightPkg;++i){
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
        for(int i = 0;i < n;++i){//遍历所有物品
            for(int j = 0;j <= weightPkg;++j){
                if(j < weiht[i]){//放不下，那就是等于不放之前的值
                    //当前重量的最大价值没有变化
                }else{
                    int maxValue = Math.max(curWeightMaxValue[j - weiht[i]] + value[i],curWeightMaxValue[j]);
                    curWeightMaxValue[j] = maxValue;
                }
            }
        }
        return curWeightMaxValue[weightPkg - 1];
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
        System.out.println("01背包的最大值" + knapsack01(n, weightPkg, weight, value));
        System.out.println("完全背包的最大值" + knapsackComplete(n, weightPkg, weight, value));

    }


}
