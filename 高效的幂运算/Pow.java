package 高效的幂运算;

public class Pow {

	public static long pow(long x,int n){
		if(n==0){
			return 1;
		}
		if(n==1){
			return x;
		}
		if(n%2==0){
			return pow(x*x,n/2);
		}else{
			return pow(x*x,n/2)*x;
		}
	}
}
