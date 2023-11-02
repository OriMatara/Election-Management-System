package application.mvc.view;

import application.mvc.listeners.EventsListener;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Vector;

import application.mvc.listeners.EventsListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import application.mvc.model.BallotBox;
import application.mvc.model.ElectionRound;

public class ViewModel implements ViewModelInterface {

	// Stage
	private Stage newWindow;
	private VBox layout = new VBox();

	// Listeners
	private Vector<EventsListener> allBallotBoxListeners = new Vector<EventsListener>();

	// Vector

	// Board
	private GridPane ElectionBoard;

	// Stage
	private Stage theStage;

	// Scene
	private Scene scene;

	// Title
	private Label lblBallotBoxAddress = new Label();
	private Label lblBallotBoxType = new Label();
	private Label lblCitizenBearthYear = new Label();
	private Label lblCitizenOrSoldierName = new Label();
	private Label lblCitizenOrSoldierId = new Label();
	private Label lblIsQuarentin = new Label();
	private Label lblNumOfSickDayes = new Label();
	private Label lblCandidateName = new Label();
	private Label lblCandidateBearthOfYear = new Label();
	private Label lblCandidateId = new Label();
	private Label lblCandidateParty = new Label();
	private Label lblPartySide = new Label();
	private Label lblVoterName = new Label();
	private Label lblPartyVote = new Label();
	private Label lblName;
	private Label lblMenu;

	// Text Field
	private TextField tfBallotBoxAddress = new TextField();
	private TextField tfCitizenOrSoldierName = new TextField();
	private TextField tfCitizenOrSoldierId = new TextField();
	private TextField tfCitizenOrSoldierBearthOfYear = new TextField();
	private TextField tfNumOfSickDays = new TextField();
	private TextField tfPartyName = new TextField();
	private TextField tfCandidateBearthOfYear = new TextField();
	private TextField tfCandidateName = new TextField();
	private TextField tfCandidateId = new TextField();
	private TextField tfCandidateNumOfSickDays = new TextField();
	private TextField tfCandidateParty = new TextField();

	private ToggleGroup tgIsQuarentin = new ToggleGroup();
	private RadioButton chIsQuarentinYes = new RadioButton("Yes");
	private RadioButton chIsQuarentinNo = new RadioButton("No");

	// ComoBox
	private ObservableList<String> ballotTypes = FXCollections.observableArrayList("Citizen", "Soldier",
			"Corona Soldier", "Corona Citizen");

	private ObservableList<String> partySides = FXCollections.observableArrayList("LEFT", "CENTER", "RIGHT");

	private ComboBox<String> comboBoxTypesBallotBox = new ComboBox<>(ballotTypes);

	private ComboBox<String> comboBoxPartySides = new ComboBox<>(partySides);

	private ComboBox<String> comboBoxAllParties = new ComboBox<>();

	private ComboBox<String> comboBoxAllCitizenBallotx = new ComboBox<String>();

	// Button
	private Button btnSubmitAddBallotBox = new Button("SUBMIT");
	private Button btnSubmitAddCitizenOrSoldier = new Button("SUBMIT");
	private Button btnSubmitAddParty = new Button("SUBMIT");
	private Button btnSubmitAddCandidateToParty = new Button("SUBMIT");
	private Button btnSubmitPartyVote = new Button("SUBMIT");
	private Button btnYesVoting = new Button("Yes");
	private Button btnNoVoting = new Button("No");
	private Button btnUploadFileYes;
	private Button btnUploadFileNo;
	private Button btnAddBallotBox;
	private Button btnAddCitizen;
	private Button btnAddParty;
	private Button btnAddCandidate;
	private Button btnShowAllBallotBoxes;
	private Button btnShowAllCitizen;
	private Button btnShowAllParties;
	private Button btnElection;
	private Button btnShowElectionResults;
	private Button btnExit;

