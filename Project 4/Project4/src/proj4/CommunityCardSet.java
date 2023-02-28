package proj4;

import java.util.ArrayList;

public class CommunityCardSet {
    private static final int HAND_SIZE = 5;
    private ArrayList<Card> cardLst;

    /**
     * Creates an empty proj4.CommunityCardSet.
     *
     * @param cardList the ArrayList of cards that should be in the hand
     */
    public CommunityCardSet(ArrayList<Card> cardList){
        cardLst = new ArrayList<>(cardList);
    }

    private void addCard(Card c){
        if (cardLst.size() < HAND_SIZE){
            cardLst.add(c);
        }
    }

    /**
     * Adds 5 cards to a proj4.PokerHand from the deck.
     * @param d the given deck
     */
    public void dealHand(Deck d){
        for(int i = 0; i < HAND_SIZE; i++){
            this.addCard(d.deal());
        }
    }

    /**
     * Gets and returns card at a specific index in cardLst.
     *
     * @param index the given index
     * @return card at the given index
     */
    public Card getIthCard(int index){
        return cardLst.get(index);
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
