package com.exercise_4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by yuqishi on 3/28/17.
 */
public class Control_ball extends JFrame{
    Draw d = null;

    public Control_ball(){
        d = new Draw();
        this.add(d);
        this.addKeyListener(d);
        this.addMouseListener(d);
        this.addMouseMotionListener(d);
        this.addWindowListener(d);

        this.setSize(300,400);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String []argus){
        Control_ball cb = new Control_ball();

    }

}

class Draw extends JPanel implements WindowListener, KeyListener, MouseListener, MouseMotionListener{
    int x = 100;
    int y = 100;

    public void paint(Graphics g){

        super.paint(g);
        g.setColor(Color.black);
        g.fillOval(x,y,20,20);
    }

    @Override
    //the key is pressed and show characters on the screen, i.e. letter
    public void keyTyped(KeyEvent e) {
        System.out.println(e.getKeyChar()+ " is pressed!");

    }

    @Override
    //a key is pressed, but can not show on screen, i.e. F1
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DOWN){
            y++;
        }else if (e.getKeyCode() == KeyEvent.VK_UP){
            y--;
        }else if (e.getKeyCode() == KeyEvent.VK_LEFT){
            x--;
        }else {
            x++;
        }
        //must call repaint method to repaint
        this.repaint();

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //e.getX() is a method to return x coordinate value
        System.out.format("\nthe coordinate you press is (%d, %d)", e.getX(), e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("the mouse is in!");

    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("the mouse is out!");

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        System.out.format("\n the mouse is moving! the coordinate is (%d, %d)",
                e.getX(), e.getY());

    }

    @Override
    public void windowOpened(WindowEvent e) {
        System.out.println("window Opened!");
    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.out.println("window is closing!");

    }

    @Override
    public void windowClosed(WindowEvent e) {
        System.out.println("window closed!");

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {
        System.out.println("window Activated!");

    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        System.out.println("window deactivated!");

    }
}
