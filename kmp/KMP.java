package kmp;

/**
 * kmp算法的实现
 * 解决字符串中子串的查找
 * @author wangpeiyu
 *
 */
public class KMP {
	
	/**
	 * 获取next数组，这是kmp算法的关键
	 * @param match
	 * @param next
	 */
	public static void getNext(String match,int[] next){
		int i=0;//后缀
		int j=-1;//前缀
		next[0]=-1;
		int lenght=match.length();
		while(i<lenght-1){
			if(-1==j||match.charAt(i)==match.charAt(j)){
				i++;
				j++;
				next[i]=j;
			}else{
				j=next[j];
			}
		}
	}
	/**
	 * 获取匹配成功的下标，这里使用上面的getNext方法获取到的next数组
	 * @param str 需要匹配的字符串
	 * @param match 匹配的字符串
	 * @return 匹配成功返回匹配的下标，未找到则返回-1
	 */
	public static int IndexOf(String str,String match){
		int StrLenght=str.length();
		int matchLenght=match.length();
		int i=0,j=0;
		int next[]=new int[matchLenght];
		getNext(match, next);
		while(j<matchLenght&&i<StrLenght){
			if(-1==j||str.charAt(i)==match.charAt(j)){
				i++;
				j++;
			}else{
				j=next[j];
			}
		}
		if(j>=matchLenght){
			j=i-matchLenght;
		}else{
			j=-1;
		}
		return j;
	}
	
	/**
	 * 从pos位置开始匹配，获取匹配成功的下标，这里使用上面的getNext方法获取到的next数组
	 * @param str 需要匹配的字符串
	 * @param match 匹配的字符串
	 * @return 匹配成功返回匹配的下标，未找到则返回-1
	 */
	public static int IndexOf(String str,String match,int pos){
		int StrLenght=str.length();
		int matchLenght=match.length();
		if(pos>=StrLenght)
			return -1;
		if(StrLenght-pos<matchLenght)
			return -1;
		int i=pos,j=0;
		int next[]=new int[matchLenght];
		getNext(match, next);
		while(j<matchLenght&&i<StrLenght){
			if(-1==j||str.charAt(i)==match.charAt(j)){
				i++;
				j++;
			}else{
				j=next[j];
			}
		}
		if(j>=matchLenght){
			j=i-matchLenght;
		}else{
			j=-1;
		}
		return j;
	}

	/**
	 * 测试kmp算法
	 * @param args
	 */
	public static void main(String[] args) {
		String str="aabbsbbabwwababaaababsbabbsbwjjbabab";
		String match="ababaaaba";
		System.out.println(IndexOf(str, match));
		String str2="aaw.www.aanw";
		String match2="ww.";
		System.out.println(IndexOf(str2, match2));
	}

}
