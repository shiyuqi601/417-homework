/**
        * Created by yuqishi on 1/21/17.
        */


import com.sun.scenario.effect.Merge;
import sun.security.util.Length;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * CSE417: Algorithms And Computational Complexity
 * Homework 3
 * Due Date: January 27th, 2017
 *
 * You are given a sequence of distinct n numbers a_1, a_2, ... a_n.
 * We define significant inversion as i < j and a_i > 2 * a_j.
 * Give algorithms to count the number of significant inversions between two orderings.
 * One implementation will be to simply compare a_i and a_j for each pair i < j (this is called naive).
 * The other implementation will use divide and conquer.
 *
 * You will be only filling in the code for countSignificantInversionsNaive() and countSignificantInversions() below.
 * You should probably add more testing at the top of main() as well. You are welcome to write unit tests if you wish.
 * Timing and generating inputs is done for you.
 *
 */
public class SignificantInversions {
    private static final int SMALLEST_INPUT_SIZE = (int) Math.pow(2, 8);
    private static final int MEDIUM_INPUT_SIZE = (int) Math.pow(2, 16);
    private static final int LARGEST_INPUT_SIZE = (int) Math.pow(2, 21);
    private static final int NUM_SAMPLE = 10; // be careful when changing this value because large value might cause overflow when adding up times.

    /**
     * Computes the number of significant inversions in the list with O(N^2) time complexity
     * where N is the input size.
     *
     * @param list an unordered list of integers
     * @return the number of significant inversion in the list
     */
    public static long countSignificantInversionsNaive(List<Integer> list) {
        /* TODO: PART (b) and (c) */
    	/* TODO: YOUR NAIVE CODE GOES HERE*/

        int n = list.size();
        long count = 0;

        for (int i = 0; i < n ; i++){
            for (int j = i + 1; j < n; j++){
                if (list.get(i) > 2 * list.get(j)){
                    count++;
                }
            }
        }

        return count;

    }

    /**
     * Computes the number of significant inversion in the list with O(N logN) time complexity
     * where N is the input size.
     *
     * @param list an unordered list of integers
     * @return the number of significant inversions in the list
     */
    public static long countSignificantInversions(List<Integer> list) {
    	/* TODO: Part (c) and (d) */
        /* TODO: YOUR DIVIDE AND CONQUER CODE GOES HERE */

        int n = list.size();
        if (n < 2)
            return 0;

        int m = n /2;
        List left = new ArrayList(list.subList(0, m)) ;
        List right = new ArrayList(list.subList(m, n));

        return countSignificantInversions(left) + countSignificantInversions(right) + Merge_and_Count(list, left, right);
    }

    public static long Merge_and_Count(List<Integer> list, List<Integer> left, List<Integer> right){
        int i = 0, j = 0, z = 0;
        long count = 0;

        while (i < left.size() || j < right.size()) {
            if (i == left.size()) {
                list.set(i+j,right.get(j));

                j++;
            } else if (j == right.size()) {
                list.set(i+j,left.get(i));
                i++;
            } else if (left.get(i) <= right.get(j)) {
                list.set(i+j,left.get(i));
                i++;
            } else {
                list.set(i+j,right.get(j));
                if(left.get(i) > 2 * right.get(j)){
                    count += left.size() - i;
                } else {
                    for (z = i; z < left.size(); z++){
                        if (left.get(z) > 2 * right.get(j)){
                            count += left.size() - z;
                            break;
                        }
                    }
                }
                j++;
            }
        }

        return count;
    }

