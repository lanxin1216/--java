package game;
import window.*;
import java.awt.*;
/*设计游戏界面的草丛，坦克可以随便通过*/
public class Tree {
	public static final int width = 30;
	public static final int length = 30;
	int x, y;
	GameWindow02 tc ;
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private static Image[] treeImags = null;
	static {
		treeImags = new Image[]{
				tk.getImage(CommonWall.class.getClassLoader().getResource("Images/tree.gif")),
		};
	}
	
	
	public Tree(int x, int y, GameWindow02 tc) {
		this.x = x;
		this.y = y;
		this.tc = tc;
	}
	
	public void draw(Graphics g) {        
		g.drawImage(treeImags[0],x, y, null);
	}
	
}
