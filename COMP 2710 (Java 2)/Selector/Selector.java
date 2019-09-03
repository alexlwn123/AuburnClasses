import java.util.Arrays;

/**
* Defines a library of selection methods
* on arrays of ints.
*
* @author   Alex Lewin asl0028@tigermail.auburn.edu
* @author   Dean Hendrix (dh@auburn.edu)
* @version  8/22/18
*
*/
public final class Selector {

   /**
    * Can't instantiate this class.
    *
    * D O   N O T   C H A N G E   T H I S   C O N S T R U C T O R
    *
    */
   private Selector() { }


   /**
    * Selects the minimum value from the array a. This method
    * throws IllegalArgumentException if a is null or has zero
    * length. The array a is not changed by this method.
    * @param a int array
    * @return min value
    *
    */
   public static int min(int[] a) {
      try {
         return Arrays.stream(a).min().getAsInt();
      } catch (java.util.NoSuchElementException | ArrayIndexOutOfBoundsException | NullPointerException e) {
         throw new IllegalArgumentException("");
      }
   }


   /**
    * Selects the maximum value from the array a. This method
    * throws IllegalArgumentException if a is null or has zero
    * length. The array a is not changed by this method.
    * @param a int array
    * @return max value
    */
   public static int max(int[] a) {
      try {
         return Arrays.stream(a).max().getAsInt();
      } catch (java.util.NoSuchElementException | ArrayIndexOutOfBoundsException | NullPointerException e) {
         throw new IllegalArgumentException("");
      }
   }


   /**
    * Selects the kth minimum value from the array a. This method
    * throws IllegalArgumentException if a is null, has zero length,
    * or if there is no kth minimum value. Note that there is no kth
    * minimum value if k < 1, k > a.length, or if k is larger than
    * the number of distinct values in the array. The array a is not
    * changed by this method.
    * @param a int array
    * @param k nth min value
    * @return min value
    */
   public static int kmin(int[] a, int k) {
      try {
         int[] x = Arrays.stream(a).sorted().distinct().toArray();
         return x[k - 1];
      } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
         throw new IllegalArgumentException("");
      }
   }


   /**
    * Selects the kth maximum value from the array a. This method
    * throws IllegalArgumentException if a is null, has zero length,
    * or if there is no kth maximum value. Note that there is no kth
    * maximum value if k < 1, k > a.length, or if k is larger than
    * the number of distinct values in the array. The array a is not
    * changed by this method.
    * @param a int array
    * @param k nth max value
    * @return max value
    */
   public static int kmax(int[] a, int k) {
      try {
         if (k < 1 || k > a.length) {
            throw new IllegalArgumentException("");
         }
         int[] x = Arrays.stream(a).sorted().distinct().toArray();
         return x[x.length - k];
      } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
         throw new IllegalArgumentException("");
      }
   }


   /**
    * Returns an array containing all the values in a in the
    * range [low..high]; that is, all the values that are greater
    * than or equal to low and less than or equal to high,
    * including duplicate values. The length of the returned array
    * is the same as the number of values in the range [low..high].
    * If there are no qualifying values, this method returns a
    * zero-length array. Note that low and high do not have
    * to be actual values in a. This method throws an
    * IllegalArgumentException if a is null or has zero length.
    * The array a is not changed by this method.
    * @param a int array
    * @param low low bounds
    * @return int array
    */
   public static int[] range(int[] a, int low, int high) {
      try {
         if (a.length == 0) {
            throw new IllegalArgumentException("");
         }
         int count = 0;
         for (int x: a) {
            if (x <= high && x >= low) {
               count++;
            }
         }
      
         int[] out = new int[count];
         
         for (int i = 0, j = 0; i < a.length; i++) {
            if (a[i] <= high && a[i] >= low) {
               out[j] = a[i];
               j++;
            }
         }
         return out;
      } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
         throw new IllegalArgumentException("");
      }
      
      
   }


   /**
    * Returns the smallest value in a that is greater than or equal to
    * the given key. This method throws an IllegalArgumentException if
    * a is null or has zero length, or if there is no qualifying
    * value. Note that key does not have to be an actual value in a.
    * The array a is not changed by this method.
    * @param a int array
    * @param key ceiling
    * @return smallest value
    */
   public static int ceiling(int[] a, int key) {
      try {
         return Arrays.stream(a).filter(x -> x >= key).min().getAsInt();
      } catch (java.util.NoSuchElementException | ArrayIndexOutOfBoundsException | NullPointerException e) {
         throw new IllegalArgumentException("");
      }
   }


   /**
    * Returns the largest value in a that is less than or equal to
    * the given key. This method throws an IllegalArgumentException if
    * a is null or has zero length, or if there is no qualifying
    * value. Note that key does not have to be an actual value in a.
    * The array a is not changed by this method.
    * @param a int array
    * @param key floor
    * @return max
    */
   public static int floor(int[] a, int key) {
      try {
         return Arrays.stream(a).filter(x -> x <= key).max().getAsInt();
      } catch (java.util.NoSuchElementException | ArrayIndexOutOfBoundsException | NullPointerException e) {
         throw new IllegalArgumentException("");
      }   
   }

}
