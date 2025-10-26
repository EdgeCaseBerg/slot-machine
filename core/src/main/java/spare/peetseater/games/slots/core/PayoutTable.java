package spare.peetseater.games.slots.core;

import java.util.Hashtable;
import java.util.Map;

public class PayoutTable {
    private final SymbolNameMap symbolNameMap;
    private Map<String, Integer> payoutTable;

    public PayoutTable(SymbolNameMap symbolNameMap) {
        this.symbolNameMap = symbolNameMap;
        loadPayoutTable(symbolNameMap);
    }

    public int payoutFor(String firstSymbol, String secondSymbol, String thirdSymbol) {
        String key = keyFor(firstSymbol, secondSymbol, thirdSymbol);
        return payoutTable.getOrDefault(key, 0);
    }

    protected void loadPayoutTable(SymbolNameMap symbolNameMap) {
        Map<String, Integer> payoutTable = new Hashtable<>();
        payoutTable.put(
            keyFor(symbolNameMap.getRedSeven(), symbolNameMap.getWhiteSeven(), symbolNameMap.getBlueSeven()), 2400
        );
        // TODO data entry
        this.payoutTable = payoutTable;
    }

    protected String keyFor(String reelOne, String reelTwo, String reelThree) {
        return reelOne + reelTwo + reelThree;
    }
}
