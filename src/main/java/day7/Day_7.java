package main.java.day7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import utils.Receiver;
import utils.Sender;

import static main.java.utils.IntCode.calculateIntCode;

public class Day_7
{
    public static void main(String[] args)
    {
        run();
    }

    public static void run()
    {
        ArrayList<Integer> input = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("C:/dev/AdventOfCode/src/main/java/day7/Day_7_InputTest"));
             Scanner scanner = new Scanner(reader.lines().collect(Collectors.joining())))
        {
            scanner.useDelimiter(",");

            while (scanner.hasNext())
            {
                int currentInt = Integer.parseInt(scanner.next());
                input.add(currentInt);
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

        int[] inPutArray = input.stream().mapToInt(i -> i).toArray();

        System.out.println("Day 7:");

        getOutputToThrusters(inPutArray, new int[]{4,3,2,1,0});
    }

    public static int getOutputToThrusters(int[] intCodeInput, int[] phaseSettings)
    {
        int[] signal = new int[]{0};

        for (int i = 0; i < phaseSettings.length; i++)
        {
            int phase = phaseSettings[i];
            boolean[] firstTime = new boolean[]{true};
            Supplier<Integer> receiver = () ->
            {
                if (firstTime[0]){ firstTime[0] = false; return phase; }
                else return signal[0];
            };
            Consumer<Integer> sender = out ->
            {
                System.out.println("\t\tOutput: " + out);
                signal[0] = out;
            };

            calculateIntCode(intCodeInput, receiver, sender);
        }

        return signal[0];
    }
}
