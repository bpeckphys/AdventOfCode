package utils;

import java.util.Objects;

public class Pair
{
    public final int x;
    public final int y;

    public Pair(int firstValue, int secondValue)
    {
        this.x = firstValue;
        this.y = secondValue;
    }

    @Override
    public String toString()
    {
        return "(" + x + "," + y + ")";
    }

    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof Pair))
        {
            return false;
        }

        Pair pair = (Pair) o;
        return x == pair.x &&
                y == pair.y;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(x, y);
    }
}
