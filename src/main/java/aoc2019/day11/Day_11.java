package aoc2019.day11;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Day_11
{
    public static void main(String[] args)
    {
        run();
    }

    public static void run()
    {
        ArrayList<Long> input = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("C:/dev/AdventOfCode/src/main/java/aoc2019/day11/Day_11_Input"));
             Scanner scanner = new Scanner(reader.lines().collect(Collectors.joining())))
        {
            scanner.useDelimiter(",");

            while (scanner.hasNext())
            {
                long currentInt = Long.parseLong(scanner.next());
                input.add(currentInt);
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
