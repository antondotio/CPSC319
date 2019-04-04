import java.io.*;
import java.util.*;

public class asgmt4{
    public static void main(String[] args) throws IOException{
        String[] fileName = args[0].split("\\.");
        Scanner scan = new Scanner(new FileReader(fileName[0]));
        
        int x = scan.nextInt();
        int y = scan.nextInt();
        int z = scan.nextInt();

        ArrayList<PixelVertex> vertices = new ArrayList<>(z);
        int[][] adj = new int[z][z];
        for(int i = 0; i < z; i++){
            adj[i][i] = -1;
        }

        for(int i = 0; i < z; i++){
            int name = scan.nextInt();
            PixelVertex vertex = new PixelVertex(x*y);

            for(int j = 0; j < vertex.pixel.length; j++){
                if(scan.nextInt() == 1)
                    vertex.pixel[j] = true;
                else
                    vertex.pixel[j] = false;
            }

            vertices.add(vertex);
            for(int j = 0; j < vertices.size(); j++){
                
            }


        }

        Graph g = new Graph(adj);
        g.printGraph(fileName[0] + "-GRAPH_out." + fileName[1]);
        g.printMST(fileName[0] + "-MST_out." + fileName[1]);
        
        scan.close();
    }
}