package kammerjaeger.entity;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.BodyDef;
import kammerjaeger.graphics.RenderTarget;
import kammerjaeger.graphics.RenderUtils;

/**
 * Created by Sven on 22.06.2014.
 * Im the King of the Hill
 */
public class PlayerEntity extends PhysicalEntity {

    private static BodyDef bodyDef = new BodyDef();

    static {
        bodyDef.type = BodyDef.BodyType.DynamicBody;
    }

    private Color torsoColor;
    private Color armColor;
    private Color headColor;

    public PlayerEntity( EntityManager entityManager ) {
        super(entityManager, bodyDef);

        torsoColor = Color.GREEN;
        armColor   = Color.BLUE;
        headColor  = Color.PINK;
    }

    @Override
    public void render(RenderTarget renderTarget) {

        final AssetManager assetManager = renderTarget.getAssetManager();

        final Texture torso = assetManager.get("player/Torso.png", Texture.class);
        final Texture arms = assetManager.get("player/Arms.png", Texture.class);
        final Texture gun = assetManager.get("player/Gun.png", Texture.class);
        final Texture head = assetManager.get("player/Head.png", Texture.class);

        renderTarget.getSpriteBatch().setColor(torsoColor);
        RenderUtils.drawTexture(renderTarget, torso, this);

        renderTarget.getSpriteBatch().setColor(armColor);
        RenderUtils.drawTexture(renderTarget, arms, this);

        renderTarget.getSpriteBatch().setColor(Color.WHITE);
        RenderUtils.drawTexture(renderTarget, gun, this);

        renderTarget.getSpriteBatch().setColor(headColor);
        RenderUtils.drawTexture(renderTarget, head, this);
    }
}
