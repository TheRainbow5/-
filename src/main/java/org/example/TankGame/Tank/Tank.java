package org.example.TankGame.Tank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.swing.*;
import java.awt.*;

/**
 * Rainbow
 *
 * @DATE:2023/10/6 0006
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tank {
    private int x;   //坦克的横坐标
    private int y;   //坦克的纵坐标
    private int direction=0;  //坦克的方向,0：上；1：下；2：左；3：右
    private int speed = 1;  //坦克的速度

    public Tank(int x,int y){
        this.x=x;
        this.y=y;
    }

    public void moveUp(){
        if(y<=0) return;
        y-=speed;
    }
    public void moveDown(){
        if(y<=0 || y>=660) return;
        y+=speed;
        System.out.println(y);
    }
    public void moveLeft(){
        if(x<=0) return;
        x-=speed;
    }
    public void moveRight(){
        if(x<=0 || x>=925) return;
        x+=speed;
        System.out.println(x);
    }

}
