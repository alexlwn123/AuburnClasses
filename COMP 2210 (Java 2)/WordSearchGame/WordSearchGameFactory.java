import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Provides a factory method for creating word search games. 
 *
 * @author Alex Lewin (asl0028@auburn.edu)
 * @author Dean Hendrix (dh@auburn.edu)
 * @version 11/6/18
 */

public class WordSearchGameFactory {
   /**
    * Returns an instance of a class that implements the WordSearchGame
    * interface.
    */
   public static WordSearchGame createGame() {
      // You must return an instance of your solution class here instead of null
      final int[][] Directions = {
         new int[]{0, 1},
         new int[]{0, -1},
         new int[]{1, 0},
         new int[]{-1, 0},
         new int[]{1, 1},
         new int[]{-1, 1},
         new int[]{-1, -1},
         new int[]{1,-1}
      
      };
      
      
      class Game implements WordSearchGame {
         
         private TreeSet<String> lexicon;
         private String[][] board;
         
         private Game() {
            lexicon = new TreeSet<String>();
         }
         
         public void loadLexicon(String fileName) {
            try {
               Scanner in = new Scanner(new BufferedReader(new FileReader(fileName)));
               while (in.hasNextLine()) {
                  String word = in.next();
                  in.nextLine();
                  lexicon.add(word.toUpperCase());
               }
               
            } catch (NullPointerException | IOException e) {
               throw new IllegalArgumentException();
            }
         }
         
         public void setBoard(String[] letterArray) {
            if (letterArray == null) {
               throw new IllegalArgumentException();
            }
            int n = (int) Math.sqrt((double)letterArray.length);
            if (n * n != letterArray.length) {
               throw new IllegalArgumentException();
            }
            board = new String[n][n];
            for (int i = 0; i < letterArray.length; i++) {
               board[i / n][i % n] = letterArray[i].toUpperCase();
            }
         }
         
         public String getBoard() {
            String out = Arrays.deepToString(this.board).replace("], [", "\n");
            out = out.replaceAll("[\\[\\]]", "");
            out = out.replaceAll(",", " ");
            return out;
         }
         
         public SortedSet<String> getAllValidWords(int minimumWordLength) {
            if (lexicon.size() == 0) {
               throw new IllegalStateException();
            }
            if (minimumWordLength < 1) {
               throw new IllegalArgumentException();
            }
            SortedSet<String> out = new TreeSet<String>();
            for (int r = 0; r < board.length; r++) {
               for (int c = 0; c < board.length; c++) {
                  ArrayList<Integer> path = new ArrayList<Integer>();
                  path.add(r * board.length + c);
                  out.addAll(dfsb(minimumWordLength, board[r][c], r * board.length + c, out, path));
               }
            }
            return out;
         }
         
         private SortedSet<String> dfsb(int minlen, String prefix, int let, SortedSet<String> words, List<Integer> path) {
            if (prefix.length() >= minlen && isValidWord(prefix) && !words.contains(prefix)) {
               words.add(prefix);
            }
            for (int[] direction: Directions) {
               int r = let / board.length;
               int c = let % board.length;
               int adjr = r + direction[0];
               int adjc = c + direction[1];
               if (adjr >= 0 && adjr < board.length && adjc >= 0 && adjc < board.length
                       && !path.contains(adjr * board.length + adjc)
                       && isValidPrefix(prefix + board[adjr][adjc])) {
                  ArrayList<Integer> npath = new ArrayList<>(path);
                  npath.add(let);
                  words = dfsb(minlen, prefix + board[adjr][adjc],
                          adjr * board.length + adjc, words, npath);
               }
            }
            return words;
         }
         
         public int getScoreForWords(SortedSet<String> words, int minimumWordLength) {
            return 0;
         }
         
         public boolean isValidPrefix(String prefixToCheck) {
            if (lexicon == null) {
               throw new IllegalStateException();
            }
            if (prefixToCheck == null) {
               throw new IllegalArgumentException();
            }
            if (lexicon.ceiling(prefixToCheck) == null) {
               return false;
            }
            return lexicon.ceiling(prefixToCheck).startsWith(prefixToCheck);
          
         }
         
         public boolean isValidWord(String wordToCheck) {
            if (lexicon == null) {
               throw new IllegalStateException();
            }
            if (wordToCheck == null) {
               throw new IllegalArgumentException();
            }
            return lexicon.contains(wordToCheck);
         }
         
         private List<Integer> dfs(int let, String prefix, String target, List<Integer> parents) {
            parents.add(let);
            prefix += board[let / board.length][let % board.length];
            if (prefix.equals(target)) {
               return parents;
            }
            for (int[] direction: Directions) {
               int r = let / board.length;
               int c = let % board.length;
               int adjr = r + direction[0];
               int adjc = c + direction[1];
               if (adjr >= 0 && adjr < board.length && adjc >= 0 && adjc < board.length
                       && target.startsWith(prefix + board[adjr][adjc])
                       && !parents.contains(adjr * board.length + adjc)) {
                  int x = adjr * board.length + adjc;
                  return dfs(x, prefix, target, parents);
               }
            }
            return new ArrayList<Integer>();
         }
         
         public List<Integer> isOnBoard(String wordToCheck) {
            String prefix = "";
            for (int r = 0; r < board.length; r++) {
               for (int c = 0; c < board.length; c++) {
                  List<Integer> list = dfs(r * board.length + c, "", wordToCheck, new ArrayList<>());
                  if (!list.isEmpty()) {
                     return list;
                  }
               }
            }
            return new ArrayList<Integer>();
         }
            
            
      }
      
      
      return new Game();
      
     
      
      
   }

}
