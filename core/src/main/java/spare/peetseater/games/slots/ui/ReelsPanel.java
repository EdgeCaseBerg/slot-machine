package spare.peetseater.games.slots.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import spare.peetseater.games.slots.core.SlotMachine;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ReelsPanel {
    private SlotMachine slotMachine;
    private ReelColumn firstReel;
    private ReelColumn secondReel;
    private ReelColumn thirdReel;

    Texture machineMask;
    Optional<TimedAccumulator> maybeSpin;
    List<ReelsSubscriber> subscribers;

    public ReelsPanel(
        SlotMachine slotMachine,
        Map<String, Texture> symbolNameToTexture,
        Texture machineMask
    ) {
        Vector2 reelWindowSize = new Vector2(4, 12);
        float reelBottomLeftY = 3;
        this.slotMachine = slotMachine;
        this.machineMask = machineMask;
        this.firstReel = new ReelColumn(
            slotMachine.getFirstReel(),
            new Vector2(8, reelBottomLeftY),
            reelWindowSize,
            symbolNameToTexture
        );
        this.secondReel = new ReelColumn(
            slotMachine.getSecondReel(),
            new Vector2(14, reelBottomLeftY),
            reelWindowSize,
            symbolNameToTexture
        );
        this.thirdReel = new ReelColumn(
            slotMachine.getThirdReel(),
            new Vector2(20, reelBottomLeftY),
            reelWindowSize,
            symbolNameToTexture
        );
        this.maybeSpin = Optional.empty();
        this.subscribers = new LinkedList<>();
    }

    public void addSubscriber(ReelsSubscriber reelsSubscriber) {
        this.subscribers.add(reelsSubscriber);
    }

    public void update(float delta) {
        maybeSpin.ifPresent((spin) -> {
            spin.update(delta);
        });

        firstReel.update(delta);
        secondReel.update(delta);
        thirdReel.update(delta);

        if (maybeSpin.isPresent() && maybeSpin.get().isDone()) {
            stopSpinning();
        }
    }

    public void stopSpinning() {
        // return time to stop from each, then take max and accum timer on spin complete
        firstReel.stopReel();
        secondReel.stopReel();
        thirdReel.stopReel();
        maybeSpin = Optional.empty();
        for (ReelsSubscriber subscriber : subscribers) {
            subscriber.onSpinComplete();
        }
    }

    public void startSpinning() {
        maybeSpin = Optional.of(new TimedAccumulator(3f));
        slotMachine.spin();
        firstReel.startSpinningReel();
        secondReel.startSpinningReel();
        thirdReel.startSpinningReel();
    }

    public void draw(SpriteBatch batch) {
        firstReel.draw(batch);
        secondReel.draw(batch);
        thirdReel.draw(batch);
        batch.draw(machineMask, 0, 0, 32, 18);
    }
}
