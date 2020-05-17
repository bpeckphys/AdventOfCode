package aoc2019.day11;

import utils.IntCode;
import utils.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static aoc2019.day11.Direction.*;
import static aoc2019.day11.PaintColor.*;

public class HullPaintingRobot
{
    private static IntCode               day11IntCode = new IntCode();
    private static Direction             direction;
    private static Map<Pair, PaintColor> hull = new HashMap<>();
    private static Pair                  currentLocation = new Pair(0,0);

    public HullPaintingRobot()
    {
        // Starting direction is up
        direction = Direction.Up;
        hull.put(currentLocation, Black);
    }

    public static int getPanelsPainted(long[] intCodeInput)
    {
        long[] intCodeInputCopy = intCodeInput.clone();
        AtomicInteger panelsPainted = new AtomicInteger();
        AtomicBoolean isPaintInstruction = new AtomicBoolean();

        Supplier<Long> receiver = () ->
        {
            if (hull.get(currentLocation) == Black)
            {
                return 0L;
            }

            return 1L;
        };

        // There will be two outputs: first color, second direction
        isPaintInstruction.set(true);

        Consumer<Long> sender = out ->
        {
            if (isPaintInstruction.get()) {
                isPaintInstruction.set(false);

//                System.out.println(out.intValue() + " " + hull.get(currentLocation).id);

                if (out.intValue() != hull.get(currentLocation).id)
                {
                    panelsPainted.getAndIncrement();
//                    System.out.println("BEFORE: " + currentLocation + " " + hull.get(currentLocation));
                    paint(currentLocation);
//                    System.out.println("AFTER: " + currentLocation + " " + hull.get(currentLocation));
                } else {
                    System.out.println("ELSE!!!!!");
                }
            } else {
                isPaintInstruction.set(true);

                if (out.equals(0L))
                {
                    turnLeft();
                } else {
                    turnRight();
                }
            }
        };

        day11IntCode.calculateIntCode(intCodeInputCopy, receiver, sender);

        return panelsPainted.get();
    }

    public static void paint(Pair currentLocation)
    {
        if (hull.get(currentLocation).equals(Black))
        {
//            System.out.println("Black to White!");
            hull.replace(currentLocation, White);
        } else {
//            System.out.println("White to Black!");
            hull.replace(currentLocation, Black);
        }
    }

    public static void turnLeft()
    {
        int x = currentLocation.x;
        int y = currentLocation.y;

        switch (direction)
        {
            case Up:    direction = Left;  currentLocation = new Pair(x - 1, y); break;
            case Down:  direction = Right; currentLocation = new Pair(x + 1, y); break;
            case Left:  direction = Down;  currentLocation = new Pair(x, y - 1); break;
            case Right: direction = Up;    currentLocation = new Pair(x, y + 1); break;
        }

        hull.putIfAbsent(currentLocation, Black);
    }

    public static void turnRight()
    {
        int x = currentLocation.x;
        int y = currentLocation.y;

        switch (direction)
        {
            case Up:    direction = Right; currentLocation = new Pair(x + 1, y); break;
            case Down:  direction = Left;  currentLocation = new Pair(x - 1, y); break;
            case Left:  direction = Up;    currentLocation = new Pair(x, y + 1); break;
            case Right: direction = Down;  currentLocation = new Pair(x, y - 1); break;
        }

        hull.putIfAbsent(currentLocation, Black);
    }
}
