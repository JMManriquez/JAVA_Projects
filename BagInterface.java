public interface BagInterface<T>
{
   //Gets the current number of entries in this bag
   //Returns the integer number of entries
   public int getCurrentSize();

   //Sees whether this bag is empty
   //Returns True if the bag is empty, or false if not
   public boolean isEmpty();

   //Adds a new entry to this bag
   //Parameters: newEntry, the object to be added as a new entry
   //Returns True if the addition is successful, or false if not
   public boolean add(T newEntry);

   //Removes one unspecified entry from this bag, if possible.
   //Returns either the removed entry, if the removal was successful
   //or returns null
   public T remove();

   //removes one occurrence of a given entry from this abg, if possible
   //Parameters: anEntry, the entry to be removed
   //Returns: If the removal was successful, or false if not
   public boolean remove(T anEntry);

   //Removes all entries from this bag
   public void clear();

   //Counts the number of times a given entry appears in this bag
   //Parameters: anEntry, the entry to be counted
   //Returns: The number of times anEntry appears in the bag
   public int getFrequencyOf(T anEntry);

   //Tests whether this bag ocntains a given entry
   //Parameters: anEntry, the entry to find
   //Returns: True if the bag contains anEntry, or faalse if not
   public boolean contains(T anEntry);

   //Retrieves all entries that are in this bag and places into a public array
   //Returns: a newly allocated array of all entries in the bag. Array is empty if bag is empty
   public T[] toArray();

   //Returns a reference to a bag that has had the contents of the calling bag be united with
   //The contents of bag 2
   //Parameters: It takes the bag reference of bag2 which will be united to the first bag
   // We are utilizing BagInterface to utilize Polymorphism for ArrayBagResize and LinkedBag
   public BagInterface<T> union(BagInterface<T> bag2);

   //Returns common items between the calling bag and parameter bag
   //Parameters bag reference from BagInterface utilizing polymorphism
   public BagInterface<T> intersection(BagInterface<T> bag2);

   //Returns the items from the calling bag and removes common items from bag2 which is part of
   //the parameters
   public BagInterface<T> difference(BagInterface<T> bag2);

   //Prints the contents of the bag without needing to place it inside a System.out.print and without 
   //calling the toArray() method
   public void printToString();
}