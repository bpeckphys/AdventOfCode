package day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Day_4
{
    public static void main(String[] args)
    {
        run();
    }

    public static void run()
    {
        String inputRange;
        int startRange;
        int endRange;

        try (Scanner scanner = new Scanner(new File("C:/dev/AdventOfCode/src/day4/Day_4_Input")))
        {
            inputRange = scanner.next();
            startRange = Integer.parseInt(inputRange.split("-")[0]);
            endRange = Integer.parseInt(inputRange.split("-")[1]);
        }
        catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }

        int totalPossible = (int) IntStream.range(startRange, endRange + 1)
            .filter(value ->
                {
                    for (int i = 0; i < 5; i++)
                    {
                        char currentChar = Integer.toString(value).charAt(i);
                        char nextChar = Integer.toString(value).charAt(i + 1);

                        if (nextChar < currentChar || Integer.toString(value).length() != 6) return false;
                    }

                    return true;
                })
            .filter(value ->
                {
                    for (int i = 0; i < 5; i++)
                    {
                        char currentChar = Integer.toString(value).charAt(i);
                        char nextChar = Integer.toString(value).charAt(i + 1);

                        if (currentChar == nextChar) return true;
                    }

                    return false;
                })
            .filter(value ->
                {
                    HashMap<Character, Integer> charCounts = new HashMap<>();

                    for (int i = 0; i < 5; i++)
                    {
                        Character currentChar = Integer.toString(value).charAt(i);
                        Character nextChar = Integer.toString(value).charAt(i + 1);

                        if (currentChar.equals(nextChar))
                        {
                            charCounts.put(currentChar, charCounts.getOrDefault(currentChar, 1) + 1);
                        }
                    }

                    if (!charCounts.values().contains(2))
                    {
                        return false;
                    }

                    return true;
                })
            .count();

        System.out.println("Day 4:");
        System.out.println("\tThere are " + totalPossible + " passwords in the given range.");
    }
}
