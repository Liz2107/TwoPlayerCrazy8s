import java.util.ArrayList;
import java.util.Scanner;
class Main {
  static ArrayList<Card> handOne;
  static ArrayList<Card> handTwo; 
  static int topCardValue;
  static String topCardSuit;

  public static void main(String[] args) {
   int numberOfPlayers = 2;
   Deck.fillDeck();
   Deck.shuffle();
  /* for(Card c: Deck.deck)
   {
     System.out.print(c.getRank() + " of " + c.getSuit());
   }*/
   
  dealCards(numberOfPlayers);
  turnoverTopCard();
  while(handTwo.size() > 0 && Deck.deck.size() > 0)
  {
    printHand();
    userPlay(topCardValue, topCardSuit);
    computerPlay(topCardValue, topCardSuit);
    
  }
  System.out.println("The computer won. Better luck next time.");
  }
  public static void computerPlay(int topV, String topS)
  {
    int startSize = handTwo.size();
    for(int i = 0; i < handTwo.size(); i++)
    {
      if(handTwo.get(i).getValue() == 8)
      {
        topCardValue = 8;
        topCardSuit = handTwo.get(i + 1).getSuit();
        System.out.println("The computer played a Crazy 8! The top card is now an eight of " + topCardSuit + ".");
        handTwo.remove(i);
        i=100;
       
      }
      else if(handTwo.get(i).getSuit().equals(topCardSuit))
      {
        topCardValue = handTwo.get(i).getValue();
        topCardSuit = handTwo.get(i).getSuit();        
        System.out.println("The computer's turn is over. The top card is now a " + handTwo.get(i).getRank() + " of " + handTwo.get(i).getSuit() + ".");
        handTwo.remove(i);
        i=100;
      }
      else if(handTwo.get(i).getValue() == topCardValue)
      {
        topCardValue = handTwo.get(i).getValue();
        topCardSuit = handTwo.get(i).getSuit();        
        System.out.println("The computer's turn is over. The top card is now a " + handTwo.get(i).getRank() + " of " + handTwo.get(i).getSuit() + ".");
        handTwo.remove(i);
        i=100;
      }
      
    }
    if(handTwo.size() == startSize)
    {
        handTwo.add(Deck.deck.get(1));
        System.out.println("The computer drew a card. It is your move.");
        Deck.deck.remove(1);
         
      
    }
  }
  public static void printHand()
  {
    System.out.println("Your hand includes the following:");
    for(int i = 0; i < handOne.size(); i ++)
    {
       System.out.println((i+1) + ". " + handOne.get(i).getRank() + " of " + handOne.get(i).getSuit());
    }
    System.out.println((handOne.size() + 1) + ". Skip");
  }
  public static void dealCards(int a)
    {
      handOne = new ArrayList<Card>();
      handTwo = new ArrayList<Card>();
        for(int j = 0; j < 5; j++)
        {
          handOne.add(Deck.deck.get(j));
          Deck.deck.remove(j);
          handTwo.add(Deck.deck.get(j));
          Deck.deck.remove(j);
         //System.out.println((j+1) + ". " + handOne.get(j).getRank() + " of " + handOne.get(j).getSuit());

        }
     // System.out.println("6. Skip");
      
    }
    public static void turnoverTopCard()
    {
      System.out.println("The top card is a " + Deck.deck.get(0).getRank() + " of " + Deck.deck.get(0).getSuit() + ".");
      topCardValue = Deck.deck.get(0).getValue();
      topCardSuit = Deck.deck.get(0).getSuit();
      Deck.deck.add(Deck.deck.get(0));
      Deck.deck.remove(0);
    }
    public static void userPlay(int topV, String topS)
    {
      if(handOne.size() <= 1) 
      {
        System.out.println("You won!");
        System.exit(0);
      }
      System.out.println("Enter the index of the card you would like to play. If you can't play anything, use the skip option.");
      Scanner userIndexInput = new Scanner(System.in);
      int indexInput = userIndexInput.nextInt();
      if(indexInput == (handOne.size() + 1))
      {
        System.out.println("You must draw a card. The card you drew was " + Deck.deck.get(1).getRank() + " of " + Deck.deck.get(1).getSuit() + ".");
        handOne.add(Deck.deck.get(1));
        Deck.deck.remove(1);
      }
      else if(handOne.get(indexInput - 1).getValue() == 8)
      {
        System.out.println("Would you like to change the suit to 1) Clubs, 2) Hearts, 3) Spades, or 4) Diamonds?");
        Scanner userSuitChoice = new Scanner(System.in);
        int suitChoice = userSuitChoice.nextInt();
        if(suitChoice == 1)
        {
          System.out.println("The next card played must be a Club.");
          topCardSuit = "clubs";
          handOne.remove(indexInput - 1);
        }
        else if(suitChoice == 2)
        {
          System.out.println("The next card played must be a Heart.");
          topCardSuit = "hearts";
          handOne.remove(indexInput - 1);
        }
        else if(suitChoice == 3)
        {
          System.out.println("The next card played must be a Spade.");
          topCardSuit = "spades";
          handOne.remove(indexInput - 1);
        }
        else if(suitChoice == 4)
        {
          System.out.println("The next card played must be a Diamond.");
          topCardSuit = "diamonds";
          handOne.remove(indexInput - 1);
        }
      }
      else if(handOne.get(indexInput - 1).getValue() == topV || handOne.get(indexInput - 1).getSuit().equals(topS))
      {
        System.out.println("The top card is a " + handOne.get(indexInput - 1).getRank() + " of " + handOne.get(indexInput - 1).getSuit() + ".");
        topCardValue = handOne.get(indexInput - 1).getValue();
        topCardSuit = handOne.get(indexInput - 1).getSuit();
        handOne.remove(indexInput - 1);
        
      }
      else
      {
        //System.out.println(topValue)
        System.out.println("You cannot play, so you must draw a card. The card you drew was " + Deck.deck.get(1).getRank() + " of " + Deck.deck.get(1).getSuit() + ".");
        handOne.add(Deck.deck.get(1));
        Deck.deck.remove(1);
      }
    }
}