/*
 * @author: AlexOzdemir
 * with MicahPedrick
 * 
 * A Queue centric calculator, in which you put operands (numbers) into the queue,
 *  and enter operators which (a) take their inputs off of the front of the queue,
 *  and (b) put their output on the back of the queue.
 *  
 * This is analagous to entering a syntax tree of operations in a breadth first manner
 * 	(right to left).
 */
package aozdemir;

import java.util.Scanner;

import aozdemir.exceptions.OperandNotRecognized;
import aozdemir.exceptions.TooFewOperands;

public class Calculator {	
	public static String NOT_RECOOGNIZED = " is not a recognized number or operation";
	public static String TOO_FEW = "There are not enough operands for the operator: ";
	private Queue queue;
	
	public Calculator() {
		this.queue = new Queue();
	}
	
	public static void main(String[] args) {
		Calculator calc = new Calculator();
		Scanner s = new Scanner(System.in);
		while (true) {
			System.out.println("Qu - " + calc.toString());
			String in = s.next();
			if (in.equals("q")) break;
			else calc.processInput(in);
		}
		s.close();
	}

	private void processInput(String in) {
		try {
			tryNumberInput(in);
		} catch (NumberFormatException e) {
			tryOperatorInput(in);
		}		
	}

	private void tryOperatorInput(String in) {
		if (in.length() != 1) {
			System.err.println(in + NOT_RECOOGNIZED);
			return;
		}
		char op = in.charAt(0);
		try {
			queue.operate(op);
		} catch (OperandNotRecognized ee) {
			System.err.println(in + NOT_RECOOGNIZED);
		} catch (TooFewOperands ee) {
			System.err.println(TOO_FEW + in);					
		}
	}

	private void tryNumberInput(String in) {
		Double d = Double.parseDouble(in);
		queue.add(d);
	}
	
	public String toString() {
		return this.queue.toString();
	}
}
