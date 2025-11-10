package spare.peetseater.games.slots.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import spare.peetseater.games.slots.core.SlotMachine;

import java.util.Map;
import java.util.Optional;

public class ReelsPanel {
    private SlotMachine slotMachine;
    private ReelColumn firstReel;
    private ReelColumn secondReel;
    private ReelColumn thirdReel;

    Texture machineMask;
    Optional<TimedAccumulator> maybeSpin;

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
    }

    public void update(float delta) {
        maybeSpin.ifPresent((spin) -> {
            spin.update(delta);
        });

        firstReel.update(delta);
        secondReel.update(delta);
        thirdReel.update(delta);

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            maybeSpin = Optional.of(new TimedAccumulator(3f));
            slotMachine.spin();
            firstReel.startSpinningReel();
            secondReel.startSpinningReel();
            thirdReel.startSpinningReel();
        }

        if (maybeSpin.isPresent() && maybeSpin.get().isDone()) {
            firstReel.stopReel();
            secondReel.stopReel();
            thirdReel.stopReel();
            maybeSpin = Optional.empty();
            int payout = slotMachine.payout();
            Gdx.app.log("PAYOUT", "$" + payout);
        }
    }

    public void draw(SpriteBatch batch) {
        firstReel.draw(batch);
        secondReel.draw(batch);
        thirdReel.draw(batch);
        batch.draw(machineMask, 0, 0, 32, 18);
    }
}
