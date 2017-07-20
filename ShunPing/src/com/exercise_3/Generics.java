package com.exercise_3;

import java.lang.reflect.Method;
import java.security.CryptoPrimitive;

/**
 * Created by yuqishi on 3/24/17.
 */
public class Generics {

    public static void main(String []argus){
//        Gen<String> gen1 = new Gen<String>("aaa");
//        gen1.show_Type_Name();
//
//        Gen<Integer> gen2 = new Gen<Integer>(2);
//        gen2.show_Type_Name();

        Gen<Bird> gen3 = new Gen<Bird>(new Bird());
        gen3.show_Type_Name();


    }
}

//use generics
class Gen<T>{
    private T o;

    public Gen(T t){
        o = t;
    }

    public void show_Type_Name(){
        System.out.println("type is "+ o.getClass().getName());
        Method [] m = o.getClass().getDeclaredMethods();

        System.out.println("The class " +o.getClass().getName()+ " has methods: ");
        for (int i = 0; i < m.length; i++){
            System.out.println(m[i].getName());
        }

    }
}

class Bird{
    public String name;
    public String color;

    public void cry(){
        System.out.println("The bird is actually singing!");
    }

    public void fly(){
        System.out.println("I am flying!");
    }

}
