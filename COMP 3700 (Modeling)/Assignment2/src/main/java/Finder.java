import java.util.stream.*;
import java.util.Arrays;
public class Finder {
   
   public static void main(String[] args) {
      int[] x = {};
      System.out.println(findMax(null));
      System.out.println(findMin(null));
   }
   public static Integer findMax(int[] intArray) {
      if (intArray == null || intArray.length == 0)
         return null;
      return Arrays.stream(intArray).max().getAsInt();
   }
   
   public static Integer findMin(int[] intArray) {
      if (intArray == null || intArray.length == 0)
         return null;
      return Arrays.stream(intArray).min().getAsInt();
   }
}
