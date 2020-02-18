package day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import utils.IntCode;

public class Day_2
{
    private static IntCode day2IntCode = new IntCode();

    public static void main(String[] args)
    {
        run();
    }

    public static void run()
    {
        ArrayList<Integer> input = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("C:/dev/AdventOfCode/src/main/java/day2/Day_2_Input"));
             Scanner scanner = new Scanner(reader.readLine()))
        {
            scanner.useDelimiter(",");

            while (scanner.hasNext()) {
                int currentInt = Integer.parseInt(scanner.next());
                input.add(currentInt);
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

        int[] inputArray = input.stream().mapToInt(i -> i).toArray();
        int[] inputArrayCopy = inputArray.clone();

        // Declare values as final and assign to the array
        inputArray[1] = 12;
        inputArray[2] = 2;

        day2IntCode.calculateIntCode(inputArray, null, null);

        System.out.println("Day 2:");
        System.out.println("\tValue at position 0 is: " + inputArray[0]);

        int[] inputs = getInputs(inputArrayCopy, 19690720);

        System.out.println("\tInput noun and verb that output 19690720 are: " + inputs[0] + ", " + inputs[1]);
        System.out.println("\t(100 * noun) + verb is: " + ((100 * inputs[0]) + inputs[1]));
    }

    public static int[] getInputs(int[] inputIntCode, int output)
    {
        int[] result = new int[2];

        loop: for (int i = 0; i <= 99; i++)
        {
            for (int j = 0; j <= 99; j++)
            {
                int[] outPutArray = inputIntCode.clone();
                outPutArray[1] = i;
                outPutArray[2] = j;

                day2IntCode.calculateIntCode(outPutArray, null, null);

                if (outPutArray[0] == output)
                {
                    result[0] = i;
                    result[1] = j;
                    break loop;
                }
            }
        }

        return result;
    }
}
