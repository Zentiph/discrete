package tests;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;

import org.junit.jupiter.api.Test;

import sets.BellNumbers;

public class TestBellNumbers {
    @Test
    public void testBellNumber() {
        // Bell number values sourced from
        // https://oeis.org/A000110
        int expected;
        BigInteger got;
        int bell = 0;

        // B_0
        expected = 1;
        got = BellNumbers.bellNumber(bell);
        assertEquals(expected, got.intValue());
        bell++;

        // B_1
        expected = 1;
        got = BellNumbers.bellNumber(bell);
        assertEquals(expected, got.intValue());
        bell++;

        // B_2
        expected = 2;
        got = BellNumbers.bellNumber(bell);
        assertEquals(expected, got.intValue());
        bell++;

        // B_3
        expected = 5;
        got = BellNumbers.bellNumber(bell);
        assertEquals(expected, got.intValue());
        bell++;

        // B_4
        expected = 15;
        got = BellNumbers.bellNumber(bell);
        assertEquals(expected, got.intValue());
        bell++;

        // B_5
        expected = 52;
        got = BellNumbers.bellNumber(bell);
        assertEquals(expected, got.intValue());
        bell++;

        // B_6
        expected = 203;
        got = BellNumbers.bellNumber(bell);
        assertEquals(expected, got.intValue());
        bell++;

        // B_7
        expected = 877;
        got = BellNumbers.bellNumber(bell);
        assertEquals(expected, got.intValue());
        bell++;

        // B_8
        expected = 4140;
        got = BellNumbers.bellNumber(bell);
        assertEquals(expected, got.intValue());
        bell++;

        // B_9
        expected = 21147;
        got = BellNumbers.bellNumber(bell);
        assertEquals(expected, got.intValue());
        bell++;

        // B_10
        expected = 115975;
        got = BellNumbers.bellNumber(bell);
        assertEquals(expected, got.intValue());
        bell++;

        // B_11
        expected = 678570;
        got = BellNumbers.bellNumber(bell);
        assertEquals(expected, got.intValue());
        bell++;

        // B_12
        expected = 4213597;
        got = BellNumbers.bellNumber(bell);
        assertEquals(expected, got.intValue());
        bell++;

        // B_13
        expected = 27644437;
        got = BellNumbers.bellNumber(bell);
        assertEquals(expected, got.intValue());
        bell++;

        // B_14
        expected = 190899322;
        got = BellNumbers.bellNumber(bell);
        assertEquals(expected, got.intValue());
        bell++;

        // B_15
        expected = 1382958545;
        got = BellNumbers.bellNumber(bell);
        assertEquals(expected, got.intValue());

        // B_26
        BigInteger exp = new BigInteger("49631246523618756274");
        got = BellNumbers.bellNumber(26);
        assertEquals(exp, got);
    }

    @Test
    public void testBellNumberRecursive() {
        // Bell number values sourced from
        // https://oeis.org/A000110
        int expected;
        BigInteger got;
        int bell = 0;

        // B_0
        expected = 1;
        got = BellNumbers.bellNumberRecursive(bell);
        assertEquals(expected, got.intValue());
        bell++;

        // B_1
        expected = 1;
        got = BellNumbers.bellNumberRecursive(bell);
        assertEquals(expected, got.intValue());
        bell++;

        // B_2
        expected = 2;
        got = BellNumbers.bellNumberRecursive(bell);
        assertEquals(expected, got.intValue());
        bell++;

        // B_3
        expected = 5;
        got = BellNumbers.bellNumberRecursive(bell);
        assertEquals(expected, got.intValue());
        bell++;

        // B_4
        expected = 15;
        got = BellNumbers.bellNumberRecursive(bell);
        assertEquals(expected, got.intValue());
        bell++;

        // B_5
        expected = 52;
        got = BellNumbers.bellNumberRecursive(bell);
        assertEquals(expected, got.intValue());
        bell++;

        // B_6
        expected = 203;
        got = BellNumbers.bellNumberRecursive(bell);
        assertEquals(expected, got.intValue());
        bell++;

        // B_7
        expected = 877;
        got = BellNumbers.bellNumberRecursive(bell);
        assertEquals(expected, got.intValue());
        bell++;

        // B_8
        expected = 4140;
        got = BellNumbers.bellNumberRecursive(bell);
        assertEquals(expected, got.intValue());
        bell++;

        // B_9
        expected = 21147;
        got = BellNumbers.bellNumberRecursive(bell);
        assertEquals(expected, got.intValue());
        bell++;

        // B_10
        expected = 115975;
        got = BellNumbers.bellNumberRecursive(bell);
        assertEquals(expected, got.intValue());
        bell++;

        // B_11
        expected = 678570;
        got = BellNumbers.bellNumberRecursive(bell);
        assertEquals(expected, got.intValue());
        bell++;

        // B_12
        expected = 4213597;
        got = BellNumbers.bellNumberRecursive(bell);
        assertEquals(expected, got.intValue());
        bell++;

        // B_13
        expected = 27644437;
        got = BellNumbers.bellNumberRecursive(bell);
        assertEquals(expected, got.intValue());
        bell++;

        // B_14
        expected = 190899322;
        got = BellNumbers.bellNumberRecursive(bell);
        assertEquals(expected, got.intValue());
        bell++;

        // B_15
        expected = 1382958545;
        got = BellNumbers.bellNumberRecursive(bell);
        assertEquals(expected, got.intValue());

        // B_26
        BigInteger exp = new BigInteger("49631246523618756274");
        got = BellNumbers.bellNumberRecursive(26);
        assertEquals(exp, got);
    }
}
