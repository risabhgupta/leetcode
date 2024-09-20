package com.comppractice.august.fractionadditionsub;

public class Solution {

    public String fractionAddition(String expression) {
        if (expression.charAt(0) != '-') {
            expression = "+" + expression;
        }

        Fraction result = new Fraction(0, 1);
        int i = 0;

        while (i < expression.length()) {
            int j = i + 1;
            while (j < expression.length() && expression.charAt(j) != '+' && expression.charAt(j) != '-') {
                j++;
            }
            result = result.add(Fraction.fromString(expression.substring(i, j)));
            i = j;
        }

        return result.toString();
    }

    public static void main(String[] args) {
        Solution fa = new Solution();
        System.out.println(fa.fractionAddition("-1/2+1/2+1/3"));
    }
}

class Fraction {
    int numerator;
    int denominator;

    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
        simplify();
    }

    public static Fraction fromString(String fraction) {
        String[] parts = fraction.split("/");
        return new Fraction(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
    }

    public Fraction add(Fraction other) {
        int newNumerator = this.numerator * other.denominator + this.denominator * other.numerator;
        int newDenominator = this.denominator * other.denominator;
        return new Fraction(newNumerator, newDenominator);
    }

    private void simplify() {
        int gcd = gcd(Math.abs(numerator), denominator);
        numerator /= gcd;
        denominator /= gcd;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }
}
