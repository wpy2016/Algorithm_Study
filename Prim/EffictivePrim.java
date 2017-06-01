package Prim;

import org.omg.PortableServer.POA;

import Graph.Graph;

public class EffictivePrim {

	private int lowcast[];// 在这个数组当中，下标索引号就是相应的节点，而每个下标指向的元素的值就是到达该节点的最短的那条边的长度

	private int lowcastOfV[];// 如果只有上面那个数组，是不够的，上面只知道了一个节点（数组下标），和到达这个节点的最短的边，但是却不知道这条边的另外一个节点，
	// 所以这个数组就是保存与上面那个数组相同下标，保存的那条边的另一个节点。下标和上面数组的下标相同，相同下标下的两个数组，lowcast数组保存最短的边，
	// lowcastOfV用于保存这条边的另一个节点
	private Graph graph;

	public EffictivePrim(Graph graph) {
		this.graph = graph;
		lowcastOfV = new int[graph.v()];
		// 数据初始化,初始化为0这个节点的值
		// 此时lowcast存放的就是第0个节点，它到其他每个节点的边就是到所有其他节点的情况了
		lowcast = graph.getArrayOfV(0);
		lowcast[0] = 0;// 表示从第0个节点开始处理
		for (int i = 0; i < graph.v(); i++) {
			lowcastOfV[i] = 0;
		}
	}

	private void prim() {
		// 此时的i并不是表示i节点，而是单纯的表示当前处理的第i个节点，并不是表示节点i，只是为了完整遍历整个图中的节点
		for (int i = 1; i < graph.v(); i++) {

			int j = 1;// 这个变量就是表示节点j，j是多少，就是表示节点j

			int k = 0;// 这个表示在目前的所有边中找到的最短的那条边的另一个节点

			int min = 65535;// 临时存储当前最短的边
			// 这个是横向遍历lowcast当前到达每个节点的最短的边
			while (j < graph.v()) {
				if (lowcast[j] != -1 && lowcast[j] != 0 && lowcast[j] < min) {
					min = lowcast[j];
					k = j;
				}
				j++;
			}
			// 标记k节点已经遍历
			lowcast[k] = 0;
			// 找到最短的边之后，k就是这个边的另一个节点,下面是保存当前k节点到其他所有节点的所有的边，
			// 下面将会将lowcastTemp的边和当前lowcast数组中边的值进行比较，然后将每个节点的最短的边重新赋值到lowcast数组中
			int lowcastTemp[] = graph.getArrayOfV(k);
			// 横向遍历lowcastTemp,更新lowcast数组中的到每个节点的最短的边
			for (int pos = 0; pos < graph.v(); pos++) {
				if (0 != lowcast[pos]) {//当前的节点还没有处理
					//第一种情况：当原来的与pos节点的距离为负数，也就是到不了，此时如果新找到的节点k，如果到pos节点的距离不是-1，也就是有距离，
					//那就将pos节点的距离更新，而且将这条边的另一个源节点，放到lowcastOfV数组中，表示到达pos节点距离短的节点为k
					//第二种情况：当原来的与pos节点的距离不是负数，但是新找到的节点k，到达pos节点的距离不是-1，也就是可到达，而且比原来的要短，此时也更新lowcast
					if (-1 == lowcast[pos] || (-1 != lowcast[pos] && lowcastTemp[pos] < lowcast[pos])) {
						if(-1!=lowcastTemp[pos]){
							lowcast[pos] = lowcastTemp[pos];
							lowcastOfV[pos] = k;	
						}
					}
				}
			}
		}
	}

	/**
	 * 生成最小支撑树
	 * @return
	 */
	public int[] MST() {
		prim();
		return lowcastOfV;
	}

	public static void main(String[] args) {
		Graph graph = new Graph(9);
		graph.addEdge(0, 1, 10);
		graph.addEdge(0, 5, 11);
		graph.addEdge(1, 0, 10);
		graph.addEdge(1, 2, 18);
		graph.addEdge(1, 6, 16);
		graph.addEdge(1, 8, 12);
		graph.addEdge(2, 1, 18);
		graph.addEdge(2, 3, 22);
		graph.addEdge(2, 8, 8);
		graph.addEdge(3, 2, 22);
		graph.addEdge(3, 8, 21);
		graph.addEdge(3, 6, 24);
		graph.addEdge(3, 7, 16);
		graph.addEdge(3, 4, 20);
		graph.addEdge(4, 3, 20);
		graph.addEdge(4, 7, 7);
		graph.addEdge(4, 5, 26);
		graph.addEdge(5, 4, 26);
		graph.addEdge(5, 6, 17);
		graph.addEdge(5, 0, 11);
		graph.addEdge(6, 5, 17);
		graph.addEdge(6, 1, 16);
		graph.addEdge(6, 3, 24);
		graph.addEdge(6, 7, 19);
		graph.addEdge(7, 3, 16);
		graph.addEdge(7, 4, 7);
		graph.addEdge(7, 6, 19);
		graph.addEdge(8, 1, 12);
		graph.addEdge(8, 2, 8);
		graph.addEdge(8, 3, 21);
		EffictivePrim prim = new EffictivePrim(graph);
		int array[] = prim.MST();
		for(int i=1;i<array.length;i++){
			System.out.println("("+i+","+array[i]+")");
		}
	}
}
