package it.polimi.ingsw.PSP45.server;

import it.polimi.ingsw.PSP45.model.Coordinate;
import it.polimi.ingsw.PSP45.model.LightBoard;
import it.polimi.ingsw.PSP45.utils.GodName;

import java.util.ArrayList;

/**
 * @author Lorenzo Longaretti
 *
 * Class that stores the informations during the creation of the match.
 *
 */
public class GodLobby_Class {
	private ArrayList<WaitingConnectionClass> list = new ArrayList<>();
	private ClientConnection c1;
	private ClientConnection c2;
	private ClientConnection c3;
	private GodName c1god;
	private GodName c2god;
	private GodName c3god;
	private int usage;
	private ArrayList<GodName> listgodnotused = new ArrayList<>();
	private ArrayList<GodName> listgodused = new ArrayList<>();
	private LightBoard lightBoard = new LightBoard();
	private Coordinate firstworker_firstPlayerCoordinate ;
	private Coordinate secondworker_firstPlayerCoordinate;
	private Coordinate firstworker_secondPlayerCoordinate;
	private Coordinate secondworker_secondPlayerCoordinate;
	private Coordinate firstworker_thirdPlayerCoordinate;
	private Coordinate secondworker_thirdPlayerCoordinate;
	private ArrayList<Coordinate> coordinates = new ArrayList<>();
	private int firstPlayer = 4;
	private ClientConnection toPutCoordinate;
	private int toPutCoordinateInt = 0;
	int tosend = 0;
	private ArrayList<String> playersNames = new ArrayList<>();

	/**
	 * Class constructor
	 *
	 * @param list
	 * @param c1
	 * @param c2
	 * @param c3
	 */
	public GodLobby_Class(ArrayList<WaitingConnectionClass> list, ClientConnection c1, ClientConnection c2, ClientConnection c3) {
		for(WaitingConnectionClass waitingConnectionClass : list){
		this.list.add(waitingConnectionClass.clone());
		}
		this.c1 = c1;
		this.c2 = c2;
		this.c3 = c3;
		this.usage =0;
		listgodnotused.add(GodName.apollo);
		listgodnotused.add(GodName.minotaur);
		listgodnotused.add(GodName.hephaestus);
		listgodnotused.add(GodName.athena);
		listgodnotused.add(GodName.pan);
		listgodnotused.add(GodName.demeter);
		listgodnotused.add(GodName.artemis);
		listgodnotused.add(GodName.atlas);
		listgodnotused.add(GodName.prometheus);
	}

	public int getFirstPlayer() {
		return firstPlayer;
	}

	public void setFirstPlayer(int firstPlayer) {
		this.firstPlayer = firstPlayer;
	}

	public ClientConnection getToPutCoordinate() {
		return toPutCoordinate;
	}

	public void setToPutCoordinate(ClientConnection toPutCoordinate) {
		this.toPutCoordinate = toPutCoordinate;
	}

	public int getToPutCoordinateInt() {
		return toPutCoordinateInt;
	}

	public void setToPutCoordinateInt(int toPutCoordinateInt) {
		this.toPutCoordinateInt = toPutCoordinateInt;
	}

	public int getTosend() {
		return tosend;
	}

	public void setTosend(int tosend) {
		this.tosend = tosend;
	}

	public ArrayList<Coordinate> getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(ArrayList<Coordinate> coordinates) {
		this.coordinates = coordinates;
	}

	public Coordinate getFirstworker_firstPlayerCoordinate() {
		return firstworker_firstPlayerCoordinate;
	}

	public void setFirstworker_firstPlayerCoordinate(Coordinate firstworker_firstPlayerCoordinate) {
		this.firstworker_firstPlayerCoordinate = firstworker_firstPlayerCoordinate;
	}

	public Coordinate getSecondworker_firstPlayerCoordinate() {
		return secondworker_firstPlayerCoordinate;
	}

	public void setSecondworker_firstPlayerCoordinate(Coordinate secondworker_firstPlayerCoordinate) {
		this.secondworker_firstPlayerCoordinate = secondworker_firstPlayerCoordinate;
	}

