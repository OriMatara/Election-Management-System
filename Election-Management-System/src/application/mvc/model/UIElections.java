package application.mvc.model;

import java.util.Vector;

public interface UIElections {

	void addBallotBox(ElectionRound currentElection);

	void addCitizenOrSoldier(ElectionRound currentElection);

	void addParty(ElectionRound currentElection);

	void addCandidateToParty(ElectionRound currentElection);

	void showAllBalotBoxes(ElectionRound currentElection);

	void showAllCitizens(ElectionRound currentElection);

	void showAllParties(ElectionRound currentElection);

	void votingFunc(ElectionRound currentElection);

	void electionReasults(ElectionRound currentElection);

}
