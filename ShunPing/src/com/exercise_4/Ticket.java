package com.exercise_4;

/**
 * Created by yuqishi on 3/30/17.
 */
public class Ticket{

    public static void main(String []argus){
        Ticket_window tw1 = new Ticket_window();
//        Ticket_window tw2 = new Ticket_window();
//        Ticket_window tw3 = new Ticket_window();

        Thread t1 = new Thread(tw1);
        Thread t2 = new Thread(tw1);
        Thread t3 = new Thread(tw1);

        t1.start();
        t2.start();
        t3.start();

    }
}

class Ticket_window implements Runnable{
    private int num = 2000;

    public void run(){
        while (true){
            synchronized (this){
                if (num > 0){
                    //Thread.currentThread().getName()
                    System.out.println(Thread.currentThread().getName()+"   "+num);
                    try {
                        Thread.sleep(1000);        //1 ticket per second
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    num--;
                }else{
                    break;
                }
            }
        }
    }
}
