public class ArrayBagTest
{
   public static void main(String[] args)
   {
      final int SIZE = 3;
      BagInterface<String> names1 = new ResizeableArrayBag<>(SIZE);
      BagInterface<String> names2 = new ResizeableArrayBag<>(SIZE+1);
      String[] someNames = {"Joseph", "James", "Jack"};
      String[] otherNames = {"Jack", "Joseph", "James", "Jacquiline"};

      for(int i = 0; i < SIZE+1; i++)
      {
         if(i < SIZE)
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