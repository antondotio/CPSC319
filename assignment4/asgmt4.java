import java.io.*;
import java.util.*;

public class asgmt4{
    private static ArrayList<PixelVertex> vertices;
    private static int x, y, z;

    public static void populateArray(Scanner scan){
        for(int i = 0; i < z; i++){
            int name = scan.nextInt();
            PixelVertex vertex = new PixelVertex(x*y);

            for(int j = 0; j < vertex.pixel.length; j++){
                int num = scan.nextInt();
                if(num == 1)
                    vertex.pixel[j] = true;
                else if(num == 0)
                    vertex.pixel[j] = false;
            }

            vertices.add(vertex);
        }
    }
    
    public static void main(String[] args) throws IOException{
        String[] fileName = args[0].split("\\.");
        Scanner scan = new Scanner(new File(args[0]));
        
        x = scan.nextInt();
        y = scan.nextInt();
        z = scan.nextInt();

        vertices = new ArrayList<>(z);
        int[][] adj = new int[z][z];

        for(int i = 0; i < z; i++){
            adj[i][i] = -1;
        }

        populateArray(scan);

        // Graph g = new Graph(adj);
        // g.printGraph(fileName[0] + "-GRAPH_out." + fileName[1]);
        // g.printMST(fileName[0] + "-MST_out." + fileName[1]);
        
        scan.close();
    }
}