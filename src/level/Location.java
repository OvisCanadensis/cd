package level;

import graphics.Sprite;
import input.KeyBoard;

/**
 * Created by Christian on 7/28/2016.
 */
public class Location {

    private int x;
    private int y;
    private KeyBoard input;

    public Location(KeyBoard input) {
        this.input = input;
    }

    public Location(int x, int y, KeyBoard input) { //enter at a different location
        this.x = x;
        this.y = y;
        this.input = input;
    }

    public void update() {
        int xa = 0, ya = 0;

        if (input.up) ya--;
        if (input.down) ya++;
        if (input.left) xa--;
        if (input.right) xa++;
        if (xa != 0 || ya != 0) {
            move(xa, ya);
        }
    }

    public void move(int xa, int ya) { //plug in -1, 0, 1
        x += xa;
        y += ya;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private boolean collision() {
        return false;
    }
}
