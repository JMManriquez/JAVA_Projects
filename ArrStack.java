import java.util.*;

public class ArrStack<T> implements StackInterface<T>
{
   private T[] stack; //array of stack entries
   private int topIndex; //Index of entries
   private boolean integrityOK = false;
   private static final int DEFAULT_CAPACITY = 50;
   private static final int MAX_CAPACITY = 10000;

   public ArrStack()
   {
      this(DEFAULT_CAPACITY);
   }

   public ArrStack(int initialCapacity)
   {
      integrityOK = false;
      checkCapacity(initialCapacity);
      
      //The cast is safe because the new array contains null entries
      @SuppressWarnings("unchecked")
      T[] tempStack = (T[]) new Object[initialCapacity];
      stack = tempStack;
      topIndex = -1;
      integrityOK = true;
   }
      
   public void push(T newEntry)
   {
      checkIntegrity();
      ensureCapacity();
      stack[topIndex + 1] = newEntry;
      topIndex++;
   }

   private void ensureCapacity()
   {
      if(topIndex >= stack.length - 1)
      {
         int newLength = 2*stack.length;
         checkCapacity(newLength);
         stack = Arrays.copyOf(stack, newLength);
      }
   }

   private void checkCapacity(int capacity)
   {
      if(capacity > MAX_CAPACITY)
         throw new IllegalStateException("Attempt to create a bag whose "
                                        + "capcity exceeds allows "
                                        + "maximum of " + MAX_CAPACITY);
   }

   public T pop()
   {
      checkIntegrity();
      if(isEmpty())
         throw new EmptyStackException();
      else
      {
         T top = stack[topIndex];
         stack[topIndex] = null;
         topIndex--;
         return top;
      }
   }

   public T peek()
   {
      checkIntegrity();
      if(isEmpty())
         throw new EmptyStackException();
      else
         return stack[topIndex];
   }

   public boolean isEmpty()
   {
      return topIndex < 0;
   }
   
   public void clear()
   {
      checkIntegrity();
      
      //Remove references to the objects in the stack
      //but do not deallocate the array
      while(topIndex > -1)
      {
         stack[topIndex] = null;
         topIndex--;
      }
   }

   private void checkIntegrity()
   {
      if(!integrityOK)
         throw new SecurityException("ArrayBagFixed object is corrupt.");
   }
}