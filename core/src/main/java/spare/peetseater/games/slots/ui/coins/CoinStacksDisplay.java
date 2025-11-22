package spare.peetseater.games.slots.ui.coins;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class CoinStacksDisplay {
    private final Texture coinTexture;
    private final Vector2 xRange;
    private final Vector2 yRange;
    private static final float coinHeight = 0.2f;
    public List<CoinStack> stacks;
    public List<FallingCoin> currentlyFalling;

    public CoinStacksDisplay(Texture coinTexture, Vector2 xRange, Vector2 yRange) {
        this.coinTexture = coinTexture;
        this.xRange = xRange;
        this.yRange = yRange;
        this.stacks = new LinkedList<>();
        currentlyFalling = new LinkedList<>();
        float xStep = 0.5f;
        assert(xRange.x < xRange.y);
        assert(yRange.x < yRange.y);
        for (float x = xRange.x; x < xRange.y; x+= xStep) {
            float yStart = yRange.x; // we're just treating the vector2 as a tuple
            stacks.add(new CoinStack(coinTexture, x, yStart, coinHeight));
        }
        assert(!stacks.isEmpty());
    }

    public void addCoins(int numberOfCoins) {
        for (int i = 0; i < numberOfCoins; i++) {
            CoinStack stack = stacks.get(MathUtils.random(0, stacks.size() - 1));
            currentlyFalling.add(new FallingCoin(stack, coinTexture, coinHeight));
//            stack.addCoin();
        }
    }

    public void removeCoins(int coinsToTake) {
        for (int i = 0; i < coinsToTake; i++) {
            // Note that this likely will get inefficient in the long term.
            // We will deal with it another time.
            List<CoinStack> stacksWithCoins = stacks.stream().filter(CoinStack::hasCoins).collect(Collectors.toList());
            if (stacksWithCoins.isEmpty()) {
                return;
            }
            CoinStack stack = stacksWithCoins.get(MathUtils.random(0, stacksWithCoins.size() - 1));
            stack.subtractCoin();
        }
    }

    public void draw(SpriteBatch batch) {
        for (CoinStack stack : stacks) {
            stack.draw(batch);
        }
        for (FallingCoin coin : currentlyFalling) {
            coin.draw(batch);
        }
    }

    public void update(float delta) {
        Iterator<FallingCoin> iter = currentlyFalling.iterator();
        for (FallingCoin coin = iter.next(); iter.hasNext(); coin = iter.next()) {
            coin.update(delta);
            if (coin.readyToBeRemoved()) {
                iter.remove();
            }
        }
    }

}
