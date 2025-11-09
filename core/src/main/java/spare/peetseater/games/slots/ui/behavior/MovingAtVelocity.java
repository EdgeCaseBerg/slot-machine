package spare.peetseater.games.slots.ui.behavior;
import com.badlogic.gdx.math.Vector2;

public class MovingAtVelocity implements PositionBehavior {
    private final Vector2 position;
    private final float speed;

    public MovingAtVelocity(Vector2 position, float speed) {
        this.position = position.cpy();
        this.speed = speed;
    }

    public void update(float delta) {
        position.y += speed * delta;
    }

    public Vector2 getPosition() {
        return position;
    }
}
