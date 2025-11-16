package spare.peetseater.games.slots.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class CoinStacksDisplay {
    private final Texture coinTexture;
    private final Vector2 xRange;
    private final Vector2 yRange;
    private static final float coinHeight = 0.2f;
    public List<CoinStack> stacks;

    public CoinStacksDisplay(Texture coinTexture, Vector2 xRange, Vector2 yRange) {
        this.coinTexture = coinTexture;
        this.xRange = xRange;
        this.yRange = yRange;
        this.stacks = new LinkedList<>();
        float xStep = 0.5f;
        assert(xRange.x < xRange.y);
        assert(yRange.x < yRange.y);
        for (float x = xRange.x; x < xRange.y; x+= xStep) {
            stacks.add(new CoinStack(x, yRange.x));
        }
    }

    public void addCoins(int numberOfCoins) {
        for (int i = 0; i < numberOfCoins; i++) {
            CoinStack stack = stacks.get(MathUtils.random(0, stacks.size() - 1));
            // TODO start coins at the top of y range and make them fall to
            // the appropriate stack for a fun visual
            stack.addCoin();
        }
    }

    public void removeCoins(int coinsToTake) {
        for (int i = 0; i < coinsToTake; i++) {
            // Note that this likely will get inefficient in the long term.
            // We will deal with it another time.
            List<CoinStack> stacksWithCoins = stacks.stream().filter(CoinStack::hasCoins).collect(Collectors.toList());
            CoinStack stack = stacksWithCoins.get(MathUtils.random(0, stacksWithCoins.size() - 1));
            stack.subtractCoin();
        }
    }

    public void draw(SpriteBatch batch) {
        for (CoinStack stack : stacks) {
            stack.draw(batch);
        }
    }

    protected class CoinStack {
        private float topPosition;
        private final float x;
        private final float y;

        public CoinStack(float x, float y) {
            this.x = x;
            this.y = y;
            this.topPosition = y;
        }

        public boolean hasCoins() {
            return this.y != topPosition;
        }

        public void addCoin() {
            this.topPosition += coinHeight;
        }

        public void subtractCoin() {
            this.topPosition -= coinHeight;
            if (this.topPosition < y) {
                this.topPosition = y;
            }
        }

        public void draw(SpriteBatch batch) {
            for (float i = y; i < topPosition; i+= coinHeight) {
                batch.draw(coinTexture, x, i, 1, coinHeight);
            }
        }
    }

}
