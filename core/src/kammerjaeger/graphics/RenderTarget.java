package kammerjaeger.graphics;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface RenderTarget {

    public SpriteBatch getSpriteBatch();

    public AssetManager getAssetManager();
}
