package com.exercise_2;

/**
 * Created by yuqishi on 3/22/17.
 */
public class Find {

    public static void main(String[] argus){

        int arr[] = {0, 3, 5, 7, 9, 11, 21, 99, 100};
        Binary_find binary = new Binary_find();
        binary.find(0,arr.length-1,6,arr);
    }
}
//For Binary find, the array should be sorted!
class Binary_find{
    //static final int NOT_FOUND = -1;

    public void find(int left_index, int right_index, int val, int arr[]){
        int mid_index = (left_index + right_index)/2;
        int mid_value = arr[mid_index];

        // This is a very important IF statement, to determine whether the
        // recurrence would continue or not.
        if (left_index <= right_index){

            if (val > mid_value){
                find(left_index+1, right_index, val, arr);
            }else if (val < mid_value){
                find(left_index, right_index-1, val, arr);
            }else {
                //return mid_index;
                System.out.println("The index of the value is: "+ mid_index);
            }
        }else {
            System.out.println("Not Found!");
        }

    }
}
