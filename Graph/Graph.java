package Graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import DFS.DFS;

/**
 * 图
 * 
 * @author wangpeiyu
 *
 */
public class Graph {

	/**
	 * 顶点数
	 */
	private int v;

	/**
	 * 边数
	 */
	private int e;

	/**
	 * 使用邻接矩阵保存图中各个顶点的关系
	 */
	private int[][] adj;

	public Graph(final int v) {
		this.v = v;
		this.e = 0;
		this.adj = new int[v][v];
		for (int i = 0; i < v; i++) {
			for (int j = 0; j < v; j++) {
				adj[i][j] = 0;
			}
		}
	}

	public void addEdge(int v, int w) throws IllegalArgumentException {
		if (w < this.v && v < this.v) {

			if (1 != adj[v][w]) {
				adj[v][w] = adj[w][v] = 1;
				e++;
			}

		} else {
			throw new IllegalArgumentException("顶点序号大于本图的顶点数");
		}
	}

	public int Edge() {
		return e;
	}

	public int v() {
		return v;
	}

	/**
	 * 返回与v顶点的所有相连的顶点，以Iterator形式
	 * 
	 * @param v
	 * @return
	 * @throws IllegalArgumentException
	 */
	@SuppressWarnings("unchecked")
	public Iterator<Integer> getAdjOfV(int v) throws IllegalArgumentException {
		if (v < this.v) {
			List<Integer> list = new ArrayList<Integer>();
			for (int i = 0; i < this.v; i++) {
				if (1 == adj[v][i]) {
					list.add(i);
				}
			}
			return list.iterator();
		} else {
			throw new IllegalArgumentException("顶点序号大于本图的顶点数");
		}
	}

	/**
	 * 测试图
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Graph graph=new Graph(6);
		graph.addEdge(0, 1);
		graph.addEdge(0, 3);
		graph.addEdge(0, 5);
		graph.addEdge(1, 4);
		graph.addEdge(1, 5);
		graph.addEdge(2, 4);
		graph.addEdge(2, 3);
		graph.addEdge(3, 4);
		graph.addEdge(0, 1);
		System.out.println("图的边的条数是:"+graph.Edge());
	}

}