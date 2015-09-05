package ai;

import java.util.ArrayList;
import java.util.Random;

public class SimulateElementsTG {
	private static int numberOfNymphs =0;
	private static int maxCreat = 23;
	private static int quanta = 0;
	private static ArrayList<String> hand = new ArrayList();
	private static ArrayList<String> deck = new ArrayList();
	private static int numOfPillars = 0;
	private static int opponentLife = 100;
	private static int turn = 0;
	private static int activeNymphs;
	public static void main(String[] args) {
		
		
		for (int i = 0; i < 30; i++) {
			if(i<5){
				deck.add("tears");
			}
			else{
				deck.add("pillar");
			}	
		}
		shuffleArrayList(deck);
		System.out.println(deck);
		
		drawCard(7);
		System.out.println("drawn 7 cards");
		addPillars();
		System.out.println(numOfPillars);
		useNymphTears();
		System.out.println(numberOfNymphs);
		System.out.println(numOfPillars);
		System.out.println(quanta);
		int counter=0;
		ArrayList<Integer> vincitaTurno = new ArrayList<Integer>(0);
		for (int i = 0; i < 15; i++) {
			vincitaTurno.add(0);
		}
		for (int i = 0; i < 1000; i++) {
			//System.out.println(i+"partita");
			shuffleArrayList(deck);
			drawCard(7);
			addPillars();
			useNymphTears();
			
			damageOpponent();
			
			addQuanta();
			if(opponentLife<=0){
				vincitaTurno.set(0,vincitaTurno.get(0)+1);
			}
			//turni successivi
			for (int j = 1; j < 15;j++){
				playTurn();
				if(won()){
					vincitaTurno.set(j,vincitaTurno.get(j)+1);
					
					break;
				}
			}
			reset();
			
		}
		System.out.println(vincitaTurno);
		//43,4% prima ninfa in gioco
	}
	static void playTurn(){
		drawCard();
		activeNymphs=numberOfNymphs;
		addPillars();
		useNymphAbility();
		useNymphTears();
		damageOpponent();
		addQuanta();
	}
	static boolean won(){
		if(opponentLife<=0){
			return true;
		}
		return false;
	}
	
	static void reset(){
		numberOfNymphs =0;
		quanta = 0;
		numOfPillars = 0;
		hand = new ArrayList<String>();
		deck = new ArrayList<String>();
		for (int i = 0; i < 30; i++) {
			if(i<5){
				deck.add("tears");
			}
			else{
				deck.add("pillar");
			}	
		}
		opponentLife=100;
		activeNymphs=0;
		
	}
	static void damageOpponent(){
		opponentLife= opponentLife-numberOfNymphs*6;
	}
	
	static void useNymphTears(){
		if(numberOfNymphs==0){
			for (int i = 0; i < hand.size(); i++) {
				if(numberOfNymphs==0){
					if(hand.get(i).equalsIgnoreCase("tears")){
						if(quanta>=6){
							numOfPillars=numOfPillars-1;
							numberOfNymphs=numberOfNymphs+1;
							hand.remove(i);
							i--;
							quanta=quanta-6;
						}
					}
				}
			}
		}
	}
	static void useNymphAbility(){
		for(int i =0; i<activeNymphs;i++){
			if(quanta>=4){
				addNymph();
				quanta=quanta-4;
			}
		}
		activeNymphs=0;
	}
	static void addNymph(){
		numOfPillars-=1;
		numberOfNymphs+=1;
	}
	static void addPillars(){
		for (int i = 0; i < hand.size(); i++) {
			if(hand.get(i).equalsIgnoreCase("pillar")){
				numOfPillars=numOfPillars+1;
				quanta=quanta+1;
				hand.remove(i);
				i--;
			}
		}
	}
	static void addQuanta(){
		quanta=quanta+numOfPillars+1;
	}
	static void drawCard(){
		hand.add(deck.get(deck.size()-1));
		deck.remove(deck.size()-1);
	}
	static void drawCard(int n){
		for (int i = 0; i < n; i++) {
			drawCard();
		}
	}
	
	static void shuffleArrayList(ArrayList<String> ar)
	  {
	    Random rnd = new Random();
	    for (int i = ar.size()-1; i > 0; i--)
	    {
	      int index = rnd.nextInt(i + 1);
	      // Simple swap
	      String a = ar.get(index);
	      ar.set(index, ar.get(i));
	      ar.set(i, a);
	    }
	  }

}
