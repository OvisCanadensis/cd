package graphics;

/**
 * Created by Christian on 7/28/2016.
 */
public class Sprite {


    public final int SIZE;
    private int x, y;
    public int[] pixels;
    private SpriteSheet sheet;

    public static Sprite dirtTile = new Sprite(16, 0, 0, SpriteSheet.tiles);
    public static Sprite mapTile = new Sprite(16, 0xBC88E30);
    public static Sprite voidTile = new Sprite(16, 0xFFCC000);

    public Sprite(int size, int x, int y, SpriteSheet sheet) {
        SIZE = size;
        pixels = new int[SIZE * SIZE];
        this.x = x * SIZE; //coordinates of where sprite is located on SpriteSheet
        this.y = y * SIZE;
        this.sheet = sheet;
        load();
    }

    public Sprite(int size, int color) {
        SIZE = size;
        pixels = new int[SIZE * SIZE];
        setColor(color);
    }

    private void setColor(int color) {
        for (int i = 0; i < SIZE * SIZE; i++) {
            pixels[i] = color;
        }

    }

    private void load() {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++)
                pixels[x + y*SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE]; //take the sprite from the spritesheet pixel array and put it into a smaller one
        }
    }
}
