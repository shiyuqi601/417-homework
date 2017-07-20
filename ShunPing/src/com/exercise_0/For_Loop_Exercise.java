package com.exercise_0;

/**
 * Created by yuqishi on 3/19/17.
 */
public class For_Loop_Exercise {

    public static void main(String args[]){

        // print a pyramid
        int lay1 = 4;
        for (int i = 1; i <= lay1; i++){
            //First line has 3 spaces, second line has 2, third line has 1, the fourth line has no space.
            //Find the pattern is lay-i
            for (int k = 1; k <= lay1-i; k++){
                System.out.print(" ");
            }
            //First line has 1 star, second line has 3, third line has 5, fourth line has 4 stars.
            //Find the pattern #of star on ith layer is 2i-1
            for (int j = 1; j<= (2*i-1); j++){
                System.out.print("*");
            }
            System.out.println();
        }

        //print a hollow pyramid
        int lay2 = 7;
        for (int i = 1; i <= lay2; i++){

            for (int k = 1; k <= lay2 - i; k++){
                System.out.print(" ");
            }
            for (int j = 1; j <= 2*i-1; j++){
            //find that first layer and the last lay is the same as the example above
                if (i == 1 || i == lay2){
                    System.out.print("*");
                }else {
                    //if you are printing the first star and the last star for every layer, just print it
                    //else means you need to print space
                    if (j ==1 || j == (2*i-1)){
                        System.out.print("*");
                    }else {
                        System.out.print(" ");
                    }
                }

            }
            System.out.println();
        }

        //print a hollow rhombus
        /*
        int lay3 = 5;
        for (int i = 1; i <= lay3; i++){

            if (i <= (lay3+1)/2) {
                for (int k = 1; k <= lay3 - i - 2; k++) {
                    for (int j = 1; j <= 2 * i - 1; j++) {
                        if (j == 1 || j == (2 * i - 1)) {
                            System.out.print("*");
                        } else {
                            System.out.print(" ");
                        }
                    }
                }
                System.out.println();
            }else {

            }


            //for (int k = )

        }
        */



    }
}
