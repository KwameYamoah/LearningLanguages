package Algorithms.Symbols;

import Algorithms.Symbols.Parenthesis;

import static org.junit.Assert.*;

public class ParenthesisTest {
    private Parenthesis parenthesisChecker;
    @org.junit.Before
    public void setUp() throws Exception {
        parenthesisChecker = new Parenthesis();
    }

    @org.junit.Test
    public void isValid() {
        String exp1 = "()(){(())}";
        String exp2 = "";
        String exp3 = "([{}])()";
        String exp4 = "(){}{]";
        String exp5 = "{]";
        String exp6 = "[{()}[{}]";
        String exp7 = "(1+2)*5";

        assertTrue("Three grouped brace endings failed", parenthesisChecker.isValid(exp1));
        assertTrue("Empty String failed",parenthesisChecker.isValid(exp2));
        assertTrue("Two grouped brace endings failed",parenthesisChecker.isValid(exp3));
        assertFalse("Incorrect brace check failed",parenthesisChecker.isValid(exp4));
        assertFalse("Incorrect brace check failed",parenthesisChecker.isValid(exp5));
        assertFalse("Incorrect length check failed",parenthesisChecker.isValid(exp6));
        assertTrue("Braces with noise(values) failed", parenthesisChecker.isValid(exp7));

    }
}