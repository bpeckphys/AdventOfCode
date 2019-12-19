package day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static utils.IntCode.calculateIntCode;

public class Day_2
{
    public static void main(String[] args)
    {
        run();
    }

    public static void run()
    {
        ArrayList<Integer> input = new ArrayList<>();
        ArrayList<Integer> output = new ArrayList<>();
        Integer[] inPutArray;
        Integer[] outputArray;
        Integer finalOutput;

        try (BufferedReader reader = new BufferedReader(new FileReader("C:/dev/AdventOfCode/src/day2/Day_2_Input"));
             Scanner scanner = new Scanner(reader.readLine()))
        {
            scanner.useDelimiter(",");

            while (scanner.hasNext()) {
                int currentInt = Integer.parseInt(scanner.next());
                input.add(currentInt);
                output.add(currentInt);
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

        inPutArray = new Integer[output.size()];
        inPutArray = output.toArray(inPutArray);
        inPutArray[1] = 12;
        inPutArray[2] = 2;

        outputArray = new Integer[output.size()];
        outputArray = output.toArray(outputArray);

        finalOutput = calculateIntCode(inPutArray, null);

        System.out.println("Day 2:");
        System.out.println("\tValue at position 0 is: " + finalOutput);

        int[] inputs;
        inputs = getInputs(outputArray, 19690720);

        System.out.println("\tInput noun and verb that output 19690720 are: " + inputs[0] + ", " + inputs[1]);
        System.out.println("\t(100 * noun) + verb is: " + ((100 * inputs[0]) + inputs[1]));
    }

    public static int[] getInputs(Integer[] inputIntCode, int output)
    {
        int finalOutput = 0;
        int i, j;
        int[] result = new int[2];
        Integer[] outPutArray = inputIntCode.clone();

        loop: for (i = 0; i <= 99; i++)
        {
            for (j = 0; j <= 99; j++)
            {
                outPutArray[1] = i;
                outPutArray[2] = j;

                finalOutput = calculateIntCode(outPutArray, null);

                if (finalOutput == output)
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
