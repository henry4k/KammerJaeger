package kammerjaeger;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import kammerjaeger.control.Control;
import kammerjaeger.entity.EntityManager;
import kammerjaeger.entity.PlayerEntity;
import kammerjaeger.graphics.Renderer;
import kammerjaeger.map.Map;

public class KammerJaegerGame extends ApplicationAdapter implements InputProcessor {

    private final float VIEW_SCALE = 3;
    private final int TILE_SIZE = 16;

    private OrthographicCamera camera;
    private TiledMapRenderer tiledMapRenderer;
    private World physicsWorld;
    private AssetManager assetManager = new AssetManager();
    private Renderer renderer;
    private EntityManager entityManager;
    private PlayerEntity playerEntity;
    private Control keyboard = new Control();

	@Override
	public void create () {

        camera = new OrthographicCamera();

        final TiledMap tiledMap = Map.getMap("Level_1");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        Gdx.input.setInputProcessor(this);

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
        playerEntity.setPosition(new Vector2(250, 250));
        entityManager.addEntity(playerEntity);

        createBodiesFromMap(tiledMap);
	}

	@Override
	public void render() {

        final Vector2 mousePosition = getViewSpaceMousePosition();
        final Vector2 playerPosition = playerEntity.getPosition();

        final float rotation = (mousePosition.sub(playerPosition).angle()) - 90;

        playerEntity.setPosition(playerPosition);
        playerEntity.setRotation(rotation);

        updateCamera();

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();

        entityManager.step();

        renderer.beginFrame(camera);
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
        keyboard.keyboard(keycode, "UP", playerEntity);
        return true;
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

    private void updateCamera() {

        final float width = Gdx.graphics.getWidth();
        final float height = Gdx.graphics.getHeight();

        final Vector2 playerPosition = playerEntity.getPosition();

        final float viewWidth = width / VIEW_SCALE;
        final float viewHeight = height / VIEW_SCALE;

        camera.setToOrtho(false, viewWidth, viewHeight);
        camera.translate(playerPosition.cpy().add(-viewWidth/2, -viewHeight/2));
        camera.update();
    }

    private Vector2 getViewSpaceMousePosition() {

        final Vector3 sceenSpaceMousePosition = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        final Vector3 viewSpaceMousePosition = camera.unproject(sceenSpaceMousePosition);
        return new Vector2(viewSpaceMousePosition.x, viewSpaceMousePosition.y);
    }

    private void createBodiesFromMap( TiledMap map ) {

        final BodyDef tileBodyDef = new BodyDef();
        tileBodyDef.type = BodyDef.BodyType.StaticBody;

        final TiledMapTileLayer collisionLayer = (TiledMapTileLayer)map.getLayers().get("Collison");

        for(int y = 0; y <= collisionLayer.getWidth(); y++)
        for(int x = 0; x <= collisionLayer.getHeight(); x++) {

            if(collisionLayer.getCell(x,y) != null) {

                final Body tileBody = physicsWorld.createBody(tileBodyDef);

                tileBody.setTransform(x*TILE_SIZE + TILE_SIZE/2, y*TILE_SIZE + TILE_SIZE/2, 0);

                final PolygonShape tileShape = new PolygonShape();
                tileShape.setAsBox(TILE_SIZE/2, TILE_SIZE/2);
                tileBody.createFixture(tileShape, 1);
            }
        }
    }


}
