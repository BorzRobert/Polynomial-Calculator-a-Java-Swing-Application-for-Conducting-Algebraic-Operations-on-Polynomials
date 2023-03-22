package model;

import java.util.TreeMap;

public class Polynomial<T, U> {
    public TreeMap<T, U> polynomialMap;

    public Polynomial(TreeMap<T, U> polynomialMap) {
        this.polynomialMap = polynomialMap;
    }

    public TreeMap<T, U> getPolynomialMap() {
        return polynomialMap;
    }

    public void setPolynomialMap(TreeMap<T, U> polynomialMap) {
        this.polynomialMap = polynomialMap;
    }

}
