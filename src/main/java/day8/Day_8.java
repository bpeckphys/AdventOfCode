package day8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Day_8
{
    public static void main(String[] args)
    {
        run();
    }

    public static void run()
    {
        char[] inputPutArray;
        int[]  integerInput;

        try (BufferedReader reader = new BufferedReader(new FileReader("C:/dev/AdventOfCode/src/main/java/day8/Day_8_Input"));
             Scanner scanner = new Scanner(reader.lines().collect(Collectors.joining())))
        {
            String input = scanner.next();
            inputPutArray = input.toCharArray();
            integerInput = new int[inputPutArray.length];

            for (int i = 0; i < inputPutArray.length; i++)
            {
                integerInput[i] = Integer.parseInt(String.valueOf(inputPutArray[i]));
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

        System.out.println("Day 8:");
        int   product = layerFewestZeros(integerInput);
        int[] output = imageResult(integerInput);

        System.out.println("\tLayer with fewest zeros has product of 1 and 2: " + product);
        System.out.print("\tImage result is: " );
        for (int i = 0; i < output.length; i++)
        {
            if (i % 25 == 0)
            {
                System.out.println();
                System.out.print("\t\t");
            }

            if (output[i] == 1)
            {
                System.out.print("*");
            }
            else
            {
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    private static int[] imageResult(int[] inputArray)
    {
        int[] imageOutput = new int[25 * 6];

        for (int i = 0; i < imageOutput.length; i++)
        {
            imageOutput[i] = 2;
        }

        for (int i = 0; i < inputArray.length; i++)
        {
            if (imageOutput[i % (25 * 6)] != 2)
            {
                continue;
            }

            imageOutput[i % (25 * 6)] = inputArray[i];
        }

        return imageOutput;
    }

    private static int layerFewestZeros(int[] inputArray)
    {
        int numZeros = Integer.MAX_VALUE;
        int currentNumZeros = 0;
        int numOnes = 0;
        int currentNumOnes = 0;
        int numTwos = 0;
        int currentNumTwos = 0;

        for (int i = 0; i < inputArray.length; i++)
        {
            if (i % (25 * 6) == 0)
            {
                if (currentNumZeros < numZeros)
                {
                    numZeros = currentNumZeros;
                    numOnes = currentNumOnes;
                    numTwos = currentNumTwos;
                }
                else
                {
                    currentNumZeros = Integer.MAX_VALUE;
                    currentNumOnes = 0;
                    currentNumTwos = 0;
                }
            }

            if (inputArray[i] == 0)
            {
                currentNumZeros++;
            }

            if (inputArray[i] == 1)
            {
                currentNumOnes++;
            }

            if (inputArray[i] == 2)
            {
                currentNumTwos++;
            }
        }

        return numOnes * numTwos;
    }
}
