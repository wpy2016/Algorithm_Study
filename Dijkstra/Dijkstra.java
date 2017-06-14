package Dijkstra;

import java.util.Iterator;

import org.omg.PortableServer.POA;

import Graph.Graph;

/**
 * 单源最短路径
 * 只提供最短的路径长度，不记录实际的路径
 * @author wangpeiyu
 *
 */
public class Dijkstra {
	
	private Graph graph;
	//用于标记节点是否已经访问
	private boolean mark[];
	//存储开始节点到各个节点的最短路径长度
	private int distance[];
	//节点的个数
	private int V;
	
	
	public  Dijkstra(Graph graph){
		setGraph(graph);
	}
	
	public void setGraph(Graph graph) {
		assert graph==null;
		this.graph = graph;
		V=graph.v();
	}
	
	/**
	 * 开始执行Dijkstra算法
	 */
	private void doDijkstra(){
		for(int i=1;i<V;i++){
			int j=0,k=0,min=65535;
			/**
			 * 找到最短的边，而且没有访问过的
			 */
			while(j<V){
				if(-1!=distance[j]&&!mark[j]&&distance[j]<min){
					k=j;
					min=distance[j];
				}
				j++;
			}
			/**
			 * 然后根据标记该节点已经访问了，然后根据该节点更新与该节点相连的节点的距离值，在distance数组中
			 */
			mark[k]=true;
			//找到与k节点相连的节点
			Iterator<Integer> iterator=graph.getAdjOfV(k);
			while(iterator.hasNext()){
				int node=iterator.next();
				if(-1==distance[node]||(-1!=distance[node]&&distance[node]>distance[k]+graph.getDistance(node, k))){
					distance[node]=distance[k]+graph.getDistance(node, k);
				}
			}
		}
	}
	
	/**
	 * 获取图中指定两个节点间的最短距离
	 * 这里是使用了Dijkstra算法找到开始节点到所有节点的最短距离，然后返回需要的节点间的距离
	 * @param StartV
	 * @param EndV
	 * @return
	 */
	public int getMinDistanceOfTwoV(int StartV,int EndV){
		assert StartV<V&&EndV<V;
		distance=graph.getArrayOfV(StartV);
		mark=new boolean[V];
		for(int i=0;i<V;i++){
			mark[i]=false;
		}
		mark[StartV]=true;
		distance[StartV]=0;
		doDijkstra();
		return distance[EndV];
	}
	
	
	public static void main(String arg[]){
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
		Dijkstra dijkstra=new Dijkstra(graph);
		System.out.println("节点0 和 节点 5 的最短路径 "+dijkstra.getMinDistanceOfTwoV(0, 5));
		System.out.println("节点0 和 节点 2 的最短路径 "+dijkstra.getMinDistanceOfTwoV(0, 2));
		System.out.println("节点0 和 节点 1 的最短路径 "+dijkstra.getMinDistanceOfTwoV(0, 1));
		System.out.println("节点0 和 节点 4 的最短路径 "+dijkstra.getMinDistanceOfTwoV(0, 4));
		System.out.println("节点0 和 节点 7 的最短路径 "+dijkstra.getMinDistanceOfTwoV(0, 7));
	}
}
