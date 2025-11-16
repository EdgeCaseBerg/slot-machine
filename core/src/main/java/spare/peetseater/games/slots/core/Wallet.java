package spare.peetseater.games.slots.core;

public class Wallet {
    private int funds;

    public Wallet(int initial) {
        funds = initial;
    }

    public void awardAmount(int toAdd) {
        funds += toAdd;
    }

    public boolean hasEnoughToBet(int amount) {
        return funds >= amount;
    }

    public boolean isBroke() {
        return funds <= 0;
    }

    public void subtractAmount(int amount) {
        funds -= amount;
        // Unlike real life. There is no debt here.
        if (funds < 0) {
            funds = 0;
        }
    }

    public int getFunds() {
        return funds;
    }
}
