package spare.peetseater.games.slots.core;

public class SymbolNameMap {
    private final String blank;
    private final String oneBar;
    private final String twoBar;
    private final String threeBar;
    private final String blueSeven;
    private final String whiteSeven;
    private final String redSeven;

    public SymbolNameMap(
        String blank,
        String one_bar,
        String two_bar,
        String three_bar,
        String blue_seven,
        String white_seven,
        String red_seven
    ) {
        this.blank = blank;
        this.oneBar = one_bar;
        this.twoBar = two_bar;
        this.threeBar = three_bar;
        this.blueSeven = blue_seven;
        this.whiteSeven = white_seven;
        this.redSeven = red_seven;
    }

    public String getBlank() {
        return blank;
    }

    public String getOneBar() {
        return oneBar;
    }

    public String getTwoBar() {
        return twoBar;
    }

    public String getThreeBar() {
        return threeBar;
    }

    public String getBlueSeven() {
        return blueSeven;
    }

    public String getWhiteSeven() {
        return whiteSeven;
    }

    public String getRedSeven() {
        return redSeven;
    }
}
