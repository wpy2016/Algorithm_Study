package AVL树;

import java.awt.RenderingHints.Key;
import java.security.acl.Acl;
import java.util.LinkedList;
import java.util.Queue;

import org.omg.CORBA.PUBLIC_MEMBER;

public class MyAVLTree<T extends Comparable<T>, V> {

	private AVLNode<T, V> mRoot;

	private class AVLNode<T extends Comparable<T>, V> {
		T key;
		V value;
		int height;
		AVLNode<T, V> leftNode;
		AVLNode<T, V> rightNode;

		public AVLNode(T key, V value, AVLNode<T, V> left, AVLNode<T, V> right) {
			this.key = key;
			this.value = value;
			leftNode = left;
			rightNode = right;
			height = 0;
		}
	}

	public int max(int a, int b) {
		return a > b ? a : b;
	}

	private int getHeight(AVLNode<T, V> node) {
		if (node != null) {
			return node.height;
		}
		return 0;
	}

	public int getHeight() {
		return getHeight(mRoot);
	}

	public MyAVLTree() {
		mRoot = null;
	}
	
	public void insert(T key,V value){
		mRoot=insert(mRoot, key, value);
	}

	private AVLNode<T, V> insert(AVLNode<T, V> node, T key, V value) {
		if (node == null) {
			node = new AVLNode<T, V>(key, value, null, null);
			assert node == null;
		} else {
			int cmp = key.compareTo(node.key);
			if (cmp < 0) {
				node.leftNode = insert(node.leftNode, key, value);
				if (2 == getHeight(node.leftNode) - getHeight(node.rightNode)) {
					if (key.compareTo(node.leftNode.key) < 0) {
						node = LeftLeftRotation(node);
					} else {
						node = LeftRightRotation(node);
					}
				}
			} else if (cmp > 0) {
				node.rightNode = insert(node.rightNode, key, value);
				if (2 == getHeight(node.rightNode) - getHeight(node.leftNode)) {
					if (key.compareTo(node.rightNode.key) < 0) {
						node = RightLeftRotation(node);
					} else {
						node = RightRightRotation(node);
					}
				}

			} else {
				System.out.println("不能插入两个相同的结点");
			}
		}
		node.height = max(getHeight(node.leftNode), getHeight(node.rightNode)) + 1;
		return node;
	}

	private AVLNode<T, V> RightLeftRotation(AVLNode<T, V> node) {
		node.rightNode = LeftLeftRotation(node.rightNode);
		return RightRightRotation(node);
	}

	private AVLNode<T, V> LeftRightRotation(AVLNode<T, V> node) {
		node.leftNode = RightRightRotation(node.leftNode);
		return LeftLeftRotation(node);
	}

	/**
	 * 向左边旋转
	 * 
	 * @param leftNode
	 * @return
	 */
	private AVLNode<T, V> RightRightRotation(AVLNode<T, V> node) {

		AVLNode<T, V> nodeRight = node.rightNode;

		node.rightNode = nodeRight.leftNode;
		nodeRight.leftNode = node;

		/**
		 * 旋转之后重新改变高度
		 */
		node.height = max(getHeight(node.leftNode), getHeight(node.rightNode)) + 1;
		nodeRight.height = max(node.height, getHeight(nodeRight.rightNode)) + 1;
		return nodeRight;
	}

	/**
	 * 向右边旋转
	 * 
	 * @param node
	 * @return
	 */
	private AVLNode<T, V> LeftLeftRotation(AVLNode<T, V> node) {
		AVLNode<T, V> nodeLeft = node.leftNode;

		node.leftNode = nodeLeft.rightNode;
		nodeLeft.rightNode = node;

		/**
		 * 旋转之后重新改变高度
		 */
		node.height = max(getHeight(node.leftNode), getHeight(node.rightNode)) + 1;
		nodeLeft.height = max(node.height, getHeight(nodeLeft.leftNode));

		return nodeLeft;
	}

	/**
	 * 前序遍历
	 * 
	 * @param node
	 */
	private void preOrder(AVLNode<T, V> node) {
		if (node != null) {
			System.out.print(node.key.toString()+" ");
			preOrder(node.leftNode);
			preOrder(node.rightNode);
		}
	}

	public void preOrder() {
		preOrder(mRoot);
	}

	/**
	 * 中序遍历
	 * 
	 * @param node
	 */
	private void inOrder(AVLNode<T, V> node) {
		if (node != null) {
			inOrder(node.leftNode);
			System.out.print(node.key.toString()+" ");
			inOrder(node.rightNode);
		}
	}

	public void inOrder() {
		inOrder(mRoot);
	}

	/**
	 * 后序遍历
	 * 
	 * @param node
	 */
	private void postOrder(AVLNode<T, V> node) {
		if (node != null) {
			postOrder(node.leftNode);
			postOrder(node.rightNode);
			System.out.print(node.key.toString()+" ");
		}
	}

	public void postOrder() {
		postOrder(mRoot);
	}

