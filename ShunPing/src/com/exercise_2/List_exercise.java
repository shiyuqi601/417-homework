package com.exercise_2;
import jdk.nashorn.internal.ir.WhileNode;

import java.io.*;
import java.util.*;

/**
 * Created by yuqishi on 3/22/17.
 */

//we cannot set the class name as "ArrayList", because it conflicts with the system set
public class List_exercise {

    public static void main(String []argus) throws Exception{
        ArrayList<Clerk> al = new ArrayList<Clerk>();

        Clerk clerk1 = new Clerk("Didi", "1213180", 24, 10000);
        Clerk clerk2 = new Clerk("Mengmeng", "1213175",22, 8000);
        Clerk clerk3 = new Clerk("Kevin", "1213174", 27, 2000);
        Clerk clerk4 = new Clerk("1", "2", 12, 100);

        Clerk_Management Sheila = new Clerk_Management();

        Sheila.add_clerk(clerk1);
        Sheila.add_clerk(clerk2);
        Sheila.add_clerk(clerk3);
        Sheila.add_clerk(clerk4);

//        al.add(clerk1);
//        al.add(clerk2);
//        al.add(clerk3);
//        al.add(clerk4);

        while (true){
            System.out.println("======================================");
            System.out.println("Please input the operation you want: ");
            System.out.println("1: show a clerk's information");
            System.out.println("2. update a clerk's salary");
            System.out.println("3. delete a clerk");
            System.out.println("4. output the highest clerk salary at present");
            System.out.println("5. add a new clerk");
            System.out.println("6. quit the system");


            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            int num = Integer.valueOf(br.readLine());
            switch (num){
                case 1:
                    System.out.println("Please input the No of the clerk: ");
                    Sheila.show_info(br.readLine());
                    break;
                case 2:
                    System.out.println("Please input the No of the clerk and new salary," +
                            " split them with a space");
                    String[] s = br.readLine().split(" ");
                    int sal = Integer.valueOf(s[1]);
                    Sheila.update_salary(s[0], sal);
                    break;
                case 3:
                    System.out.println("Please input the No of the clerk: ");
                    Sheila.delete_clerk(br.readLine());
                    break;
                case 4:
                    Sheila.highest_salary();
                    break;
                case 5:
                    System.out.println("Please input the new clerk's name: ");
                    String name = br.readLine();
                    System.out.println("Please input the new clerk's number: ");
                    String cNo = br.readLine();
                    System.out.println("Please input the new clerk's age: ");
                    int age = Integer.valueOf(br.readLine());
                    System.out.println("Please input the new clerk's salary: ");
                    int salary = Integer.valueOf(br.readLine());
                    Clerk clerk = new Clerk(name, cNo, age, salary);
                    Sheila.add_clerk(clerk);
                case 6:
                    System.exit(0);
                    break;
            }

        }
    }

}

class Clerk{
    private String name;
    private String cNo;
    private int age;
    private int sal;

    // constructor
    public Clerk(String name, String cNo, int age, int sal){
        this.name = name;
        this.cNo = cNo;
        this.age = age;
        this.sal = sal;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSal() {
        return sal;
    }

    public void setSal(int sal) {
        this.sal = sal;
    }

    public String getcNo() {
        return cNo;
    }

    public void setcNo(String cNo) {
        this.cNo = cNo;
    }

}

class Clerk_Management{
    private ArrayList<Clerk> al = null;

    public Clerk_Management(){
        al = new ArrayList<Clerk>();
    }

    //show clerk's information according to his No
    public void show_info(String cNo){
        for (int i = 0; i < al.size(); i++){

            //we must new a clerk here! al.get(i) is a type of object, we convert it to Clerk type.
            //after we add <Clerk> to ArrayList al definition, we do not need to convert it anymore.
            Clerk clerk = (Clerk)al.get(i);

            // equals is used to compare whether the content of string is equal,
            //"==" will compare the address of two strings instead of the content.
            if (cNo.equals(clerk.getcNo())){
                System.out.format("No.clerk is %s, name: %s, age: %d, salary: %d.",
                        clerk.getcNo(),clerk.getName(), clerk.getAge(),clerk.getSal());
                System.out.println();
            }
        }
    }

    public void update_salary(String cNo, int newSalary){
        for (int i = 0; i < al.size(); i++){
            Clerk clerk = (Clerk)al.get(i);
            if (cNo.equals(clerk.getcNo())){
                System.out.format("NO.%s salary is %d", clerk.getcNo(), clerk.getSal());
                clerk.setSal(newSalary);
                System.out.println(", now change to " + clerk.getSal());
            }

        }
    }

    public void delete_clerk(String cNo){
        for (int i = 0; i < al.size(); i++){
            Clerk clerk = (Clerk)al.get(i);
            if (cNo.equals(clerk.getcNo())){
                al.remove(i);

                //al.remove(clerk);
                System.out.println("You have remove "+ cNo + " from the list!");
            }
        }

    }

    public void highest_salary(){
        int max = 0;
        String name = null;

        for (int i = 0; i < al.size(); i++){
            Clerk clerk = (Clerk)al.get(i);
            if (clerk.getSal() > max){
                max = clerk.getSal();
                name = clerk.getName();
            }
        }
        System.out.println("The highest salary is " + max +
                ", whose name is " + name);

    }

    public void add_clerk(Clerk clerk){
        al.add(clerk);
    }

}
