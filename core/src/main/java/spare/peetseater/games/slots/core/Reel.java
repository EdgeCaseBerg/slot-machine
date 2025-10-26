package spare.peetseater.games.slots.core;

import com.badlogic.gdx.math.MathUtils;

public abstract class Reel {
    protected String[] symbols;
    protected int idx;

    public Reel(SymbolNameMap symbolNames) {
        symbols = new String[64];
        idx = 0;
        loadSymbolsToReel(symbolNames);
    }

    public int spin() {
        idx = MathUtils.random(0, symbols.length - 1);
        return idx;
    }

    public int getIdx() {
        return idx;
    }

    public String getSymbol(int position) {
        assert(0 <= position && position < symbols.length);
        return symbols[position];
    }

    protected abstract void loadSymbolsToReel(SymbolNameMap symbolNames);

    public String getCurrentSymbol() {
        return getSymbol(idx);
    }
}
