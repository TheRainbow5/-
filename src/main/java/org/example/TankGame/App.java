package org.example.TankGame;

import org.example.TankGame.Panel.MyPanel;

import javax.swing.*;

/**
 * Rainbow
 *
 * @DATE:2023/10/6 0006
 */
public class App extends JFrame {
    private MyPanel myPanel;
    public static void main(String[] args) {
        new App();
    }
    public App(){
        myPanel= new MyPanel();
        //将myPanel创建一个线程
        new Thread(myPanel).start();
        //把面板放到窗口中
        this.add(myPanel);
        this.addKeyListener(myPanel);  //键盘监听
        this.setTitle("坦克大战");
        this.setSize(1000,750);
        this.setVisible(true);      //可视化
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
