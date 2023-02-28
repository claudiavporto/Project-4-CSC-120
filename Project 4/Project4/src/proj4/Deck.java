package proj4;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Deck {
    private static final int SPADES = 0;
    private static final int HEARTS = 1;
    private static final int CLUBS = 2;
    private static final int DIAMONDS = 3;
    private static final int LOWEST_RANK = 2;
    private static final int HIGHEST_RANK_ACE = 14;
    private static final int SIZE_OF_DECK = 52;
    private static final int FIRST_CARD = 0;
    private static final int EMPTY = 0;

    private int nextToDeal = 0;
    private ArrayList<Card> cardDeck;

    /**
     * Constructs a deck of 52 cards
     */
    public Deck() {
        cardDeck = new ArrayList<Card>(SIZE_OF_DECK);

        int[] suits = {SPADES, HEARTS, CLUBS, DIAMONDS};

        for (Integer suit : suits) {
            for (int rank = LOWEST_RANK; rank <= HIGHEST_RANK_ACE; rank++) {
                cardDeck.add(new Card(rank, suit));
            }
        }
    }

    /**
     * Shuffles a deck of cards (undealt cards)
     */
    public void shuffle(){
        for (int i = nextToDeal; i < size(); i++){
            Card cardToSwap = cardDeck.get(i);
            int randIndex = ThreadLocalRandom.current().nextInt(nextToDeal, size());
            Card randCard = cardDeck.get(randIndex);

            cardDeck.set(i, randCard);
            cardDeck.set(randIndex,cardToSwap);
        }
    }

    private Card getIthCard(int index){
        return cardDeck.get(index);
    }

    /**
     * Determines the size of the deck of undealt cards.
     *
     * @return the size of the deck
     */
    public int size(){
        return SIZE_OF_DECK - nextToDeal;
    }

    private boolean isEmpty(){
        return size() == EMPTY;
    }

    /**
     * Returns card deck to its original state with no cards dealt.
     *
     * @return the card deck
     */
    public void gather() {
        nextToDeal = FIRST_CARD;
    }

    /**
     * Checks to see if the card deck is not empty.
     * If it is not empty, get next card to deal and increment nextToDeal.
     *
     * @return the next card to deal if not empty, null if card deck is empty
     */
    public Card deal(){
        if (!isEmpty()) {
            Card c = this.getIthCard(nextToDeal);
            nextToDeal++;
            return c;
        }else{
            return null;
        }
    }

    public String toString(){
        StringBuilder strCardDeck = new StringBuilder();
        for (int i = nextToDeal; i < size(); i++){
            strCardDeck.append(this.getIthCard(i));
            strCardDeck.append("\n");
        }
        return strCardDeck.toString();
    }

    public boolean equals(Deck other){
        boolean isEqual = true;

        for(int i = 0; i < this.size(); i++){
            if(!(this.getIthCard(i).equals(other.getIthCard(i)))){
                isEqual = false;
            }
        }
        return isEqual;
    }
}
