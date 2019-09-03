import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Provides an implementation of the Set interface.
 * A doubly-linked list is used as the underlying data structure.
 * Although not required by the interface, this linked list is
 * maintained in ascending natural order. In those methods that
 * take a LinkedSet as a parameter, this order is used to increase
 * efficiency.
 *
 * @author Dean Hendrix (dh@auburn.edu)
 * @version 2018-10-04
 */
public class LinkedSet<T extends Comparable<? super T>> implements Set<T> {
   
   //////////////////////////////////////////////////////////
   // Do not change the following three fields in any way. //
   //////////////////////////////////////////////////////////
   
   /**
    * References to the first and last node of the list.
    */
   Node front;
   
   Node rear;
   
   /**
    * The number of nodes in the list.
    */
   int size;
   
   /////////////////////////////////////////////////////////
   // Do not change the following constructor in any way. //
   /////////////////////////////////////////////////////////
   
   /**
    * Instantiates an empty LinkedSet.
    */
   public LinkedSet() {
      front = null;
      rear = null;
      size = 0;
   }
   
   //////////////////////////////////////////////////
   // Public interface and class-specific methods. //
   //////////////////////////////////////////////////
   
   ///////////////////////////////////////
   // DO NOT CHANGE THE TOSTRING METHOD //
   ///////////////////////////////////////
   
   /**
    * Return a string representation of this LinkedSet.
    *
    * @return a string representation of this LinkedSet
    */
   @Override
   public String toString() {
      if (isEmpty()) {
         return "[]";
      }
      StringBuilder result = new StringBuilder();
      result.append("[");
      for (T element : this) {
         //System.out.println("print: " + element);
         result.append(element + ", ");
      }
      
      result.delete(result.length() - 2, result.length());
      result.append("]");
      return result.toString();
   }
   
   ///////////////////////////////////
   // DO NOT CHANGE THE SIZE METHOD //
   ///////////////////////////////////
   
   /**
    * Returns the current size of this collection.
    *
    * @return the number of elements in this collection.
    */
   public int size() {
      return size;
   }
   
   //////////////////////////////////////
   // DO NOT CHANGE THE ISEMPTY METHOD //
   //////////////////////////////////////
   
   /**
    * Tests to see if this collection is empty.
    *
    * @return true if this collection contains no elements, false otherwise.
    */
   public boolean isEmpty() {
      return (size == 0);
   }
   
   
   /**
    * Ensures the collection contains the specified element. Neither duplicate
    * nor null values are allowed. This method ensures that the elements in the
    * linked list are maintained in ascending natural order.
    *
    * @param element The element whose presence is to be ensured.
    * @return true if collection is changed, false otherwise.
    */
   public boolean add(T element) {
     
      Node newNode = new Node(element);
      if (size == 0) {
         front = newNode;
         rear = newNode;
         size++;
         return true;
      }
      
      Node temp = front;
      
      if (element.compareTo(front.element) < 0) {
         front = newNode;
         temp.prev = newNode;
         newNode.next = temp;
         size++;
         return true;
      }
   
      //System.out.println("tempbefore: " + temp.element);
      
      while (temp != rear && element.compareTo(temp.element) > 0) {
         temp = temp.next;
      }
      //System.out.println("tempafter: " + temp.element);
      
      
      if (element.compareTo(temp.element) == 0) {
         return false;
      }
      
      if (temp == rear) {
         if (element.compareTo(temp.element) < 0) {
            newNode.next = temp;
            temp.prev.next = newNode;
            newNode.prev = temp.prev;
            temp.prev = newNode;
            
            size++;
            return true;
            
         }
         temp.next = newNode;
         newNode.prev = temp;
         rear = newNode;
         size++;
   
         
      }
      
      else {  //insert node
         newNode.next = temp;
         newNode.prev = temp.prev;
         temp.prev.next = newNode;
         temp.prev = newNode;
         
         size++;
      }
      
      return true;
   }
   
