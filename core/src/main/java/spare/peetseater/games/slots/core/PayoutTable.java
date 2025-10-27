package spare.peetseater.games.slots.core;

import java.util.Arrays;
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
        if (payoutTable.containsKey(key)) {
            return payoutTable.get(key);
        }

        String[] row = new String[]{firstSymbol, secondSymbol, thirdSymbol};
        boolean allSevens = Arrays.stream(row).allMatch(symbolNameMap::isSeven);
        if (allSevens) {
            payoutTable.put(key, 80);
            return payoutTable.get(key);
        }

        boolean redWhiteAndBlue = symbolNameMap.isRed(firstSymbol) && symbolNameMap.isWhite(secondSymbol) && symbolNameMap.isBlue(thirdSymbol);
        if (redWhiteAndBlue) {
            payoutTable.put(key, 20);
            return payoutTable.get(key);
        }

        boolean allBars = Arrays.stream(row).allMatch(symbolNameMap::isBar);
        if (allBars) {
            payoutTable.put(key, 5);
            return payoutTable.get(key);
        }

        boolean allReds = Arrays.stream(row).allMatch(symbolNameMap::isRed);
        if (allReds) {
            payoutTable.put(key, 2);
            return payoutTable.get(key);
        }

        boolean allWhite = Arrays.stream(row).allMatch(symbolNameMap::isWhite);
        if (allWhite) {
            payoutTable.put(key, 2);
            return payoutTable.get(key);
        }

        boolean allBlue = Arrays.stream(row).allMatch(symbolNameMap::isBlue);
        if (allBlue) {
            payoutTable.put(key, 2);
            return payoutTable.get(key);
        }

        payoutTable.put(key, 0);
        return payoutTable.get(key);
    }

    protected void loadPayoutTable(SymbolNameMap symbolNameMap) {
        Map<String, Integer> payoutTable = new Hashtable<>();
        payoutTable.put(
            keyFor(symbolNameMap.getRedSeven(), symbolNameMap.getWhiteSeven(), symbolNameMap.getBlueSeven()), 2400
        );
        payoutTable.put(
            keyFor(symbolNameMap.getRedSeven(), symbolNameMap.getRedSeven(), symbolNameMap.getRedSeven()), 1200
        );
        payoutTable.put(
            keyFor(symbolNameMap.getWhiteSeven(), symbolNameMap.getWhiteSeven(), symbolNameMap.getWhiteSeven()), 200
        );
        payoutTable.put(
            keyFor(symbolNameMap.getBlueSeven(), symbolNameMap.getBlueSeven(), symbolNameMap.getBlueSeven()), 150
        );
        payoutTable.put(
            keyFor(symbolNameMap.getOneBar(), symbolNameMap.getTwoBar(), symbolNameMap.getThreeBar()), 50
        );
        payoutTable.put(
            keyFor(symbolNameMap.getThreeBar(), symbolNameMap.getThreeBar(), symbolNameMap.getThreeBar()), 40
        );
        payoutTable.put(
            keyFor(symbolNameMap.getTwoBar(), symbolNameMap.getTwoBar(), symbolNameMap.getTwoBar()), 25
        );
        payoutTable.put(
            keyFor(symbolNameMap.getOneBar(), symbolNameMap.getOneBar(), symbolNameMap.getOneBar()), 10
        );
        payoutTable.put(
            keyFor(symbolNameMap.getBlank(), symbolNameMap.getBlank(), symbolNameMap.getBlank()), 1
        );
        this.payoutTable = payoutTable;
    }

    protected String keyFor(String reelOne, String reelTwo, String reelThree) {
        return reelOne + reelTwo + reelThree;
    }
}
