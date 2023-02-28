package proj4;

import java.util.ArrayList;

public class StudPokerHandTester {
    public static void main(String[] args){
        proj4.Testing.setVerbose(true);

        testCompareTo();

        proj4.Testing.finishTests();
    }

    private static StudPokerHand createStudHand(String[] comm, String[] cardList){
        ArrayList<Card> studCards = new ArrayList<Card>();
        ArrayList<Card> community = new ArrayList<Card>();

        for(String c: cardList){
            Card newCard = createCard(c);
            studCards.add(newCard);
        }

        for(String card: comm) {
            Card newCard = createCard(card);
            community.add(newCard);
        }

        CommunityCardSet communityCardSet = new CommunityCardSet(community);

        return new StudPokerHand(communityCardSet, studCards);
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

    private static void testCompareTo(){
        proj4.Testing.testSection("Testing compareTo() of a Stud Poker Hand");

        String[] comm = {"60", "140", "21", "63", "120"};
        String[] comm1 = {"111", "71", "41", "131", "60"};
        String[] comm2 = {"141", "82", "53", "111", "72"};


        StudPokerHand stud = createStudHand(comm, new String[]{"121", "143"});
        StudPokerHand other = createStudHand(comm, new String[]{"61", "62"});
        StudPokerHand stud1 = createStudHand(comm, new String[]{"22", "30"});
        StudPokerHand stud2 = createStudHand(comm1, new String[]{"140", "122"});
        StudPokerHand other2 = createStudHand(comm, new String[]{"61", "32"});
        StudPokerHand other3 = createStudHand(comm1, new String[]{"23", "123"});
        StudPokerHand stud3 = createStudHand(comm2, new String[]{"21", "43"});
        StudPokerHand other4 = createStudHand(comm2, new String[]{"30", "22"});
        StudPokerHand stud4 = createStudHand(comm2, new String[]{"80", "43"});
        StudPokerHand other5 = createStudHand(comm2, new String[]{"30", "81"});
        StudPokerHand stud5 = createStudHand(comm2, new String[]{"21", "33"});
        StudPokerHand other6 = createStudHand(comm2, new String[]{"20", "32"});

        proj4.Testing.assertEquals("Two Pair(A, Q) beats 4-of-a-Kind(6s)", 1, stud.compareTo(other));
        proj4.Testing.assertEquals("Two Pair(6,2) loses to 4-of-a-Kind(6s)", -1, stud1.compareTo(other));
        proj4.Testing.assertEquals("Hand with better hole cards loses to Flush", -1, stud2.compareTo(other2));
        proj4.Testing.assertEquals("Two Pair( , ) beats Full House( over )", 1, stud2.compareTo(other3));
        proj4.Testing.assertEquals("Hole Cards makes two different two pair hands", 1, stud.compareTo(other3));
        proj4.Testing.assertEquals("Community is the best hand", 0, stud3.compareTo(other4));
        proj4.Testing.assertEquals("Tie One Pair", 0, stud4.compareTo(other5));
        proj4.Testing.assertEquals("Tie High Card", 0, stud5.compareTo(other6));


    }
}
