package pathfinding;

public class Vertex {
	
	private int index;
	private String name;
	
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
	
	public void reset() {
		this.explored = false;
		this.distanceFromStartingNode = Double.POSITIVE_INFINITY;
		this.previousNode = null;
	}
	
	public void setExplored() {
		explored = true;
	}
	
	public void setPreviousNode(Vertex prev) {
		 //System.out.println("Prevnode " + this.getName() + ": " + prev.getName());
		 this.previousNode = prev;
	}
	
	public Vertex getPreviousNode() {
		return this.previousNode;
	}
	
	@Override
	public boolean equals(Object o) {
		Vertex v = (Vertex) o;
		return index==v.getIndex();
	}
	
	public double getDistanceFromStartingNode() { 
		return this.distanceFromStartingNode; 
		}
	
	public double setDistance(double distance) {
		return this.distanceFromStartingNode = distance;
	}

	public int getIndex() {
		return this.index;
	}

	public boolean getExplored() {
		return this.explored;
	}

	public String getName() {
		return this.name;
	}

}
