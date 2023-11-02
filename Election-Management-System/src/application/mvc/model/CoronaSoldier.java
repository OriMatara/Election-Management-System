package application.mvc.model;

import java.io.Serializable;
import java.util.InputMismatchException;

public class CoronaSoldier extends Soldier implements Serializable {


	public CoronaSoldier(String name, String id, int birthYear, boolean isQuarentin, int numOfSickDays, boolean isCarryWepon) throws InputMismatchException, Exception {
		super(name, id, birthYear, isQuarentin, numOfSickDays, isCarryWepon);		
	}

	public int getNumOfSickDays() {
		return numOfSickDays;
	}

	public void setNumOfSickDays(int numOfSickDays) {
		this.numOfSickDays = numOfSickDays;
	}

	public boolean getIsQuarentin() {
		return isQuarentin;
	}

	public void setQuarentin(boolean isQuarentin) {
		this.isQuarentin = isQuarentin;
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof CoronaSoldier))
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
