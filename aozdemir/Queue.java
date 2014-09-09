
package aozdemir;

import java.util.LinkedList;

import aozdemir.exceptions.OperandNotRecognized;
import aozdemir.exceptions.TooFewOperands;

public class Queue extends LinkedList<Double>{
	private static final long serialVersionUID = -3735516454660342283L;

	protected void operate(char op) throws TooFewOperands, OperandNotRecognized {
		switch(op) {
		case 'c':
			this.clear();
			return;
		case '+':
			this.checkOperands(2);
			this.addLast(this.pollFirst() + this.pollFirst());
			return;
		case '-':
			this.checkOperands(2);
			this.addLast(this.pollFirst() - this.pollFirst());
			return;
		case '*':
			this.checkOperands(2);
			this.addLast(this.pollFirst() * this.pollFirst());
			return;
		case '/':
			this.checkOperands(2);
			this.addLast(this.pollFirst() / this.pollFirst());
			return;
		case '^':
			this.checkOperands(2);
			this.addLast(Math.pow(this.pollFirst(), this.pollFirst()));
			return;
		case 'n':
			this.checkOperands(1);
			this.addLast(-this.pollFirst());
			return;
		default:
			throw new OperandNotRecognized();
		}
	}
	
	private void checkOperands(int n) throws TooFewOperands {
		if (this.size() < n) {
			throw new TooFewOperands();
		}
	}
	
	public String toString() {
		String result = "<< ";
		if (this.isEmpty()) {
			result += "none ";
		} else {
			for (Double num : this) {
				result += num.toString() + " ";
			}
		}
		result += "<<";
		return result;
	}
}
