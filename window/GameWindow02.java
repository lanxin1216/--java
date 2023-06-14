package window;

import game.*;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.JOptionPane;
/*
坦克客户端
 */

public class GameWindow02 extends Frame implements ActionListener {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public static final int Fram_width = 800;
    public static final int Fram_length = 600;
    public static boolean printable = true;
    MenuBar jmb = null;
    Menu jm1 = null, jm2 = null, jm3 = null, jm4 = null,jm5=null;
    MenuItem jmi1 = null, jmi2 = null, jmi3 = null, jmi4 = null, jmi5 = null,
            jmi6 = null, jmi7 = null, jmi8 = null, jmi9 = null,jmi10=null,jmi11=null;
    Image screenImage = null;

    public Tank homeTank = new Tank(300, 560, true, Direction.STOP, this,1);
    public Tank homeTank2 = new Tank(449, 560,true,Direction.STOP,this,2);
    public Boolean Player2=false;
    public GetBlood blood = new GetBlood();
    public Home home = new Home(373, 557, this);
    public Boolean win=false,lose=false;
    public List<River> theRiver = new ArrayList<River>();
    public List<Tank> tanks = new ArrayList<Tank>();
    public List<BombTank> bombTanks = new ArrayList<BombTank>();
    public List<Bullets> bullets = new ArrayList<Bullets>();
    public List<Tree> trees = new ArrayList<Tree>();
    public List<CommonWall> homeWall = new ArrayList<CommonWall>();
    public List<CommonWall> otherWall = new ArrayList<CommonWall>();
    public List<MetalWall> metalWall = new ArrayList<MetalWall>();

    public void update(Graphics g) {

        screenImage = this.createImage(Fram_width, Fram_length);

        Graphics gps = screenImage.getGraphics();
        Color c = gps.getColor();
        gps.setColor(Color.GRAY);
        gps.fillRect(0, 0, Fram_width, Fram_length);
        gps.setColor(c);
        framPaint(gps);
        g.drawImage(screenImage, 0, 0, null);
    }

