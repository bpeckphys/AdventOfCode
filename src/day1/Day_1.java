package day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day_1
{
    public static void main(String[] args)
    {
        run();
    }

    public static void run()
    {
        ArrayList<Integer> inputList = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File("C:/dev/AdventOfCode/src/day1/Day_1_Input")))
        {
            while (scanner.hasNextInt())
            {
                inputList.add(scanner.nextInt());
            }
        }
        catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }

        System.out.println("Day 1:");
        System.out.println("\tTotal required fuel is: " + calculateFuel(inputList));
    }

    static int calculateFuel(ArrayList<Integer> masses)
    {
        int totalFuel = 0;

        for (int i = 0; i < masses.size(); i++)
        {
            int currentMass = masses.get(i);
            int additionalFuel = calculateAdditionalFuel(currentMass);
            totalFuel = totalFuel + additionalFuel;
        }

        return totalFuel;
    }

    static int calculateAdditionalFuel(int mass)
    {
        if ((mass / 3) - 2 <= 0)
        {
            return 0;
        }

        int additionalMass = (mass / 3) - 2;

        return additionalMass + calculateAdditionalFuel(additionalMass);
    }
}
