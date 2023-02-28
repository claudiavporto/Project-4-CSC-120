package proj4;

import java.util.ArrayList;

public class PokerHandTester {
    public static void main(String[] args){
        proj4.Testing.setVerbose(true);

        testGetIthCard();
        testCompareTo();

        proj4.Testing.finishTests();
    }

    private static PokerHand createPokerHand(String[] cardList){
        ArrayList<Card> phCards = new ArrayList<Card>();
        for(String c: cardList){
            Card newCard = createCard(c);
            phCards.add(newCard);
        }

        return new PokerHand(phCards);
    }

    private static Card createCard(String cardString){
        if(cardString.length() == 3) {
            String rank = cardString.substring(0, 2);
            String suit = cardString.substring(2);
            int r = Integer.parseInt(rank);
            int s = Integer.parseInt(suit);

            return new Card(r, s);
        }else{
            String rank = cardString.substring(0,1);
            String suit = cardString.substring(1);
            int r = Integer.parseInt(rank);
            int s = Integer.parseInt(suit);

            return new Card(r, s);
        }

    }
    private static void testGetIthCard(){
        proj4.Testing.testSection("Testing getIthCard() of a PokerHand");

        PokerHand ph = createPokerHand(new String[]{"23", "132", "62", "80", "101"});

        proj4.Testing.assertTrue("Checking to see if getIthCard() gets correct card at specific index(1)", createCard("132").equals(ph.getIthCard(1)));
        proj4.Testing.assertTrue("Checking to see if getIthCard() gets correct card at specific index(3)", createCard("80").equals(ph.getIthCard(3)));
    }

    private static void testCompareTo(){
        proj4.Testing.testSection("Testing compareTo() of a PokerHand");

        testHandType();
        testTies();
        testCompareToFlush();
        testCompareTwoPair();
        testComparePair();
        testCompareHighCard();
    }

    private static void testHandType(){
        proj4.Testing.testSection("Testing handType() of a PokerHand");

        PokerHand phFlush = createPokerHand(new String[]{"70", "140", "80", "50","100"});
        PokerHand ph2Pair = createPokerHand(new String[]{"42", "110", "112", "80","82"});
        PokerHand phPair = createPokerHand(new String[]{"102", "103", "132", "62","122"});
        PokerHand phHighCard = createPokerHand(new String[]{"120", "142", "72", "90","83"});
        PokerHand ph3ofaKind = createPokerHand(new String[]{"112", "113", "111", "62","122"});
        PokerHand ph4ofaKind = createPokerHand(new String[]{"102", "103", "101", "62","100"});

        proj4.Testing.assertEquals("Comparing Flush hand to Two-Pair hand", 1, phFlush.compareTo(phPair));
        proj4.Testing.assertEquals("Comparing Flush hand to Two-Pair hand", 1, ph2Pair.compareTo(phHighCard));
        proj4.Testing.assertEquals("Comparing 3-of-a-Kind hand to Two-Pair hand", -1, ph3ofaKind.compareTo(ph2Pair));
        proj4.Testing.assertEquals("Comparing 4-of-a Kind hand to Pair hand", 1, ph4ofaKind.compareTo(phPair));
    }

