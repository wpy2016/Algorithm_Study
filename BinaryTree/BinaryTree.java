package BinaryTree;

import java.util.Stack;

import javax.xml.soap.Node;

import org.omg.PortableServer.POA;

/**
 * 普通二叉树中检索其中的一个值，并把这个值对应的路径打印出来
 * 
 * @author wangpeiyu
 *
 */
public class BinaryTree {

	private Stack<Integer> stack = new Stack<Integer>();

	private boolean isFind = false;

	private Node root;

	private class Node {

		public Node left;

		public Node right;

		public int value;
		
		public Node(Node left,Node right,int value){
			this.left=left;
			this.right=right;
			this.value=value;
		}
	}
	
	public String getRoad(int value){
		dfs(root, value, 0);
		String road="";
		if(!isFind){
			road="树中没有该值节点";
			stack.pop();
		}
		while(!stack.isEmpty()){
		road=stack.pop()+road;
		}
		return road;
	}
	
	
	public void generateBinaryTree(){
		root=new Node(new Node(new Node(null, null, 12), new Node(null, null, 14), 5),new Node(new Node(null, null, 15), new Node(null, null, 17), 7),3);
	}

	public void dfs(Node node, int value, int status) {
		stack.push(status);
		if (node == null) {
			return;
		}
		if (node.value == value) {
			isFind = true;
			return;
		}
		dfs(node.left, value, 0);
		if (!isFind){
			stack.pop();	
			dfs(node.right, value, 1);
			if (!isFind)
				stack.pop();
		}else{
			return;
		}
	}
	
	public static void main(String args[]){
		BinaryTree tree=new BinaryTree();
		tree.generateBinaryTree();
		String road = tree.getRoad(99);
		System.out.println(road);
	}

}
