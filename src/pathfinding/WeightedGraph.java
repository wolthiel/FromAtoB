package pathfinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class WeightedGraph {
	
	private double[][] adjacencyMatrix;
	private Random rand;
	private ArrayList<Vertex> graph = new ArrayList<Vertex>();

	// generates an array of all the letters of the English alphabet, since .concat must take two parameters, 
	// we added numbers from 0 to 9 so that the vertices names do not repeat
	private static String[] names = IntStream.concat(
									IntStream.rangeClosed('A', 'Z'),
									IntStream.rangeClosed('0', '9')
									).mapToObj(c -> (char) c+",").collect(Collectors.joining()).split(",");
			
	
	public WeightedGraph(int nodes, int edges) {
		rand = new Random();
		adjacencyMatrix = createAdjacencyMatrix(nodes, edges);
		graph = createGraphFromAdjacencyMatrix(adjacencyMatrix, names);
	}
	
	public void dijkstraForRandomPoints() {
		int start = rand.nextInt(graph.size());
		int end = rand.nextInt(graph.size());
		while (start == end) {
			start = rand.nextInt(graph.size());
			end = rand.nextInt(graph.size());
		}
		System.out.println("\nStarting point: " + graph.get(start).getName());
		System.out.println("Destination point: " + graph.get(end).getName() + "\n");
		System.out.println(dijkstra(start, end, Mode.CHEAPEST));
		resetGraph();
		System.out.println(dijkstra(start, end, Mode.SHORTEST));
	}
	
	public String dijkstra(int start, int end, Mode mode) {
		Vertex source = graph.get(start);
		Vertex destination = graph.get(end);
		
		source.setDistance(0);
		
		while(destination.getExplored() == false) {
			ArrayList<Vertex> exploreList = new ArrayList<Vertex>();
			
			graph.forEach(vertex -> {
				if (vertex.getExplored() == false) {
					exploreList.add(vertex);
				}
			});
			
			Vertex exploreVertex = exploreList.stream().min(Comparator.comparingDouble(Vertex::getDistanceFromStartingNode)).get();
			exploreVertex.setExplored();
			//System.out.println("Explloring vertex " + exploreVertex.getName());
			double [] edges = adjacencyMatrix[exploreVertex.getIndex()];
			
			for (int i = 0; i < edges.length; i++) {
				if (edges[i] > 0) {
					double cost = mode == Mode.CHEAPEST ? edges[i] : 1;
					//double cost = edges[i];
					if ( exploreVertex.getDistanceFromStartingNode() + cost < graph.get(i).getDistanceFromStartingNode() ) {
						graph.get(i).setDistance(exploreVertex.getDistanceFromStartingNode() + cost);
						graph.get(i).setPreviousNode(exploreVertex);
					}
				}
			}	
		}	

		if (destination.getPreviousNode() == null) {
			return "No path exists";
		}

		ArrayList<Vertex> previousNodes = new ArrayList<>();
		Vertex current = destination;
		while (current.getPreviousNode() != null) {
			previousNodes.add(current);
			current = current.getPreviousNode();
		}
		previousNodes.add(source);

		Collections.reverse(previousNodes);
		
		String path = "The " + mode + " path between two vertices:";
		for (int i=0; i<previousNodes.size(); i++) {
			path += " " + previousNodes.get(i).getName();
		}
		toString(path);
		
		return path;
	}
	
	public String toString(String path) {
		String result = path;
		return result;
	}
	
	public void resetGraph() {
		graph.forEach(Vertex::reset);
	}
		
	private double [][] createAdjacencyMatrix(int nodes, int edges) {
		double[][] matrix = new double[nodes][nodes];
		int i = edges;
		while (i > 0) {
			int x = rand.nextInt(nodes);
			int y = rand.nextInt(nodes);
			if (x != y && matrix[x][y] == 0.0) {
				matrix[x][y] = 1 + rand.nextInt(8);
				i--;
			}
		}
		return matrix;
	}
	
	private ArrayList<Vertex> createGraphFromAdjacencyMatrix(double[][] adjMat, String[] names) {
		ArrayList<Vertex> vertices = new ArrayList<Vertex>();
		for (int i=0; i<adjMat.length; i++) {
			vertices.add(new Vertex(i, names[i]));
		}
		return vertices;
	}

	public void printMatrix() {
		var header = "  " + graph.stream().map(Vertex::getName).collect(Collectors.joining("    "));
		System.out.println(header);
		for (int i = 0; i < adjacencyMatrix.length; i++) {
			System.out.print(graph.get(i).getName() + " ");
			for (int j = 0; j < adjacencyMatrix.length; j++) {
				System.out.print(adjacencyMatrix[i][j]);
				System.out.print("  ");
			}
			System.out.println("");
		}
	}

	public enum Mode {
		CHEAPEST, SHORTEST
	}
}