    public void framPaint(Graphics g) {

        Color c = g.getColor();
        g.setColor(Color.black);

        //对游戏界面顶端的数据布局
        Font f1 = g.getFont();
        g.setFont(new Font("宋体", Font.BOLD, 20));
        if (!Player2) g.drawString("剩余敌坦克: ", 50, 75);
        else g.drawString("剩余敌坦克: ", 50, 75);
        g.setFont(new Font("宋体", Font.BOLD, 20));
        if (!Player2) g.drawString("" + tanks.size(), 170, 75);
        else g.drawString("" + tanks.size(), 170, 75);
        g.setFont(new Font("宋体", Font.BOLD, 20));
        if (!Player2) g.drawString("血量: ", 580, 75);
        else g.drawString("血量: ", 380, 75);
        g.setFont(new Font("宋体", Font.BOLD, 20));
        if (!Player2) g.drawString("" + homeTank.getLife(), 650, 75);
        else g.drawString("玩家1:  " + homeTank.getLife() + "    玩家2:  " + homeTank2.getLife(), 450, 75);
        g.setFont(f1);


        //第一个为没有玩家二的情况下判断胜负。结果出来，暂停游戏出现弹窗。
        //两次弹窗按钮在再来一次的情况下会执行不同的对于逻辑。
        if (!Player2) {
            if (tanks.size() == 0 && home.isLive() && homeTank.isLive() && !lose) {
                printable = false;
                int option = JOptionPane.showOptionDialog(this, "恭喜通关！", "通关",
                        JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"再来一局", "退出"}, null);
                if (option == JOptionPane.YES_OPTION) {
                    // 重新开始逻辑，对应单人模式
                    GameWindow02 gameWindow = new GameWindow02();
                    gameWindow.setVisible(true);
                } else if (option == JOptionPane.NO_OPTION) {
                    // 退出逻辑
                    dispose(); // 关闭当前窗口
                }
                win = true;
            }

            if (!homeTank.isLive() && !win) {
                printable = false;
                int option = JOptionPane.showOptionDialog(this, "很抱歉你输了！", "失败",
                        JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"重新开始", "退出"}, null);
                if (option == JOptionPane.YES_OPTION) {
                    // 重新开始逻辑，对应单人模式
                    GameWindow02 gameWindow = new GameWindow02();
                    gameWindow.setVisible(true);
                } else if (option == JOptionPane.NO_OPTION) {
                    // 退出逻辑
                    dispose(); // 关闭当前窗口
                }
                lose = true;
            }
        } else {     //以下为有两个玩家的情况下，判断胜负
            if (tanks.size() == 0 && home.isLive() && (homeTank.isLive() || homeTank2.isLive()) && !lose) {
                printable = false;
                int option = JOptionPane.showOptionDialog(this, "恭喜通关！", "通关",
                        JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"再来一局", "退出"}, null);
                if (option == JOptionPane.YES_OPTION) {
                    // 重新开始逻辑，对应双人模式
                    GameWindow02 Player2add=new GameWindow02();
                    Player2add.Player2=true;
                } else if (option == JOptionPane.NO_OPTION) {
                    // 退出逻辑
                    dispose(); // 关闭当前窗口
                }
                win = true;
            }

            if (!homeTank.isLive() && !homeTank2.isLive() && !win) {
                printable = false;
                int option = JOptionPane.showOptionDialog(this, "很抱歉你输了！", "失败",
                        JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"重新开始", "退出"}, null);
                if (option == JOptionPane.YES_OPTION) {
                    // 重新开始逻辑，对应双人模式
                    GameWindow02 Player2add=new GameWindow02();
                    Player2add.Player2=true;
                } else if (option == JOptionPane.NO_OPTION) {
                    // 退出逻辑
                    dispose(); // 关闭当前窗口
                }
                lose = true;
            }
        }
        g.setColor(c);

        for (int i = 0; i < theRiver.size(); i++) {
            River r = theRiver.get(i);
            r.draw(g);
        }

        for (int i = 0; i < theRiver.size(); i++) {
            River r = theRiver.get(i);
            homeTank.collideRiver(r);
            if(Player2) homeTank2.collideRiver(r);
            r.draw(g);
        }

        home.draw(g);
        homeTank.draw(g);
        homeTank.eat(blood);
        if (Player2) {homeTank2.draw(g);homeTank2.eat(blood);}

        for (int i = 0; i < bullets.size(); i++) {
            Bullets m = bullets.get(i);
            m.hitTanks(tanks);
            m.hitTank(homeTank);
            m.hitTank(homeTank2);
            m.hitHome();
            for(int j=0;j<bullets.size();j++){
                if (i==j) continue;
                Bullets bts=bullets.get(j);
                m.hitBullet(bts);
            }
            for (int j = 0; j < metalWall.size(); j++) {
                MetalWall mw = metalWall.get(j);
                m.hitWall(mw);
            }

            for (int j = 0; j < otherWall.size(); j++) {
                CommonWall w = otherWall.get(j);
                m.hitWall(w);
            }

            for (int j = 0; j < homeWall.size(); j++) {
                CommonWall cw = homeWall.get(j);
                m.hitWall(cw);
            }
            m.draw(g);
        }

        for (int i = 0; i < tanks.size(); i++) {
            Tank t = tanks.get(i);

            for (int j = 0; j < homeWall.size(); j++) {
                CommonWall cw = homeWall.get(j);
                t.collideWithWall(cw);
                cw.draw(g);
            }
            for (int j = 0; j < otherWall.size(); j++) {
                CommonWall cw = otherWall.get(j);
                t.collideWithWall(cw);
                cw.draw(g);
            }
            for (int j = 0; j < metalWall.size(); j++) {
                MetalWall mw = metalWall.get(j);
                t.collideWithWall(mw);
                mw.draw(g);
            }
            for (int j = 0; j < theRiver.size(); j++) {
                River r = theRiver.get(j);
                t.collideRiver(r);
                r.draw(g);
                // t.draw(g);
            }

            t.collideWithTanks(tanks);
            t.collideHome(home);

            t.draw(g);
        }

        //blood.draw(g);

        for (int i = 0; i < trees.size(); i++) {
            Tree tr = trees.get(i);
            tr.draw(g);
        }

        for (int i = 0; i < bombTanks.size(); i++) {
            BombTank bt = bombTanks.get(i);
            bt.draw(g);
        }

        for (int i = 0; i < otherWall.size(); i++) {
            CommonWall cw = otherWall.get(i);
            cw.draw(g);
        }

        for (int i = 0; i < metalWall.size(); i++) {
            MetalWall mw = metalWall.get(i);
            mw.draw(g);
        }

        homeTank.collideWithTanks(tanks);
        homeTank.collideHome(home);
        if (Player2) {homeTank2.collideWithTanks(tanks);
            homeTank2.collideHome(home);}

        for (int i = 0; i < metalWall.size(); i++) {
            MetalWall w = metalWall.get(i);
            homeTank.collideWithWall(w);
            if (Player2)homeTank2.collideWithWall(w);
            w.draw(g);
        }

        for (int i = 0; i < otherWall.size(); i++) {
            CommonWall cw = otherWall.get(i);
            homeTank.collideWithWall(cw);
            if (Player2)homeTank2.collideWithWall(cw);
            cw.draw(g);
        }

        for (int i = 0; i < homeWall.size(); i++) {
            CommonWall w = homeWall.get(i);
            homeTank.collideWithWall(w);
            if (Player2)homeTank2.collideWithWall(w);
            w.draw(g);
        }

    }

    public GameWindow02() {

        setFocusable(true);  // 获取键盘焦点
        // printable = false;

        jmb = new MenuBar();
        jm1 = new Menu("游戏");
        jm2 = new Menu("暂停/继续");
        jm3 = new Menu("帮助");
        jm4 = new Menu("难度");
        jm5 = new Menu("其他");

        jmi1 = new MenuItem("新游戏");
        jmi2 = new MenuItem("退出");
        jmi3 = new MenuItem("停止");
        jmi4 = new MenuItem("继续");
        jmi5 = new MenuItem("帮助");
        jmi6 = new MenuItem("一级");
        jmi7 = new MenuItem("二级");
        jmi8 = new MenuItem("三级");
        jmi9 = new MenuItem("四级");
        jmi10=new MenuItem("添加玩家2");
        jmi11= new MenuItem("加入游戏");

        Font font = new Font("宋体", Font.PLAIN, 15);
        jm1.setFont(font);
        jm2.setFont(font);
        jm3.setFont(font);
        jm4.setFont(font);
        jm5.setFont(font);
        jmi1.setFont(font);
        jmi2.setFont(font);
        jmi3.setFont(font);
        jmi4.setFont(font);
        jmi5.setFont(font);
        jmi6.setFont(font);
        jmi7.setFont(font);
        jmi8.setFont(font);
        jmi9.setFont(font);
        jmi10.setFont(font);
        jmi11.setFont(font);

        jm1.add(jmi1);
        jm1.add(jmi2);
        jm2.add(jmi3);
        jm2.add(jmi4);
        jm3.add(jmi5);
        jm4.add(jmi6);
        jm4.add(jmi7);
        jm4.add(jmi8);
        jm4.add(jmi9);
        jm5.add(jmi10);
        jm5.add(jmi11);

        jmb.add(jm1);
        jmb.add(jm2);

        jmb.add(jm4);
        jmb.add(jm5);
        jmb.add(jm3);


        jmi1.addActionListener(this);
        jmi1.setActionCommand("新游戏");
        jmi2.addActionListener(this);
        jmi2.setActionCommand("退出");
        jmi3.addActionListener(this);
        jmi3.setActionCommand("停止");
        jmi4.addActionListener(this);
        jmi4.setActionCommand("继续");
        jmi5.addActionListener(this);
        jmi5.setActionCommand("帮助");
        jmi6.addActionListener(this);
        jmi6.setActionCommand("一级");
        jmi7.addActionListener(this);
        jmi7.setActionCommand("二级");
        jmi8.addActionListener(this);
        jmi8.setActionCommand("三级");
        jmi9.addActionListener(this);
        jmi9.setActionCommand("四级");
        jmi10.addActionListener(this);
        jmi10.setActionCommand("添加玩家2");
        jmi11.addActionListener(this);
        jmi11.setActionCommand("加入游戏");

        this.setMenuBar(jmb);
        this.setVisible(true);

        for (int i = 0; i < 10; i++) {
            if (i < 4)
                homeWall.add(new CommonWall(350, 580 - 21 * i, this));
            else if (i < 7)
                homeWall.add(new CommonWall(372 + 22 * (i - 4), 517, this));
            else
                homeWall.add(new CommonWall(416, 538 + (i - 7) * 21, this));

        }

        for (int i = 0; i < 32; i++) {
            if (i < 16) {
                otherWall.add(new CommonWall(200 + 21 * i, 300, this));
                otherWall.add(new CommonWall(500 + 21 * i, 180, this));
                otherWall.add(new CommonWall(200, 400 + 21 * i, this));
                otherWall.add(new CommonWall(500, 400 + 21 * i, this));
            } else if (i < 32) {
                otherWall.add(new CommonWall(200 + 21 * (i - 16), 320, this));
                otherWall.add(new CommonWall(500 + 21 * (i - 16), 220, this));
                otherWall.add(new CommonWall(222, 400 + 21 * (i - 16), this));
                otherWall.add(new CommonWall(522, 400 + 21 * (i - 16), this));
            }
        }

        for (int i = 0; i < 20; i++) {
            if (i < 10) {
                metalWall.add(new MetalWall(140 + 30 * i, 150, this));
                metalWall.add(new MetalWall(600, 400 + 20 * (i), this));
            } else if (i < 20)
                metalWall.add(new MetalWall(140 + 30 * (i - 10), 180, this));

        }

        for (int i = 0; i < 4; i++) {
            if (i < 4) {
                trees.add(new Tree(0 + 30 * i, 360, this));
                trees.add(new Tree(220 + 30 * i, 360, this));
                trees.add(new Tree(440 + 30 * i, 360, this));
                trees.add(new Tree(660 + 30 * i, 360, this));
            }

        }

        theRiver.add(new River(85, 100, this));

        for (int i = 0; i < 20; i++) {
            if (i < 9)
                tanks.add(new Tank(150 + 70 * i, 40, false, Direction.D, this,0));
            else if (i < 15)
                tanks.add(new Tank(700, 140 + 50 * (i - 6), false, Direction.D,
                        this,0));
            else
                tanks
                        .add(new Tank(10, 50 * (i - 12), false, Direction.D,
                                this,0));
        }

        setSize(Fram_width, Fram_length);
        setLocation(280, 50);
        setTitle("坦克大战");

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        setResizable(false);
        setBackground(Color.GREEN);
        setVisible(true);

        this.addKeyListener(new KeyMonitor());
        new Thread(new PaintThread()).start();
    }

