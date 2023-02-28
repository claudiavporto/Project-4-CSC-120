package proj4;

import java.util.ArrayList;
import java.util.Arrays;

public class PokerHand {
    private static final int HAND_1_WINS = 1;
    private static final int HAND_TIE = 0;
    private static final int HAND_2_WINS = -1;

    private static final int NUM_HIGH_CARDS_PAIR = 3;
    private static final int NUM_TWO_PAIRS = 2;
    private static final int NUM_PAIRS = 1;
    private static final int FIRST_INDEX = 0;

    private static final int HAND_SIZE = 5;
    private static final int FOUR_OF_A_KIND = 4;
    private static final int THREE_OF_A_KIND = 3;
    private static final int FLUSH = 4;
    private static final int TWO_PAIR = 3;
    private static final int PAIR = 2;
    private static final int HIGH_CARD = 1;

    private ArrayList<Card> cardLst;

    /**
     * Creates an empty proj4.PokerHand.
     *
     * @param cardList the ArrayList of cards that should be in the hand
     */
    public PokerHand(ArrayList<Card> cardList){
        cardLst = new ArrayList<>(cardList);
    }

    /**
     * Adds a card to an ArrayList of cards.
     * Does nothing if size of ArrayList is already 5.
     *
     * @param c the card to be added
     */
    public void addCard(Card c){
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

    private int[] getRanksInHand(){
        int[] ranks = new int[HAND_SIZE];

        for (int i = 0; i < cardLst.size(); i++){
            ranks[i] = this.getIthCard(i).getRank();
        }

        Arrays.sort(ranks);
        reverseOrder(ranks);

        return ranks;
    }

    private String[] getSuitsInHand(){
        String[] suits = new String[HAND_SIZE];

        for (int i = 0; i < cardLst.size(); i++){
            suits[i] = this.getIthCard(i).getSuit();
        }
        return suits;
    }

    private String getFirstSuitInHand(){
        return this.getSuitsInHand()[0];
    }

    private int count(int match, int[] array){
        int counter = 0;
        for (int i : array) {
            if (match == i) {
                counter++;
            }
        }
        return counter;
    }

    private boolean isFlush(){
        boolean isFlush = true;
        String[] suitsInHand = this.getSuitsInHand();
        String firstSuit = this.getFirstSuitInHand();
        for (int s = 1; s < suitsInHand.length; s++){
            if(!suitsInHand[s].equals(firstSuit)){
                isFlush = false;
            }
        }
        return isFlush;
    }

    private boolean isPair(){
        boolean isPair = false;
        int[] ranksInHand = this.getRanksInHand();
        ArrayList<Integer> pairs = new ArrayList<>();
        for(Integer rank: ranksInHand){
            if(!pairs.contains(rank)){
                if(count(rank, ranksInHand) > 1) {
                    pairs.add(rank);
                }
            }
        }
        if(pairs.size() == NUM_PAIRS) {
            isPair = true;
        }
        return isPair;
    }

    private boolean isTwoPair(){
        boolean isTwoPair = false;
        int[] ranksInHand = this.getRanksInHand();
        ArrayList<Integer> pairs = new ArrayList<>();

        for(Integer rank: ranksInHand){
            if(!pairs.contains(rank)){
                if(count(rank, ranksInHand) > 1) {
                    pairs.add(rank);
                    if(count(rank, ranksInHand) == FOUR_OF_A_KIND){
                        pairs.add(rank);
                    }
                }
            }
        }
        if(pairs.size() == NUM_TWO_PAIRS) {
            isTwoPair = true;
        }
        return isTwoPair;
    }

    private int handType(){
        if (this.isFlush()){
            return FLUSH;
        }else if(this.isTwoPair()){
            return TWO_PAIR;
        }else if(this.isPair()){
            return PAIR;
        }else{
            return HIGH_CARD;
        }
    }

    private int compareRanks(int rank1, int rank2){
        if(rank1 > rank2){
            return HAND_1_WINS;
        }else if(rank1 == rank2){
            return HAND_TIE;
        }else{
            return HAND_2_WINS;
        }
    }

    private int compareFlushOrHighCard(PokerHand other){
        int[] ranks1 = this.getRanksInHand();
        int[] ranks2 = other.getRanksInHand();

        int winningHand = 0;
        int i = 0;
        while(i < HAND_SIZE && winningHand == 0){
            winningHand = this.compareRanks(ranks1[i], ranks2[i]);
            i++;
        }
        return winningHand;
    }

    private ArrayList<Integer> getPairs(){
        int[] handRanks = this.getRanksInHand();
        ArrayList<Integer> pairs = new ArrayList<>();

        for(Integer rank: handRanks){
            if(!pairs.contains(rank)){
                if(count(rank, handRanks) > 1) {
                    pairs.add(rank);
                    if (count(rank, handRanks) == FOUR_OF_A_KIND) {
                        pairs.add(rank);
                    }
                }
            }
        }
        return pairs;
    }

    private ArrayList<Integer> getHighCardsInPairs(){
        int[] handRanks = this.getRanksInHand();
        ArrayList<Integer> pairs = new ArrayList<>();
        ArrayList<Integer> highCard = new ArrayList<>();

        for(Integer rank: handRanks){
            if(!pairs.contains(rank)){
                if(count(rank, handRanks) > 1) {
                    if (count(rank, handRanks) == THREE_OF_A_KIND){
                        highCard.add(rank);
                    }
                    pairs.add(rank);
                }else{
                    highCard.add(rank);
                }
            }
        }
        return highCard;
    }

    private int compareTwoPair(PokerHand other){
        ArrayList<Integer> hand1Pairs = this.getPairs();
        ArrayList<Integer> hand1HighCards = this.getHighCardsInPairs();
        ArrayList<Integer> hand2Pairs = other.getPairs();
        ArrayList<Integer> hand2HighCards = other.getHighCardsInPairs();

        int winningHand = 0;
        int i = 0;

        while(i < NUM_TWO_PAIRS && winningHand == 0){
            winningHand = this.compareRanks(hand1Pairs.get(i), hand2Pairs.get(i));
            i++;
        }
        if(winningHand == 0){
            winningHand = this.compareRanks(hand1HighCards.get(FIRST_INDEX), hand2HighCards.get(FIRST_INDEX));
        }
        return winningHand;
    }

    private int comparePair(PokerHand other){
        ArrayList<Integer> hand1Pairs = this.getPairs();
        ArrayList<Integer> hand1HighCards = this.getHighCardsInPairs();
        ArrayList<Integer> hand2Pairs = other.getPairs();
        ArrayList<Integer> hand2HighCards = other.getHighCardsInPairs();

        int winningHand = 0;
        int i = 0;

        while(i < NUM_PAIRS && winningHand == 0){
            winningHand = this.compareRanks(hand1Pairs.get(i), hand2Pairs.get(i));
            i++;
        }
        if(winningHand == 0){
            while(i < NUM_HIGH_CARDS_PAIR && winningHand == 0){
                winningHand = this.compareRanks(hand1HighCards.get(i), hand2HighCards.get(i));
                i++;
            }
        }
        return winningHand;
    }

    private void reverseOrder(int[] array){
        int length = array.length;
        for (int i = 0; i < length / 2; i++){
            int temp = array[i];
            array[i] = array[length - i - 1];
            array[length - i - 1] = temp;
        }
    }

    /**
     *  Determines how this hand compares to another hand, returns
     *  positive, negative, or zero depending on the comparison.
     *
     *  @param other The hand to compare this hand to
     *  @return a negative number if this is worth LESS than other,
     *  zero if they are worth the SAME,
     *  and a positive number if this is worth MORE than other
     */
    public int compareTo(PokerHand other){
        int thisType = this.handType();
        int otherType = other.handType();

        if(thisType > otherType){
            return HAND_1_WINS;
        }else if(thisType < otherType){
            return HAND_2_WINS;
        }else{
            if(thisType == FLUSH){
                return this.compareFlushOrHighCard(other);
            }else if(thisType == TWO_PAIR){
                return this.compareTwoPair(other);
            }else if(thisType == PAIR){
                return this.comparePair(other);
            }else{
                return this.compareFlushOrHighCard(other);
            }
        }
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

