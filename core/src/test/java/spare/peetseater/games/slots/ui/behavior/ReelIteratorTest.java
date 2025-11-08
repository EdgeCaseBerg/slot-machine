package spare.peetseater.games.slots.ui.behavior;

import org.junit.jupiter.api.Test;
import spare.peetseater.games.slots.core.FirstReel;
import spare.peetseater.games.slots.core.Reel;
import spare.peetseater.games.slots.core.SymbolNameMap;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReelIteratorTest {

    private SymbolNameMap symbolNameMap;
    private List<String> expectedCondensedReel;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        symbolNameMap = new SymbolNameMap(
            "_",
            "br1",
            "bw2",
            "bb3",
            "7b",
            "7w",
            "7r"
        );

        expectedCondensedReel = new ArrayList<>();
        expectedCondensedReel.add(symbolNameMap.getTwoBar());
        expectedCondensedReel.add(symbolNameMap.getBlank());
        expectedCondensedReel.add(symbolNameMap.getThreeBar());
        expectedCondensedReel.add(symbolNameMap.getBlank());
        expectedCondensedReel.add(symbolNameMap.getWhiteSeven());
        expectedCondensedReel.add(symbolNameMap.getBlank());
        expectedCondensedReel.add(symbolNameMap.getOneBar());
        expectedCondensedReel.add(symbolNameMap.getBlank());
        expectedCondensedReel.add(symbolNameMap.getBlueSeven());
        expectedCondensedReel.add(symbolNameMap.getBlank());
        expectedCondensedReel.add(symbolNameMap.getTwoBar());
        expectedCondensedReel.add(symbolNameMap.getBlank());
        expectedCondensedReel.add(symbolNameMap.getThreeBar());
        expectedCondensedReel.add(symbolNameMap.getBlank());
        expectedCondensedReel.add(symbolNameMap.getRedSeven());
        expectedCondensedReel.add(symbolNameMap.getBlank());
        expectedCondensedReel.add(symbolNameMap.getThreeBar());
        expectedCondensedReel.add(symbolNameMap.getBlank());
        expectedCondensedReel.add(symbolNameMap.getTwoBar());
        expectedCondensedReel.add(symbolNameMap.getBlank());
        expectedCondensedReel.add(symbolNameMap.getOneBar());
        expectedCondensedReel.add(symbolNameMap.getBlank());
    }

    @Test
    public void testCircularNature() {
        Reel reel = new FirstReel(symbolNameMap);
        ReelIterator iter = new ReelIterator(reel);
        int condensedSize = expectedCondensedReel.size();
        String[] iteratedTwiceResult = new String[condensedSize * 2];
        for (int i = 0; i < condensedSize * 2; i++) {
            iteratedTwiceResult[i] = iter.next();
        }
        for (int i = 0; i < condensedSize; i++) {
            assertSame(
                iteratedTwiceResult[i],
                iteratedTwiceResult[i + condensedSize],
                String.format("Expected %s to be %s", iteratedTwiceResult[i], iteratedTwiceResult[i + condensedSize])
            );
        }
    }

    @Test
    public void peakingDoesNotAdvanceInternalCursor() {
        Reel reel = new FirstReel(symbolNameMap);
        ReelIterator iter = new ReelIterator(reel);
        String nextPeak = iter.peek();
        String expectedPeak = expectedCondensedReel.get(0);
        assertSame(nextPeak, expectedPeak);
        String secondPeak = iter.peek();
        expectedPeak = expectedCondensedReel.get(0);
        String notExpected = expectedCondensedReel.get(1);
        assertSame(secondPeak, expectedPeak);
        assertNotEquals(notExpected, secondPeak);
        assertNotEquals(notExpected, expectedPeak);
    }
}
