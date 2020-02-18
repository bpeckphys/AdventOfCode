package utils;

import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class IntCode
{
    static Scanner scanner = new Scanner(System.in);

    public void calculateIntCode(int[] inputIntCode, Supplier<Integer> receiver, Consumer<Integer> sender)
    {
        boolean run = true;
        int i = 0;
        int relativeBase = 0;

        while (i < inputIntCode.length && run)
        {
            Integer value;
            int[] opCode = getOpCode(inputIntCode[i]);
            int paramOne = opCode[3];
            int paramTwo = opCode[2];
            int paramThree = opCode[1];
            int instructions = opCode[4];

            switch (instructions)
            {
                case 1:
                    value = (paramOne == 0 ? inputIntCode[inputIntCode[i + 1]] : inputIntCode[i + 1])
                            + (paramTwo == 0 ? inputIntCode[inputIntCode[i + 2]] : inputIntCode[i + 2]);
                    inputIntCode[inputIntCode[i + 3]] = value;

                    i += 4;

                    break;

                case 2:
                    value = (paramOne == 0 ? inputIntCode[inputIntCode[i + 1]] : inputIntCode[i + 1])
                            * (paramTwo == 0 ? inputIntCode[inputIntCode[i + 2]] : inputIntCode[i + 2]);
                    inputIntCode[inputIntCode[i + 3]] = value;

                    i += 4;

                    break;

                case 3:
                    int input = receiver.get();
                    inputIntCode[inputIntCode[i + 1]] = input;

                    i += 2;

                    break;

                case 4:
                    value = paramOne == 0 ? inputIntCode[inputIntCode[i + 1]] : inputIntCode[i + 1];
                    sender.accept(value);

                    i += 2;

                    break;

                case 5:
                    paramOne = paramOne == 0 ? inputIntCode[inputIntCode[i + 1]] : inputIntCode[i + 1];
                    paramTwo = paramTwo == 0 ? inputIntCode[inputIntCode[i + 2]] : inputIntCode[i + 2];

                    i = paramOne != 0 ? paramTwo : i + 3;

                    break;

                case 6:
                    paramOne = paramOne == 0 ? inputIntCode[inputIntCode[i + 1]] : inputIntCode[i + 1];
                    paramTwo = paramTwo == 0 ? inputIntCode[inputIntCode[i + 2]] : inputIntCode[i + 2];

                    i = paramOne == 0 ? paramTwo : i + 3;

                    break;

                case 7:
                    paramOne = paramOne == 0 ? inputIntCode[inputIntCode[i + 1]] : inputIntCode[i + 1];
                    paramTwo = paramTwo == 0 ? inputIntCode[inputIntCode[i + 2]] : inputIntCode[i + 2];

                    inputIntCode[inputIntCode[i + 3]] = paramOne < paramTwo ? 1 : 0;

                    i += 4;

                    break;

                case 8:
                    paramOne = paramOne == 0 ? inputIntCode[inputIntCode[i + 1]] : inputIntCode[i + 1];
                    paramTwo = paramTwo == 0 ? inputIntCode[inputIntCode[i + 2]] : inputIntCode[i + 2];

                    inputIntCode[inputIntCode[i + 3]] = paramOne == paramTwo ? 1 : 0;

                    i += 4;

                    break;

                case 9:
                    relativeBase += inputIntCode[i + 1];

                case 99:
                    run = false;

                    break;
            }
        }
    }

    public static int[] getOpCode(int opInput)
    {
        int[] opCode = new int[5];

        String operation = Integer.toString(opInput);

        while (operation.length() < 6)
        {
            operation = "0" + operation;
        }

        opCode[4] = Integer.parseInt(operation.substring(4));

        for (int i = 0; i < 4; i++)
        {
            opCode[i] = Integer.parseInt(operation.substring(i, i + 1));
        }

        return opCode;
    }
}
