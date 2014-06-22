package kammerjaeger.graphics;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Renderer implements RenderTarget {

    private AssetManager assetManager;
    private SpriteBatch spriteBatch;

    public Renderer(AssetManager assetManager) {
        this.assetManager = assetManager;
        this.spriteBatch = new SpriteBatch();
    }

    public void beginFrame( Camera camera ) {

        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
    }

    public void endFrame() {

        spriteBatch.end();
    }

    @Override
    public SpriteBatch getSpriteBatch() {
        return spriteBatch;
    }

    @Override
    public AssetManager getAssetManager() {
        return assetManager;
    }
}
