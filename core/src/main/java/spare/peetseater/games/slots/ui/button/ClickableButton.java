package spare.peetseater.games.slots.ui.button;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import spare.peetseater.games.slots.ui.behavior.PositionBehavior;
import spare.peetseater.games.slots.ui.behavior.StationaryPosition;
import spare.peetseater.games.slots.ui.behavior.VerticallySpinningPosition;

import java.util.LinkedList;
import java.util.List;

public class ClickableButton {
    private final Texture btnTexture;
    private PositionBehavior positionBehavior;
    private final Vector2 initialPosition;
    private final float width;
    private final float height;
    private final List<ButtonSubscriber> subscribers;

    public ClickableButton(Texture btnTexture, float x, float y, float width, float height) {
        this.btnTexture = btnTexture;
        this.initialPosition = new Vector2(x, y);
        this.positionBehavior = new StationaryPosition(x, y);
        this.width = width;
        this.height = height;
        this.subscribers = new LinkedList<>();
    }

    public void addSubscriber(ButtonSubscriber buttonSubscriber) {
        subscribers.add(buttonSubscriber);
    }

    public void draw(SpriteBatch batch) {
        Vector2 p = positionBehavior.getPosition();
        batch.draw(btnTexture, p.x, p.y, width, height);
    }

    public void update(float delta) {
        positionBehavior.update(delta);
    }

    public boolean isPointInside(float x, float y) {
        Vector2 p = positionBehavior.getPosition();
        boolean inX = p.x <= x && x <= p.x + width;
        boolean inY = p.y <= y && y <= p.y + height;
        return inX && inY;
    }

    public void down() {
        positionBehavior = new StationaryPosition(initialPosition.x + MathUtils.random(), initialPosition.y + MathUtils.random());
    }
    public void up() {
        positionBehavior = new StationaryPosition(initialPosition.x, initialPosition.y);
        for (ButtonSubscriber subscriber : subscribers) {
            subscriber.onClick(this);
        }
    }
}
