package kammerjaeger.entity;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import kammerjaeger.graphics.RenderTarget;

/**
 * Created by Sven on 22.06.2014.
 * Im the King of the Hill
 */
public class PlayerEntity extends Entity {



    @Override
    public void render(RenderTarget renderTarget) {
        final AssetManager assetManager = renderTarget.getAssetManager();
        
        final Texture tex = assetManager.get("Player.png", Texture.class);
        renderTarget.getSpriteBatch().draw(tex,getPosition().x, getPosition().y);

    }
}
