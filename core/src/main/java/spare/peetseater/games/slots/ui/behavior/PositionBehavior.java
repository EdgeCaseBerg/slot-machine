package spare.peetseater.games.slots.ui.behavior;

import com.badlogic.gdx.math.Vector2;

public interface PositionBehavior {
    public Vector2 getPosition();
    public void update(float delta);
}
