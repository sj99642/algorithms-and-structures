package java_algorithms.misc;

public class DynamicFibonacci
{
    public static void main(String[] args)
    {
        if (args.length != 1) {
            System.err.println("Must give a single number to calculate Fib(n)");
            return;
        }

        int n = Integer.parseInt(args[0]);
        System.out.println((new DynamicFibonacci()).getNthFibonacci(n));
    }

    public long getNthFibonacci(int n)
    {
        // Base cases
        if (n <= 0) {
            return 0;
        } else if (n == 1 || n == 2) {
            return 1;
        }

        // Count up this and the last, adding them together to make the new
        // one each time.
        long lastFib = 1;
        long fib = 1;

        for (int i = 2; i < n; i++) {
            long temp = fib;
            fib = lastFib + fib;
            lastFib = temp;
        }

        return fib;
    }
}