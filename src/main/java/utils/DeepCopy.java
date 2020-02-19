package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DeepCopy
{
    public static List<Integer> copyIntegerArrayList(ArrayList<Integer> input)
    {
        int[] listCopy = input.stream().mapToInt().toArray();

        return listCopy;

//        for (T element : input)
//        {
//            T value = element;
//            listCopy.add(value);
//        }
    }
}
