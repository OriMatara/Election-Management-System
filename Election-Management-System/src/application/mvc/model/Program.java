//package application.mvc.model;
//
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//import java.time.LocalDate;
//import java.util.Date;
//import java.util.InputMismatchException;
//import java.util.Random;
//import java.util.Scanner;
//import java.util.Vector;
//
//import application.mvc.model.Parties.agenda;
//
//public class Program implements UIElections {
//
//	private static Scanner s = new Scanner(System.in);
//	private static ElectionManeger electionManeger = new ElectionManeger();
//
//	public static void main(String[] args) throws Exception {
//
//		Program program = new Program();
//
//		int upload = 0;
//
//		do {
//
//			System.out.println("do you want to upload a file information? ");
//			System.out.println("press 1 for yes/ press 2 for no");
//			try {
//				upload = s.nextInt();
//			} catch (InputMismatchException e) {
//				System.out.println();
//				s.nextLine();
//			}
//			if (upload == 1) {
//				ObjectInputStream inFile = new ObjectInputStream(new FileInputStream("electionFile.txt"));
//				ElectionManeger newElectionManeger = (ElectionManeger) inFile.readObject();
//				inFile.close();
//				program.menuFunc(newElectionManeger);
//			}
//			if (upload == 2) {
//				// Hard coded citizens
//				Citizen c1 = new Citizen("yosi", "123496789", 2002, false, 0);
//				Citizen c2 = new Citizen("david", "136456789", 2003, true, 7);
//				Citizen c3 = new Citizen("michael", "123498789", 1993, false, 0);
//				Citizen c4 = new Citizen("morgan", "123856789", 1999, true, 20);
//				Citizen c5 = new Citizen("yareh", "123456789", 2009, false, 0);
//
//				// Hard code parties
//
//				Parties p1 = new Parties("kadima", agenda.CENTER);
//				Parties p2 = new Parties("likud", agenda.RIGHT);
//				Parties p3 = new Parties("meretz", agenda.LEFT);
//
//				// Hard coded candidates
//				Candidate d1 = new Candidate("shimi", "223456789", 1995, false, 0, "likud");
//				Candidate d2 = new Candidate("koko", "423456789", 1996, true, 9, "kadima");
//				Candidate d3 = new Candidate("popo", "321654987", 1991, true, 39, "meretz");
//				Candidate d4 = new Candidate("yuval", "321987654", 1987, false, 0, "kadima");
//				Candidate d5 = new Candidate("ori", "963852741", 1994, false, 0, "likud");
//				Candidate d6 = new Candidate("josh", "369258147", 1989, true, 45, "kadima");
//
//				// Hard coded ballot boxes
//				BallotBox a1 = new BallotBox("frishman");
//				BallotBox a2 = new BallotBox("dizingof");
//				BallotBox a3 = new BallotBox("rotshild");
//				BallotBox a4 = new BallotBox("alenbi");
//
//				electionManeger.newElectionRound();
//				electionManeger.getCurrentRound().addCitizenOrSoldier(c1);
//				electionManeger.getCurrentRound().addCitizenOrSoldier(c2);
//				electionManeger.getCurrentRound().addCitizenOrSoldier(c3);
//				electionManeger.getCurrentRound().addCitizenOrSoldier(c4);
//				electionManeger.getCurrentRound().addCitizenOrSoldier(c5);
//
//				electionManeger.getCurrentRound().addParty(p1);
//				electionManeger.getCurrentRound().addParty(p2);
//				electionManeger.getCurrentRound().addParty(p3);
//
//				electionManeger.getCurrentRound().addCitizenOrSoldier((Citizen) d1);
//				electionManeger.getCurrentRound().addCandidateToParty(d1);
//				electionManeger.getCurrentRound().addCitizenOrSoldier((Citizen) d2);
//				electionManeger.getCurrentRound().addCandidateToParty(d2);
//				electionManeger.getCurrentRound().addCitizenOrSoldier((Citizen) d3);
//				electionManeger.getCurrentRound().addCandidateToParty(d3);
//				electionManeger.getCurrentRound().addCitizenOrSoldier((Citizen) d4);
//				electionManeger.getCurrentRound().addCandidateToParty(d4);
//				electionManeger.getCurrentRound().addCitizenOrSoldier((Citizen) d5);
//				electionManeger.getCurrentRound().addCandidateToParty(d5);
//				electionManeger.getCurrentRound().addCitizenOrSoldier((Citizen) d6);
//				electionManeger.getCurrentRound().addCandidateToParty(d6);
//
//				electionManeger.getCurrentRound().citizenBallotBoxes.add(a1);
//				System.out.println("The ballot successfully added!");
//
//				electionManeger.getCurrentRound().armyBallotBoxes.add(a2);
//				System.out.println("The ballot successfully added!");
//
//				electionManeger.getCurrentRound().coronaBallotBoxes.add(a3);
//				System.out.println("The ballot successfully added!");
//
//				electionManeger.getCurrentRound().coronaArmyBallotBoxes.add(a4);
//				System.out.println("The ballot successfully added!");
//
//				program.menuFunc(electionManeger);
//			}
//			if (upload != 1 && upload != 2) {
//				System.out.println("you chose wrong number, please try again");
//			}
//		} while (upload != 1 && upload != 2);
//
//		
//	}
//
//	public void menuFunc(ElectionManeger electionManeger) throws Exception {
//
//		Scanner s = new Scanner(System.in);
//		boolean exit = false;
//		int choise = 0;
//
//		ElectionRound currentElection = new ElectionRound();
//
//		do {
//			boolean validInput = false;
//			while (!validInput) {
//			try {
//			System.out.println(" hey you! please enter your choice:");
//			System.out.println("1 - Add ballot box");
//			System.out.println("2 - Add citizen");
//			System.out.println("3 - Add party");
//			System.out.println("4 - Add candidate for party");
//			System.out.println("5 - Show all the ballot boxes");
//			System.out.println("6 - Show all the citizens");
//			System.out.println("7 - Show all the parties");
//			System.out.println("8 - Elections");
//			System.out.println("9 - Show the election results");
//			System.out.println("10 - Exit");
//
//			choise = s.nextInt();
//			validInput = true;
//			}
//			catch(InputMismatchException e) {
//				System.out.println("invalid input, you must follow the instructions, try again\n");
//				s.nextLine();
//			}
//			}
//			currentElection = electionManeger.getCurrentRound();
//
//			switch (choise) {
//
//			case 1: {
//				addBallotBox(currentElection);
//			}
//				break;
//
//			case 2: {
//				addCitizenOrSoldier(currentElection);
//			}
//				break;
//
//			case 3: {
//				addParty(currentElection);
//			}
//				break;
//
//			case 4: {
//				addCandidateToParty(currentElection);
//			}
//				break;
//
//			case 5: {
//				showAllBalotBoxes(currentElection);
//			}
//				break;
//
//			case 6: {
//				showAllCitizens(currentElection);
//			}
//				break;
//
//			case 7: {
//				showAllParties(currentElection);
//			}
//				break;
//
//			case 8: {
//				votingFunc(currentElection);
//			}
//				break;
//
//			case 9: {
//				int choose = 0;
//				electionReasults(currentElection);
//				boolean isValidInput = false;
//				while (!isValidInput) {
//					try {
//					System.out.println("\ndo you want to do new election round?");
//					System.out.println("for yes press 1/ for no press 2");
//					choose = s.nextInt();
//					if (choose != 1 && choose != 2) {
//						System.out.println("you chose wrong number, please try again");
//						isValidInput = false;
//						continue;
//					}
//					isValidInput = true;
//					}
//					catch(InputMismatchException e) {
//						System.out.println("invalid input, you must follow the instructions, try again\n");
//						s.nextLine();
//					}
//				}
//
//				switch (choose) {
//				case 1: {
//					electionManeger.newElectionRound();
//				}
//					break;
//				case 2: {
//					System.out.println("you choose to stay in this election round");
//				}
//					break;
//				}
//			}
//
//				break;
//
//			case 10: {
//
//				ObjectOutputStream outFile = new ObjectOutputStream(new FileOutputStream("electionFile.txt"));
//				outFile.writeObject(electionManeger);
//				outFile.close();
//
//				exit = true;
//				System.out.println("you choose to exit,");
//				System.out.println("bye bye , thank you!");
//				s.close();
//				break;
//			}
//			}
//		} while (exit == false);
//	}
//
//	public void addBallotBox(ElectionRound currentElection) {
//		int choose = 0;
//		boolean isValidInput = false;
//		while (!isValidInput) {
//			try {
//			System.out.println(
//					"Choose your ballotbox:\nfor citizen ballot box press 1 \nfor army ballot box press 2 \nfor corona ballot box press 3\nfor corona army ballot box press 4");
//
//			choose = s.nextInt();
//			if (!(choose >= 1 && choose <= 4)) {
//				System.out.println("invalid number try again");
//				isValidInput = false;
//				continue;
//			}
//		isValidInput = true;
//			}
//			catch(InputMismatchException e) {
//				System.out.println("invalid input, you must follow the instructions, try again\n");
//				s.nextLine();
//			}
//		}
//		System.out.println("please enter the street adress: ");
//		String trashInput = s.nextLine();
//		String streetAdress = s.nextLine();
//		currentElection.addBallotBox(streetAdress, streetAdress);
//	}
//
//	public void addCitizenOrSoldier(ElectionRound currentElection) {
//		boolean isValidInput = false;
//		while (!isValidInput) {
//			try {
//				System.out.println("hey, you chose to add citizen/soldier");
//				System.out.println("please enter the data:");
//				System.out.println("name: ");
//				String trashInput = s.nextLine();
//				String name = s.nextLine();
//				System.out.println("id: ");
//				String id = s.next();
//				System.out.println("birth year: ");
//				int birthYear = s.nextInt();
//				System.out.println("for quarentin press true/false: ");
//				boolean isQuarentin = s.nextBoolean();
//
//				int numOfSickDays = 0;
//
//				if (isQuarentin == true) {
//					System.out.println("number of sick days: ");
//					numOfSickDays = s.nextInt();
//				}
//
//				Citizen temp = new Citizen(name, id, birthYear, isQuarentin, numOfSickDays);
//				currentElection.addCitizenOrSoldier(temp);
//
//				isValidInput = true;
//			} catch (InputMismatchException e) {
//				System.out.println("invalid input, you must follow the instructions, try again\n");
//			} catch (Exception e) {
//				System.out.println(e.getMessage());
//			}
//		}
//	}
//
//	public void addParty(ElectionRound currentElection) {
//		int choise = 0;
//		System.out.println("you chose to add party!");
//		System.out.println("please enter the name of your party: ");
//		String trashInput = s.nextLine();
//		String name = s.nextLine();
//		boolean isValidInput = false;
//		while (!isValidInput) {
//			try {
//
//			System.out.println("What is the party political side?\npress 1 to Left,\n2 to Center,\n3 to Right");
//			choise = s.nextInt();
//			if (!(choise >= 1 && choise <= 3)) {
//				System.out.println("invalid opinion please try again");
//				isValidInput = false;
//				continue;
//			}
//			isValidInput = true;
//			}
//			catch(InputMismatchException e) {
//				System.out.println("invalid input, you must follow the instructions, try again\n");
//				s.nextLine();
//			}
//		}
//		switch (choise) {
//		case 1: {
//			Parties party = new Parties(name, agenda.LEFT);
//			currentElection.addParty(party);
//		}
//			break;
//		case 2: {
//			Parties party = new Parties(name, agenda.CENTER);
//			currentElection.addParty(party);
//		}
//			break;
//		case 3: {
//			Parties party = new Parties(name, agenda.RIGHT);
//			currentElection.addParty(party);
//		}
//			break;
//		}
//	}
//
//	public void addCandidateToParty(ElectionRound currentElection) {
//		String trashInput;
//		boolean isValidInput = false;

