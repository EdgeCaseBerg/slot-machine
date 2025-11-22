package spare.peetseater.games.slots.ui.button;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import spare.peetseater.games.slots.ui.behavior.PositionBehavior;
import spare.peetseater.games.slots.ui.behavior.StationaryPosition;

import java.util.LinkedList;
import java.util.List;

public class ClickableButton {
    private final Texture btnTexture;
    private PositionBehavior positionBehavior;
    private final Vector2 initialPosition;
    private float width;
    private float height;
    private float offset;
    private final List<ButtonSubscriber> subscribers;
    private boolean isDisabled;
    private boolean isHovering;
    private float accum;

    public ClickableButton(Texture btnTexture, float x, float y, float width, float height) {
        this.btnTexture = btnTexture;
        this.initialPosition = new Vector2(x, y);
        this.offset = 0;
        this.positionBehavior = new StationaryPosition(x, y);
        this.width = width;
        this.height = height;
        this.subscribers = new LinkedList<>();
        this.isDisabled = false;
    }

    public void addSubscriber(ButtonSubscriber buttonSubscriber) {
        subscribers.add(buttonSubscriber);
    }

    public void draw(SpriteBatch batch) {
        Vector2 p = positionBehavior.getPosition();
        float y = p.y;
        float dynamicOffset = 0;
        if (isHovering) {
            dynamicOffset = MathUtils.lerp(0.0f, 0.25f, (float)Math.abs(Math.sin(accum)));
        }
        batch.draw(
            btnTexture,
            p.x - offset - dynamicOffset,
            y - offset - dynamicOffset,
            width + (offset+dynamicOffset)*2,
            height + (offset+dynamicOffset)*2
        );
    }

    public void update(float delta) {
        positionBehavior.update(delta);
        accum+= delta;
    }

    public boolean isPointInside(float x, float y) {
        Vector2 p = positionBehavior.getPosition();
        boolean inX = p.x <= x && x <= p.x + width;
        boolean inY = p.y <= y && y <= p.y + height;
        return inX && inY;
    }

    public void down() {
        offset = 0.25f;
    }
    public void up() {
        offset = 0;
        positionBehavior = new StationaryPosition(initialPosition.x, initialPosition.y);
        if (isDisabled) {
            return;
        }
        for (ButtonSubscriber subscriber : subscribers) {
            subscriber.onClick(this);
        }
    }

    public boolean isDisabled() {
        return isDisabled;
    }

    public void setDisabled(boolean disabled) {
        isDisabled = disabled;
    }

    public boolean isHeld() {
        return offset != 0;
    }

    public void resetClick() {
        offset = 0;
    }

    public void hover() {
        isHovering = true;
    }

    public void stopHover() {
        isHovering = false;
    }
}
