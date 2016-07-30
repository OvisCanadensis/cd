package level.tile;

import graphics.Screen;
import graphics.Sprite;

/**
 * Created by Christian on 7/28/2016.
 */
public class Tile {

    public int x, y;
    public Sprite sprite;

    public Tile(Sprite sprite, int x, int y) {
        this.sprite = sprite;
        this.x = x;
        this.y =y;
    }

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void render(Screen screen) {
        screen.renderTile(x << 4, y << 4, this);
    }

    public boolean solid() {
        return false; //this is not solid if we don't override it
    }
}
