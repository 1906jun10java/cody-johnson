package com.revature;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Mutations {
	private String startSeq;
	private String endSeq;
	private List<String> seqBank;
	private String dataFilePath;
	
	// Constructor
	Mutations(String dataFilePath) {
		this.setDataFilePath(dataFilePath);
	}
	
	// Load bank from file
	public void loadDataFromFile() {
		try {
			Scanner s = new Scanner(new File(this.dataFilePath));
			this.seqBank = new ArrayList<String>();
			int i = 0;
			while (s.hasNext()) {
				if (i == 0) {
					this.startSeq = s.next();
				} else if (i == 1) {
					this.endSeq = s.next();
				} else {
					this.seqBank.add(s.next());
				}
				i++;
			}
			s.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	// Calculate minimum mutations
	public int minMutations() {
		// If end sequence is not in bank, return -1
		if (!this.seqBank.contains(this.endSeq)) {
			return -1;
		}
		
		// If start and end sequences are equal, return 0
		if (this.startSeq.equals(this.endSeq)) {
			return 0;
		}
		
		// Get number of genes changed
		int n = 0;
		for (int i=0; i<this.startSeq.length(); i++) {
			if (this.startSeq.charAt(i) != this.endSeq.charAt(i)) {
				n++;
			}
		}
		
		// Need to go through each path to determine validity
		
		return n;
	}

	// Get permutations
	public ArrayList<String> permutations(String start, String end) {
		ArrayList<String> al = new ArrayList<>();
		for (int i=0; i<start.length(); i++) {
			if (start.charAt(i) != end.charAt(i)) {
				String s = start.substring(0,i)
						+ end.charAt(i) + start.substring(i+1);
				al.add(s);
			}
		}
		return al;
	}
	
	// Check if string is in bank
	public boolean inBank(String s) {
		return this.seqBank.contains(s);
	}

	public String getDataFilePath() {
		return dataFilePath;
	}

	public void setDataFilePath(String dataFilePath) {
		this.dataFilePath = dataFilePath;
	}
}
