package spare.peetseater.games.slots.core;

public class FirstReel extends Reel {
    protected String[] symbols;

    public FirstReel(SymbolNameMap symbolNames) {
        super(symbolNames);
    }

    protected void loadSymbolsToReel(SymbolNameMap symbolNames) {
        symbols[0] = symbolNames.getTwoBar();
        symbols[1] = symbolNames.getTwoBar();
        symbols[2] = symbolNames.getTwoBar();
        symbols[3] = symbolNames.getBlank();
        symbols[4] = symbolNames.getBlank();
        symbols[5] = symbolNames.getThreeBar();
        symbols[6] = symbolNames.getThreeBar();
        symbols[7] = symbolNames.getBlank();
        symbols[8] = symbolNames.getBlank();
        symbols[9] = symbolNames.getBlank();
        symbols[10] = symbolNames.getWhiteSeven();
        symbols[11] = symbolNames.getWhiteSeven();
        symbols[12] = symbolNames.getWhiteSeven();
        symbols[13] = symbolNames.getWhiteSeven();
        symbols[14] = symbolNames.getWhiteSeven();
        symbols[15] = symbolNames.getWhiteSeven();
        symbols[16] = symbolNames.getBlank();
        symbols[17] = symbolNames.getBlank();
        symbols[18] = symbolNames.getBlank();
        symbols[19] = symbolNames.getOneBar();
        symbols[20] = symbolNames.getOneBar();
        symbols[21] = symbolNames.getOneBar();
        symbols[22] = symbolNames.getBlank();
        symbols[23] = symbolNames.getBlank();
        symbols[24] = symbolNames.getBlank();
        symbols[25] = symbolNames.getBlueSeven();
        symbols[26] = symbolNames.getBlueSeven();
        symbols[27] = symbolNames.getBlueSeven();
        symbols[28] = symbolNames.getBlueSeven();
        symbols[29] = symbolNames.getBlueSeven();
        symbols[30] = symbolNames.getBlueSeven();
        symbols[31] = symbolNames.getBlank();
        symbols[32] = symbolNames.getBlank();
        symbols[33] = symbolNames.getBlank();
        symbols[34] = symbolNames.getTwoBar();
        symbols[35] = symbolNames.getTwoBar();
        symbols[36] = symbolNames.getBlank();
        symbols[37] = symbolNames.getBlank();
        symbols[38] = symbolNames.getThreeBar();
        symbols[39] = symbolNames.getBlank();
        symbols[40] = symbolNames.getBlank();
        symbols[41] = symbolNames.getBlank();
        symbols[42] = symbolNames.getBlank();
        symbols[43] = symbolNames.getBlank();
        symbols[44] = symbolNames.getRedSeven();
        symbols[45] = symbolNames.getBlank();
        symbols[46] = symbolNames.getBlank();
        symbols[47] = symbolNames.getBlank();
        symbols[48] = symbolNames.getBlank();
        symbols[49] = symbolNames.getBlank();
        symbols[50] = symbolNames.getThreeBar();
        symbols[51] = symbolNames.getThreeBar();
        symbols[52] = symbolNames.getThreeBar();
        symbols[53] = symbolNames.getBlank();
        symbols[54] = symbolNames.getBlank();
        symbols[55] = symbolNames.getTwoBar();
        symbols[56] = symbolNames.getTwoBar();
        symbols[57] = symbolNames.getBlank();
        symbols[58] = symbolNames.getBlank();
        symbols[59] = symbolNames.getOneBar();
        symbols[60] = symbolNames.getOneBar();
        symbols[61] = symbolNames.getOneBar();
        symbols[62] = symbolNames.getBlank();
        symbols[63] = symbolNames.getBlank();
    }
}
