package DFS;

import java.util.Iterator;

import Graph.Graph;

/**
 * 深度优先搜索
 * @author wangpeiyu
 *
 */
public class DFS {
	
	private Graph graph;
	
	private boolean marked[];
	
	private int count;
	
	private int v;
	
	public DFS(Graph graph){
		this.graph=graph;
		v=graph.v();
		marked=new boolean[v];
		for(int i=0;i<v;i++){
			marked[i]=false;
		}
	}
	
	
	public void dfs(int v){
		marked[v]=true;
		handlerWithV(v);
		Iterator<Integer> iterator=graph.getAdjOfV(v);
		while(iterator.hasNext()){
			int sonV=iterator.next();
			if(!marked[sonV]){
				dfs(sonV);
			}
		}
	}
	
	/**
	 * 对顶点V进行响应的处理
	 * @param v
	 */
	public void handlerWithV(int v){
		System.out.print(v+"   ");
	}

	/**
	 * 测试dfs算法
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
		new DFS(graph).dfs(0);
	}

}
