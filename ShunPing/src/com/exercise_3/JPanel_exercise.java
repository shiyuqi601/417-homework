package com.exercise_3;


import javax.swing.*;
import java.awt.*;

/**
 * Created by yuqishi on 3/25/17.
 */
public class JPanel_exercise extends JFrame {
    JPanel jp1, jp2;
    JButton jb1, jb2, jb3, jb4, jb5, jb6;

    public JPanel_exercise(){
        //JPanel's default layout is flow layout.
        jp1 = new JPanel();
        jp2 = new JPanel();

        jb1 = new JButton("apple");
        jb2 = new JButton("banana");

        jb3 = new JButton("watermelon");

        jb4 = new JButton("pear");
        jb5 = new JButton("strawberry");
        jb6 = new JButton("coconut");

        jp1.add(jb1);
        jp1.add(jb2);
        jp2.add(jb4);
        jp2.add(jb5);
        jp2.add(jb6);

        this.add(jp1, BorderLayout.NORTH);
        this.add(jb3);
        this.add(jp2, BorderLayout.SOUTH);

        this.setTitle("fruits are coming");
        this.setSize(300,500);
        this.setLocation(100,200);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void main(String []argus){
        //JPanel_exercise jp = new JPanel_exercise();
        Login l = new Login();

    }
}

class Login extends JFrame{
    JPanel jp1, jp2, jp3;
    JButton jb1, jb2;
    JLabel jl1, jl2;
    JTextField jtf1;
    JPasswordField jpf1;

    public Login(){
        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();

        jb1 = new JButton("login");
        jb2 = new JButton("quit");

        jl1 = new JLabel("User name");
        jl2 = new JLabel("Password");

        jtf1 = new JTextField(10);
        jpf1 = new JPasswordField(10);


        this.setLayout(new GridLayout(3,1));

        jp1.add(jl1);
        jp1.add(jtf1);

        jp2.add(jl2);
        jp2.add(jpf1);

        jp3.add(jb1);
        jp3.add(jb2);

        this.add(jp1);
        this.add(jp2);
        this.add(jp3);

        this.setTitle("Log in");
        this.setSize(300,150);
        this.setLocation(100,200);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}
