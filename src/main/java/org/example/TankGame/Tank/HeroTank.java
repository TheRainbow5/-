package org.example.TankGame.Tank;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.TankGame.Bullet.ShotBullet;

import java.util.Vector;

/**
 * Rainbow
 *
 * @DATE:2023/10/6 0006
 */
@Data
@AllArgsConstructor
public class HeroTank extends Tank{
    private ShotBullet shotBullet;
    public HeroTank(int defaultX, int defaultY) {
        super(defaultX, defaultY);
    }

    /**
     * 发射子弹
     */
    public void fire(){
        switch (getDirection()){
            case 0:
                //创建子弹对象
                shotBullet=new ShotBullet(getX()+18,getY(),getDirection());
                shotBullet.setY(shotBullet.getY()-10);
                break;
            case 1:
                shotBullet=new ShotBullet(getX()+18,getY()+60,getDirection());
                shotBullet.setY(shotBullet.getY()+10);
                break;
            case 2:
                shotBullet=new ShotBullet(getX(),getY()+18,getDirection());
                shotBullet.setX(shotBullet.getX()-10);
                break;
            case 3:
                shotBullet=new ShotBullet(getX()+60,getY()+18,getDirection());
                shotBullet.setX(shotBullet.getX()+10);
                break;
        }
        //创建新线程
        new Thread(shotBullet).start();

    }
}
