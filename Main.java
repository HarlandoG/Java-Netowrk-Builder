import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		Network File = new Network();
		File.readFile();
		//File.writeEdge();
		File.deg();
		File.avHighDeg();
		File.outTable();
	}

}
