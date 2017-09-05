package dp;

import java.util.Scanner;

/**
 * Created by wangpeiyu on 2017/9/5.
 */
public class LongestSubsequence {

    /**
     * test
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String lineX = sc.nextLine();
            String lineY = sc.nextLine();
            int[][] result = new int[lineX.length()][lineY.length()];
            for (int i = 0; i < lineX.length(); ++i) {
                for (int j = 0; j < lineY.length(); ++j) {
                    result[i][j] = -1;
                }
            }
            Long start = System.currentTimeMillis();
            System.out.println(longestSubsequenceOptimize(lineX.toCharArray(), lineY.toCharArray(), lineX.length() - 1, lineY.length() - 1, result));
            Long end = System.currentTimeMillis();
            System.out.println("optimize cost time :" + (end - start));

            start = System.currentTimeMillis();
            System.out.println(longestSubsequenceOrigin(lineX.toCharArray(),lineY.toCharArray(),lineX.length() - 1,lineY.length() - 1));
            end = System.currentTimeMillis();
            System.out.println("origin cost time :" + (end - start));

            /**
             * 经过测试
             * 优化版本比Origin版本快非常多
             HHJKHJKHJKGHJGHGHJGJHHJKHKJHJHHHJ
             HJHJHJGFHJGGJHGHHJKHKJKJKA
             21
             optimize cost time :0
             21
             origin cost time :146405
             即时两个串很长，Optimize版本依然很快，而Origin则是遥遥无期
             */

        }

    }


    /**
     * 优化：
     * 很多子问题都已经计算过，所以可以保存计算过的子问题的答案
     * @param xArr
     * @param yArr
     * @param x
     * @param y
     * @return
     */
    private static int longestSubsequenceOptimize(char[] xArr, char[] yArr, int x, int y, int[][] result) {
        /**
         * 定义递归的终止条件
         */
        if (x < 0 || y < 0) {
            return 0;
        }
        if (xArr[x] == yArr[y]) {//如果两个字符相同，那么当前字符串的长度的最长子序列就是两个串减去当前的字符的子串最长子序列 + 1
            if (result[x][y] != -1) {
                //计算过，不用处理
            } else {
                result[x][y] = longestSubsequenceOptimize(xArr, yArr, x - 1, y - 1, result) + 1;
            }
            return result[x][y];
        } else {
            int subX = 0;
            if (x - 1 >= 0) {
                if (result[x - 1][y] != -1) {
                    subX = result[x - 1][y];
                } else {
                    result[x - 1][y] = subX = longestSubsequenceOptimize(xArr, yArr, x - 1, y, result);
                }
            }
            int subY = 0;
            if (y - 1 >= 0) {
                if (result[x][y - 1] != -1) {
                    subY = result[x][y - 1];
                } else {
                    result[x][y - 1] = subY = longestSubsequenceOptimize(xArr, yArr, x, y - 1, result);
                }
            }
            result[x][y] = Math.max(subX, subY);
            return result[x][y];
        }
    }

    /**
     * @param xArr
     * @param yArr
     * @param x
     * @param y
     * @return
     */
    private static int longestSubsequenceOrigin(char[] xArr, char[] yArr, int x, int y) {
        /**
         * 定义递归的终止条件
         */
        if (x < 0 || y < 0) {
            return 0;
        }
        if (xArr[x] == yArr[y]) {//如果两个字符相同，那么当前字符串的长度的最长子序列就是两个串减去当前的字符的子串最长子序列 + 1
            return longestSubsequenceOrigin(xArr, yArr, x - 1, y - 1) + 1;
        } else {
            return Math.max(longestSubsequenceOrigin(xArr, yArr, x - 1, y), longestSubsequenceOrigin(xArr, yArr, x, y - 1));
        }
    }
}
