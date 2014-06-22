package kammerjaeger.entity;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import kammerjaeger.graphics.RenderTarget;
import kammerjaeger.graphics.RenderUtils;

/**
 * Created by Sven on 22.06.2014.
 * Im the King of the Hill
 */
public class PlayerEntity extends PhysicalEntity {

    public static final float MAX_WALK_SPEED = 500;

    private static BodyDef bodyDef = new BodyDef();

    static {
        bodyDef.type = BodyDef.BodyType.DynamicBody;
    }

    private Color torsoColor;
    private Color armColor;
    private Color headColor;

    public PlayerEntity( EntityManager entityManager ) {
        super(entityManager, bodyDef);

        final CircleShape shape = new CircleShape();
        shape.setRadius(8);
        getBody().createFixture(shape, 1);

        torsoColor = new Color(0x3f372eFF);
        armColor   = new Color(0x474e45FF);
        headColor  = new Color(0x2e3f28FF);
    }

    @Override
    public void render(RenderTarget renderTarget) {

        final AssetManager assetManager = renderTarget.getAssetManager();

        final Texture torso = assetManager.get("player/Torso.png", Texture.class);
        final Texture arms = assetManager.get("player/Arms.png", Texture.class);
        final Texture gun = assetManager.get("player/Gun.png", Texture.class);
        final Texture head = assetManager.get("player/Head.png", Texture.class);

        renderTarget.getSpriteBatch().setColor(armColor);
        RenderUtils.drawTexture(renderTarget, arms, this);

        renderTarget.getSpriteBatch().setColor(Color.WHITE);
        RenderUtils.drawTexture(renderTarget, gun, this);

        renderTarget.getSpriteBatch().setColor(torsoColor);
        RenderUtils.drawTexture(renderTarget, torso, this);

        renderTarget.getSpriteBatch().setColor(headColor);
        RenderUtils.drawTexture(renderTarget, head, this);
    }
}
