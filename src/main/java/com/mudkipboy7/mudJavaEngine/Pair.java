package com.mudkipboy7.mudJavaEngine;

public class Pair<one, two> {
	one key;
	two value;

	public Pair(one one, two two) {
		key = one;
		value = two;
	}

	public two getValue() {
		return value;
	}

	public one getKey() {
		return key;
	}
}
