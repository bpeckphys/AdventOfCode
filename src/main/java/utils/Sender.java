package utils;

@FunctionalInterface
public interface Sender
{
    void send(int out);
}

// Consumer<Integer>