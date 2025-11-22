package spare.peetseater.games.slots.ui.button;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;

import java.util.LinkedList;
import java.util.List;

public class ButtonMultiplexer implements InputProcessor {

    private final Camera camera;
    List<ClickableButton> buttons;

    /* Camera is required to convert screen coords to world space */
    public ButtonMultiplexer(Camera camera) {
        this.camera = camera;
        buttons = new LinkedList<>();
    }

    public void addButton(ClickableButton button) {
        buttons.add(button);
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector3 worldXYZ = camera.unproject(new Vector3(screenX, screenY, 0));
        boolean touched = false;
        for (ClickableButton btn : buttons) {
            if (btn.isPointInside(worldXYZ.x, worldXYZ.y)) {
                btn.down();
                touched = true;
            }
        }
        return touched;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Vector3 worldXYZ = camera.unproject(new Vector3(screenX, screenY, 0));
        boolean touched = false;
        for (ClickableButton btn : buttons) {
            if (btn.isPointInside(worldXYZ.x, worldXYZ.y)) {
                btn.up();
                touched = true;
            } else {
                btn.resetClick();
            }
        }
        return touched;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        Vector3 worldXYZ = camera.unproject(new Vector3(screenX, screenY, 0));
        boolean touched = false;
        for (ClickableButton btn : buttons) {
            if (!btn.isPointInside(worldXYZ.x, worldXYZ.y) && btn.isHeld()) {
                btn.resetClick();
            } else if (btn.isPointInside(worldXYZ.x, worldXYZ.y)) {
                btn.hover();
            } else {
                btn.stopHover();
            }
        }
        return touched;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

    public void update(float delta) {
        for (ClickableButton button : buttons) {
            button.update(delta);
        }
    }
}
