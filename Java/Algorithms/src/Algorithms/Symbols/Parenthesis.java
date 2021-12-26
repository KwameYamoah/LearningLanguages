package Algorithms.Symbols;

import java.util.ArrayList;
import java.util.Collections;

public class Parenthesis {
    private final ArrayList<Character> opened_bracket = new ArrayList<>();
    private final ArrayList<Character> closed_bracket = new ArrayList<>();

    Parenthesis(){
        opened_bracket.add('{');
        opened_bracket.add('[');
        opened_bracket.add('(');

        closed_bracket.add('}');
        closed_bracket.add(']');
        closed_bracket.add(')');
    }

    /***
     * Method to check if a syntactically correct expression is valid(opening braces matches closing braces)
     * @param expression is the expression supplied
     * @return true if valid,else false
     */
     boolean isValid(String expression) {
        if (expression.equals("")) return true;             //if empty

        if (closed_bracket.contains(expression.charAt(0))) return false; //if it starts with closing brace

        ArrayList<Character> openBracesStack = new ArrayList<>();     //holds consecutive opening braces
        ArrayList<Character> closedBracesStack = new ArrayList<>();    //holds consecutive closing braces

        int index = 0;    // points to current character
        while (index < expression.length()) {
            //stores consecutive opening braces into openBracesStack
            while (index < expression.length() && !closed_bracket.contains(expression.charAt(index))) {
                if(opened_bracket.contains(expression.charAt(index))) openBracesStack.add(expression.charAt(index));
                index++;
            }
            Collections.reverse(openBracesStack);

            //stores consecutive closing braces into closedBracesStack
            while (index < expression.length() && !opened_bracket.contains(expression.charAt(index))) {
                if(closed_bracket.contains(expression.charAt(index))) closedBracesStack.add(expression.charAt(index));
                index++;
            }

            //if stack sizes are uneven -> invalid expression
            if (openBracesStack.size() != closedBracesStack.size()) return false;
                // else check if corresponding braces are equal
            else {
                while (openBracesStack.size() != 0) {
                    char pop_stack_1 = openBracesStack.get(0);     //end of opening braces list to start from inner parenthesis
                    openBracesStack.remove(0);
                    char pop_stack_2 = closedBracesStack.get(0);                   //first of closing braces to start from inner parenthesis
                    closedBracesStack.remove(0);
                    if (!oppositeOf(pop_stack_1, pop_stack_2))
                        return false;      //if braces aren't opposite - > invalid expression
                }
            }
        }

        return true;
    }

    private static boolean oppositeOf(char pop_stack_1, char pop_stack_2) { //checks if pop_stack_2 is the closing brace of pop_stack_1
        switch (pop_stack_1) {
            case '{':
                if (pop_stack_2 == '}') return true;
                break;
            case '[':
                if (pop_stack_2 == ']') return true;
                break;
            case '(':
                if (pop_stack_2 == ')') return true;
                break;
        }
        return false;
    }

}


