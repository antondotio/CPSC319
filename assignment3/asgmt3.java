import java.util.Scanner;
import java.io.*;

public class asgmt3{

    public static class BinaryTree{
        Node root;

        private static class Node{
            String word;
            int frequency;
            Node left;
            Node right;

            public Node(String word){
                this.word = word;
                frequency = 1;
                left = null;
                right = null; 
            }
        }

        public Node insert(Node curr, String word){
            if(curr == null){
                return new Node(word);
            }

            if(word.compareTo(curr.word) < 0){
                curr.left = insert(curr.left, word);
            }else if(word.compareTo(curr.word) > 0){
                curr.right = insert(curr.right, word);
            }else{
                // word already exists, just increase frequency
                curr.frequency++;
                return curr;
            }
            return curr;            
        }

        public void inOrder(Node curr){
            
        }

        public void preOrder(Node curr){

        }
        
        public void postOrder(Node curr){

        }

        public void maximumDepth(Node curr){

        }

    }

    public static void createBinaryTree(BinaryTree bnt, String[] words){
        System.out.println("Creating the Binary Tree...");
        for(int i = 0; i < words.length; i++){
            bnt.root = bnt.insert(bnt.root, words[i]);
        }
    }

    public static void initialOutput(BinaryTree bnt){
    }

    public static String[] readFile(String file){
        File text = null;
        BufferedReader br = null;
        String sentence = "";
        try{
            text = new File(file);
            br = new BufferedReader(new FileReader(text));
            while(br.readLine() != null){
                sentence += br.readLine() + " ";
            } 
        }catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
        }
        //This removes all punctuations, turns everything into lowercase and splits it by the spaces
        String[] words = sentence.replaceAll("\\p{P}", "").toLowerCase().split("\\s+");

        return words;
    }
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        System.out.print("Please enter the name of the text file(excluding '.txt'): ");
        String inFile = scan.nextLine() + ".txt";
        String[] words = readFile(inFile);
        BinaryTree bnt = new BinaryTree();

        createBinaryTree(bnt, words); 
        initialOutput(bnt);

        scan.close();
    }
}