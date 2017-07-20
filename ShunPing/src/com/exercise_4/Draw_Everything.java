package com.exercise_4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

/**
 * Created by yuqishi on 3/27/17.
 */
public class Draw_Everything extends JFrame implements ActionListener{

    My_Panel mp = null;
    JButton jb1, jb2;


    public Draw_Everything(){
        mp = new My_Panel();
        jb1 = new JButton("Red");
        jb2 = new JButton("Blue");

        this.add(jb1, BorderLayout.NORTH);
        this.add(mp);
        this.add(jb2, BorderLayout.SOUTH);

        jb1.setActionCommand("aa");
        jb1.addActionListener(this);
        jb2.setActionCommand("bb");
        jb2. addActionListener(this);


        this.setSize(400,300);
        //this.setLocation(10,10);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void actionPerformed(ActionEvent e){
        if (e.getActionCommand().equals("aa")){
            mp.setBackground(Color.red);
        }else if (e.getActionCommand().equals("bb")){
            mp.setBackground(Color.BLUE);
        }else {
            System.out.println("Error");
        }

    }


    public static void main(String []argus){
        Draw_Everything de = new Draw_Everything();

    }

}

class My_Panel extends JPanel{
    JPanel my_panel;
    Image im = null;
    URL resource = null;


    public My_Panel(){

        resource = Panel.class.getResource("/com/exercise_4/IMG_5896.jpg");
//        if (resource == null){
//            System.out.println("fuck");
//        }

        im = Toolkit.getDefaultToolkit().getImage(resource);
    }


    //override JPanel 's Paint method
    //Graphics is an important class to draw picture, we can understand it as a paintbrush
    public void paint(Graphics g){
        //calling father class's method, which cannot be omitted!
        super.paint(g);
        //System.out.println("Paint method has been called!");
        //g.drawOval(10,10,30,30);

        g.drawImage(im, 10, 10, 193, 257, this);

//         g.setColor(Color.red);
//         g.setFont(new Font("Times New Roman", Font.BOLD, 30));
//         g.drawString("I like Computer Science!", 70,70);


    }
}
