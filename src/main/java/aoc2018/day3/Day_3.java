package aoc2018.day3;

import utils.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day_3 {
    private static Map<Pair, String> overlapMap = new HashMap<>();
    private static Map<Integer, Claim> claims = new HashMap<>();

    public static void main(String[] args) { run(); }

    public static void run() {
        try (Scanner scanner = new Scanner(new File("C:/dev/AdventOfCode/src/main/java/aoc2018/day3/Day_3_Input"))) {
            while (scanner.hasNext()) {
                String[] currentClaim = scanner.nextLine().substring(1).split("\\s@\\s|,|:\\s|x");
                int id = Integer.parseInt(currentClaim[0]);
                int x = Integer.parseInt(currentClaim[1]);
                int y = Integer.parseInt(currentClaim[2]);
                int width = Integer.parseInt(currentClaim[3]);
                int height = Integer.parseInt(currentClaim[4]);

                claims.put(id, new Claim(id, x, y, width, height));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Day 2:");
        System.out.println("\tSquare inches of overlap: " + getOverlap());
        System.out.println("\tID of claim with no overlap: " + getNonOverlappedId());
    }

    private static int getOverlap() {
        int overlap = 0;

        for (Map.Entry<Integer, Claim> claim : claims.entrySet()) {
            int x = claim.getValue().getX();
            int y = claim.getValue().getY();
            int width = claim.getValue().getWidth();
            int height = claim.getValue().getHeight();

            for (int i = x + 1; i < x + 1 + width; i++) {
                for (int j = y + 1; j < y + 1 + height; j++) {
                    Pair currentPair = new Pair(i, j);

                    if (overlapMap.containsKey(currentPair)) {
                        overlapMap.put(currentPair, "X");
                    } else {
                        overlapMap.put(currentPair, ".");
                    }
                }
            }
        }

        for (Map.Entry<Pair, String> entry : overlapMap.entrySet())
        {
            if (entry.getValue().equals("X"))
            {
                overlap++;
            }
        }

        return  overlap;
    }

    private static int getNonOverlappedId() {
        int id = -1;

        for (Map.Entry<Integer, Claim> claim : claims.entrySet()) {

            if (!hasOverlap(claim)) {
                id = claim.getValue().getId();
            }
        }

        return id;
    }

    private static boolean hasOverlap(Map.Entry<Integer, Claim> claim)
    {
        int x = claim.getValue().getX();
        int y = claim.getValue().getY();
        int width = claim.getValue().getWidth();
        int height = claim.getValue().getHeight();

        for (int i = x + 1; i < x + 1 + width; i++) {
            for (int j = y + 1; j < y + 1 + height; j++) {
                Pair currentPair = new Pair(i, j);

                if (overlapMap.get(currentPair).equals("X")) {
                    claim.getValue().setHasOverlap();
                    return true;
                }
            }
        }

        return false;
    }
}
