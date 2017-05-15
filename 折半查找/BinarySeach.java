package 折半查找;

public class BinarySeach {

	/**
	 * 只用二分的前提是数组必须已经排好序了的
	 * 二分查找一个数，当找到这个数的时候，返回他的下标，当找不到的时候，返回-1
	 * @param a
	 * @param find
	 * @return
	 */
	public static <T extends Comparable<? super T>> int BinarySeacherFunc(T[] a,T find){
		int left=0,right=a.length-1;
		while(left<=right){
			int center = (left+right)/2;
			if(a[center].compareTo(find)>0){
				right=center-1;
			}else if(a[center].compareTo(find)<0){
				left=center+1;
			}else{
				return center;//查找到了所以返回
			}
		}
		return -1;//未找到返回-1
	}
	
	public static  int BinarySeacherFuncInt(int[] a,int find){
		int left=0,right=a.length-1;
		while(left<=right){
			int center = (left+right)/2;
			if(a[center]>find){
				right=center-1;
			}else if(a[center]<find){
				left=center+1;
			}else{
				return center;//查找到了所以返回
			}
		}
		return -1;//未找到返回-1
	}
}
