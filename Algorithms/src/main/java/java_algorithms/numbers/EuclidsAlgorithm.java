package java_algorithms.numbers;

public class EuclidsAlgorithm
{
    public static void main(String[] args)
    {
        // Takes two numbers from the command line and prints out their GCD
        if (args.length != 2) {
            System.err.println("EuclidsAlgorithm needs 2 arguments");
            return;
        }

        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);

        System.out.println((new EuclidsAlgorithm()).gcd(a, b));
    }

    /**
     * Finds the GCD of two integers
     */
    public int gcd(int a, int b)
    {
        // We want a>=b
        if (a < b) {
            return gcd(b, a);
        }

        // Return base case, where a is a multiple of b
        if (a % b == 0) {
            return b;
        }

        // Iterate the algorithm, using the fact that gcd(a, b) = gcd(b, r) where r = a % b
        return gcd(b, a % b);
    }
}