	public Coordinate getFirstworker_secondPlayerCoordinate() {
		return firstworker_secondPlayerCoordinate;
	}

	public void setFirstworker_secondPlayerCoordinate(Coordinate firstworker_secondPlayerCoordinate) {
		this.firstworker_secondPlayerCoordinate = firstworker_secondPlayerCoordinate;
	}

	public Coordinate getSecondworker_secondPlayerCoordinate() {
		return secondworker_secondPlayerCoordinate;
	}

	public void setSecondworker_secondPlayerCoordinate(Coordinate secondworker_secondPlayerCoordinate) {
		this.secondworker_secondPlayerCoordinate = secondworker_secondPlayerCoordinate;
	}

	public Coordinate getFirstworker_thirdPlayerCoordinate() {
		return firstworker_thirdPlayerCoordinate;
	}

	public void setFirstworker_thirdPlayerCoordinate(Coordinate firstworker_thirdPlayerCoordinate) {
		this.firstworker_thirdPlayerCoordinate = firstworker_thirdPlayerCoordinate;
	}

	public Coordinate getSecondworker_thirdPlayerCoordinate() {
		return secondworker_thirdPlayerCoordinate;
	}

	public void setSecondworker_thirdPlayerCoordinate(Coordinate secondworker_thirdPlayerCoordinate) {
		this.secondworker_thirdPlayerCoordinate = secondworker_thirdPlayerCoordinate;
	}

	public LightBoard getLightBoard() {
		return lightBoard;
	}

	public void setLightBoard(LightBoard lightBoard) {
		this.lightBoard = lightBoard;
	}

	public ArrayList<WaitingConnectionClass> getList() {
		return list;
	}

	public ClientConnection getC1() {
		return c1;
	}

	public ClientConnection getC2() {
		return c2;
	}

	public ClientConnection getC3() {
		return c3;
	}

	/**
	 * Updates the usage value.
	 *
	 */
	public void Updateusage(){
		System.out.println("size list usage" + list.size());
		if(list.size()==2 && usage == 1){
			usage = 3;
		}
		else{
			usage++;
		}
	}

	public int getUsage() {
		return usage;
	}

	public ArrayList<GodName> getListgodnotused() {
		return listgodnotused;
	}

	public void setC1GodName(GodName godName) {
		this.c1god = godName;
		listgodused.add(godName);
	}

	public void setC2GodName(GodName godName) {
		this.c2god = godName;
		listgodused.add(godName);
	}

	public void setC3GodName(GodName godName) {
		this.c3god = godName;
		listgodused.add(godName);
	}

	public GodName getC1god() {
		return c1god;
	}

	public GodName getC2god() {
		return c2god;
	}

	public GodName getC3god() {
		return c3god;
	}

	public void removeGodName(GodName godName) {
		listgodnotused.remove(godName);
	}

	public ArrayList<GodName> getListgodused() {
		return listgodused;
	}


	public void setListgodused(ArrayList<GodName> listgodused) {
		this.listgodused = listgodused;
	}

	public void setListgodnotused(ArrayList<GodName> listgodnotused) {
		this.listgodnotused = listgodnotused;
	}

	public ArrayList<String> getPlayersNames() {
		return playersNames;
	}

	public void setPlayersNames(ArrayList<String> playersNames) {
		this.playersNames = playersNames;
	}

	/**
	 * Sorts the divinities.
	 *
	 */
	public void sort(){
		ArrayList<GodName> help1 = new ArrayList<>();
		if(listgodused.size()== 2){
			help1.add(listgodused.get(1));
			help1.add(listgodused.get(0));
		}
		else{
			help1.add(listgodused.get(2));
			help1.add(listgodused.get(0));
			help1.add(listgodused.get(1));
		}
		listgodused.clear();
		listgodused = help1;

		ArrayList<WaitingConnectionClass> help2 = new ArrayList<>();
		if(list.size()== 2){
			help2.add(list.get(0));
			help2.add(list.get(1));
		}
		else{
			help2.add(list.get(0));
			help2.add(list.get(1));
			help2.add(list.get(2));
		}
		list.clear();
		list = help2;
	}
}
