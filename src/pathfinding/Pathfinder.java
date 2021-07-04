package pathfinding;

public class Pathfinder {
	
	public static void main(String[] args) {
		
		/** 
		 * max for the first parameter(nodes) = 36; 
		 * max for the second parameter(edges) = nodes^2 - nodes;
		 * this is necessary in order non to get a point connected to itself
		 * thus, point A doesn't have an edge to point A, point B is not connected to point B and so on.
		 */
		
		WeightedGraph graph = new WeightedGraph(20, 40);  
		System.out.println("The random Matrix:\n");
		graph.printMatrix();
		graph.dijkstraForRandomPoints();
//		graph.resetGraph();
		
//		WeightedGraph graph2 = new WeightedGraph(3, 6);
//		System.out.println("The random Matrix2:\n");
//		graph2.printMatrix();
//		System.out.println(graph.dijkstraForRandomPoints(Mode.SHORTEST));
//		graph2.resetMatrix();
	}
}