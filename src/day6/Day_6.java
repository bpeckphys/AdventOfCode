package day6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day_6
{
    public static void main(String[] args)
    {
        run();
    }

    public static void run()
    {
        ArrayList<String> input = new ArrayList<>();
        HashMap<String, HashSet<String>> orbits = new HashMap<>();

        System.out.println("Day 6:");

        try (Scanner scanner = new Scanner(new File("C:/dev/AdventOfCode/src/day6/Day_6_Input")))
        {
            scanner.useDelimiter("\\n");

            while (scanner.hasNextLine())
            {
                input.add(scanner.nextLine());
            }
        }
        catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < input.size(); i++)
        {
            String parent = input.get(i).split("\\)")[0];
            String child = input.get(i).split("\\)")[1];

            HashSet<String> currentChild = new HashSet<>();

            if (!orbits.containsKey(parent))
            {
                currentChild.add(child);

                orbits.put(parent, currentChild);
                continue;
            }

            currentChild = orbits.get(parent);
            currentChild.add(child);
            orbits.put(parent, currentChild);
        }

        int totalChildren = getChildren(orbits, "COM", 0);

        System.out.println("\tThe total number of direct and indirect orbits is: " + totalChildren);
    }

    public static int getChildren(HashMap<String, HashSet<String>> map, String parent, int level)
    {
        level += 1;
        int count = 0;

        if (map.containsKey(parent))
        {
            for (String child : map.get(parent))
            {
                count += getChildren(map, child, level);
            }

            count += map.get(parent).size() * level;
        }

        return count;
    }
}