   /**
    * Ensures the collection does not contain the specified element.
    * If the specified element is present, this method removes it
    * from the collection. This method, consistent with add, ensures
    * that the elements in the linked lists are maintained in ascending
    * natural order.
    *
    * @param element The element to be removed.
    * @return true if collection is changed, false otherwise.
    */
   public boolean remove(T element) {
      if (this.size == 0) {
         return false;
      }
      if (this.size == 1) {
         if (front.element.compareTo(element) == 0) {
            front = null;
            rear = null;
            size--;
            return true;
         }
         else {
            return false;
         }
      }
      Node temp = front;
      if (!this.contains(element)) {
         return false;
      }
      while (element.compareTo(temp.element) > 0 && temp != rear) {
         temp = temp.next;
      }
      if (temp == rear && element.compareTo(rear.element) >= 0) {
         if (element.compareTo(rear.element) != 0) { //DNE
            return false;
         }
         rear = rear.prev; //element is rear
         rear.next = null;
         size--;
         return true;
      }
      if (element.compareTo(temp.element) == 0) { //found
         if (temp == front) {
            front = temp.next;
            temp.next.prev = null;
            size--;
            return true;
         }
         temp.prev.next = temp.next;
         temp.next.prev = temp.prev;
         size--;
         return true;
      }
      //DNE
      return false;
   }
   
   
   /**
    * Searches for specified element in this collection.
    *
    * @param element The element whose presence in this collection is to be tested.
    * @return true if this collection contains the specified element, false otherwise.
    */
   public boolean contains(T element) {
      if (size == 0) {
         return false;
      }
      Node temp = front;
      while (temp != rear && temp.element.compareTo(element) < 0) {
         temp = temp.next;
      }
      
      return temp.element.compareTo(element) == 0;
      
   }
   
   
   /**
    * Tests for equality between this set and the parameter set.
    * Returns true if this set contains exactly the same elements
    * as the parameter set, regardless of order.
    *
    * @return true if this set contains exactly the same elements as
    *     the parameter set, false otherwise
    */
   public boolean equals(Set<T> s) {
      if (s.size() != this.size()) {
         //System.out.println(this.size + " " + s.size());
         return false;
      }

      //System.out.println(this + " " + s);
      for (T elem: this) {
         if (!s.contains(elem)) {
            return false;
         }
      }
      return true;
      
   }
      
     
   
   
   /**
    * Tests for equality between this set and the parameter set.
    * Returns true if this set contains exactly the same elements
    * as the parameter set, regardless of order.
    *
    * @return true if this set contains exactly the same elements as
    *     the parameter set, false otherwise
    */
   public boolean equals(LinkedSet<T> s) {
      if (this.size != s.size) {
         return false;
      }
      //System.out.println(this + " " + s);
      for (T elem: this) {
         if (!s.contains(elem)) {
            return false;
         }
      }
      return true;
   
   }
   
   
   /**
    * Returns a set that is the union of this set and the parameter set.
    *
    * @return a set that contains all the elements of this set and the parameter set
    */
   public Set<T> union(Set<T> s) {
      Set<T> out = new LinkedSet<T>();
      for (T elem : s) {
         out.add(elem);
      }
      for (T elem : this) {
         out.add(elem);
      }
      return out;
   }
   
   
   /**
    * Returns a set that is the union of this set and the parameter set.
    *
    * @return a set that contains all the elements of this set and the parameter set
    */
   public Set<T> union(LinkedSet<T> s) {
      Set<T> out = new LinkedSet<T>();
      
      for (T elem : s) {
         out.add(elem);
      }
      for (T elem : this) {
         out.add(elem);
      }
      
      return out;
   }
   
   
   /**
    * Returns a set that is the intersection of this set and the parameter set.
    *
    * @return a set that contains elements that are in both this set and the parameter set
    */
   public Set<T> intersection(Set<T> s) {
      if (this.size == 0) {
         return this;
      }
      Set<T> out = new LinkedSet<T>();
      for (T elem : s) {
         if (this.contains(elem)) {
            out.add(elem);
         }
      }
      return out;
      
   }
   
