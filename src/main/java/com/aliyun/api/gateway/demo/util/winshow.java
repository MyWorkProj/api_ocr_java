package com.aliyun.api.gateway.demo.util;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

public class winshow {
	private Frame f;
    private TextField tf;
    private Button but;
    private TextArea ta;
    private Dialog d;
    private Label lab;
    private Button okBut;

    public winshow() {
        init();
    }

    public void init() {
        f = new Frame("by 星云");// 创建窗体对象
        f.setBounds(300, 100, 600, 500);// 设置窗体位置和大小
        f.setLayout(new FlowLayout());// 设置窗体布局为流式布局

        tf = new TextField(60);// 创建单行文本对象60长度大小字符

        but = new Button("转到");// 创建按钮对象

        ta = new TextArea(25, 70);// 创建多行文本对象25行，70列

        d = new Dialog(f, "提示信息-self", true);//弹出的对话框
        d.setBounds(400, 200, 350, 150);//设置弹出对话框的位置和大小
        d.setLayout(new FlowLayout());//设置弹出对话框的布局为流式布局
        lab = new Label();//创建lab标签填写提示内容
        okBut = new Button("确定");//创建确定按钮

        d.add(lab);//将标签添加到弹出的对话框内
        d.add(okBut);//将确定按钮添加到弹出的对话框内。

        f.add(tf);// 单行文本添加到窗体上
        f.add(but);// 按钮添加到窗体上
        f.add(ta);// 多行文本添加到窗体上

        myEvent();// 加载事件处理
        f.setVisible(true);// 设置窗体可见

    }

    private void myEvent() {

        // 确定按钮监听器
        okBut.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                d.setVisible(false);
            }

        });

        //文本框兼容器
        tf.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER)//如果按下回车键执行showDir()功能函数
                    showDir();//调用功能函数
            }

        });

        // 对话框监听器
        d.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {

                d.setVisible(false);//设置对话框不可见

            }

        });

        // 按钮事件监听器
        but.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                showDir();
            }

        });
        // 窗体关闭监听器
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);

            }

        });

    }

    //showDir()功能函数
    private void showDir(){
    	 String info ="game over！";
         lab.setText(info);//显示文本错误提示信息
         d.setVisible(true);//设置对话框可见。
         
//        String dirPath = tf.getText();// 获取单行文本内容保存到字符串dirPath中
//        File dir = new File(dirPath);// 将字符串dirPath封装成文件
//
//        // 如果文件存在，而且是个目录执行下列操作
//        if (dir.exists() && dir.isDirectory()) {
//            ta.setText(null);// 没打开一个目录前清空多行文本内容
//            String[] names = dir.list();// 文件目录列表存放到字符数组中
//            for (String name : names) {
//                ta.append(name + "\r\n");// 追加文本内容并换行
//
//            }
//        } else {
//
//            String info = "您输入的路径" + dirPath + "是错误的，请重新输入！";
//            lab.setText(info);//显示文本错误提示信息
//            d.setVisible(true);//设置对话框可见。
//        }
    }

//    public static void main(String[] args) {
//
//        new MyWindowDemo();
//    }

}
