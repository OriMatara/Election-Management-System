package application.mvc.model;

import java.io.Serializable;
import java.util.InputMismatchException;

public class Soldier extends Citizen implements Serializable {
	
	private boolean carryWepon;

	public Soldier(String name, String id, int birthYear, boolean isQuarentin, int numOfSickDays, boolean isCarryWepon)
			throws InputMismatchException, Exception {
		super(name, id, birthYear, isQuarentin, numOfSickDays);
		
		carryWepon = isCarryWepon;
		
	}

	public void carryWeapon() {
		System.out.println("i got a gun!!!");
	}

	@Override
	public int getBallotBox() {
		return super.getBallotBox();
	}

	@Override
	public void setBallotBox(int ballotBox) {
		super.setBallotBox(ballotBox);
	}

	@Override
	public String getName() {
		return super.getName();
	}

	@Override
	public void setName(String name) {
		super.setName(name);
	}

	@Override
	public int getBirthYear() {
		return super.getBirthYear();
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Soldier))
			return false;
		if (!(super.equals(other)))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return super.toString();
	}
}