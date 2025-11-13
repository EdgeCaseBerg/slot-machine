package spare.peetseater.games.slots.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.PrimitiveIterator;
import java.util.stream.IntStream;

public class NumberRenderer {
    private final TextureRegion[] digits;

    public NumberRenderer(Texture digitSpriteSheet) {
        TextureRegion[][] allRowsAndColumns = TextureRegion.split(digitSpriteSheet, digitSpriteSheet.getWidth(), digitSpriteSheet.getHeight()/10);
        digits = new TextureRegion[10];
        int d = 0;
        for (int i = 0; i < allRowsAndColumns.length; i++) {
            for (int j = 0; j < allRowsAndColumns[i].length; j++) {
                digits[d] = allRowsAndColumns[i][j];
                d++;
            }
        }
    }

    public void draw(SpriteBatch batch, int number, float x, float y) {
        // We will hard code the limit for now.
        assert (number >= 0);

        char[] characters = String.valueOf(number).toCharArray();

        int i = 0;
        for (char c : characters) {
            int digit = c - 48;
            // % 2 because spacing on our font digits is WIDE but I don't want to change the width of the tile.
            batch.draw(digits[digit], x + (float) (i * 3) /2, y, 3, 2);
            i++;
        }
    }
}
