import java.util.*;

public final class ArrayQueue<T> implements QueueInterface<T>
{
   //Circular array of queue entries and one unused location
   private T[] queue;
   private int frontIndex; //Index of front entry
   private int backIndex; //Index of back entry
   private boolean integrityOK;
   //true if data structure is created correctly, false if not
   private static final int DEFAULT_CAPACITY = 3;
   private static final int MAX_CAPACITY = 1000;

   public ArrayQueue()
   {
      this(DEFAULT_CAPACITY);
   } 

   public ArrayQueue(int initialCapacity)
   {
      integrityOK = false;
      checkCapacity(initialCapacity);
      
      @SuppressWarnings("unchecked")
      T[] tempQueue = (T[]) new Object[initialCapacity + 1];
      queue = tempQueue;
      frontIndex = 0;
      backIndex = initialCapacity;
      integrityOK = true;
   }

   //Throws an exception if the client requests a capacity that is too large
   private void checkCapacity(int capacity)
   {
      if(capacity > MAX_CAPACITY)
         throw new IllegalStateException("Attempt to create a queue whose "
                                        + "capcity exceeds allows "
                                        + "maximum of " + MAX_CAPACITY);
   }

   public void enqueue(T newEntry)
   {
      checkIntegrity();
      ensureCapacity();
      backIndex = (backIndex + 1) % queue.length;
      queue[backIndex] = newEntry;
   }

   private void checkIntegrity()
   {
      if(!integrityOK)
         throw new SecurityException("ArrayQueue object is corrupt.");
   }
   
   //Doubles the size of the array queue if it is full.
   //Precondition: checkIntegrity has been called
   private void ensureCapacity() //If array full, double the size
   {
      T[] oldQueue = queue;
      int oldSize = oldQueue.length;
      int newSize = 2*oldSize;
      checkCapacity(newSize);
      integrityOK = false;

      //The cast is safe because the new array contains null entries
      @SuppressWarnings("unchecked")
      T[] tempQueue = (T[]) new Object[newSize];
      queue = tempQueue;
      for(int index = 0; index < oldSize - 1; index++)
      {
         queue[index] = oldQueue[frontIndex];
         frontIndex = (frontIndex + 1) % oldSize;
      }
      frontIndex = 0;
      backIndex = oldSize - 2;
      integrityOK = true;
   }

   public T dequeue()
   {
      checkIntegrity();
      if(isEmpty())
         throw new EmptyQueueException();
      else
      {
         T front = queue[frontIndex];
         queue[frontIndex] = null;
         frontIndex = (frontIndex + 1) % queue.length;
         return front;
      }
   }

   public T getFront()
   {
      checkIntegrity();
      if(isEmpty())
         throw new EmptyQueueException();
      else
         return queue[frontIndex];
   }

   public boolean isEmpty()
   {
      checkIntegrity();
      return frontIndex == ((backIndex + 1) % queue.length);
   }

   public void clear()
   {
      checkIntegrity();
      if(!isEmpty())
      {
         for(int index = frontIndex; index != backIndex; index = (index + 1) % queue.length)
         {
            queue[index] = null;
         }
         queue[backIndex] = null;
      }
      frontIndex = 0;
      backIndex = queue.length - 1;
   }
}
   