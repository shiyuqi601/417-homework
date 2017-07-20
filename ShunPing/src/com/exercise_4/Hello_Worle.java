package com.exercise_4;

/**
 * Created by yuqishi on 3/29/17.
 */
public class Hello_Worle {

    public static void main(String []argus){
        Cat cat = new Cat();
        //启动线程，会导致run函数的运行
        //cat.start();

        Dog dog = new Dog(5);
        Thread t1 = new Thread(dog);
        t1.start();

        Bird bird = new Bird(5);
        Thread t2 = new Thread(bird);
        t2.start();


    }
}


//用继承thread类的方法实现线程
class Cat extends Thread{

    int time = 0;

    //override run() method
    public void run(){
        while (true){
            //1000 represent 1s
            //sleep() will make the thread into blocked state and release the resource
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            time++;
            System.out.println("Hello World!"+time);
            if (time == 10){
                break;
            }

        }
    }

}


//用实现runnable接口的方法实现线程
class Dog implements Runnable{
    int times;
    int n;

    public Dog(int n){
        this.n = n;
    }

    public void run(){
        while (true){
            //1000 represent 1s
            //sleep() will make the thread into blocked state and release the resource
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            times++;
            System.out.println("I am thread, I am outputting "+times+ "th time.");
            if (times == n){
                break;
            }

        }
    }
}

class Bird implements Runnable{
    int n;
    int result;
    int time;

    public Bird(int n){
        this.n = n;
    }
    public void run(){
        while (true){
            //1000 represent 1s
            //sleep() will make the thread into blocked state and release the resource
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            result += (++time);
            System.out.println("result now is "+ result);
            if (time == n){
                System.out.println("final result = "+ result);
                break;   //don't forget break, or it will continue forever
            }
        }
    }
}
