package window;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
启动器界面窗口设计

 */
public class GameWindow01 extends JFrame {

    public static boolean printable = true;

    /*窗口界面*/
    public GameWindow01() {

        JFrame frame = new JFrame();
        setTitle("坦克大战"); // 设置窗口标题
        setSize(800, 600); // 设置窗口大小
        setLocationRelativeTo(null); // 窗口居中显示
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 关闭窗口时退出程序

        // 创建一个自定义的 JPanel 来绘制背景图片
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image backgroundImage = new ImageIcon("images/背景图片.jpg").getImage();
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };

        //调用按钮方法（button）
        button(panel);

        // 将面板添加到 JFrame 的 contentPane 中
        getContentPane().add(panel);
        setVisible(true);//显示窗口
    }

    /*设置界面按钮的方法*/
    public void button(JPanel panel) {
        //使用createButton方法创建按键，按键可设置文本，按键大小，字体，字体大小
        JButton startButton = createButton("开始游戏", 220, 50, "黑体", 45);
        JButton dualPlayerButton = createButton("双人游戏", 220, 50, "黑体", 45);
        JButton exitButton = createButton("退出游戏", 220, 50, "黑体", 45);
        JButton aboutButton = createButton("关于", 100, 50, "黑体", 30);
        JButton helpButton = createButton("帮助", 100, 50, "黑体", 30);

        // 设置主面板的布局为null
        panel.setLayout(null);

        // 设置按钮的位置和大小
        startButton.setBounds(450, 250, 220, 50); // x坐标、y坐标、宽度、高度
        dualPlayerButton.setBounds(450, 310, 220, 50);
        exitButton.setBounds(450, 370, 220, 50);
        aboutButton.setBounds(450, 440, 100, 50);
        helpButton.setBounds(560, 440, 100, 50);

        // 将按钮添加到主面板中
        panel.add(startButton);
        panel.add(dualPlayerButton);
        panel.add(exitButton);
        panel.add(aboutButton);
        panel.add(helpButton);

        //配置各个按键的功能
        // 创建通用的事件监听器对象
        ButtonClickListener buttonClickListener = new ButtonClickListener();

        startButton.addActionListener(buttonClickListener);
        dualPlayerButton.addActionListener(buttonClickListener);
        exitButton.addActionListener(buttonClickListener);
        aboutButton.addActionListener(buttonClickListener);
        helpButton.addActionListener(buttonClickListener);

    }

    /*界面按键设置，文本、尺寸、字体和字体大小的按钮*/
    private JButton createButton(String text, int width, int height, String fontName, int fontSize) {
        JButton button = new JButton(text);// 创建加粗字体
        Font buttonFont = new Font(fontName, Font.BOLD, fontSize);
        button.setFont(buttonFont); // 设置按钮字体
        button.setPreferredSize(new Dimension(width, height)); // 设置按钮的大小

        button.setOpaque(false); // 设置按钮透明
        button.setContentAreaFilled(false); // 设置按钮的内容区域透明
        button.setFocusPainted(false); // 设置按钮无焦点样式
        button.setBorderPainted(false); // 设置按钮无边框样式
        button.setForeground(Color.RED); // 设置按钮字体颜色

        return button;
    }

    /*监听器监听按钮被点击之后的执行逻辑 */
    //创建一个实现ActionListener接口的类，该类将包含按钮点击后要执行的逻辑。
    public class ButtonClickListener extends Component implements ActionListener {
        private static final int DELAY = 100; // 灰暗状态的持续时间（以毫秒为单位）

        @Override
        public void actionPerformed(ActionEvent e) {
            // 按钮被点击时的操作
            // 修改按钮的外观属性，实现灰暗效果
            // 获取触发事件的按钮
            JButton sourceButton = (JButton) e.getSource();

            sourceButton.setEnabled(false); // 禁用按钮
            Timer timer = new Timer(DELAY, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    sourceButton.setEnabled(true); // 恢复按钮可用状态
                }
            });
            timer.setRepeats(false); // 仅执行一次
            timer.start(); // 启动计时器

            // 判断触发事件的按钮是哪个，并执行相应的逻辑
            if (sourceButton.getText().equals("开始游戏")) {
                int result = JOptionPane.showConfirmDialog(this, "确定开始游戏？", "开始游戏",
                        JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    GameWindow02 gameWindow = new GameWindow02();
                    gameWindow.setVisible(true);
                }
            } else if (sourceButton.getText().equals("双人游戏")) {
                // 调用其他类中的方法，执行双人游戏逻辑
                int result = JOptionPane.showConfirmDialog(this, "确定开始双人游戏？", "双人游戏",
                        JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    GameWindow02 Player2add=new GameWindow02();
                    Player2add.Player2=true;
                }
                } else if (sourceButton.getText().equals("退出游戏")) {
                    // 退出游戏按钮点击后显示确认框
                    int result = JOptionPane.showConfirmDialog(this, "确定要退出游戏吗？", "退出游戏",
                            JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        // 用户点击了确认按钮
                        System.exit(0);
                    }
                } else if (sourceButton.getText().equals("关于")) {
                    // 按钮点击后显示弹出框
                    JOptionPane.showMessageDialog(this, "                    ❤  哈尔滨师范大学  ❤\n" +
                            "\n" + "计算机科学与信息工程学院❤数据科学与大数据技术\n" + "\n" + "开发人员：王雨彤、顾思雨\n" + "                 蓝   鑫、陈宇霆、熊仓煜", "信息",
                            JOptionPane.INFORMATION_MESSAGE);
                } else if (sourceButton.getText().equals("帮助")) {
                    // 帮助按钮点击后显示消息框
                    JOptionPane.showMessageDialog(this, "使用W~S~A~D控制Player1的方向，\n使用F开火\n按R重新启动\n使用diection键控制Player2，使用斜线（/）开火", "帮助",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }