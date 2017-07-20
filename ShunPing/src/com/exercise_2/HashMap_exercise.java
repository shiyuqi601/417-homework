package com.exercise_2;

import java.util.*;

/**
 * Created by yuqishi on 3/24/17.
 */
public class HashMap_exercise {

    public static void main(String []argus){

        HashMap hm = new HashMap();

        Clerk aa = new Clerk("Jenny", "1213144", 27, 2789);
        Clerk bb = new Clerk("Tom", "1213145", 24, 1111);

        hm.put("1213144", aa);
        hm.put("1213145", bb);

        //Find
        if (hm.containsKey("1213144")){
            Clerk clerk = (Clerk)hm.get("1213144");
            System.out.format("We have this person, whose name is %s, age is %d, salary is %d.",
                    clerk.getName(), clerk.getAge(), clerk.getSal());
        }else {
            System.out.println("we do not have this person!");
        }

        //traverse
        Iterator it = hm.keySet().iterator();
        while (it.hasNext()){
            String key = it.next().toString();
            Clerk clerk = (Clerk)hm.get(key);
            System.out.println();
            System.out.format("name: %s, number: %s, age: %d, salary: %d",
                    clerk.getName(),clerk.getcNo(),clerk.getAge(), clerk.getSal());
        }



    }


}
