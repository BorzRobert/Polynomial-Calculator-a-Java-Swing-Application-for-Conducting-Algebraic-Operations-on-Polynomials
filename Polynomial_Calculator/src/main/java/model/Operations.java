package model;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Operations {

    public boolean patternMatch(String input) {
        String regex = "^[-+]?\\d*\\.?\\d*x\\^\\d+([-+]?\\d*\\.?\\d*x\\^\\d+)*([-+]?\\d*\\.?\\d+|[-+]?[0-9]+)?$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    public Polynomial<Integer, Monomial> stringToPolynomial(String textBox, Polynomial<Integer, Monomial> polynomial) {
        boolean startsWithMinus = false, xFlag = false;
        TreeMap<Integer, Monomial> myMap = new TreeMap<Integer, Monomial>(Collections.reverseOrder());
        polynomial = new Polynomial<>(myMap);
        String[] stringPolynomial;
        String[] stringMonomial;
        if (textBox.charAt(0) == '-') {
            startsWithMinus = true;
            textBox = textBox.substring(1, textBox.length());
        }
        textBox = textBox.replace("-", "+-");
        stringPolynomial = textBox.split("\\+");
        for (String element : stringPolynomial
        ) {
            if (element.charAt(0) == 'x')
                xFlag = true;
            Monomial myMonomial = new Monomial(0, 0);
            stringMonomial = element.split("x\\^");
            if (element == stringPolynomial[0] && startsWithMinus)
                stringMonomial[0] = "-" + stringMonomial[0];
            if (xFlag) {
                myMonomial.setCoefficient(1);
                myMonomial.setPower(Integer.parseInt(stringMonomial[1]));
                polynomial.polynomialMap.put(Integer.parseInt(stringMonomial[1]), myMonomial);
            } else {
                if (stringMonomial.length > 1) {
                    myMonomial.setCoefficient(Double.parseDouble(stringMonomial[0]));
                    myMonomial.setPower(Integer.parseInt(stringMonomial[1]));
                    polynomial.polynomialMap.put(Integer.parseInt(stringMonomial[1]), myMonomial);
                } else {
                    myMonomial.setCoefficient(Double.parseDouble(stringMonomial[0]));
                    myMonomial.setPower(0);
                    polynomial.polynomialMap.put(0, myMonomial);
                }
            }
            xFlag = false;
        }
        return polynomial;
    }

    public String polynomialToString(Polynomial<Integer, Monomial> polynomial) {
        String polynomialString = "";
        for (Map.Entry<Integer, Monomial> element : polynomial.polynomialMap.entrySet()
        ) {
            if (element.getValue().getCoefficient() != 0) {
                if (element.getValue().getPower() != highestDegree(polynomial).power) {
                    if (element.getValue().getCoefficient() >= 0) {
                        if (element.getValue().getPower() == 0)
                            polynomialString = polynomialString + "+" + element.getValue().getCoefficient();
                        else if (element.getValue().getPower() == 1) {
                            polynomialString = polynomialString + "+" + element.getValue().getCoefficient() + "x";
                        } else
                            polynomialString = polynomialString + "+" + element.getValue().getCoefficient() + "x^" + element.getValue().getPower();
                    } else {
                        if (element.getValue().getPower() == 0)
                            polynomialString = polynomialString + element.getValue().getCoefficient();
                        else if (element.getValue().getPower() == 1) {
                            polynomialString = polynomialString + element.getValue().getCoefficient() + "x";
                        } else
                            polynomialString = polynomialString + element.getValue().getCoefficient() + "x^" + element.getValue().getPower();
                    }
                } else {
                    if (element.getValue().getPower() == 0)
                        polynomialString = polynomialString + element.getValue().getCoefficient();
                    else if (element.getValue().getPower() == 1) {
                        polynomialString = polynomialString + element.getValue().getCoefficient() + "x";
                    } else
                        polynomialString = polynomialString + element.getValue().getCoefficient() + "x^" + element.getValue().getPower();
                }
            }
        }
        return polynomialString;
    }

    public Polynomial<Integer, Monomial> addPolynomials(Polynomial<Integer, Monomial> polynomial1, Polynomial<Integer, Monomial> polynomial2) {
        double coeff1 = 0, coeff2 = 0;
        boolean first, second, both;
        TreeMap<Integer, Monomial> myMap = new TreeMap<Integer, Monomial>(Collections.reverseOrder());
        Polynomial<Integer, Monomial> result = new Polynomial<>(myMap);
        int max = Math.max(polynomial1.polynomialMap.firstEntry().getValue().getPower(), polynomial2.polynomialMap.firstEntry().getValue().getPower());
        for (int i = max; i >= 0; i--) {
            first = second = both = false;
            for (Map.Entry<Integer, Monomial> element : polynomial1.polynomialMap.entrySet()) {
                if (element.getValue().getPower() == i) {
                    first = true;
                    coeff1 = element.getValue().getCoefficient();
                }
            }
            for (Map.Entry<Integer, Monomial> element : polynomial2.polynomialMap.entrySet()
            ) {
                if (element.getValue().getPower() == i) {
                    second = true;
                    coeff2 = element.getValue().getCoefficient();
                }
            }
            if (first && second) {
                Monomial myMonomial = new Monomial(0, 0);
                myMonomial.setCoefficient(coeff1 + coeff2);
                myMonomial.setPower(i);
                result.polynomialMap.put(i, myMonomial);
            } else {
                if (first) {
                    Monomial myMonomial = new Monomial(0, 0);
                    myMonomial.setCoefficient(coeff1);
                    myMonomial.setPower(i);
                    result.polynomialMap.put(i, myMonomial);
                }
                if (second) {
                    Monomial myMonomial = new Monomial(0, 0);
                    myMonomial.setCoefficient(coeff2);
                    myMonomial.setPower(i);
                    result.polynomialMap.put(i, myMonomial);
                }
            }
        }
        return result;
    }

    public Polynomial<Integer, Monomial> subtractPolynomials(Polynomial<Integer, Monomial> polynomial1, Polynomial<Integer, Monomial> polynomial2) {
        for (Map.Entry<Integer, Monomial> element : polynomial2.polynomialMap.entrySet()) {
            element.getValue().setCoefficient(-element.getValue().getCoefficient());
        }
        return addPolynomials(polynomial1, polynomial2);
    }

    public void derivePolynomial(Polynomial<Integer, Monomial> polynomial) {

        for (Map.Entry<Integer, Monomial> element : polynomial.polynomialMap.entrySet()) {
            if (element.getValue().getPower() != 0) {
                element.getValue().setCoefficient(element.getValue().getCoefficient() * element.getValue().getPower());
                element.getValue().setPower(element.getValue().getPower() - 1);
            } else
                element.getValue().setCoefficient(0);
        }
    }

    public void integratePolynomial(Polynomial<Integer, Monomial> polynomial) {
        double coeff1;
        DecimalFormat numberFormat = new DecimalFormat("#.000");
        for (Map.Entry<Integer, Monomial> element : polynomial.polynomialMap.entrySet()) {
            coeff1 = element.getValue().getCoefficient() * 1 / (element.getValue().getPower() + 1);
            element.getValue().setCoefficient(Double.parseDouble(numberFormat.format(coeff1)));
            element.getValue().setPower(element.getValue().getPower() + 1);
        }
    }

    public Polynomial<Integer, Monomial> multiplyPolynomials(Polynomial<Integer, Monomial> polynomial1, Polynomial<Integer, Monomial> polynomial2) {
        TreeMap<Integer, Monomial> myMap = new TreeMap<Integer, Monomial>(Collections.reverseOrder());
        TreeMap<Integer, Monomial> myMap1 = new TreeMap<Integer, Monomial>(Collections.reverseOrder());
        Polynomial<Integer, Monomial> aux = new Polynomial<>(myMap1);
        Polynomial<Integer, Monomial> result = new Polynomial<>(myMap);
        Monomial myMonomial = new Monomial(0, 0);
        result.polynomialMap.put(0, myMonomial);
        for (Map.Entry<Integer, Monomial> element : polynomial1.polynomialMap.entrySet()
        ) {
            aux.polynomialMap.clear();
            for (Map.Entry<Integer, Monomial> element1 : polynomial2.polynomialMap.entrySet()
            ) {
                Monomial myMonomial1 = new Monomial(0, 0);
                myMonomial1.setPower(element.getValue().getPower() + element1.getValue().getPower());
                myMonomial1.setCoefficient(element.getValue().getCoefficient() * element1.getValue().getCoefficient());
                aux.polynomialMap.put(element.getValue().getPower() + element1.getValue().getPower(), myMonomial1);
            }
            result = addPolynomials(result, aux);
        }
        return result;
    }

    public String dividePolynomials(Polynomial<Integer, Monomial> polynomial1, Polynomial<Integer, Monomial> polynomial2) {
        DecimalFormat numberFormat = new DecimalFormat("#.000");
        double coefficient;
        TreeMap<Integer, Monomial> myMap = new TreeMap<Integer, Monomial>(Collections.reverseOrder());
        Polynomial<Integer, Monomial> quotient = new Polynomial<>(myMap);
        TreeMap<Integer, Monomial> myMap1 = new TreeMap<Integer, Monomial>(Collections.reverseOrder());
        Polynomial<Integer, Monomial> result = new Polynomial<>(myMap1);

        while (highestDegree(polynomial1).power >= polynomial2.polynomialMap.firstEntry().getValue().getPower()) {
            Monomial myMonomial = new Monomial(0, 0);
            myMonomial.setPower(highestDegree(polynomial1).power - polynomial2.polynomialMap.firstEntry().getValue().getPower());
            myMonomial.setCoefficient(highestDegree(polynomial1).coefficient / polynomial2.polynomialMap.firstEntry().getValue().getCoefficient());
            quotient.polynomialMap.put(highestDegree(polynomial1).power - polynomial2.polynomialMap.firstEntry().getValue().getPower(), myMonomial);
            result.polynomialMap.clear();
            result.polynomialMap.put(highestDegree(polynomial1).power - polynomial2.polynomialMap.firstEntry().getValue().getPower(), myMonomial);
            polynomial1 = subtractPolynomials(polynomial1, multiplyPolynomials(polynomial2, result));
        }
        return "Q: " + polynomialToString(quotient) + " ;" + " R: " + polynomialToString(polynomial1);
    }

    private TwoInts highestDegree(Polynomial<Integer, Monomial> polynomial1) {
        TwoInts record = new TwoInts(0, 0);

        for (Map.Entry<Integer, Monomial> element : polynomial1.polynomialMap.entrySet()
        ) {
            if (element.getValue().getCoefficient() != 0) {
                record.coefficient = element.getValue().getCoefficient();
                record.power = element.getValue().getPower();
                return record;
            }
        }
        return new TwoInts(-1, -1);
    }
}
