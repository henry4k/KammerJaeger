package kammerjaeger.entity;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import kammerjaeger.graphics.RenderTarget;

/**
 * Created by Sven on 22.06.2014.
 * Im the King of the Hill
 */
public class PlayerEntity extends PhysicalEntity {

    private static BodyDef bodyDef = new BodyDef();

    static {
        bodyDef.type = BodyDef.BodyType.DynamicBody;
    }

    public PlayerEntity( EntityManager entityManager ) {
        super(entityManager, bodyDef);
    }

    @Override
    public void render(RenderTarget renderTarget) {
        final AssetManager assetManager = renderTarget.getAssetManager();
        
        final Texture tex = assetManager.get("Player.png", Texture.class);
        renderTarget.getSpriteBatch().draw(tex,getPosition().x, getPosition().y);

    }
}
