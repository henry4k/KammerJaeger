package kammerjaeger.entity;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;

public abstract class PhysicalEntity implements Entity {

    public static PhysicalEntity bodyToPhysicsEntity( Body body ) {

        if(body.getUserData() instanceof PhysicalEntity)
            return (PhysicalEntity)body.getUserData();
        else
            return null;
    }

    private Body body;

    public PhysicalEntity( EntityManager entityManager, BodyDef bodyDef ) {

        this.body = entityManager.getPhysicsWorld().createBody(bodyDef);
        this.body.setUserData(this);
    }

    @Override
    public Vector2 getPosition() {
        return body.getPosition();
    }

    @Override
    public void setPosition( Vector2 position ) {
        body.setTransform(position, body.getAngle());
    }

    @Override
    public float getRotation() {
        return body.getAngle();
    }

    @Override
    public void setRotation( float rotation ) {
        body.setTransform(body.getPosition(), rotation);
    }

    public Body getBody() {
        return body;
    }

    @Override
    public void dispose( EntityManager entityManager ) {
        entityManager.getPhysicsWorld().destroyBody(body);
    }
}
