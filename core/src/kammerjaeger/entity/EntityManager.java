package kammerjaeger.entity;

import kammerjaeger.graphics.RenderTarget;

import java.util.ArrayList;

import static kammerjaeger.Utils.getTime;

public class EntityManager {

    private static final float MAX_TIME_STEP = 0.01f;

    private ArrayList<Entity> entities;
    private float lastStepTime;

    public EntityManager() {

        entities = new ArrayList<Entity>();
        lastStepTime = getTime();
    }

    public void step() {

        final float currentTime = getTime();
        float timeStep = currentTime - lastStepTime;
        lastStepTime = currentTime;

        // Prevent simulating high time steps:
        for(; timeStep > MAX_TIME_STEP; timeStep -= MAX_TIME_STEP) {
            step(MAX_TIME_STEP);
        }
        step(timeStep);
    }

    private void step( float timeStep ) {

        for(Entity entity : entities) {
            // TODO: Run collision checks
        }
    }

    public void render( RenderTarget renderTarget ) {

        for(Entity entity : entities) {
            entity.render(renderTarget);
        }
    }

    public void addEntity(Entity entity){

        entities.add(entity);
    }
}
