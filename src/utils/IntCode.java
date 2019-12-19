package utils;

import java.util.Scanner;

public class IntCode
{
    static Scanner scanner = new Scanner(System.in);

    public static Integer calculateIntCode(Integer[] inputIntCode, Integer input)
    {
        boolean run = true;
        int i = 0;
        Integer[] outPutArray = inputIntCode.clone();

        if (input != null)
        {
            System.out.println("\tInput value: " + input);
        }

        while (i < outPutArray.length && run)
        {
            Integer value;
            int paramOne;
            int paramTwo;
            int[] opCode = getOpCode(outPutArray[i]);
            int instructions = opCode[4];

            switch (instructions)
            {
                case 1:
                    value = (opCode[3] == 0 ? outPutArray[outPutArray[i + 1]] : outPutArray[i + 1])
                            + (opCode[2] == 0 ? outPutArray[outPutArray[i + 2]] : outPutArray[i + 2]);
                    outPutArray[outPutArray[i + 3]] = value;

                    i += 4;

                    break;

                case 2:
                    value = (opCode[3] == 0 ? outPutArray[outPutArray[i + 1]] : outPutArray[i + 1])
                            * (opCode[2] == 0 ? outPutArray[outPutArray[i + 2]] : outPutArray[i + 2]);
                    outPutArray[outPutArray[i + 3]] = value;

                    i += 4;

                    break;

                case 3:
                    outPutArray[outPutArray[i + 1]] = input;

                    i += 2;

                    break;

                case 4:
                    value = opCode[3] == 0 ? outPutArray[outPutArray[i + 1]] : outPutArray[i + 1];
                    System.out.println("\t\tOutput: " + value);

                    i += 2;

                    break;

                case 5:
                    paramOne = opCode[3] == 0 ? outPutArray[outPutArray[i + 1]] : outPutArray[i + 1];
                    paramTwo = opCode[2] == 0 ? outPutArray[outPutArray[i + 2]] : outPutArray[i + 2];

                    i = paramOne != 0 ? paramTwo : i + 3;

                    break;

                case 6:
                    paramOne = opCode[3] == 0 ? outPutArray[outPutArray[i + 1]] : outPutArray[i + 1];
                    paramTwo = opCode[2] == 0 ? outPutArray[outPutArray[i + 2]] : outPutArray[i + 2];

                    i = paramOne == 0 ? paramTwo : i + 3;

                    break;

                case 7:
                    paramOne = opCode[3] == 0 ? outPutArray[outPutArray[i + 1]] : outPutArray[i + 1];
                    paramTwo = opCode[2] == 0 ? outPutArray[outPutArray[i + 2]] : outPutArray[i + 2];

                    outPutArray[outPutArray[i + 3]] = paramOne < paramTwo ? 1 : 0;

                    i += 4;

                    break;

                case 8:
                    paramOne = opCode[3] == 0 ? outPutArray[outPutArray[i + 1]] : outPutArray[i + 1];
                    paramTwo = opCode[2] == 0 ? outPutArray[outPutArray[i + 2]] : outPutArray[i + 2];

                    outPutArray[outPutArray[i + 3]] = paramOne == paramTwo ? 1 : 0;

                    i += 4;

                    break;

                case 99:
                    run = false;

                    break;
            }
        }

        return outPutArray[0];
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
