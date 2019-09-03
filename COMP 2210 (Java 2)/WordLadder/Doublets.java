import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;


/**
 * Provides an implementation of the WordLadderGame interface.
 *
 * @author Your Name (you@auburn.edu)
 * @author Dean Hendrix (dh@auburn.edu)
 * @version 2018-10-22
 */
public class Doublets implements WordLadderGame {
   
   // The word list used to validate words.
   // Must be instantiated and populated in the constructor.
   /////////////////////////////////////////////////////////////////////////////
   // DECLARE A FIELD NAMED lexicon HERE. THIS FIELD IS USED TO STORE ALL THE //
   // WORDS IN THE WORD LIST. YOU CAN CREATE YOUR OWN COLLECTION FOR THIS     //
   // PURPOSE OF YOU CAN USE ONE OF THE JCF COLLECTIONS. SUGGESTED CHOICES    //
   // ARE TreeSet (a red-black tree) OR HashSet (a closed addressed hash      //
   // table with chaining).
   /////////////////////////////////////////////////////////////////////////////
   private Graph lexicon;
   
   /**
    * Instantiates a new instance of Doublets with the lexicon populated with
    * the strings in the provided InputStream. The InputStream can be formatted
    * in different ways as long as the first string on each line is a word to be
    * stored in the lexicon.
    */
   
   public Doublets(InputStream in) {
      try {
         this.lexicon = new Graph();
         //////////////////////////////////////
         // INSTANTIATE lexicon OBJECT HERE  //
         //////////////////////////////////////
         Scanner s = new Scanner(new BufferedReader(new InputStreamReader(in)));
         while (s.hasNext()) {
            String str = s.next();
            /////////////////////////////////////////////////////////////
            // INSERT CODE HERE TO APPROPRIATELY STORE str IN lexicon. //
            /////////////////////////////////////////////////////////////
            
            this.lexicon.add(str.toLowerCase());
            s.nextLine();
         }
         
         //System.out.println(this.lexicon);
         in.close();
      }
      catch (java.io.IOException e) {
         System.err.println("Error reading from InputStream.");
         System.exit(1);
      }
   }
   
   //////////////////////////////////////////////////////////////
   // ADD IMPLEMENTATIONS FOR ALL WordLadderGame METHODS HERE  //
   //////////////////////////////////////////////////////////////
   
   /**
    * Returns the Hamming distance between two strings, str1 and str2. The
    * Hamming distance between two strings of equal length is defined as the
    * number of positions at which the corresponding symbols are different. The
    * Hamming distance is undefined if the strings have different length, and
    * this method returns -1 in that case. See the following link for
    * reference: https://en.wikipedia.org/wiki/Hamming_distance
    *
    * @param  str1 the first string
    * @param  str2 the second string
    * @return      the Hamming distance between str1 and str2 if they are the
    *                  same length, -1 otherwise
    */
   
   public int getHammingDistance(String str1, String str2) {
      str1 = str1.toLowerCase();
      str2 = str2.toLowerCase();
      if (str1.length() != str2.length()) {
         return -1;
      }
      
      int count = 0;
      for (int i = 0; i < str1.length(); i++) {
         if (str1.charAt(i) != str2.charAt(i)) {
            count++;
         }
      }
      return count;
   }
   /**
    * Returns a minimum-length word ladder from start to end. If multiple
    * minimum-length word ladders exist, no guarantee is made regarding which
    * one is returned. If no word ladder exists, this method returns an empty
    * list.
    *
    * Breadth-first search must be used in all implementing classes.
    *
    * @param  start  the starting word
    * @param  end    the ending word
    * @return        a minimum length word ladder from start to end
    */
   
   public List<String> getMinLadder(String start, String end) {
      return lexicon.bfs(start, end);
   }
   /**
    * Returns all the words that have a Hamming distance of one relative to the
    * given word.
    *
    * @param  str the given word
    * @return      the neighbors of the given word
    */
   
   public List<String> getNeighbors(String str) {
      str = str.toLowerCase();
      List<String> out = new ArrayList<String>();
      if (!isWord(str)) {
         return new ArrayList<String>();
      }
      if (!lexicon.hasNeighbors(str)) {
         lexicon.setNeighbors(str);
      }
      for (String neighbor : lexicon.adj.get(str)) {
         out.add(neighbor);
      }
      return out;
      
   }
   
   /**
    * Returns the total number of words in the current lexicon.
    *
    * @return number of words in the lexicon
    */
   public int getWordCount() {
      return this.lexicon.adj.keySet().size();
   }
   /**
    * Checks to see if the given string is a word.
    *
    * @param  str the string to check
    * @return     true if str is a word, false otherwise
    */
   
   public boolean isWord(String str) {
      return this.lexicon.adj.containsKey(str.toLowerCase());
   }
   
   /**
    * Checks to see if the given sequence of strings is a valid word ladder.
    *
    * @param  sequence the given sequence of strings
    * @return          true if the given sequence is a valid word ladder,
    *                       false otherwise
    */
   
