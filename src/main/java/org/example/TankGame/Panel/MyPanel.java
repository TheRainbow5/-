package org.example.TankGame.Panel;


import org.example.TankGame.Bullet.ShotBullet;
import org.example.TankGame.Tank.EnemyTank;
import org.example.TankGame.Tank.HeroTank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

/**
 * Rainbow
 *
 * @DATE:2023/10/6 0006
 * 坦克大战的绘图区
 */
public class MyPanel extends JPanel implements KeyListener,Runnable {
    HeroTank heroTank;
    private int DefaultX=450;
    private int DefaultY=500;
    private Vector<EnemyTank> enemyTanks=new Vector<>();  //敌方坦克集合


    public MyPanel() {
        //初始化坦克方向、位置、速度
        heroTank=new HeroTank(DefaultX,DefaultY);
        heroTank.setSpeed(5);
        //初始化敌方坦克
        for(int i=0;i<3;i++){
            EnemyTank enemyTank=new EnemyTank(100*(i+1),0);
            enemyTank.setDirection(1);
            enemyTanks.add(enemyTank);
        }
    }

    /**
     * 绘图
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {  //绘图方法
        super.paintComponent(g);
        g.fillRect(0,0,1000,750);  //绘制背景
        //初始化我方坦克位置
        drawTank(heroTank.getX(),heroTank.getY(), heroTank.getDirection(), 0,g);

        //绘制敌方坦克
        for(int i=0;i<enemyTanks.size();i++){
            drawTank(enemyTanks.get(i).getX(),enemyTanks.get(i).getY(),enemyTanks.get(i).getDirection(),1,g);
        }

        //绘制子弹
        if(heroTank.getShotBullet()!=null&&heroTank.getShotBullet().isLive()==true){
            drawBullet(heroTank.getShotBullet().getX(),
                    heroTank.getShotBullet().getY(),
                    g);
        }
    }

    //有字符输入时，该方法触发
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * 根据键按下，该改变坦克的移动方向
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_W){      //向上走
            heroTank.setDirection(0);   //修改坦克方向
            heroTank.moveUp();   //修改坦克坐标
        }else if(e.getKeyCode()==KeyEvent.VK_S){  //向下走
            heroTank.setDirection(1);
            heroTank.moveDown();
        }else if(e.getKeyCode()==KeyEvent.VK_A){    //向左走
            heroTank.setDirection(2);
            heroTank.moveLeft();
        }else if(e.getKeyCode()==KeyEvent.VK_D){   //向右走
            heroTank.setDirection(3);
            heroTank.moveRight();
        }
        //如果用户按下J，就发射子弹
        if(e.getKeyCode()==KeyEvent.VK_J){
            heroTank.fire();
        }
        this.repaint();
    }
    //当某个键松开，该方法触发
    @Override
    public void keyReleased(KeyEvent e) {

    }

    /**
     * 为了让Panel不断的重绘子弹，需要将MyPanel实现Runnable接口，当作一个线程使用
     * 1、每隔100毫秒重绘一次
     */
    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            this.repaint();
        }
    }

    /**
     * 绘制坦克
     * @param x：坦克左上角的x坐标
     * @param y：坦克左上角的y坐标
     * @param direction：坦克的方向（上下左右）
     * @param type：坦克类型
     * @param g：画笔
     */
    public void drawTank(int x,int y,int direction,int type,Graphics g){
        switch (type){
            case 0:   //我们的坦克
                g.setColor(Color.cyan);
                break;
            case 1:   //敌人的坦克
                g.setColor(Color.yellow);
                break;
        }
        //根据坦克的方向来绘制坦克
        switch (direction){
            case 0:   //向上
                g.fill3DRect(x, y, 10,60,false);  //左边的轮子
                g.fill3DRect(x+30, y, 10,60,false);  //右边的轮子;
                g.fill3DRect(x+10, y+10, 20,40,false);  //中间的矩形
                g.fillOval(x+10, y+20, 20,20);  //左边的圆
                g.drawLine(x+20, y+30, x+20, y);  //炮筒
                break;
            case 1:   //向下
                g.fill3DRect(x, y, 10,60,false);  //左边的轮子
                g.fill3DRect(x+30, y, 10,60,false);  //右边的轮子;
                g.fill3DRect(x+10, y+10, 20,40,false);  //中间的矩形
                g.fillOval(x+10, y+20, 20,20);  //左边的圆
                g.drawLine(x+20, y+30, x+20, y+60);  //炮筒
                break;
            case 2:   //向左
                g.fill3DRect(x, y, 60,10,false);  //左边的轮子
                g.fill3DRect(x, y+30, 60,10,false);  //右边的轮子;
                g.fill3DRect(x+10, y+10, 40,20,false);  //中间的矩形
                g.fillOval(x+20, y+10, 20,20);  //左边的圆
                g.drawLine(x+30, y+20,x, y+20);  //炮筒
                break;
            case 3:   //向右
                g.fill3DRect(x, y, 60,10,false);  //左边的轮子
                g.fill3DRect(x, y+30, 60,10,false);  //右边的轮子;
                g.fill3DRect(x+10, y+10, 40,20,false);  //中间的矩形
                g.fillOval(x+20, y+10, 20,20);  //左边的圆
                g.drawLine(x+30, y+20, x+60, y+20);  //炮筒
                break;
            default:
                System.out.println("暂时不处理");
        }
    }


    /**
     * 绘制子弹
     * @param x
     * @param y
     * @param g
     */
    public void drawBullet(int x,int y,Graphics g){
        g.setColor(Color.WHITE);
        g.fill3DRect(x, y, 5,5,false);
    }
}
