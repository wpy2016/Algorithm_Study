package 欧拉函数实现;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/*线性筛O(n)时间复杂度内筛出maxn内欧拉函数值*/
/*
 * int m[maxn],phi[maxn],p[maxn],pt;//m[i]是i的最小素因数，p是素数，pt是素数个数
int make()
{
    phi[1]=1;
    int N=maxn;
    int k;
    for(int i=2;i<N;i++)
    {
        if(!m[i])//i是素数
            p[pt++]=m[i]=i,phi[i]=i-1;
        for(int j=0;j<pt&&(k=p[j]*i)<N;j++)
        {
            m[k]=p[j];
            if(m[i]==p[j])//为了保证以后的数不被再筛，要break
            {
                phi[k]=phi[i]*p[j];
                 //这里的phi[k]与phi[i]后面的∏(p[i]-1)/p[i]都一样（m[i]==p[j]）只差一个p[j]，就可以保证∏(p[i]-1)/p[i]前面也一样了
                break;    
            }
            else
                phi[k]=phi[i]*(p[j]-1);//积性函数性质，f(i*k)=f(i)*f(k)
        }
    }
}*/
public class Oula {
    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        int num=scanner.nextInt();
        int a=num;
        double oulaAnwser=0;
        ArrayList<Integer> oulaList = new ArrayList<Integer>();
        if (isPrime(num)){
            oulaAnwser=num-1;
        }else{
            List<Integer> allPrime = getAllPrime(num);
            for(int i : allPrime){
                int tem=num;
                num=repeatdivide(num,i);
                if (tem!=num){
                    oulaList.add(i);
                }
            }
            oulaAnwser=a;
            for (int j :oulaList){
                 oulaAnwser=oulaAnwser*(1-(double)1/j);
            }
        }
        System.out.println("欧拉函数的值为"+Math.round(oulaAnwser));
    }
    public static List<Integer> getAllPrime(int num){
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int i =2;i<num;i++){
            if (isPrime(i)) {
                result.add(i);
            }
        }
        return result;
    }
    public static boolean isPrime(int num){
        if(num < 2) {
            return false;
        }
        for(int i = 2; i <= Math.sqrt(num); i++ ) {
            if(num%i == 0) {
                return false;
            }
        }
        return true;
    }
 
    public static boolean canbedivide(int num,int i ){
        return num==1?false:num%i==0?true:false;
    }
    public static int repeatdivide(int num,int i ){
        int result=0;
        if (canbedivide(num,i)){
            result=repeatdivide(num/i,i);
        }else{
            return num;
        }
        return result;
    }
}