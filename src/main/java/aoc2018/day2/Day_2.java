package aoc2018.day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day_2
{
    public static void main(String[] args)
    {
        run();
    }

    public static void run()
    {
        ArrayList<String> inputList = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File("C:/dev/AdventOfCode/src/main/java/aoc2018/day2/Day_2_Input")))
        {
            while (scanner.hasNext())
            {
                inputList.add(scanner.nextLine());
            }
        }
        catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }

        System.out.println("Day 2:");
        System.out.println("\tCheckSum: " + checkSum(inputList));
        System.out.println("\tThe common characters are: " + getCommonCharacters(inputList));
    }

    private static int checkSum(List<String> inputList)
    {
        int twoCount = 0;
        int threeCount = 0;

        for (String id : inputList)
        {
            int[] charCounts = new int[26];
            boolean hasTwo = false;
            boolean hasThree = false;

            for (int i = 0; i < id.length(); i++)
            {
                charCounts[id.charAt(i) - 'a']++;
            }

            for (int i = 0; i < charCounts.length; i++)
            {
                if (charCounts[i] == 2)
                {
                    hasTwo = true;
                }

                if (charCounts[i] == 3)
                {
                    hasThree = true;
                }
            }

            if (hasTwo)
            {
                twoCount++;
            }

            if (hasThree)
            {
                threeCount++;
            }
        }

        return twoCount * threeCount;
    }

    private static String getCommonCharacters(List<String> inputList)
    {
        StringBuilder commonChars = new StringBuilder();

        for (int i = 0; i < inputList.size() - 1; i++)
        {
            for (int j = i + 1; j < inputList.size(); j++)
            {
                int differentChars = 0;
                int differenceLocation = -1;

                for (int c = 0; c < inputList.get(i).length(); c++)
                {
                    if (inputList.get(i).charAt(c) != inputList.get(j).charAt(c))
                    {
                        differentChars++;
                        differenceLocation = c;
                    }

                    if (differentChars > 1)
                    {
                        break;
                    }

                    if (c == inputList.get(i).length() - 1)
                    {
                        for (int k = 0; k < inputList.get(i).length(); k++)
                        {
                            if (k == differenceLocation)
                            {
                                continue;
                            }

                            commonChars.append(inputList.get(i).charAt(k));
                        }
                    }
                }
            }
        }

        return commonChars.toString();
    }
}
