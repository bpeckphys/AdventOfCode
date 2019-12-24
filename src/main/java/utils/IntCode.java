package utils;

import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class IntCode
{
    static Scanner scanner = new Scanner(System.in);

    public static void calculateIntCode(int[] inputIntCode, Supplier<Integer> receiver, Consumer<Integer> sender)
    {
        boolean run = true;
        int i = 0;

        while (i < inputIntCode.length && run)
        {
            Integer value;
            int paramOne;
            int paramTwo;
            int[] opCode = getOpCode(inputIntCode[i]);
            int instructions = opCode[4];

            switch (instructions)
            {
                case 1:
                    value = (opCode[3] == 0 ? inputIntCode[inputIntCode[i + 1]] : inputIntCode[i + 1])
                            + (opCode[2] == 0 ? inputIntCode[inputIntCode[i + 2]] : inputIntCode[i + 2]);
                    inputIntCode[inputIntCode[i + 3]] = value;

                    i += 4;

                    break;

                case 2:
                    value = (opCode[3] == 0 ? inputIntCode[inputIntCode[i + 1]] : inputIntCode[i + 1])
                            * (opCode[2] == 0 ? inputIntCode[inputIntCode[i + 2]] : inputIntCode[i + 2]);
                    inputIntCode[inputIntCode[i + 3]] = value;

                    i += 4;

                    break;

                case 3:
                    int input = receiver.get();
                    inputIntCode[inputIntCode[i + 1]] = input;

                    i += 2;

                    break;

                case 4:
                    value = opCode[3] == 0 ? inputIntCode[inputIntCode[i + 1]] : inputIntCode[i + 1];
                    sender.accept(value);

                    i += 2;

                    break;

                case 5:
                    paramOne = opCode[3] == 0 ? inputIntCode[inputIntCode[i + 1]] : inputIntCode[i + 1];
                    paramTwo = opCode[2] == 0 ? inputIntCode[inputIntCode[i + 2]] : inputIntCode[i + 2];

                    i = paramOne != 0 ? paramTwo : i + 3;

                    break;

                case 6:
                    paramOne = opCode[3] == 0 ? inputIntCode[inputIntCode[i + 1]] : inputIntCode[i + 1];
                    paramTwo = opCode[2] == 0 ? inputIntCode[inputIntCode[i + 2]] : inputIntCode[i + 2];

                    i = paramOne == 0 ? paramTwo : i + 3;

                    break;

                case 7:
                    paramOne = opCode[3] == 0 ? inputIntCode[inputIntCode[i + 1]] : inputIntCode[i + 1];
                    paramTwo = opCode[2] == 0 ? inputIntCode[inputIntCode[i + 2]] : inputIntCode[i + 2];

                    inputIntCode[inputIntCode[i + 3]] = paramOne < paramTwo ? 1 : 0;

                    i += 4;

                    break;

                case 8:
                    paramOne = opCode[3] == 0 ? inputIntCode[inputIntCode[i + 1]] : inputIntCode[i + 1];
                    paramTwo = opCode[2] == 0 ? inputIntCode[inputIntCode[i + 2]] : inputIntCode[i + 2];

                    inputIntCode[inputIntCode[i + 3]] = paramOne == paramTwo ? 1 : 0;

                    i += 4;

                    break;

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
