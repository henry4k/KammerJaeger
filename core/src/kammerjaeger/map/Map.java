package kammerjaeger.map;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;


/**
 * Created by Sven on 21.06.2014.
 * Im the King of the Hill
 */
public class Map {

    static TiledMap map;
    private Array<Rectangle> tiles = new Array<Rectangle>();
    public Pool<Rectangle> rectPool = new Pool<Rectangle>() {
        @Override
        protected Rectangle newObject () {
            return new Rectangle();
        }
    };

    public static TiledMap getMap(String mapName)
    {
        mapName = mapName + ".tmx";
        map = new TmxMapLoader().load("Maps/" + mapName);
        return map;
    }

    public Array<Rectangle> getMapCollison(int startX, int startY, int endX, int endY)
    {
        TiledMapTileLayer layer = (TiledMapTileLayer)map.getLayers().get("Collison");
        rectPool.freeAll(tiles);
        tiles.clear();
        for (int y = startY; y <= endY; y++) {
            for (int x = startX; x <= endX; x++) {
                TiledMapTileLayer.Cell cell = layer.getCell(x, y);
                if (cell != null) {
                    Rectangle rect = rectPool.obtain();
                    rect.set(x, y, 1, 1);
                    tiles.add(rect);
                }
            }
        }
    return tiles;
    }

}