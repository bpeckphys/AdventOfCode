package utils;

public class GCD
{
    public static int findGCD(int first, int second)
    {
        int absFirst = Math.abs(first);

        if(second == 0)
        {
            return absFirst;
        }

        return findGCD(second, first % second);
    }
}
