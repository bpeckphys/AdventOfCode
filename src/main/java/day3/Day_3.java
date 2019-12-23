package main.java.day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.function.Function;
import utils.Pair;

public class Day_3
{

    public static void main(String[] args)
    {
        run();
    }

    public static void run()
    {
        HashMap<Pair, Integer> firstPairMap;
        HashMap<Pair, Integer> secondPairMap;

        try (Scanner scanner = new Scanner(new File("C:/dev/AdventOfCode/src/main/java/day3/Day_3_Input")))
        {
            firstPairMap = getPairMap(scanner.nextLine());
            secondPairMap = getPairMap(scanner.nextLine());
        }
        catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }

        Function<Map.Entry<Pair, Integer>, Integer> func1 = entry -> Math.abs(entry.getKey().x) + Math.abs(entry.getKey().y);
        Function<Map.Entry<Pair, Integer>, Integer> func2 = entry -> secondPairMap.get(entry.getKey()) + entry.getValue();

        Function<Function<Map.Entry<Pair, Integer>, Integer>, Integer> func3 = mapper -> firstPairMap.entrySet().stream()
            .filter(entry -> secondPairMap.containsKey(entry.getKey()))
            .map(mapper)
            .min(Integer::compareTo)
            .orElse(Integer.MAX_VALUE);

        int manhattanDistance = func3.apply(func1);
        int combinedSteps = func3.apply(func2);

        System.out.println("Day 3:");
        System.out.println("\tThe manhattanDistance from the central port to the closest intersection is: " + manhattanDistance);
        System.out.println("\tThe fewest combined steps to reach an intersection is: " + combinedSteps);
    }

    public static HashMap<Pair, Integer> getPairMap(String input)
    {
        Pair prev = new Pair(0,0);
        String[] wire = input.split(",");
        HashMap<Pair, Integer> pairMap = new HashMap<>();
        int steps = 1;

        for (int i = 0; i < wire.length; i++)
        {
            char direction = wire[i].charAt(0);
            int magnitude = Integer.parseInt(wire[i].substring(1));

            for (int j = 0; j < magnitude; j++)
            {
                switch (direction)
                {
                    case 'U': prev = new Pair(prev.x, prev.y + 1); break;
                    case 'D': prev = new Pair(prev.x, prev.y - 1); break;
                    case 'R': prev = new Pair(prev.x + 1, prev.y); break;
                    case 'L': prev = new Pair(prev.x - 1, prev.y); break;

                    // Add default case, maybe throw an exception
                }

                pairMap.put(prev, steps++);
            }
        }

        return pairMap;
    }
}
