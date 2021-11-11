public class LinkedBag<T> implements BagInterface<T>
{
   private Node firstNode;
   private int numberOfEntries;

   public LinkedBag()
   {
      firstNode = null;
      numberOfEntries = 0;
   }

   public boolean add(T newEntry)
   {
      //add to the beginning of chain:
      Node newNode = new Node(newEntry);
      newNode.next = firstNode;

      firstNode = newNode;
      numberOfEntries++;
      return true;
   }

   public T remove()
   {
      T result = null;
      if(firstNode != null)
      {
         result = firstNode.getData();
         //firstNode.getData() = null; security line
         firstNode = firstNode.getNextNode();
         numberOfEntries--;
      }
      return result;
   }

   public boolean remove(T anEntry)
   {
      boolean result = false;
      Node nodeUpdate = getReferenceTo(anEntry);

      if(nodeUpdate != null)
      {
         //Replace located entry with entry in first node
         nodeUpdate.setData(firstNode.getData());
         //Remove first node
         firstNode = firstNode.getNextNode();

         numberOfEntries--;
         result = true;
      }
      return result;
   }

   //Locates a given entry within this bag
   //Returns a reference to the node containing the entry
   //If located or null otherwise
   private Node getReferenceTo(T anEntry)
   {
      boolean found = false;
      Node currentNode = firstNode;

      while(!found && (currentNode != null))
      {
         if(anEntry.equals(currentNode.getData()))
            found = true;
         else
            currentNode = currentNode.getNextNode();
      }
      return currentNode;
   }

   public boolean isEmpty()
   {
      return numberOfEntries == 0;
   }

   public int getCurrentSize()
   {
      return numberOfEntries;
   }

   public void clear()
   {
      firstNode = null; //reset the chain from the beginning
      //unlinking the firstNode destroys the rest of the chain
      numberOfEntries = 0; //This resets the count in the linked chain as well.
   }

   public int getFrequencyOf(T anEntry)
   {
      int frequency = 0, counter = 0;
      Node currentNode = firstNode;
 
      while((counter < numberOfEntries) && (currentNode != null))
      {
         if(anEntry.equals(currentNode.getData()))
            frequency++;
         counter++;
         currentNode = currentNode.getNextNode();
      }
      return frequency; 
   }

   public boolean contains(T anEntry)
   {
      boolean found = false;
      Node currentNode = firstNode;

      while(!found && (currentNode != null))
      {
         if(anEntry.equals(currentNode.getData()))
            found = true;
         else
            currentNode = currentNode.getNextNode();
      }
      return found;
   }

   public T[] toArray()
   {
      //The cast is safe because the new array countains null entries
      @SuppressWarnings("unchecked")
      T[] result = (T[]) new Object[numberOfEntries];

      int index = 0;
      Node currentNode = firstNode;
      while((index < numberOfEntries) && (currentNode != null))
      {
         result[index] = currentNode.getData();
         index++;
         currentNode = currentNode.getNextNode();
      }
      return result;
   }

   public BagInterface<T> union(BagInterface<T> bag2)
   { 
      //Create a copy of the contents of bag2
      T[] bag2Content = bag2.toArray();

      //Create a new bag with the capacity of calling bag and parameter bag
      BagInterface<T> result = new LinkedBag<T>();

      int index = 0;
      Node currentNode = this.firstNode;
      while((index < this.numberOfEntries) && (currentNode != null))
      {
         result.add(currentNode.getData());
         index++;
         currentNode = currentNode.getNextNode();
      } 
      for(int i = 0; i < bag2Content.length; i++)
         result.add((T) bag2Content[i]);

      return result;
   }

   public BagInterface<T> intersection(BagInterface<T> bag2)
   {
      //Create Array Copy of the contents of bag2
      T[] bag2Content = bag2.toArray();
      int count = 0; //iteration variable to count how many intersections we have

      //Create a new bag with the capacity of calling bag and parameter bag
      BagInterface<T> result = new LinkedBag<T>();
      
      int index = 0;
      Node currentNode = this.firstNode;
      while((index < this.numberOfEntries) && (currentNode != null))
      {
         for(int j = 0 + count; j < bag2Content.length; j++)
         {
            if(currentNode.getData().equals((T) bag2Content[j]))
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
         index++;
         currentNode = currentNode.getNextNode();
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
      BagInterface<T> result = new LinkedBag<T>();

      int index = 0;
      Node currentNode = this.firstNode;
      while((index < this.numberOfEntries) && (currentNode != null))
      {
         different = true;
         for(int j = 0 + count; j < bag2Content.length; j++)
         {
            if(currentNode.getData().equals((T) bag2Content[j]))
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
            result.add(currentNode.getData());
         index++;
         currentNode = currentNode.getNextNode();
      }
      return result;
   }

   public void printToString()
   {
      int index = 0;
      Node currentNode = this.firstNode;
      while((index < this.numberOfEntries) && (currentNode != null))
      {
         System.out.print(((String) currentNode.getData()) + " ");
         index++;
         currentNode = currentNode.getNextNode();
      }
      System.out.println();
   }

   private class Node
   {
      private T data; 
      private Node next;

      private Node(T dataPortion)
      {
         this(dataPortion, null);
      }

      private Node(T dataPortion, Node nextNode)
      {
         data = dataPortion;
         next = nextNode;
      }

      public T getData()
      {
         return data;
      }

      public void setData(T newData)
      {
         data = newData;
      }

      public Node getNextNode()
      {
         return next;
      }

      public void setNextNode(Node nextNode)
      {
         next = nextNode;
      }
   }
}