    /**
     * main function for HW3. tests correctness of your implementations.
     * reports the running time of both implementation of counting significant inversions
     * and reports number of significant inversions on some large inputs.
     *
     * @param args arguments to the main method
     */
    public static void main(String[] args) {


        // start with some correctness testing
        System.out.println("\n\n============================== Testing Your Code Correctness ==============================\n\n");

        // fix some list and have it output.
        List<Integer> testList = new ArrayList<Integer>();
        testList.add(7);
        testList.add(3);
        testList.add(1);
        testList.add(15);
        testList.add(31);
        //testList = generateInput(16, 0);
        //testList = [7, 3, 1, 15, 31]
        System.out.println("Test 1:");
        System.out.println("Before the sorting the list is: " + testList.toString());
        System.out.println("Number of significant inversions using naive implementation: " + countSignificantInversionsNaive(testList));
        System.out.println("Number of significant inversions using divide and conquer: " + countSignificantInversions(testList));
        System.out.println("Actual significant inversions: 3");
        System.out.println("After the divide and conquer algorithm the list is : " + testList.toString());
        System.out.println("You should probably add more tests.");
        System.out.println("\n");

        // Runs two different implementation of counting significant inversions, divide and conquer and naive.
        // reports time(in milliseconds) it takes to run each algorithm with different input sizes.
        System.out.println("============================== Measuring Running Time ==============================\n\n");
        reportTimes();
        System.out.println();

        // Runs divide and conquer implementation of counting significant inversions in a list
        // outputs five different number each represents number of significant inversions found in five different lists
        System.out.println("============================== Measuring Number of Significant Inversions ==============================");
        reportNumInversions();
    }

    /*
     * YOU SHOULD NOT HAVE TO TOUCH ANY CODE BELOW HERE.
     */

    /**
     * Reports the running time(in milliseconds) of counting significant inversions.
     */
    private static void reportTimes() {
        for (int size = SMALLEST_INPUT_SIZE; size <= MEDIUM_INPUT_SIZE; size *= 2) {
            // runs a small number of samples to get average running time of different methods.
            long timeSum = 0;
            long naiveTimeSum = 0;
            for (int i = 0; i < NUM_SAMPLE; i++) {
                // generate random input
                List<Integer> input = generateInput(size, System.nanoTime());

                // calls divide and conquer method and time the method
                long startTime = System.nanoTime();
                countSignificantInversions(input);
                long endTime = System.nanoTime();
                timeSum += ((endTime - startTime) / 1000000); // divide by 1000000 to get milliseconds

                // calls naive and time the method
                startTime = System.nanoTime();
                countSignificantInversionsNaive(input);
                endTime = System.nanoTime();
                naiveTimeSum += ((endTime - startTime) / 1000000); // divide by 1000000 to get milliseconds
            }

            System.out.println("input size: " + size);
            System.out.println("Divide and conquer: " + (timeSum / NUM_SAMPLE) + "ms");
            System.out.println("Naive: " + (naiveTimeSum / NUM_SAMPLE) + "ms");
            System.out.println();
        }
    }

    /**
     * Reports a number of counting significant inversions.
     */
    private static void reportNumInversions() {
    	/* DO NOT CHANGE THIS*/
        List<Integer> l1 = generateInput(LARGEST_INPUT_SIZE, "CSE".hashCode());
        List<Integer> l2 = generateInput(LARGEST_INPUT_SIZE, "417".hashCode());
        List<Integer> l3 = generateInput(LARGEST_INPUT_SIZE, "Algorithms".hashCode());
        List<Integer> l4 = generateInput(LARGEST_INPUT_SIZE, "Complexity".hashCode());
        List<Integer> l5 = generateInput(LARGEST_INPUT_SIZE, "BEST CLASS EVER!".hashCode());

        // count numbers of significant inversions
        long c1 = countSignificantInversions(l1);
        long c2 = countSignificantInversions(l2);
        long c3 = countSignificantInversions(l3);
        long c4 = countSignificantInversions(l4);
        long c5 = countSignificantInversions(l5);


        System.out.println("\n");
        System.out.println("The following five numbers represent the number of significant inversions for five randomly generated lists.");
        System.out.println("You should submit these five numbers on canvas.");
        // print numbers of significant inversions
        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);
        System.out.println(c4);
        System.out.println(c5);
    	/* DO NOT CHANGE THIS*/
    }

    /**
     * Generate a unsorted/random list
     * <p>
     * e.g When the input size is 10, the output file will look like following:
     * [4, 1, 7, 8, 6, 2, 5, 10, 9, 3]
     *
     * @param size the size of list to be returned.
     * @return a unsorted/random list
     */
    private static List<Integer> generateInput(int size, long seed) {
        // create an ordered list
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < size; i++) list.add(i + 1);

        // shuffle the list
        Collections.shuffle(list, new Random(seed));

        return list;
    }

}

