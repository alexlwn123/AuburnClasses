import org.junit.Assert;
import org.junit.Test;

public class FinderTest {
   @Test
   public void findMax_empty() {
      int[] arr = new int[0];
     Assert.assertNull(Finder.findMax(arr));
   }
   @Test
   public void findMax_null() {
      int[] arr = new int[0];
      Assert.assertNull(Finder.findMax(arr));
   }
   @Test
   public void findMin_empty() {
      int[] arr = new int[0];
      Assert.assertNull(Finder.findMax(arr));
   }
   @Test
   public void findMin_null() {
      int[] arr = new int[0];
      Assert.assertNull(Finder.findMax(arr));
   }
   @Test
   public void findMax_valid() {
      int[] arr = {1,2,3,4,5,6};
      Assert.assertTrue(Finder.findMax(arr) == 6);
   }
   @Test
   public void findMin_valid() {
      int[] arr = {1,2,3,4,5,6};
      Assert.assertTrue(Finder.findMin(arr) == 1);
   }
 
}