	public ViewModel(Stage theStage) {

		ElectionBoard = new GridPane();
		this.theStage = theStage;

		lblName = new Label("Welcome to Oris and Michaels Elections project!");
		lblName.setStyle(
				"-fx-font-family: TRON; -fx-font-size: 14; -fx-font-style: italic; -fx-font-weight: bold; -fx-border-color: black; -fx-border-width: 0 0 2 0;");

		lblMenu = new Label("do you want to upload a file information?");
		btnUploadFileYes = new Button("Yes");
		btnUploadFileNo = new Button("No");

		ElectionBoard.add(btnUploadFileYes, 3, 5);
		ElectionBoard.add(btnUploadFileNo, 4, 5);
		ElectionBoard.add(lblName, 0, 0);
		ElectionBoard.add(lblMenu, 0, 1);

		lblMenu = new Label("What do you like to do?:");
		lblMenu.setStyle("-fx-font-family: TRON; -fx-font-size: 15;");

		btnAddBallotBox = new Button("1 - Add ballot box");
		btnAddCitizen = new Button("2 - Add citizen");
		btnAddParty = new Button("3 - Add party");
		btnAddCandidate = new Button("4 - Add candidate for party");
		btnShowAllBallotBoxes = new Button("5 - Show all the ballot boxes");
		btnShowAllCitizen = new Button("6 - Show all the citizens");
		btnShowAllParties = new Button("7 - Show all the parties");
		btnElection = new Button("8 - Elections");
		btnShowElectionResults = new Button("9 - Show the election results");
		btnExit = new Button("10 - Exit and Save");

		ElectionBoard.setPadding(new Insets(10));
		ElectionBoard.setHgap(10);
		ElectionBoard.setVgap(10);

		scene = new Scene(ElectionBoard, 450, 150);
		this.theStage.setTitle("Elections");
		this.theStage.setScene(scene);
		this.theStage.show();

	}

	public void hardCodedData() {

		ElectionBoard = new GridPane();

		lblName.setStyle(
				"-fx-font-family: TRON; -fx-font-size: 20; -fx-font-style: italic; -fx-font-weight: bold; -fx-border-color: black; -fx-border-width: 0 0 2 0;");

		ElectionBoard.add(lblName, 0, 0);
		ElectionBoard.add(lblMenu, 0, 1);
		ElectionBoard.add(btnAddBallotBox, 0, 2);
		ElectionBoard.add(btnAddCitizen, 0, 3);
		ElectionBoard.add(btnAddParty, 0, 4);
		ElectionBoard.add(btnAddCandidate, 0, 5);
		ElectionBoard.add(btnShowAllBallotBoxes, 0, 6);
		ElectionBoard.add(btnShowAllCitizen, 0, 7);
		ElectionBoard.add(btnShowAllParties, 0, 8);
		ElectionBoard.add(btnElection, 0, 9);
		ElectionBoard.add(btnShowElectionResults, 0, 10);
		ElectionBoard.add(btnExit, 0, 11);

		ElectionBoard.setPadding(new Insets(10));
		ElectionBoard.setHgap(10);
		ElectionBoard.setVgap(10);

		scene = new Scene(ElectionBoard, 600, 500);
		theStage.setScene(scene);
		theStage.show();

	}

	public void uploadedData() {

		ElectionBoard = new GridPane();

		lblName.setStyle(
				"-fx-font-family: TRON; -fx-font-size: 20; -fx-font-style: italic; -fx-font-weight: bold; -fx-border-color: black; -fx-border-width: 0 0 2 0;");

		ElectionBoard.add(lblName, 0, 0);
		ElectionBoard.add(lblMenu, 0, 1);
		ElectionBoard.add(btnAddBallotBox, 0, 2);
		ElectionBoard.add(btnAddCitizen, 0, 3);
		ElectionBoard.add(btnAddParty, 0, 4);
		ElectionBoard.add(btnAddCandidate, 0, 5);
		ElectionBoard.add(btnShowAllBallotBoxes, 0, 6);
		ElectionBoard.add(btnShowAllCitizen, 0, 7);
		ElectionBoard.add(btnShowAllParties, 0, 8);
		ElectionBoard.add(btnElection, 0, 9);
		ElectionBoard.add(btnShowElectionResults, 0, 10);
		ElectionBoard.add(btnExit, 0, 11);

		ElectionBoard.setPadding(new Insets(10));
		ElectionBoard.setHgap(10);
		ElectionBoard.setVgap(10);

		scene = new Scene(ElectionBoard, 600, 500);
		theStage.setScene(scene);
		theStage.show();

	}

