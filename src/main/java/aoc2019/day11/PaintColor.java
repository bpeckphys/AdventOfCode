package aoc2019.day11;

public enum PaintColor
{
    Black(0),
    White(1)
    ;

    public final int id;

    PaintColor(int id) {
        this.id = id;
    }

    public static int valueOf(PaintColor paintColor)
    {
        return paintColor.id;
    }
}
