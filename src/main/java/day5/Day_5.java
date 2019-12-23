package main.java.day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static main.java.utils.IntCode.calculateIntCode;

public class Day_5
{
    public static void main(String[] args)
    {
        run();
    }

    public static void run()
    {
        ArrayList<Integer> input = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("C:/dev/AdventOfCode/src/main/java/day5/Day_5_Input"));
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

        int[] inputArray = input.stream().mapToInt(i -> i).toArray();
        int[] inputArrayClone = inputArray.clone();

        System.out.println("Day 5:");

        Consumer<Integer> sender = out -> { System.out.println("\t\tOutput: " + out); };

        calculateIntCode(inputArray, () -> 1, sender);
        calculateIntCode(inputArrayClone, () -> 5, sender);
    }
}
