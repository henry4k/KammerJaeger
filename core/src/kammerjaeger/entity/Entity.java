package kammerjaeger.entity;

import com.badlogic.gdx.math.Vector2;
import kammerjaeger.graphics.RenderTarget;

public interface Entity {

    public Vector2 getPosition();
    public void setPosition( Vector2 position );
    
    public float getRotation();
    public void setRotation( float rotation );

    public void render( RenderTarget renderTarget );
    public void dispose( EntityManager entityManager );
}
