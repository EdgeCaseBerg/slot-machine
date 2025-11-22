package spare.peetseater.games.slots.ui.coins;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import spare.peetseater.games.slots.ui.behavior.ArrivingAtPosition;
import spare.peetseater.games.slots.ui.behavior.PositionBehavior;

public class FallingCoin {
    private final Texture coinTexture;
    private final float coinHeight;
    private final Vector2 end;
    private final CoinStack stack;
    PositionBehavior positionBehavior;
    private boolean readyToBeRemoved;

    public FallingCoin(CoinStack stackToAddToWhenFinishedFalling, Texture coinTexture, float coinHeight) {
        this.coinTexture = coinTexture;
        this.coinHeight = coinHeight;
        this.stack = stackToAddToWhenFinishedFalling;
        this.readyToBeRemoved = false;
        end = stackToAddToWhenFinishedFalling.getPosition();
        Vector2 start = new Vector2(end.x, 20); // TODO take this in or something
        float within = MathUtils.random(3,5);
        positionBehavior = new ArrivingAtPosition(start, end, within);
    }

    public void draw(SpriteBatch batch) {
        Vector2 p = positionBehavior.getPosition();
        batch.draw(coinTexture, p.x, p.y, 1, coinHeight);
    }

    public void update(float delta) {
        positionBehavior.update(delta);
        if (positionBehavior.getPosition().equals(end) && !readyToBeRemoved) {
            stack.addCoin();
            readyToBeRemoved = true;
        }
    }

    public boolean readyToBeRemoved() {
        return readyToBeRemoved;
    }
}
