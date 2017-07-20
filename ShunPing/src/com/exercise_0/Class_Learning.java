package com.exercise_0;

/**
 * Created by yuqishi on 3/20/17.
 */
public class Class_Learning {

    public static void main(String args[]){
        Person a = new Person();
        a.getAge();
        a.name = "Lily";
        a.speak();
        a.calculator(100);
        a.sum(132,23);
        System.out.println("the number of peach is " + a.peach_count(10,1));

        Picture pic = new Picture();
        pic.multiplication(8);


        Dog d1 = new Dog(2, "Jenny");
        d1.joinRunning();
        Dog d2 = new Dog(3, "peter");
        d2.joinRunning();
        Dog d3 = new Dog(1, "husky");
        d3.joinRunning();
        Dog d4 = new Dog(5, "ben");
        d4.joinRunning();

        System.out.println("the number of the dog running is: " + Dog.getTotal());

        Clerk clerk1 = new Clerk(24, "Sally", 8883.8f);

        //age is a private(because we want to protect data), so can not be visited
        // out of the class, we mush create a method inner class to make it possible
        // be visited out of class(i.e.getAge())
        System.out.println("name is "+ clerk1.name + " age is "+ clerk1.getAge());

        //体现多态
        Animal animal1 = new Cow();
        animal1.cry();
        animal1 = new Cat();
        animal1.cry();

        Master master = new Master();
        master.feed(new Cow(), new Grass());
        master.feed(new Cat(), new Fish());


    }

}


class Dog
{
    static int total = 0;
    //the static is only executed once
    static {
        total++;
    }

    int age;
    String name;


    public Dog(int age, String name){
        this.age = age;
        this.name = name;
    }

    public void joinRunning(){
        System.out.println("A new dog has joined running!");
        total++;
    }

    //since total is a static variable, we create a static method to visit it
    public static int getTotal(){
        return total;
    }
}

class Person
{
    private int age;
    String name;

    public Person(){
        this.name = name;
}
    public void getAge(){
        System.out.println("the age is " + this.age);
    }

    public int peach_count(int day, int peach_remain){
        int sum = peach_remain;
        for (int i = 1; i <= (day-1); i++){
            sum = (sum + 1)*2;
            //System.out.println(sum);
        }
        return sum;
    }

    public void speak(){
        System.out.println("I am smart!");
    }

    public void calculator(int n){
        int result = 0;
        for (int i = 1; i <= n; i++){
            result += i;
        }
        System.out.println("result = "+ result);
    }

    public int sum(int num1, int num2){
        return num1 + num2;
    }
}





class Picture
{
    public void multiplication(int n){
        for(int i = 1; i <= n; i++){
            for (int j = 1; j <= i; j++){
                if (i <= 3){
                    System.out.format("%d * %d = %d   ", j, i, i*j);
                }else if (i == 4 && j == 3){
                    System.out.format("%d * %d = %d  ", j, i, i*j);
                }else if (i >= 5 && j >= 2){
                    System.out.format("%d * %d = %d  ", j, i, i*j);
                }else {
                    System.out.format("%d * %d = %d   ", j, i, i*j);
                }
            }
            System.out.println();
        }

    }
}



class Clerk
{
    private int age;
    String name;
    private float salary;

    public Clerk(int age, String name, float salary){
        this.age = age;
        this.name = name;
        this.salary = salary;
    }

    public int getAge(){
        return this.age;
    }
}

class Animal
{
    private String name;
    private int age;

    public void cry(){
        System.out.println("do not know how to cry!");
    }

    public void eat(){
        System.out.println("do not know what to eat!");
    }
}

class Cow extends Animal{
    public void cry(){
        System.out.println("mou~mou~");
    }

    public void eat(){
        System.out.println("A cow is eating grass!");
    }
}


class Cat extends Animal{
    public void cry(){
        System.out.println("miao~miao~");
    }

    public void eat(){
        System.out.println("A cat is eating fish!");
    }

}


class Food{
    String name;

    public void showName(){
        System.out.println("food");
    }

}

class Fish extends Food{

    public void showName(){
        System.out.println("fish");
    }
}


class Grass extends Food{

    public void showName(){
        System.out.println("grass");
    }
}

class Master{
    public void feed(Animal a, Food f){
        a.eat();
        f.showName();

    }
}








