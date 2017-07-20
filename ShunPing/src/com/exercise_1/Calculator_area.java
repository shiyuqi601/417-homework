package com.exercise_1;

import java.io.*;


/**
 * Created by yuqishi on 3/22/17.
 * Q:line 80: how to check p and q again and again to guarantee p < q?
 */
public class Calculator_area {
    static double Pi = 3.1415926;

    public static void main(String args[])throws IOException{
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            System.out.println("Please input what you want: \n 1. arithmetic \n 2. area");

            int i_1 = Integer.valueOf(br.readLine());

            if (i_1 == 1){
                System.out.println("Please input the operation: \n 1. addition \n 2. subtraction " +
                        "\n 3. multiplication \n 4. division");

                int i_2 = Integer.valueOf(br.readLine());

                System.out.println("using a space to split two numbers: ");
                String[] s = br.readLine().split(" ");
                float x = Float.valueOf(s[0]);
                float y = Float.valueOf(s[1]);

                switch (i_2){
                    case 1:
                        System.out.format("%f + %f = %f", x, y, x+y);
                        break;
                    case 2:
                        System.out.format("%f - %f = %f", x, y, x-y);
                        break;
                    case 3:
                        System.out.format("%f * %f = %f", x, y, x*y);
                        break;
                    case 4:
                        System.out.format("%f / %f = %f", x, y, x/y);
                        break;
                }
            }else {
                System.out.println("Please choose the pattern: \n 1. circle \n 2. square " +
                        "\n 3. rectangular \n 4. trapezoid");

                int i_3 = Integer.valueOf(br.readLine());

                switch (i_3){
                    case 1:
                        System.out.println("please input the radius: ");
                        double r = Double.valueOf(br.readLine());
                        System.out.println("the circle area is: " + Pi * r * r);
                        break;

                    case 2:
                        System.out.println("please input the side: ");
                        double a = Double.valueOf(br.readLine());
                        System.out.println("the circle area is: " + a * a);
                        break;

                    case 3:
                        System.out.println("using a space to split length and width: ");
                        String[] s = br.readLine().split(" ");
                        float x = Float.valueOf(s[0]);
                        float y = Float.valueOf(s[1]);
                        System.out.println("the rectangular area is: " + x * y);
                        break;

                    case 4:
                        System.out.println("using space to split short side, long side and height: ");
                        String[] str = br.readLine().split(" ");
                        float p = Float.valueOf(str[0]);
                        float q = Float.valueOf(str[1]);
                        float h = Float.valueOf(str[2]);

                        // how to check p and q again and again to guarantee p < q?
                        if (p < q){
                            System.out.println("the trapezoid area is: " + (p+q)*h/2);
                        }else {
                            System.out.println("It is not a trapezoid! Please retry: ");
                            String[] str1 = br.readLine().split(" ");
                            float p1 = Float.valueOf(str1[0]);
                            float q1 = Float.valueOf(str1[1]);
                            float h1 = Float.valueOf(str1[2]);
                            System.out.println("the trapezoid area is: " + (p1+q1)*h1/2);
                        }
                        break;
                }

            }
        }catch (IndexOutOfBoundsException e) {
        System.err.println("IndexOutOfBoundsException: " + e.getMessage());
        }catch (IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
        }

    }
}
