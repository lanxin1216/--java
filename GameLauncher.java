import window.GameWindow01;
import javax.swing.*;
/*
坦克大战游戏的启动代码，主程序。
 */
public class GameLauncher {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GameWindow01 GameWindow01 = new GameWindow01();
            }
        });
    }
}
