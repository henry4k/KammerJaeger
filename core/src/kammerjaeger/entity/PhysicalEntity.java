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

    public Vector2 getPosition() {
        return body.getPosition();
    }

    public void setPosition( Vector2 position ) {
        body.setTransform(position, body.getAngle());
    }

    public Body getBody() {
        return body;
    }

    public void dispose( EntityManager entityManager ) {
        entityManager.getPhysicsWorld().destroyBody(body);
    }
}
