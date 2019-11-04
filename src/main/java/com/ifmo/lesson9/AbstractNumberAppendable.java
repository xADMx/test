package com.ifmo.lesson9;

public abstract class AbstractNumberAppendable<T> implements Appendable<T> {

	private ArithmeticOperation op;

	public AbstractNumberAppendable(ArithmeticOperation op) {
		this.op = op;
	}
}
