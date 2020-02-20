package utils;

import java.util.HashMap;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class IntCode
{
    private long relativeBase = 0;
    private HashMap<Long, Long> intCode = new HashMap<>();

    public void calculateIntCode(long[] inputIntCode, Supplier<Long> receiver, Consumer<Long> sender)
    {
        boolean run = true;

        for (long i = 0; i < inputIntCode.length; i++)
        {
            intCode.put(i, inputIntCode[(int) i]);
        }

        long i = 0;

        while (i < intCode.size() && run)
        {
            long value;
            int[] opCode = getOpCode(Math.toIntExact(intCode.get(i)));
            int paramOne = opCode[2];
            int paramTwo = opCode[1];
            int paramThree = opCode[0];
            int instructions = opCode[3] * 10 + opCode[4];
            long valueOne;
            long valueTwo;

            switch (instructions)
            {
                case 1:
                    value = modeOutput(1, paramOne, i)
                            + modeOutput(2, paramTwo, i);
                    intCode.replace(intCode.get(i + 3), value);

                    i += 4;

                    break;

                case 2:
                    value = modeOutput(1, paramOne, i)
                            * modeOutput(2, paramTwo, i);
                    intCode.replace(intCode.get(i + 3), value);

                    i += 4;

                    break;

                case 3:
                    long input = receiver.get();
                    intCode.replace(intCode.get(i + 1), input);

                    i += 2;

                    break;

                case 4:
                    value = modeOutput(1, paramOne, i);
                    sender.accept(value);

                    i += 2;

                    break;

                case 5:
                    valueOne = modeOutput(1, paramOne, i);
                    valueTwo = modeOutput(2, paramTwo, i);

                    i = valueOne != 0 ? valueTwo : i + 3;

                    break;

                case 6:
                    valueOne = modeOutput(1, paramOne, i);
                    valueTwo = modeOutput(2, paramTwo, i);

                    i = valueOne == 0 ? valueTwo : i + 3;

                    break;

                case 7:
                    valueOne = modeOutput(1, paramOne, i);
                    valueTwo = modeOutput(2, paramTwo, i);

                    intCode.replace(intCode.get(i + 3), (valueOne < valueTwo ? 1L : 0));

                    i += 4;

                    break;

                case 8:
                    valueOne = modeOutput(1, paramOne, i);
                    valueTwo = modeOutput(2, paramTwo, i);

                    intCode.replace(intCode.get(i + 3), (long) (valueOne == valueTwo ? 1 : 0));

                    i += 4;

                    break;

                case 9:
                    relativeBase += intCode.get(i + 1);

                    i += 2;

                    break;

                case 99:
                    run = false;

                    break;
            }
        }
    }

    private int[] getOpCode(int opInput)
    {
        int[] opCode = new int[5];

        StringBuilder operation = new StringBuilder(Integer.toString(opInput));

        while (operation.length() < 5)
        {
            operation.insert(0, "0");
        }

        for (int i = 0; i < 5; i++)
        {
            opCode[i] = Integer.parseInt(operation.substring(i, i + 1));
        }

        return opCode;
    }

    private long modeOutput(int paramNumber, int paramValue, long pointer)
    {
        checkMap(pointer);
        long localRelativeBase = relativeBase + intCode.get(pointer);

        switch (paramValue)
        {
            case 0:
                return intCode.get(intCode.get(pointer + paramNumber));

            case 1:
                return intCode.get(pointer + paramNumber);

            case 2:
                return intCode.get(localRelativeBase);

            default:
                throw new IndexOutOfBoundsException();
        }
    }

    private void checkMap(long pointer)
    {
         intCode.putIfAbsent(intCode.get(intCode.get(pointer)), 0L);
         intCode.putIfAbsent(intCode.get(pointer + 1), 0L);
         intCode.putIfAbsent(relativeBase + intCode.get(pointer), 0L);
    }

    public long getValue(long index)
    {
        if (index >= intCode.size())
        {
            throw new IndexOutOfBoundsException();
        }

        return intCode.get(index);
    }
}
