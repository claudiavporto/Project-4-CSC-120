package proj4;

public class Card {
    private static final int INT_JACK = 11;
    private static final int INT_QUEEN = 12;
    private static final int INT_KING = 13;
    private static final int INT_ACE = 14;
    private static final String JACK = "11";
    private static final String QUEEN = "12";
    private static final String KING = "13";
    private static final String ACE = "14";
    private static final String SPADES = "0";
    private static final String HEARTS = "1";
    private static final String CLUBS = "2";
    private static final String DIAMONDS = "3";

    private String rank;
    private String suit;

    /**
     * Creates a Card with a given rank and suit.
     *
     * @param rank whole cards(2-10) can either be spelled
     *            out like "two" or numeric like "2". Case
     *             insensitive.
     * @param suit "Spades", "Hearts", "Clubs", or "Diamonds"
     */
    public Card(String rank, String suit){
        this.rank = rank;
        this.suit = suit;
    }

    /**
     * Creates a Card with a given rank and suit.
     *
     * @param rank The rank of the card, which must be between
     *            2 and 14, inclusive.
     * @param suit The suit of the card, which must be
     *            0=SPADES, 1 HEARTS, 2=CLUBS, or 3=DIAMONDS
     */
    public Card(int rank, int suit){
        this.rank = Integer.toString(rank);
        this.suit = Integer.toString(suit);
    }

    /**
     Gets and returns the rank of the card.
     @return the rank
     */
    public int getRank(){
        return intRank();
    }

    /**
     * Gets and returns the suit of the card.
     * @return the suit
     */
    public String getSuit(){
        return strSuit();
    }


    private int intRank(){
        int intRank = 0;
        String[] wholeRanks = {"two","three","four", "five", "six", "seven",
                                "eight", "nine", "ten"};
        String[] wholeRanksInt = {"2", "3", "4", "5", "6", "7", "8", "9", "10"};

        int[] wholeIntRanks = {2,3,4,5,6,7,8,9,10};

        for(int i = 0; i < wholeRanks.length; i++){
            if(this.rank.equalsIgnoreCase(wholeRanks[i]) || this.rank.equals(wholeRanksInt[i])){
                intRank = wholeIntRanks[i];
            }
        }

        if (this.rank.equals(JACK) || this.rank.equalsIgnoreCase("Jack")){
            intRank = INT_JACK;
        }else if (this.rank.equals(QUEEN)|| this.rank.equalsIgnoreCase("Queen")){
            intRank = INT_QUEEN;
        }else if (this.rank.equals(KING)|| this.rank.equalsIgnoreCase("King")){
            intRank = INT_KING;
        }else if (this.rank.equals(ACE)|| this.rank.equalsIgnoreCase("Ace")) {
            intRank = INT_ACE;
        }

        return intRank;
    }

    private String strRank(){
        String stringRank = "";

        String[] wholeRanks = {"two","three","four", "five", "six", "seven",
                "eight", "nine", "ten"};
        String[] wholeStrRanks = {"2","3","4","5","6","7","8","9","10"};

        if (this.rank.equals(JACK)){
            stringRank = "Jack";
        }else if (this.rank.equals(QUEEN)){
            stringRank = "Queen";
        }else if (this.rank.equals(KING)){
            stringRank = "King";
        }else if (this.rank.equals(ACE)) {
            stringRank = "Ace";
        }else{
            stringRank = this.rank;
        }

        for(int i = 0; i < wholeRanks.length; i++){
            if(this.rank.equalsIgnoreCase(wholeRanks[i])){
                stringRank = wholeStrRanks[i];
            }
        }

        return stringRank;
    }
    private String strSuit(){
        String stringSuit = "";

        if (this.suit.equals(SPADES)){
            stringSuit = "Spades";
        }else if (this.suit.equals(HEARTS)){
            stringSuit = "Hearts";
        }else if (this.suit.equals(CLUBS)){
            stringSuit = "Clubs";
        }else if (this.suit.equals(DIAMONDS)){
            stringSuit = "Diamonds";
        }else{
            stringSuit = this.suit;
        }
        return stringSuit;
    }

    public String toString(){
        String stringRank = "";
        String stringSuit = "";

        stringRank = strRank();
        stringSuit = strSuit();

        return stringRank + " of " + stringSuit;
    }
}


