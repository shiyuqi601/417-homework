package com.exercise_3;

import javax.swing.*;
import java.awt.*;

/**
 * Created by yuqishi on 3/27/17.
 */
public class JSplitPane_exercise extends JFrame {

    JSplitPane jsp;
    JList jlist;
    JLabel jLabel;

    public JSplitPane_exercise(){
        String []list = {"child", "primary school", "middle school", "high school",
                "university", "graduation", "marriage", "babies"};
        jlist = new JList(list);

        //JLable可以放图片！
        jLabel = new JLabel(new ImageIcon("/Users/yuqishi/Desktop/src/com/" +
                "exercise_3/IMG_5896.jpg"));

        jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jlist, jLabel);
        jsp.setOneTouchExpandable(true); //可以拉出来也可以收回去

        this.add(jsp);

        this.setTitle("My Growth");
        this.setSize(1000,1000);
        this.setLocation(100,200);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public static void main(String []argus){
        //JSplitPane_exercise jspe = new JSplitPane_exercise();
        QQ qq = new QQ();

    }
}


class QQ extends JFrame{
     JTextArea jta;
     JScrollPane jsp;
     JPanel jp;
     JComboBox jcb;
     JTextField jtf;
     JButton jb;

     public QQ(){
         jta = new JTextArea();
         jsp = new JScrollPane(jta);
         jp = new JPanel();
         String [] list = {"dad", "mom", "sister", "brother"};
         jcb = new JComboBox(list);
         jtf = new JTextField(10);
         jb = new JButton("send");

         jp.add(jcb);
         jp.add(jtf);
         jp.add(jb);

         this.add(jsp);
         this.add(jp, BorderLayout.SOUTH);

         this.setTitle("QQ chat");
         this.setIconImage((new ImageIcon("/Users/yuqishi/Desktop/src/com/" +
                 "exercise_3/qq.jpg")).getImage());

         this.setSize(400,150);
         this.setLocation(100,200);
         this.setVisible(true);
         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


     }
}