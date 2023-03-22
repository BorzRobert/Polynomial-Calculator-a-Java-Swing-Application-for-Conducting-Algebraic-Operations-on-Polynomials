package view_controller;

import model.Monomial;
import model.Operations;
import model.Polynomial;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class View extends JDialog {

    private JTextField firstPolynomialTextField;
    private JTextField secondPolynomialTextField;
    private JButton addButton;
    private JButton multiplyButton;
    private JButton deriveButton;
    private JButton subtractButton;
    private JButton divideButton;
    private JButton integrateButton;
    private JButton a1Button;
    private JButton a4Button;
    private JButton a7Button;
    private JButton a2Button;
    private JButton a5Button;
    private JButton a8Button;
    private JButton a3Button;
    private JButton a6Button;
    private JButton a9Button;
    private JButton a0Button;
    private JButton divButton;
    private JButton pointButton;
    private JButton plusButton;
    private JButton mulButton;
    private JButton xVarButton;
    private JButton minusButton;
    private JButton powerButton;
    private JButton delButton;
    private JTextField resultTextField;
    private JPanel calculatorPanel;
    private JButton howToUseButton;
    private boolean firstSelected;
    private boolean secondSelected;

    private final Operations operation = new Operations();
    private Polynomial<Integer, Monomial> firtstPolynomial;
    private Polynomial<Integer, Monomial> secondtPolynomial;
    private Polynomial<Integer, Monomial> result;

    public View(JFrame parent) {
        super(parent);
        setTitle("Polynomial Calculator");
        setContentPane(calculatorPanel);
        setMinimumSize(new Dimension(500, 400));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        //CHECK WHETHER firstPolynomialTextField or secondPolynomialTextField IS SELECTED
        firstPolynomialTextField.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                firstSelected = true;
                secondSelected = false;
            }

            @Override
            public void focusLost(FocusEvent e) {
            }
        });
        secondPolynomialTextField.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                secondSelected = true;
                firstSelected = false;
            }

            @Override
            public void focusLost(FocusEvent e) {
            }
        });

        //OPERATIONS BUTTONS
        addButton.addActionListener(new ActionListener() {//ADD
            @Override
            public void actionPerformed(ActionEvent e) {

                if (checkBothInputs(firstPolynomialTextField, secondPolynomialTextField)) {
                    if (operation.patternMatch(firstPolynomialTextField.getText()) && operation.patternMatch(secondPolynomialTextField.getText())) {
                        initPolynomials();
                        result = operation.addPolynomials(firtstPolynomial, secondtPolynomial);
                        resultTextField.setText(operation.polynomialToString(result));
                    } else {
                        showMessage("The polynomials don't respect the required format!");
                        refresh();
                    }
                } else {
                    showMessage("In order to perform addition both polynomials must be inserted!");
                }
            }
        });
        subtractButton.addActionListener(new ActionListener() {//SUBTRACT
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkBothInputs(firstPolynomialTextField, secondPolynomialTextField)) {
                    if (operation.patternMatch(firstPolynomialTextField.getText()) && operation.patternMatch(secondPolynomialTextField.getText())) {
                        initPolynomials();
                        result = operation.subtractPolynomials(firtstPolynomial, secondtPolynomial);
                        resultTextField.setText(operation.polynomialToString(result));
                    } else {
                        showMessage("The polynomials don't respect the required format!");
                        refresh();
                    }
                } else {
                    showMessage("In order to perform subtraction both polynomials must be inserted!");
                }
            }
        });
        multiplyButton.addActionListener(new ActionListener() {//MULTIPLY
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkBothInputs(firstPolynomialTextField, secondPolynomialTextField)) {
                    if (operation.patternMatch(firstPolynomialTextField.getText()) && operation.patternMatch(secondPolynomialTextField.getText())) {
                        initPolynomials();
                        result = operation.multiplyPolynomials(firtstPolynomial, secondtPolynomial);
                        resultTextField.setText(operation.polynomialToString(result));
                    } else {
                        showMessage("The polynomials don't respect the required format!");
                        refresh();
                    }
                } else {
                    showMessage("In order to perform multiplication both polynomials must be inserted!");
                }

            }
        });
        divideButton.addActionListener(new ActionListener() {//DIVIDE
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkBothInputs(firstPolynomialTextField, secondPolynomialTextField)) {
                    if (operation.patternMatch(firstPolynomialTextField.getText()) && operation.patternMatch(secondPolynomialTextField.getText())) {
                        initPolynomials();
                        if (firtstPolynomial.polynomialMap.firstEntry().getValue().getPower() < secondtPolynomial.polynomialMap.firstEntry().getValue().getPower()) {
                            showMessage("The polynomial with the highest degree must be inserted first!");
                            refresh();
                        }
                        resultTextField.setText(operation.dividePolynomials(firtstPolynomial, secondtPolynomial));
                    } else {
                        showMessage("The polynomials don't respect the required format!");
                        refresh();
                    }
                } else {
                    showMessage("In order to perform division both polynomials must be inserted!");
                }
            }
        });
        deriveButton.addActionListener(new ActionListener() {//DERIVE
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkFirstInput(firstPolynomialTextField)) {
                    if (operation.patternMatch(firstPolynomialTextField.getText())) {
                        firtstPolynomial = operation.stringToPolynomial(firstPolynomialTextField.getText(), firtstPolynomial);
                        operation.derivePolynomial(firtstPolynomial);
                        resultTextField.setText(operation.polynomialToString(firtstPolynomial));
                    } else {
                        showMessage("The first polynomial doesn't respect the required format!");
                        refresh();
                    }
                } else {
                    showMessage("In order to derive, the first polynomial must be inserted");
                }
            }
        });
        integrateButton.addActionListener(new ActionListener() {//INTEGRATE
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkFirstInput(firstPolynomialTextField)) {
                    if (operation.patternMatch(firstPolynomialTextField.getText())) {
                        firtstPolynomial = operation.stringToPolynomial(firstPolynomialTextField.getText(), firtstPolynomial);
                        operation.integratePolynomial(firtstPolynomial);
                        resultTextField.setText(operation.polynomialToString(firtstPolynomial));
                    } else {
                        showMessage("The first polynomial doesn't respect the required format!");
                        refresh();
                    }
                } else {
                    showMessage("In order to integrate, the first polynomial must be inserted");
                }
            }
        });
        howToUseButton.addActionListener(new ActionListener() {//INTEGRATE
            @Override
            public void actionPerformed(ActionEvent e) {
                showMessage("In order for the calculator to work properly the next restrictions must be met:\n" +
                        "1.The polynomials must contain unique terms for certain powers\n" +
                        "2.The syntax for a monomial should be the following(+/-(coefficient)x^power)\n" +
                        "   The first term of the polynomial doesn't need to have the sign + if it is positive\n"+
                        "    For power=0, the monomial will consist only of the real value(+/-coefficient)\n" +
                        "3.If the first term of the polynomial is negative, its coefficient must be present, even if it is 1\n");
            }
        });
        //CALCULATOR KEYBOARD BUTTONS
        a1Button.addActionListener(new ActionListener() {//1
            @Override
            public void actionPerformed(ActionEvent e) {
                writeFromAppKeyboard(a1Button.getText());
            }
        });
        a2Button.addActionListener(new ActionListener() {//2
            @Override
            public void actionPerformed(ActionEvent e) {
                writeFromAppKeyboard(a2Button.getText());
            }
        });
        a3Button.addActionListener(new ActionListener() {//3
            @Override
            public void actionPerformed(ActionEvent e) {
                writeFromAppKeyboard(a3Button.getText());
            }
        });
        a4Button.addActionListener(new ActionListener() {//4
            @Override
            public void actionPerformed(ActionEvent e) {
                writeFromAppKeyboard(a4Button.getText());
            }
        });
        a5Button.addActionListener(new ActionListener() {//5
            @Override
            public void actionPerformed(ActionEvent e) {
                writeFromAppKeyboard(a5Button.getText());
            }
        });
        a6Button.addActionListener(new ActionListener() {//6
            @Override
            public void actionPerformed(ActionEvent e) {
                writeFromAppKeyboard(a6Button.getText());
            }
        });
        a7Button.addActionListener(new ActionListener() {//7
            @Override
            public void actionPerformed(ActionEvent e) {
                writeFromAppKeyboard(a7Button.getText());
            }
        });
        a8Button.addActionListener(new ActionListener() {//8
            @Override
            public void actionPerformed(ActionEvent e) {
                writeFromAppKeyboard(a8Button.getText());
            }
        });
        a9Button.addActionListener(new ActionListener() {//9
            @Override
            public void actionPerformed(ActionEvent e) {
                writeFromAppKeyboard(a9Button.getText());
            }
        });
        a0Button.addActionListener(new ActionListener() {//0
            @Override
            public void actionPerformed(ActionEvent e) {
                writeFromAppKeyboard(a0Button.getText());
            }
        });
        plusButton.addActionListener(new ActionListener() {//+
            @Override
            public void actionPerformed(ActionEvent e) {
                writeFromAppKeyboard(plusButton.getText());
            }
        });
        minusButton.addActionListener(new ActionListener() {//-
            @Override
            public void actionPerformed(ActionEvent e) {
                writeFromAppKeyboard(minusButton.getText());
            }
        });
        divButton.addActionListener(new ActionListener() {//->/
            @Override
            public void actionPerformed(ActionEvent e) {
                writeFromAppKeyboard(divButton.getText());
            }
        });
        mulButton.addActionListener(new ActionListener() {//*
            @Override
            public void actionPerformed(ActionEvent e) {
                writeFromAppKeyboard(mulButton.getText());
            }
        });
        powerButton.addActionListener(new ActionListener() {//^
            @Override
            public void actionPerformed(ActionEvent e) {
                writeFromAppKeyboard(powerButton.getText());
            }
        });
        pointButton.addActionListener(new ActionListener() {//.
            @Override
            public void actionPerformed(ActionEvent e) {
                writeFromAppKeyboard(pointButton.getText());
            }
        });
        xVarButton.addActionListener(new ActionListener() {//x
            @Override
            public void actionPerformed(ActionEvent e) {
                writeFromAppKeyboard(xVarButton.getText());
            }
        });
        delButton.addActionListener(new ActionListener() {//del
            @Override
            public void actionPerformed(ActionEvent e) {
                refresh();
            }
        });
        setVisible(true);
    }

    void refresh() {
        firstPolynomialTextField.setText(null);
        secondPolynomialTextField.setText(null);
        resultTextField.setText(null);
    }

    void writeFromAppKeyboard(String button) {
        if (firstSelected) {
            String value = firstPolynomialTextField.getText() + button;
            firstPolynomialTextField.setText(value);
        } else {
            String value = secondPolynomialTextField.getText() + button;
            secondPolynomialTextField.setText(value);
        }
    }

    //INITIALIZE POLYNOMIALS
    void initPolynomials() {
        firtstPolynomial = operation.stringToPolynomial(firstPolynomialTextField.getText(), firtstPolynomial);
        secondtPolynomial = operation.stringToPolynomial(secondPolynomialTextField.getText(), secondtPolynomial);
    }

    private boolean checkBothInputs(JTextField first, JTextField second) {
        return !first.getText().isEmpty() && !second.getText().isEmpty();
    }

    private boolean checkFirstInput(JTextField first) {
        return !first.getText().isEmpty();
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

}
