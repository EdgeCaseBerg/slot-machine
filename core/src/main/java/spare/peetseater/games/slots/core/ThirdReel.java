package spare.peetseater.games.slots.core;

public class ThirdReel extends Reel {
    public ThirdReel(SymbolNameMap symbolNames) {
        super(symbolNames);
    }

    @Override
    protected void loadSymbolsToReel(SymbolNameMap symbolNames) {
        symbols[0] = symbolNames.getTwoBar();
        symbols[1] = symbolNames.getTwoBar();
        symbols[2] = symbolNames.getTwoBar();
        symbols[3] = symbolNames.getBlank();
        symbols[4] = symbolNames.getBlank();
        symbols[5] = symbolNames.getThreeBar();
        symbols[6] = symbolNames.getBlank();
        symbols[7] = symbolNames.getBlank();
        symbols[8] = symbolNames.getBlank();
        symbols[9] = symbolNames.getWhiteSeven();
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
        symbols[22] = symbolNames.getOneBar();
        symbols[23] = symbolNames.getOneBar();
        symbols[24] = symbolNames.getBlank();
        symbols[25] = symbolNames.getBlank();
        symbols[26] = symbolNames.getBlank();
        symbols[27] = symbolNames.getBlueSeven();
        symbols[28] = symbolNames.getBlank();
        symbols[29] = symbolNames.getBlank();
        symbols[30] = symbolNames.getBlank();
        symbols[31] = symbolNames.getTwoBar();
        symbols[32] = symbolNames.getTwoBar();
        symbols[33] = symbolNames.getTwoBar();
        symbols[34] = symbolNames.getBlank();
        symbols[35] = symbolNames.getBlank();
        symbols[36] = symbolNames.getThreeBar();
        symbols[37] = symbolNames.getBlank();
        symbols[38] = symbolNames.getBlank();
        symbols[39] = symbolNames.getBlank();
        symbols[40] = symbolNames.getBlank();
        symbols[41] = symbolNames.getBlank();
        symbols[42] = symbolNames.getRedSeven();
        symbols[43] = symbolNames.getBlank();
        symbols[44] = symbolNames.getBlank();
        symbols[45] = symbolNames.getBlank();
        symbols[46] = symbolNames.getBlank();
        symbols[47] = symbolNames.getBlank();
        symbols[48] = symbolNames.getThreeBar();
        symbols[49] = symbolNames.getThreeBar();
        symbols[50] = symbolNames.getThreeBar();
        symbols[51] = symbolNames.getBlank();
        symbols[52] = symbolNames.getBlank();
        symbols[53] = symbolNames.getTwoBar();
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
