package aoc2019.day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import utils.IntCode;

public class Day_5
{
    public static IntCode day5IntCode = new IntCode();

    public static void main(String[] args)
    {
        run();
    }

    public static void run()
    {
        ArrayList<Integer> input = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("C:/dev/AdventOfCode/src/main/java/aoc2019/day5/Day_5_Input"));
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

        long[] inputArray = input.stream().mapToLong(i -> i).toArray();
        long[] inputArrayClone = inputArray.clone();

        System.out.println("Day 5:");

        Consumer<Long> sender = out -> System.out.println("\t\tOutput: " + out);
        System.out.println("\tInput value: 1");

        day5IntCode.calculateIntCode(inputArray, () -> 1L, sender);

        System.out.println("\tInput value: 5");
        day5IntCode.calculateIntCode(inputArrayClone, () -> 5L, sender);
    }
}
