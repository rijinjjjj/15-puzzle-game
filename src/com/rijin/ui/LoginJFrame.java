package com.rijin.ui;

import java.util.ArrayList;
import javax.swing.*;
import com.rijin.User.User;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LoginJFrame extends JFrame implements MouseListener {
    static ArrayList<User> list= new ArrayList<>();
    static {
        list.add(new User("zhangsan","123"));
        list.add(new User("lisi","1234"));
    }

    JButton login = new JButton();
    JButton register = new JButton();
    JLabel rightCode = new JLabel();

    JTextField username = new JTextField();
    JTextField password = new JTextField();
    JTextField captcha = new JTextField();

    String cph;

    public LoginJFrame() {
        //初始化界面
        initJFrame();
        
        //在这个页面中添加内容
        initView();

        this.setVisible(true);
    }

    public void initView() {
        //清空原本已经出现的所有图片
        this.getContentPane().removeAll();
        //1.添加用户名文字
        JLabel usernameText = new JLabel(new ImageIcon("image\\login\\用户名.png"));
        usernameText.setBounds(116, 135, 47, 17);
        this.getContentPane().add(usernameText);

        //2.添加用户名输入框
        username.setBounds(195, 134, 200, 30);
        this.getContentPane().add(username);

        //3.添加密码文字
        JLabel passwordText = new JLabel(new ImageIcon("image\\login\\密码.png"));
        passwordText.setBounds(130, 195, 32, 16);
        this.getContentPane().add(passwordText);

        //4.添加密码输入框
        password.setBounds(195, 195, 200, 30);
        this.getContentPane().add(password);

        //5.验证码提示
        JLabel codeText = new JLabel(new ImageIcon("image\\login\\验证码.png"));
        codeText.setBounds(133, 256, 50, 30);
        this.getContentPane().add(codeText);

        //6.验证码的输入框
        captcha.setBounds(195, 256, 100, 30);
        this.getContentPane().add(captcha);

        //7.生成验证码
        cph = Captcha.getCode();
        //设置内容
        rightCode.setText(cph);
        //位置和宽高
        rightCode.setBounds(300, 256, 50, 30);
        //添加到界面
        this.getContentPane().add(rightCode);
        //给验证码添加鼠标监听
        rightCode.addMouseListener(this);

        //8.添加登录按钮
        login.setBounds(123, 310, 128, 47);
        login.setIcon(new ImageIcon("image\\login\\登录按钮.png"));
        //去除按钮的默认边框
        login.setBorderPainted(false);
        //去除按钮的默认背景
        login.setContentAreaFilled(false);
        this.getContentPane().add(login);
        //给登录按钮添加鼠标监听
        login.addMouseListener(this);

        //9.添加注册按钮
        register.setBounds(256, 310, 128, 47);
        register.setIcon(new ImageIcon("image\\login\\注册按钮.png"));
        //去除按钮的默认边框
        register.setBorderPainted(false);
        //去除按钮的默认背景
        register.setContentAreaFilled(false);
        this.getContentPane().add(register);
        //给注册按钮添加鼠标监听
        register.addMouseListener(this);

        //10.添加背景图片
        JLabel background = new JLabel(new ImageIcon("image\\login\\background.png"));
        background.setBounds(0, 0, 470, 390);
        this.getContentPane().add(background);
    }

    public void initJFrame() {
        this.setSize(488, 430);//设置宽高
        this.setTitle("拼图游戏 V1.0登录");//设置标题
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//设置关闭模式
        this.setLocationRelativeTo(null);//居中
        this.setAlwaysOnTop(true);//置顶
        this.setLayout(null);//取消内部默认布局
    }
    
    //展示用户名或密码错误的弹窗
    public void showJDialog(String content) {
        //创建一个弹框对象
        JDialog jDialog = new JDialog();
        //给弹框设置大小
        jDialog.setSize(200, 150);
        //让弹框置顶
        jDialog.setAlwaysOnTop(true);
        //让弹框居中
        jDialog.setLocationRelativeTo(null);
        //弹框不关闭永远无法操作下面的界面
        jDialog.setModal(true);

        //创建Jlabel对象管理文字并添加到弹框当中
        JLabel warning = new JLabel(content);
        warning.setBounds(0, 0, 200, 150);
        jDialog.getContentPane().add(warning);

        //让弹框展示出来
        jDialog.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //单击按钮
        Object obj = e.getSource();
        if (obj == register) {
            //跳转到注册页面，待补充
        }else if (obj == login) {
            //获取用户输入的验证码
            String inputCaptcha = captcha.getText();
            if (!inputCaptcha.equalsIgnoreCase(cph)) {
                showJDialog("验证码错误");
                return;
            }
            //获取用户输入的用户名和密码
            String inputUsername = username.getText();
            String inputPassword = password.getText();
            if (inputUsername.equals("")) {
                showJDialog("用户名不能为空");
            }else if (inputPassword.equals("")) {
                showJDialog("密码不能为空");
            }else {
                boolean flag = true;
                //遍历用户列表，判断用户名和密码是否正确
                for (User user : list) {
                    if (user.getUsername().equals(inputUsername) && user.getPassword().equals(inputPassword)) {
                        this.setVisible(false);
                        flag = false;
                        //用户名和密码正确，跳转到游戏界面
                        new GameJFrame();
                    }
                }
                if (flag) {
                    showJDialog("用户名或密码错误");
                }
            }
        }else if (obj == rightCode) {
            cph = Captcha.getCode();
            rightCode.setText(cph);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //按下按钮
        Object obj = e.getSource();
        if (obj == register) {
            register.setIcon(new ImageIcon("image\\login\\注册按下.png"));
        }else if (obj == login) {
            login.setIcon(new ImageIcon("image\\login\\登录按下.png"));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //松开按钮
        Object obj = e.getSource();
        if (obj == register) {
            register.setIcon(new ImageIcon("image\\login\\注册按钮.png"));
        }else if (obj == login) {
            login.setIcon(new ImageIcon("image\\login\\登录按钮.png"));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //划入按钮
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //划出按钮
    }

}