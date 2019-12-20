package day6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day_6
{
    private static HashMap<String, String> parents = new HashMap<>();
    private static HashMap<String, HashSet<String>> orbits = new HashMap<>();

    public static void main(String[] args)
    {
        run();
    }

    public static void run()
    {
        ArrayList<String> input = new ArrayList<>();

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

            parents.put(child, parent);

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

        int totalChildren = getChildren("COM", 0);
        int totalOrbitJumps = getOrbitJumps("YOU", "SAN");

        System.out.println("\tThe total number of direct and indirect orbits is: " + totalChildren);
        System.out.println("\tThe total number of orbit jumps to get to Santa is: " + totalOrbitJumps);
    }

    public static int getChildren(String parent, int level)
    {
        level += 1;
        int count = 0;

        if (orbits.containsKey(parent))
        {
            for (String child : orbits.get(parent))
            {
                count += getChildren(child, level);
            }

            count += orbits.get(parent).size() * level;
        }

        return count;
    }

    public static int getOrbitJumps(String me, String santa)
    {
        HashSet<String> myOrbits = new HashSet<>();
        HashSet<String> santasOrbits = new HashSet<>();
        HashSet<String> comOrbits = new HashSet<>();

        while (!me.equals("COM"))
        {
            System.out.println("me: " + me);
            me = parents.get(me);
            myOrbits.add(me);
        }

        while (!santa.equals("COM"))
        {
            System.out.println("santa: " + santa);
            santa = parents.get(santa);
            santasOrbits.add(santa);
        }

        for (String orbit : myOrbits)
        {
            if (!santasOrbits.contains(orbit))
            {
                comOrbits.add(orbit);
            }
        }

        for (String orbit : santasOrbits)
        {
            if (!myOrbits.contains(orbit))
            {
                comOrbits.add(orbit);
            }
        }

        return comOrbits.size() + 1;
    }
}
