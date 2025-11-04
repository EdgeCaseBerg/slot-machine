package spare.peetseater.games.slots.ui.behavior;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import spare.peetseater.games.slots.ui.TimedAccumulator;

public class ArrivingAtPosition implements PositionBehavior {
    private Vector2 current;
    private final Vector2 end;
    private final TimedAccumulator accumulator;

    public ArrivingAtPosition(Vector2 start, Vector2 end, float withinTime) {
        this.current = start.cpy();
        this.end = end.cpy();
        accumulator = new TimedAccumulator(withinTime);
    }

    public void update(float delta) {
        accumulator.update(delta);
        this.current.interpolate(end, accumulator.getProgress(), Interpolation.linear);
    }

    public Vector2 getPosition() {
        return this.current.cpy();
    }
}
