package game;

import graphics.Sprite;
import graphics.SpriteSheet;
import input.KeyBoard;
import entity.mob.Player;
import level.Level;
import graphics.Screen;
import level.Location;
import entity.EntityList;
import level.tile.tileconfigs.TConfig;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;


/**
 * Created by Christian on 7/28/2016.
 */
public class Game extends Canvas implements Runnable{
    private static final long serialVersionUID = 1L;

    public static int width = 600;
    public static int height = (width / 16) * 12;
    public static int scale = 2;
    public static String title = "Game";

    private Thread thread;
    private JFrame frame;
    private KeyBoard key;
    private Level level;
    private Player player;
    private EntityList entityList;
    private boolean running = false;

    private Screen screen;
    private Location location;

    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

    public Game() {
        Dimension size = new Dimension(width * scale, height * scale);
        setPreferredSize(size);

        screen = new Screen(width, height);
        frame = new JFrame();
        key = new KeyBoard();
        level = new Level(20, 20);
        TConfig t = new TConfig(Sprite.dirtTile, 10, 10, 'U');
        level.addTiles(t);
        t = new TConfig(Sprite.dirtTile, 15, 15, 'R');
        level.addTiles(t);
        location = new Location(key);
        player = null;
        entityList = null;

        addKeyListener(key);
    }

    public synchronized void start() {
        running = true;
        thread = new Thread(this, "Display");
        thread.start();
    }

    public synchronized void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ns = 1000000000.0 / 60.0;
        double delta = 0;
        int frames = 0, updates = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            requestFocus();
            while (delta >= 1) {
                update();
                updates++;
                delta--;
            }
            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frame.setTitle(title + " | " + updates + " ups, " + frames + " fps");
                updates = 0;
                frames = 0;
            }
        }
    }

    public void update() {
        key.update();
        level.update();
        location.update();
    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        screen.clear();
        int xScroll = location.getX() - screen.width / 10;
        int yScroll = location.getY() - screen.height / 10;
        level.render(xScroll, yScroll, screen);
        //entityList.render(xScroll, yScroll, screen);

        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = screen.pixels[i];
        }

        Graphics g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        g.dispose();
        bs.show();
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.frame.setResizable(false);
        game.frame.setTitle(Game.title);
        game.frame.add(game);
        game.frame.pack();
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.frame.setLocationRelativeTo(null);
        game.frame.setVisible(true);

        game.start();
    }

}
