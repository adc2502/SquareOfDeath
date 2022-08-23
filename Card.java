public class Card {

    public String cardId;
    public int number;
    public Suits suit;
    public int xBoard;
    public int yBoard;


    public enum Suits {
        SPADES, // 1
        HEARTS, // 2
        DIAMONDS, // 3
        CLUBS; // 4
    }

    public Card(int suitInt, int number) {
        this.suit = intToSuit(suitInt);
        this.number = number;
        this.cardId = number + "_" + intToSuit(suitInt).name();
        this.xBoard = -1;
        this.yBoard = -1;
    }

    public Suits intToSuit(int number) {
        if (number == 1) {
            return Suits.SPADES;

        } else if (number == 2) {
            return Suits.HEARTS;

        } else if (number == 3) {
            return Suits.DIAMONDS;

        } else if (number == 4) {
            return Suits.CLUBS;
        }

        System.out.println("Int to suit failed");

        return null;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCard(String cardId) {
        this.cardId = cardId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Suits getSuit() {
        return suit;
    }

    public void setSuite(Suits suit) {
        this.suit = suit;
    }

    public int getxBoard() {
        return xBoard;
    }

    public void setxBoard(int xBoard) {
        this.xBoard = xBoard;
    }

    public int getyBoard() {
        return yBoard;
    }

    public void setyBoard(int yBoard) {
        this.yBoard = yBoard;
    }

}
