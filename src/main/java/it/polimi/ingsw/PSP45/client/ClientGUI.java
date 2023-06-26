package it.polimi.ingsw.PSP45.client;

import it.polimi.ingsw.PSP45.model.LightBlock;
import it.polimi.ingsw.PSP45.utils.GodName;
import it.polimi.ingsw.PSP45.utils.Service;
import it.polimi.ingsw.PSP45.utils.StateNumber;
import it.polimi.ingsw.PSP45.utils.cliMessage;
import it.polimi.ingsw.PSP45.gui.gui;
import javafx.application.Platform;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * @author Lorenzo Longaretti
 * @author Andrea Gerosa
 *
 * Class that represent a client.
 * This Version is for the gui.
 *
 */
public class ClientGUI{
	private String IP;
	private int port;
	private StateNumber state;
	private Service service = new Service(StateNumber.active, "default", "default");
	private ObjectOutputStream socketOut;
	private Boolean first = false;
	private ArrayList<LightBlock> whereToMove = new ArrayList<>();

	public ClientGUI(String IP, int port) {
		this.IP = IP;
		this.port = port;
		this.state = StateNumber.active;
	}
	private boolean active = true;

	public synchronized boolean isActive() {
		return active;
	}

	public synchronized void setActive(boolean active) {
		this.active = active;
	}

	public ArrayList<GodName> getGodName(){
		return service.getListGod();
	}

	public Service getService() {
		return service;
	}

	public StateNumber getState() {
		return state;
	}

	/**
	 * @author Lorenzo Longaretti
	 *
	 * Method that reply to the server
	 *
	 * @param service
	 * @throws IOException
	 */
	public synchronized void sendOutput(Service service) throws IOException {
		socketOut.writeObject(service);
		socketOut.flush();
	}


	/**
	 * @author Andrea Gerosa
	 * @author Lorenzo Longaretti
	 *
	 * Sets the name of the player and the number of players of the match.
	 * @param inputLine
	 * @param numero
	 */
	public void setNameAndNumber (String inputLine, int numero) {

		try {
			service.setResponseForName(inputLine, numero);
			socketOut.reset();
			sendOutput(service);
			state = StateNumber.active;

		} catch (IOException e) {
			System.err.println("Problems with name's sending");
		}
	}

	/**
	 * @author Lorenzo Longaretti
	 *
	 * Method that send to the server what god I choosed
	 *
	 * @param godname
	 */
	public void setGodName(GodName godname) {
		try {
			System.out.println("The chosen name is "+godname);
			service.replyChosenGod(godname);
			sendOutput(service);
			state = StateNumber.active;

		} catch (Exception e) {
			System.out.println(e + "error in choose god: I don't know ");
		}
	}

	/**
	 * @author Lorenzo Longaretti
	 *
	 * Method that send to the server what gods I choosed
	 *
	 * @param godname
	 */
	public void setGodsName(ArrayList<GodName> godname) {
		try {
			System.out.println("The chosen name is "+godname);
			service.replyChosenGod1(godname);
			sendOutput(service);
			state = StateNumber.active;

		} catch (Exception e) {
			System.out.println(e + "error in choose god: I don't know ");
		}
	}

	/**
	 * @author Lorenzo Longaretti
	 *
	 * Method that send to the server where to move
	 *
	 * @param lb
	 * @param id
	 * @throws IOException
	 */
	public void sendmoveGui(LightBlock lb, int id) throws IOException {
		service.receiveMoveMessage(lb,id);
		state = StateNumber.active;
		sendOutput(service);
	}

	/**
	 * @author Lorenzo Longaretti
	 *
	 * Method that send to the server where to build
	 *
	 * @param lb
	 * @throws IOException
	 */
	public void sendBuildGui(LightBlock lb) throws IOException {
		service.receiveBuildMessage(lb);
		sendOutput(service);
		state = StateNumber.active;
	}

	/**
	 * @author Lorenzo Longaretti
	 *
	 * Method that send to the server who will start
	 *
	 * @param dato
	 * @throws IOException
	 */
	public void sendFirstPlayer(int dato) throws IOException {
		service.receiveFirstPlayer(dato);
		sendOutput(service);
		state = StateNumber.active;
	}

	/**
	 * @author Lorenzo Longaretti
	 *
	 * Method that send to the server where to put the players
	 *
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @throws IOException
	 */
	public void sendCoordinate(int x1, int y1, int x2, int y2) throws IOException {
		service.setCoordindatePlayer(x1,y1,x2,y2);
		sendOutput(service);
		System.out.println("è in coordinate player");
		state = StateNumber.active;
	}

	/**
	 * @author Lorenzo Longaretti
	 *
	 * Method that set where I can move
	 *
	 * @param lb
	 */
	public ArrayList<LightBlock> setWhereToMove(LightBlock lb) {

		whereToMove.clear();
		ArrayList<LightBlock> helper = service.getAvailableCells();
		for (LightBlock lightBlock: helper) {
			if(lb.getIdWorker() == lightBlock.getIdWorker()){
				whereToMove.add(lightBlock);
			}
		}
		return whereToMove;
	}

