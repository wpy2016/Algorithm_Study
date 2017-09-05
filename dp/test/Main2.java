package dp.test;

import java.util.Scanner;

/**
 * 学校联欢晚会的时候，为了使每一个同学都能参与进来，主持人常常会带着同学们玩击鼓传花的游戏。游戏规则是这样的：
 * n个同学坐着围成一个圆圈，指定一个同学手里拿着一束花，主持人在旁边背对着大家开始击鼓，鼓声开始之后拿着花的同学开始传花，
 * 每个同学都可以把花传给自己左右的两个同学中的一个（左右任意），当主持人停止击鼓时，传花停止，此时，正拿着花没传出去的那个同学就要给大家表演一个节目。
 聪明的小赛提出一个有趣的问题：有多少种不同的方法可以使得从小赛手里开始传的花，传了m次以后，又回到小赛手里。对于传递的方法当且仅当这两种方法中，
 接到花的同学按接球顺序组成的序列是不同的，才视作两种传花的方法不同。
 比如有3个同学1号、2号、3号，并假设小赛为1号，花传了3次回到小赛手里的方式有1->2->3->1和1->3->2->1，共2种。


 输入共一行，有两个用空格隔开的整数n，m（3<=n<=30，1<=m<=30）
 样例输入
 3 3

 输出
 输出共一行，有一个整数，表示符合题意的方法数
 样例输出
 2
 * Created by wangpeiyu on 2017/9/5.
 */
public class Main2 {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int[][] result= new int[n][m];
            for(int i = 0;i < n;++i){
                for(int j = 0;j < m;++j){
                    result[i][j] = -1;
                }
            }
            System.out.println(get(m,1,n,result));
        }
    }
    private static int get(int time ,int person,int totalPerson,int[][] result){
        if(person == 0){
            person = totalPerson;
        }
        if(person == totalPerson + 1){
            person = 1;
        }
        if(time == 0){
            if(person == 1){
                return 1;
            }else{
                return 0;
            }
        }else{
            int sub = 0;
            if (time > 1) {
                if(result[person - 2 == -1 ?  totalPerson - 1 : person - 2][time - 2] != -1){
                    sub = result[person - 2 == -1 ?  totalPerson - 1 : person - 2][time - 2];
                }else{
                    sub = result[person - 2 == -1 ?  totalPerson - 1 : person - 2][time - 2] = get(time - 1,person - 1,totalPerson,result);
                }
            }else{
                sub = get(time - 1,person - 1,totalPerson,result);
            }
            int add = 0;
            if (time > 1) {
                if(result[person == totalPerson ? 0 : person][time - 2] != -1){
                    add = result[person == totalPerson ? 0 : person][time - 2];
                }else{
                    add = result[person == totalPerson ? 0 : person][time - 2] = get(time - 1,person + 1,totalPerson,result);
                }
            }else{
                add = get(time - 1,person + 1,totalPerson,result);
            }
            result[person - 1][time - 1] = sub + add;
            return result[person - 1][time - 1];
        }
    }
}
