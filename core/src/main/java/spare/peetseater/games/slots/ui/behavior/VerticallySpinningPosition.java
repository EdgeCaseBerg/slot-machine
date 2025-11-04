package spare.peetseater.games.slots.ui.behavior;

import com.badlogic.gdx.math.Vector2;

public class VerticallySpinningPosition implements PositionBehavior {
    private final Vector2 position;
    private final Vector2 range;
    private final float speed;

    /**
     * @param range range [x,y]
     * The position will wrap to x when position.x reaches y
     * */
    public VerticallySpinningPosition(Vector2 position, float speed, Vector2 range) {
        this.position = position.cpy();
        this.speed = speed;
        this.range = range;
    }

    public void update(float delta) {
        position.y += speed * delta;
        if (position.y > range.y) {
            position.y = range.x;
        }
    }

    public Vector2 getPosition() {
        return position;
    }
}