//		while (!isValidInput) {

//			try {

//				System.out.println("hey, you chose to add candidate to a party");
//				System.out.println("please enter the data:");
//				System.out.println("name: ");
//				trashInput = s.nextLine();
//				String name = s.nextLine();
//				System.out.println("id: ");
//				String id = s.next();
//				System.out.println("birth year: ");
//				int birthYear = s.nextInt();
//				System.out.println("party: ");
//				trashInput = s.nextLine();
//				String party = s.nextLine();
//				System.out.println("for quarentin press true/false: ");
//				boolean isQuarentin = s.nextBoolean();
//
//				int numOfSickDays = 0;
//
//				if (isQuarentin == true) {
//					System.out.println("number of sick days: ");
//					numOfSickDays = s.nextInt();
//				}
//
//				Citizen temp = new Citizen(name, id, birthYear, isQuarentin, numOfSickDays);
//				currentElection.addCitizenOrSoldier(temp);
//				Candidate newCandidate = new Candidate(name, id, birthYear, isQuarentin, numOfSickDays, party);
//				currentElection.addCandidateToParty(newCandidate);
//
//				isValidInput = true;
//			} catch (InputMismatchException e) {
//				System.out.println("invalid input, you must follow the instructions, try again\n");
//			} catch (Exception e) {
//				System.out.println(e.getMessage());
//			}
//		}
//	}
//
//	public void showAllBalotBoxes(ElectionRound currentElection) {
//		currentElection.showAllBalotBoxes();
//	}
//
//	public void showAllCitizens(ElectionRound currentElection) {
//		currentElection.showAllCitizens();
//	}
//
//	public void showAllParties(ElectionRound currentElection) {
//		currentElection.showAllParties();
//	}
//
//	public void votingFunc(ElectionRound currentElection) {
//		int answer1 = 0, answer2 = 0;
//
//		System.out.println("you choosed to start the elections: ");
//		System.out.println("here are the citizen list:");
//		for (int i = 0; i < currentElection.getCitizenVotersList().getCurrentSize(); i++) {
//			boolean isValidInput = false;
//			while (!isValidInput) {
//				try {
//				System.out.println("hello " + currentElection.getCitizenVotersList().get(i).getName());
//				System.out.println("Do you wanna vote? : press 1 for Yes/press 2 for No");
//				answer1 = s.nextInt();
//				if (answer1 != 1 && answer1 != 2) {
//					System.out.println("you chose wrong number, please try again");
//					isValidInput = false;
//					continue;
//				}
//				isValidInput = true;
//				}
//				catch(InputMismatchException e) {
//					System.out.println("invalid input, you must follow the instructions, try again\n");
//					s.nextLine();
//				}
//			}
//			if (answer1 == 1) {
//				isValidInput = false;
//				while (!isValidInput) {
//					try {
//
//					System.out.println("What party you wanna vote?: Press the number you choose");
//					for (int j = 0; j < currentElection.getAllParties().size(); j++) {
//						System.out.println(j + 1 + ") " + currentElection.getAllParties().elementAt(j).getName());
//
//					}
//					answer2 = s.nextInt();
//					if (answer2 < 1 || answer2 > currentElection.getAllParties().size()) {
//						System.out.println("you chose wrong number, please try again");
//						isValidInput = false;
//						continue;
//					}
//					isValidInput = true;
//					}
//					catch(InputMismatchException e) {
//						System.out.println("invalid input, you must follow the instructions, try again\n");
//						s.nextLine();
//					}
//				}
//				currentElection.votingFunc(currentElection.getCitizenVotersList().get(i));
//				currentElection.getAllParties().elementAt(answer2 - 1).setPartyVoterCounter();
//				currentElection.getCitizenVotersList().get(i)
//						.setChooseParty(currentElection.getAllParties().elementAt(answer2 - 1).getName());
//				for (int k = 0; k < currentElection.getCitizenBallotBoxes().size(); k++) {
//					if (currentElection.getCitizenVotersList().get(i).getBallotBox() == currentElection
//							.getCitizenBallotBoxes().elementAt(k).getSerialNumber()) {
//						currentElection.getCitizenBallotBoxes().elementAt(k).setActuallyVoted();
//					}
//				}
//				continue;
//			}
//			if (answer1 == 2) {
//				System.out.println("you chose not to vote");
//				currentElection.votingFunc(currentElection.getCitizenVotersList().get(i));
//			}
//
//		}
//
//		System.out.println("here are the corona citizen list:");
//		for (int i = 0; i < currentElection.getCoronaVotersList().size(); i++) {
//			boolean isValidInput = false;
//			while (!isValidInput) {
//				try {
//				System.out.println("hello " + currentElection.getCoronaVotersList().get(i).getName());
//				System.out.println("Do you wanna vote? : press 1 for Yes/press 2 for No");
//				answer1 = s.nextInt();
//				if (answer1 != 1 && answer1 != 2) {
//					System.out.println("you chose wrong number, please try again");
//					isValidInput = false;
//					continue;
//				}
//				isValidInput = true;
//				}
//				catch(InputMismatchException e) {
//					System.out.println("invalid input, you must follow the instructions, try again\n");
//					s.nextLine();
//				}
//			}
//			if (answer1 == 1) {
//				isValidInput = false;
//				while (!isValidInput) {
//					try {
//					System.out.println("What party you wanna vote?: Press the number you choose");
//					for (int j = 0; j < currentElection.getAllParties().size(); j++) {
//						System.out.println(j + 1 + ") " + currentElection.getAllParties().elementAt(j).getName());
//
//					}
//					answer2 = s.nextInt();
//					if (answer2 < 1 || answer2 > currentElection.getAllParties().size()) {
//						System.out.println("you chose wrong number, please try again");
//						isValidInput = false;
//						continue;
//					}
//					isValidInput = true;
//					}
//					catch(InputMismatchException e) {
//						System.out.println("invalid input, you must follow the instructions, try again\n");
//						s.nextLine();
//					}
//				}
//				currentElection.votingFunc(currentElection.getCoronaVotersList().get(i));
//
//				for (int k = 0; k < currentElection.getCoronaBallotBoxes().size(); k++) {
//					if (currentElection.getCoronaVotersList().get(i).getBallotBox() == currentElection
//							.getCoronaBallotBoxes().elementAt(k).getSerialNumber()) {
//						if (currentElection.getCoronaBallotBoxes().elementAt(k).hasProtection() == true) {
//							currentElection.getAllParties().elementAt(answer2 - 1).setPartyVoterCounter();
//							currentElection.getCoronaVotersList().get(i)
//									.setChooseParty(currentElection.getAllParties().elementAt(answer2 - 1).getName());
//							currentElection.getCoronaBallotBoxes().elementAt(k).setActuallyVoted();
//						}
//					}
//				}
//			}
//			if (answer1 == 2) {
//				System.out.println("you chose not to vote");
//				currentElection.votingFunc(currentElection.getCoronaVotersList().get(i));
//			}
//
//		}
//
//		System.out.println("here are the soldier list:");
//
//		for (int i = 0; i < currentElection.getSoldierList().size(); i++) {
//			boolean isValidInput = false;
//			while (!isValidInput) {
//				try {
//				System.out.println("hello " + currentElection.getSoldierList().elementAt(i).getName());
//				System.out.println("Do you wanna vote? : press 1 for Yes/press 2 for No");
//				answer1 = s.nextInt();
//				if (answer1 != 1 && answer1 != 2) {
//					System.out.println("you chose wrong number, please try again");
//					isValidInput = false;
//					continue;
//				}
//				isValidInput = true;
//				}
//				catch(InputMismatchException e) {
//					System.out.println("invalid input, you must follow the instructions, try again\n");
//					s.nextLine();
//				}
//			}
//			if (answer1 == 1) {
//				isValidInput = false;
//				while (!isValidInput) {
//					try {
//					System.out.println("What party you wanna vote?: Press the number you choose");
//					for (int j = 0; j < currentElection.getAllParties().size(); j++) {
//						System.out.println(j + 1 + ") " + currentElection.getAllParties().elementAt(j).getName());
//
//					}
//					answer2 = s.nextInt();
//					if (answer2 < 1 || answer2 > currentElection.getAllParties().size()) {
//						System.out.println("you chose wrong number, please try again");
//						isValidInput = false;
//						continue;
//					}
//					isValidInput = true;
//					}
//					catch(InputMismatchException e) {
//						System.out.println("invalid input, you must follow the instructions, try again\n");
//						s.nextLine();
//					}
//				}
//				currentElection.votingFunc(currentElection.getSoldierList().elementAt(i));
//				currentElection.getAllParties().elementAt(answer2 - 1).setPartyVoterCounter();
//				currentElection.getSoldierList().elementAt(i)
//						.setChooseParty(currentElection.getAllParties().elementAt(answer2 - 1).getName());
//				for (int k = 0; k < currentElection.getArmyBallotBoxes().size(); k++) {
//					if (currentElection.getSoldierList().elementAt(i).getBallotBox() == currentElection
//							.getArmyBallotBoxes().elementAt(k).getSerialNumber()) {
//						currentElection.getArmyBallotBoxes().elementAt(k).setActuallyVoted();
//					}
//				}
//				continue;
//			}
//			if (answer1 == 2) {
//				System.out.println("you chose not to vote");
//				currentElection.votingFunc(currentElection.getSoldierList().elementAt(i));
//			}
//
//		}
//
//		System.out.println("here are the corona soldier list:");
//		for (int i = 0; i < currentElection.getCoronaSoldierList().size(); i++) {
//			boolean isValidInput = false;
//			while (!isValidInput) {
//				try {
//				System.out.println("hello " + currentElection.getCoronaSoldierList().get(i).getName());
//				System.out.println("Do you wanna vote? : press 1 for Yes/press 2 for No");
//				answer1 = s.nextInt();
//				if (answer1 != 1 && answer1 != 2) {
//					System.out.println("you chose wrong number, please try again");
//					isValidInput = false;
//					continue;
//				}
//				isValidInput = true;
//				}
//				catch(InputMismatchException e) {
//					System.out.println("invalid input, you must follow the instructions, try again\n");
//					s.nextLine();
//				}
//			}
//			if (answer1 == 1) {
//				isValidInput = false;
//				while (!isValidInput) {
//					try {
//					System.out.println("What party you wanna vote?: Press the number you choose");
//					for (int j = 0; j < currentElection.getAllParties().size(); j++) {
//						System.out.println(j + 1 + ") " + currentElection.getAllParties().elementAt(j).getName());
//
//					}
//					answer2 = s.nextInt();
//					if (answer2 < 1 || answer2 > currentElection.getAllParties().size()) {
//						System.out.println("you chose wrong number, please try again");
//						isValidInput = false;
//						continue;
//					}
//					isValidInput = true;
//					}
//					catch(InputMismatchException e) {
//						System.out.println("invalid input, you must follow the instructions, try again\n");
//						s.nextLine();
//					}
//				}
//				currentElection.votingFunc(currentElection.getCoronaSoldierList().get(i));
//
//				for (int k = 0; k < currentElection.getCoronaArmyBallotBoxes().size(); k++) {
//					if (currentElection.getCoronaSoldierList().get(i).getBallotBox() == currentElection
//							.getCoronaArmyBallotBoxes().elementAt(k).getSerialNumber()) {
//						if (currentElection.getCoronaArmyBallotBoxes().elementAt(k).hasProtection() == true) {
//							currentElection.getAllParties().elementAt(answer2 - 1).setPartyVoterCounter();
//							currentElection.getCoronaSoldierList().get(i)
//									.setChooseParty(currentElection.getAllParties().elementAt(answer2 - 1).getName());
//							currentElection.getCoronaArmyBallotBoxes().elementAt(k).setActuallyVoted();
//						}
//					}
//				}
//			}
//			if (answer1 == 2) {
//				System.out.println("you chose not to vote");
//				currentElection.votingFunc(currentElection.getCoronaSoldierList().get(i));
//			}
//
//		}
//	}
//
//	public void electionReasults(ElectionRound currentElection) {
//
//		System.out.println("you choosed to see the election results");
//		System.out.println("here are the number of votes from each ballot box to each party: ");
//		currentElection.electionReasults();
//	}
//
//}