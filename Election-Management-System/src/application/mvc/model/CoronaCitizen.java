package application.mvc.model;

import java.io.Serializable;
import java.util.InputMismatchException;

public class CoronaCitizen extends Citizen implements Serializable {

	public CoronaCitizen(String name, String id, int birthYear, boolean isQuarentin, int numOfSickDays)
			throws InputMismatchException, Exception {
		super(name, id, birthYear, isQuarentin, numOfSickDays);
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof CoronaCitizen))
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
