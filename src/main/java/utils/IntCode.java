package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class IntCode
{
    private int relativeBase = 0;
    private ArrayList<Integer> intCode;

    public void calculateIntCode(int[] inputIntCode, Supplier<Integer> receiver, Consumer<Integer> sender)
    {
        boolean run = true;

        intCode = Arrays.stream(inputIntCode.clone()).boxed().collect(Collectors.toCollection(ArrayList::new));

        int i = 0;

        while (i < intCode.size() && run)
        {
            Integer value;
            int[] opCode = getOpCode(intCode.get(i));
            int paramOne = opCode[2];
            int paramTwo = opCode[1];
            int paramThree = opCode[0];
            int instructions = opCode[3] * 10 + opCode[4];

            switch (instructions)
            {
                case 1:
                    value = modeOutput(1, paramOne, i)
                            + modeOutput(2, paramTwo, i);
                    intCode.set(intCode.get(i + 3), value);

                    i += 4;

                    break;

                case 2:
                    value = modeOutput(1, paramOne, i)
                            * modeOutput(2, paramTwo, i);
                    intCode.set(intCode.get(i + 3), value);

                    i += 4;

                    break;

                case 3:
                    int input = receiver.get();
                    intCode.set(intCode.get(i + 1), input);

                    i += 2;

                    break;

                case 4:
                    value = modeOutput(1, paramOne, i);
                    sender.accept(value);

                    i += 2;

                    break;

                case 5:
                    paramOne = modeOutput(1, paramOne, i);
                    paramTwo = modeOutput(2, paramTwo, i);

                    i = paramOne != 0 ? paramTwo : i + 3;

                    break;

                case 6:
                    paramOne = modeOutput(1, paramOne, i);
                    paramTwo = modeOutput(2, paramTwo, i);

                    i = paramOne == 0 ? paramTwo : i + 3;

                    break;

                case 7:
                    paramOne = modeOutput(1, paramOne, i);
                    paramTwo = modeOutput(2, paramTwo, i);

                    intCode.set(intCode.get(i + 3), paramOne < paramTwo ? 1 : 0);

                    i += 4;

                    break;

                case 8:
                    paramOne = modeOutput(1, paramOne, i);
                    paramTwo = modeOutput(2, paramTwo, i);

                    intCode.set(intCode.get(i + 3), paramOne == paramTwo ? 1 : 0);

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

        String operation = Integer.toString(opInput);

        while (operation.length() < 5)
        {
            operation = "0" + operation;
        }

        for (int i = 0; i < 5; i++)
        {
            opCode[i] = Integer.parseInt(operation.substring(i, i + 1));
        }

        return opCode;
    }

    private int modeOutput(int paramNumber, int paramValue, int pointer)
    {
        int localRelativeBase = relativeBase + intCode.get(pointer + paramNumber);

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

    public int getValue(int index)
    {
        if (index >= intCode.size())
        {
            throw new IndexOutOfBoundsException();
        }

        return intCode.get(index);
    }
}
