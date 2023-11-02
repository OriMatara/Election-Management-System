package application.mvc.model;

import java.io.Serializable;

public class Candidate extends Citizen implements Serializable {
	private String addedToParty;

	public Candidate(String name, String id, int birthYear, boolean isQuarentin, int numOfSickDays, String addedToParty)
			throws Exception {
		super(name, id, birthYear, isQuarentin, numOfSickDays);
		this.addedToParty = addedToParty;
	}

	public String getAddedToParty() {
		return addedToParty;
	}

	public void setAddedToParty(String addedToParty) {
		this.addedToParty = addedToParty;
	}

	@Override
	public String getChooseParty() {

		return super.getChooseParty();
	}

	@Override
	public void setChooseParty(String party) {

		super.setChooseParty(party);
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
	public int getBallotBox() {

		return super.getBallotBox();
	}

	@Override
	public void setBallotBox(int ballotBox) {

		super.setBallotBox(ballotBox);
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Candidate))
			return false;
		if (!(super.equals(other)))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return super.toString() + " party: " + addedToParty + ".";
	}

}
