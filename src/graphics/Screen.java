package graphics;

import level.Level;
import level.tile.Tile;

/**
 * Created by Christian on 7/28/2016.
 */
public class Screen {

    public int width, height;
    public int[] pixels;
    public static final int MAP_SIZE = 20;
    public static final int MAP_SIZE_MASK = MAP_SIZE - 1;
    public int xOffset, yOffset;
    public int[] tiles = new int[MAP_SIZE * MAP_SIZE];

    public Screen(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width * height];
    }

    public void clear() {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0;
        }
    }

    public void renderTile(int xp, int yp, Tile tile) {
        xp -= xOffset; //because walking right is map scrolling left
        yp -= yOffset;
        for (int y = 0; y < tile.sprite.SIZE; y++) {
            int ya = y + yp; //absolute value that gets offset
            for (int x = 0; x < tile.sprite.SIZE; x++) {
                int xa = x + xp;
                if (xa < -tile.sprite.SIZE || xa >= width - width/4 || ya < 0 || ya >= height - height/4) break; //if a tile would exit the screen, stop the loop b/c tilemap can be really big
                if (xa < 0) xa = 0;
                pixels[xa + ya * width] = tile.sprite.pixels[x + y * tile.sprite.SIZE]; //pixels on the sprite are not offset
            }
        }
    }

    public void setOffset(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

}
