
public class Edge {
	Node start;
	Node end;
	
	public Edge (Node a, Node b) {
		this.start = a;
		this.end = b;
	}

	public Node getStart() {
		return start;
	}

	public void setStart(Node start) {
		this.start = start;
	}

	public Node getEnd() {
		return end;
	}

	public void setEnd(Node end) {
		this.end = end;
	}

}
