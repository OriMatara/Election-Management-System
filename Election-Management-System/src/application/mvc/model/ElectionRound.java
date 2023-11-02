package application.mvc.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Random;
import java.util.Vector;

import application.mvc.controller.ElectionController;
import application.mvc.view.ViewModelInterface;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ElectionRound implements Serializable, ModelInterface {

	/**
	 * 
	 */
	private Stage theStage = new Stage();
	private static final long serialVersionUID = 1L;
	public int size = 1000;
	public Vector<BallotBox> citizenBallotBoxes;
	public Vector<BallotBox> coronaBallotBoxes;
	public Vector<BallotBox> armyBallotBoxes;
	public Vector<BallotBox> coronaArmyBallotBoxes;
	public Vector<Candidate> candidateList;
	public Set<Citizen> citizenVotersList;
	public Vector<CoronaCitizen> coronaVotersList;
	public Vector<Soldier> soldierList;
	public Vector<CoronaSoldier> CoronaSoldierList;
	public Set<Citizen> allCitizen;
	public Vector<Parties> allParties;

	private VBox layout = new VBox();

	private VBox layoutForResults = new VBox();

	private ListView listViewBallots = new ListView();
	private ListView listViewCitizenOrSoldier = new ListView();
	private ListView listViewParty = new ListView();

	private ListView listViewElectionResults = new ListView();

	public static final String F_NAME = "Ellection.txt";
	private Scene scene = new Scene(layout);
	Calendar cal;

	public int[] CitizenPartyVotesCounter;
	public int[] soldierPartyVotesCounter;
	public int[] coronaPartyVotesCounter;
	public int[] coronaSoldierPartyVotesCounter;

	public ElectionRound() {
		citizenBallotBoxes = new Vector<BallotBox>();
		coronaBallotBoxes = new Vector<BallotBox>();
		armyBallotBoxes = new Vector<BallotBox>();
		coronaArmyBallotBoxes = new Vector<BallotBox>();
		candidateList = new Vector<Candidate>();
		citizenVotersList = new Set<Citizen>();
		coronaVotersList = new Vector<CoronaCitizen>();
		soldierList = new Vector<Soldier>();
		CoronaSoldierList = new Vector<CoronaSoldier>();
		allCitizen = new Set<Citizen>();
		allParties = new Vector<Parties>();
		cal = Calendar.getInstance();
		CitizenPartyVotesCounter = new int[size];
		soldierPartyVotesCounter = new int[size];
		coronaPartyVotesCounter = new int[size];
		coronaSoldierPartyVotesCounter = new int[size];
	}

	@Override
	public boolean addBallotBox(String streetAdress, String type) {

		switch (type) {
		case "Citizen": {
			BallotBox newBallot = new BallotBox(streetAdress);
			if (citizenBallotBoxes.contains(newBallot)) {
				return false;
			}
			citizenBallotBoxes.add(newBallot);
			listViewBallots.getItems().add(newBallot);
			return true;
		}

		case "Soldier": {

			BallotBox newBallot = new BallotBox(streetAdress);
			if (armyBallotBoxes.contains(newBallot)) {
				return false;
			}

			armyBallotBoxes.add(newBallot);
			listViewBallots.getItems().add(newBallot);
			return true;
		}

		case "Corona Citizen": {
			BallotBox newBallot = new BallotBox(streetAdress);
			if (coronaBallotBoxes.contains(newBallot)) {
				return false;
			}

			coronaBallotBoxes.add(newBallot);
			listViewBallots.getItems().add(newBallot);
			return true;
		}

		case "Corona Soldier": {
			BallotBox newBallot = new BallotBox(streetAdress);

			if (coronaArmyBallotBoxes.contains(newBallot)) {
				return false;
			}

			coronaArmyBallotBoxes.add(newBallot);
			listViewBallots.getItems().add(newBallot);
			return true;

		}

		}

		return false;

	}

	@Override
	public boolean addCitizenOrSoldier(Citizen c) throws Exception {

		if (LocalDate.now().getYear() - c.getBirthYear() >= 18 && LocalDate.now().getYear() - c.getBirthYear() <= 21) {
//			isCarryWepon = application.mvc.controller.ElectionController.isCarryWepon();

			boolean isCarryWepon = isCarryWepon(c.getName());

			if (c.isQuarentin == true) {

				CoronaSoldier newCoronaSoldier = new CoronaSoldier(c.getName(), c.getId(), c.getBirthYear(),
						c.getIsQuarentin(), c.getNumOfSickDays(), isCarryWepon);

				if (CoronaSoldierList.contains(newCoronaSoldier)) {

					return false;
				}

				CoronaSoldierList.add(newCoronaSoldier);
				listViewCitizenOrSoldier.getItems().add(newCoronaSoldier);

				return true;

			} else {

				Soldier newSoldier = new Soldier(c.getName(), c.getId(), c.getBirthYear(), c.getIsQuarentin(),
						c.getNumOfSickDays(), isCarryWepon);

				if (soldierList.contains(newSoldier)) {

					return false;
				}

				soldierList.add(newSoldier);

				listViewCitizenOrSoldier.getItems().add(newSoldier);

				return true;
			}
		} else {
			if (c.isQuarentin == true) {
				CoronaCitizen newCoronaCitizen = new CoronaCitizen(c.getName(), c.getId(), c.getBirthYear(),
						c.getIsQuarentin(), c.getNumOfSickDays());

				for (int i = 0; i < allCitizen.getCurrentSize(); i++) {
					if (allCitizen.get(i).equals(c)) {

						return false;
					}
				}

				Citizen newCitizen = new Citizen(c.getName(), c.getId(), c.getBirthYear(), c.getIsQuarentin(),
						c.getNumOfSickDays());

				allCitizen.add(newCitizen);

				listViewCitizenOrSoldier.getItems().add(newCitizen);

				if (LocalDate.now().getYear() - c.getBirthYear() > 21) {
					coronaVotersList.add(newCoronaCitizen);

					return true;

				} else {

					return false;
				}

			} else {
				Citizen newCitizen = new Citizen(c.getName(), c.getId(), c.getBirthYear(), c.getIsQuarentin(),
						c.getNumOfSickDays());

				for (int i = 0; i < allCitizen.getCurrentSize(); i++) {

					if (allCitizen.get(i).equals(newCitizen)) {

						return false;
					}
				}

				allCitizen.add(newCitizen);

				listViewCitizenOrSoldier.getItems().add(newCitizen);

				if (LocalDate.now().getYear() - c.getBirthYear() > 21) {

					citizenVotersList.add(newCitizen);

					return true;

				}
				return true;
			}
		}
	}

	@Override
	public boolean addCandidateToParty(Candidate newCandidate) {
		if (LocalDate.now().getYear() - newCandidate.getBirthYear() <= 21) {

			return false;
		}

		// check if candidate exist by id, if he is he wont get to candidate list and to
		// other party

		if (candidateList.contains(newCandidate)) {

			return false;
		}

		for (int i = 0; i < allParties.size(); i++) {
			if (allParties.elementAt(i).getName().equals(newCandidate.getAddedToParty())) {
				allParties.elementAt(i).addCandidateToParty(newCandidate);
				candidateList.add(newCandidate);

				return true;
			}
		}

		return false;
	}

	public void electionReasults() {

		Stage newStage = new Stage();

		GridPane pane = new GridPane();

		int widthSize = 100;

		Label title = new Label();
		Label ballotsTitle = new Label();
		Label citizenBallots;
		Label coronaBallots;
		Label armyBallots;
		Label coronaArmyBallots;
		Label Parties;

		title.setText("Parties:");
		pane.add(title, 0, 0);
		// Parties data
		for (int i = 0; i < allParties.size(); i++) {

			Parties = new Label(allParties.elementAt(i).toString());
			listViewElectionResults.getItems().add(allParties.elementAt(i));
		}

		ballotsTitle.setText("citizen Ballots");
		pane.add(ballotsTitle, 0, 0);
		for (int i = 0; i < citizenBallotBoxes.size(); i++) {

			citizenBallots = new Label(citizenBallotBoxes.elementAt(i).toString());

			listViewElectionResults.getItems().add(citizenBallotBoxes.elementAt(i));

		}

		// Corona Ballots
		for (int i = 0; i < coronaBallotBoxes.size(); i++) {

			citizenBallots = new Label(coronaBallotBoxes.elementAt(i).toString());
			listViewElectionResults.getItems().add(coronaBallotBoxes.elementAt(i));

		}

		// Soldier Ballots
		for (int i = 0; i < armyBallotBoxes.size(); i++) {

			citizenBallots = new Label(armyBallotBoxes.elementAt(i).toString());
			listViewElectionResults.getItems().add(armyBallotBoxes.elementAt(i));

		}

		// Corona Soldier Ballots
		for (int i = 0; i < coronaArmyBallotBoxes.size(); i++) {

			citizenBallots = new Label(coronaArmyBallotBoxes.elementAt(i).toString());
			listViewElectionResults.getItems().add(coronaArmyBallotBoxes.elementAt(i));

		}

		layoutForResults.getChildren().addAll(listViewElectionResults);

		layoutForResults = new VBox(10);

		layoutForResults.setPadding(new Insets(10));

		layoutForResults.getChildren().addAll(listViewElectionResults);

		scene = new Scene(layoutForResults, 600, 500);
		theStage.setScene(scene);
		theStage.show();

	}

	public void showAllBallotBoxes() {

		if (listViewBallots.getItems().isEmpty()) {

			showMessage("sorry but there is't any ballot boxes to show!");

		} else {

			showLayout(listViewBallots, "Ballots list");

		}

	}

	public void showAllCitizens() {

		if (listViewCitizenOrSoldier.getItems().isEmpty()) {

			showMessage("sorry but there is't any Citizens to show");

		} else {

			showLayout(listViewCitizenOrSoldier, "Citizen List");

		}
	}

	public void showAllParties() {

		if (listViewParty.getItems().isEmpty()) {

			showMessage("sorry but there is't any parties to show");

		} else {
			showLayout(listViewParty, "Parties list");

		}
	}

	public void showLayout(ListView listView, String Title) {

		theStage.setTitle(Title);

		layout = new VBox(10);

		layout.setPadding(new Insets(10));

		layout.getChildren().addAll(listView);

		scene = new Scene(layout, 600, 500);
		theStage.setScene(scene);
		theStage.show();
	}

	public void isHeVoting() {
		for (int i = 0; i < getCitizenVotersList().getCurrentSize(); i++) {
		}
	}

	public void votingFunc(Citizen c) {

		Random rnd = new Random();

		// This method gives you a random boolean variable every round.

		switch (citizenKind(c)) {

		// in the function "citizenKind" we send the citizen to a function that telling

		// us if the citizen is a soldier or he is in quarantine

		case "citizen": {

			int temp = rnd.nextInt(citizenBallotBoxes.size());
			citizenBallotBoxes.elementAt(temp).isVotedHere(c);
			c.setBallotBox(citizenBallotBoxes.elementAt(temp).getSerialNumber());
			CitizenPartyVotesCounter[temp]++;
			citizenBallotBoxes.elementAt(temp).setPartyVotesCounter(CitizenPartyVotesCounter);
			getCitizenBallotBoxes().elementAt(temp).setActuallyVoted();
		}
			break;
		case "soldier": {

			int temp = rnd.nextInt(armyBallotBoxes.size());
			Soldier s = (Soldier) c;
			armyBallotBoxes.elementAt(temp).isVotedHere(s);
			s.setBallotBox(armyBallotBoxes.elementAt(temp).getSerialNumber());
			soldierPartyVotesCounter[temp]++;
			armyBallotBoxes.elementAt(temp).setPartyVotesCounter(soldierPartyVotesCounter);
			getCitizenBallotBoxes().elementAt(temp).setActuallyVoted();
		}
			break;
		case "corona citizen": {

			if (isHaveProtection(c.getName())) {

				int temp = rnd.nextInt(coronaBallotBoxes.size());
				coronaBallotBoxes.elementAt(temp).isVotedHere(c);
				c.setBallotBox(coronaBallotBoxes.elementAt(temp).getSerialNumber());
				coronaPartyVotesCounter[temp]++;
				coronaBallotBoxes.elementAt(temp).setPartyVotesCounter(coronaPartyVotesCounter);
				getCitizenBallotBoxes().elementAt(temp).setActuallyVoted();
			} else {

				showMessage("you dont have protection so you cant vote");
			}
		}
			break;

		case "corona soldier": {

			if (isHaveProtection(c.getName())) {
				int temp = rnd.nextInt(coronaArmyBallotBoxes.size());
				CoronaSoldier s = (CoronaSoldier) c;
				coronaArmyBallotBoxes.elementAt(temp).isVotedHere(s);
				s.setBallotBox(coronaArmyBallotBoxes.elementAt(temp).getSerialNumber());
				coronaSoldierPartyVotesCounter[temp]++;
				coronaArmyBallotBoxes.elementAt(temp).setPartyVotesCounter(coronaSoldierPartyVotesCounter);
				getCitizenBallotBoxes().elementAt(temp).setActuallyVoted();
			} else {
				showMessage("you dont have protection so you cant vote");

			}
		}
			break;
		}
	}

	@Override
	public void counterVoties(String partyName) {
		for (int i = 0; i < getAllParties().size(); i++) {
			if (getAllParties().elementAt(i).getName().equals(partyName)) {
				getAllParties().elementAt(i).setPartyVoterCounter();
			}
		}
	}

	public String citizenKind(Citizen c) {
		if (LocalDate.now().getYear() - c.getBirthYear() >= 18 && LocalDate.now().getYear() - c.getBirthYear() <= 21) {

			if (c.isQuarentin) {

				return "corona soldier";
			}
			return "soldier";

		} else {
			if (c.isQuarentin) {

				return "corona citizen";
			}

			return "citizen";

		}
	}

	public boolean addParty(Parties newParty) {

		if (allParties.contains(newParty)) {

			return false;
		}

		allParties.add(newParty);

		BallotBox.setPartiesList(newParty);
		BallotBox.setPartyCounter();

		listViewParty.getItems().add(newParty);

		return true;
	}

	public void saveToFile() throws FileNotFoundException, IOException {
		try {
			ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(F_NAME));

			output.writeObject(citizenBallotBoxes);
			output.writeObject(coronaBallotBoxes);
			output.writeObject(armyBallotBoxes);
			output.writeObject(coronaArmyBallotBoxes);
			output.writeObject(allCitizen);
			output.writeObject(allParties);
			output.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	@Override
	public boolean readFromFile(ViewModelInterface view) throws Exception {

		try {
			ObjectInputStream input = new ObjectInputStream(new FileInputStream(F_NAME));

			// Citizen Ballotx load
			Vector<BallotBox> allCitizenBallotsFromFile = (Vector<BallotBox>) input.readObject();
			citizenBallotBoxes = allCitizenBallotsFromFile;
			for (int i = 0; i < allCitizenBallotsFromFile.size(); i++) {
				addBallotBox(allCitizenBallotsFromFile.get(i).getStreetAdress(), "Citizen");
			}

			// Soldier Ballotx load
			Vector<BallotBox> allArmyBallotsFromFile = (Vector<BallotBox>) input.readObject();
			armyBallotBoxes = allArmyBallotsFromFile;
			for (int i = 0; i < allArmyBallotsFromFile.size(); i++) {
				addBallotBox(allArmyBallotsFromFile.get(i).getStreetAdress(), "Soldier");
			}

			// Corona Citizen Ballotx load
			Vector<BallotBox> allCoronaCitizenBallotsFromFile = (Vector<BallotBox>) input.readObject();
			coronaBallotBoxes = allCoronaCitizenBallotsFromFile;
			for (int i = 0; i < allCoronaCitizenBallotsFromFile.size(); i++) {
				addBallotBox(allCoronaCitizenBallotsFromFile.get(i).getStreetAdress(), "Corona Citizen");
			}

			// Corona Soldier Ballotx load
			Vector<BallotBox> allCoronaSoldierBallotsFromFile = (Vector<BallotBox>) input.readObject();
			coronaArmyBallotBoxes = allCoronaSoldierBallotsFromFile;
			for (int i = 0; i < allCoronaSoldierBallotsFromFile.size(); i++) {
				addBallotBox(allCoronaSoldierBallotsFromFile.get(i).getStreetAdress(), "Corona Soldier");
			}

			Set<Citizen> allCitizensFromFile = (Set<Citizen>) input.readObject();
			allCitizen = allCitizensFromFile;
			for (int i = 0; i < allCitizensFromFile.getCurrentSize(); i++) {
				addCitizenOrSoldier(allCitizensFromFile.get(i));
			}

			Vector<Parties> allPartiesFromFile = (Vector<Parties>) input.readObject();
			allParties = allPartiesFromFile;
			for (int i = 0; i < allPartiesFromFile.size(); i++) {
				addParty(allPartiesFromFile.get(i));

			}
			input.close();
			return true;

		} catch (FileNotFoundException e) {
			System.out.println("There is no file!");
			return false;

		} catch (IOException e) {
			System.out.println("The file is empty!");
			return false;

		} catch (ClassNotFoundException e) {
			System.out.println("Something went wrong!");
			return false;

		}
	}

	@Override
	public boolean isHaveProtection(String str) {

		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		ButtonType yes = new ButtonType("Yes");
		ButtonType no = new ButtonType("No");

		alert.setTitle("Message");
		alert.initStyle(StageStyle.UTILITY);
		alert.getButtonTypes().setAll(yes, no);
		alert.setContentText("Is " + str + " have protection ?");

		Optional<ButtonType> answer = alert.showAndWait();

		if (answer.get() == yes) {

			return true;
		} else {

			return false;
		}
	}

	@Override
	public boolean isCarryWepon(String str) {

		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		ButtonType yes = new ButtonType("Yes");
		ButtonType no = new ButtonType("No");

		alert.setTitle("Message");
		alert.initStyle(StageStyle.UTILITY);
		alert.getButtonTypes().setAll(yes, no);
		alert.setContentText("Is soldier " + str + " carrys a wepon ?");

		Optional<ButtonType> answer = alert.showAndWait();

		if (answer.get() == yes) {

			return true;
		} else {

			return false;
		}
	}

	@Override
	public void showMessage(String str) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.initStyle(StageStyle.UTILITY);
//		alert.initOwner(newWindow);
		alert.setTitle("Message");
		alert.setHeaderText(null);
		alert.setContentText(str);
		alert.showAndWait();
	}

	public ListView getlistCitizenOrSoldier() {
		return listViewCitizenOrSoldier;
	}

	public Set<Citizen> getAllCitizen() {
		return allCitizen;
	}

	public void setAllCitizen(Set<Citizen> allCitizen) {
		this.allCitizen = allCitizen;
	}

	public Set<Citizen> getCitizenVotersList() {
		return citizenVotersList;
	}

	public void setCitizenVotersList(Set<Citizen> citizenVotersList) {
		this.citizenVotersList = citizenVotersList;
	}

	public Vector<CoronaCitizen> getCoronaVotersList() {
		return coronaVotersList;
	}

	public void setCoronaVotersList(Vector<CoronaCitizen> coronaVotersList) {
		this.coronaVotersList = coronaVotersList;
	}

	public Vector<Parties> getAllParties() {
		return allParties;
	}

	public void setAllParties(Vector<Parties> allParties) {
		this.allParties = allParties;
	}

	public Vector<BallotBox> getCitizenBallotBoxes() {
		return citizenBallotBoxes;
	}

	public void setCitizenBallotBoxes(Vector<BallotBox> citizenBallotBoxes) {
		this.citizenBallotBoxes = citizenBallotBoxes;
	}

	public Vector<BallotBox> getCoronaBallotBoxes() {
		return coronaBallotBoxes;
	}

	public void setCoronaBallotBoxes(Vector<BallotBox> coronaBallotBoxes) {
		this.coronaBallotBoxes = coronaBallotBoxes;
	}

	public Vector<BallotBox> getArmyBallotBoxes() {
		return armyBallotBoxes;
	}

	public void setArmyBallotBoxes(Vector<BallotBox> armyBallotBoxes) {
		this.armyBallotBoxes = armyBallotBoxes;
	}

	public Vector<BallotBox> getCoronaArmyBallotBoxes() {
		return coronaArmyBallotBoxes;
	}

	public void setCoronaArmyBallotBoxes(Vector<BallotBox> coronaArmyBallotBoxes) {
		this.coronaArmyBallotBoxes = coronaArmyBallotBoxes;
	}

	public Vector<Soldier> getSoldierList() {
		return soldierList;
	}

	public void setSoldierList(Vector<Soldier> soldierList) {
		this.soldierList = soldierList;
	}

	public Vector<CoronaSoldier> getCoronaSoldierList() {
		return CoronaSoldierList;
	}

	public void setCoronaSoldierList(Vector<CoronaSoldier> coronaSoldierList) {
		CoronaSoldierList = coronaSoldierList;
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public String toString() {
		return "ElectionRound: dateOfElection=" + cal.getTime();
	}
}