package org.example.TankGame.Tank;

import org.example.TankGame.Bullet.ShotBullet;

import java.util.Vector;

/**
 * Rainbow
 *
 * @DATE:2023/10/6 0006
 * 因为多个敌人，就有多个线程，考虑到线程安全问题
 * 所以将敌方坦克放入Vector集合中
 * 将敌方坦克的移动方法放入EnemyTank线程中
 */
public class EnemyTank extends Tank{
    private Vector<ShotBullet> vectorShotBullet;  //存储子弹对象的集合
    public EnemyTank(int defaultX, int defaultY) {
        super(defaultX, defaultY);
    }
}
