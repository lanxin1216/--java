package game;
import window.*;
import java.awt.*;

/*
游戏所需要保护的固定家，
 */
public class Home {
	private int x, y;
	private GameWindow02 tc;
	public static final int width = 43, length = 43; 
	private boolean live = true;

	private static Toolkit tk = Toolkit.getDefaultToolkit(); 
	private static Image[] homeImags = null;
	static {
		homeImags = new Image[] { tk.getImage(CommonWall.class
				.getClassLoader().getResource("Images/home.jpg")), };
	}

	public Home(int x, int y, GameWindow02 tc) {
		this.x = x;
		this.y = y;
		this.tc = tc; 
	}

	public void gameOver(Graphics g) {

		tc.tanks.clear();
		tc.metalWall.clear();
		tc.otherWall.clear();
		tc.bombTanks.clear();
		tc.theRiver.clear();
		tc.trees.clear();
		tc.bullets.clear();
		tc.homeTank.setLive(false);
		Color c = g.getColor(); 
		g.setColor(Color.green);
		Font f = g.getFont();
		g.setFont(new Font(" ", Font.PLAIN, 40));
		g.setFont(f);
		g.setColor(c);

	}

	public void draw(Graphics g) {

		if (live) { 
			g.drawImage(homeImags[0], x, y, null);

			for (int i = 0; i < tc.homeWall.size(); i++) {
				CommonWall w = tc.homeWall.get(i);
				w.draw(g);
			}
		} else {
			gameOver(g); 

		}
	}

	public boolean isLive() { 
		return live;
	}

	public void setLive(boolean live) { 
		this.live = live;
	}

	public Rectangle getRect() { 
		return new Rectangle(x, y, width, length);
	}

}
