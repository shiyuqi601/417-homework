package com.exercise_3;

import javax.swing.*;
import java.awt.*;

/**
 * Created by yuqishi on 3/25/17.
 */
public class Swing_exercise extends JFrame{
    JPanel jp1, jp2, jp3, jp4;
    JLabel jl1, jl2, jl3, jl4;

    //复选框
    JCheckBox jcb1, jcb2, jcb3;

    JButton jb1, jb2;

    //单选框 必须在定义buttonGroup包起来
    JRadioButton jrb1, jrb2;
    ButtonGroup bg1;

    //下拉菜单
    JComboBox jcbx1;

    //列表
    JList jlt1;

    //滚动条
    JScrollPane jsp1;


    public Swing_exercise(){
        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        jp4 = new JPanel();

        jl1 = new JLabel("The sports you like");
        jl2 = new JLabel("Gender");
        jl3 = new JLabel("Where are you from");
        jl4 = new JLabel("The city you like");

        jb1 = new JButton("Registration");
        jb2 = new JButton("Cancle");

        jcb1 = new JCheckBox("Football");
        jcb2 = new JCheckBox("Basketball");
        jcb3 = new JCheckBox("Volleyball");

        jrb1 = new JRadioButton("male");
        jrb2 = new JRadioButton("female");

        //初始化JComboBox
        String s1[] = {"China", "Europe", "America", "Australia"};
        jcbx1 = new JComboBox(s1);

        //初始化JList
        String s2[] = {"Seattle", "Beijing", "Shanghai", "New York", "LA", "UIUC", "USC"};
        jlt1 = new JList(s2);
        jlt1.setVisibleRowCount(3);  //设置滚动条不滚动时一开始显示多少个
        jsp1 = new JScrollPane(jlt1);

        this.setLayout(new GridLayout(4,1));

        bg1 = new ButtonGroup();  //don't forget to put the JRadioButton to a group!
        bg1.add(jrb1);
        bg1.add(jrb2);

        jp1.add(jl1);
        jp1.add(jcb1);
        jp1.add(jcb2);
        jp1.add(jcb3);

        jp2.add(jl2);
        //往panel里面添加的时候还是将单选框一一添加，而不是添加buttonGroup
        jp2.add(jrb1);
        jp2.add(jrb2);

        jp3.add(jb1);
        jp3.add(jb2);

        jp4.add(jl3);
        jp4.add(jcbx1);
        jp4.add(jl4);
        jp4.add(jsp1); //这里加的不是JList，而是滚动条！

        this.add(jp1);
        this.add(jp2);
        this.add(jp3);
        this.add(jp4);

        this.setTitle("Registration");
        this.setSize(450,300);
        this.setLocation(100,200);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }


    public static void main(String []argus){
        Swing_exercise se = new Swing_exercise();




    }
}
