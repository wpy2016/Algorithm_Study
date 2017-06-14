package 优先级队列;


import java.util.Random;

/**
 * 优先级队列的实现
 * 基于二叉堆的思想
 * 使用二叉堆的前提条件是这棵二叉树必须是完全二叉树
 * @author wangpeiyu
 *
 */
public class PriorityQueue {
	//默认数组的最大长度
	private static final int ARRAY_COUNT=100;
	//存放数组
	private int array[];
	
	public PriorityQueue() {
		array=new int[ARRAY_COUNT];
		array[0]=0;
	}
	//增加元素，此时使用了上浮，也就是增加的元素在二叉堆中向上浮动
	public boolean addItem(int item){
		boolean isAdd=true;
		int k=++array[0];
		array[k]=item;
		while(0!=k/2){
			if(array[k]>array[k/2]){
				swap(k, k/2);
				k=k/2;
			}else{
				break;
			}
		}
		return isAdd;
	}
	//交换两个元素
	public void swap(int a,int b){
		int temp=array[a];
		array[a]=array[b];
		array[b]=temp;
	}
	//弹出最大值，然后将最后的值赋值到array[1]的位置，也就是弹出最大值的位置，然后该值在二叉堆中向下沉
	public int popMaxItem(){
		int result=array[1];
		array[1]=array[array[0]--];
		int  k=1;
		while(2*k<array[0]){
			if(array[k]>=getMaxItemValue(2*k, 2*k+1)){
				break;
			}else{
				int maxIndex=getMaxItemIndex(2*k, 2*k+1);
				swap(k, maxIndex);
				k=maxIndex;
			}
		}
		if(2*k==array[0]){
			if(array[k]<array[2*k]){
				swap(k, 2*k);
			}
		}
		return result;
	}
	//获取两个位置最大值的索引
	private int getMaxItemIndex(int a,int b){
		return array[a]>array[b]?a:b;
	}
	//获取两个位置的最大值
	private int getMaxItemValue(int a,int b){
		return array[a]>array[b]?array[a]:array[b];
	}
	//测试当前类
	public static void main(String arg[]){
		PriorityQueue priorityQueue=new PriorityQueue();
		Random random=new Random(System.currentTimeMillis());
		for(int i=0;i<50;i++){
			int item=random.nextInt(100);
			System.out.print(item+"  ");
			priorityQueue.addItem(item);
		}
		System.out.println();
		for(int i=0;i<50;i++){
			System.out.println(i+1+" max is "+priorityQueue.popMaxItem());
		}
	}
}
