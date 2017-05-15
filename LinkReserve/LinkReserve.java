package LinkReserve;

import java.rmi.server.RemoteServer;

/**
 * 不能开辟O(n)空间，将两个升序的单向链表转为一个降序的链表
 * @author wangpeiyu
 *
 */
public class LinkReserve {
	
	/** 
	 * 链表节点
	 * @author wangpeiyu
	 *
	 */
	private static class Node{
		int value;
		Node next;
		public Node(int value,Node next){
			this.value=value;
			this.next=next;
		}
	}

	/**
	 * 测试
	 * @param args
	 */
	public static void main(String[] args) {
		//创建两个单向升序的链表
		Node node=new Node(0,null);
		Node nodeCur=node;
		for(int i=1;i<10;i++){
			nodeCur=nodeCur.next=new Node((int)(i*10+Math.random()*10), null);
		}
		Node node1=new Node(10,null);
		Node nodeCur1=node1;
		for(int i=1;i<10;i++){
			nodeCur1=nodeCur1.next=new Node((int)(i*10+Math.random()*10), null);
		}
		//打印两个链表
		System.out.println("链表反转前：");
		printNode(node);
		printNode(node1);
		//实现单向链表的反转，不开辟O(n)空间
		//首先将两个链表反转
		node =RemoteServer(node);
		node1=RemoteServer(node1);
		//打印反转之后的字符串
		System.out.println("链表反转前：");
		printNode(node);
		printNode(node1);
		//合并两个链表
		Node newNode =combainNodes(node, node1);
		System.out.println("合并后的链表");
		printNode(newNode);
	}
	/**
	 * 反转链表
	 * @param node
	 * @return
	 */
	public static Node RemoteServer(Node node){
		Node endNode=node;
		while(endNode.next!=null){
			endNode=endNode.next;
		}
		Node next=null;
		for(Node i=node;i!=endNode;){
			next=i.next;
			if(i==node){
				endNode.next=i;
				i.next=null;
			}else{
				i.next=endNode.next;
				endNode.next=i;
			}
			i=next;
		}
		return endNode;
	}
	/**
	 * 打印链表
	 * @param node
	 */
	public static void printNode(Node node){
		while(node!=null){
			System.out.print(node.value+"  ");
			node=node.next;
		}
		System.out.println();
	}
	
	/**
	 * 合并两个升序的链表，结合为一个降序的链表输出
	 * @param node1
	 * @param node2
	 * @return
	 */
	public static Node combainNodes(Node node1,Node node2){
		Node head=node1;
		Node next=null;
		Node curOperate=node1;
		Node pre=null;
		for(Node i=node2;i!=null;){
			next=i.next;
			if(curOperate.value<i.value){
				if(pre==null){
				pre=i;
				i.next=curOperate;
				head=i;
				}else{
					i.next=curOperate;
					pre.next=i;
					pre=i;
				}
			}else if(curOperate.value==i.value){
				Node next2=curOperate.next;
				curOperate.next=i;
				i.next=next2;
				pre=i;
				curOperate=next2;
			}else{
				while(curOperate.next!=null&&curOperate.next.value>i.value){
					curOperate=curOperate.next;
				}
				//表示第一个链表已经到尾了
				if(curOperate.next==null){
					curOperate.next=i;
					//此时只要将第二个链表剩下的全部放到next即可
					return head;
				}
				Node next3=curOperate.next;
				curOperate.next=i;
				i.next=next3;
				pre=i;
				curOperate=next3;
			}
			i=next;
		}
		return head;
	}
}
