package com.exercise_5;

import java.awt.*;
import java.util.*;

/**
 * Created by yuqishi on 3/29/17.
 */
class Tank{

    //x, y represent the coordinate of the tank
    int x = 0;
    int y = 0;

    //0-up;1-right;2-down;3-left
    int direct = 0;

    int speed = 3;
    int color;
    boolean isLive = true;

    public Tank(int x, int y){
        this.x = x;
        this.y = y;
    }

    public  int  getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public int getSpeed(){
        return speed;
    }

    public void setSpeed(int speed){
        this.speed = speed;
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }


}

//My tank
class Hero extends Tank{

    Vector<Bullet> bb = new Vector<Bullet>();
    Bullet b_hero = null;

    public Hero(int x, int y){
        super(x, y);
    }

    public void shotEnemy(){

        switch (this.getDirect()){
            case 0: //up
                b_hero = new Bullet(x+10, y, 0);
                bb.add(b_hero);
                break;
            case 1: //right
                b_hero = new Bullet(x+30, y+10, 1);
                bb.add(b_hero);
                break;
            case 2: //down
                b_hero = new Bullet(x+10, y+30, 2);
                bb.add(b_hero);
                break;
            case 3: //left
                b_hero = new Bullet(x, y+10, 3);
                bb.add(b_hero);
                break;
        }
        Thread t = new Thread(b_hero);
        t.start();
    }

    public void moveUp(){
        y-=speed;
    }

    public void moveDown(){
        y+=speed;
    }

    public void moveLeft(){
        x-=speed;
    }

    public void moveRight(){
        x+=speed;
    }

}

class EnemyTank extends Tank implements Runnable{
    Vector<Bullet> bb_enemy = new Vector<Bullet>();
    Bullet b_enemy = null;
    int times = 0;

    public EnemyTank(int x, int y){
        super(x,y);
    }
    
    public void hitHero(){
        switch (this.getDirect()){
            case 0: //up
                b_enemy = new Bullet(x+10, y, 0);
                bb_enemy.add(b_enemy);
                break;
            case 1: //right
                b_enemy = new Bullet(x+30, y+10, 1);
                bb_enemy.add(b_enemy);
                break;
            case 2: //down
                b_enemy = new Bullet(x+10, y+30, 2);
                bb_enemy.add(b_enemy);
                break;
            case 3: //left
                b_enemy = new Bullet(x, y+10, 3);
                bb_enemy.add(b_enemy);
                break;
        }
    }

    public void run(){

        while (true){
            switch (this.direct){
                case 0: //up
                    for (int i = 0; i < 30; i++){
                        if (y > 0){
                            y -= speed;
                        }
                        try {
                            Thread.sleep(50);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                    break;
                case 1: //right
                    for (int i = 0; i < 30; i++) {
                        if (x < 365){
                            x += speed;
                        }
                        try {
                            Thread.sleep(50);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 2: //down
                    for (int i = 0; i < 30; i++) {
                        if (y < 255){
                            y += speed;
                        }
                        try {
                            Thread.sleep(50);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 3: //left
                    for (int i = 0; i < 30; i++) {
                        if (x > 0){
                            x -= speed;
                        }
                        try {
                            Thread.sleep(50);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }

            //check if we need to add bullet to enemy's tank
            this.times++;
            if (times % 2 == 0){
                if (this.isLive){
                    if (this.bb_enemy.size() < 2){
                        Bullet b =null;
                        switch (this.getDirect()){
                            case 0: //up
                                b = new Bullet(x+10, y, 0);
                                bb_enemy.add(b);
                                break;
                            case 1: //right
                                b = new Bullet(x+30, y+10, 1);
                                bb_enemy.add(b);
                                break;
                            case 2: //down
                                b = new Bullet(x+10, y+30, 2);
                                bb_enemy.add(b);
                                break;
                            case 3: //left
                                b = new Bullet(x, y+10, 3);
                                bb_enemy.add(b);
                                break;
                        }
                        Thread t = new Thread(b);
                        t.start();

                    }
                }
            }
            //let the enemy's tank produce a new direction randomly
            this.direct = (int)(Math.random()*4);
             if (this.isLive == false){
                //when the enemy's tank is dead, quit the thread
                break;
            }
        }
    }
}


class  Bullet implements Runnable{
    int x;
    int y;
    int direct;
    int speed = 3;
    boolean isLive = true;

    public Bullet(int x, int y, int direct){
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    public void run(){
        while (true){
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            switch (direct){
                case 0: //up
                    y -= speed;
                    break;
                case 1: //right
                    x += speed;
                    break;
                case 2: //down
                    y += speed;
                    break;
                case 3: //left
                    x -= speed;
                    break;
            }
            //System.out.print("x = "+x+" y = "+y);
            if (x < 0 || y < 0 || x > 400 || y > 300){
                this.isLive = false;
                break;
            }
        }
    }

}

class Bomb{
    int x, y;
    //the boolean value is useful because it can check whether
    // it is necessary to draw the object of class.
    boolean isLive = true;
    int life = 9;

    public Bomb(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void lifeDown(){
        if (life > 0){
            life--;
        }else {
            this.isLive = false;
        }
    }
}

