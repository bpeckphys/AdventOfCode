package day7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import utils.IntCode;

import static utils.PermutingArray.getPerms;

public class Day_7
{
    public static IntCode day7IntCode = new IntCode();

    public static void main(String[] args)
    {
        run();
    }

    public static void run()
    {
        ArrayList<Integer> input = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("C:/dev/AdventOfCode/src/main/java/day7/Day_7_Input"));
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

        int[] phases = new int[]{0,1,2,3,4};

        int maxOutputToThrusters = getMaxOutputToThrusters(inPutArray, phases);
        System.out.println("\tMax output to thrusters is: " + maxOutputToThrusters);
    }

    private static int getOutputToThrusters(int[] intCodeInput, int[] phaseSettings)
    {
        int[] signal = new int[]{0};
        int[] intCodeInputCopy = intCodeInput.clone();
//        System.out.println("\tInput phases: " + phaseSettings[0] + ", " + phaseSettings[1] + ", " + phaseSettings[2] + ", " + phaseSettings[3] + ", " + phaseSettings[4] + ", ");

        for (int phase : phaseSettings) {
            boolean[] firstTime = new boolean[]{true};
            Supplier<Integer> receiver = () ->
            {
                if (firstTime[0]) {
                    firstTime[0] = false;
                    return phase;
                } else return signal[0];
            };
            Consumer<Integer> sender = out -> signal[0] = out;

            day7IntCode.calculateIntCode(intCodeInputCopy, receiver, sender);
        }

//        System.out.println("\t\tOutput: " + signal[0]);
        return signal[0];
    }

    private static int getMaxOutputToThrusters(int[] intCodeInput, int[] phaseSettings)
    {
        HashMap<Integer, int[]> phasePermutations = getPerms(phaseSettings);
        int maxOutput = Integer.MIN_VALUE;

        for (int[] currentIter : phasePermutations.values()) {
            int currentOutput = getOutputToThrusters(intCodeInput, currentIter);
//            System.out.println(currentIter);

            if (currentOutput > maxOutput) {
                maxOutput = currentOutput;
            }
        }

        return maxOutput;
    }
}
