package com.exercise_5;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.*;

/**
 * Created by yuqishi on 3/27/17.
 */
public class Tank_Game1 extends JFrame{

    MyPanel mp = null;

    public Tank_Game1(){
        mp = new MyPanel();

        this.add(mp);
        this.addKeyListener(mp);

        Thread t = new Thread(mp);
        t.start();

        this.setSize(400,300);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void main(String []argus){
        Tank_Game1 tg = new Tank_Game1();

    }
}

//everything should be drew on the panel rather than JFrame
class MyPanel extends JPanel implements KeyListener, Runnable{

    Hero hero = null;

    Vector<EnemyTank> ets = new Vector<EnemyTank>();
    //define the number of enemy's tanks
    int enSize = 3;

    Vector<Bomb> bombs = new Vector<Bomb>();

    Image image1 = null;
    Image image2 = null;
    Image image3 = null;

    public MyPanel(){
        hero = new Hero(200,200);

        //initialize enemies'tanks
        for (int i = 0; i < enSize; i++){
            EnemyTank et = new EnemyTank((i+1)*50, 0);   //initialize tank's coordination.
            et.setDirect(2);

            //start the thread of enemy's tank
            Thread t = new Thread(et);
            t.start();

            //add a bullet for enemy's tank, whose direction is 2(down),
            // thus the bullet's coordination could be calculated
            Bullet b_hero = new Bullet(et.x+10, et.y+30,2);
            et.bb_enemy.add(b_hero);
            //start the thread of bullet of enemy's tank
            Thread tt = new Thread(b_hero);
            tt.start();

            ets.add(et);
        }

        //initialize bomb pictures
//        try {
//            image1 = ImageIO.read(new File("/com/exercise_5/explosion_1.gif"));
//            image2 = ImageIO.read(new File("/com/exercise_5/explosion_2.gif"));
//            image3 = ImageIO.read(new File("/com/exercise_5/explosion_3.gif"));
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
        image1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/com/exercise_5/explosion_1.gif"));
        image2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/com/exercise_5/explosion_2.gif"));
        image3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/com/exercise_5/explosion_3.gif"));

    }

    //override paint method
    public void paint(Graphics g){
        super.paint(g); //call father class(JPanel)'s method to set up the "brush"

        //background color
        g.fillRect(0,0,400,300);

        //draw my tank
        if (this.hero.isLive == true){
            this.drawTank(hero.getX(), hero.getY(), g, this.hero.getDirect(), 1);
        }

        //draw enemies' tanks
        for (int i = 0; i < ets.size(); i++){
            EnemyTank et = ets.get(i);
            if (et.isLive == true){
                this.drawTank(et.getX(), et.getY(), g, et.getDirect(), 0);

                //draw bullet of enemies'tank
                for (int j = 0; i < et.bb_enemy.size(); j++){
                    Bullet b_hero = et.bb_enemy.get(i);
                    if (b_hero != null && b_hero.isLive == true){
                        g.fill3DRect(b_hero.x, b_hero.y, 3, 3, false);
                    }
                    if (b_hero.isLive == false){
                        et.bb_enemy.remove(b_hero);
                    }
                }
            }
        }

        //draw bullets
        for (int i = 0; i < hero.bb.size(); i++){
            Bullet myBullet = hero.bb.get(i);
            if (myBullet != null && myBullet.isLive == true){
                g.fill3DRect(myBullet.x, myBullet.y, 3, 3, false);
            }
            //we need to remove the dead bullet from vector bullet (bb)
            if (myBullet.isLive == false){
                hero.bb.remove(myBullet);
            }
        }

        //draw bombs
        for (int i = 0; i < bombs.size(); i++){
            Bomb b = bombs.get(i);
            if (b.life > 6){
                g.drawImage(image1, b.x, b.y, 30, 30, this);
            }else if (b.life > 3){
                g.drawImage(image2, b.x, b.y, 30, 30, this);
            }else {
                g.drawImage(image3, b.x, b.y, 30, 30, this);
            }
            b.lifeDown();
            //if the bomb is dead, remove it from the bombs vectors to release memory
            if (b.life == 0){
                bombs.remove(b);
            }
        }

    }

