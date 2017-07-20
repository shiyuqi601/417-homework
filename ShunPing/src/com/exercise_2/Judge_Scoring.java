package com.exercise_2;

import java.util.*;
import java.io.*;

/**
 * Created by yuqishi on 3/25/17.
 * 跳水比赛，8个评委打分，要去掉最高分和最低分，剩下6个的平均分就是选手的成绩，还要能输出最差评委，即打分
 * 与最终得分差最多，和最佳评委，即打分与最终差最少。
 *
 */
public class Judge_Scoring {

    public static void main(String []argus){

//        Judge j1 = new Judge("N1", 9.99f);
//        Judge j2 = new Judge("N2", 9.23f);
//        Judge j3 = new Judge("N3", 8.99f);
//        Judge j4 = new Judge("N4", 5.95f);
//        Judge j5 = new Judge("N5", 7.44f);
//        Judge j6 = new Judge("N6", 9.00f);
//        Judge j7 = new Judge("N7", 8.96f);
//        Judge j8 = new Judge("N8", 9.19f);
//
//        ArrayList<Judge> list = new ArrayList<Judge>();
//        list.add(j1);
//        list.add(j2);
//        list.add(j3);
//        list.add(j4);
//        list.add(j5);
//        list.add(j6);
//        list.add(j7);
//        list.add(j8);
        Judging j = new Judging();

        System.out.println("the final score is: " + j.final_score());
        j.optimal_judge();




    }
}

class Judge{
    private String name;
    private float score;

    public Judge(String name, float score){
        this.name =  name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }
}

class Judging {
    private float[] al = null;
    int size = 5;

    public Judging() {
        al = new float[size];

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            for (int i = 0; i < size; i++) {
                System.out.println("Please input the " + (i + 1) + "th judge score: ");
                al[i] = Float.valueOf(br.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public int index_low_score() {
        int index = 0;
        float low_score = al[0];
        for (int i = 1; i < al.length; i++) {
            if (low_score > al[i]) {
                low_score = al[i];
                index = i;
            }
        }
        return index;
    }

    public int index_high_score() {
        int index = 0;
        float high_score = al[0];
        for (int i = 1; i < al.length; i++) {
            if (high_score < al[i]) {
                high_score = al[i];
                index = i;
            }
        }
        return index;
    }

    public float final_score() {
//        Judging j = new Judging();
//        int high_index = j.index_high_score(al);
//        int low_index = j.index_low_score(al);
        int high_index = this.index_high_score();
        int low_index = this.index_low_score();

        float sum = 0;
        for (int i = 0; i < al.length; i++){
            sum += al[i];
        }
        float final_score = (sum - al[low_index] - al[high_index])/(al.length - 2);
        return final_score;
    }

    public void optimal_judge(){
        float final_score = this.final_score();
        float difference []= new float[size];

        for (int i = 0; i < al.length; i++){
            difference[i] = Math.abs(al[i] - final_score);
        }

        float max_d = difference[0];
        float min_d = difference[0];

        int max_index = 0;
        int min_index = 0;

        for (int j = 1; j < difference.length; j++){
            if (max_d < difference[j]){
                max_index = j;
            }
            if (min_d > difference[j]){
                min_index = j;
            }
        }
        System.out.format("The best judge is %d th judge, the worst judge is %d th judge",
                min_index+1, max_index+1);
    }
}

