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
		node =convertLink(node);
		node1=convertLink(node1);
		//打印反转之后的字符串
		System.out.println("链表反转前：");
		printNode(node);
		printNode(node1);
		//合并两个链表
		Node newNode =mergeTwoLink(node, node1);
		System.out.println("合并后的链表");
		printNode(newNode);
	}

	/**
	 * 链表反转
	 *
	 * @param node
	 * @return
	 */
	private static Node convertLink(Node node) {
		if (node == null || node.next == null) {
			return node;
		}
		Node preNode = node;
		Node editNode = node.next;
		Node nextNode = editNode.next;
		//循环开始之前必须要先把原来链表的头的next置为空，因为这个节点将是新链表的尾节点
		preNode.next = null;
		while (editNode != null) {
			editNode.next = preNode;
			preNode = editNode;
			editNode = nextNode;
			if (nextNode != null) {
				nextNode = nextNode.next;
			}
		}
		return preNode;
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
	 * 传入两个链表都是降序排列
	 *
	 * @param node1
	 * @param node2
	 * @return
	 */
	private static Node mergeTwoLink(Node node1, Node node2) {
		Node nextNode;//先将下一个节点保存起来
		Node insertNode;
		Node editNode = node1;
		Node headNode = node1;
		//先判断头节点那个大
		if (node1.value > node2.value) {
			insertNode = node2;
		} else {
			insertNode = node2.next;
			node2.next = node1;
			headNode = node2;
			editNode = node2;
		}
		while (insertNode != null) {
			nextNode = insertNode.next;
			while (editNode != null && editNode.next != null && editNode.next.value > insertNode.value)
				editNode = editNode.next;
			Node editNextNode = editNode.next;
			editNode.next = insertNode;
			insertNode.next = editNextNode;
			insertNode = nextNode;
		}
		return headNode;
	}
}
