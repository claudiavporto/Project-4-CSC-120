package proj4;

import java.util.ArrayList;

public class StudPokerHand {
    private static final int SIZE_OF_HAND = 5;
    private static final int STUD_PH_SIZE = 2;
    private ArrayList<Card> cardLst;
    private CommunityCardSet community;

    /**
     * Creates a StudPokerHand. An object of this class
     * represents a 2-card poker hand that has access to the community cards.
     *
     * @param cardList the ArrayList of cards that should be in the hand
     * @param cc the CommunityCardSet of cards that are accessed
     */
    public StudPokerHand(CommunityCardSet cc, ArrayList<Card> cardList){
        cardLst = new ArrayList<Card>(cardList);
        this.community = cc;
    }

    private void addCard(Card c){
        if (cardLst.size() < STUD_PH_SIZE){
            cardLst.add(c);
        }
    }

    private Card getIthCard(int index){
        return cardLst.get(index);
    }

    /**
     * Adds 2 cards to a proj4.StudPokerHand from the deck.
     * @param d the given deck
     */
    public void dealStudPokerHand(Deck d){
        for(int i = 0; i < STUD_PH_SIZE; i++){
            this.addCard(d.deal());
        }
    }

    private ArrayList<Card> getSevenCardList(){
        ArrayList<Card> communityStudCards = new ArrayList<Card>();

        for (int i = 0; i < 5; i++){
            Card comm = community.getIthCard(i);
            communityStudCards.add(comm);
        }

        for (int i = 0; i < 2; i ++){
            communityStudCards.add(this.getIthCard(i));
        }

        return communityStudCards;
    }

    private ArrayList<ArrayList<Card>> combos(ArrayList<Card> commStudCards, int comboSize) {

        ArrayList<Card> hand = new ArrayList<Card>();
        for(int i = 0; i < SIZE_OF_HAND; i++){
            hand.add(commStudCards.get(i));
        }

        ArrayList<ArrayList<Card>> result = new ArrayList<ArrayList<Card>>();
        combos2(commStudCards, comboSize, 0, hand, result);

        return result;
    }

    private static void combos2(ArrayList<Card> commStudCards, int length, int start, ArrayList<Card> hand, ArrayList<ArrayList<Card>> result) {

        if (length == 0) {
            result.add((ArrayList<Card>) hand.clone());
            return;
        }

        for (int i = start; i <= commStudCards.size() - length; i++) {
            hand.set(SIZE_OF_HAND - length, commStudCards.get(i));
            combos2(commStudCards, length - 1, i + 1, hand, result);
        }

    }

    private ArrayList<PokerHand> combosToPokerHand(ArrayList<ArrayList<Card>> combos){
        ArrayList<PokerHand> handCombos = new ArrayList<PokerHand>();

        for (ArrayList<Card> combo: combos){
            PokerHand comboHand = new PokerHand(combo);
            handCombos.add(comboHand);
        }

        return handCombos;
    }

    private ArrayList<PokerHand> getAllFiveCardHands() {
        ArrayList<PokerHand> all5CardHands = new ArrayList<PokerHand>();
        ArrayList<Card> sevenCards = getSevenCardList();

        all5CardHands = combosToPokerHand(combos(sevenCards, SIZE_OF_HAND));

        return all5CardHands;

    }

    private PokerHand getBestFiveCardHand()
    {
        ArrayList<PokerHand> hands = getAllFiveCardHands();
        PokerHand bestSoFar = hands.get(0);

        for (int i = 1; i < hands.size(); i++) {
            if (hands.get(i).compareTo(bestSoFar) > 0) {
                bestSoFar = hands.get(i);
            }
        }
        return bestSoFar;
    }

    /**
     * Determines how this hand compares to another hand, using the
     * community card set to determine the best 5-card hand it can
     * make. Returns positive, negative, or zero depending on the comparison.
     *
     * @param other The hand to compare this hand to
     * @return a negative number if this is worth LESS than other, zero
     * if they are worth the SAME, and a positive number if this is worth
     * MORE than other
     */

    public int compareTo(StudPokerHand other){
        PokerHand myBest = this.getBestFiveCardHand();
        PokerHand otherBest = other.getBestFiveCardHand();
        return myBest.compareTo(otherBest);
    }

    public String toString(){
        StringBuilder strCardHand = new StringBuilder();

        for (Card c: cardLst){
            strCardHand.append(c);
            strCardHand.append("\n");
        }
        return strCardHand.toString();
    }
}
