package application.mvc.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Random;
import java.util.Vector;

public class Parties implements Serializable {

	private String name;
	private String opinion;
	private int partyVoterCounter = 0;
	public Vector<Candidate> candidateList;
	Calendar cal;

	public Parties(String name, String opinion) {
		this.name = name;
		this.opinion = opinion;
		cal = Calendar.getInstance();
		candidateList = new Vector<Candidate>();
	}

	public void addCandidateToParty(Candidate newCandidate) {
		candidateList.add(newCandidate);
		System.out.print("The candidate " + newCandidate.getName() + " successfully added!\n");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPartyVoterCounter() {
		partyVoterCounter = 1 + partyVoterCounter;
		System.out.println("party name: " + name + " party voters number: " + partyVoterCounter);
	}

	public int getPartyVoterCounter() {
		return partyVoterCounter;
	}

	public String getOpinion() {
		return opinion;
	}

	public Vector<Candidate> getCandidateList() {
		return candidateList;
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Parties))
			return false;
		Parties p = (Parties) other;
		return p.name.equals(name);
	}

	@Override
	public String toString() {
		StringBuffer temp = new StringBuffer();
		temp.append("\nParty: name: " + name + ", opinion: " + opinion + ", number of voters :" + partyVoterCounter
				+ ",\nprimaries: ");
		for (int i = 0; i < candidateList.size(); i++) {
			temp.append("\n" + (i + 1) + ") " + candidateList.elementAt(i));
		}
		return temp.toString();
	}

}
