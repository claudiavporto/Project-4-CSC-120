package proj4;

public class DeckTester {
    public static void main(String[] args){
        proj4.Testing.setVerbose(true);

        testShuffle();
        testSize();
        testGather();
        testDeal();

        proj4.Testing.finishTests();
    }

    private static Deck createDeck(){
        Deck d = new Deck();
        return d;
    }

    private static void testShuffle(){
        proj4.Testing.testSection("Testing shuffle() of a Deck");

        Deck d = createDeck();
        Deck d1 = createDeck();
        d1.shuffle();

        proj4.Testing.assertFalse("Testing equality of an unshuffled Deck to a shuffled Deck", d.equals(d1));

    }

    private static void testSize(){
        proj4.Testing.testSection("Testing size() of a Deck");

        Deck d = createDeck();
        int sizeBefore = d.size();
        d.deal();
        int sizeAfter = d.size();

        proj4.Testing.assertTrue("Testing equality of the Size of deck before and after a card had been dealt", (sizeBefore - 1) == (sizeAfter));
    }

    private static void testGather(){
        proj4.Testing.testSection("Testing gather() of a Deck");

        Deck d = createDeck();

        for (int i = 0; i < 5; i++){
            d.deal();
        }
        int sizeAfterDeal = d.size();
        d.gather();
        int sizeAfterGather = d.size();

        proj4.Testing.assertTrue("Testing equality of the Size of deck after 5 cards are dealt and after deck is gathered", (sizeAfterDeal) == (sizeAfterGather - 5));

    }

    private static void testDeal(){
        proj4.Testing.testSection("Testing deal() of a Deck");

        Deck d = createDeck();
        int sizeBeforeDeal = d.size();
        d.deal();
        int sizeAfterDeal = d.size();

        proj4.Testing.assertTrue("Testing equality of the Size of deck before and after 1 card is dealt", (sizeAfterDeal) == (sizeBeforeDeal - 1));


    }

}
