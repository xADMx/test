package com.ifmo.lesson9;

public class StringAppendable implements Appendable<String> {
	List<String> value = new LinkedList<>();
	
	private final String separator;
	
	public StringAppendable(String separator) {
		super();
		this.separator = separator;
	}

	@Override
	public Appendable<String> append(String type) {
		// TODO Auto-generated method stub
		value.add(type);
		return this;
	}

	@Override
	public String value() {
		// TODO Auto-generated method stub
		String res = "";
		
		for (int i = 0; i < value.size() - 1; i++) {
			res += value.get(i) + separator; 
		}
		
		return res + value.get(value.size() - 1);
	}
	
}
