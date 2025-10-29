package spare.peetseater.games.slots.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SlotMachineTest {

    SlotMachine slotMachine;
    SymbolNameMap symbolNameMap;

    @BeforeEach
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
        slotMachine = new SlotMachine(symbolNameMap);
    }

    @Test
    void payout() {
        int initialAll0Payout = slotMachine.payout();
        // Initial reels are at 2 bar 2 bar 2 bar
        assertEquals(25, initialAll0Payout);
    }
}
