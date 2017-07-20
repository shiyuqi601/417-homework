package com.exercise_1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by yuqishi on 3/22/17.
 * Q: how to output scissor instead of 1 when show the result?
 * 1: scissor
 * 2: stone
 * 3: cloth
 */
public class Finger_guessing {

    public static void main(String[] argus){


        int computer_win_times = 0;
        int user_win_times = 0;
        int games_drawn = 0;

        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);

            System.out.println("how many times you want to play? Please input an odd number: ");
            int n = Integer.valueOf(br.readLine());

            for (int j = 1; j <= n; j++){
                System.out.println();
                System.out.println("Please input a number: \n 1. scissor \n 2. stone \n 3. cloth");
                int b = Integer.valueOf(br.readLine());
                //randomize number from 1 to 3(inclusive the bound, to represent computer's choice.
                int a = (int) (Math.random() * 3 + 1);

                int temp = a - b;
                if (temp == -1 || temp == 2){
                    System.out.println("Congratulations! You win the computer!");
                    user_win_times ++;
                }else if (temp == -2 || temp == 1){
                    System.out.println("Cheer up! computer win......");
                    computer_win_times ++;
                }else {
                    System.out.println("Game drawn");
                    games_drawn ++;
                }
            }
            System.out.println();
            if (computer_win_times > user_win_times){
                System.out.format("You win %d times, computer win %d times, the game drawn is %d times." +
                        "In summary, you lose......", user_win_times, computer_win_times, games_drawn);
            }else {
                System.out.format("You win %d times, computer win %d times, the game drawn is %d times." +
                        " In summary, you win!", user_win_times, computer_win_times, games_drawn);
            }

        }catch (Exception e){

        }





    }
}
