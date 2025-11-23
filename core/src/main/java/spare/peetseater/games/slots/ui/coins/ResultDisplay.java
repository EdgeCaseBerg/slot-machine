package spare.peetseater.games.slots.ui.coins;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import spare.peetseater.games.slots.ui.NumberRenderer;
import spare.peetseater.games.slots.ui.TimedAccumulator;
import spare.peetseater.games.slots.ui.behavior.PositionBehavior;
import spare.peetseater.games.slots.ui.behavior.StationaryPosition;

public class ResultDisplay {
    private final Texture resultTexture;
    private final NumberRenderer numberRenderer;
    private final int coinsWon;
    private final PositionBehavior position;
    private final TimedAccumulator timer;

    public ResultDisplay(
        Texture resultTexture,
        NumberRenderer numberRenderer,
        int coinsWon,
        Vector2 position,
        float displayFor
    ) {
        this.resultTexture = resultTexture;
        this.numberRenderer = numberRenderer;
        this.coinsWon = coinsWon;
        this.position = new StationaryPosition(position.x, position.y);
        this.timer = new TimedAccumulator(displayFor);
    }

    public void update(float delta) {
        position.update(delta);
        timer.update(delta);
    }

    public void draw(SpriteBatch batch) {
        if (timer.isDone()) {
            return;
        }

        Vector2 p = position.getPosition();
        batch.draw(resultTexture, 4, 3, 24, 15);
        numberRenderer.draw(batch, coinsWon, p.x, p.y);
    }

    public boolean readyToHide() {
        return timer.isDone();
    }
}
