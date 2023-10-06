package org.example.TankGame.Bullet;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Rainbow
 * 发射子弹对象
 * @DATE:2023/10/6 0006
 *
 * 发射子弹
 * 思路：
 * 1、当发射一个子弹，就相当于启动一个线程
 * 2、Hero有子弹的对象，当按下j键时，就启动一个发射子弹线程，让子弹不停的移动，形成一个射击的效果
 * 3、MyPanel需要不停的重绘，才会有该效果
 * 4、当子弹自动到边界时，就应该销毁
 * 5、当子弹碰到敌人坦克时，敌方坦克爆炸，销毁子弹线程
 */
@Data
@AllArgsConstructor
public class ShotBullet implements Runnable{
    private int x;   //子弹的x坐标
    private int y;   //子弹的y坐标
    private int direction;  //子弹的方向
    private int speed=10;  //子弹的速度
    private boolean isLive=true;  //子弹是否存活

    public ShotBullet(int x, int y,int direction) {
        this.x = x;
        this.y = y;
        this.direction=direction;
    }

    /**
     * 1、子弹休眠，实现连续发射的效果
     * 2、当到达边界时，销毁子弹线程
     * 3、当子弹碰到敌人坦克时，敌方坦克爆炸，销毁子弹线程
     */
    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //根据方向改变x，y坐标
            switch (direction){
                case 0:   //上
                    y-=speed;
                    break;
                case 1:   //下
                    y+=speed;
                    break;
                case 2:   //左
                    x-=speed;
                    break;
                case 3:   //右
                    x+=speed;
                    break;
            }
            System.out.println("子弹的坐标"+x+","+y);
            if(isBorder()){  //子弹到达边界
                isLive=false;
                break;  //销毁子弹线程
            }
        }
    }

    /**
     * 判断断子弹是否到边界
     * @return
     */
    public boolean isBorder(){
        if(x<0||x>1000||y<0||y>750){
            return true;
        }
        return false;  //没有到边界
    }
}

