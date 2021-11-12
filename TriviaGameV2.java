// Juan M. Manriquez
// CS1400, Section 03
// Project 6 - Trivia Game
// 04/19/2021

import java.util.Scanner;

public class TriviaGameV2
{
   private int ARRAY_SIZE = 5;
   private Trivia[] trivia;
   private int score;
   private int questionNum;

   public TriviaGameV2()
   {
      score = 0; questionNum = 0;
      trivia = new Trivia[ARRAY_SIZE];

      trivia[0]=new Trivia("The first Pokemon that Ash recieves from Professor Oak",
                             "pikachu", 1);
      trivia[1]=new Trivia("Erling Kagge skiied into here alone on January 7, 1933",
                             "south pole", 2);
      trivia[2]=new Trivia("1997 British band that produced 'Tub Thumper'",
                             "chumbawumba", 2);
      trivia[3]=new Trivia("Who is the tallest person on record (8ft. 11in) that has lived?",
                             "robert wadlow", 3);
      trivia[4]=new Trivia("PT Barnum said \"This way to the ________\" to attract people to the exit.",
                             "egress", 1);

   }
   
   public boolean askNextQuestion()
   {
      if(questionNum >= ARRAY_SIZE)
         return false;
      
      Scanner kb = new Scanner(System.in);
      System.out.println("\nQuestion " + (questionNum+1));
      System.out.println(trivia[questionNum].getQuestion());
      String userAns = kb.nextLine();

      //Can use .equalsIgnoreCase() for later string comparisons
      if(userAns.toUpperCase().equals(trivia[questionNum].getAnswer().toUpperCase()))
      {
         System.out.println("That is correct!");
         score += trivia[questionNum].getValue();
      }
      else
         System.out.println("Wrong. The correct answer is " +
                           trivia[questionNum].getAnswer());

      questionNum++;
      return true;
   }
   
   public void showScore()
   {   System.out.println("Your score is " + score);   }
}