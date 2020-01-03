
/* STUDENT NAME :Mert Gurkan  
 * STUDENT ID :260716883

 */

import java.util.Stack;
import java.util.ArrayList;

public class Expression {
	private ArrayList<String> tokenList;

	// Constructor
	/**
	 * The constructor takes in an expression as a string and tokenizes it
	 * (breaks it up into meaningful units) These tokens are then stored in an
	 * array list 'tokenList'.
	 */

	Expression(String expressionString) throws IllegalArgumentException {
		tokenList = new ArrayList<String>();
		StringBuilder token = new StringBuilder();

		// ADD YOUR CODE BELOW HERE
		// ..

		for (int i = 0; i < expressionString.length(); i++)
		
		{

			if (expressionString.charAt(i) >= '0' && expressionString.charAt(i) <= '9')

			{
				int m = i + 1;

		while (expressionString.charAt(m) >= '0' && expressionString.charAt(m) <= '9')
					m++;

		token = token.append(expressionString.substring(i, m));
				
				tokenList.add(token.toString()); 
				
				token.delete(0, token.length()); 

				i = m - 1;

			} else if ((expressionString.charAt(i) == '[') || (expressionString.charAt(i) == ']')
					|| (expressionString.charAt(i) == '(') || (expressionString.charAt(i) == ')')) {

				if (expressionString.charAt(i) == '(') {
					tokenList.add("(");	
				}
				else if (expressionString.charAt(i) == ')') {
					tokenList.add(")");
				} 
				else if (expressionString.charAt(i) == '[') {
					tokenList.add("[");
				}
				else if (expressionString.charAt(i) == ']') {
					tokenList.add("]");
				}
				else 
				{
					continue;
				}
				
				} 
			else if ((expressionString.charAt(i) == '-') && (expressionString.charAt(i + 1) == '-')) {
				tokenList.add("--");
				i++;
			} 
			else if ((expressionString.charAt(i) == '+') && (expressionString.charAt(i + 1) == '+')) {
				tokenList.add("++");
				i++;

			} 
			else if ((expressionString.charAt(i) == '-') && (expressionString.charAt(i + 1) != '-')) {
				tokenList.add("-");
				
			} 
			else if ((expressionString.charAt(i) == '+') && (expressionString.charAt(i + 1) != '+')) {
				tokenList.add("+");

			} 
			else if ((expressionString.charAt(i) == '*')) {
				tokenList.add("*");

			} 
			else if ((expressionString.charAt(i) == '/')) {
				tokenList.add("/");

			} 
			else
			{
				continue;
			}
		}

		// ..
		// ADD YOUR CODE ABOVE HERE
	}

	/**
	 * This method evaluates the expression and returns the value of the
	 * expression Evaluation is done using 2 stack ADTs, operatorStack to store
	 * operators and valueStack to store values and intermediate results. - You
	 * must fill in code to evaluate an expression using 2 stacks
	 */
	public Integer eval() {
		Stack<String> operatorStack = new Stack<String>();
		Stack<Integer> valueStack = new Stack<Integer>();

		// ADD YOUR CODE BELOW HERE

		for (int repeat = 0; repeat < tokenList.size(); repeat++) 
		{

			while (!(tokenList.get(repeat).equals(")"))) {
				if (isInteger(tokenList.get(repeat))) {
					valueStack.push(Integer.parseInt(tokenList.get(repeat)));
					repeat++;
					if (!(repeat < tokenList.size())) {
						break;
					}
				} else if (tokenList.get(repeat).equals("(") || tokenList.get(repeat).equals("[")) {
					repeat++;
					if (!(repeat < tokenList.size())) {
						break;
					}
				} 
				else 
				{
					operatorStack.push(tokenList.get(repeat));
					repeat++;
					if (!(repeat < tokenList.size())) {
						break;
					}
				}

			}

			if (!(operatorStack.empty())) {

				if (operatorStack.peek().equals("++")) {
					int a = valueStack.pop();
					a++;
					valueStack.push(a);
					operatorStack.pop();

				}
				else if (operatorStack.peek().equals("--")) {
					int b = valueStack.pop();
					b--;
					valueStack.push(b);
					operatorStack.pop();
				} 
				else if (operatorStack.peek().equals("]")) {
					int c = valueStack.pop();

					if (c < 0) {
						int z = c * (-1);
						valueStack.push(z);
					} else {
						valueStack.push(c);
					}

					operatorStack.pop();
				}

				else if (operatorStack.peek().equals("+")) {
					int d = valueStack.pop();
					int e = valueStack.pop();
					int addition = d + e;
					valueStack.push(addition);
					operatorStack.pop();

				} else if (operatorStack.peek().equals("-")) {
					int f = valueStack.pop();
					int g = valueStack.pop();
					int substitution = g - f;
					valueStack.push(substitution);
					operatorStack.pop();
				}

				else if (operatorStack.peek().equals("*")) {
					int h = valueStack.pop();
					int j = valueStack.pop();
					int multiply = h * j;
					valueStack.push(multiply);
					operatorStack.pop();
				}
				else if (operatorStack.peek().equals("/")) {
					int k = valueStack.pop();
					int l = valueStack.pop();
					int division = l / k;
					valueStack.push(division);
					operatorStack.pop();
				}

			} else {
				continue;
			}

		}

		int result=0;
		result = valueStack.pop();
		return result;
		
		// ADD YOUR CODE ABOVE HERE

	}

	// Helper methods
	/**
	 * Helper method to test if a string is an integer Returns true for strings
	 * of integers like "456" and false for string of non-integers like "+" - DO
	 * NOT EDIT THIS METHOD
	 */
	private boolean isInteger(String element) {
		try {
			Integer.valueOf(element);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	/**
	 * Method to help print out the expression stored as a list in tokenList. -
	 * DO NOT EDIT THIS METHOD
	 */

	@Override
	public String toString() {
		String s = new String();
		for (String t : tokenList)
			s = s + "~" + t;
		return s;
	}

}