   public boolean isWordLadder(List<String> sequence) {
      if (sequence.isEmpty()) {
         return false;
      }
      
      for (int i = 0; i < sequence.size() - 1; i++) {
         String current = sequence.get(i).toLowerCase();
         String next = sequence.get(i + 1).toLowerCase();
         if (!isWord(current) || !isWord(next)) {
            return false;
         }
         if (!lexicon.hasNeighbors(current)) {
            lexicon.setNeighbors(current);
         }
         if (!lexicon.hasNeighbors(next)) {
            lexicon.setNeighbors(next);
         }
         if (!lexicon.adj.get(current).contains(next)) {
            return false;
         }
      }
      return true;
   }
   
   
   ////////////////////////////////////////////////////
   ////Graph class, stores all words and neighbors////
   ///////////////////////////////////////////////////
   
   /**
    * Class for graph.
    */
   
   public class Graph {
      
      private Map<String, Set<String>> adj;
      
      private final Set<Character> alphabet = new HashSet<Character>(
              Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
                      'm', 'n', 'o', 'p', 'q', 'r', 's',
                      't', 'u', 'v', 'w', 'x', 'y', 'z'));
      
      public Graph() {
         this.adj = new HashMap<String, Set<String>>();
      }
   
      /**
       * Graph constructor.
       * @param words adds words to "dictionary" mapped to
       *              values of empty sets
       */
      
      public Graph(Set<String> words) {
         this.adj = new HashMap<String, Set<String>>();
         for (String str : words) {
            this.adj.put(str.toLowerCase(), new HashSet<String>());
         }
         
      }
      
      @Override
      /**
       * Returns string of elements in graph.
       */
      public String toString() {
         return adj.keySet().toString();
      }
        
        /* Too Slow
        //set up neighbors on graph with all elements added
        public void setUpAllNeighbors() {
            Set<String> words = adj.keySet();
            for (String word: words) {
                for(String other: words) {
                    if(!other.equals(word) && getHammingDistance(word, other) == 1) {
                        this.adj.get(word).add(other);
                    }
                }
            }
        }
        */
   
      /**
       * Adds str to map with a value of an empty set.
       * @param str string to be added to set
       * @return true if str is unique and add is successful
       */
      
      public boolean add(String str) {
         if (adj.containsKey(str)) {
            return false;
         }
         adj.put(str.toLowerCase(), new HashSet<String>());
         return true;
      }
   
      /**
       * Adds neighbor to value set in map.
       * @param node specifies recipient of neighbor
       * @param neighbor neighbor to be added
       */
      
      public void addNeighbor(String node, String neighbor) {
         adj.get(node.toLowerCase()).add(neighbor.toLowerCase());
      }
   
      /**
       * Checks if neighbors exist.
       * @param node string to be checked
       * @return true if any neighbors exist
       */
      
      public boolean hasNeighbors(String node) {
         return !adj.get(node.toLowerCase()).isEmpty();
      }
   
      /**
       * Traverses through all possible strings with distance == 1
       * and adds to set if unique and exist as independent
       * keys in map.
       * @param node key to add neighbors to
       */
      public void setNeighbors(String node) {
         node = node.toLowerCase();
         if (!isWord(node)) {
            return;
         }
         
         for (int i = 0; i < node.length(); i++) {
            StringBuilder temp = new StringBuilder(node);
            for (char letter : alphabet) {
               temp.setCharAt(i, letter);
               if (isWord(temp.toString()) && !temp.toString().equals(node)) {
                  addNeighbor(node, temp.toString());
               }
               
            }
         }
      }
   
      /**
       * Uses a breadth first search to fnd the shortest "path" between two words.
       * @param from string at start of path
       * @param to string at end of path
       * @return list of strings along path
       */
      
      public List<String> bfs(String from, String to) { //finds shortest path between
         //two nodes
         if (!isWord(from) || !isWord(to) || from.length() != to.length()) {
            return new ArrayList<String>();
         }
         
         //Allows for backtracking
         Map<String, String> previous = new HashMap<String, String>();
         
         //Allows reuse of bfs without recreating graph
         Map<String, Boolean> visited = new HashMap<String, Boolean>();
         for (String key : adj.keySet()) {
            visited.put(key, false);
         }
         visited.put(from, true);  //marks start as visited
         
         LinkedList<String> queue = new LinkedList<String>();
         queue.add(from);
         
         while (queue.size() > 0) {
            //System.out.println(queue);
            from = queue.poll(); //pop last
            //System.out.println("f: " + from + " t: " + to);
            if (from.equals(to)) {
               break;
            }
            if (!hasNeighbors(from)) {
               this.setNeighbors(from);
            }
            //System.out.print("\n" + from + " N: ");
            for (String neighbor : adj.get(from)) {
               //System.out.print(neighbor + " ");
               if (visited.get(neighbor) == false) {
                  visited.put(neighbor, true);
                  previous.put(neighbor, from);
                  queue.add(neighbor);
                  
               }
            }
         }
         
         if (!from.equals(to)) {
            //System.out.println(queue);
            return new ArrayList<String>();
         }
         
         List<String> shortestPath = new ArrayList<String>();
         
         for (String current = to; current != null; current = previous.get(current)) {
            shortestPath.add(0, current);
         }
         
         return shortestPath;
         
      }
      
   }
   
}

