package kammerjaeger;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import static kammerjaeger.Utils.getTime;

public class KammerJaegerGame extends ApplicationAdapter {

    private static final float MAX_TIME_STEP = 0.01;
    
    
	SpriteBatch batch;
	Texture img;

    private World world;
    private float lastWorldStepTime;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("TileMap.png");

        world = new World(new Vector2(0,0), false);
        world.setAutoClearForces(false);

        lastWorldStepTime = getTime();
	}

    private void stepWorld() {

        final float currentTime = getTime();
        float timeStep = currentTime - lastWorldStepTime;
        lastWorldStepTime = currentTime;

        // Prevent simulating high time steps:
        for(; timeStep > MAX_TIME_STEP; timeStep -= MAX_TIME_STEP) {
            world.step(MAX_TIME_STEP, 1, 1);
        }
        world.step(timeStep, 1,1);
        world.clearForces();
    }

	@Override
	public void render() {

		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();

        stepWorld();
	}

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        world.dispose();
    }
}
