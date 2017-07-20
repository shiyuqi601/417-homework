package com.exercise_2;
import java.util.*;

/**
 * Created by yuqishi on 3/22/17.
 */
public class Sort_All {

    public static void main(String[] argus){

        int len = 100000;
        int arr1[] = new int[len];
        for (int p = 0; p < len; p++){
            //Math.random() could randomly create numbers between 0~1, double is the default type
            //Math.random()*10000 could randomly create numbers between 0~10000
            int t = (int)(Math.random()*10000);
            arr1[p] = t;
        }

        int arr[] = {-1, 0, 9, 4, 11, 13, 21, 7};


        Bubble bubble = new Bubble();

        //Calendar cal_1 = Calendar.getInstance();
        //System.out.println("start bubble sorting time: "+ cal_1.getTime());

        bubble.sort(arr);

        //Calendar cal_2 = Calendar.getInstance();
        //System.out.println("end bubble sorting time: "+ cal_2.getTime());


        Select select = new Select();

        //Calendar cal_3 = Calendar.getInstance();
        //System.out.println("start select sorting time: "+ cal_3.getTime());

        select.sort(arr);

        //Calendar cal_4 = Calendar.getInstance();
        //System.out.println("end select sorting time: "+ cal_4.getTime());

        Insertion insertion = new Insertion();
        insertion.sort(arr);


    }
}

class Bubble{
     public void sort(int arr[]){
         int temp = 0;

         for(int i = 0; i < arr.length - 1; i++){
             for (int j = 0; j < arr.length - 1 - i; j++){
                 if (arr[j] > arr[j+1]){
                     temp = arr[j];
                     arr[j] = arr[j+1];
                     arr[j+1] = temp;
                 }
             }
         }
         System.out.println("The bubble sorted result is: ");
         for (int k = 0; k < arr.length; k++){
             System.out.print(arr[k]+" ");
         }
     }
}

class Select {
    public void sort(int arr[]) {
        System.out.println();
        int temp = 0;

        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;

                }
            }
        }
        System.out.println("The selected sorted result is: ");
        for (int k = 0; k < arr.length; k++) {
            System.out.print(arr[k] + " ");
        }
    }
}

class Insertion{
    public void sort(int arr[]){
        System.out.println();

        for (int i = 1; i < arr.length; i++){
            int interVal = arr[i];
            int index = i - 1;
            while (index >= 0 && interVal < arr[index]){
                arr[index+1] = arr[index];
                index--;
            }
            arr[index+1] = interVal;
        }
        System.out.println("The insertion sorted result is: ");
        for (int k = 0; k < arr.length; k++) {
            System.out.print(arr[k] + " ");
        }


    }

}

