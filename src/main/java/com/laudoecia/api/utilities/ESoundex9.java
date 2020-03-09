package com.laudoecia.api.utilities;

public class ESoundex9 extends Soundex {

	public ESoundex9() {
		super(true, Integer.MAX_VALUE, 0, MAP_9);
	}

	public static void main(String[] args) {
		ESoundex9 inst = new ESoundex9();
		for (String arg : args)
			System.out.println(inst.toFuzzy(arg));
	}
}
