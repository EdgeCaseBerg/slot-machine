package spare.peetseater.games;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import spare.peetseater.games.slots.core.SlotMachine;
import spare.peetseater.games.slots.core.SymbolNameMap;
import spare.peetseater.games.slots.core.Wallet;
import spare.peetseater.games.slots.ui.ReelsSubscriber;
import spare.peetseater.games.slots.ui.coins.CoinStacksDisplay;
import spare.peetseater.games.slots.ui.NumberRenderer;
import spare.peetseater.games.slots.ui.ReelsPanel;
import spare.peetseater.games.slots.ui.button.ButtonMultiplexer;
import spare.peetseater.games.slots.ui.button.ButtonSubscriber;
import spare.peetseater.games.slots.ui.button.ClickableButton;

import java.util.*;

/** First screen of the application. Displayed after the application is created. */
public class FirstScreen implements Screen {

    SpriteBatch batch;
    OrthographicCamera camera;
    Viewport viewport;
    Map<String, Texture> symbolNameToTexture;
    SlotMachine slotMachine;
    private ReelsPanel reelsPanel;
    private Texture spinBtnTexture;
    private Texture bet1BtnTexture;
    private Texture betMaxBtnTexture;
    private ClickableButton bet1Btn;
    private ClickableButton betMaxBtn;
    private TextureRegion[] digits;
    private NumberRenderer numberRenderer;
    private int bet;
    private ClickableButton spinBtn;
    private Texture allDigits;
    private ButtonMultiplexer btnPlexer;
    Wallet wallet;
    private Texture coinTexture;
    private CoinStacksDisplay coinStacks;

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

        bet = 1;
        wallet = new Wallet(100);
        coinTexture = new Texture(Gdx.files.internal("gold-coin.png"));
        coinStacks = new CoinStacksDisplay(coinTexture, new Vector2(25, 32), new Vector2(0, 18));
        coinStacks.addCoins(wallet.getFunds());
        coinStacks.update(1000);

        Texture machineMask = new Texture(Gdx.files.internal("MachineMaskMini.png"));
        reelsPanel = new ReelsPanel(
            slotMachine,
            symbolNameToTexture,
            machineMask
        );
        reelsPanel.addSubscriber(new ReelsSubscriber() {
            @Override
            public void onSpinComplete() {
                wallet.awardAmount(bet * slotMachine.payout());
                coinStacks.addCoins(bet * slotMachine.payout());
                spinBtn.setDisabled(false);
            }
        });

        bet1BtnTexture = new Texture(Gdx.files.internal("Bet1.png"));
        bet1Btn = new ClickableButton(bet1BtnTexture, 1, 14, 5 , 3);
        bet1Btn.addSubscriber(new ButtonSubscriber() {
            @Override
            public void onClick(ClickableButton clickableButton) {
                if (!wallet.hasEnoughToBet(bet + 1)) {
                    return;
                }
                bet +=1;
                if (bet > 5) bet = 1;
            }
        });
        Texture betMaxBtnTexture = new Texture(Gdx.files.internal("BetMax.png"));
        betMaxBtn = new ClickableButton(betMaxBtnTexture, 1,10,5,3);
        betMaxBtn.addSubscriber(new ButtonSubscriber() {
            @Override
            public void onClick(ClickableButton clickableButton) {
                if (wallet.hasEnoughToBet(5)) {
                    bet = 5;
                } else {
                    bet = wallet.getFunds();
                }
            }
        });

        spinBtnTexture = new Texture(Gdx.files.internal("spin.png"));
        spinBtn = new ClickableButton(spinBtnTexture, 1,4,5,5);
        spinBtn.addSubscriber(new ButtonSubscriber() {
            @Override
            public void onClick(ClickableButton clickableButton) {
                if (!wallet.hasEnoughToBet(bet)) {
                    // NO! Do some sort of interaction to show they can't
                    return;
                }
                spinBtn.setDisabled(true);
                wallet.subtractAmount(bet);
                coinStacks.removeCoins(bet);
                reelsPanel.startSpinning();
            }
        });
        btnPlexer = new ButtonMultiplexer(camera);
        btnPlexer.addButton(bet1Btn);
        btnPlexer.addButton(betMaxBtn);
        btnPlexer.addButton(spinBtn);
        Gdx.input.setInputProcessor(btnPlexer);

        allDigits = new Texture(Gdx.files.internal("digits.png"));
        numberRenderer = new NumberRenderer(allDigits);
    }

    @Override
    public void render(float delta) {
        // Draw your screen here. "delta" is the time since last render in seconds.
        ScreenUtils.clear(Color.BLACK);
        camera.update();
        reelsPanel.update(delta);
        btnPlexer.update(delta);
        coinStacks.update(delta);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        reelsPanel.draw(batch);

        bet1Btn.draw(batch);
        betMaxBtn.draw(batch);
        spinBtn.draw(batch);
        numberRenderer.draw(batch, bet, 2, 1);
        coinStacks.draw(batch);
        numberRenderer.draw(batch, wallet.getFunds(), 25.5f, 0);
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
        bet1BtnTexture.dispose();
        betMaxBtnTexture.dispose();
        spinBtnTexture.dispose();
        for (Map.Entry<String, Texture> entry : symbolNameToTexture.entrySet()) {
            entry.getValue().dispose();
        }
        allDigits.dispose();
    }
}
