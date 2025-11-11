package spare.peetseater.games;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import spare.peetseater.games.slots.core.SlotMachine;
import spare.peetseater.games.slots.core.SymbolNameMap;
import spare.peetseater.games.slots.ui.NumberRenderer;
import spare.peetseater.games.slots.ui.ReelsPanel;

import java.util.*;

/** First screen of the application. Displayed after the application is created. */
public class FirstScreen implements Screen {

    SpriteBatch batch;
    OrthographicCamera camera;
    Viewport viewport;
    Map<String, Texture> symbolNameToTexture;
    SlotMachine slotMachine;
    private ReelsPanel reelsPanel;
    private Texture spinBtn;
    private Texture betMaxBtn;
    private Texture bet1Btn;
    private TextureRegion[] digits;
    private float accum;
    private NumberRenderer numberRenderer;

    @Override
    public void show() {
        SymbolNameMap symbolMap = new SymbolNameMap(
            "blank",
            "one_bar",
            "two_bar",
            "three_bar",
            "blue_seven",
            "white_seven",
            "red_seven"
        );
        slotMachine = new SlotMachine(symbolMap);
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new FitViewport(32, 18, camera);
        camera.setToOrtho(false);
        camera.update();
        symbolNameToTexture = new Hashtable<>();
        symbolNameToTexture.put(symbolMap.getRedSeven(), new Texture(Gdx.files.internal("RedSeven.png")));
        symbolNameToTexture.put(symbolMap.getWhiteSeven(), new Texture(Gdx.files.internal("WhiteSeven.png")));
        symbolNameToTexture.put(symbolMap.getBlueSeven(), new Texture(Gdx.files.internal("BlueSeven.png")));
        symbolNameToTexture.put(symbolMap.getOneBar(), new Texture(Gdx.files.internal("OneBar.png")));
        symbolNameToTexture.put(symbolMap.getTwoBar(), new Texture(Gdx.files.internal("TwoBar.png")));
        symbolNameToTexture.put(symbolMap.getThreeBar(), new Texture(Gdx.files.internal("ThreeBar.png")));
        symbolNameToTexture.put(symbolMap.getBlank(), new Texture(Gdx.files.internal("Blank.png")));
        Texture machineMask = new Texture(Gdx.files.internal("MachineMaskMini.png"));
        reelsPanel = new ReelsPanel(
            slotMachine,
            symbolNameToTexture,
            machineMask
        );

        bet1Btn = new Texture(Gdx.files.internal("Bet1.png"));
        betMaxBtn = new Texture(Gdx.files.internal("BetMax.png"));
        spinBtn = new Texture(Gdx.files.internal("spin.png"));
        Texture allDigits = new Texture(Gdx.files.internal("digits.png"));
        numberRenderer = new NumberRenderer(allDigits);
    }

    @Override
    public void render(float delta) {
        // Draw your screen here. "delta" is the time since last render in seconds.
        ScreenUtils.clear(Color.BLACK);
        camera.update();
        reelsPanel.update(delta);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        reelsPanel.draw(batch);

        batch.draw(bet1Btn, 1, 14, 5, 3);
        batch.draw(betMaxBtn, 1, 10, 5, 3);
        batch.draw(spinBtn, 1, 4, 5, 5);
        accum += delta;
        numberRenderer.draw(batch, (int) accum % 10, 2, 1);
        numberRenderer.draw(batch, 1234567890, 8, 1);
        batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            reelsPanel.startSpinning();
        }
    }

    @Override
    public void resize(int width, int height) {
        // If the window is minimized on a desktop (LWJGL3) platform, width and height are 0, which causes problems.
        // In that case, we don't resize anything, and wait for the window to be a normal size before updating.
        if(width <= 0 || height <= 0) return;

        // Resize your screen here. The parameters represent the new window size.
        viewport.update(width, height);
        viewport.apply(true);
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
