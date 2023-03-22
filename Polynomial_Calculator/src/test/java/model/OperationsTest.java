package model;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class OperationsTest {

    @Test
    void testAddition() {
        Operations op = new Operations();
        TreeMap<Integer, Monomial> myMap1 = new TreeMap<Integer, Monomial>(Collections.reverseOrder());
        TreeMap<Integer, Monomial> myMap2 = new TreeMap<Integer, Monomial>(Collections.reverseOrder());
        Polynomial<Integer, Monomial> firstPolynomial, secondPolynomial;
        firstPolynomial = new Polynomial<>(myMap1);
        secondPolynomial = new Polynomial<>(myMap2);
        firstPolynomial = op.stringToPolynomial("5.12x^3+2x^2-36", firstPolynomial);
        secondPolynomial = op.stringToPolynomial("1.73x^2+2x^1+5", secondPolynomial);
        assertEquals("5.12x^3+3.73x^2+2.0x-31.0", op.polynomialToString(op.addPolynomials(firstPolynomial, secondPolynomial)));
    }

    @Test
    void testSubtraction() {
        Operations op = new Operations();
        TreeMap<Integer, Monomial> myMap1 = new TreeMap<Integer, Monomial>(Collections.reverseOrder());
        TreeMap<Integer, Monomial> myMap2 = new TreeMap<Integer, Monomial>(Collections.reverseOrder());
        Polynomial<Integer, Monomial> firstPolynomial, secondPolynomial;
        firstPolynomial = new Polynomial<>(myMap1);
        secondPolynomial = new Polynomial<>(myMap2);
        firstPolynomial = op.stringToPolynomial("5.12x^3+2x^2-36", firstPolynomial);
        secondPolynomial = op.stringToPolynomial("1.73x^2+2x^1+5", secondPolynomial);
        assertEquals("5.12x^3+0.27x^2-2.0x-41.0", op.polynomialToString(op.subtractPolynomials(firstPolynomial, secondPolynomial)));
    }

    @Test
    void testMultiplication() {
        Operations op = new Operations();
        TreeMap<Integer, Monomial> myMap1 = new TreeMap<Integer, Monomial>(Collections.reverseOrder());
        TreeMap<Integer, Monomial> myMap2 = new TreeMap<Integer, Monomial>(Collections.reverseOrder());
        Polynomial<Integer, Monomial> firstPolynomial, secondPolynomial;
        firstPolynomial = new Polynomial<>(myMap1);
        secondPolynomial = new Polynomial<>(myMap2);
        firstPolynomial = op.stringToPolynomial("5.12x^3+2x^2-36", firstPolynomial);
        secondPolynomial = op.stringToPolynomial("1.73x^2+2x^1+5", secondPolynomial);
        assertEquals("8.8576x^5+13.7x^4+29.6x^3-52.28x^2-72.0x-180.0", op.polynomialToString(op.multiplyPolynomials(firstPolynomial, secondPolynomial)));
    }

    @Test
    void testMultiplication1() {
        Operations op = new Operations();
        TreeMap<Integer, Monomial> myMap1 = new TreeMap<Integer, Monomial>(Collections.reverseOrder());
        TreeMap<Integer, Monomial> myMap2 = new TreeMap<Integer, Monomial>(Collections.reverseOrder());
        Polynomial<Integer, Monomial> firstPolynomial, secondPolynomial;
        firstPolynomial = new Polynomial<>(myMap1);
        secondPolynomial = new Polynomial<>(myMap2);
        firstPolynomial = op.stringToPolynomial("x^2+2x^1+1", firstPolynomial);
        secondPolynomial = op.stringToPolynomial("5x^3+1.72x^1+77", secondPolynomial);
        assertEquals("5.0x^5+10.0x^4+6.72x^3+80.44x^2+155.72x+77.0", op.polynomialToString(op.multiplyPolynomials(firstPolynomial, secondPolynomial)));
    }

    @Test
    void testDerive() {
        Operations op = new Operations();
        TreeMap<Integer, Monomial> myMap1 = new TreeMap<Integer, Monomial>(Collections.reverseOrder());
        Polynomial<Integer, Monomial> firstPolynomial;
        firstPolynomial = new Polynomial<>(myMap1);
        firstPolynomial = op.stringToPolynomial("5.12x^3+2x^2-36", firstPolynomial);
        op.derivePolynomial(firstPolynomial);
        assertEquals("15.36x^2+4.0x", op.polynomialToString(firstPolynomial));
    }

    @Test
    void testIntegrate() {
        Operations op = new Operations();
        TreeMap<Integer, Monomial> myMap1 = new TreeMap<Integer, Monomial>(Collections.reverseOrder());
        Polynomial<Integer, Monomial> firstPolynomial;
        firstPolynomial = new Polynomial<>(myMap1);
        firstPolynomial = op.stringToPolynomial("5.12x^3+2x^2-36", firstPolynomial);
        op.integratePolynomial(firstPolynomial);
        assertEquals("1.28x^4+0.667x^3-36.0x", op.polynomialToString(firstPolynomial));
    }

    @Test
    void testDivision() {
        Operations op = new Operations();
        TreeMap<Integer, Monomial> myMap1 = new TreeMap<Integer, Monomial>(Collections.reverseOrder());
        TreeMap<Integer, Monomial> myMap2 = new TreeMap<Integer, Monomial>(Collections.reverseOrder());
        Polynomial<Integer, Monomial> firstPolynomial, secondPolynomial;
        firstPolynomial = new Polynomial<>(myMap1);
        secondPolynomial = new Polynomial<>(myMap2);
        firstPolynomial = op.stringToPolynomial("x^3-2x^2+6x^1-5", firstPolynomial);
        secondPolynomial = op.stringToPolynomial("x^2-1", secondPolynomial);
        assertEquals("Q: 1.0x-2.0 ; R: 7.0x-7.0", op.dividePolynomials(firstPolynomial, secondPolynomial));
    }

    @Test
    void testDivision1() {
        Operations op = new Operations();
        TreeMap<Integer, Monomial> myMap1 = new TreeMap<Integer, Monomial>(Collections.reverseOrder());
        TreeMap<Integer, Monomial> myMap2 = new TreeMap<Integer, Monomial>(Collections.reverseOrder());
        Polynomial<Integer, Monomial> firstPolynomial, secondPolynomial;
        firstPolynomial = new Polynomial<>(myMap1);
        secondPolynomial = new Polynomial<>(myMap2);
        firstPolynomial = op.stringToPolynomial("21x^5+29x^4-10x^3+42x^1-12", firstPolynomial);
        secondPolynomial = op.stringToPolynomial("7x^1-2", secondPolynomial);
        assertEquals("Q: 3.0x^4+5.0x^3+6.0 ; R: ", op.dividePolynomials(firstPolynomial, secondPolynomial));
    }
}