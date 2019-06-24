package com.revature;

public class Driver {
	public static void main(String[] args) {
		String file = "data.txt";
		
		Mutations m = new Mutations(file);
		m.loadDataFromFile();
		System.out.println(m.minMutations());
	}
}
