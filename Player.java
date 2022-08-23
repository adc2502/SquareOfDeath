public class Player {

    public Integer drinks;
    public boolean hasFinished;

    public Player() {
        this.drinks = 0;
    }

    public void addDrink(int pileCount) {
        this.drinks = this.getDrinks() + pileCount;
    }

    public Integer getDrinks() {
        return drinks;
    }

    public void setDrinks(Integer drinks) {
        this.drinks = drinks;
    }

    public boolean isHasFinished() {
        return hasFinished;
    }

    public void setHasFinished(boolean hasFinished) {
        this.hasFinished = hasFinished;
    }
}
