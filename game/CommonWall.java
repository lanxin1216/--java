package game;
import window.*;
import java.awt.*;
/*
游戏界面的墙体设计，可以被子弹击破的墙体
 */
public class CommonWall {
    public static final int width = 22;
    public static final int length = 21;
    int x, y;

    GameWindow02 tc;
    private static Toolkit tk = Toolkit.getDefaultToolkit();
    private static Image[] wallImags = null;
    static {
        wallImags = new Image[] {
                tk.getImage(CommonWall.class.getClassLoader().getResource("Images/commonWall.gif")), };
    }

    public CommonWall(int x, int y, GameWindow02 tc) {
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
