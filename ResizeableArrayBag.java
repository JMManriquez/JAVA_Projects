import java.util.Arrays;

public class ResizeableArrayBag<T> implements BagInterface<T>
{
   private T[] bag;
   private static final int DEFAULT_CAPACITY = 25;
   private int numberOfEntries;
   //These 2 data fields below are not necessary but are important in practice
   private boolean integrityOK = false;
   private static final int MAX_CAPACITY = 10000;

   public ResizeableArrayBag()
   {
      this(DEFAULT_CAPACITY);
   }

   public ResizeableArrayBag(int capacity)
   {
      if(capacity <= MAX_CAPACITY)
      {
         numberOfEntries = 0;
         //The type cast is safe because the new array contains null entries
         @SuppressWarnings("unchecked")
         T[] tempBag = (T[])new Object[capacity]; //unchecked cast
         bag = tempBag;
         integrityOK = true;
      }
      else
        throw new IllegalStateException("Attempt to create a bag whose "
                                      + "capacity exceeds allowed maximum.");
   }
   
   private void checkIntegrity()
   {
      if(!integrityOK)
         throw new SecurityException("ArrayBagFixed object is corrupt.");
   }

   public boolean add(T newEntry)
   {
      checkIntegrity();
 
      if(isFull())
         doubleCapacity();

      bag[numberOfEntries] = newEntry;
      numberOfEntries++;
      return true;
   }

   //Throws an exception if the client requests a capacity that is too large
   private void checkCapacity(int capacity)
   {
      if(capacity > MAX_CAPACITY)
         throw new IllegalStateException("Attempt to create a bag whose "
                                        + "capcity exceeds allows "
                                        + "maximum of " + MAX_CAPACITY);
   }
   
   //Doubles the size of the array bag
   //Precondition: checkIntegrity has been called
   private void doubleCapacity()
   {
      int newLength = 2 * bag.length;
      checkCapacity(newLength);
      bag = Arrays.copyOf(bag, newLength);
   }

   public boolean isFull()
   {
      return numberOfEntries == bag.length;
   }

   public T[] toArray()
   {
      //the cast is safe because the new array contains null entries
      @SuppressWarnings("unchecked")
      T[] result = (T[]) new Object[numberOfEntries];
      for(int index = 0; index < numberOfEntries; index++)
      {
         result[index] = bag[index];
      }

      return result;
   }

   public boolean isEmpty()
   {
      return numberOfEntries == 0; //True if bag is empty
   }

   public int getCurrentSize()
   {
      return numberOfEntries;
   }

   public int getFrequencyOf(T anEntry)
   {
      checkIntegrity();
      int counter = 0;
      
      for(int index = 0; index < numberOfEntries; index++)
      {
         if(anEntry.equals(bag[index]))
            counter++;
      }
      return counter;
   }

   public boolean remove(T anEntry)
   {
      checkIntegrity();
      int index = getIndexOf(anEntry);
      T result = removeEntry(index);
      return anEntry.equals(result);
   }

   private int getIndexOf(T anEntry)
   {
      int where = -1;
      boolean found = false;
      int index = 0;
      
      while(!found && (index < numberOfEntries))
      {
         if(anEntry.equals(bag[index]))
         {
            found = true;
            where = index;
         }
      } //If where > -1, anEntry is in the array bag, and it
        //equals bag[where]; otherwise, anEntry is not in the array
      return where;
   } 

   private T removeEntry(int givenIndex)
   {
      T result = null;

      if(!isEmpty() && (givenIndex >=0))
      {
         result = bag[givenIndex]; //Entry to remove
         bag[givenIndex] = bag[numberOfEntries - 1]; //Replace entry with last entry
         bag[numberOfEntries - 1] = null; //Remove last entry
         numberOfEntries--;
      }
      
      return result;
   }

   public T remove()
   {
      checkIntegrity();
      T result = removeEntry(numberOfEntries - 1);
      return result;
   }

   public void clear()
   {
      while(!isEmpty())
         remove();
   }

   public boolean contains(T anEntry)
   {
      checkIntegrity();
      return getIndexOf(anEntry) > -1;
   }

   public BagInterface<T> union(BagInterface<T> bag2)
   {
      //Create a copy of the contents of bag2
      T[] bag2Content = bag2.toArray();
      
      //Create a new bag with the capacity of calling bag and parameter bag
      BagInterface<T> result = new ResizeableArrayBag<T>(this.numberOfEntries + bag2.getCurrentSize());

      for(int i = 0; i < (this.numberOfEntries + bag2.getCurrentSize()); i++)
      {
         if(i < this.numberOfEntries)
         {
            result.add(this.bag[i]); //Add contents from the first bag
         }
         else
         {
            result.add((T) bag2Content[i-bag.length]); //Add contents from the second bag
         }
      }
      return result;
   }

   public BagInterface<T> intersection(BagInterface<T> bag2)
   {
      //Create Array Copy of the contents of bag2
      T[] bag2Content = bag2.toArray();
      int count = 0; //iteration variable to count how many intersections we have

      //Create a new bag with the capacity of calling bag and parameter bag in case all intersect
      BagInterface<T> result = new ResizeableArrayBag<T>(numberOfEntries + bag2Content.length);

      for(int i = 0; i < numberOfEntries; i++)
      {
         for(int j = 0 + count; j < bag2Content.length; j++)
         {
            if(bag[i].equals((T) bag2Content[j]))
            {
               result.add((T) bag2Content[j]);
               T temp = bag2Content[j];
               bag2Content[j] = bag2Content[0+count];
               bag2Content[0+count] = temp;
               //Any duplicates are moved to the beginning of the arry and the inner for
               //loop counts past any positions that has duplicates
               count++;
               break; //break ensures we only traverse the amount of positions needed
            }
         }   
      }
      return result;
   }

   public BagInterface<T> difference(BagInterface<T> bag2)
   {
      //Create Array Copy of the contents of bag2
      T[] bag2Content = bag2.toArray();
      int count = 0; //iteration variable to count how many intersections we have
      boolean different; //Flag that tells us if the values are different

      //Create a new bag with the capacity of calling bag and parameter bag in case all intersect
      BagInterface<T> result = new ResizeableArrayBag<T>(numberOfEntries + bag2Content.length);

      for(int i = 0; i < numberOfEntries; i++)
      {
         different = true;
         for(int j = 0 + count; j < bag2Content.length; j++)
         {
            if(bag[i].equals((T) bag2Content[j]))
            {
               different = false;
               T temp = bag2Content[j];
               bag2Content[j] = bag2Content[0+count];
               bag2Content[0+count] = temp;
               //Any duplicates are moved to the beginning of the arry and the inner for
               //loop counts past any positions that has duplicates
               count++;
               break; //break ensures we only traverse the amount of positions needed
            }
         }
         if(different)
            result.add(bag[i]);   
      }
      return result;
   }

   public void printToString()
   {
      for(int i = 0; i < numberOfEntries; i++)
         System.out.print(bag[i] + " ");
      System.out.println();
   }
}