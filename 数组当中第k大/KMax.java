package 数组当中第k大;

/**
 * 求数组当中的第k大
 * @author wangpeiyu
 *
 */
public class KMax {
	public static  int getKMax(int[] a,final int k){
		if(k>=a.length){
			return -1;
		}else{
			int[] b=new int[k];
			for(int i=0;i<k;i++){
				b[i]=a[i];
			}
			insertSort(b);
			for(int i=k;i<a.length;i++){
				if(a[i]>b[k-1]){
					//如果a[i]>b[k-1],那么b[]中一定要少一个，而且少的那个就是第k-1个，所以可以将b[k-1]重新赋值a[i]
					b[k-1]=a[i];
					//将b数组从k-1位置重新插入排序
					insertSortByPosition(b, k-1);
				}
			}
			return b[k-1];
		}	
	}
	public static void insertSort(int[] a){
		for(int i=1;i<a.length;i++){
			insertSortByPosition(a,i);
		}
	}	
	public static void insertSortByPosition(int[] a,int position) {
		for(int j=position;j>0;j--){
			if(a[j]>a[j-1]){
				swap(a, j, j-1);
			}else{
				//当比前面小时，不必再进行for循环浪费时间
				break;
			}
		}
	}
	public static void swap(int[] a,int position1,int position2){
		int temp=a[position1];
		a[position1]=a[position2];
		a[position2]=temp;
	}

}
