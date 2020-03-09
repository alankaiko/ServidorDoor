package com.laudoecia.api.utilities;

public class ESoundex extends Soundex {

	public ESoundex() {
		super(true, Integer.MAX_VALUE, 0, MAP_6);
	}

	public static void main(String[] args) {
		ESoundex inst = new ESoundex();
		for (String arg : args)
			System.out.println(inst.toFuzzy(arg));
	}

}
