package spare.peetseater.games.slots.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import spare.peetseater.games.slots.ui.behavior.ArrivingAtPosition;
import spare.peetseater.games.slots.ui.behavior.PositionBehavior;
import spare.peetseater.games.slots.ui.behavior.StationaryPosition;

public class ReelSymbol {
    float width, height = 4;
    Texture texture;
    PositionBehavior positionBehavior;

    public ReelSymbol(float x, float y, Texture texture, float width, float height) {
        positionBehavior = new StationaryPosition(x, y);
        this.texture = texture;
        this.width = width;
        this.height = height;
    }

    public void draw(SpriteBatch batch, float modulo) {
        batch.draw(texture, positionBehavior.getPosition().x, positionBehavior.getPosition().y % modulo, width, height);
    }

    public void update(float delta) {
        positionBehavior.update(delta);
    }

    public Vector2 getPosition() {
        return positionBehavior.getPosition();
    }

    public void setPositionBehavior(PositionBehavior behavior) {
        this.positionBehavior = behavior;
    }
}
