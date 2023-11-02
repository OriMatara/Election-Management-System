package application.mvc.model;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Vector;

public class BallotBox<T extends Citizen> implements Serializable {

	protected static int size = 1000;

	protected static Vector<Parties> partiesList = new Vector<Parties>();
	protected static int partyCounter;
	protected static int otomaticSerial = 1000;
	protected String streetAdress;
	protected int serialNumber;
	protected int actuallyVoted = 0;
	
	public int[] partyVotesCounter;
	public Vector<T> votersList = new Vector<T>();
	
	transient Scanner s = new Scanner(System.in);

	public BallotBox(String streetAdress) {
		this.streetAdress = streetAdress;
		serialNumber = otomaticSerial++;
		
		partyVotesCounter = new int[size];
		
	}
	
	public void isVotedHere(T c) {

		System.out.println("you are in ballot box number: " + serialNumber);
		if(c instanceof CoronaSoldier) {
			CoronaSoldier cs = (CoronaSoldier) c;
			cs.setBallotBox(serialNumber);
			votersList.add((T) cs);
		}
		else if(c instanceof Soldier) {
			Soldier s = (Soldier) c;
			s.setBallotBox(serialNumber);
			votersList.add((T) s);
		}
		else if(c instanceof CoronaCitizen) {
			CoronaCitizen cc = (CoronaCitizen) c;
			cc.setBallotBox(serialNumber);
			votersList.add((T) cc);
		}
		else if(c instanceof Citizen) {
			Citizen ci = (Citizen) c;
			ci.setBallotBox(serialNumber);
			votersList.add((T) ci);
		}
	}

	public int getPercentageOfVoters() {
		if (votersList.size() == 0) {
			return (actuallyVoted * 100) / 1;
		} else {
			return (actuallyVoted * 100) / votersList.size();
		}
	}
	
	public boolean hasProtection() {
		int protection = 0;
		boolean isValidInput = false;
		while (!isValidInput) {
			try {
			System.out.println("do you have suit protection? (for true press 1/ for false press 2) ");
			protection = s.nextInt();
			if (protection != 1 && protection != 2) {
				System.out.println("invalid answer please try again");
				isValidInput = false;
				continue;
			}
			isValidInput = true;
			}
			catch(InputMismatchException e) {
				System.out.println("invalid input, you must follow the instructions, try again\n");
				s.nextLine();
			}
		}
		if (protection == 1) {
			System.out.println("you can vote beacause you have corona protection");
			return true;
		} else {
			System.out.println("sorry, you can't vote beacause you don't have corona protection");
			return false;
		}
	}

	
	
	public Vector<T> getVotersList() {
		return votersList;
	}

	public void setVotersList(Vector<T> votersList) {
		this.votersList = votersList;
	}

	public int[] getPartyVotesCounter() {
		return partyVotesCounter;
	}

	public void setPartyVotesCounter(int[] partyVotesCounter) {
		this.partyVotesCounter = partyVotesCounter;
	}
	
	public static Vector<Parties> getPartiesList() {
		return partiesList;
	}

	public static void setPartiesList(Parties newParty) {
		partiesList.add(newParty);
	}

	public int getPartyCounter() {
		return partyCounter;
	}

	public static void setPartyCounter() {
		partyCounter++;
	}

	public int getActuallyVoted() {
		return actuallyVoted;
	}

	public void setActuallyVoted() {
		actuallyVoted++;
	}

	public String getStreetAdress() {
		return streetAdress;
	}

	public void setStreetAdress(String streetAdress) {
		this.streetAdress = streetAdress;
	}

	public int getSerialNumber() {
		return serialNumber;
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof BallotBox))
			return false;
		BallotBox b = (BallotBox) other;
		return b.streetAdress.equals(streetAdress);
	}

	public String toString() {
		StringBuffer temp = new StringBuffer();
		temp.append("street adress: " + streetAdress + ", id: " + serialNumber + "\n"
				+ "voters list:");
		for (int i = 0; i < votersList.size(); i++) {
			temp.append("\nname: " + votersList.elementAt(i).getName());
		}
		temp.append("\npercentage of voters that actually vote: " + getPercentageOfVoters() + "%");
		for (int i = 0; i < partyCounter; i++) {
//			partyVotesCounter[i] = 0;
		}
		for (int i = 0; i < partyCounter; i++) {
			for (int j = 0; j < votersList.size(); j++) {
				if (partiesList.elementAt(i).getName().equals(votersList.elementAt(j).getChooseParty())) {
//					System.out.println(partyVotesCounter[i]++ + " this!!!");
					partyVotesCounter[i]++;
				}
			}
		}
		temp.append("\nthe number of votes in this ballot box to each party: ");
		for (int i = 0; i < partyCounter; i++) {
			temp.append("\n" + partiesList.elementAt(i).getName() + " = " + partyVotesCounter[i]);
		}
		return temp.toString();
	}
}
