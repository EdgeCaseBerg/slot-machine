package spare.peetseater.games.slots.ui.sound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundPlayer {
    private final Sound plink;
    private final Sound plonk;
    private final Sound boop;
    private final Sound beep;
    private final Sound click;
    private final Sound clack;
    private final Sound weeWoo;
    private final Sound winner;
    private final Sound tryagain;
    private final Sound gameover;

    public SoundPlayer() {
        plink = Gdx.audio.newSound(Gdx.files.internal("sounds/plink.wav"));
        plonk = Gdx.audio.newSound(Gdx.files.internal("sounds/plonk.wav"));
        beep = Gdx.audio.newSound(Gdx.files.internal("sounds/beep.wav"));
        boop = Gdx.audio.newSound(Gdx.files.internal("sounds/boop.wav"));
        click = Gdx.audio.newSound(Gdx.files.internal("sounds/click.wav"));
        clack = Gdx.audio.newSound(Gdx.files.internal("sounds/clack.wav"));
        weeWoo  = Gdx.audio.newSound(Gdx.files.internal("sounds/WeeWooWeeWoo.wav"));
        winner  = Gdx.audio.newSound(Gdx.files.internal("sounds/winner.wav"));
        tryagain  = Gdx.audio.newSound(Gdx.files.internal("sounds/tryagain.wav"));
        gameover  = Gdx.audio.newSound(Gdx.files.internal("sounds/gameover.wav"));
    }

    public void playWin() {
        winner.play();
    }

    public void playTryAgain() {
        tryagain.play();
    }

    public void playPlink() {
        plink.loop();
    }

    public void playPlonk() {
        plonk.play();
    }

    public void playBeep() {
        beep.play();
    }

    public void playBoop() {
        boop.play();
    }

    public void playClick() {
        click.play();
    }

    public void playClack() {
        clack.play();
    }

    public void playWeeWoo() {
        weeWoo.loop();
    }

    public void playGameOver() {
        gameover.play();
    }

    public void stopWin() {
        winner.stop();
    }

    public void stopTryAgain() {
        tryagain.stop();
    }

    public void stopPlink() {
        plink.stop();
    }

    public void stopPlonk() {
        plonk.stop();
    }

    public void stopBeep() {
        beep.stop();
    }

    public void stopBoop() {
        boop.stop();
    }

    public void stopClick() {
        click.stop();
    }

    public void stopClack() {
        clack.stop();
    }

    public void stopWeeWoo() {
        weeWoo.stop();
    }

    public void stopGameOver() {
        gameover.stop();
    }

    public void dispose() {
        plink.dispose();
        plonk.dispose();
        beep.dispose();
        boop.dispose();
        click.dispose();
        clack.dispose();
        weeWoo.dispose();
        winner.dispose();
        tryagain.dispose();
        gameover.dispose();
    }
}
