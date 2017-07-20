package com.exercise_3;

import java.awt.*;
import javax.swing.*;

/**
 * Created by yuqishi on 3/25/17.
 */
public class Layout_exercise extends JFrame{
    JButton jb1 = null;

    public static void main(String []argus){

//        GUI_exercise gui = new GUI_exercise();
//        Border_Layout bl = new Border_Layout();
//        Flow_Layout fl = new Flow_Layout();
        Grid_Layout gl = new Grid_Layout();


    }

    //build a constructor of the class
    public Layout_exercise(){

        //JFrame jf = new JFrame();
        jb1 = new JButton("Yes");
        this.add(jb1);

        this.setTitle("Hello World");

        //size is defined by pixel
        this.setSize(200, 200);
        this.setLocation(100, 200);

        //setResizable is used to define whether user could change the window size or not.
        //this.setResizable(false);
        this.setVisible(true);

        //when close the window, the algorithm will also be stopped.
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

class Border_Layout extends JFrame{
    JButton jb1, jb2, jb3, jb4, jb5;

    public Border_Layout(){
        jb1 = new JButton("center");
        jb2 = new JButton("north");
        jb3 = new JButton("south");
        jb4 = new JButton("west");
        jb5 = new JButton("east");

        //we must set the location of the buttons, the default is center.
        this.add(jb1, BorderLayout.CENTER);
        this.add(jb2, BorderLayout.NORTH );
        this.add(jb3, BorderLayout.SOUTH);
        this.add(jb4, BorderLayout.WEST);
        this.add(jb5, BorderLayout.EAST);

        this.setTitle("BorderLayout Exercise");
        this.setSize(200,100);
        this.setLocation(300, 200);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}

class Flow_Layout extends JFrame{
    JButton jb1, jb2, jb3, jb4, jb5, jb6;

    public Flow_Layout(){
        jb1 = new JButton("first");
        jb2 = new JButton("second");
        jb3 = new JButton("third");
        jb4 = new JButton("fourth");
        jb5 = new JButton("fifth");
        jb6 = new JButton("sixth");

        //we do not need to set the location, the default is center,
        //we can change it to left, right, xxx
        this.add(jb1);
        this.add(jb2);
        this.add(jb3);
        this.add(jb4);
        this.add(jb5);
        this.add(jb6);

        //we need to define what kind of layout we want
        this.setLayout(new FlowLayout(FlowLayout.LEFT));


        this.setTitle("FlowLayout Exercise");
        this.setSize(300,200);
        this.setLocation(500, 200);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}

class Grid_Layout extends JFrame{
    int size = 9;
    JButton jb[] = new JButton[size];

    public Grid_Layout(){
        for (int i = 0; i < size; i++){
            jb[i] = new JButton(String.valueOf(i));
            this.add(jb[i]);
        }

        this.setLayout(new GridLayout(3, 3, 10, 10));

        this.setTitle("GridLayout Exercise");
        this.setSize(300,300);
        this.setLocation(500, 200);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
