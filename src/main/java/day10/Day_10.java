package day10;

import utils.GCD;
import utils.Pair;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Day_10
{
    public static void main(String[] args)
    {
        run();
    }

    public static void run()
    {
        ArrayList<String> input = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File("C:/dev/AdventOfCode/src/main/java/day10/Day_10_Input")))
        {
            while (scanner.hasNextLine())
            {
                input.add(scanner.nextLine());
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

        HashMap<Pair, Set<Pair>> asteroids = new HashMap<>();

        for (int y = 0; y < input.size(); y++)
        {
            for (int x = 0; x < input.get(y).length(); x++)
            {
                if (input.get(y).charAt(x) == '#')
                {
                    asteroids.put(new Pair(x, y), new HashSet<>());
                }
            }
        }

        Pair maxAsteroid = new Pair(0,0);
        int maxAsteroidView = 0;

        for (Map.Entry<Pair, Set<Pair>> entry : asteroids.entrySet())
        {
            for (Map.Entry<Pair, Set<Pair>> entry2 : asteroids.entrySet())
            {
                if (!entry.equals(entry2))
                {
                    int dx = entry.getKey().x - entry2.getKey().x;
                    int dy = entry.getKey().y - entry2.getKey().y;
                    int gcd = GCD.findGCD(dx, dy);

                    Pair reducedPair = new Pair(dx / gcd, dy / gcd);

                    entry.getValue().add(reducedPair);
                }

                if (entry.getValue().size() > maxAsteroidView)
                {
                    maxAsteroid = entry.getKey();
                    maxAsteroidView = entry.getValue().size();
                }
            }
        }

        asteroids.remove(new Pair(30,34));

        System.out.println("Day 10:");
        System.out.println("\tThe best location for the monitoring station is: " + maxAsteroid.toString());
        System.out.println("\tThe station could detect " + maxAsteroidView + " asteroids");
        System.out.println();

        Comparator<Pair> compareByAngle = Comparator.comparingDouble(asteroid ->
                (Math.atan2(asteroid.y - 34, asteroid.x - 30) - Math.toRadians(269)) % Math.toRadians(360));
        Comparator<Pair> compareByDistance = Comparator.comparingDouble(asteroid ->
                Math.sqrt(Math.pow(asteroid.y - 34, 2) + Math.pow(asteroid.x - 30, 2)));

        ArrayList<Pair> sortedAsteroids = asteroids.keySet().stream()
            .sorted(compareByAngle.thenComparing(compareByDistance.reversed()))
            .collect(Collectors.toCollection(ArrayList::new));

        int i = 0;
        int asteroidsRemoved = 0;

        while (sortedAsteroids.size() > 0)
        {
            if (i + 1 >= sortedAsteroids.size())
            {
                i = 0;
                continue;
            }

            if (Math.atan2(sortedAsteroids.get(i).y - 34, sortedAsteroids.get(i).x - 30) == Math.atan2(sortedAsteroids.get(i + 1).y - 34, sortedAsteroids.get(i + 1).x - 30))
            {
                i++;
                continue;
            }

            if (asteroidsRemoved == 199)
            {
                System.out.println("\tThe 200th asteroid destroyed is " + sortedAsteroids.get(i));
                break;
            }

            sortedAsteroids.remove(sortedAsteroids.get(i));
            asteroidsRemoved++;
        }

        int output = sortedAsteroids.get(i).x * 100 + sortedAsteroids.get(i).y;

        System.out.println("\tThe output for the bet is: " + output);
    }
}
