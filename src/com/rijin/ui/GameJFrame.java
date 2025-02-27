package com.rijin.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameJFrame extends JFrame implements KeyListener, ActionListener{
    //创建一个二维数组来管理图片位置
    int[][] imagePosition = new int[4][4];
    int zeroPositionX, zeroPositionY;
    //提高字符串拼接效率
    StringBuilder sb = new StringBuilder();
    //图片路径
    String path = "image\\animal\\animal3\\";
    //是否胜利
    boolean isWin = false;
    //统计步数
    int count = 0;
    //随机数生成器
    Random r = new Random();

    //创建选项下面的条目对象（原本位于initJMenuBar方法中），这样才能在actionPerformed方法中使用
    JMenuItem replayItem = new JMenuItem("重新游戏");
    JMenuItem reLoginItem = new JMenuItem("重新登录");
    JMenuItem closeItem = new JMenuItem("关闭游戏");
    JMenuItem accountItem = new JMenuItem("公众号");
    JMenuItem girl = new JMenuItem("美女");
    JMenuItem animal = new JMenuItem("动物");
    JMenuItem sport = new JMenuItem("运动");

    // 设置游戏主界面窗口
    public GameJFrame() {
        //初始化界面
        initJFrame();
        
        //初始化菜单
        initJMenuBar();

        //初始化数据（打乱）
        initImagePosition();

        //初始化图片
        initImage();

        //显示界面，应写在最后
        this.setVisible(true);
    }

    private void initImagePosition() {
        int[] temp = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        //打乱数据
        for (int i = 0; i < temp.length; i++) {
            int rint = r.nextInt(temp.length);
            int s = temp[i];
            temp[i] = temp[rint];
            temp[rint] = s;
        }
        //将打乱的数据放入二维数组中，来记录图片位置
        for (int i = 0; i < temp.length; i++) {
            if (temp[i] == 0) {
                zeroPositionX = i % 4;
                zeroPositionY = i / 4;
            }
            imagePosition[i / 4][i % 4] = temp[i];
        }

    }

    private void initImage() {
        //先加载的图片在上方，后加载的图片在下方
        //清空原本已经出现的所有图片
        this.getContentPane().removeAll();
        //是否完成游戏判定
        if (isWin) {
            JLabel v = new JLabel(new ImageIcon("image\\win.png"));
            v.setBounds(203, 280, 197, 73);
            this.getContentPane().add(v);
        }
        //添加步数显示
        JLabel stepCount = new JLabel("步数：" + this.count);
        stepCount.setBounds(50, 30, 100, 20);
        this.getContentPane().add(stepCount);
        //循环加载拼图图片
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (imagePosition[i][j] == 0) {
                    continue;
                }
                //创建一个图片ImageIcon对象
                //ImageIcon icon = new ImageIcon("image\\animal\\animal3\\1.jpg");
                //创建一个JLabel对象（管理容器）
                //JLabel jLabel = new JLabel(icon);
                //以上两行代码可以合并简写
                JLabel jLabel = new JLabel(
                    new ImageIcon(
                        sb.append(path).append(imagePosition[i][j]).append(".jpg").toString()
                    )
                );
                sb.delete(0, sb.length());
                // JLabel jLabel = new JLabel(new ImageIcon(path + imagePosition[i][j] + ".jpg"));
                //指定图片的位置和宽高
                jLabel.setBounds(105 * j + 83, 105 * i + 134, 105, 105);
                //给图片添加边框（往内传入要添加的边框类型，这里使用斜面边框）
                //构造方法中的参数1(RAISED)表示让图片下凹边框，0(LOWERED)表示让图片凸起的边框
                jLabel.setBorder(new BevelBorder(BevelBorder.RAISED));
                //把管理容器添加到界面中
                this.getContentPane().add(jLabel);
            }
        }

        //添加背景图片
        JLabel background = new JLabel(new ImageIcon("image\\background.png"));
        background.setBounds(40, 40, 508, 560);
        //把背景图片添加到界面当中
        this.getContentPane().add(background);

        //刷新界面
        this.getContentPane().repaint();
    }
    
    private void initJFrame() {
        //设置界面宽高
        this.setSize(603, 680);
        //设置界面标题
        this.setTitle("拼图单机版v1.0");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面在屏幕居中
        this.setLocationRelativeTo(null);
        //设置默认关闭方式，默认为1
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //取消界面的默认布局方式，只有取消了才会按照XY轴的形式添加组件
        this.setLayout(null);
        //给整个界面添加键盘监听事件
        this.addKeyListener(this);
    }

    private void initJMenuBar() {
        //创建整个菜单对象
        JMenuBar jMenuBar = new JMenuBar();

        //创建菜单上面的两个选项（功能 关于我们）
        JMenu functionJMenu = new JMenu("功能");
        JMenu aboutJMenu = new JMenu("关于我们");

        JMenu changeImage = new JMenu("更换图片");

        //将图片类别添加到更换图片选项中
        changeImage.add(girl);
        changeImage.add(animal);
        changeImage.add(sport);

        //将每一个选项下面的条目添加到菜单栏的选项中
        functionJMenu.add(changeImage);
        functionJMenu.add(replayItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(closeItem);
        aboutJMenu.add(accountItem);

        //给条目绑定事件
        replayItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);
        accountItem.addActionListener(this);
        girl.addActionListener(this);
        animal.addActionListener(this);
        sport.addActionListener(this);

        //将菜单内的两个选项添加到菜单栏中
        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);

        //给整个界面设置菜单
        this.setJMenuBar(jMenuBar);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        /*用户按下某个键并释放时触发
         * 只处理能产生字符的按键（如数字、字母、符号键等）
         * 无法处理功能键（如Shift、Ctrl、Alt、箭头等）
         */
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (isWin) {
            return;
        }
        /*当用户按下某个键时触发
         * 能处理所有键
         * 若按下后不松开，该方法会反复调用
         */
        //按住a不松时，弹出完整图片，a：65
        int code = e.getKeyCode();
        if (code == 65) {
            //清空原本已经出现的所有图片
            this.getContentPane().removeAll();
            //加载完整图片
            // JLabel fullImage = new JLabel(new ImageIcon(path + "all.jpg"));
            JLabel fullImage = new JLabel(new ImageIcon(sb.append(path).append("all.jpg").toString()));
            sb.delete(0, sb.length());
            fullImage.setBounds(83, 134, 420, 420);
            this.getContentPane().add(fullImage);
            //添加背景图片
            JLabel background = new JLabel(new ImageIcon("image\\background.png"));
            background.setBounds(40, 40, 508, 560);
            //把背景图片添加到界面当中
            this.getContentPane().add(background);
            this.getContentPane().repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //如果游戏已经胜利，禁止操作
        if (isWin) {
            return;
        }
        /*当用户释放某个键时触发
         * 能处理所有键
         */
        //对上、下、左、右进行判断
        //左：37，上：38，右：39，下：40，w：87
        int code = e.getKeyCode();
        switch (code) {
            case 37->{
                if (zeroPositionX == 3) {
                    break;
                }
                imagePosition[zeroPositionY][zeroPositionX] = imagePosition[zeroPositionY][zeroPositionX + 1];
                imagePosition[zeroPositionY][zeroPositionX + 1] = 0;
                zeroPositionX++;
                this.count++;
            }
            case 38->{
                if (zeroPositionY == 3) {
                    break;
                }
                imagePosition[zeroPositionY][zeroPositionX] = imagePosition[zeroPositionY + 1][zeroPositionX];
                imagePosition[zeroPositionY + 1][zeroPositionX] = 0;
                zeroPositionY++;
                this.count++;
            }
            case 39->{
                if (zeroPositionX == 0) {
                    break;
                }
                imagePosition[zeroPositionY][zeroPositionX] = imagePosition[zeroPositionY][zeroPositionX - 1];
                imagePosition[zeroPositionY][zeroPositionX - 1] = 0;
                zeroPositionX--;
                this.count++;
            }
            case 40->{
                if (zeroPositionY == 0) {
                    break;
                }
                imagePosition[zeroPositionY][zeroPositionX] = imagePosition[zeroPositionY - 1][zeroPositionX];
                imagePosition[zeroPositionY - 1][zeroPositionX] = 0;
                zeroPositionY--;
                this.count++;
            }
            case 87->{
                imagePosition = new int[][]{
                    {1, 2, 3, 4},
                    {5, 6, 7, 8},
                    {9, 10, 11, 12},
                    {13, 14, 15, 0}
                };
            }
            default -> { }
        }
        victory();
        initImage();
    }

    public void victory() {
        for (int i = 0; i < 15; i++) {
            if (imagePosition[i / 4][i % 4] != i + 1) {
                return;
            }
        }
        isWin = true;
    }

    public void replayGame() {
        //步数清零
        this.count = 0;
        //重置胜利状态
        isWin = false;
        //重新打乱图片
        initImagePosition();
        //重新加载图片
        initImage();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj == replayItem) {
            replayGame();
        }else if (obj == reLoginItem) {
            //关闭当前界面
            this.setVisible(false);
            //打开登录界面
            new LoginJFrame();
        }else if (obj == closeItem) {
            System.exit(0);
        }else if (obj == accountItem) {
            //创建一个弹窗对象
            JDialog jDialog = new JDialog();
            //设置弹窗标题
            jDialog.setTitle("关于我们");
            //创建图片对象并设置位置和大小
            JLabel jLabel = new JLabel(new ImageIcon("image\\about.png"));
            jLabel.setBounds(0, 0, 258, 258);
            //把图片添加到弹窗中
            jDialog.getContentPane().add(jLabel);
            //给弹窗设置大小
            jDialog.setSize(344, 344);
            //让弹窗置顶
            jDialog.setAlwaysOnTop(true);
            //设置弹窗居中
            jDialog.setLocationRelativeTo(null);
            //弹窗不关闭则无法操作其他界面
            jDialog.setModal(true);
            //设置弹窗可见
            jDialog.setVisible(true);
        }else if (obj == girl) {
            //随机加载13张美女图片中的一张
            int girlNum = r.nextInt(13) + 1;
            path = sb.append("image\\girl\\girl").append(girlNum).append("\\").toString();
            sb.delete(0, sb.length());
            // path = "image\\girl\\girl" + girlNum + "\\";
            //并且重新开始游戏
            replayGame();
        }else if (obj == animal) {
            //随机加载8张动物图片中的一张
            int animalNum = r.nextInt(8) + 1;
            path = sb.append("image\\animal\\animal").append(animalNum).append("\\").toString();
            sb.delete(0, sb.length());
            //并且重新开始游戏
            replayGame();
        }else if (obj == sport) {
            //随机加载10张运动图片中的一张
            int sportNum = r.nextInt(10) + 1;
            path = sb.append("image\\sport\\sport").append(sportNum).append("\\").toString();
            sb.delete(0, sb.length());
            //并且重新开始游戏
            replayGame();
        }
    
    }
}