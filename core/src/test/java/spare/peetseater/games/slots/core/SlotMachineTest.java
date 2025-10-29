package spare.peetseater.games.slots.core;

import com.badlogic.gdx.math.MathUtils;
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

    @Test
    void spin() {
        MathUtils.random.setSeed(1);
        slotMachine.spin();
        String spin1Reel1 = slotMachine.getFirstReel().getCurrentSymbol();
        String spin1Reel2 = slotMachine.getSecondReel().getCurrentSymbol();
        String spin1Reel3 = slotMachine.getThirdReel().getCurrentSymbol();
        MathUtils.random.setSeed(1);
        slotMachine.spin();
        String spin2Reel1 = slotMachine.getFirstReel().getCurrentSymbol();
        String spin2Reel2 = slotMachine.getSecondReel().getCurrentSymbol();
        String spin2Reel3 = slotMachine.getThirdReel().getCurrentSymbol();
        assertEquals(spin1Reel1, spin2Reel1);
        assertEquals(spin1Reel2, spin2Reel2);
        assertEquals(spin1Reel3, spin2Reel3);
    }
}

