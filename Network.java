import java.util.*;
import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Network {
	private ArrayList <Edge> Edges;
	private ArrayList <Node> Nodes;
	private HashMap <String, Integer> degree = new HashMap <String, Integer> ();
	Integer hubValue = null;
	private Scanner sc =new Scanner(System.in);
	public Network() 
	{
		
		Nodes = new ArrayList<>();
		Edges = new ArrayList<>();
		
	}
	
	
	
	public Node AddNode(String name)
	{
		Node node = null;	
		for (Node exists : Nodes) {
			if(exists.getName().contentEquals(name)) {
				node = exists;
			}
		}
		if(node == null) {
			node = new Node(name);
			Nodes.add(node);
		}
		return node;
	}
	

	public Edge AddEdge(Node n1, Node n2) {
		Edge edge = null;
		for(Edge exists : Edges) {
			if((exists.getStart().equals(n1) & exists.getEnd().equals(n2))||(exists.getStart().equals(n2) & exists.getEnd().equals(n1)))
				edge = exists;
		}
		if(edge == null) {
			edge = new Edge(n1, n2);
			Edges.add(edge);
		}
		return edge;
		
	}
	
	public void print() {
		for(Edge ident : Edges) {
			System.out.println(ident.getStart().getName());
			System.out.println(ident.getEnd().getName());
			System.out.println();
		}
	}
	
	public static TableModel toTableModel(Map<?,?> map) {
	    DefaultTableModel model = new DefaultTableModel(
	        new Object[] { "Key", "Value" }, 0
	    );
	    for (Map.Entry<?,?> entry : map.entrySet()) {
	        model.addRow(new Object[] { entry.getKey(), entry.getValue() });
	    }
	    return model;
	}
	
		public void readFile() throws IOException 
		{
			
			System.out.println("Please enter filename:");
	        String input = sc.nextLine();
	        Path file = Paths.get(input);
	        try(BufferedReader read = Files.newBufferedReader(file)) {
	        	String line = null;
	        	while((line = read.readLine()) != null) {
	        		String [] parts = line.split("	");
	        		String node1 = parts[0];
	        		String node2 = parts[1];
	        		Node n1 = AddNode(node1);
	        		Node n2 = AddNode(node2);
	        		AddEdge(n1, n2);
	        	}
	        	
	        	read.close();
	        	
	        }
	        catch(IOException x) {
	        	System.out.println("Error, incorrect format or file does not exist. Please enter filename:");
	        	System.err.format("IOException: %s%n", x);
	        	readFile();
	        }
		}
	        
		public void writeEdge() throws IOException {
			System.out.println("Enter new interaction. Enter 1st protein name:");
			String prot1 = sc.nextLine();
			String prot2 = sc.nextLine();
			if (prot1 != null) {
				Node n1 = null;
				Node n2 = null;
				while(sc.hasNext()) {
					n1 = AddNode(prot1);
					}
				
				System.out.println("Enter 2nd protein name:");
				while(sc.hasNext()) {
					n2 = AddNode(prot2);
					}
				AddEdge(n1, n2);
				writeEdge();
			} else if (prot1 == null) {
			print();
			}	
		}
			    	
public void deg()
{
	System.out.println("Degree of Node - Please enter name of node:");
	String name = sc.nextLine();
	int deg = 0;
			for(Edge e :Edges) {
				if((e.getStart().getName().contentEquals(name)||e.getEnd().getName().contentEquals(name))||((e.getStart().getName().contentEquals(name) && e.getEnd().getName().contentEquals(name)))) {
					deg ++;
				}
				
				}
			
			System.out.println("Degree of " + name + " is " + deg);	
			}


public void avHighDeg()
{
	
	double cDeg = 0;
		for(Node n :Nodes) {
			int deg = 0;
			for (Edge e:Edges) {
				if((e.getStart().getName().contentEquals(n.getName())||e.getEnd().getName().contentEquals(n.getName()))||(e.getStart().getName().contentEquals(n.getName()) && e.getEnd().getName().contentEquals(n.getName()))) {
					deg ++;
					cDeg ++;
			}
				degree.put(n.getName(), deg);	
			}
		}
		double avDeg = cDeg/Nodes.size();
		List<String> hubs = new ArrayList<>();
		for (Entry<String, Integer> entry : degree.entrySet()) {
		  if (hubValue == null || hubValue.equals(entry.getValue())) {
		    hubValue = entry.getValue();
		    hubs.add(entry.getKey());
		  } else if (entry.getValue() > hubValue) {
		    hubValue = entry.getValue();
		    hubs.clear();
		    hubs.add(entry.getKey());
		  }
		}
		System.out.println("Average degree of all nodes: " + avDeg);
		System.out.println("Highest Degree: "+hubValue);
		System.out.println("Proteins with a degree of " + hubValue + " are: " + hubs);
		System.out.println(degree);
		}

public void outTable() {
	HashMap <Integer, Integer> table = new HashMap <Integer, Integer> ();
	for (int i = 1; i < hubValue + 1; i++) {
		int count = 0;
		for (Entry<String, Integer> value : degree.entrySet()) {
			if (value.getValue()==i) {
				count ++;
			}
		}
		table.put(i, count);
}
System.out.println(table);


JTable t=new JTable(toTableModel(table));
JPanel p=new JPanel();
p.add(t);
JFrame f=new JFrame();
f.add(p);
f.setSize(200,200);
f.setVisible(true);
}	

}

	
		
	
		