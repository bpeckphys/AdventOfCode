package aoc2019.day9;

import utils.IntCode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Day_9
{
    public static void main(String[] args)
    {
        run();
    }

    public static void run()
    {
        ArrayList<Long> input = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("C:/dev/AdventOfCode/src/main/java/aoc2019/day9/Day_9_Input"));
             Scanner scanner = new Scanner(reader.lines().collect(Collectors.joining())))
        {
            scanner.useDelimiter(",");

            while (scanner.hasNext())
            {
                long currentLong = Long.parseLong(scanner.next());
                input.add(currentLong);
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

        long[] inputArray = input.stream()
            .mapToLong(i -> i)
            .toArray();

        IntCode day9IntCode = new IntCode();
        IntCode day9IntCodeCoord = new IntCode();

        System.out.println("Day 9:");

        System.out.print("\tBOOST keycode produced: ");

        Consumer<Long> sender = System.out::println;

        day9IntCode.calculateIntCode(inputArray, () -> 1L, sender);

        System.out.print("\tCoordinates of distress signal: ");

        day9IntCodeCoord.calculateIntCode(inputArray, () -> 2L, sender);
    }
}
