package kammerjaeger.entity;

import com.badlogic.gdx.math.Vector2;

public abstract class StaticEntity implements Entity {

    private Vector2 position;
    private float rotation;

    public StaticEntity( EntityManager entityManager ) {
        position = new Vector2(0,0);
        rotation = 0;
    }

    @Override
    public Vector2 getPosition() {
        return position;
    }

    @Override
    public void setPosition(Vector2 position) {
        this.position = position;
    }

    @Override
    public float getRotation() {
        return rotation;
    }

    @Override
    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    @Override
    public void dispose(EntityManager entityManager) {
    }
}
