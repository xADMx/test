package com.ifmo.lesson9;

import com.ifmo.lesson9.StringAppendable;

public class Main {
	public static void main(String... arg) {
		StringAppendable sa = new StringAppendable(",");
		sa.append("1").append("2").append("3");
		System.out.print(sa.value());
	}
}
