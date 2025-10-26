package spare.peetseater.games.slots.core;

public class SecondReel extends Reel {
    public SecondReel(SymbolNameMap symbolNames) {
        super(symbolNames);
    }

    @Override
    protected void loadSymbolsToReel(SymbolNameMap symbolNames) {
        symbols[0] = symbolNames.getTwoBar();
        symbols[1] = symbolNames.getTwoBar();
        symbols[2] = symbolNames.getBlank();
        symbols[3] = symbolNames.getBlank();
        symbols[4] = symbolNames.getThreeBar();
        symbols[5] = symbolNames.getThreeBar();
        symbols[6] = symbolNames.getBlank();
        symbols[7] = symbolNames.getBlank();
        symbols[8] = symbolNames.getBlank();
        symbols[9] = symbolNames.getWhiteSeven();
        symbols[10] = symbolNames.getBlank();
        symbols[11] = symbolNames.getBlank();
        symbols[12] = symbolNames.getBlank();
        symbols[13] = symbolNames.getOneBar();
        symbols[14] = symbolNames.getOneBar();
        symbols[15] = symbolNames.getOneBar();
        symbols[16] = symbolNames.getOneBar();
        symbols[17] = symbolNames.getBlank();
        symbols[18] = symbolNames.getBlank();
        symbols[19] = symbolNames.getBlank();
        symbols[20] = symbolNames.getBlueSeven();
        symbols[21] = symbolNames.getBlueSeven();
        symbols[22] = symbolNames.getBlueSeven();
        symbols[23] = symbolNames.getBlueSeven();
        symbols[24] = symbolNames.getBlueSeven();
        symbols[25] = symbolNames.getBlueSeven();
        symbols[26] = symbolNames.getBlueSeven();
        symbols[27] = symbolNames.getBlank();
        symbols[28] = symbolNames.getBlank();
        symbols[29] = symbolNames.getBlank();
        symbols[30] = symbolNames.getTwoBar();
        symbols[31] = symbolNames.getTwoBar();
        symbols[32] = symbolNames.getBlank();
        symbols[33] = symbolNames.getBlank();
        symbols[34] = symbolNames.getThreeBar();
        symbols[35] = symbolNames.getThreeBar();
        symbols[36] = symbolNames.getBlank();
        symbols[37] = symbolNames.getBlank();
        symbols[38] = symbolNames.getBlank();
        symbols[39] = symbolNames.getBlank();
        symbols[40] = symbolNames.getBlank();
        symbols[41] = symbolNames.getRedSeven();
        symbols[42] = symbolNames.getRedSeven();
        symbols[43] = symbolNames.getRedSeven();
        symbols[44] = symbolNames.getBlank();
        symbols[45] = symbolNames.getBlank();
        symbols[46] = symbolNames.getBlank();
        symbols[47] = symbolNames.getBlank();
        symbols[48] = symbolNames.getBlank();
        symbols[49] = symbolNames.getThreeBar();
        symbols[50] = symbolNames.getThreeBar();
        symbols[51] = symbolNames.getThreeBar();
        symbols[52] = symbolNames.getBlank();
        symbols[53] = symbolNames.getBlank();
        symbols[54] = symbolNames.getTwoBar();
        symbols[55] = symbolNames.getTwoBar();
        symbols[56] = symbolNames.getBlank();
        symbols[57] = symbolNames.getBlank();
        symbols[58] = symbolNames.getOneBar();
        symbols[59] = symbolNames.getOneBar();
        symbols[60] = symbolNames.getOneBar();
        symbols[61] = symbolNames.getOneBar();
        symbols[62] = symbolNames.getBlank();
        symbols[63] = symbolNames.getBlank();
    }
}