   /**
    * Returns a set that is the intersection of this set and
    * the parameter set.
    *
    * @return a set that contains elements that are in both
    *     this set and the parameter set
    */
   public Set<T> intersection(LinkedSet<T> s) {
      if (this.size == 0) {
         return this;
      }
      Set<T> out = new LinkedSet<T>();
      
      for (T elem : s) {
         if (this.contains(elem)) {
            out.add(elem);
         }
      }
      return out;
   }
   
   
   /**
    * Returns a set that is the complement of this set and the parameter set.
    *
    * @return a set that contains elements that are in this set but not the parameter set
    */
   public Set<T> complement(Set<T> s) {
      if (this.size == 0) {
         return this;
      }
      if (s.size() == 0) {
         return this;
      }
      Set<T> out = new LinkedSet<T>();
      for (T elem : this) {
         if (!s.contains(elem)) {
            out.add(elem);
         }
      }
      return out;
   }
   
   
   /**
    * Returns a set that is the complement of this set and
    * the parameter set.
    *
    * @return a set that contains elements that are in this
    *     set but not the parameter set
    */
   public Set<T> complement(LinkedSet<T> s) {
      if (this.size == 0) {
         return this;
      }
      if (s.size == 0) {
         return this;
      }
      Set<T> out = new LinkedSet<T>();
      
      for (T elem : this) {
         if (!s.contains(elem)) {
            out.add(elem);
         }
      }
      return out;
   }
   
   
   /**
    * Returns an iterator over the elements in this LinkedSet.
    * Elements are returned in ascending natural order.
    *
    * @return an iterator over the elements in this LinkedSet
    */
   public Iterator<T> iterator() {
      
      Iterator<T> iter = new Iterator<T>() {
         private Node current = front;
         @Override
         public T next() {
            if (current == null) {
               return null;
            }
            T out = current.element;
            current = current.next;
           
            return out;
         }
         
         @Override
         public boolean hasNext() {
            return current != null;
         }
      };
      return iter;
   }
   
   
   /**
    * Returns an iterator over the elements in this LinkedSet.
    * Elements are returned in descending natural order.
    *
    * @return an iterator over the elements in this LinkedSet
    */
   public Iterator<T> descendingIterator() {
      
      return new Iterator<T>() {
         
         private Node temp = rear;
         
         @Override
         public T next() {
            if (temp == null) {
               return null;
            }
            T out = temp.element;
            temp = temp.prev;
            return out;
         }
         
         @Override
         public boolean hasNext() {
            return temp != null;
         }
      };
   }
   
   
   /**
    * Returns an iterator over the members of the power set
    * of this LinkedSet. No specific order can be assumed.
    *
    * @return an iterator over members of the power set
    */
   public Iterator<Set<T>> powerSetIterator() {
      int powerSize = (int) Math.pow(2, this.size);
      
      
      Iterator<Set<T>> iter = new Iterator<Set<T>>() {
         
         private int currentSet = 0;
         
         @Override
         public boolean hasNext() {
            return currentSet < powerSize;
         }
         
         @Override
         public Set<T> next() {
            Iterator<T> desc = LinkedSet.this.descendingIterator();
            Set<T> out = new LinkedSet<T>();
            String thisSet = Integer.toBinaryString(currentSet);
            //System.out.println(thisSet);
            for (int i = thisSet.length() - 1; i >= 0; i--) {
               if (thisSet.charAt(i) == '1') {
                  out.add(desc.next());
               }
               else {
                  desc.next();
               }
            }
            currentSet++;
            return out;
         }
      };
      return iter;
      
   }
   
   //////////////////////////////
   // Private utility methods. //
   //////////////////////////////
   
   // Feel free to add as many private methods as you need.
   
   ////////////////////
   // Nested classes //
   ////////////////////
   
   //////////////////////////////////////////////
   // DO NOT CHANGE THE NODE CLASS IN ANY WAY. //
   //////////////////////////////////////////////
   
   /**
    * Defines a node class for a doubly-linked list.
    */
   class Node {
      
      /**
       * the value stored in this node.
       */
      T element;
      
      /**
       * a reference to the node after this node.
       */
      Node next;
      
      /**
       * a reference to the node before this node.
       */
      Node prev;
      
      /**
       * Instantiate an empty node.
       */
      public Node() {
         element = null;
         next = null;
         prev = null;
      }
      
      /**
       * Instantiate a node that containts element
       * and with no node before or after it.
       */
      public Node(T e) {
         element = e;
         next = null;
         prev = null;
      }
   }
   
}


