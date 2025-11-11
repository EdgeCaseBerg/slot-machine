package spare.peetseater.games.slots.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

import java.util.LinkedList;
import java.util.List;

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
        assert(number >= 0);

        List<Integer> numbersToRender = new LinkedList<>();
        numbersToRender.add(number % 10);

        int i = 1;
        int modulo = 100;
        while (modulo < number) {
            int amountForPlace = (number % modulo) / 10 * i;
            numbersToRender.add(amountForPlace);
            modulo *= 10;
            i++;
        }

        for (int j = 0; j < numbersToRender.size(); j++) {
            int digit = numbersToRender.get(j);
            // TODO spacing.
            batch.draw(digits[digit], x + j * 3, y, 3, 2);
        }
    }
}
