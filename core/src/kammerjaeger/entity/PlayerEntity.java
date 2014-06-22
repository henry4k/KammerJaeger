package kammerjaeger.entity;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import kammerjaeger.graphics.RenderTarget;
import kammerjaeger.graphics.RenderUtils;

/**
 * Created by Sven on 22.06.2014.
 * Im the King of the Hill
 */
public class PlayerEntity extends Entity {



    @Override
    public void render(RenderTarget renderTarget) {
        final AssetManager assetManager = renderTarget.getAssetManager();
                 //public void draw (Texture texture, float x, float y, float originX, float originY, float width, float height, float scaleX,
                //float scaleY, float rotation, int srcX, int srcY, int srcWidth, int srcHeight, boolean flipX, boolean flipY)


        final Texture torso = assetManager.get("player/Torso.png", Texture.class);
        final Texture gun = assetManager.get("player/Gun.png", Texture.class);
        final Texture arms = assetManager.get("player/Arms.png", Texture.class);
        final Texture head = assetManager.get("player/Head.png", Texture.class);

        RenderUtils.drawTexture(renderTarget,torso,this);
        RenderUtils.drawTexture(renderTarget, gun,this);
        RenderUtils.drawTexture(renderTarget, arms,this);
        RenderUtils.drawTexture(renderTarget,arms,this);



    }
}
