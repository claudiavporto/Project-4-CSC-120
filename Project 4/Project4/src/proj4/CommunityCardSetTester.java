package proj4;

import java.util.ArrayList;

public class CommunityCardSetTester {
    public static void main(String[] args){
        proj4.Testing.setVerbose(true);

        testGetIthCard();

        proj4.Testing.finishTests();
    }

    private static CommunityCardSet createHand(String[] cardList){
        ArrayList<Card> phCards = new ArrayList<Card>();
        for(String c: cardList){
            Card newCard = createCard(c);
            phCards.add(newCard);
        }

        return new CommunityCardSet(phCards);
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
        proj4.Testing.testSection("Testing getIthCard() of a Community Card Set");

        CommunityCardSet comm = createHand(new String[]{"23", "132", "62", "80", "101"});

        proj4.Testing.assertTrue("Checking to see if getIthCard() gets correct card at specific index(1)", createCard("132").equals(comm.getIthCard(1)));
        proj4.Testing.assertTrue("Checking to see if getIthCard() gets correct card at specific index(3)", createCard("80").equals(comm.getIthCard(3)));
    }
}
