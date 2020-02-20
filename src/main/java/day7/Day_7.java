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

        long[] inPutArray = input.stream().mapToLong(i -> i).toArray();

        System.out.println("Day 7:");

        int[] phases = new int[]{0,1,2,3,4};

        long maxOutputToThrusters = getMaxOutputToThrusters(inPutArray, phases);
        System.out.println("\tMax output to thrusters is: " + maxOutputToThrusters);
    }

    private static long getOutputToThrusters(long[] intCodeInput, int[] phaseSettings)
    {
        long[] signal = new long[]{0};
        long[] intCodeInputCopy = intCodeInput.clone();
//        System.out.println("\tInput phases: " + phaseSettings[0] + ", " + phaseSettings[1] + ", " + phaseSettings[2] + ", " + phaseSettings[3] + ", " + phaseSettings[4] + ", ");

        for (long phase : phaseSettings) {
            boolean[] firstTime = new boolean[]{true};
            Supplier<Long> receiver = () ->
            {
                if (firstTime[0]) {
                    firstTime[0] = false;
                    return phase;
                } else return signal[0];
            };
            Consumer<Long> sender = out -> signal[0] = out.intValue();

            day7IntCode.calculateIntCode(intCodeInputCopy, receiver, sender);
        }

//        System.out.println("\t\tOutput: " + signal[0]);
        return signal[0];
    }

    private static long getMaxOutputToThrusters(long[] intCodeInput, int[] phaseSettings)
    {
        HashMap<Integer, int[]> phasePermutations = getPerms(phaseSettings);
        long maxOutput = Integer.MIN_VALUE;

        for (int[] currentIter : phasePermutations.values()) {
            long currentOutput = getOutputToThrusters(intCodeInput, currentIter);
//            System.out.println(currentIter);

            if (currentOutput > maxOutput) {
                maxOutput = currentOutput;
            }
        }

        return maxOutput;
    }
}
