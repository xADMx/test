package com.ifmo.lesson9;

public abstract class AbstractNumberAppendable<T extends Number> implements Appendable<T> {

	protected ArithmeticOperation<T> op;
	protected LinkedList<T> list = new LinkedList<T>();

	public AbstractNumberAppendable(ArithmeticOperation op) {
		this.op = op;
	}

}
