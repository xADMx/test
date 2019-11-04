package com.ifmo.lesson9;

public interface Appendable<T> {
	
	Appendable<T> append(T type);
	T value();

}
