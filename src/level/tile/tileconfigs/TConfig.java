package level.tile.tileconfigs;

import graphics.Sprite;
import level.tile.Tile;
import level.tile.TileConfig;
import graphics.Screen;

/**
 * Created by Christian on 7/28/2016.
 */

public class TConfig extends TileConfig {

    public TConfig(Sprite sprite, int x, int y, char rotation) {
        switch(rotation) {
            case 'U':
                tiles[0] = new Tile(sprite, x  , y);
                tiles[1] = new Tile(sprite, x+1, y);
                tiles[2] = new Tile(sprite, x+2, y);
                tiles[3] = new Tile(sprite, x+1, y+1);
                tiles[4] = new Tile(sprite, x+1, y+2);
                tiles[5] = new Tile(sprite, x+1, y+3);
                break;
            case 'D':
                tiles[0] = new Tile(sprite, x+1, y);
                tiles[1] = new Tile(sprite, x+1, y+1);
                tiles[2] = new Tile(sprite, x+1, y+2);
                tiles[3] = new Tile(sprite, x  , y+3);
                tiles[4] = new Tile(sprite, x+1, y+3);
                tiles[5] = new Tile(sprite, x+2, y+3);
                break;
            case 'L':
                tiles[0] = new Tile(sprite, x, y);
                tiles[1] = new Tile(sprite, x, y+1);
                tiles[2] = new Tile(sprite, x, y+2);
                tiles[3] = new Tile(sprite, x+1, y+1);
                tiles[4] = new Tile(sprite, x+2, y+1);
                tiles[5] = new Tile(sprite, x+3, y+1);
                break;
            case 'R':
                tiles[0] = new Tile(sprite, x  , y+1);
                tiles[1] = new Tile(sprite, x+1, y+1);
                tiles[2] = new Tile(sprite, x+2, y+1);
                tiles[3] = new Tile(sprite, x+3, y+1);
                tiles[4] = new Tile(sprite, x+3, y);
                tiles[5] = new Tile(sprite, x+3, y+2);

        }
    }

    public boolean usable(int x, int y) {
        if (x < 0 || x + 3 > Screen.MAP_SIZE_MASK || y < 0 || y + 3 > Screen.MAP_SIZE_MASK) {
            return false;
        }
        return true;
    }
}
