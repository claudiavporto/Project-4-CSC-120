package proj4;

public class CardTester {
    public static void main(String[] args){
        proj4.Testing.setVerbose(true);

        testGetRank();
        testGetSuit();

        proj4.Testing.finishTests();
    }

    private static Card createCard(String rank, String suit){
        Card c = new Card(rank, suit);
        return c;
    }

    private static Card createCard(int rank, int suit){
        Card c = new Card(rank, suit);
        return c;
    }
    private static void testGetRank(){
        proj4.Testing.testSection("Testing getRank()");

        testGetRankString();
        testGetRankInt();

    }

    private static void testGetRankString(){
        proj4.Testing.testSection("Testing getRank() creating a Card using Constructor of Rank and Suit type String");

        int cRank = createCard("Two", "Diamonds").getRank();
        int c1Rank = createCard("two", "diamonds").getRank();
        int c2Rank = createCard("Queen", "Spades").getRank();

        proj4.Testing.assertEquals("Whole Number Rank of given type String with Capital", 2, cRank);
        proj4.Testing.assertEquals("Whole Number Rank of given type String all lowercase", 2, c1Rank);
        proj4.Testing.assertEquals("Face Card Rank of given type String", 12, c2Rank);

    }

    private static void testGetRankInt(){
        proj4.Testing.testSection("Testing getRank() creating a Card using Constructor of Rank and Suit type int");

        int cRank = createCard(2, 3).getRank();
        int c1Rank = createCard(12, 1).getRank();

        proj4.Testing.assertEquals("Whole Number Rank of given type int", 2, cRank);
        proj4.Testing.assertEquals("Face Card Rank of given type int", 12, c1Rank);
    }

    private static void testGetSuit(){
        proj4.Testing.testSection("Testing getSuit()");

        testGetSuitString();
        testGetSuitInt();
    }

    private static void testGetSuitString(){
        proj4.Testing.testSection("Testing getSuit() creating a Card using String constructor");

        String cSuit = createCard("Two", "Diamonds").getSuit();
        String c1Suit = createCard("Queen", "Spades").getSuit();

        proj4.Testing.assertEquals("Suit of given type String", "Diamonds", cSuit);
        proj4.Testing.assertEquals("Suit of given type String", "Spades", c1Suit);
    }

    private static void testGetSuitInt(){
        proj4.Testing.testSection("Testing getSuit() creating a Card using int constructor");

        String cSuit = createCard(2, 3).getSuit();
        String c1Suit = createCard(12, 1).getSuit();

        proj4.Testing.assertEquals("Suit of given type int", "Diamonds", cSuit);
        proj4.Testing.assertEquals("Suit of given type int", "Hearts", c1Suit);
    }
}
