package utils;

import java.util.*;

public class PermutingArray
{
    public static HashMap<Integer, int[]> getPerms(int[] array)
    {
        int arrayCount = 0;
        HashMap<Integer, int[]> permutations = new HashMap<>();
        int[] tempArray = array.clone();
        permutations.put(arrayCount++, tempArray);
        int n = array.length;
        int[] indexes = new int[n];

        int i = 0;
        while (i < n)
        {
            if (indexes[i] < i)
            {
                swap(array, i % 2 == 0 ? 0 : indexes[i], i);
                tempArray = array.clone();
                permutations.put(arrayCount++, tempArray);
                indexes[i]++;
                i = 0;
            }
            else
            {
                indexes[i] = 0;
                i++;
            }
        }

        return permutations;
    }

    private static int[] swap(int[] input, int a, int b)
    {
        int temp = input[a];
        input[a] = input[b];
        input[b] = temp;

        return input;
    }
}
