package spare.peetseater.games.slots.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import spare.peetseater.games.slots.core.Reel;
import spare.peetseater.games.slots.ui.behavior.ArrivingAtPosition;
import spare.peetseater.games.slots.ui.behavior.StationaryPosition;
import spare.peetseater.games.slots.ui.behavior.VerticallySpinningPosition;

import java.util.Map;

public class ReelColumn {
    private final Reel reel;
    private final Vector2 position;
    private final Vector2 windowSize;
    private final ReelSymbol[] symbols;

    public ReelColumn(Reel reel, Vector2 position, Vector2 windowSize, Map<String, Texture> symbolToTextureMap) {
        this.reel = reel;
        this.position = position;
        this.windowSize = windowSize;
        this.symbols = new ReelSymbol[reel.getSize()];
        float reelSize = 4;
        for (int i = 0; i < reel.getSize(); i++) {
            symbols[i] = new ReelSymbol(
                position.x, position.y + i * reelSize,
                symbolToTextureMap.get(reel.getSymbol(i)),
                reelSize, reelSize
            );
        }
    }

    public void draw(SpriteBatch batch) {
        float height = symbols[0].getHeight();
        float modulo = symbols.length * height;
        for (ReelSymbol reelSymbol : symbols) {
            Vector2 symbolLocation = reelSymbol.getPosition();
            if (position.y  < symbolLocation.y + height && symbolLocation.y < position.y + windowSize.y) {
                reelSymbol.draw(batch, modulo);
            }
        }
    }

    public void update(float delta) {
        for (ReelSymbol symbol : symbols) {
            symbol.update(delta);
        }
    }

    public void startSpinningReel() {
        float maxYForSpinning= symbols.length * symbols[0].getHeight();
        float symbolSpinSpeed  = 24;
        for (ReelSymbol symbol : symbols) {
            symbol.setPositionBehavior(
                new VerticallySpinningPosition(
                        symbol.getPosition(),
                        symbolSpinSpeed,
                        new Vector2(-1, maxYForSpinning)
                    )
            );
        }
    }

    public void stopReel() {
        int middleRow = reel.getIdx();
        int bottomRow = (reel.getIdx() - 1) % reel.getSize();
        int topRow = (reel.getIdx() + 1) % reel.getSize();
        for (ReelSymbol symbol : symbols) {
            symbol.setPositionBehavior(new StationaryPosition(symbol.getPosition().x, symbol.getPosition().y));
        }
    }
}
