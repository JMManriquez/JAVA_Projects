

public class LinkedQueue<T> implements QueueInterface<T>
{
   private Node firstNode; //references node at front of queue
   private Node lastNode; //references node at the back of queue
   
   public LinkedQueue()
   {
      firstNode = null;
      lastNode = null;
   }

   public void enqueue(T newEntry)
   {
      Node newNode = new Node(newEntry, null);
      if(isEmpty())
         firstNode = newNode;
      else
         lastNode.setNextNode(newNode);
      lastNode = newNode;
   }

   public T dequeue()
   {
      //Might throw EmptyQueueException
      T front = getFront(); //Assertion: firstNode != null

      firstNode.setData(null);
      firstNode = firstNode.getNextNode();

      if(firstNode == null)
         lastNode = null;
      return front;
   }

   public T getFront()
   {
      if(isEmpty())
         throw new EmptyQueueException();
      else
         return firstNode.getData();
   }

   public boolean isEmpty()
   {
      return (firstNode == null) && (lastNode == null);
   }

   public void clear()
   {
      firstNode = null;
      lastNode = null;
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