    Image offScreenImage = null;
    public void update(Graphics g) {
        if(offScreenImage == null) {
            offScreenImage = this.createImage(400, 300);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.GREEN);
        gOffScreen.fillRect(0, 0, 400, 300);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    //check whether a tank is hit by a bullet
    public void hitTank(Bullet b, Tank t){
        switch (t.getDirect()){
            case 0: //up
            case 2: //down
                if (b.x > t.x && b.x < t.x +20 && b.y < t.y+30 &&b.y > t.y){
                    b.isLive = false;
                    t.isLive = false;
                    //we create a bomb, since the picture is drew from top left corner,
                    // which is exactly the coordinate of tank.
                    Bomb bomb = new Bomb(t.x, t.y);
                    bombs.add(bomb);
                }
            case 1: //right
            case 3: //left
                if (b.x > t.x && b.x < t.x + 30 && b.y > t.y && b.y < t.y +20){
                    b.isLive = false;
                    t.isLive = false;
                    Bomb bomb = new Bomb(t.x, t.y);
                    bombs.add(bomb);
                }
        }
    }

    public void hitEnemyTank(){
        //take out one bullet, use traverse
        for (int i = 0; i < hero.bb.size(); i++){
            Bullet b = hero.bb.get(i);
            if (b.isLive == true){
                //traverse every enemyTank to check whether it is hit by the bullet we take out
                for (int j = 0; j < ets.size(); j++){
                    EnemyTank et = ets.get(j);
                    if (et.isLive == true){
                        this.hitTank(b, et);
                    }
                }
            }
        }
    }

    public void hitMe(){
        //take out every enemies' tank
        for (int i = 0; i < ets.size(); i++){
            EnemyTank et = ets.get(i);
            //traverse every enemy tank's bullet to check whether my tank(hero) is hit by it
            for (int j = 0; j < et.bb_enemy.size(); j++){
                Bullet b = et.bb_enemy.get(j);
                //if (hero.isLive == true){
                    this.hitTank(b, hero);
                //}
            }
        }
    }

    public void drawTank(int x, int y, Graphics g, int direction, int type){

        //check if it is enemy's tank or my tank
        switch (type){
            //My tank
            case 0:
                g.setColor(Color.cyan);
                break;
            //enemy's tank
            case 1:
                g.setColor(Color.yellow);
                break;
        }

        switch(direction){
            //UP
            case 0:
                g.fill3DRect(x, y, 5, 30, false);  //draw left rectangular
                g.fill3DRect(x+15, y, 5,30, false);  //draw right rectangular
                g.fill3DRect(x+5, y+5, 10,20, false);  //draw middle rectangular
                g.fillOval(x+5, y+10, 10, 10);  //draw the middle circle
                g.drawLine(x+10, y+15, x+10, y);  // draw the line between point(x1, y1) and point(x2, y2)
                break;
            //Right
            case 1:
                g.fill3DRect(x,y,30,5,false);
                g.fill3DRect(x, y+15, 30,5, false);
                g.fill3DRect(x+5, y+5, 20,10, false);
                g.fillOval(x+10, y+5, 10, 10);
                g.drawLine(x+15, y+10, x+33, y+10);
                break;
            //Down
            case 2:
                g.fill3DRect(x, y, 5, 30, false);  //draw left rectangular
                g.fill3DRect(x+15, y, 5,30, false);  //draw right rectangular
                g.fill3DRect(x+5, y+5, 10,20, false);  //draw middle rectangular
                g.fillOval(x+5, y+10, 10, 10);  //draw the middle circle
                g.drawLine(x+10, y+15, x+10, y+33);  // draw the line between point(x1, y1) and point(x2, y2)
                break;
            //Right
            case 3:
                g.fill3DRect(x,y,30,5,false);
                g.fill3DRect(x, y+15, 30,5, false);
                g.fill3DRect(x+5, y+5, 20,10, false);
                g.fillOval(x+10, y+5, 10, 10);
                g.drawLine(x-3, y+10, x+15, y+10);
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    //a-left;d-right;s-down;w-up
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_W){
            this.hero.setDirect(0);
            this.hero.moveUp();
        }else if (e.getKeyChar() == KeyEvent.VK_S){
            this.hero.setDirect(2);
            this.hero.moveDown();
        }else if (e.getKeyChar() == KeyEvent.VK_A){
            this.hero.setDirect(3);
            this.hero.moveLeft();
        }else if (e.getKeyChar() == KeyEvent.VK_D){
            this.hero.setDirect(1);
            this.hero.moveRight();
        }
        this.repaint();


        //press J the tank will shot a bullet
        //the reason why we rewrite an if statement is to let tank could shot and move simultaneously.
        if (e.getKeyChar() == KeyEvent.VK_J){
            if (this.hero.bb.size()<= 5){
                this.hero.shotEnemy();
            }
        }
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.hitEnemyTank();
            this.hitMe();
            this.repaint();
        }
    }
}





