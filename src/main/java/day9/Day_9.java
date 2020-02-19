//package day9;
//
//import utils.IntCode;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Scanner;
//import java.util.function.Consumer;
//import java.util.stream.Collectors;
//
//public class Day_9
//{
//    public static void main(String[] args)
//    {
//        run();
//    }
//
//    public static void run()
//    {
//        ArrayList<Integer> input = new ArrayList<>();
//
//        try (BufferedReader reader = new BufferedReader(new FileReader("C:/dev/AdventOfCode/src/main/java/day9/Day_9_Input_Test"));
//             Scanner scanner = new Scanner(reader.lines().collect(Collectors.joining())))
//        {
//            scanner.useDelimiter(",");
//
//            while (scanner.hasNext())
//            {
//                int currentInt = Integer.parseInt(scanner.next());
//                input.add(currentInt);
//            }
//        }
//        catch (IOException e)
//        {
//            throw new RuntimeException(e);
//        }
//
//        int[] inputArray = input.stream()
//            .mapToInt(i ->
//            {
////                System.out.println(i);
//                return i;
//            })
//            .toArray();
//
//        IntCode day9IntCode = new IntCode();
//
//        System.out.println("Day 9:");
//
//        System.out.print("\t");
//
//        boolean[] firstTime = new boolean[]{true};
//        Consumer<Integer> sender = out ->
//        {
//            if (firstTime[0])
//            {
//                firstTime[0] = false;
//                System.out.print(out);
//            }
//            else
//            {
//                System.out.print("," + out);
//            }
//        };
//
//        day9IntCode.calculateIntCode(inputArray, () -> 1, sender);
//    }
//}
