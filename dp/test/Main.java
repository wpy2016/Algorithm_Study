package dp.test;

import java.util.Scanner;

/**
 * 有一楼梯共m级，刚开始时你在第一级，若每次只能跨上一级或二级，要走上第m级，共有多少走法？
 * Created by wangpeiyu on 2017/9/5.
 */
public class Main {

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            for(int i = 0;i < n;++i){
                int m = scanner.nextInt();
                int[] record = new int[m];
                for(int j = 0; j < m;++j){
                    record[j] = -1;
                }
                System.out.println(get(record,m));
            }
        }

    }

    private static int get(int[] record,int i){
        if( i < 1){
            return 0;
        }else if (i == 1){
            return 1;
        }else{
            int one = 0;
            if(record[i - 1] != -1){
                one = record[i - 1];
            }else{
                one = get(record,i - 1);
                record[i - 1] = one;
            }
            int two = 0;
            if(record[i - 2] != -1){
                two = record[i - 2];
            }else{
                two = get(record,i - 2);
                record[i - 2] = two;
            }
            return  one + two;
        }
    }
}
