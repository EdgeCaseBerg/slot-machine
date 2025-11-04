package spare.peetseater.games.slots.ui.behavior;

import com.badlogic.gdx.math.Vector2;

public class StationaryPosition implements PositionBehavior {
    Vector2 position;
    public StationaryPosition(float x, float y) {
        position = new Vector2(x, y);
    }

    @Override
    public Vector2 getPosition() {
        return position;
    }

    @Override
    public void update(float delta) {
        // Do nothing.
    }
}
