package huffmanCode;

import java.util.PriorityQueue;

import BinaryTree.BinaryTree;

abstract class HuffmanTree implements Comparable<HuffmanTree> {
	public final int frequency; // the frequency of this tree

	public HuffmanTree(int freq) {
		frequency = freq;
	}

	// compares on the frequency
	public int compareTo(HuffmanTree tree) {
		return frequency - tree.frequency;
	}
}

class HuffmanLeaf extends HuffmanTree {
	public final char value; // the character this leaf represents

	public HuffmanLeaf(int freq, char val) {
		super(freq);
		value = val;
	}
}

class HuffmanNode extends HuffmanTree {
	public final HuffmanTree left, right; // subtrees

	public HuffmanNode(HuffmanTree l, HuffmanTree r) {
		super(l.frequency + r.frequency);
		left = l;
		right = r;
	}
}

public class HuffmanCode {

	private static int curPos = 0;

	// input is an array of frequencies, indexed by character code
	public static HuffmanTree buildTree(int[] charFreqs) {
		PriorityQueue<HuffmanTree> trees = new PriorityQueue<HuffmanTree>();
		// initially, we have a forest of leaves
		// one for each non-empty character
		for (int i = 0; i < charFreqs.length; i++)
			if (charFreqs[i] > 0)
				trees.offer(new HuffmanLeaf(charFreqs[i], (char) i));

		assert trees.size() > 0;
		// loop until there is only one tree left
		while (trees.size() > 1) {
			// two trees with least frequency
			HuffmanTree a = trees.poll();
			HuffmanTree b = trees.poll();

			// put into new node and re-insert into queue
			trees.offer(new HuffmanNode(a, b));
		}
		return trees.poll();
	}

	public static void printCodes(HuffmanTree tree, StringBuffer prefix, String huffmanCode[]) {
		assert tree != null;
		if (tree instanceof HuffmanLeaf) {
			HuffmanLeaf leaf = (HuffmanLeaf) tree;

			// print out character, frequency, and code for this leaf (which is
			// just the prefix)
			System.out.println(leaf.value + "\t" + leaf.frequency + "\t" + prefix);
			huffmanCode[leaf.value] = prefix.toString();
		} else if (tree instanceof HuffmanNode) {
			HuffmanNode node = (HuffmanNode) tree;

			// traverse left
			prefix.append('0');
			printCodes(node.left, prefix, huffmanCode);
			prefix.deleteCharAt(prefix.length() - 1);

			// traverse right
			prefix.append('1');
			printCodes(node.right, prefix, huffmanCode);
			prefix.deleteCharAt(prefix.length() - 1);
		}
	}

	public static void main(String[] args) {
		String test = "this is an example for huffman encoding";

		// we will assume that all our characters will have
		// code less than 256, for simplicity
		int[] charFreqs = new int[256];

		String haffmancode[] = new String[256];
		// read each character and record the frequencies
		for (char c : test.toCharArray())
			charFreqs[c]++;

		// build tree
		HuffmanTree tree = buildTree(charFreqs);

		// print out results
		System.out.println("SYMBOL\tWEIGHT\tHUFFMAN CODE");
		printCodes(tree, new StringBuffer(), haffmancode);

		/**
		 * 输出原来字符串的哈夫曼编码
		 * 
		 */
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < test.length(); i++) {
			builder.append(haffmancode[((int) test.charAt(i))]);
		}
		System.out.println("Origin String");
		System.out.println(test);
		System.out.println("Haffuman String");
		System.out.println(builder.toString());

		/**
		 * 解码
		 */
		System.out.println("decode haffman String");
		System.out.println(decode(tree, builder.toString(), new StringBuilder()));
	}

	public static String decode(HuffmanTree treeRoot, String encodeString, StringBuilder builder) {
		long lenght = encodeString.length();
		curPos = 0;
		while (curPos < lenght) {
			Character c=getChar(treeRoot, encodeString);
			if(c!=null){
				builder.append(c);	
			}
		}
		return builder.toString();
	}

	private static Character getChar(HuffmanTree tree, String encodeString) {
		if (tree instanceof HuffmanLeaf) {
			HuffmanLeaf leaf = (HuffmanLeaf) tree;
			return leaf.value;
		} else {
			HuffmanNode node = (HuffmanNode) tree;
			if(curPos<encodeString.length()){
				if (encodeString.charAt(curPos) == '1') {
					curPos++;
					return getChar(node.right, encodeString);
				} else if (encodeString.charAt(curPos) == '0') {
					curPos++;
					return getChar(node.left, encodeString);
				}
			}else{
				return null;
			}
		}
		return null;
	}
}