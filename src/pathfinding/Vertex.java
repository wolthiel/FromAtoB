package pathfinding;

import java.util.HashMap;
import pathfinding.WeightedGraph.Mode;

public class Vertex implements Node {
	
	private int index;
	private String name;
	private HashMap<Vertex, Double> neighbours = new HashMap<Vertex, Double>();
	
	private boolean explored;
	private double distanceFromStartingNode;
	private Vertex previousNode;
	
	public Vertex(int index, String name) {
		this.index = index;
		this.name = name;
		this.explored = false;
		this.distanceFromStartingNode = Double.POSITIVE_INFINITY;
		this.previousNode = null;
	}
	
	public void reset() {}
	
	public void explore (Mode mode) {}
	
	public void addNeighbour(Node node, double distance) {}
	
	public boolean setDistanceAndPrevNode(double shortestDistanceCandidate, Node prevNodeCandidate) {}
	
	@Override
	public boolean equals(Object o) {
		Vertex v = (Vertex) o;
		return index==v.getIndex();
	}
	
	public HashMap<Vertex, Double> getNeighbours() {}
	
	public Node getPreviousNode() {}
	
	public int getIndex() {}
	
	public boolean getExplored() {}
	
	public String getName() {}
	
	public double getDistanceFromStartingNode() {}

}
