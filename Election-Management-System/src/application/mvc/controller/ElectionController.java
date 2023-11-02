package application.mvc.controller;

import application.mvc.model.BallotBox;
import application.mvc.model.Candidate;
import application.mvc.model.Citizen;
import application.mvc.model.ElectionRound;
import application.mvc.model.ModelInterface;
import application.mvc.model.Parties;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.Random;

import application.mvc.listeners.EventsListener;
import application.mvc.view.ViewModelInterface;
import javafx.stage.Stage;

public class ElectionController implements EventsListener {

	private String partyTheVoterChoosed;

	private int numOfElections;

	private ElectionRound newElectionRound = new ElectionRound();

	private ViewModelInterface viewInterface;
	@SuppressWarnings("unused")
	private ModelInterface model;

	public ElectionController(ModelInterface model, ViewModelInterface viewInterface) {
		this.model = model;
		this.viewInterface = viewInterface;

		viewInterface.addBallotBoxWindow();

		viewInterface.onClickSubmitAddBallotBox(e -> {

			String ballotBoxAddress = viewInterface.getAddressBallotBox().getText();

			String type = viewInterface.getComboBoxTypesBallotBox().getValue();
			try {
				int tempAdress = Integer.parseInt(ballotBoxAddress);

				tempAdress = tempAdress / 2;
				viewInterface.messageAlert("you most give a street address");
				return;

			} catch (Exception e1) {

				boolean isExsits = model.addBallotBox(ballotBoxAddress, type);

				if (ballotBoxAddress.isEmpty()) {
					viewInterface.messageAlert("you most give a street address");

				} else if (isExsits) {

					viewInterface.messageAlert("The ballot successfully added!");

					viewInterface.closeTheStage();

				} else {
					viewInterface.messageAlert("This ballot box is already exist in the system");

				}
			}
		});

		viewInterface.addCitizenOrSoldierWindow();

		viewInterface.onClickSubmitAddCitizen(e -> {

			Stage tempStage = viewInterface.getTheStage();

			String citizenOrSoldierId = viewInterface.getCitizenOrSoldierId().getText();

			String citizenOrSoldierName = viewInterface.getCitizenOrSoldierName().getText();

			boolean isQuarentine = viewInterface.getToggelGroup();

			int citizenOrSoldierBirthOfYear;

			Citizen temp = null;

			try {

				citizenOrSoldierBirthOfYear = Integer
						.parseInt(viewInterface.getCitizenOrSoldierBearthOfYear().getText());

				if (isQuarentine) {

					int numOfSickDyes = Integer.parseInt(viewInterface.getNumOfSickDayes().getText());

					temp = new Citizen(citizenOrSoldierName, citizenOrSoldierId, citizenOrSoldierBirthOfYear,
							isQuarentine, numOfSickDyes);

				} else {

					temp = new Citizen(citizenOrSoldierName, citizenOrSoldierId, citizenOrSoldierBirthOfYear,
							isQuarentine, 0);

				}

				if (LocalDate.now().getYear() - citizenOrSoldierBirthOfYear < 18) {
					viewInterface.messageAlert(
							"Sorry " + citizenOrSoldierName + " but you are under aged and you can't vote");
					return;

				}

				boolean addAnswer = model.addCitizenOrSoldier(temp);

				if (addAnswer) {

					if (LocalDate.now().getYear() - citizenOrSoldierBirthOfYear > 21) {
						viewInterface.messageAlert("The citizen voter  " + citizenOrSoldierName
								+ " successfully added to voters list too!");
						viewInterface.closeTheStage();
						return;

					}

					viewInterface.messageAlert("This " + model.citizenKind(temp) + " successfully added!");

					viewInterface.closeTheStage();

				} else {
					viewInterface.messageAlert(
							"Sorry but this " + model.citizenKind(temp) + " is already exist in the system!");
				}

			} catch (NumberFormatException q) {
				viewInterface.messageAlert("you most fill all the data and folow the instactions");

			} catch (Exception q) {
				viewInterface.messageAlert(q.getMessage());

			}
		});

		viewInterface.addPartyWindow();

		viewInterface.onClickSubmitAddParty(e -> {

			String partyName = viewInterface.getPartyName().getText();

			String partySide = viewInterface.getComboBoxPartySide().getValue();

			Parties newParty = new Parties(partyName, partySide);

			if (partyName.isEmpty()) {
				viewInterface.messageAlert("you most give a name to the party");
				return;

			}

			boolean addAnswer = model.addParty(newParty);

			if (addAnswer) {
				viewInterface.messageAlert("The party " + partyName + " successfully added!");

				viewInterface.addPartyToComboBox(partyName);

				viewInterface.closeTheStage();

			} else {
				viewInterface.messageAlert("The party " + partyName + " is already exist in the system");

			}
		});

		viewInterface.addCandidateToPartyWindow();

		viewInterface.onClickSubmitAddCandidate(e -> {

			String candidateName = viewInterface.getCandidateName().getText();

			String candidateId = viewInterface.getCandidateId().getText();

			String candidateParty = viewInterface.getCandidateParty().getText();

			boolean isQuarentin = viewInterface.getToggelGroup();

			Candidate newCandidate;

			try {

				int candidateBirthOfYear = Integer.parseInt(viewInterface.getCandidateBearthOfYear().getText());

				if (LocalDate.now().getYear() - candidateBirthOfYear <= 21) {
					viewInterface.messageAlert("sorry, you cant join to be candidate you under the age");
					return;
				}

				if (isQuarentin) {

					int candidateNumOfSickDays = Integer.parseInt(viewInterface.getCandidateNumOfSickDays().getText());

					newCandidate = new Candidate(candidateName, candidateId, candidateBirthOfYear, isQuarentin,
							candidateNumOfSickDays, candidateParty);

				} else {

					newCandidate = new Candidate(candidateName, candidateId, candidateBirthOfYear, isQuarentin, 0,
							candidateParty);

				}

				boolean addAnswer = model.addCandidateToParty(newCandidate);

				if (addAnswer) {
					viewInterface.messageAlert("The Candidate, " + candidateName + " is sucsesfully add!");
					viewInterface.closeTheStage();

				}

				else {
					viewInterface.messageAlert("sorry, " + candidateParty + " is not exist in the system");
				}

			} catch (NumberFormatException q) {
				viewInterface.messageAlert("you most put number of sick days or birth year!");

			} catch (Exception q) {
				viewInterface.messageAlert(q.getMessage());

			}
		});

		viewInterface.onClickShowAllBallotBoxes(e -> {

			model.showAllBallotBoxes();

		});

		viewInterface.onClickShowAllCitizenOrSoldier(e -> {

			model.showAllCitizens();
		});

		viewInterface.onClickShowAllParties(e -> {

			model.showAllParties();
		});

		viewInterface.onClickNoUploadFile(e -> {

			viewInterface.hardCodedData();

			try {

				BallotBox b1 = new BallotBox("frishman");
				BallotBox b2 = new BallotBox("dizingof");
				BallotBox b3 = new BallotBox("rotshild");
				BallotBox b4 = new BallotBox("rashi");

				model.addBallotBox(b1.getStreetAdress(), "Citizen");
				model.addBallotBox(b2.getStreetAdress(), "Soldier");
				model.addBallotBox(b3.getStreetAdress(), "Corona Citizen");
				model.addBallotBox(b4.getStreetAdress(), "Corona Soldier");

				Citizen c1 = new Citizen("michael", "123498789", 1995, false, 0);
				Citizen c2 = new Citizen("morgan", "123856789", 1999, true, 20);
				Citizen c3 = new Citizen("yareh", "123456781", 1992, false, 0);

				model.addCitizenOrSoldier(c1);
				model.addCitizenOrSoldier(c2);
				model.addCitizenOrSoldier(c3);

				Parties p1 = new Parties("kadima", "CENTER");
				Parties p2 = new Parties("likud", "RIGHT");
				Parties p3 = new Parties("meretz", "LEFT");

				model.addParty(p1);
				model.addParty(p2);
				model.addParty(p3);

				Candidate d1 = new Candidate("shimi", "223456789", 1995, false, 0, "likud");
				Candidate d2 = new Candidate("koko", "423456789", 1996, true, 9, "kadima");
				Candidate d3 = new Candidate("popo", "321654987", 1991, true, 39, "meretz");
				Candidate d4 = new Candidate("yuval", "321987654", 1987, false, 0, "kadima");
				Candidate d5 = new Candidate("ori", "963852741", 1994, false, 0, "likud");
				Candidate d6 = new Candidate("josh", "369258147", 1989, true, 45, "meretz");

				model.addCandidateToParty(d1);
				model.addCandidateToParty(d2);
				model.addCandidateToParty(d3);
				model.addCandidateToParty(d4);
				model.addCandidateToParty(d5);
				model.addCandidateToParty(d6);

				viewInterface.addPartyToComboBox(p1.getName());
				viewInterface.addPartyToComboBox(p2.getName());
				viewInterface.addPartyToComboBox(p3.getName());

			} catch (Exception e1) {

				System.out.println(e1);
			}

			viewInterface.messageAlert("hard coded data uploaded!");
		});

		viewInterface.onClickElectionRound(e -> {

			numOfElections++;

			int numRond = 0;

			// Citizen Voters
			for (int i = 0; i < model.getCitizenVotersList().getCurrentSize(); i++) {
				numRond = 1;
				String voterName = model.getCitizenVotersList().get(i).getName();

				boolean answer = viewInterface.isVoting(voterName);

				if (answer) {
					viewInterface.whatParty();
					partyTheVoterChoosed = viewInterface.getComboBoxPartyVote().getValue();

					model.votingFunc(model.getCitizenVotersList().get(i));

				}
			}

			// Corona Voters
			for (int i = 0; i < model.getCoronaVotersList().size(); i++) {
				numRond = 1;
				String voterName = model.getCoronaVotersList().get(i).getName();

				boolean answer = viewInterface.isVoting(voterName);

				if (answer) {
					viewInterface.whatParty();
					partyTheVoterChoosed = viewInterface.getComboBoxPartyVote().getValue();

					model.votingFunc(model.getCoronaVotersList().get(i));
				}
			}

			// Soldier Voters
			for (int i = 0; i < model.getSoldierList().size(); i++) {
				numRond = 1;
				String voterName = model.getSoldierList().get(i).getName();

				boolean answer = viewInterface.isVoting(voterName);

				if (answer) {
					viewInterface.whatParty();
					partyTheVoterChoosed = viewInterface.getComboBoxPartyVote().getValue();

					model.votingFunc(model.getSoldierList().get(i));
				}
			}

			// Corona Soldier Voters
			for (int i = 0; i < model.getCoronaSoldierList().size(); i++) {
				numRond = 1;
				String voterName = model.getCoronaSoldierList().get(i).getName();

				boolean answer = viewInterface.isVoting(voterName);

				if (answer) {
					viewInterface.whatParty();
					partyTheVoterChoosed = viewInterface.getComboBoxPartyVote().getValue();

					model.votingFunc(model.getCoronaSoldierList().get(i));
				}
			}
			if (numRond != 0) {
				viewInterface.messageAlert("The Election Finished, Thenk for your time");

			} else {
				viewInterface.messageAlert("There isnt enough data to start elections!");
			}

		});

		// Party vote
		viewInterface.onClickSubmitPartyVote(e -> {

			model.counterVoties(partyTheVoterChoosed);

			viewInterface.closeTheStage();

		});

		viewInterface.onClickElectionResults(e -> {

			if (numOfElections == 0) {
				viewInterface.messageAlert("there wasnt Elections yet!");
				return;
			} else {
				model.electionReasults();

			}

		});

		viewInterface.onClickSaveFile(e -> {
			try {
				model.saveToFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			viewInterface.closeTheElection();
		});

		viewInterface.onClickYesUploadFile(e -> {
			try {
				model.readFromFile(viewInterface);
				viewInterface.uploadedData();
			} catch (Exception e1) {
				e1.printStackTrace();
				viewInterface.uploadedData();
			}
			viewInterface.messageAlert("you choosed to upload the previous elections.");
		});
	}

	@Override
	public void addBallotBoxToUI(String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public void howManySickDaysTf() {
		// TODO Auto-generated method stub

	}

}
