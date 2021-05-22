package github.io.ylongo.dsaa.chapter03;

import org.apache.zookeeper.Op;

import java.util.*;

/**
 * 后缀表达式计算
 */
public class PostfixCalc {


	public static void main(String[] args) {
		
		String[] inputOri = {"4.99", "*", "1.06", "+", "5.99", "+", "6.99", "*", "1.06"};

		String[] input = convert2Postfix(inputOri);

//		String[] input = {"4.99", "1.06", "*", "5.99", "+", "6.99", "1.06", "*", "+"};
		System.out.println(Arrays.toString(input));
		Stack<String> inputStack = new Stack<>();
		for (String s : input) {

			calc(inputStack, s);
		}

		System.out.println(inputStack.pop());
	}

	private static String[] convert2Postfix(String[] inputOri) {
		Stack<String> opStack = new Stack<>();
		String[] input = new String[inputOri.length];
		int j = 0;
		for (String s : inputOri) {

			if ("*".equals(s)) { // * = 1
				opStack.push(s);
			} else if ("+".equals(s)) { // + = 0
				while (!opStack.isEmpty()) {
					input[j++] = opStack.pop();
				}
				opStack.push(s);
			} else {
				input[j++] = s;
			}
		}

		while (!opStack.isEmpty()) {
			input[j++] = opStack.pop();
		}
		
		return input;
	}

	private static void calc(Stack<String> inputStack, String s) {
		if ("*".equals(s)) {
			double first = Double.parseDouble(inputStack.pop());
			double second = Double.parseDouble(inputStack.pop());
			inputStack.push(String.valueOf(first * second));
		} else if ("+".equals(s)) {
			double first = Double.parseDouble(inputStack.pop());
			double second = Double.parseDouble(inputStack.pop());
			inputStack.push(String.valueOf(first + second));
		} else {
			inputStack.push(s);
		}
	}
}
