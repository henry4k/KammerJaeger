package kammerjaeger.entity;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.BodyDef;
import kammerjaeger.graphics.RenderTarget;
import kammerjaeger.graphics.RenderUtils;

/**
 * Created by Sven on 22.06.2014.
 * Im the King of the Hill
 */
public class BulletEntity extends PhysicalEntity {

    public BulletEntity(EntityManager entityManager, BodyDef bodyDef) {
        super(entityManager, bodyDef);
    }

    @Override
    public void render(RenderTarget renderTarget) {

        final AssetManager assetManager = renderTarget.getAssetManager();
        assetManager.load("bullet.png", Texture.class);
        final Texture bullet = assetManager.get("bullet.png", Texture.class);

    }

    public void shoot(){

    }
}
