package aoc2019.day11;

public enum Direction
{
    Up(0),
    Down(1),
    Right(2),
    Left(3)
    ;

    public final int id;

    Direction(int id) {
        this.id = id;
    }

    public static int valueOf(Direction direction)
    {
        return direction.id;
    }
}
