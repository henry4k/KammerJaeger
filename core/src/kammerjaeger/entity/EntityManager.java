package kammerjaeger.entity;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import kammerjaeger.graphics.RenderTarget;

import java.util.ArrayList;

import static kammerjaeger.Utils.getTime;

public class EntityManager {

    private static final float MAX_TIME_STEP = 0.01f;

    private World physicsWorld;
    private ArrayList<Entity> entities;
    private float lastStepTime;

    public EntityManager( World physicsWorld ) {

        this.physicsWorld = physicsWorld;
        this.entities = new ArrayList<Entity>();
        this.lastStepTime = getTime();
    }

    public void step() {

        final float currentTime = getTime();
        float timeStep = currentTime - lastStepTime;
        lastStepTime = currentTime;

        final int velocityIterations = 1; // I'm 12 and what is this?
        final int positionIterations = 1; // TODO: Find out what this is.

        // Prevent simulating high time steps:
        for(; timeStep > MAX_TIME_STEP; timeStep -= MAX_TIME_STEP) {
            physicsWorld.step(MAX_TIME_STEP, velocityIterations, positionIterations);
        }
        physicsWorld.step(timeStep, velocityIterations, positionIterations);
        physicsWorld.clearForces();
    }

    public void render( RenderTarget renderTarget ) {

        for(Entity entity : entities) {
            entity.render(renderTarget);
        }
    }

    public void addEntity( Entity entity ) {

        entities.add(entity);

        final BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        physicsWorld.createBody(bodyDef);
    }

    private void removeEntity( Entity entity ) {

        entities.remove(entity);
        entity.dispose(this);
    }

    public World getPhysicsWorld() {
        return physicsWorld;
    }

    public void dispose() {

        for(Entity entity : entities) {
            entity.dispose(this);
        }
    }
}
