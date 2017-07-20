package com.exercise_2;

import java.io.*;

/**
 * Created by yuqishi on 3/22/17.
 */
public class Array {

    public static void main(String []argus) throws Exception{

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        System.out.println("Please input the number of dogs: ");
        int num = Integer.valueOf(br.readLine());

        //set an array(is equal to int arr [] = new int[];)
        Dog dogs [] = new Dog[num];

        for (int i = 0; i < num; i++){
            //must add new Dog(), because when we you create an array, the pointer point to nothing,
            //we need to make the pointer point to object.
            dogs[i] = new Dog();

            System.out.println("please input the " + (i+1) + "th dog name: ");
            dogs[i].setName(br.readLine());

            System.out.println("please input the " + (i+1) + "th dog age: ");
            int age = Integer.valueOf(br.readLine());
            dogs[i].setAge(age);
        }

        int sum = 0;
        for (int j = 0; j < num; j++){
            sum += dogs[j].getAge();
        }
        System.out.println("The average age of 4 dogs is: " + sum/dogs.length);


    }
}

class Dog{
    private String name;
    private int age;

    public void setName(String name){
        this.name = name;
    }

    public void setAge(int age){
        this.age = age;
    }

    public int getAge(){
        return this.age;
    }

    public String getName(){
        return this.name;
    }
}

