package BFS;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import DFS.DFS;
import Graph.Graph;
/**
 * 广度优先搜素
 * @author wangpeiyu
 *
 */
public class BFS {

	private Queue<Integer> queue;

	private boolean marked[];

	private Graph graph;

	public BFS(Graph graph) {
		this.graph = graph;
		marked = new boolean[graph.v()];
		queue = new LinkedList<Integer>();
	}

	public void bfs(int v) throws IllegalArgumentException {
		if (v >= graph.v()) {
			throw new IllegalArgumentException("遍历顶点超过范围");
		} else {
			if (!marked[v]) {
				queue.add(v);
				marked[v] = true;
			}
			while (!queue.isEmpty()) {
				int node = queue.poll();
				handlerWithV(node);
				Iterator<Integer> iterator = graph.getAdjOfV(node);
				while (iterator.hasNext()) {
					int next = iterator.next();
					if (!marked[next]) {
						queue.add(next);
						marked[next] = true;
						//此时在这里放置handlerWithV()方法，是一样的，这里是在放入队列时就行处理，上面的方法是在从队列中取出来时处理
						//handlerWithV();
					}
				}
			}
		}
	}

	/**
	 * 对node顶点进行响应的处理
	 * @param node
	 */
	private void handlerWithV(int node) {
		// TODO Auto-generated method stub
		System.out.print(node + "  ");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Graph graph = new Graph(6);
		graph.addEdge(0, 1);
		graph.addEdge(0, 3);
		graph.addEdge(0, 5);
		graph.addEdge(1, 4);
		graph.addEdge(1, 5);
		graph.addEdge(2, 4);
		graph.addEdge(2, 3);
		graph.addEdge(3, 4);
		graph.addEdge(0, 1);
		new BFS(graph).bfs(0);
	}

}