    private static void testTies(){
        proj4.Testing.testSection("Testing for ties of two PokerHands");

        PokerHand phFlush = createPokerHand(new String[]{"100", "50", "130", "70","30"});
        PokerHand phFlush1 = createPokerHand(new String[]{"101", "51", "131", "71","31"});
        proj4.Testing.assertEquals("Two Flush Hands With Same Ranks are equal", 0, phFlush.compareTo(phFlush1));

        PokerHand ph2Pair = createPokerHand(new String[]{"100", "50", "130", "70","30"});
        PokerHand ph2Pair1 = createPokerHand(new String[]{"101", "51", "131", "71","31"});
        proj4.Testing.assertEquals("Two Flush Hands With Same Ranks are equal", 0, ph2Pair.compareTo(ph2Pair1));

        PokerHand phPair = createPokerHand(new String[]{"100", "50", "130", "70","30"});
        PokerHand phPair1 = createPokerHand(new String[]{"101", "51", "131", "71","31"});
        proj4.Testing.assertEquals("Two Flush Hands With Same Ranks are equal", 0, phPair.compareTo(phPair1));

        PokerHand phHighCard = createPokerHand(new String[]{"100", "50", "130", "70","30"});
        PokerHand phHighCard1 = createPokerHand(new String[]{"101", "51", "131", "71","31"});
        proj4.Testing.assertEquals("Two Flush Hands With Same Ranks are equal", 0, phHighCard.compareTo(phHighCard1));
    }
    private static void testCompareToFlush(){
        proj4.Testing.testSection("Testing compareToFlushOrHighCard() of two PokerHands");

        PokerHand phFlush = createPokerHand(new String[]{"100", "50", "130", "70","30"});
        PokerHand phFlush2 = createPokerHand(new String[]{"102", "52", "132", "72","22"});
        PokerHand phFlush3 = createPokerHand(new String[]{"83", "43", "143", "33","23"});

        proj4.Testing.assertEquals("Comparing Flush hand to another Flush hand with all same rank except lowest rank", 1, phFlush.compareTo(phFlush2));
        proj4.Testing.assertEquals("Comparing two Flush hands with different ranks", -1, phFlush.compareTo(phFlush3));

    }

    private static void testCompareTwoPair(){
        proj4.Testing.testSection("Testing compareTwoPair() of two PokerHands");

        PokerHand ph2Pair = createPokerHand(new String[]{"42", "110", "112", "80","82"});
        PokerHand ph2Pair1 = createPokerHand(new String[]{"111", "113", "51", "50","140"});
        PokerHand ph2Pair2 = createPokerHand(new String[]{"111", "113", "81", "83","20"});
        PokerHand ph2Pair3 = createPokerHand(new String[]{"130", "133", "71", "72","100"});

        proj4.Testing.assertEquals("Comparing two Two-Pair hands with same pair ranks and different high card", 1, ph2Pair.compareTo(ph2Pair1));
        proj4.Testing.assertEquals("Comparing two Two-Pair hands with highest pair ranks", 1, ph2Pair.compareTo(ph2Pair2));
        proj4.Testing.assertEquals("Comparing two Two-Pair hands with different pair ranks", -1, ph2Pair.compareTo(ph2Pair3));

    }

    private static void testComparePair(){
        proj4.Testing.testSection("Testing comparePair() of two PokerHands");

        PokerHand phPair = createPokerHand(new String[]{"102", "103", "132", "62","122"});
        PokerHand phPair1 = createPokerHand(new String[]{"100", "101", "130", "123","20"});
        PokerHand phPair2 = createPokerHand(new String[]{"100", "101", "130", "81","20"});
        PokerHand phPair3 = createPokerHand(new String[]{"100", "101", "72", "61","30"});
        PokerHand phPair4 = createPokerHand(new String[]{"121", "122", "130", "61","30"});

        proj4.Testing.assertEquals("Comparing two Pair hands with same pair ranks and different lowest high card", 1, phPair.compareTo(phPair1));
        proj4.Testing.assertEquals("Comparing two Pair hands with same pair rank and same highest high card", 1, phPair.compareTo(phPair2));
        proj4.Testing.assertEquals("Comparing two Pair hands with same pair rank and different high cards", 1, phPair.compareTo(phPair3));
        proj4.Testing.assertEquals("Comparing two Pair hands with different pair ranks", -1, phPair.compareTo(phPair4));

    }

    private static void testCompareHighCard(){
        proj4.Testing.testSection("Testing compareToFlushOrHighCard() of two PokerHands");

        PokerHand phHighCard = createPokerHand(new String[]{"120", "132", "72", "90","83"});
        PokerHand phHighCard1 = createPokerHand(new String[]{"121", "130", "81", "93","20"});
        PokerHand phHighCard2 = createPokerHand(new String[]{"130", "100", "61", "113","20"});
        PokerHand phHighCard3 = createPokerHand(new String[]{"140", "100", "61", "113","20"});

        proj4.Testing.assertEquals("Comparing two High Card hands with different lowest high card", 1, phHighCard.compareTo(phHighCard1));
        proj4.Testing.assertEquals("Comparing two High Card hands with same highest high card", 1, phHighCard.compareTo(phHighCard2));
        proj4.Testing.assertEquals("Comparing two different High Card hands", -1, phHighCard.compareTo(phHighCard3));

    }


}
