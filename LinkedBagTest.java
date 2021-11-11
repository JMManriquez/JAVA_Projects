public class LinkedBagTest
{
   public static void main(String[] args)
   {
      BagInterface<String> names1 = new LinkedBag<>();
      BagInterface<String> names2 = new LinkedBag<>();
      String[] someNames = {"Joseph", "James", "Jack"};
      String[] otherNames = {"Jack", "Joseph", "James", "Jacquiline"};

      for(int i = 0; i < otherNames.length; i++)
      {
         if(i < someNames.length)
            names1.add(someNames[i]);
         names2.add(otherNames[i]);
      }

      System.out.print("Bag 1: ");
      names1.printToString();
      System.out.print("Bag 2: ");
      names2.printToString();
      System.out.println();

      BagInterface<String> allNames = names1.union(names2);
      System.out.print("\nUnion of Bag1 and Bag2: ");
      allNames.printToString();
      System.out.println();

      BagInterface<String> commonNames = names1.intersection(names2);
      System.out.print("\nIntersection of Bag1 and Bag2: ");
      commonNames.printToString();
      System.out.println();

      BagInterface<String> diffNames1 = names1.difference(names2);
      System.out.print("\nBag1 Difference Bag2: ");
      diffNames1.printToString();
      System.out.println();

      BagInterface<String> diffNames2 = names2.difference(names1);
      System.out.print("\nBag2 Difference Bag1: ");
      diffNames2.printToString();
      System.out.println();
   }
}