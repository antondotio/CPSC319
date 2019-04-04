import java.io.PrintWriter;
import java.io.IOException;
import java.util.PriorityQueue;

public class Graph{
    int n;
    int [][] adj;

    public Graph(int[][] _adj){
        adj = _adj;
        n = adj.length;
    }

    public void printGraph(String outFile) throws IOException {
		PrintWriter pr = new PrintWriter(outFile);
		pr.println("Edge\t\tWeight");
		boolean[] vis = new boolean[n];
		this.DFS(0, vis, pr);
		pr.close();
    }
    
    public void printMST(String outFile) throws IOException {
		PrintWriter pr = new PrintWriter(outFile);
		pr.println("Edge\t\tWeight");
		this.prim(0, pr);
		pr.close();
    }
    
	private void DFS(int u, boolean[] vis, PrintWriter pr) {
        vis[u] = true;

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; i++){

            }
        }
    }

    private void prim(int s, PrintWriter pr) {

    }

}