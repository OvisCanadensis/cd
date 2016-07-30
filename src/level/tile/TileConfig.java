package level.tile;

import java.util.Iterator;

/**
 * Created by Christian on 7/28/2016.
 */
public abstract class TileConfig implements Iterable<Tile>{

    private int x;
    private int y;

    protected Tile[] tiles = new Tile[6];

    public Tile[] getTiles() {
        return tiles;
    }

    public abstract boolean usable(int x, int y);

    public Iterator<Tile> iterator() {
        return new TileIterator();
    }

    public class TileIterator implements Iterator<Tile> {

        private int index;

        public TileIterator() {
            index = 0;
        }

        public boolean hasNext() {
            return index < tiles.length;
        }

        public Tile next() {
            Tile rVal = tiles[index];
            index++;
            return rVal;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

}
