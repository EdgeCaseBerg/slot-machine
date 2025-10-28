package spare.peetseater.games.slots.core;

import com.sun.rowset.internal.Row;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class PayoutTableTest {
    PayoutTable payoutTable;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        payoutTable = new PayoutTable(new SymbolNameMap(
            "_",
            "br1",
            "bw2",
            "bb3",
            "7b",
            "7w",
            "7r"
        ));
    }

    static Stream<RowAndPayout> payoutExpectations() {
        return Stream.of(
            RowAndPayout.caseOf("7r", "7w", "7b", 2400),
            RowAndPayout.caseOf("7r", "7r", "7r", 1200),
            RowAndPayout.caseOf("7w", "7w", "7w", 200),
            RowAndPayout.caseOf("7b", "7b", "7b", 150),
            RowAndPayout.caseOf("7b", "7r", "7w", 80),
            RowAndPayout.caseOf("7w", "7r", "7w", 80),
            RowAndPayout.caseOf("br1", "bw2", "bb3", 50),
            RowAndPayout.caseOf("bb3", "bb3", "bb3", 40),
            RowAndPayout.caseOf("bw2", "bw2", "bw2", 25),
            RowAndPayout.caseOf("br1", "7w", "bb3", 20),
            RowAndPayout.caseOf("br1", "br1", "br1", 10),
            RowAndPayout.caseOf("br1", "bb3", "br1", 5),
            RowAndPayout.caseOf("bb3", "bb3", "br1", 5),
            RowAndPayout.caseOf("7r", "br1", "br1", 2),
            RowAndPayout.caseOf("7w", "bw2", "7w", 2),
            RowAndPayout.caseOf("7b", "bb3", "7b", 2),
            RowAndPayout.caseOf("_", "_", "_", 1),
            RowAndPayout.caseOf("_", "7r", "_", 0),
            RowAndPayout.caseOf("_", "7r", "bw2", 0)
        );
    }

    static class RowAndPayout {
        private final String r1;
        private final String r2;
        private final String r3;
        private final int win;

        public RowAndPayout(String r1, String r2, String r3, int win) {
            this.r1 = r1;
            this.r2 = r2;
            this.r3 = r3;
            this.win = win;
        }

        public static RowAndPayout caseOf(String s1, String s2, String s3, int winnings) {
            return new RowAndPayout(s1, s2, s3, winnings);
        }

        public String toString() {
            return r1 + "|" + r2 + "|" + r3 + " = " + win;
        }
    }

    @ParameterizedTest
    @MethodSource("payoutExpectations")
    void payoutForTest(RowAndPayout rowAndPayout) {
        int amountWon = payoutTable.payoutFor(rowAndPayout.r1, rowAndPayout.r2, rowAndPayout.r3);
        assertEquals(rowAndPayout.win, amountWon, rowAndPayout.toString());
    }
}
