import java.util.*;
public class LinkedStack<T> implements StackInterface<T>
{
   private Node topNode; //References the first node as the Top Node

   public LinkedStack()
   {
      topNode = null;
   }

   public void push(T newEntry)
   {
      Node newNode = new Node(newEntry, topNode);
      topNode = newNode;
      //topNode = new Node(newEntry, topnode); //Alt Code
   }

   public T pop()
   {
      T top = peek(); //Might throw EmptyStackException
      //Assumption: topNode != null;
      topNode = topNode.getNextNode();
      return top;
   }

   public T peek()
   {
      if(isEmpty())
         throw new EmptyStackException();
      else
         return topNode.getData();
   }

   public boolean isEmpty()
   {
      return topNode == null;
   }

   public void clear()
   {
      topNode = null;
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