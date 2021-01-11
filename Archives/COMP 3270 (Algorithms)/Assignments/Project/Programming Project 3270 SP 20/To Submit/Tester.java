import java.util.Arrays;
import java.util.Iterator;

public class Tester {
   
   public static void main(String[] args) {
      String[] words = {"ape", "app", "ban", "bat", "bee", "car", "cat"};
      double[] weights = {6, 4, 2, 3, 5, 7, 1};
   
      Autocomplete.TrieAutocomplete func = new Autocomplete.TrieAutocomplete(words, weights);
   
      ////TOPMATCH SECTION
      System.out.println("topMatch Tests \n----------------");
   
      String[] q1 = {"", "a", "app", "ap", "b", "ba", "c","app", "ca", "cat", "d", " "};
      String[] out1 = {"car", "ape", "app", "ape", "bee", "bat", "car", "app", "car", "cat", "", ""};
      int pass = 0, fail = 0;
   
      for (int i = 0; i < q1.length; i++) {
         System.out.print("Case #" + (i+1) + ": ");
         
      
         if (func.topMatch(q1[i]).equals(out1[i])) {
            pass++;
            System.out.println("Pass");
         }
         else {
            fail++;
            System.out.println("Fail");
         }
      }
      System.out.println("\nPassed Cases: " + pass + "/" + (fail+pass));
      //Passed 10/10
   
      ////TOPMATCHES SECTION
      System.out.println("\ntopMatches Tests: \n---------------------");
   
      String[] q2 = {"", "", "", "", "a", "ap", "b", "ba", "d"};
      int[] k = {8, 1, 2, 3, 1, 1, 2, 2, 100};
      String[][] out2 = {
         {"car", "ape", "bee", "app", "bat", "ban", "cat"},
         {"car"},
         {"car", "ape"},
         {"car", "ape", "bee"},
         {"ape"},
         {"ape"},
         {"bee", "bat"},
         {"bat", "ban"},
         {}
      };
      pass = 0; fail = 0;
   
      for (int i = 0; i < q2.length; i++) {
         System.out.print("Case #" + (i+1) + ": ");
         try {
            Iterator<String> in = func.topMatches(q2[i], k[i]).iterator();
            Iterator<String> out = Arrays.asList(out2[i]).iterator();
            while (in.hasNext()) {
               if (!out.hasNext() || !in.next().equals(out.next())) {
                  throw new Error();
               }
            }
            System.out.println("Pass");
            pass++;
         }
         catch(Error e) {
            System.out.println("Fail");
            fail++;
         }
   
      }
         
      System.out.println("\nPassed Cases: " + pass + "/" + (pass+fail));
      //Passed 12/12
   
   }
}
