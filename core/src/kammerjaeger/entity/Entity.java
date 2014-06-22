package kammerjaeger.entity;

import com.badlogic.gdx.math.Vector2;
import kammerjaeger.graphics.RenderTarget;

public abstract class Entity {

    private Vector2 position;
    private Vector2 velocity;
    private Vector2 size;
    private float rotation;

    protected Entity() {

        position = new Vector2(0,0);
        velocity = new Vector2(0,0);
        size = new Vector2(0,0);
        rotation = 0f;
    }

    public abstract void render( RenderTarget renderTarget );

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition( Vector2 position ) {
        this.position = position;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity( Vector2 velocity ) {
        this.velocity = velocity;
    }

    public Vector2 getSize() {
        return size;
    }

    public void setSize( Vector2 size ) {
        this.size = size;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }
}