	public void addBallotBoxWindow() {
		btnAddBallotBox.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				GridPane secondaryWindow = new GridPane();
				lblBallotBoxType.setText("Choose a ballot box type");
				lblBallotBoxAddress.setText("Please write the address of the ballot box");

				tfBallotBoxAddress.setPromptText("what address is the ballot?");
				tfBallotBoxAddress.setMaxWidth(200);

				comboBoxTypesBallotBox.setValue("Citizen");

				Scene secondScene = new Scene(secondaryWindow, 450, 400);
				newWindow = new Stage();

				secondaryWindow.add(lblBallotBoxAddress, 0, 3);
				secondaryWindow.add(tfBallotBoxAddress, 0, 4);
				secondaryWindow.add(lblBallotBoxType, 0, 5);
				secondaryWindow.add(comboBoxTypesBallotBox, 0, 6);
				secondaryWindow.add(btnSubmitAddBallotBox, 1, 7);

				secondaryWindow.setPadding(new Insets(10));
				secondaryWindow.setHgap(15);
				secondaryWindow.setVgap(10);

				newWindow.setTitle("Add ballot box");
				newWindow.setAlwaysOnTop(true);
				newWindow.setScene(secondScene);

				newWindow.show();

			}
		});
	}

	@Override
	public void addCitizenOrSoldierWindow() {
		btnAddCitizen.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				GridPane secondaryWindow = new GridPane();

				lblMenu = new Label("you choosed to add citizen");

				lblCitizenOrSoldierName.setText("Please Enter a name:");
				lblCitizenBearthYear.setText("Please Enter year of bearth:");
				lblCitizenOrSoldierId.setText("Please Enter your ID:");
				lblIsQuarentin.setText("Is the Citizen is quarentin?:");
				lblNumOfSickDayes.setText("How many sick days?");

				chIsQuarentinYes.setToggleGroup(tgIsQuarentin);
				chIsQuarentinNo.setToggleGroup(tgIsQuarentin);

				tgIsQuarentin.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
					@Override
					public void changed(ObservableValue<? extends Toggle> arg0, Toggle arg1, Toggle arg2) {
						if (tgIsQuarentin.getSelectedToggle() == chIsQuarentinYes) {
							tfNumOfSickDays.setDisable(false);
						} else {
							tfNumOfSickDays.setDisable(true);
						}
					}
				});

				tfCitizenOrSoldierName.setPromptText("what is you Name?");
				tfCitizenOrSoldierId.setPromptText("what is you ID?");
				tfCitizenOrSoldierBearthOfYear.setPromptText("bearth of year?");
				tfNumOfSickDays.setDisable(false);

				tfCitizenOrSoldierName.setMaxWidth(300);
				tfCitizenOrSoldierId.setMaxWidth(300);
				tfCitizenOrSoldierBearthOfYear.setMaxWidth(300);

				Scene secondScene = new Scene(secondaryWindow, 400, 400);
				newWindow = new Stage();

				secondaryWindow.setPadding(new Insets(10));
				secondaryWindow.setHgap(15);
				secondaryWindow.setVgap(10);

				secondaryWindow.add(lblMenu, 0, 0);
				secondaryWindow.add(lblCitizenOrSoldierName, 0, 1);
				secondaryWindow.add(lblCitizenOrSoldierId, 0, 2);
				secondaryWindow.add(lblCitizenBearthYear, 0, 3);
				secondaryWindow.add(lblIsQuarentin, 0, 4);
				secondaryWindow.add(lblNumOfSickDayes, 0, 6);

				secondaryWindow.add(chIsQuarentinYes, 2, 4);
				secondaryWindow.add(chIsQuarentinNo, 2, 5);

				secondaryWindow.add(tfCitizenOrSoldierName, 2, 1);
				secondaryWindow.add(tfCitizenOrSoldierId, 2, 2);
				secondaryWindow.add(tfCitizenOrSoldierBearthOfYear, 2, 3);
				secondaryWindow.add(tfNumOfSickDays, 2, 6);

				secondaryWindow.add(btnSubmitAddCitizenOrSoldier, 2, 7);

				newWindow.setTitle("Add citizen");
				newWindow.setAlwaysOnTop(true);
				newWindow.setScene(secondScene);

				newWindow.show();
			}
		});
	}

	public void addPartyWindow() {

		btnAddParty.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				GridPane secondaryWindow = new GridPane();

				lblMenu = new Label("you choosed to add party");
				lblName = new Label("Enter a party name:");
				lblPartySide = new Label("Add please party side:");

				Scene secondScene = new Scene(secondaryWindow, 400, 250);
				newWindow = new Stage();

				comboBoxPartySides.setValue("RIGHT");

				secondaryWindow.setPadding(new Insets(10));
				secondaryWindow.setHgap(15);
				secondaryWindow.setVgap(10);

				secondaryWindow.add(lblMenu, 0, 0);
				secondaryWindow.add(lblName, 0, 1);
				secondaryWindow.add(lblPartySide, 0, 2);

				secondaryWindow.add(tfPartyName, 2, 1);

				secondaryWindow.add(comboBoxPartySides, 2, 2);

				secondaryWindow.add(btnSubmitAddParty, 2, 3);

				newWindow.setTitle("Add Party");
				newWindow.setAlwaysOnTop(true);
				newWindow.setScene(secondScene);

				newWindow.show();

			}
		});
	}

	public void addCandidateToPartyWindow() {

		btnAddCandidate.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				GridPane secondaryWindow = new GridPane();

				Scene secondScene = new Scene(secondaryWindow, 450, 400);
				newWindow = new Stage();

				lblMenu = new Label("you choosed to add candidate to a party");

				lblCandidateName.setText("Please Enter a name:");
				lblCandidateBearthOfYear.setText("Please Enter year of bearth:");
				lblCandidateId.setText("Please Enter your ID:");
				lblIsQuarentin.setText("Is the Citizen is quarentin?:");
				lblNumOfSickDayes.setText("How many sick days?");
				lblCandidateParty.setText("What is the Candidate party");

				chIsQuarentinYes.setToggleGroup(tgIsQuarentin);
				chIsQuarentinNo.setToggleGroup(tgIsQuarentin);

				tfCandidateName.setPromptText("candidate Name?");
				tfCandidateId.setPromptText("what is the candidate ID?");
				tfCandidateBearthOfYear.setPromptText("year of bearth?");
				tfCandidateParty.setPromptText("Candidate party");
				tfNumOfSickDays.setDisable(false);

				tfCandidateName.setMaxWidth(300);
				tfCandidateId.setMaxWidth(300);
				tfCandidateBearthOfYear.setMaxWidth(300);

				tgIsQuarentin.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
					@Override
					public void changed(ObservableValue<? extends Toggle> arg0, Toggle arg1, Toggle arg2) {
						if (tgIsQuarentin.getSelectedToggle() == chIsQuarentinYes) {
							tfCandidateNumOfSickDays.setDisable(false);
						} else {
							tfCandidateNumOfSickDays.setDisable(true);
						}
					}
				});

				secondaryWindow.add(lblMenu, 0, 0);
				secondaryWindow.add(lblCandidateName, 0, 1);
				secondaryWindow.add(lblCandidateId, 0, 2);
				secondaryWindow.add(lblCandidateBearthOfYear, 0, 3);
				secondaryWindow.add(lblCandidateParty, 0, 4);
				secondaryWindow.add(lblNumOfSickDayes, 0, 7);
				secondaryWindow.add(lblIsQuarentin, 0, 5);

				secondaryWindow.add(chIsQuarentinYes, 1, 5);
				secondaryWindow.add(chIsQuarentinNo, 1, 6);

				secondaryWindow.add(tfCandidateName, 1, 1);
				secondaryWindow.add(tfCandidateId, 1, 2);
				secondaryWindow.add(tfCandidateBearthOfYear, 1, 3);
				secondaryWindow.add(tfCandidateParty, 1, 4);
				secondaryWindow.add(tfCandidateNumOfSickDays, 1, 7);

				secondaryWindow.add(btnSubmitAddCandidateToParty, 1, 10);

				secondaryWindow.setPadding(new Insets(10));
				secondaryWindow.setHgap(15);
				secondaryWindow.setVgap(10);

				newWindow.setTitle("Add Candidate to a party");
				newWindow.setAlwaysOnTop(true);
				newWindow.setScene(secondScene);

				newWindow.show();
			}
		});
	}

	@Override
	public void whatParty() {

		lblPartyVote.setText(" What party you wanna vote?");

		GridPane secondaryWindow = new GridPane();

		Scene secondScene = new Scene(secondaryWindow, 300, 200);

		newWindow = new Stage();

		comboBoxAllParties.getSelectionModel().selectFirst();

		secondaryWindow.add(lblPartyVote, 0, 1);

		secondaryWindow.add(comboBoxAllParties, 0, 2);

		secondaryWindow.add(btnSubmitPartyVote, 2, 2);

		secondaryWindow.setPadding(new Insets(10));
		secondaryWindow.setHgap(15);
		secondaryWindow.setVgap(10);

		newWindow.setTitle("Voting");
		newWindow.setAlwaysOnTop(false);
		newWindow.setScene(secondScene);

		newWindow.showAndWait();

	}

	public boolean isVoting(String name) {

		Alert alert = new Alert(Alert.AlertType.INFORMATION);

		alert.setTitle("Election ");
		alert.setHeaderText("Hello, " + name + " do you wanna vote?");

		ButtonType buttonYes = new ButtonType("Yes");
		ButtonType buttonNo = new ButtonType("No");

		alert.getButtonTypes().setAll(buttonYes, buttonNo);

		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == buttonYes) {
			return true;
		} else {
			return false;
		}
	}

	public void closeTheElection() {
		theStage.close();
	}

	@Override
	public void onClickSaveFile(EventHandler<ActionEvent> e) {
		btnExit.setOnAction(e);
	}

	@Override
	public void onClickElectionResults(EventHandler<ActionEvent> e) {
		btnShowElectionResults.setOnAction(e);
	}

	@Override
	public void onClickSubmitPartyVote(EventHandler<ActionEvent> e) {
		btnSubmitPartyVote.setOnAction(e);
	}

	@Override
	public void yesVotingButton(EventHandler<ActionEvent> e) {
		btnYesVoting.setOnAction(e);
	}

	@Override
	public void onClickElectionRound(EventHandler<ActionEvent> e) {
		btnElection.setOnAction(e);
	}

	@Override
	public void onClickNoUploadFile(EventHandler<ActionEvent> e) {
		btnUploadFileNo.setOnAction(e);
	}

	@Override
	public void onClickYesUploadFile(EventHandler<ActionEvent> e) {
		btnUploadFileYes.setOnAction(e);
	}

	@Override
	public void onClickShowAllBallotBoxes(EventHandler<ActionEvent> e) {
		btnShowAllBallotBoxes.setOnAction(e);
	}

	@Override
	public void onClickShowAllCitizenOrSoldier(EventHandler<ActionEvent> e) {
		btnShowAllCitizen.setOnAction(e);
	}

	@Override
	public void onClickShowAllParties(EventHandler<ActionEvent> e) {
		btnShowAllParties.setOnAction(e);
	}

	@Override
	public void onClickSubmitAddCandidate(EventHandler<ActionEvent> e) {
		btnSubmitAddCandidateToParty.setOnAction(e);
	}

	@Override
	public void onClickSubmitAddBallotBox(EventHandler<ActionEvent> e) {
		btnSubmitAddBallotBox.setOnAction(e);
	}

	@Override
	public void onClickSubmitAddCitizen(EventHandler<ActionEvent> e) {
		btnSubmitAddCitizenOrSoldier.setOnAction(e);
	}

	@Override
	public void onClickSubmitAddParty(EventHandler<ActionEvent> e) {
		btnSubmitAddParty.setOnAction(e);
	}

	@Override
	public void onClickLoadFromFile(EventHandler<ActionEvent> e) {
		btnUploadFileYes.setOnAction(e);
	}

	@Override
	public void onClickAddBallotBox(EventHandler<ActionEvent> e) {
//		btnAddBallotBox.setOnAction(e);
	}

	@Override
	public void messageAlert(String msg) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.initStyle(StageStyle.UTILITY);
		alert.initOwner(newWindow);
		alert.setTitle("Message");
		alert.setHeaderText(null);
		alert.setContentText(msg);
		alert.showAndWait();

	}

	@Override
	public TextField getAddressBallotBox() {
		return tfBallotBoxAddress;
	}

	@Override
	public ComboBox<String> getComboBoxTypesBallotBox() {
		return comboBoxTypesBallotBox;
	}

	@Override
	public ComboBox<String> getComboBoxPartySide() {
		return comboBoxPartySides;
	}

	@Override
	public ComboBox<String> getComboBoxPartyVote() {
		return comboBoxAllParties;
	}

	@Override
	public void addPartyToComboBox(String partyName) {
		comboBoxAllParties.getItems().add(partyName);
	}

	public Stage getTheStage() {
		return newWindow;
	}

	@Override
	public void closeTheStage() {
		newWindow.close();
	}

	@Override
	public boolean getToggelGroup() {
		if (tgIsQuarentin.getSelectedToggle() == chIsQuarentinYes) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public TextField getPartyName() {
		return tfPartyName;
	}

	@Override
	public TextField getCitizenOrSoldierName() {
		return tfCitizenOrSoldierName;
	}

	@Override
	public TextField getCitizenOrSoldierId() {
		return tfCitizenOrSoldierId;
	}

	@Override
	public TextField getCitizenOrSoldierBearthOfYear() {
		return tfCitizenOrSoldierBearthOfYear;
	}

	@Override
	public TextField getNumOfSickDayes() {
		return tfNumOfSickDays;
	}

	@Override
	public TextField getCandidateNumOfSickDays() {
		return tfCandidateNumOfSickDays;
	}

	@Override
	public TextField getCandidateName() {
		return tfCandidateName;
	}

	@Override
	public TextField getCandidateId() {
		return tfCandidateId;
	}

	@Override
	public TextField getCandidateBearthOfYear() {
		return tfCandidateBearthOfYear;
	}

	@Override
	public TextField getCandidateParty() {
		return tfCandidateParty;
	}
}
