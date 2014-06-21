package kammerjaeger.graphics;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Renderer implements RenderTarget {

    private SpriteBatch spriteBatch;

    public Renderer() {

        spriteBatch = new SpriteBatch();
    }

    public void beginFrame() {

        spriteBatch.begin();
    }

    public void endFrame() {

        spriteBatch.end();
    }

    @Override
    public SpriteBatch getSpriteBatch() {
        return spriteBatch;
    }
}
