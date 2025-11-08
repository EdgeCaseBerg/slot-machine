package spare.peetseater.games.slots.ui.behavior;

import spare.peetseater.games.slots.core.Reel;

import java.util.LinkedList;
import java.util.List;

public class ReelIterator {
    private final Reel reel;
    private final List<String> symbolsLoop;
    private int nextIdx;

    public ReelIterator(Reel reel) {
        this.reel = reel;
        assert(reel.getSize() != 0);
        this.symbolsLoop = new LinkedList<String>();
        this.nextIdx = 0;
        String symbol = null;
        for (int i = 0; i < reel.getSize(); i++) {
            String nextSymbol = reel.getSymbol(i);
            if (!nextSymbol.equals(symbol)) {
                symbol = nextSymbol;
                symbolsLoop.add(nextSymbol);
            }
        }
    }

    public String peek() {
        return symbolsLoop.get(nextIdx);
    }
    public String next() {
        String symbol = symbolsLoop.get(nextIdx);
        nextIdx++;
        if (nextIdx >= symbolsLoop.size()) {
            nextIdx = 0;
        }
        return symbol;
    }
}
