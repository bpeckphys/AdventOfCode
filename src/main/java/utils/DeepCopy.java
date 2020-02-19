package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DeepCopy extends ArrayList<Integer>
{
    public static ArrayList<Integer> copyIntegerArrayList(ArrayList<Integer> input)
    {
        int[] listCopy = input.stream().mapToInt(Integer::intValue).toArray().clone();

        ArrayList<Integer> copy = Arrays.stream(listCopy).boxed().collect(Collectors.toCollection(ArrayList::new));

        return copy;
    }
}
