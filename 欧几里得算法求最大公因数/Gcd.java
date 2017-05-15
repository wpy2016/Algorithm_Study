package 欧几里得算法求最大公因数;

public class Gcd {

	
	/**
	 * 求两个数的最大公约数
	 * @param m
	 * @param n
	 * @return
	 */
	public static long gcd(long m,long n){
		while(n!=0){
			long rem=m%n;
			m=n;
			n=rem;
		}
		return m;
	}
}
