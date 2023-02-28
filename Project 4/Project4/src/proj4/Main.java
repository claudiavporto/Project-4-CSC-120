package proj4;

import java.util.*;

/** I affirm that I have carried out the attached academic endeavors
 *  with full academic honesty, in accordance with the Union College
 *  Honor Code and the course syllabus.
 */

public class Main {
    private static final int MAX_POINTS = 11;

    public static void main(String[] args){
        Deck gameDeck = new Deck();
        gameDeck.shuffle();

        ArrayList<Card> community = new ArrayList<Card>();
        CommunityCardSet commCards = new CommunityCardSet(community);
        commCards.dealHand(gameDeck);

        ArrayList<Card> handACards = new ArrayList<Card>();
        ArrayList<Card> handBCards = new ArrayList<Card>();

        boolean correctAnswer = true;
        int totalScore = 0;

        while ((totalScore < MAX_POINTS) && correctAnswer) {

            System.out.println("The Community Cards are: ");
            System.out.println(commCards);

            System.out.println("Which of the following hands is worth more?");

            StudPokerHand handA = new StudPokerHand(commCards, handACards);
            StudPokerHand handB = new StudPokerHand(commCards, handBCards);
            handA.dealStudPokerHand(gameDeck);
            handB.dealStudPokerHand(gameDeck);

            System.out.println("Hand A:");
            System.out.println(handA);
            System.out.println("Hand B:");
            System.out.println(handB);


            int actualResult = handA.compareTo(handB);

            String answer = "";
            if (actualResult == 1) {
                answer = "A";
            } else if (actualResult == -1) {
                answer = "B";
            } else if (actualResult == 0) {
                answer = "Tie";
            }

            Scanner input = new Scanner(System.in);
            System.out.println("Enter a or b (or SPACE if hands tie):");
            String userGuess = input.nextLine();

            int guess = 3;
            if (userGuess.equalsIgnoreCase("a")) {
                guess = 1;
            } else if (userGuess.equalsIgnoreCase("b")) {
                guess = -1;
            } else if (userGuess.equals(" ")) {
                guess = 0;
            }

            if (actualResult != guess) {
                correctAnswer = false;
                System.out.println("Sorry! You lost!\nThe correct answer was " + answer + ".");
            } else {
                totalScore++;
                System.out.println("WOOHOO! THAT'S CORRECT!!!");
            }
        }
        System.out.println("Final score: " + totalScore);
        System.out.println("---------------------------");

        gameDeck.gather();
        gameDeck.shuffle();
    }
}