//    public static void main(String[] args) {
//        new GameWindow02();
//    }

    private class PaintThread implements Runnable {
        public void run() {
            // 待办事项自动生成的方法存根
            while (printable) {
                repaint();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class KeyMonitor extends KeyAdapter {

        public void keyReleased(KeyEvent e) {
            homeTank.keyReleased(e);
            homeTank2.keyReleased(e);
        }

        public void keyPressed(KeyEvent e) {
            homeTank.keyPressed(e);
            homeTank2.keyPressed(e);
        }

    }

    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("新游戏")) {
            printable = false;
            Object[] options = { "确认", "取消" };
            int response = JOptionPane.showOptionDialog(this, "确认开始新游戏？", "",
                    JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                    options, options[0]);
            if (response == 0) {

                printable = true;
                this.dispose();
                new GameWindow02();
            } else {
                printable = true;
                new Thread(new PaintThread()).start();
            }

        } else if (e.getActionCommand().endsWith("停止")) {
            printable = false;
            // try {
            // Thread.sleep(10000);
            //
            // } catch (InterruptedException e1) {
            // // TODO Auto-generated catch block
            // e1.printStackTrace();
            // }
        } else if (e.getActionCommand().equals("继续")) {

            if (!printable) {
                printable = true;
                new Thread(new PaintThread()).start();
            }
        } else if (e.getActionCommand().equals("退出")) {
            printable = false;
            Object[] options = { "确认", "取消" };
            int response = JOptionPane.showOptionDialog(this, "确认退出？", "",
                    JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                    options, options[0]);
            if (response == 0) {
                System.out.println("break down");
                System.exit(0);
            } else {
                printable = true;
                new Thread(new PaintThread()).start();

            }

        } else if(e.getActionCommand().equals("添加玩家2")){
            printable = false;
            Object[] options = { "确认", "取消" };
            int response = JOptionPane.showOptionDialog(this, "确认添加玩家2", "",
                    JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                    options, options[0]);
            if (response == 0) {
                printable = true;
                this.dispose();
                GameWindow02 Player2add=new GameWindow02();
                Player2add.Player2=true;
            } else {
                printable = true;
                new Thread(new PaintThread()).start();
            }
        }
        else if (e.getActionCommand().equals("帮助")) {
            printable = false;
            JOptionPane.showMessageDialog(null, "使用WSAD控制Player1的方向，\n使用F开火,按R重新启动\n使用diection键控制Player2，使用斜线开火",
                    "帮助", JOptionPane.INFORMATION_MESSAGE);
            this.setVisible(true);
            printable = true;
            new Thread(new PaintThread()).start();
        } else if (e.getActionCommand().equals("一级")) {
            Tank.count = 12;
            Tank.speedX = 6;
            Tank.speedY = 6;
            Bullets.speedX = 10;
            Bullets.speedY = 10;
            this.dispose();
            new GameWindow02();
        } else if (e.getActionCommand().equals("二级")) {
            Tank.count = 12;
            Tank.speedX = 10;
            Tank.speedY = 10;
            Bullets.speedX = 12;
            Bullets.speedY = 12;
            this.dispose();
            new GameWindow02();

        } else if (e.getActionCommand().equals("三级")) {
            Tank.count = 20;
            Tank.speedX = 14;
            Tank.speedY = 14;
            Bullets.speedX = 16;
            Bullets.speedY = 16;
            this.dispose();
            new GameWindow02();
        } else if (e.getActionCommand().equals("四级")) {
            Tank.count = 20;
            Tank.speedX = 16;
            Tank.speedY = 16;
            Bullets.speedX = 18;
            Bullets.speedY = 18;
            this.dispose();
            new GameWindow02();
        } else if (e.getActionCommand().equals("加入游戏")){
            printable = false;
            String s=JOptionPane.showInputDialog("请输入URL:");
            System.out.println(s);
            printable = true;
            new Thread(new PaintThread()).start();
        }

    }

    //开启双人游戏的调用连接方法
    public void Double(){
        dispose();
        GameWindow02 Player2add=new GameWindow02();
        Player2add.Player2=true;
    }

}
