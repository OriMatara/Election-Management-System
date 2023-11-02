package application.mvc.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.InputMismatchException;

public class Citizen implements Serializable {

	protected String name;
	protected String id;
	protected int birthYear;
	protected int ballotBox;
	protected boolean isQuarentin;
	protected String chooseParty;
	protected int numOfSickDays;

	public Citizen(String name, String id, int birthYear, boolean isQuarentin, int numOfSickDays) throws Exception {
		this.name = name;
		setId(id);
		setBirthYear(birthYear);
		setQuarentin(isQuarentin);
		setNumOfSickDays(numOfSickDays);
	}

	public boolean getIsQuarentin() {
		return isQuarentin;
	}

	public void setQuarentin(boolean isQuarentin) throws InputMismatchException {
		this.isQuarentin = isQuarentin;
	}

	public int getNumOfSickDays() {
		return numOfSickDays;
	}

	public void setNumOfSickDays(int numOfSickDays) throws Exception {
		if (numOfSickDays < 0) {
			throw new Exception("invalid input, you must enter positive number for sick days, try again\n");
		} else {
			this.numOfSickDays = numOfSickDays;
		}
	}

	public String getChooseParty() {
		return chooseParty;
	}

	public void setChooseParty(String party) {
		this.chooseParty = party;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) throws Exception {
		for (int i = 0; i < id.length(); i++) {
			if ((int) id.charAt(i) < 48 || (int) id.charAt(i) > 57 || id.length() != 9) {
				throw new Exception("invalid id, you must enter 9 digits of your id, try again\n");
				
			}
		}
		this.id = id;

	}

	public int getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(int birthYear) throws Exception {
		if (LocalDate.now().getYear() < birthYear || birthYear < 0) {
			throw new Exception("invalid birth year, try again\n");
		} else {
			this.birthYear = birthYear;
		}
	}

	public int getBallotBox() {
		return ballotBox;
	}

	public void setBallotBox(int ballotBox) {
		this.ballotBox = ballotBox;
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Citizen))
			return false;
		Citizen c = (Citizen) other;
		return c.id.equals(id);
	}

	@Override
	public String toString() {
		StringBuffer temp = new StringBuffer();
		temp.append(getClass().getSimpleName() + " name: " + name + ", id: " + id + ", birth year: " + birthYear
				+ ", is quarentin: " + isQuarentin + ", num of sick days: " + numOfSickDays);
		if (ballotBox != 0) {
			temp.append(", ballotBox: " + ballotBox + ".");
		} else {
			temp.append(".");
		}
		return temp.toString();
	}
}
