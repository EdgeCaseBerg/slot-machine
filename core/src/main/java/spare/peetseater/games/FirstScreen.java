package spare.peetseater.games;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import spare.peetseater.games.slots.core.SlotMachine;
import spare.peetseater.games.slots.core.SymbolNameMap;
import spare.peetseater.games.slots.ui.ReelColumn;
import spare.peetseater.games.slots.ui.ReelSymbol;
import spare.peetseater.games.slots.ui.TimedAccumulator;
import spare.peetseater.games.slots.ui.behavior.ArrivingAtPosition;
import spare.peetseater.games.slots.ui.behavior.VerticallySpinningPosition;

import java.util.*;

/** First screen of the application. Displayed after the application is created. */
public class FirstScreen implements Screen {

    SpriteBatch batch;
    OrthographicCamera camera;
    Viewport viewport;
    Texture[] textures = new Texture[7];
    Map<String, Texture> symbolNameToTexture;
    Texture machineMask;
    List<ReelSymbol> symbolsOnReels;
    float maxYForSpinning;
    Optional<TimedAccumulator> maybeSpin;
    SlotMachine slotMachine;
    private ReelColumn testReel;

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
        textures[0] = new Texture(Gdx.files.internal("RedSeven.png"));
        textures[1] = new Texture(Gdx.files.internal("WhiteSeven.png"));
        textures[2] = new Texture(Gdx.files.internal("BlueSeven.png"));
        textures[3] = new Texture(Gdx.files.internal("OneBar.png"));
        textures[4] = new Texture(Gdx.files.internal("TwoBar.png"));
        textures[5] = new Texture(Gdx.files.internal("ThreeBar.png"));
        textures[6] = new Texture(Gdx.files.internal("Blank.png"));
        symbolNameToTexture = new Hashtable<>();
        symbolNameToTexture.put(symbolMap.getRedSeven(), textures[0]);
        symbolNameToTexture.put(symbolMap.getWhiteSeven(), textures[1]);
        symbolNameToTexture.put(symbolMap.getBlueSeven(), textures[2]);
        symbolNameToTexture.put(symbolMap.getOneBar(), textures[3]);
        symbolNameToTexture.put(symbolMap.getTwoBar(), textures[4]);
        symbolNameToTexture.put(symbolMap.getThreeBar(), textures[5]);
        symbolNameToTexture.put(symbolMap.getBlank(), textures[6]);
        testReel = new ReelColumn(
            slotMachine.getFirstReel(),
            new Vector2(1, 3),
            new Vector2(4, 12),
            symbolNameToTexture
        );
        machineMask = new Texture(Gdx.files.internal("MachineMaskMini.png"));
        symbolsOnReels = new LinkedList<>();
        maybeSpin = Optional.empty();

        for (int i = 0; i < 3; i++) {
            float x = 8 + i * 6;
            for (int j = 0; j < textures.length; j++) {
                float y = 3 + j * 4;
                symbolsOnReels.add(new ReelSymbol(x, y, textures[j], 4, 4));
                maxYForSpinning = y - 3;
            }
        }
    }

    @Override
    public void render(float delta) {
        // Draw your screen here. "delta" is the time since last render in seconds.
        ScreenUtils.clear(Color.BLACK);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        maybeSpin.ifPresent((spin) -> {
            spin.update(delta);
        });
        for (ReelSymbol symbolTexture : symbolsOnReels) {
            symbolTexture.update(delta);
            symbolTexture.draw(batch, textures.length * 4);
        }

        batch.draw(machineMask, 0, 0, 32, 18);
        testReel.update(delta);
        testReel.draw(batch);

        batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            maybeSpin = Optional.of(new TimedAccumulator(3f));
            for (ReelSymbol symbolTexture : symbolsOnReels) {
                symbolTexture.setPositionBehavior(
                    new VerticallySpinningPosition(
                        symbolTexture.getPosition(),
                        4 * maxYForSpinning,
                        new Vector2(-1, maxYForSpinning)
                    )
                );
            }
            testReel.startSpinningReel();
        }

        if (maybeSpin.isPresent() && maybeSpin.get().isDone()) {
            testReel.stopReel();
            Iterator<ReelSymbol> iter = symbolsOnReels.iterator();
            for (int i = 0; i < 3; i++) {
                float x = 8 + i * 6;
                for (int j = 0; j < textures.length; j++) {
                    float y = 3 + j * 4;
                    ReelSymbol reelSymbol = iter.next();
                    reelSymbol.setPositionBehavior(
                        new ArrivingAtPosition(
                            reelSymbol.getPosition(),
                            new Vector2(x, y),
                            1f
                        )
                    );
                }
            }
            maybeSpin = Optional.empty();
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
