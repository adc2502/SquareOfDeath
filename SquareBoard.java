import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class SquareBoard {

    public Card[][] board;
    public Integer[][] boardPileCounter;
    public Queue<Card> deck;
    public boolean isGameOver;
    public Player ollie;
    public Player patrick;

    public static void main(String[] args) {
        System.out.println("Starting Game of Square:");

        int patrickTotalDrinks = 0;
        int ollieTotalDrinks = 0;

        int simulationCount = 1;

        for (int i = 0; i < simulationCount; i++) {
            SquareBoard squareBoard = new SquareBoard();

            squareBoard.playUntilThreeCorrect(true);

            patrickTotalDrinks += squareBoard.patrick.getDrinks();
            ollieTotalDrinks += squareBoard.ollie.getDrinks();

            for (int l = 0; l < 3; l++) {
                for (int j = 0; j < 3; j++) {
                    System.out.println("[" + l + "][" + j + "] Pile: " + squareBoard.boardPileCounter[l][j]);
                }
            }
        }

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        System.out.println("Drinks on average over " + simulationCount + " games:");
        System.out.println();
        System.out.println("Patrick: " + patrickTotalDrinks/simulationCount);
        System.out.println("Ollie: " + ollieTotalDrinks/simulationCount);

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();




    }

    public void playUntilThreeCorrect(boolean isOllieTurn) {
        int correct = 0;
        if (this.isGameOver) return;
        System.out.println();
        System.out.println();

        while (correct < 3) {
            Card choice = makeBestBoardDecision(this.getBoard()); // normal strategy

            if (isOllieTurn && correct < 2) { // Ollie's strategy
                choice = makeSecondBestBoardDecision(this.getBoard());
            }

            if (this.deck.size() == 0) {
                System.out.println("Game Over");
                this.isGameOver = true;
                return;
            }


            boolean chooseHigher = chooseHigher(choice.getNumber());
            Card dealtCard = dealCard(this.deck);
            this.boardPileCounter[choice.getxBoard()][choice.getyBoard()]++;
            int pileCount = this.boardPileCounter[choice.getxBoard()][choice.getyBoard()];
            System.out.println("Card chosen: " + choice.getCardId());
            if (chooseHigher) {
                System.out.println("Selected: Higher");
            } else {
                System.out.println("Selected: Lower");
                }
            System.out.println("Card Dealt: " + dealtCard.getCardId());
            if (chooseHigher && dealtCard.getNumber() > choice.getNumber()) {
                System.out.println("Correct Guess");
                correct++;
                System.out.println(correct + " in a row");
            } else if (!chooseHigher && dealtCard.getNumber() < choice.getNumber()) {
                System.out.println("Correct Guess");
                correct++;
                System.out.println(correct + " in a row");
            } else {
                correct = 0;
                if (isOllieTurn) {
                    this.ollie.addDrink(pileCount);
                    System.out.println("Wrong Guess");
                    System.out.println("Pile count: " + pileCount);
                    System.out.println("Ollie total: " + this.ollie.getDrinks());
                } else {
                    this.patrick.addDrink(pileCount);
                    System.out.println("Wrong Guess");
                    System.out.println("Pile count: " + pileCount);
                    System.out.println("Patrick total: " + this.patrick.getDrinks());
                }
            }

            this.board[choice.getxBoard()][choice.getyBoard()] = dealtCard;
            System.out.println();
            System.out.println();
        }

//        if (isOllieTurn) { // play one round
//            this.ollie.setHasFinished(true);
//        } else {
//            this.ollie.setHasFinished(true);
//        }
//        if (isOllieTurn & !this.patrick.isHasFinished()) {
//            System.out.println("Patrick's turn");
//            playUntilThreeCorrect(false);
//        } else if (!this.ollie.isHasFinished()){
//            System.out.println("Ollie's turn");
//            playUntilThreeCorrect(true);
//        }

        if (isOllieTurn) {
            System.out.println("Patrick's turn");
            playUntilThreeCorrect(false);
        } else {
            System.out.println("Ollie's turn");
            playUntilThreeCorrect(true);
        }

    }

    public Card makeBestBoardDecision(Card[][] board) {
        int currentBestDiff = 0;
        Card bestCard = board[0][0];
        bestCard.setxBoard(0);
        bestCard.setyBoard(0);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int checkCardDiff = getDifference(board[i][j].getNumber());

                if (checkCardDiff >= currentBestDiff) {
                    bestCard = board[i][j];
                    bestCard.setxBoard(i);
                    bestCard.setyBoard(j);
                    currentBestDiff = checkCardDiff;

                }
            }
        }
        return bestCard;
    }

    public int getDifference(int cardNumber) {
        int diff = cardNumber - 7;

        if (diff < 0) {
            diff = diff * -1;
        }
        return diff;
    }

    public Card makeSecondBestBoardDecision(Card[][] board) {
        int currentBestDiff;
        Card bestCard;

        int currentSecondBestDiff;
        Card secondBestCard;

        if (getDifference(board[0][0].getNumber()) > getDifference(board[1][1].getNumber())) {
            bestCard = board[0][0];
            bestCard.setxBoard(0);
            bestCard.setyBoard(0);

            secondBestCard = board[1][1];
            secondBestCard.setxBoard(1);
            secondBestCard.setyBoard(1);
            currentBestDiff = getDifference(board[0][0].getNumber());
            currentSecondBestDiff = getDifference(board[1][1].getNumber());
        } else {
            bestCard = board[1][1];
            bestCard.setxBoard(1);
            bestCard.setyBoard(1);

            secondBestCard = board[0][0];
            secondBestCard.setxBoard(0);
            secondBestCard.setyBoard(0);

            currentBestDiff = getDifference(board[1][1].getNumber());
            currentSecondBestDiff = getDifference(board[0][0].getNumber());
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int checkCardDiff = getDifference(board[i][j].getNumber());

                if (checkCardDiff > currentBestDiff) {
                    bestCard = board[i][j];
                    bestCard.setxBoard(i);
                    bestCard.setyBoard(j);
                    currentBestDiff = checkCardDiff;
                    secondBestCard = bestCard;
                } else if (checkCardDiff > currentSecondBestDiff) {
                    secondBestCard = board[i][j];
                    secondBestCard.setxBoard(i);
                    secondBestCard.setyBoard(j);
                    currentSecondBestDiff = checkCardDiff;

                }
            }
        }
        return secondBestCard;
    }

    public Card dealCard(Queue<Card> deck) {
        return deck.remove();
    }

    public void dealInitialBoard() {
        System.out.println("Dealing the board");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.board[i][j] = this.getDeck().remove();
                this.board[i][j].setxBoard(i);
                this.board[i][j].setxBoard(j);
                this.boardPileCounter[i][j] = 1;
            }
        }
        System.out.println("Board dealt");
    }

    public static Card[] shuffleDeck(Card[] board) {
        System.out.println("Shuffling the deck");
        Random random = new Random();

        for (int i = board.length-1; i > 0; i--) {

            // Pick a random index from 0 to i
            int j = random.nextInt(i+1);

            // Swap array[i] with the element at random index
            Card temp = board[i];
            board[i] = board[j];
            board[j] = temp;
        }
        return board;
    }

    public SquareBoard() {
        isGameOver = false;

        this.boardPileCounter = new Integer[3][3];

        Card[] deck = createDeck();
        deck = shuffleDeck(deck);
        this.deck = new LinkedList<>(Arrays.asList(deck));

        this.board = new Card[3][3];
        dealInitialBoard();

        this.ollie = new Player();
        this.patrick = new Player();
    }

    public static boolean chooseHigher(int topCard) {
        if (topCard < 7) {
            return true;
        } else if (topCard > 7) {
            return false;
        } else {
            Random rand = new Random();
            return rand.nextBoolean();
        }
    }

    public Card[] createDeck() {
        System.out.println("Creating deck of cards");
        Card[] deck = new Card[52];
        int counter = 0;
        for (int suit = 1; suit < 5; suit++) {
            for (int cardInt = 1; cardInt < 14; cardInt++) {
                Card card = new Card(suit, cardInt);
                deck[counter] = card;
                //System.out.println("Card created: " + card.getCardId());
                counter++;
            }
        }
        return deck;
    }

    public Card[][] getBoard() {
        return board;
    }

    public void setBoard(Card[][] board) {
        this.board = board;
    }

    public Integer[][] getBoardPileCounter() {
        return boardPileCounter;
    }

    public void setBoardPileCounter(Integer[][] boardPileCounter) {
        this.boardPileCounter = boardPileCounter;
    }

    public Queue<Card> getDeck() {
        return deck;
    }

    public void setDeck(Queue<Card> deck) {
        this.deck = deck;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }

    public Player getOllie() {
        return ollie;
    }

    public void setOllie(Player ollie) {
        this.ollie = ollie;
    }

    public Player getPatrick() {
        return patrick;
    }

    public void setPatrick(Player patrick) {
        this.patrick = patrick;
    }
}
