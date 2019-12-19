package day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

import static utils.IntCode.calculateIntCode;

public class Day_5
{
    public static void main(String[] args)
    {
        run();
    }

    public static void run()
    {
        ArrayList<Integer> input = new ArrayList<>();
        Integer[] inPutArray;

        try (BufferedReader reader = new BufferedReader(new FileReader("C:/dev/AdventOfCode/src/day5/Day_5_Input"));
             Scanner scanner = new Scanner(reader.lines().collect(Collectors.joining())))
        {
            scanner.useDelimiter(",");

            while (scanner.hasNext())
            {
                int currentInt = Integer.parseInt(scanner.next());
                input.add(currentInt);
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

        inPutArray = new Integer[input.size()];
        inPutArray = input.toArray(inPutArray);

        System.out.println("Day 5:");

        calculateIntCode(inPutArray, 1);
        calculateIntCode(inPutArray, 5);
    }
}
