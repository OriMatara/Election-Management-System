package application.mvc.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

import application.mvc.view.ViewModelInterface;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public interface ModelInterface {

	public boolean addBallotBox(String ballotBoxAddress, String type);

	public boolean addCitizenOrSoldier(Citizen c) throws Exception;

	public boolean addParty(Parties newParty);

	public boolean addCandidateToParty(Candidate newCandidate);

	public boolean isCarryWepon(String str);

	public void showAllBallotBoxes();

	public void showAllCitizens();

	public void showAllParties();

	public void counterVoties(String partyName);

	public void votingFunc(Citizen c);

	public void saveToFile() throws FileNotFoundException, IOException;

	public boolean readFromFile(ViewModelInterface view) throws Exception;

	public void electionReasults();

	public ListView getlistCitizenOrSoldier();

	public Set<Citizen> getCitizenVotersList();

	public Vector<CoronaCitizen> getCoronaVotersList();

	public Vector<CoronaSoldier> getCoronaSoldierList();

	public Vector<Soldier> getSoldierList();

	public void isHeVoting();

//	public void loadHardCoded();

	public String citizenKind(Citizen c);

	public Vector<BallotBox> getCitizenBallotBoxes();

	public Vector<BallotBox> getCoronaArmyBallotBoxes();

	public Vector<BallotBox> getArmyBallotBoxes();

	public Vector<BallotBox> getCoronaBallotBoxes();

	boolean isHaveProtection(String str);

	void showMessage(String str);

}
