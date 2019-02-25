import java.util.Scanner;
import java.io.*;

public class asgmt2{
    
    public static class LinkedList{
        Node head;
        int size;

        private static class Node{
            private String data;
            private Node next;
    
            public Node(String data){
                this.data = data;
                this.next = null;
            }
        }


        public void push_front(String data){
            Node new_node = new Node(data);
            new_node.next = head;
            head = new_node;
            size++;
        }

        public void push_back(String data){
            Node new_node = new Node(data);
            
            if(head == null){
                new_node.next = head;
                head = new_node;
            }else{
                Node p = head;
                while(p.next != null){
                    p = p.next;
                }
                p.next = new_node;
                new_node.next = null;
            }
            size++;
        }

        public String search(String data){
            Node p = head;
            while(p != null){
                if(data.equals(p.data)){
                    return data;
                }
                p = p.next;
            }
            return null;
        }

        public void printList(LinkedList list){
            Node currNode = list.head;
            while(currNode != null){
                System.out.print(currNode.data + " ");
                currNode = currNode.next;
            }
            System.out.println();
        }
    }

    public static int numOfUniqueCombs(String[] words){
        LinkedList uniqueAnagram = new LinkedList();
        for(String w: words){
            String anagram = anagram(w);
            if(uniqueAnagram.search(anagram) == null){            
                uniqueAnagram.push_back(anagram);
            }
        }
        return uniqueAnagram.size;
    }

    public static boolean searchAnagram(LinkedList[] list, String[] words, int i){
        for(int j = 0; j < list.length; j++){
            if(list[j] != null){
                if(list[j].search(words[i]) != null){
                    return true;
                }
            }
        }
        return false;
    }

    public static void createList(String[] words){
        LinkedList[] list = new LinkedList[numOfUniqueCombs(words)];
        int current = 0;
        for(int i = 0; i < words.length; i++){
            boolean found = searchAnagram(list, words, i);
            if(!found){
                list[current] = new LinkedList();
                list[current].push_front(words[i]);
                for(int l = i + 1; l < words.length; l++){
                    if(anagram(words[i]).equals(anagram(words[l]))){
                        list[current].push_back(words[l]);
                    }
                }
                current++;
            }
        }
        for(int j = 0; j < list.length; j++){
            list[j].printList(list[j]);
        }
    }

    public static String anagram(String word){
        return mergeSort(word.toCharArray());
    }

    public static void merge(char[] word, char[] left, char[] right){
        int a = 0, b = 0;
        for(int i = 0; i < word.length; i++){
            if(b >= right.length || (a < left.length && left[a] < right[b])){
                word[i] = left[a];
                a++;
            }else{
                word[i] = right[b];
                b++;
            }
        }
    }

    public static String mergeSort(char[] word){
        if(word.length >= 2){
            char[] left = new char[word.length / 2];
            char[] right = new char[word.length  - word.length / 2];

            for(int i = 0; i < left.length; i++){
                left[i] = word[i];
            }
            for(int i = 0; i < right.length; i++){
                right[i] = word[i + word.length / 2];
            }
            mergeSort(left);
            mergeSort(right);
            merge(word, left, right);
            return new String(word);
        }
        return null;
    }

    public static void merge(String[] words, String[] left, String[] right){
        int a = 0, b = 0;
        for(int i = 0; i < words.length; i++){
            if(b >= right.length || (a < left.length && left[a].compareTo(right[b]) < 0)){
                words[i] = left[a];
                a++;
            }else{
                words[i] = right[b];
                b++;
            }
        }
    }

    public static void mergeSort(String[] words){
        if(words.length >= 2){
            String[] left = new String[words.length / 2];
            String[] right = new String[words.length  - words.length / 2];

            for(int i = 0; i < left.length; i++){
                left[i] = words[i];
            }
            for(int i = 0; i < right.length; i++){
                right[i] = words[i + words.length / 2];
            }
            mergeSort(left);
            mergeSort(right);
            merge(words, left, right);
        }
        
    }

    public static String[] fileRead(Scanner scan){
        String words = "";
        while (scan.hasNext()) {
            words = words + scan.nextLine() + ";";
        }
        String[] wordList = words.split(";");
        return wordList;
    }

    public static void main(String[] args) throws IOException{
        Scanner scan = new Scanner(System.in);
        String[] words = fileRead(scan);

        mergeSort(words);
        createList(words);

        scan.close();
    }
}