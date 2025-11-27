package spare.peetseater.games;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import spare.peetseater.games.slots.ui.button.ButtonMultiplexer;
import spare.peetseater.games.slots.ui.button.ButtonSubscriber;
import spare.peetseater.games.slots.ui.button.ClickableButton;
import spare.peetseater.games.slots.ui.sound.SoundPlayer;

public class GameOver implements Screen {
    private final GameRunner gameRunner;
    private  SoundPlayer soundPlayer;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private FitViewport viewport;
    private Texture gameOverTexture;
    private Texture againBtnTexture;
    private Texture quitBtnTexture;
    private ClickableButton quitBtn;
    private ClickableButton againBtn;
    private ButtonMultiplexer btnPlexer;

    public GameOver(GameRunner gameRunner) {
        this.gameRunner = gameRunner;
    }


    @Override
    public void show() {
        soundPlayer = new SoundPlayer();
        soundPlayer.playGameOver();
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new FitViewport(32, 18, camera);
        camera.setToOrtho(false);
        camera.update();
        gameOverTexture = new Texture(Gdx.files.internal("gameOver.png"));
        againBtnTexture = new Texture(Gdx.files.internal("tryAgain.png"));
        againBtn = new ClickableButton(againBtnTexture, 6, 2, 4, 6);
        quitBtnTexture = new Texture(Gdx.files.internal("quit.png"));
        quitBtn = new ClickableButton(quitBtnTexture, 12, 6, 4, 6);

        btnPlexer = new ButtonMultiplexer(camera);
        btnPlexer.addButton(againBtn);
        btnPlexer.addButton(quitBtn);
        Gdx.input.setInputProcessor(btnPlexer);

        againBtn.addSubscriber(new ButtonSubscriber() {
            @Override
            public void onClick(ClickableButton clickableButton) {
                gameRunner.setScreen(new FirstScreen(gameRunner));
                soundPlayer.playClick();
            }
        });
        quitBtn.addSubscriber(new ButtonSubscriber() {
            @Override
            public void onClick(ClickableButton clickableButton) {
                soundPlayer.playClack();
                Gdx.app.exit();
            }
        });
    }

    @Override
    public void render(float delta) {
        btnPlexer.update(delta);

        ScreenUtils.clear(Color.BLACK);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(gameOverTexture, 0, 0, 32, 18);
        quitBtn.draw(batch);
        againBtn.draw(batch);
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
        viewport.update(width, height);
        viewport.apply(true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        againBtnTexture.dispose();
        gameOverTexture.dispose();
        quitBtnTexture.dispose();
        soundPlayer.dispose();
    }
}
