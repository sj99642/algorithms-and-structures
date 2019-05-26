package java_algorithms.numbers;

import org.junit.Test;

import static org.junit.Assert.*;

public class EuclidsAlgorithmTest
{
    public EuclidsAlgorithm algorithm = new EuclidsAlgorithm();

    @Test
    public void testMultiples()
    {
        assertEquals(5, algorithm.gcd(20, 5));
        assertEquals(5, algorithm.gcd(5, 20));
        assertEquals(7, algorithm.gcd(105, 7));
        assertEquals(7, algorithm.gcd(7, 105));
    }

    @Test
    public void testCoprime()
    {
        assertEquals(1, algorithm.gcd(17, 19));
        assertEquals(1, algorithm.gcd(105, 8));
        assertEquals(1, algorithm.gcd(5, 3));
    }

    @Test
    public void testGeneralExamples()
    {
        assertEquals(7, algorithm.gcd(28, 63));
        assertEquals(6, algorithm.gcd(54, 24));
        assertEquals(14, algorithm.gcd(42, 56));
    }
}
