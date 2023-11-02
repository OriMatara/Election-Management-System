package application.mvc.view;

import application.mvc.model.Citizen;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public interface ViewModelInterface {

	public void onClickLoadFromFile(EventHandler<ActionEvent> e);

	public void onClickAddBallotBox(EventHandler<ActionEvent> e);

	public void onClickSubmitAddBallotBox(EventHandler<ActionEvent> e);

	public void onClickYesUploadFile(EventHandler<ActionEvent> e);

	public void onClickSubmitAddCitizen(EventHandler<ActionEvent> e);

	public void onClickSubmitAddParty(EventHandler<ActionEvent> e);

	public void onClickSubmitAddCandidate(EventHandler<ActionEvent> e);

	public void onClickShowAllBallotBoxes(EventHandler<ActionEvent> e);

	public void onClickShowAllCitizenOrSoldier(EventHandler<ActionEvent> e);

	public void onClickShowAllParties(EventHandler<ActionEvent> e);

	public void onClickNoUploadFile(EventHandler<ActionEvent> e);

	public void onClickElectionRound(EventHandler<ActionEvent> e);

	public void yesVotingButton(EventHandler<ActionEvent> e);

	public void onClickSubmitPartyVote(EventHandler<ActionEvent> e);

	public void onClickElectionResults(EventHandler<ActionEvent> e);

	public void onClickSaveFile(EventHandler<ActionEvent> e);

	public void messageAlert(String msg);

	public void addPartyToComboBox(String name);

	public void closeTheElection();

	public void uploadedData();

	public void hardCodedData();

	public void addBallotBoxWindow();

	public void addCandidateToPartyWindow();

	public void addCitizenOrSoldierWindow();

	public void addPartyWindow();

	public void closeTheStage();

	public void whatParty();
	
	public boolean isVoting(String name);

	public boolean getToggelGroup();

	public Stage getTheStage();

	public TextField getAddressBallotBox();

	public TextField getCitizenOrSoldierName();

	public TextField getCitizenOrSoldierId();

	public TextField getCitizenOrSoldierBearthOfYear();

	public TextField getNumOfSickDayes();

	public TextField getPartyName();

	public TextField getCandidateName();

	public TextField getCandidateId();

	public TextField getCandidateBearthOfYear();

	public TextField getCandidateNumOfSickDays();

	public TextField getCandidateParty();

	public ComboBox<String> getComboBoxTypesBallotBox();

	public ComboBox<String> getComboBoxPartySide();

	public ComboBox<String> getComboBoxPartyVote();

}
