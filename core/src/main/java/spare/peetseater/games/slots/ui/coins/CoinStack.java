package spare.peetseater.games.slots.ui.coins;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class CoinStack {
    private final Texture coinTexture;
    private float topPosition;
    private final float coinHeight;
    private final float x;
    private final float y;

    public CoinStack(Texture coinTexture, float x, float y, float coinHeight) {
        this.x = x;
        this.y = y;
        this.topPosition = y;
        this.coinHeight = coinHeight;
        this.coinTexture = coinTexture;
    }

    public boolean hasCoins() {
        return this.y != topPosition;
    }

    public void addCoin() {
        // TODO start coins at the top of y range and make them fall to
        // the appropriate stack for a fun visual
        this.topPosition += coinHeight;
    }

    public void subtractCoin() {
        this.topPosition -= coinHeight;
        if (this.topPosition < y) {
            this.topPosition = y;
        }
    }

    public void draw(SpriteBatch batch) {
        for (float i = y; i < topPosition; i += coinHeight) {
            batch.draw(coinTexture, x, i, 1, coinHeight);
        }
    }

    public Vector2 getPosition() {
        return new Vector2(x, topPosition);
    }
}
