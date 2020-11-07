package aoc2018.day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day_1
{
    public static void main(String[] args)
    {
        run();
    }

    public static void run()
    {
        ArrayList<String> inputList = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File("C:/dev/AdventOfCode/src/main/java/aoc2018/day1/Day_1_Input")))
        {
            while (scanner.hasNext())
            {
                inputList.add(scanner.nextLine());
            }
        }
        catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }

        System.out.println("Day 1:");
        System.out.println("\tResulting Frequency: " + calculateFrequency(inputList));
        System.out.println("\tFirst Duplicate Frequency: " + calculateDuplicateFrequency(inputList));
    }

    private static int calculateFrequency(List<String> stringList)
    {
        int frequency = 0;

        for (String element : stringList)
        {
            if (element.startsWith("+"))
            {
                frequency += Integer.parseInt(element.substring(1));
            }

            if (element.startsWith("-"))
            {
                frequency -= Integer.parseInt(element.substring(1));
            }
        }

        return frequency;
    }

    private static int calculateDuplicateFrequency(List<String> stringList)
    {
        Set<Integer> frequencies = new HashSet<>();
        int currentFrequency = 0;

        for (int i = 0; i < stringList.size(); i++)
        {
            String currentAdjustment = stringList.get(i);

            if (currentAdjustment.startsWith("+"))
            {
                currentFrequency += Integer.parseInt(currentAdjustment.substring(1));
            }

            if (currentAdjustment.startsWith("-"))
            {
                currentFrequency -= Integer.parseInt(currentAdjustment.substring(1));
            }

            if (frequencies.contains(currentFrequency))
            {
                return currentFrequency;
            }

            frequencies.add(currentFrequency);

            if (i == stringList.size() - 1)
            {
                i = -1;
            }
        }

        return calculateDuplicateFrequency(stringList);
    }
}
