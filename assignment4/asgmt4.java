import java.io.*;
import java.util.*;

public class asgmt4{
    private static ArrayList<PixelVertex> vertices;
    private static int row, col, size;
    private static int[][] adj;

    public static void populateArray(Scanner scan){
        for(int i = 0; i < size; i++){
            int name = scan.nextInt();
            PixelVertex vertex = new PixelVertex(row*col);

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

    public static void checkDifference(){
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                adj[i][j] = vertices.get(i).difference(vertices.get(j));
            }
        }
    }
    
    public static void main(String[] args) throws IOException{
        String[] fileName = args[0].split("\\.");
        Scanner scan = new Scanner(new File(args[0]));
        
        row = scan.nextInt();
        col = scan.nextInt();
        size = scan.nextInt();

        vertices = new ArrayList<>(size);
        adj = new int[size][size];

        for(int i = 0; i < size; i++){
            adj[i][i] = 0;
        }

        populateArray(scan);
        checkDifference();

        Graph g = new Graph(adj);
        g.printGraph(fileName[0] + "-GRAPH_out." + fileName[1]);
        g.printMST(fileName[0] + "-MST_out." + fileName[1]);
        
        scan.close();
    }
}