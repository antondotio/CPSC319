import java.util.Scanner;
import java.io.*;

public class asgmt3{

    public static class BinaryTree{
        Node root;
        int counter = 0;
        int numUniqueWords = 0;
        int mostFreq = 0;

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
            if(curr == null)
                return;
            inOrder(curr.left);
            System.out.print(curr.word + " ");
            inOrder(curr.right);
        }

        public void preOrder(Node curr){
            if(curr == null)
                return;
            System.out.print(curr.word + " ");
            preOrder(curr.left);
            preOrder(curr.right);
        }
        
        public void postOrder(Node curr){
            if(curr == null)
                return;
            postOrder(curr.left);
            postOrder(curr.right);
            System.out.print(curr.word + " ");
        }

        public int maximumDepth(Node curr){
            if(curr == null)
                return 0;
            else{
                int lDepth = maximumDepth(curr.left);
                int rDepth = maximumDepth(curr.right);

                if(lDepth > rDepth)
                    return (lDepth + 1);
                else
                    return (rDepth + 1);
            }
        }

        public void initialTraverse(Node curr){
            if(curr == null)
                return;
            if(curr.frequency == 1){
                numUniqueWords++;
            }
            if(curr.frequency > mostFreq){
                mostFreq = curr.frequency;
            }
            counter++;
            
            initialTraverse(curr.left);
            initialTraverse(curr.right);
        }

        public void printFrequency(Node curr){
            if(curr == null)
                return;
             if(curr.frequency == mostFreq){
                System.out.println(curr.word + " = " + mostFreq + " times");
            }
            printFrequency(curr.left);
            printFrequency(curr.right);  
           
        }

        public boolean lookFor(Node curr, String w){
            if(curr == null)
                return false;
            if(curr.word.equals(w)){
                System.out.println("Found! It appears " + curr.frequency + " times");
                return true;
            }else{
                lookFor(curr.left, w);
                lookFor(curr.right, w);
                
                return false;
            }
        }

    }

    public static void createBinaryTree(BinaryTree bnt, String[] words){
        for(int i = 0; i < words.length; i++){
            bnt.root = bnt.insert(bnt.root, words[i]);
        }
    }

    public static void initialOutput(BinaryTree bnt){
        // bnt.numOfWordsTraverse(bnt.root);
        bnt.initialTraverse(bnt.root);

        System.out.println("Total number of words is: " + bnt.counter);
        System.out.println("Number of unique words is: " + bnt.numUniqueWords);
        
        System.out.println("The word(s) that occur(s) most often and the number of times it/they occur(s): ");
        bnt.printFrequency(bnt.root);
        
        System.out.println("The maximum height of the tree is: " + bnt.maximumDepth(bnt.root));

    }

    public static void search(BinaryTree bnt, String word){
        boolean found = bnt.lookFor(bnt.root, word);
        if(found == false){ //TODO: fix this
            System.out.println("Word not found");
        }
    }

    public static String[] readFile(String file){
        File text = null;
        Scanner scan = null;
        String sentence = "";
        try{
            text = new File(file);
            scan = new Scanner(new FileReader(text));
            while(scan.hasNext()){
                sentence += scan.nextLine() + " ";
            } 
        }catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (scan != null) {
					scan.close();
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
        
        boolean b = true;
        while(b){
            System.out.println("\nWhat else would you like to do?\n"
                                + "\t1. Find a word\n"
                                + "\t2. Print binary tree IN-ORDER\n"
                                + "\t3. Print binary tree PRE-ORDER\n"
                                + "\t4. Print binary tree POST-ORDER\n"
                                + "\t5. Quit");
            int choice = scan.nextInt();
            scan.nextLine();
            switch(choice){
                case 1:
                    System.out.print("What word are you looking for? ");
                    String find = scan.nextLine();
                    search(bnt, find);
                    break;
                case 2: 
                    System.out.println("\nPrinting the binary tree IN-ORDER...");
                    bnt.inOrder(bnt.root);
                    System.out.println();
                    break;
                case 3:
                    System.out.println("\nPrinting the binary tree PRE-ORDER...");
                    bnt.preOrder(bnt.root);
                    System.out.println();
                    break;
                case 4:
                    System.out.println("\nPrinting the binary tree POST-ORDER...");
                    bnt.postOrder(bnt.root);
                    System.out.println();
                    break;
                case 5:
                    
                    System.out.println("Ending Program");
                    b = false;
                    break;
            }
        }

            scan.close();
    }
}