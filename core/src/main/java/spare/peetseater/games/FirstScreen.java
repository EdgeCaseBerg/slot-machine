package spare.peetseater.games;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import spare.peetseater.games.slots.core.SymbolNameMap;

import java.util.HashMap;

/** First screen of the application. Displayed after the application is created. */
public class FirstScreen implements Screen {

    SpriteBatch batch;
    OrthographicCamera camera;
    Viewport viewport;
    Texture[] textures = new Texture[6];
    Texture machineMask;

    @Override
    public void show() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new FitViewport(1280, 720, camera);
        camera.setToOrtho(false);
        camera.update();
        textures[0] = new Texture(Gdx.files.internal("RedSeven.png"));
        textures[1] = new Texture(Gdx.files.internal("WhiteSeven.png"));
        textures[2] = new Texture(Gdx.files.internal("BlueSeven.png"));
        textures[3] = new Texture(Gdx.files.internal("OneBar.png"));
        textures[4] = new Texture(Gdx.files.internal("TwoBar.png"));
        textures[5] = new Texture(Gdx.files.internal("ThreeBar.png"));
        machineMask = new Texture(Gdx.files.internal("MachineMaskMini.png"));
    }

    @Override
    public void render(float delta) {
        // Draw your screen here. "delta" is the time since last render in seconds.
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        float ratio = 16f/9f;
        for (int i = 0; i < 3; i++) {
            float x = 20 * 9 + 20 * 7 * i;
            for (int j = 0; j < textures.length; j++) {
                float y = 20 * 4 + j * 80;
                batch.draw(textures[j], x * ratio, y * ratio, 80 * ratio, 80 * ratio);
            }
        }
        batch.draw(machineMask, 0, 0, 1280, 720);
        batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }
    }

    @Override
    public void resize(int width, int height) {
        // If the window is minimized on a desktop (LWJGL3) platform, width and height are 0, which causes problems.
        // In that case, we don't resize anything, and wait for the window to be a normal size before updating.
        if(width <= 0 || height <= 0) return;

        // Resize your screen here. The parameters represent the new window size.
    }

    @Override
    public void pause() {
        // Invoked when your application is paused.
    }

    @Override
    public void resume() {
        // Invoked when your application is resumed after pause.
    }

    @Override
    public void hide() {
        // This method is called when another screen replaces this one.
    }

    @Override
    public void dispose() {
        // Destroy screen's assets here.
    }
}