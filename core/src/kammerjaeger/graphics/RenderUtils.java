package kammerjaeger.graphics;

import com.badlogic.gdx.graphics.Texture;
import kammerjaeger.entity.Entity;

/**
 * Created by Sven on 22.06.2014.
 * Im the King of the Hill
 */
public class RenderUtils {

    public static void drawTexture(RenderTarget renderTarget, Texture texture, float x, float y, float rotation, float originX, float originY) {

        renderTarget.getSpriteBatch().draw(texture,
                x,
                y,
                originX,
                originY,
                texture.getWidth(),
                texture.getHeight(),
                1f,
                1f,
                rotation,
                0,
                0,
                texture.getWidth(),
                texture.getHeight(),
                false,
                false);
    }

    public static void drawTexture(RenderTarget renderTarget, Texture texture, float x, float y, float rotation) {
        drawTexture(renderTarget, texture, x, y, rotation, texture.getWidth()/2, texture.getHeight()/2);
    }

    public static void drawTexture(RenderTarget renderTarget, Texture texture, Entity entity){
        drawTexture(renderTarget, texture, entity.getPosition().x, entity.getPosition().y, entity.getRotation());
    }

}
