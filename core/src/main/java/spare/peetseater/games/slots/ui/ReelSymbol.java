package spare.peetseater.games.slots.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class ReelSymbol {
    float currentX, currentY, destinationX, destinationY = 0;
    float accum, spinTime = 0;
    float width, height = 4;
    Texture texture;

    public ReelSymbol(float sx, float sy, Texture texture, float width, float height) {
        this.texture = texture;
        this.currentX = this.destinationX = sx;
        this.currentY = this.destinationY = sy;
        this.width = width;
        this.height = height;
    }

    public void draw(SpriteBatch batch, float modulo) {
        batch.draw(texture, currentX, currentY % modulo, width, height);
    }

    // https://floating-point-gui.de/errors/comparison/
    public static boolean nearlyEqual(float a, float b, float epsilon) {
        final float absA = Math.abs(a);
        final float absB = Math.abs(b);
        final float diff = Math.abs(a - b);

        if (a == b) { // shortcut, handles infinities
            return true;
        } else if (a == 0 || b == 0 || (absA + absB < Float.MIN_NORMAL)) {
            // a or b is zero or both are extremely close to it
            // relative error is less meaningful here
            return diff < (epsilon * Float.MIN_NORMAL);
        } else { // use relative error
            return diff / Math.min((absA + absB), Float.MAX_VALUE) < epsilon;
        }
    }

    public void update(float delta) {
        float episilon = 0.000000001f;
        if (nearlyEqual(currentX, destinationX, episilon) && nearlyEqual(currentY, destinationY, episilon)) {
            Gdx.app.log("EARLYRETURN", accum + "!");
            return;
        }

        accum += delta;
        Gdx.app.log("UPDATING", accum + "!" + " (" + currentX + "," + currentY + ",)");
        float alpha = MathUtils.clamp(accum / spinTime, 0, 1);
        Vector2 a = new Vector2(currentX, currentY);
        Vector2 newPosition = a.interpolate(new Vector2(destinationX, destinationY), alpha, Interpolation.bounceIn);
        currentX = newPosition.x;
        currentY = newPosition.y;
    }

    public void setDestination(float x, float y, float spinTime) {
        this.destinationX = x;
        this.destinationY = y;
        this.spinTime = spinTime;
    }


    public float getCurrentX() {
        return currentX;
    }

    public float getCurrentY() {
        return currentY;
    }
}
