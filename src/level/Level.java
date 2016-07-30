package level;

import graphics.Screen;
import graphics.Sprite;
import level.tile.Tile;
import level.tile.TileConfig;

/**
 * Created by Christian on 7/28/2016.
 */
public class Level {


    protected int width, height;
    protected Tile[][] tiles;

    public Level(int width, int height) {
        this.width = width;
        this.height = height;
        tiles = new Tile[height][width];
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                tiles[i][j] = new Tile(Sprite.mapTile, i, j);
            }
        }
    }

    public void add(Tile t) {
        int x = t.x;
        int y = t.y;
        tiles[x][y] = t;
    }

    public void addTiles(TileConfig tc) {
        for(Tile t : tc) {
            add(t);
        }
    }

    public void render(int xScroll, int yScroll, Screen screen) {
        screen.setOffset(xScroll, yScroll);
        int x0 = xScroll >> 4; //same as divide by 16 (size of tile), so creating tile number
        int x1 = (xScroll + screen.width + 16) >> 4;
        int y0 = yScroll >> 4;
        int y1 = (yScroll + screen.height + 16) >> 4;

        for (int y = y0; y < y1; y ++) {
            for (int x = x0; x < x1; x++) {
                getTile(x, y).render(screen);
            }
        }

    }

    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) {
            return new Tile(Sprite.voidTile, x, y);
        } else {
            return tiles[x][y];
        }
    }

    public void update() {

    }

}
