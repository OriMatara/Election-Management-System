//package application.mvc.model;
//
//import java.io.Serializable;
//import java.util.Vector;
//
//import application.mvc.view.ViewModelInterface;
//import javafx.stage.Stage;
//
//public class ElectionManeger implements Serializable, ModelInterface {
//
//	public Vector<ElectionRound> electionList;
//
//	public ElectionManeger() {
//		electionList = new Vector<ElectionRound>();
//	}
//
//	public void newElectionRound() {
//		System.out.println("amazing here is your new election round: ");
//		electionList.add(new ElectionRound());
//	}
//
//	public ElectionRound getCurrentRound() {
//		return electionList.elementAt(electionList.size() - 1);
//	}
//
//	@Override
//	public boolean addBallotBox(String ballotBoxAddress, String type) {
//		return false;
//	}
//
//	@Override
//	public void loadInfo(ViewModelInterface view) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public Vector<BallotBox> getCitizenBallotBoxes() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Vector<BallotBox> getCoronaArmyBallotBoxes() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Vector<BallotBox> getArmyBallotBoxes() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Vector<BallotBox> getCoronaBallotBoxes() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public boolean addCitizenOrSoldier(Citizen c) throws Exception {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public void showMessage(String str) {
//		// TODO Auto-generated method stub
//		
//	}
//
//}