	/**
	 * 层序遍历
	 */
	public void LayerOrder() {
		Queue<AVLNode<T, V>> queue = new LinkedList<>();
		if (mRoot != null) {
			queue.add(mRoot);
			while (!queue.isEmpty()) {
				AVLNode<T, V> node = queue.poll();
				if (node.leftNode != null) {
					queue.add(node.leftNode);
				}
				if (node.rightNode != null) {
					queue.add(node.rightNode);
				}
				System.out.print(node.key.toString()+" ");
			}
		}
	}

	private AVLNode<T, V> Search(AVLNode<T, V> node, T key) {
		if (node != null) {
			int cmp = key.compareTo(node.key);
			if (0 == cmp) {
				return node;
			} else if (0 > cmp) {
				return Search(node.leftNode, key);
			} else {
				return Search(node.rightNode, key);
			}
		} else {
			return null;
		}

	}

	public AVLNode<T, V> Search(T key) {
		return Search(mRoot, key);
	}

	public AVLNode<T, V> IteratorSearch(T key) {
		if (mRoot != null) {
			AVLNode<T, V> node = mRoot;
			int cmp = 0;
			while (node != null) {
				cmp = key.compareTo(node.key);
				if (0 > cmp) {
					node = node.leftNode;
				} else if (cmp > 0) {
					node = node.rightNode;
				} else {
					return node;
				}
			}
		}
		return null;

	}
	
	public void remove(T key){
		mRoot=remove(mRoot, key);
	}
	
	private AVLNode<T, V> remove(AVLNode<T, V> node,T key){
		if(node==null){
			System.out.println("找不到相应的节点");
		}else{
			int cmp=key.compareTo(node.key);
			if(0>cmp){
				node.leftNode=remove(node.leftNode, key);
				node.height=max(getHeight(node.leftNode), getHeight(node.rightNode))+1;
				if(2==getHeight(node.rightNode)-getHeight(node.leftNode)){
					AVLNode<T, V> nodeRight=node.rightNode;
					if(getHeight(nodeRight.leftNode)>getHeight(nodeRight.leftNode)){
						node = RightLeftRotation(node);
					}else{
						node = RightRightRotation(node);
					}
				}
			}else if (0<cmp){
				node.rightNode=remove(node.rightNode, key);
				node.height=max(getHeight(node.leftNode), getHeight(node.rightNode))+1;
				if(2==getHeight(node.leftNode)-getHeight(node.rightNode)){
					AVLNode<T, V> nodeLeft=node.leftNode;
					if(getHeight(nodeLeft.leftNode)>getHeight(nodeLeft.rightNode)){
						node =LeftLeftRotation(node);
					}else{
						node=LeftRightRotation(node);
					}
				}
			}else{
				if(node.leftNode!=null&&node.rightNode!=null){
					if(getHeight(node.leftNode)>getHeight(node.rightNode)){//左子树高，从左子树取最大值
						AVLNode<T, V> leftMaxNode=maxNode(node.leftNode);
						node.key=leftMaxNode.key;
						node.value=leftMaxNode.value;
						node.leftNode=remove(node.leftNode, leftMaxNode.key);
						node.height=max(getHeight(node.leftNode), getHeight(node.rightNode))+1;
					}else{//右子树高，从右子树取最小值
						AVLNode<T, V> rightMinNode=mixNode(node.rightNode);
						node.key=rightMinNode.key;
						node.value=rightMinNode.value;
						node.rightNode=remove(node.rightNode, rightMinNode.key);
						node.height=max(getHeight(node.leftNode), getHeight(node.rightNode))+1;
					}
				}else{
					return node.leftNode==null?node.rightNode:node.leftNode;
				}
			}
		}
		return node;
	}
	
	private AVLNode<T, V> maxNode(AVLNode<T, V> node) {
		if(node!=null){
			while(node.rightNode!=null){
				node=node.rightNode;
			}
		}
		return node;
	}
	
	

	private AVLNode<T, V> mixNode(AVLNode<T, V> node) {
		if(node!=null){
			while(node.leftNode!=null){
				node = node.leftNode;
			}
		}
		return node;
	}

	private void print(AVLNode<T, V> node,AVLNode<T, V> root,int direction){
		if(node!=null){
			if(0==direction){
				System.out.println("root is "+node.key);
			}else{
				System.out.println("node is "+node.key+" is "+root.key+"'s "+(direction==-1?"left":"right")+" node");
			}
			print(node.leftNode, node, -1);
			print(node.rightNode, node, 1);
		}
	}

	public void print(){
		print(mRoot, mRoot, 0);
	}
	
	public static void main(String[] args) {
		int arr[]= {3,2,1,4,5,6,7,16,15,14,13,12,11,10,8,9};
		MyAVLTree<Integer, Integer> avlTree=new MyAVLTree<>();
		for(int i=0;i<arr.length;i++){
			avlTree.insert(arr[i], arr[i]);
		}
		System.out.println("前序遍历是");
		avlTree.preOrder();
		System.out.println("\n中序遍历是");
		avlTree.inOrder();
		System.out.println("\n后序遍历是");
		avlTree.postOrder();
		System.out.println("\n层序遍历是");
		avlTree.LayerOrder();
		System.out.println("\nAVL树的详细信息");
		avlTree.print();
		
		System.out.println("删除节点8");
		avlTree.remove(6);
		avlTree.print();
	}

}
