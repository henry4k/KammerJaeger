package kammerjaeger;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import kammerjaeger.control.Control;
import kammerjaeger.entity.PlayerEntity;
import kammerjaeger.map.Map;

import com.badlogic.gdx.utils.Array;
import kammerjaeger.entity.EntityManager;
import kammerjaeger.graphics.Renderer;


public class KammerJaegerGame extends ApplicationAdapter implements InputProcessor {

	SpriteBatch batch;
    OrthographicCamera camera;
    TiledMapRenderer tiledMapRenderer;
    Array<Rectangle> tiles = new com.badlogic.gdx.utils.Array<Rectangle>();
    Map map = new Map();
    Rectangle test = new Rectangle();

    private World physicsWorld;
    private AssetManager assetManager = new AssetManager();
    private Renderer renderer;
    private EntityManager entityManager;
    private PlayerEntity playerEntity;
    private Vector2 mousePosition;
    private Vector2 playerPosition;





    private float roatation;
    Control keyboard = new Control();

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

        physicsWorld = new World(new Vector2(0,0), false);
        physicsWorld.setAutoClearForces(false);

        assetManager.load("player/Arms.png", Texture.class);
        assetManager.load("player/Gun.png", Texture.class);
        assetManager.load("player/Head.png", Texture.class);
        assetManager.load("player/Torso.png", Texture.class);

        assetManager.finishLoading();
        renderer = new Renderer(assetManager);
        entityManager = new EntityManager(physicsWorld);


        playerEntity = new PlayerEntity(entityManager);
        entityManager.addEntity(playerEntity);

        playerPosition = new Vector2(250f, 250f);
        playerEntity.setPosition(playerPosition);
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
        final Vector2 playerPosition = playerEntity.getPosition();
        mousePosition = new Vector2(new Vector2(Gdx.input.getX(),Gdx.graphics.getHeight() - Gdx.input.getY()));









        roatation = (mousePosition.sub(playerPosition).angle()) - 90;


        playerEntity.setRotation(roatation);


        tiles = map.getMapCollison((int)test.getX(),(int)test.getY(),(int)test.getX(),(int)test.getY());
        for (Rectangle tile : tiles) {

            if(test.overlaps(tile)){
                System.out.print("ACHTUNG\n");
            }
        }

        entityManager.step();

        renderer.beginFrame();
        entityManager.render(renderer);
        renderer.endFrame();
	}

    @Override
    public boolean keyDown(int keycode) {
        keyboard.keyboard(keycode, "DOWN", playerEntity);
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return keyboard.keyboard(keycode, "UP", playerEntity);

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
        entityManager.dispose();
        physicsWorld.dispose();
    }
}
