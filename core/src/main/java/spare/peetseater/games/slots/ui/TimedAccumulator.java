package spare.peetseater.games.slots.ui;

public class TimedAccumulator {
    private float target;
    private float accum;

    public TimedAccumulator(float forSeconds) {
        accum = 0;
        this.target = forSeconds;
    }

    public TimedAccumulator() { this(0); }

    public void update(float delta) {
        if (isDone()) {
            accum = target;
            return;
        }

        accum += delta;
    }

    public boolean isDone() {
        return accum >= target;
    }

    /* Returns 0 - 1, 1 being 100% complete*/
    public float getProgress() {
        if (target == 0) return 1;
        return accum / target;
    }

    public void reset() {
        accum = 0;
    }

    public void setTarget(float newTarget) {
        this.target = newTarget;
    }
}
