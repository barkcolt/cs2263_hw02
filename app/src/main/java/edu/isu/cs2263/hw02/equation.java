package edu.isu.cs2263.hw02;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * Author: Colter Barker
 * Version: 1.0
 */
public class Equation {
    Integer firstNum;
    Integer secondNum;
    String operator;
    int solution;

    /**
     * sets values for the equation object
     * @param expression the user input structured as a linkedlist
     * @return the linkedlist with used values removed
     */
    public LinkedList<String> setNums(LinkedList<String> expression) {
        this.firstNum = Integer.parseInt(expression.remove());
        this.operator = expression.remove();
        this.secondNum = Integer.parseInt(expression.remove());
        return expression;
    }
    public String Solve(LinkedList<String> equation, Equation toSolve) {
        while (!(equation.size() <= 1)) {
            equation = toSolve.setNums(equation);
            String result = Integer.toString(toSolve.evaluate());
            equation.addFirst(result);
        }
        return equation.remove();
    }
    /**
     * enacts one of the operators in the equation
     * @return the solution of the part of the equation done
     */
    public int evaluate() {
        switch (operator) {
            case "*" -> solution = firstNum * secondNum;
            case "/" -> solution = firstNum / secondNum;
            case "+" -> solution = firstNum + secondNum;
            case "-" -> solution = firstNum - secondNum;
        }
        return solution;
    }

}
