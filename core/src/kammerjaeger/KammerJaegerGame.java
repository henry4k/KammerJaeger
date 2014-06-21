package kammerjaeger;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import kammerjaeger.map.Map;

import static kammerjaeger.Utils.getTime;

public class KammerJaegerGame extends ApplicationAdapter implements InputProcessor {




    private static final float MAX_TIME_STEP = 0.01f;
    
    

    OrthographicCamera camera;
    TiledMapRenderer tiledMapRenderer;
    com.badlogic.gdx.utils.Array<Rectangle> tiles = new com.badlogic.gdx.utils.Array<Rectangle>();
    Map map = new Map();
    Rectangle test = new Rectangle();






    private World world;
    private float lastWorldStepTime;
	
	@Override
	public void create () {

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        camera = new OrthographicCamera();
        camera.setToOrtho(false,w,h);
        camera.update();
        tiledMapRenderer = new OrthogonalTiledMapRenderer(Map.getMap("Level_1"));
        Gdx.input.setInputProcessor(this);
        test.setHeight(5);
        test.setWidth(5);



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
        camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
        test.setX(Gdx.input.getX() / 16);
        test.setY((Gdx.graphics.getHeight() - Gdx.input.getY()) / 16);

        tiles = map.getMapCollison((int)test.getX(),(int)test.getY(),(int)test.getX(),(int)test.getY());
        for (Rectangle tile : tiles) {

            if(test.overlaps(tile)){
                System.out.print("ACHTUNG\n");
            }
        }




        stepWorld();
	}

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
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
