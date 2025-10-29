package spare.peetseater.games.slots.core;

public class SlotMachine {

    private final Reel first;
    private final Reel second;
    private final Reel third;

    private int[] currentReelIndicies;
    private int[] spunReelIndicices;

    private final PayoutTable payoutTable;

    public SlotMachine(SymbolNameMap symbolNameMap) {
        first = new FirstReel(symbolNameMap);
        second = new SecondReel(symbolNameMap);
        third = new ThirdReel(symbolNameMap);
        currentReelIndicies = new int[]{
            first.getIdx(),
            second.getIdx(),
            third.getIdx()
        };
        spunReelIndicices = new int[]{
            first.getIdx(),
            second.getIdx(),
            third.getIdx()
        };
        payoutTable = new PayoutTable(symbolNameMap);
    }

    public void spin() {
        int firstIdx = first.spin();
        int secondIdx = second.spin();
        int thirdIdx = third.spin();
        spunReelIndicices[0] = firstIdx;
        spunReelIndicices[1] = secondIdx;
        spunReelIndicices[2] = thirdIdx;
    }

    public int payout() {
        // Transfer the target to the current for bookkeeping
        currentReelIndicies[0] = spunReelIndicices[0];
        currentReelIndicies[1] = spunReelIndicices[1];
        currentReelIndicies[2] = spunReelIndicices[2];

        return payoutTable.payoutFor(
            first.getCurrentSymbol(),
            second.getCurrentSymbol(),
            third.getCurrentSymbol()
        );
    }

    public Reel getFirstReel() {
        return first;
    }
    public Reel getSecondReel() {
        return second;
    }
    public Reel getThirdReel() {
        return third;
    }
}
