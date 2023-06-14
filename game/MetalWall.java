package game;
import window.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
/*
金属墙，不可以被击破
 */
public class MetalWall {
    public static final int width = 36;
    public static final int length = 37;
    private int x, y;
    GameWindow02 tc;
    private static Toolkit tk = Toolkit.getDefaultToolkit();
    private static Image[] wallImags = null;
    static {
        wallImags = new Image[] { tk.getImage(CommonWall.class
                .getClassLoader().getResource("Images/metalWall.gif")), };
    }

    public MetalWall(int x, int y, GameWindow02 tc) {
        this.x = x;
        this.y = y;
        this.tc = tc;
    }

    public void draw(Graphics g) {
        g.drawImage(wallImags[0], x, y, null);
    }

    public Rectangle getRect() {
        return new Rectangle(x, y, width, length);
    }
}