	/**
	 *@author Lorenzo Longaretti
	 *
	 *  Method that read the input from server
	 *
	 * @param socketIn
	 * @return
	 */
	public Thread asyncReadFromSocket(final ObjectInputStream socketIn) {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					while(isActive()) {
						Service inputObject = new Service(StateNumber.active,"def", "def");
						try{   inputObject = (Service) socketIn.readObject();}
						catch (Exception e) {
							System.out.println(e.getMessage());
							setActive(false);
						}
						if(state == StateNumber.active) {
							if (inputObject.getType() == StateNumber.nameAndNumberRequest) {
								System.out.println(inputObject.getCodice());
								Platform.runLater(
										() -> {
											try {
												gui.changeToPlayerSetup();
											} catch (IOException e) {
												e.printStackTrace();
											}
										}
								);
								state = inputObject.getType();
							}
							else if (inputObject.getType() == StateNumber.moveState) {
								state = inputObject.getType();
								service = inputObject;
								service.getLightBoard().printBoard();

								for(int l = 0 ; l< service.getAvailableCells().size(); l++){
									System.out.print(service.getAvailableCells().get(l).getC().getX() + ","+ service.getAvailableCells().get(l).getC().getY() + "V" + service.getAvailableCells().get(l).getIdWorker() + "|");
								}
								System.out.println();
								if(!first){
									first = true;
									Platform.runLater(
											() -> {
												try {
													gui.changeToMain();
												} catch (IOException e) {
													e.printStackTrace();
												}
											}
									);
								}
								else{
									Platform.runLater(
											() -> {
												try {
													gui.goToMove();
												}catch (Error e){
													e.printStackTrace();
												}
											}
									);
								}

							}
							else if (inputObject.getType() == StateNumber.buildState) {
								state = inputObject.getType();
								service = inputObject;
								service.getLightBoard().printBoard();
								for(int l = 0 ; l< service.getAvailableCells2().size(); l++){
									System.out.print(service.getAvailableCells2().get(l).getC().getX() + ","+ service.getAvailableCells2().get(l).getC().getY() +"|");
								}
								System.out.println();
								if(!first){
									first = true;
									Platform.runLater(
											() -> {
												try {
													gui.changeToMain();
												} catch (IOException e) {
													e.printStackTrace();
												}
											}
									);
								}
								else{
									Platform.runLater(
											() -> {
												try {
													gui.goToBuild();
												}catch (Error e){
													System.err.println("null");
												}
											}
									);
								}
							}
							else if (inputObject.getType() == StateNumber.wait) {
								service = inputObject;
								Platform.runLater(
										() -> {
											try {
												gui.updateBoard();
											} catch (Exception e) {
												e.printStackTrace();
											}
										}
								);
							}
							else if (inputObject.getType() == StateNumber.setgod){
								state = inputObject.getType();
								service = inputObject;
								for(GodName godname : service.getListGod()){
									System.out.print(godname);
								}
								Platform.runLater(
										() -> {
											try {
												gui.changeToDivinity();
											} catch (IOException e) {
												e.printStackTrace();
											}
										}
								);

							}
							else if (inputObject.getType() == StateNumber.firstplayer){
								service = inputObject;
								Platform.runLater(
										() -> {
											try {
												gui.changeToFirstChoice();
											} catch (IOException e) {
												e.printStackTrace();
											}
										}
								);

							}
							else if (inputObject.getType() == StateNumber.setcoordinate){
								service = inputObject;
								Platform.runLater(
										() -> {
											try {
												gui.changeToCoordinateChoice();
											} catch (IOException e) {
												e.printStackTrace();
											}
										}
								);

							}
							else if (inputObject.getType() == StateNumber.sendhaswon || inputObject.getType() == StateNumber.sendhaslose  ){
								service = inputObject;
								Platform.runLater(
										() -> {
											gui.goToEnd();
										}
								);

								setActive(false);
							}
							else if (inputObject.getType() == StateNumber.disconnection){
								service = inputObject;
								Platform.runLater(
										() -> {
											try {
												gui.changeToDisconnect();
											} catch (IOException e) {
												e.printStackTrace();
											}
										}
								);
								setActive(false);
							}

						}
					}

					if( !(service.getType() == StateNumber.haswon || service.getType() == StateNumber.sendhaslose || service.getType() == StateNumber.disconnection || service.getType() == StateNumber.sendhaswon)){
						Platform.runLater(
								() -> {
									try {
										gui.changeToDisconnect();
									} catch (IOException e) {
										e.printStackTrace();
									}
								}
						);
					}
				}
				catch(Exception e) {
					System.err.println(e);
					setActive(false);
				}
			}
		});

		t.start();

		return t;


	}

	/**
	 * @author Lorenzo Longaretti
	 *
	 * Thread that send to the server the ping
	 *
	 * @return
	 */
	public Thread pingClient() {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				Service ping = new Service(StateNumber.pingreceive,"def","def");
				ping.receivePingMessage();
				try {
					while (isActive()) {
						sendOutput(ping);
						Thread.sleep(200);
					}

				}
				catch (Exception e) {
					System.err.println(e + ": "  + cliMessage.wrongInputMessage);
					}
			}
		});
		t.start();
		return t;
	}

	/**
	 * @author Lorenzo Longaretti
	 * Creates a new thread that sends the player's inputs from the gui to the server.
	 *
	 * @throws IOException
	 */
	public void run() throws IOException {
		Socket socket = new Socket(IP, port);
		System.out.println("Connection established");
		ObjectInputStream socketIn = new ObjectInputStream(socket.getInputStream());
		socketOut = new ObjectOutputStream(socket.getOutputStream());
		Scanner stdin = new Scanner(System.in);
		try {

			Thread t1 = asyncReadFromSocket(socketIn);
			Thread t2 = pingClient();
			t1.join();
			t2.join();
		}
		catch(InterruptedException | NoSuchElementException e) {
			System.out.println("Connection closed form the client side.");
		}
		finally {
			stdin.close();
			socketIn.close();
			socketOut.close();
			socket.close();
		}
	}
}


