package com.company;

import static java.lang.Math.sqrt;

public class Main {

    public static void main(String[] args) {
        /**
         * Point is: we have upper and lower limit presented by multiplications: 100*100 = 10000 and 999*999 = 998001.
         * From this point we understand that the biggest palindrome we are able to get is 997799. Using palindrome's
         * features we build palindromes by mirroring 3-digit numbers starting with 997. Important is that we produce
         * search on decreasing values so the first factorised palindrome we get is the biggest one.
         *
         * At this step we make factorization of palindrome presented by "bruteForce" method: we count root to have
         * the starting point and then search for compatible integral multipliers. Because we can't get integral root
         * for every palindrome we get, so we use two numbers as upper and lower rounding. We provide search based
         * on this numbers.
         *
         * As long as we need only 3-digit multipliers we also have bounds for our search -- [100,999]. Also we have
         * root number as algebraic average. This way we achieve improvement of brute force by more than two times.
         */
        for (int i = 997; i > 100; i--) {
            //As mentioned below, if bruteForce returns not 0 so integral multiplier is found.
            if (bruteForce(makePalindrome(i)) != 0)
                return;
        }
    }

    /**
     * This is the first step of algorithm -- build the palindrome.
     *
     * @param number is value which will be mirrored to palindrome.
     *               Should be emphasised that this function oriented only on given task,
     *               so it works only for 3-digit numbers.
     * @return gives palindrome.
     */
    public static int makePalindrome(int number) {
        int hundreds = number % 10;
        int decs = number % 100 - hundreds;
        int ones = number - number % 100;
        return number * 1000 + ones / 100 + decs + hundreds * 100;
    }

    /**
     * This is the second step -- restricted factorisation.
     *
     * @param number is a palindrome.
     * @return 0 if palindrome doesn't have integral multipliers
     * or one of the multipliers if such is found.
     */
    public static int bruteForce(final int number) {
        final int root = (int) sqrt(number);

        /**
         * @param LIMIT_1 provided for algebraic average count based on lower limit of root.
         */
        final int LIMIT_1 = root * 2;
        /**
         * @param LIMIT_2 provided for algebraic average count based on upper limit of root.
         */
        final int LIMIT_2 = (root + 1) * 2;

        //Check whether number has an integral root.
        if (root * root == number) {
            System.out.println("Answer: " + root + "^2 = " + number);
            return root;
        }

        //This case is hard to fit in loop, so it stands alone.
        if (root * (root + 1) == number) {
            System.out.println("Answer: " + root + "*" + (root + 1) + " = " + number);
            return root;
        }

        //Check for lower limit based average.
        for (int i = root - 1, j = root + 1; i + j == LIMIT_1 && i >= 100 && j < 1000; --i, ++j) {
            if (i * j == number) {
                System.out.println("Answer: " + i + "*" + j + " = " + number);
                return i;
            }
        }
        //Check for upper limit based average.
        for (int i = root, j = root + 2; i + j == LIMIT_2 && i >= 100 && j < 1000; --i, ++j) {
            if (i * j == number) {
                System.out.println("Answer: " + i + "*" + j + " = " + number);
                return i;
            }
        }
        return 0;
    }

}
