package utils;

import java.util.HashMap;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class IntCode
{
    private boolean debug = false;
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

            if (debug)
            {
                System.out.println();
                System.out.println("Pointer is: " + i);
                System.out.print("Opcode is: ");
                for (int j = 0; j < opCode.length; j++) { System.out.print(opCode[j] + " "); }
                System.out.println();
                System.out.println("Instruction is: " + instructions);
            }

            switch (instructions)
            {
                case 1:
                    value = modeRead(1, paramOne, i)
                          + modeRead(2, paramTwo, i);
                    intCode.put(modeWrite(3, paramThree, i), value);

                    if (debug) { System.out.println("Case 1 value: " + value + " saved to: " + modeWrite(3, paramThree, i)); }

                    i += 4;

                    break;

                case 2:
                    value = modeRead(1, paramOne, i)
                          * modeRead(2, paramTwo, i);
                    intCode.put(modeWrite(3, paramThree, i), value);

                    if (debug) { System.out.println("Case 2 value: " + value + " saved to: " + modeWrite(3, paramThree, i)); }

                    i += 4;

                    break;

                case 3:
                    long input = receiver.get();
                    intCode.put(modeWrite(1, paramOne, i), input);

                    if (debug) { System.out.println("Case 3 value: " + input + " saved to: " + modeWrite(1, paramOne, i)); }

                    i += 2;

                    break;

                case 4:
                    value = modeRead(1, paramOne, i);
                    sender.accept(value);

                    if (debug) { System.out.println("Case 4 value: " + value + " output"); }

                    i += 2;

                    break;

                case 5:
                    valueOne = modeRead(1, paramOne, i);
                    valueTwo = modeRead(2, paramTwo, i);

                    if (debug) { System.out.println("Case 5 valueOne: " + valueOne + " valueTwo: " + valueTwo); }

                    i = valueOne != 0 ? valueTwo : i + 3;

                    if (debug) { System.out.println("Pointer set to: " + i); }

                    break;

                case 6:
                    valueOne = modeRead(1, paramOne, i);
                    valueTwo = modeRead(2, paramTwo, i);

                    if (debug) { System.out.println("Case 6 valueOne: " + valueOne + " valueTwo: " + valueTwo); }

                    i = valueOne == 0 ? valueTwo : i + 3;

                    if (debug) { System.out.println("Pointer set to: " + i); }

                    break;

                case 7:
                    valueOne = modeRead(1, paramOne, i);
                    valueTwo = modeRead(2, paramTwo, i);

                    intCode.put(modeWrite(3, paramThree, i), (valueOne < valueTwo ? 1L : 0));

                    if (debug) { System.out.println(); }

                    if (debug) { System.out.println("Case 7 valueOne: " + valueOne + " valueTwo: " + valueTwo
                        + " output value: " + (valueOne < valueTwo ? 1L : 0) + " saved to: " + modeWrite(3, paramThree, i)); }

                    i += 4;

                    break;

                case 8:
                    valueOne = modeRead(1, paramOne, i);
                    valueTwo = modeRead(2, paramTwo, i);

                    intCode.put(modeWrite(3, paramThree, i), (valueOne == valueTwo ? 1L : 0));

                    if (debug) { System.out.println("Case 8 valueOne: " + valueOne + " valueTwo: " + valueTwo + "\rValue "
                            + (valueOne == valueTwo ? 1L : 0) + " saved to: " + modeWrite(3, paramThree, i)); }

                    i += 4;

                    break;

                case 9:
                    if (debug) { System.out.println("Starting relative base: " + relativeBase + " increase: " + modeRead(1, paramOne, i)); }

                    relativeBase += modeRead(1, paramOne, i);

                    if (debug) { System.out.println("Relative base increased to: " + relativeBase); }

                    i += 2;

                    break;

                case 99:
                    run = false;

                    if (debug) { System.out.println("Program halt instruction sent"); }

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

    private long modeRead(int paramNumber, int paramValue, long pointer)
    {
        checkMap(pointer);
        if (debug) { System.out.print("Instruction mode is: "); }

        switch (paramValue)
        {
            case 0:
                if (debug) System.out.println("parameter mode value at " + intCode.get(pointer + paramNumber) + " is " + intCode.get(intCode.get(pointer + paramNumber)));

                return intCode.get(intCode.get(pointer + paramNumber));

            case 1:
                if (debug) System.out.println("immediate mode value at " + (pointer + paramNumber) + " is " + intCode.get(pointer + paramNumber));

                return intCode.get(pointer + paramNumber);

            case 2:
                if (debug) System.out.println("relative mode");

                if (debug) System.out.print("Value at " + (relativeBase + intCode.get(pointer + paramNumber)) + " is ");
                if (debug) { System.out.println(intCode.get(relativeBase + intCode.get(pointer + paramNumber))); }

                return intCode.get(relativeBase + intCode.get(pointer + paramNumber));

            default:
                if (debug) { System.out.println("Exception thrown for parameter value"); }

                throw new IndexOutOfBoundsException();
        }
    }

    private long modeWrite(int paramNumber, int paramValue, long pointer)
    {
        checkMap(pointer);
        long localRelativeBase = relativeBase + intCode.get(pointer + paramNumber);
        if (debug) { System.out.print("Output mode is: "); }

        switch (paramValue)
        {
            case 0:
                if (debug) System.out.println("parameter mode value at " + (pointer + paramNumber) + " is " + intCode.get(pointer + paramNumber));
                return intCode.get(pointer + paramNumber);

            case 2:
                if (debug) System.out.println("relative mode");
                if (debug) { System.out.println("Local relative base is: " + localRelativeBase); }
                return localRelativeBase;

            default:
                if (debug) { System.out.println("Exception thrown for parameter value"); }
                throw new IndexOutOfBoundsException();
        }
    }

    private void checkMap(long pointer)
    {
         intCode.putIfAbsent(intCode.get(intCode.get(pointer)), 0L);
         intCode.putIfAbsent(intCode.get(pointer + 1), 0L);
         intCode.putIfAbsent(relativeBase + intCode.get(pointer + 1), 0L);
    }

    public long getValue(long index)
    {
        if (index >= intCode.size())
        {
            throw new IndexOutOfBoundsException();
        }

        return intCode.get(index);
    }

    public void setDebug(boolean debug)
    {
        this.debug = true;
        System.out.println();
    }
}
