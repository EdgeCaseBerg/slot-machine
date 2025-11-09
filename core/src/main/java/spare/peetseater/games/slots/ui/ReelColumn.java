package spare.peetseater.games.slots.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import spare.peetseater.games.slots.core.Reel;
import spare.peetseater.games.slots.ui.behavior.*;

import java.util.*;

public class ReelColumn {
    private final Reel reel;
    private final Vector2 position;
    private final Vector2 windowSize;
    private final Deque<ReelSymbol> symbols;
    private final Map<String, Texture> symbolToTextureMap;
    private final ReelIterator reelSymbolsToCome;
    private final float reelSize;
    private boolean isSpinning;

    public ReelColumn(Reel reel, Vector2 position, Vector2 windowSize, Map<String, Texture> symbolToTextureMap) {
        this.reel = reel;
        this.reelSymbolsToCome = new ReelIterator(this.reel);
        this.reelSize = 4;
        this.position = position;
        this.windowSize = windowSize;
        this.isSpinning = false;
        this.symbols = new ArrayDeque<>();
        this.symbolToTextureMap = symbolToTextureMap;
        for (int i = 0; i < 4; i++) {
            addNextSymbolToReel(position.y - reelSize + i * reelSize);
        }
    }

    public ReelSymbol addNextSymbolToReel(float startingYPosition) {
        String symbol = reelSymbolsToCome.next();
        ReelSymbol nextReelSymbol = new ReelSymbol(
            position.x, startingYPosition,
            symbolToTextureMap.get(symbol),
            reelSize, reelSize
        );
        if (isSpinning) {
            int speed = 24;
            nextReelSymbol.setPositionBehavior(
                new MovingAtVelocity(nextReelSymbol.getPosition(), speed)
            );
        }
        symbols.add(nextReelSymbol);
        return nextReelSymbol;
    }

    public void draw(SpriteBatch batch) {
        float modulo = symbols.size() * reelSize;
        for (ReelSymbol reelSymbol : symbols) {
            reelSymbol.draw(batch, modulo);
        }
    }

    public void update(float delta) {
        if (!isSpinning) {
            for (ReelSymbol reelSymbol : symbols) {
                reelSymbol.update(delta);
            }
            return;
        }
        // When spinnning we need to deal with removing and adding new elements as they go
        // when we're not spinning, we want to return early because bouncing interpolation
        // could move the symbol outside of the range and trigger the removal code and we
        // don't want that when we're arriving at the stop positions.
        Iterator<ReelSymbol> iter = symbols.descendingIterator();
        float minY = Float.MAX_VALUE;
        for (Iterator<ReelSymbol> it = iter; it.hasNext(); ) {
            ReelSymbol symbol = it.next();
            minY = Math.min(minY, symbol.getPosition().y);
            symbol.update(delta);
            if (symbol.getPosition().y >= position.y + windowSize.y) {
                it.remove();
            }
        }
        if (symbols.size() < 4) {
            ReelSymbol newSymbol = addNextSymbolToReel(minY - reelSize);
            newSymbol.update(delta);
        }
    }

    public void startSpinningReel() {
        isSpinning = true;
        float speed = 24;
        for (ReelSymbol symbol : symbols) {
            symbol.setPositionBehavior(
                new MovingAtVelocity(symbol.getPosition(), speed)
            );
        }
    }

    public void stopReel() {
        isSpinning = false;

        // We'll need to know where to start extending the reel out so that the
        // winning row spins up into place.
        float currentBottommostY = Float.MAX_VALUE;
        Iterator<ReelSymbol> iter = symbols.iterator();
        for (Iterator<ReelSymbol> it = iter; it.hasNext(); ) {
            ReelSymbol symbol = it.next();
            currentBottommostY = Math.min(currentBottommostY, symbol.getPosition().y);
        }

        String symbolToLandOn = reel.getCurrentSymbol();
        Gdx.app.log("STOP ON ", symbolToLandOn);
        String symbolAdded;
        do {
            symbolAdded = reelSymbolsToCome.peek();
            currentBottommostY -= reelSize;
            addNextSymbolToReel(currentBottommostY);
        } while (!symbolToLandOn.equals(symbolAdded));

        // The last Element added to the symbols is the winner for the reel this spin.
        // Since we want it to land in the middle. Add one more.
        currentBottommostY -= reelSize;
        addNextSymbolToReel(currentBottommostY);

        // Lastly, update all the new symbols to flow to the right place accordingly,
        // they should all move at the same rate to not look weird, so we compute the
        // time for each based on their position in the queue.
        iter = symbols.descendingIterator();
        int i = 0;
        float time = 1f;
        float stopReelWithinSeconds = time * symbols.size();
        for (Iterator<ReelSymbol> it = iter; it.hasNext(); i++) {
            float stoppingPosition = position.y + i * reelSize;
            ReelSymbol symbol = iter.next();
            symbol.setPositionBehavior(
                new ArrivingAtPosition(
                    symbol.getPosition(),
                    new Vector2(position.x, stoppingPosition),
                    stopReelWithinSeconds
                )
            );
        }
    }
}
