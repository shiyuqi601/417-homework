import com.sun.scenario.effect.Merge;
import com.sun.tools.corba.se.idl.constExpr.ShiftRight;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

/**
 * Created by yuqishi on 1/22/17.
 */
public class invCount {

    public static long merge(int[] arr, int[] left, int[] right) {
        /*
        Object[] arr = List.toArray();
        Object[] left = left.toArray();
        Object[] right = right.toArray();


        int arr[], left[], right[];

        List<Integer> List = new ArrayList<Integer>();
        Integer[] arr = List.toArray(new Integer[0]);

        List<Integer> left = new ArrayList<Integer>();
        Integer[] left = left.toArray(new Integer[0]);

        List<Integer> right = new ArrayList<Integer>();
        Integer[] right = right.toArray(new Integer[0]);
        //int[] intArray = ArrayUtils.toPrimitive(integerArray);


        List<Integer> list = Arrays.asList(arr);
        Integer[] arr = list.toArray(new Integer[list.size()]);*/

        int i = 0, j = 0, z = 0, count = 0;
        while (i < left.length || j < right.length) {
            if (i == left.length) {
                arr[i+j] = right[j];
                j++;
            } else if (j == right.length) {
                arr[i+j] = left[i];
                i++;
            } else if (left[i] <= right[j]) {
                arr[i+j] = left[i];
                i++;
            } else if (left[i] > right[j]){
                arr[i+j] = right[j];

                if (left[i] > right[j]*2){
                    count += left.length - i;
                } else {
                    for (z = i; z < left.length; z++){
                        if (arr[z] > right[j]*2){
                            count += left.length - z;
                        }
                    }
                }
                j++;
            }
        }

        //System.out.println(Arrays.toString(left));
        //System.out.println(Arrays.toString(right));
        //System.out.println(Arrays.toString(arr));

        return count;
    }

    public static long invCount(int[] arr) {
        if (arr.length < 2)
            return 0;

        //int m = (arr.length + 1) / 2;
        int m = arr.length / 2;
        int left[] = Arrays.copyOfRange(arr, 0, m);
        int right[] = Arrays.copyOfRange(arr, m, arr.length);

        return invCount(left) + invCount(right) + merge(arr, left, right);
    }
    public static void main(String[] args) {
        int[] test = {7,3,2,1,15,31};
        System.out.println(test.length);
        System.out.println(invCount(test));
    }
}
