package it.polimi.ingsw.PSP45.client;
import it.polimi.ingsw.PSP45.model.LightBlock;
import it.polimi.ingsw.PSP45.server.WaitingConnectionClass;
import it.polimi.ingsw.PSP45.utils.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * @author Andrea Gerosa
 * @author Lorenzo Longaretti
 *
 * Class that represent a client.
 * This version is for the cli.
 *
 */
public class Client {
	private String IP;
	private int port;
	private StateNumber state;
	private Service service = new Service(StateNumber.active, "default", "default");
	private ObjectOutputStream socketOut;
	Scanner stdin;
	boolean newMatch;

	/**
	 * @author Andrea Gerosa
	 *
	 * Class constructor.
	 *
	 * @param IP
	 * @param port
	 */
	public Client(String IP, int port) {
		this.IP = IP;
		this.port = port;
		this.state = StateNumber.active;
		this.stdin = new Scanner(System.in);
	}
	private boolean active = true;
	public synchronized boolean isActive() {
		return active;
	}
	public synchronized void setActive(boolean active) {
		this.active = active;
	}

	public boolean isNewMatch() {
		return newMatch;
	}

	/*public void setStdin(Scanner stdin) {
		this.stdin = stdin;
	}*/

	/**
	 * @author Andrea Gerosa
	 *
	 * Method that writes the service object on the outputStream and then slush the stream.
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
	 *
	 *
	 * @param str input
	 * @return true if the input is an integer, false otherwise.
	 */
	public boolean isInteger(String str) {
		try {
			Integer.parseInt(str);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}

	/**
	 * @author Andrea Gerosa
	 *
	 * @param str inputs
	 * @return true if every input is an integer, false otherwise.
	 */
	public boolean areAllIntegers(String[] str) {
		int helper = 0;
		for(String string : str) {
			if(isInteger(string)) {
				helper ++;
			}
		}

		return helper == str.length;
	}


	/**
	 * @author Andrea Gerosa
	 * @author Lorenzo Longaretti
	 *
	 * Creates a new thread that reads the messages from the inputStream, recognizes the service and set the state of the client.
	 *
	 * @param socketIn the stream in input
	 * @return the new created thread
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
							System.out.println(active);
							//setActive(true);
							if (inputObject.getType() == StateNumber.nameAndNumberRequest) {
								System.out.println(inputObject.getCodice());
								state = inputObject.getType();
							}
							else if (inputObject.getType() == StateNumber.setgod){
								state = inputObject.getType();
								service = inputObject;
								System.out.println(inputObject.getCodice());
								for(GodName godname : inputObject.getListGod()){
									System.out.print(godname);
									System.out.println();
								}
							}
							else if(inputObject.getType() == StateNumber.firstplayer) {
								state = inputObject.getType();
								service = inputObject;
								System.out.println(inputObject.getCodice());

							}
							else if (inputObject.getType() == StateNumber.setcoordinate) {
								state = inputObject.getType();
								service = inputObject;
								System.out.println(inputObject.getCodice());
								System.out.println();
								service.getLightBoard().printBoard();
							}
							else if (inputObject.getType() == StateNumber.moveState) {
								state = inputObject.getType();
								service = inputObject;

								service.getLightBoard().printBoard();

								System.out.println(inputObject.getCodice());
								System.out.println("Available cells for both your workers:");
								for (int l = 0; l < service.getAvailableCells().size(); l++) {
									System.out.print(service.getAvailableCells().get(l).getC().getX() + "," + service.getAvailableCells().get(l).getC().getY() + "|");
								}
								System.out.println();
							}
							else if (inputObject.getType() == StateNumber.buildState) {
								state = inputObject.getType();
								service = inputObject;
								service.getLightBoard().printBoard();
								System.out.println(inputObject.getCodice());
								for (int l = 0; l < service.getAvailableCells2().size(); l++) {
									System.out.print(service.getAvailableCells2().get(l).getC().getX() + "," + service.getAvailableCells2().get(l).getC().getY() + "|");
								}
								System.out.println();
							}
							else if (inputObject.getType() == StateNumber.wait) {
								System.out.println(inputObject.getCodice());
								inputObject.getLightBoard().printBoard();
							}
							else if (inputObject.getType() == StateNumber.sendhaswon || inputObject.getType() == StateNumber.sendhaslose) {
								state = inputObject.getType();
								System.out.println(inputObject.getCodice());
								System.out.println(cliMessage.endGameMessage);
							}
							else if(inputObject.getType() == StateNumber.disconnection) {
								state = inputObject.getType();
								System.out.println(inputObject.getCodice());
							}

						}
					}
				}
				catch(Exception e) {
					setActive(false);
				}
			}
		});
		t.start();
		return t;
	}


	/**
	 * @author Andrea Gerosa
	 *
	 * Creates a new thread that reads a line from the standard input, checks the client's state and calls
	 * the most appropriate method, which will perform the correct action.
	 *
	 * @param stdin standard input
	 * @return the new created thread
	 */
	public Thread asyncWriteFromSocket(final Scanner stdin) {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {

				try {
					while (isActive()) {
						String inputLine;

						/*if(stdin.hasNext()) {
							inputLine = stdin.nextLine();
						}
						else {
							stdin.reset();
							inputLine = stdin.nextLine();
						}*/
						stdin.reset();
						inputLine = stdin.nextLine();


						if (state == StateNumber.nameAndNumberRequest) {
							setNameAndNumber(inputLine, stdin);
						}

						else if (state == StateNumber.moveState) {
							sendMove(inputLine, stdin);
						}

						else if (state == StateNumber.buildState) {
							sendBuild(inputLine, stdin);
						}

						else if (state == StateNumber.setgod)  {
							setGodName(inputLine, stdin);
						}
						else if (state == StateNumber.firstplayer) {
							setFirstPlayer(inputLine, stdin);
						}
						else if (state == StateNumber.setcoordinate) {
							setWorkerPositions(inputLine, stdin);
						}
						else if (state == StateNumber.sendhaswon || state == StateNumber.sendhaslose) {
							endGameResponse(inputLine, stdin);
						}
						else if (state == StateNumber.disconnection) {
							disconnectionResponse(inputLine, stdin);
						}

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
	 * Thread that sends ping messages to the server.
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
	 * @author Andrea Gerosa
	 * Creates the socket and the streams; handles the threads.
	 *
	 * @throws IOException
	 */
	public void run() throws IOException {
		Socket socket = new Socket(IP, port);
		System.out.println("Connection established");
		System.out.println();
		ObjectInputStream socketIn = new ObjectInputStream(socket.getInputStream());
		socketOut = new ObjectOutputStream(socket.getOutputStream());

		System.out.println(Title.title);

		try {

			Thread t1 = asyncWriteFromSocket(stdin);
			Thread t0 = asyncReadFromSocket(socketIn);
			Thread t2 = pingClient();
			t0.join();
			t1.join();
			t2.join();
		}
		catch(InterruptedException | NoSuchElementException e) {
			System.out.println("Connection closed form the client side.");
		}
		finally {
			socketIn.close();
			socketOut.close();
			socket.close();
		}
	}


	/**
	 * @author Andrea Gerosa
	 * Sets the name of the player and the number of players of the match.
	 *
	 * @param inputLine line of text from stdin
	 * @param stdin standard input
	 */
	public void setNameAndNumber (String inputLine, Scanner stdin) {
		String[] inputs = new String[2];
		inputs[0] = inputLine;

		while(inputs[0].equals("\n") || inputs[0].equals("")) {
			System.out.println("Your name must be at least one-character long!");
			stdin.reset();
			inputLine = stdin.nextLine();
			inputs[0] = inputLine;
		}

		System.out.println("Would you like to play a 2-player or a 3-player game?");

		stdin.reset();
		inputLine = stdin.nextLine();
		inputs[1] = inputLine;

		integerControl(inputLine, inputs, stdin);
		twoOrThreeControl(inputLine, inputs, stdin);

		try {
			if (Integer.parseInt(inputs[1]) < 2 || Integer.parseInt(inputs[1]) > 3) {
				throw new IllegalArgumentException();
			} else {
				service.setResponseForName(inputs[0], Integer.parseInt(inputs[1]));
				sendOutput(service);
				state = StateNumber.active;
			}
		} catch (Exception e) {
			System.out.println(e + ": you can only play a 2-players or 3-players match. Insert the number again.");
		}
	}


	/**
	 * @author Lorenzo Longaretti
	 * @author Andrea Gerosa
	 * Sends the selected god's name to the server, after some controls.
	 *
	 * @param inputLine line of text from stdin
	 * @param stdin standard input
	 */
	public void setGodName(String inputLine, Scanner stdin) {

		if(service.getIntservizio() == 0) {
			String[] inputs = new String[1];
			inputs[0] = inputLine;

			GodName godName = godControl(inputLine, stdin);


			try {
				System.out.println("The chosen name is " + godName);
				service.replyChosenGod(godName);
				sendOutput(service);
				state = StateNumber.active;

			} catch (Exception e) {
				System.out.println(e + "error in choose god");
			}
		}
		else if(service.getIntservizio() == 2) {
			String[] inputs = new String[2];
			inputs[0] = inputLine;

			GodName godName1 = godControl(inputLine, stdin);

			stdin.reset();
			inputLine = stdin.nextLine();
			inputs[1] = inputLine;
			GodName godName2 = godControl(inputLine, stdin);

			ArrayList<GodName> Divinities2Players = new ArrayList<>();
			Divinities2Players.add(0, godName1);
			Divinities2Players.add(1, godName2);

			try {

				service.replyChosenGod1(Divinities2Players);
				sendOutput(service);
				state = StateNumber.active;

			} catch (Exception e) {
				System.out.println(e + "error in choose god");
			}
		}
		else if(service.getIntservizio() == 3) {
			String[] inputs = new String[3];
			inputs[0] = inputLine;

			GodName godName1 = godControl(inputLine, stdin);

			stdin.reset();
			inputLine = stdin.nextLine();
			inputs[1] = inputLine;
			GodName godName2 = godControl(inputLine, stdin);

			stdin.reset();
			inputLine = stdin.nextLine();
			inputs[2] = inputLine;
			GodName godName3 = godControl(inputLine, stdin);

			ArrayList<GodName> Divinities3Players = new ArrayList<>();
			Divinities3Players.add(0, godName1);
			Divinities3Players.add(1, godName2);
			Divinities3Players.add(2, godName3);

			try {

				service.replyChosenGod1(Divinities3Players);
				sendOutput(service);
				state = StateNumber.active;

			} catch (Exception e) {
				System.out.println(e + "error in choose god");
			}
		}

	}

	/**
	 * @author Lorenzo Longaretti
	 * Checks if the input is a divinity among those which are available.
	 *
	 * @param inputLine line of text from stdin
	 * @param stdin standard input
	 */
	public GodName godControl(String inputLine, Scanner stdin) {
		GodName findgod = GodName.apollo;
		boolean find = false;
		while(!find) {
			for (GodName godName : service.getListGod()){
				if (godName.toString().equals(inputLine.toUpperCase())){
					find = true;
					findgod = godName;
				}
			}
			if(!find){
				System.out.println(cliMessage.wrongGodMessage);
				stdin.reset();
				inputLine = stdin.nextLine();
			}

		}
		return findgod;
	}

	/**
	 * @author Andrea Gerosa
	 * Checks if the inserted player is correct and send the information about it.
	 *
	 * @param inputline
	 * @param stdin
	 */
	public void setFirstPlayer (String inputline, Scanner stdin) {
		String[] inputs = new String[1];
		inputs[0] = inputline;

		boolean ok = false;
		while(!ok) {

			if(service.getPlayersToChoose().size() == 2) {
				while(!(inputs[0].equals(service.getPlayersToChoose().get(0)) || inputs[0].equals(service.getPlayersToChoose().get(1)))) {
					System.out.println("Choice not valid! Try again!");
					stdin.reset();
					inputline = stdin.nextLine();
					inputs[0] = inputline;
				}
				ok = true;
			}
			else if(service.getPlayersToChoose().size() == 3) {
				while(!(inputs[0].equals(service.getPlayersToChoose().get(0)) || inputs[0].equals(service.getPlayersToChoose().get(1)) || inputs[0].equals(service.getPlayersToChoose().get(2)))) {
					System.out.println("Choice not valid! Try again!");
					stdin.reset();
					inputline = stdin.nextLine();
					inputs[0] = inputline;
				}
				ok = true;
			}

		}

		try {
			if(inputs[0].equals(service.getPlayersToChoose().get(0))) {
				service.receiveFirstPlayer(0);
			}
			else if(inputs[0].equals(service.getPlayersToChoose().get(1))) {
				service.receiveFirstPlayer(1);
			}
			else if(inputs[0].equals(service.getPlayersToChoose().get(2))) {
				service.receiveFirstPlayer(2);
			}
			sendOutput(service);
			state = StateNumber.active;
		}
		catch(IOException e) {
			System.err.println(e + "Error in first player choice.");
		}

	}

	/**
	 * @author Andrea Gerosa
	 * Takes the input of the first and the second coordinate where the workers will be positioned and
	 * does some controls. Then sends the service as an outputStream.
	 *
	 * @param inputLine line of text from stdin
	 * @param stdin standard input
	 */
	public void setWorkerPositions(String inputLine, Scanner stdin) throws IOException {

		String[] inputsFirst = new String[2];
		String[] inputsHelper = inputLine.split(",");
		inputLine = moveChecks(inputLine, inputsHelper, stdin);
		inputsHelper = inputLine.split(",");
		inputsFirst[0] = inputsHelper[0];
		inputsFirst[1] = inputsHelper[1];

		inputLine = boundsAndAvailabilityCheckPositions(inputLine, inputsFirst, stdin);
		inputsHelper = inputLine.split(",");
		inputsFirst[0] = inputsHelper[0];
		inputsFirst[1] = inputsHelper[1];


		System.out.println("Second one:");
		String[] inputsSecond = new String[2];
		stdin.reset();
		inputLine = stdin.nextLine();
		inputsHelper = inputLine.split(",");

		boolean okDiff = false;
		while(!okDiff) {
			while(Integer.parseInt(inputsHelper[0]) == Integer.parseInt(inputsFirst[0]) && Integer.parseInt(inputsHelper[1]) == Integer.parseInt(inputsFirst[1])) {
				System.out.println("This cell has been already chosen!");

				stdin.reset();
				inputLine = stdin.nextLine();
				inputsHelper = inputLine.split(",");
				inputLine = moveChecks(inputLine, inputsHelper, stdin);
				inputsHelper = inputLine.split(",");
			}
			okDiff = true;
		}

		inputLine = moveChecks(inputLine, inputsHelper, stdin);
		inputsHelper = inputLine.split(",");
		inputsSecond[0] = inputsHelper[0];
		inputsSecond[1] = inputsHelper[1];

		inputLine = boundsAndAvailabilityCheckPositions(inputLine, inputsSecond, stdin);
		inputsHelper = inputLine.split(",");
		inputsSecond[0] = inputsHelper[0];
		inputsSecond[1] = inputsHelper[1];


		service.setCoordindatePlayer(Integer.parseInt(inputsFirst[0]), Integer.parseInt(inputsFirst[1]), Integer.parseInt(inputsSecond[0]), Integer.parseInt(inputsSecond[1]));
		state = StateNumber.active;
		sendOutput(service);
	}

	/**
	 * @author Andrea Gerosa
	 * Checks if the input meets the costraints of the coordinates.
	 *
	 * @param inputLine
	 * @param inputs
	 * @param stdin
	 * @return
	 */
	public String boundsAndAvailabilityCheckPositions(String inputLine, String[] inputs, Scanner stdin) {
		boolean okExt = false;
		while (!okExt) {
			try {

				while (Integer.parseInt(inputs[0]) < 0 || Integer.parseInt(inputs[0]) > 4 || Integer.parseInt(inputs[1]) < 0 || Integer.parseInt(inputs[1]) > 4) {
					System.out.println(cliMessage.impossibleCellMessage);

					boolean okInt = false;
					while (!okInt) {
						try {
							stdin.reset();
							inputLine = stdin.nextLine();
							String[] inputsHelper = inputLine.split(",");

							String iptln;
							iptln = moveChecks(inputLine, inputsHelper, stdin);
							inputsHelper = iptln.split(",");
							inputs[0] = inputsHelper[0];
							inputs[1] = inputsHelper[1];


							okInt = true;
						} catch (Exception e) {
							System.err.println(e + ": " + cliMessage.wrongInputMessage);
						}
					}

				}
				okExt = true;
			}
			catch(Exception e) {
				System.err.println(e + ": " + cliMessage.unavailableCellMessage);
			}
		}
		return inputLine;
	}

	/**
	 * @author Andrea Gerosa
	 * Checks if the input is an integer.
	 *
	 * @param inputLine line of text from stdin
	 * @param inputs array where values of interest has been split into
	 * @param stdin standard input
	 */
	public void integerControl(String inputLine, String[] inputs, Scanner stdin) {
		while(!isInteger(inputLine)) {
			System.err.println("You must insert a number!");
			stdin.reset();
			inputLine = stdin.nextLine();
			inputs[1] = inputLine;
		}
	}

	/**
	 * @author Andrea Gerosa
	 * Checks if the input match the possible numbers for the players (2 or 3).
	 *
	 * @param inputLine line of text from stdin
	 * @param inputs array where values of interest has been split into
	 * @param stdin standard input
	 */
	public void twoOrThreeControl(String inputLine, String[] inputs, Scanner stdin) {
		boolean okInt = false;

		while(!okInt) {
			try {
				while(Integer.parseInt(inputs[1]) != 2 && Integer.parseInt(inputs[1]) != 3) {
					System.out.println(cliMessage.wrongNumberOfPlayersMessage);
					stdin.reset();
					inputLine = stdin.nextLine();
					inputs[1] = inputLine;

					integerControl(inputLine, inputs, stdin);
				}
				okInt = true;
			}
			catch(Exception e) {
				System.err.println(e + ": " + cliMessage.wrongInputMessage);
			}
		}
	}


	/**
	 * @author Andrea Gerosa
	 * Calls all the methods that do various controls before sending the service.
	 *
	 * @param inputLine line of text from stdin
	 * @param stdin standard input
	 */
	public void sendMove(String inputLine, Scanner stdin) {
		//String[] inputs = inputLine.split(",");

		String[] inputs = new String[3];
		inputs[0] = inputLine;

		boolean ok = false;
		while(!ok) {
			try {

				while (!isInteger(inputLine) || (Integer.parseInt(inputs[0]) != 0 && Integer.parseInt(inputs[0]) != 1)) {
					if (!isInteger(inputLine)) {
						System.err.println("You must insert a numeric value!");
					}
					else {
						if (Integer.parseInt(inputs[0]) != 0 && Integer.parseInt(inputs[0]) != 1) {
							System.out.println(cliMessage.wrongWorkerMessage);
						}
					}
					stdin.reset();
					inputLine = stdin.nextLine();
					inputs[0] = inputLine;
				}

				ok = true;
			}
			catch (Exception e) {
				System.err.println(e + "You must insert a numeric value!");
			}
		}


		System.out.println("Available cells for the selected worker:");
		ArrayList<LightBlock> AvailableCellsSupport = new ArrayList<>();
		for(int l = 0 ; l < service.getAvailableCells().size(); l++){
			if(service.getAvailableCells().get(l).getIdWorker() == Integer.parseInt(inputs[0])) {
				AvailableCellsSupport.add(service.getAvailableCells().get(l));
			}
		}
		service.getAvailableCells().clear();
		for (LightBlock lightBlock : AvailableCellsSupport) {
			service.getAvailableCells().add(lightBlock);
		}
		AvailableCellsSupport.clear();
		for (int l = 0; l < service.getAvailableCells().size(); l++) {
			System.out.print(service.getAvailableCells().get(l).getC().getX() + "," + service.getAvailableCells().get(l).getC().getY() + "|");
		}
		System.out.println();

		stdin.reset();
		inputLine = stdin.nextLine();
		String[] inputsHelper = inputLine.split(",");

		inputLine = moveChecks(inputLine, inputsHelper, stdin);
		inputsHelper = inputLine.split(",");
		inputs[1] = inputsHelper[0];
		inputs[2] = inputsHelper[1];

		boundsAndAvailabilityCheck(inputLine, inputs, stdin);

	}

	/**
	 * @author Andrea Gerosa
	 * Checks if the input is a valid and makes sense.
	 *
	 * @param inputLine line of text from stdin
	 * @param inputs array where values of interest has been split into
	 * @param stdin standard input
	 * @return inputline
	 */
	public String moveChecks(String inputLine, String[] inputs, Scanner stdin) {
		while(inputs.length != 2 || !areAllIntegers(inputs)) {

			if(inputs.length != 2) {
				System.out.println(cliMessage.wrongInputMessage);
			}
			if(!areAllIntegers(inputs)) {
				System.err.println("You must insert numeric values!");
			}
			stdin.reset();
			inputLine = stdin.nextLine();
			inputs = inputLine.split(",");

		}
		return inputLine;
	}

	/**
	 * @author Andrea Gerosa
	 * Checks if the input meets the costraints and then sends the service as an outputStream.
	 *
	 * @param inputLine line of text from stdin
	 * @param inputs array where values of interest has been split into
	 * @param stdin standard input
	 */
	public void boundsAndAvailabilityCheck(String inputLine, String[] inputs, Scanner stdin) {
		boolean okExt = false;
		while(!okExt) {
			try {

				while (Integer.parseInt(inputs[1]) < 0 || Integer.parseInt(inputs[1]) > 4 || Integer.parseInt(inputs[2]) < 0 || Integer.parseInt(inputs[2]) > 4) {
					System.out.println(cliMessage.impossibleCellMessage);

					boolean okInt = false;
					while (!okInt) {
						try {
							stdin.reset();
							inputLine = stdin.nextLine();
							String[] inputsHelper = inputLine.split(",");

							String iptln;
							iptln = moveChecks(inputLine, inputsHelper, stdin);
							inputsHelper = iptln.split(",");
							inputs[1] = inputsHelper[0];
							inputs[2] = inputsHelper[1];


							okInt = true;
						}
						catch (Exception e) {
							//System.err.println("errore generico in lettura da terminale!!" + e.getMessage() + "!!!" + e.initCause(e.getCause()));
							System.err.println(e + ": " + cliMessage.wrongInputMessage);
							//run();
						}
					}


				}

				int flag = checkIfAvailable(inputs);

				if(flag != 0) {
					okExt = true;
					state = StateNumber.active;
					sendOutput(service);
				}
				else {
					System.err.println(cliMessage.unavailableCellMessage);
					stdin.reset();
					inputLine = stdin.nextLine();
					String[] inputsHelper = inputLine.split(",");

					String iptln;
					iptln = moveChecks(inputLine, inputsHelper, stdin);
					inputsHelper = iptln.split(",");
					inputs[1] = inputsHelper[0];
					inputs[2] = inputsHelper[1];
				}

			}
			catch(Exception e) {
				System.err.println(e + ": " + cliMessage.unavailableCellMessage);
			}

		}
	}

	/**
	 * @author Andrea Gerosa
	 * Checks the availability of a cell.
	 *
	 * @param inputs array where values of interest has been split into
	 * @return a value that tells if the chosen cell is one of the available cells.
	 */
	public int checkIfAvailable(String[] inputs) {
		int flag = 0;
		for (LightBlock lb : service.getAvailableCells()) {

			if (lb.getC().getX() == Integer.parseInt(inputs[1]) && lb.getC().getY() == Integer.parseInt(inputs[2])) {
				try {
					service.receiveMoveMessage(lb, Integer.parseInt(inputs[0]));
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}

				//state = StateNumber.active;
				flag++;
				break;
			}
		}
		return flag;
	}

	/**
	 * @author Andrea Gerosa
	 * Calls all the methods that do various controls before sending the service.
	 *
	 * @param inputLine line of text from stdin
	 * @param stdin standard input
	 * @throws IOException
	 */
	public void sendBuild(String inputLine, Scanner stdin) throws IOException {

		//100 as default value
		String[] inputs = new String[]{"100", "100", "100"};
		String[] inputsHelper = inputLine.split(",");
		for(int i = 0; i < inputsHelper.length; i++) {
			if(inputsHelper[i] != null) {
				inputs[i] = inputsHelper[i];
			}
		}

		inputLine = moveChecks(inputLine, inputsHelper, stdin);
		inputsHelper = inputLine.split(",");
		for(int i = 0; i < inputsHelper.length; i++) {
			if(inputsHelper[i] != null) {
				inputs[i] = inputsHelper[i];
			}
		}

		inputLine = boundsAndAvailabilityCheck2(inputLine, inputsHelper, stdin);
		inputsHelper = inputLine.split(",");
		for(int i = 0; i < inputsHelper.length; i++) {
			if(inputsHelper[i] != null) {
				inputs[i] = inputsHelper[i];
			}
		}


		System.out.println("What floor are you building?");

		stdin.reset();
		inputLine = stdin.nextLine();
		inputs[2] = inputLine;

		boolean ok = false;
		while(!ok) {
			try {

				while (!isInteger(inputLine) || (Integer.parseInt(inputs[2]) < 1 && Integer.parseInt(inputs[2]) > 4)) {
					if (!isInteger(inputLine)) {
						System.err.println("You must insert a numeric value!");
					}
					else {
						if (Integer.parseInt(inputs[2]) < 1 && Integer.parseInt(inputs[2]) > 4) {
							System.out.println(cliMessage.wrongLevelMessage);
						}
					}
					stdin.reset();
					inputLine = stdin.nextLine();
					inputs[2] = inputLine;
				}

				if(levelCheck(inputs)) {
					ok = true;
				}
				else {
					System.err.println("You can't build this floor at this right moment!");
					stdin.reset();
					inputLine = stdin.nextLine();
					inputs[2] = inputLine;
				}

			}
			catch (Exception e) {
				System.err.println(e + "You must insert a numeric value!");
			}
		}

		sendOutput(service);
		state = StateNumber.active;

	}

	/**
	 * @author Andrea Gerosa
	 * Checks if the input meets the costraints and then sends the service as an outputStream.
	 *
	 * @param inputLine line of text from stdin
	 * @param inputs array where values of interest has been split into
	 * @param stdin standard input
	 * @return input line
	 */
	public String boundsAndAvailabilityCheck2(String inputLine, String[] inputs, Scanner stdin) {
		boolean okExt = false;
		while(!okExt) {
			try {

				while (Integer.parseInt(inputs[0]) < 0 || Integer.parseInt(inputs[0]) > 4 || Integer.parseInt(inputs[1]) < 0 || Integer.parseInt(inputs[1]) > 4) {
					System.out.println(cliMessage.impossibleCellMessage);

					boolean okInt = false;
					while (!okInt) {
						try {
							stdin.reset();
							inputLine = stdin.nextLine();
							String[] inputsHelper = inputLine.split(",");

							String iptln;
							iptln = moveChecks(inputLine, inputsHelper, stdin);
							inputLine = iptln;
							inputsHelper = iptln.split(",");
							inputs[0] = inputsHelper[0];
							inputs[1] = inputsHelper[1];


							okInt = true;
						}
						catch (Exception e) {
							System.err.println(e + ": " + cliMessage.wrongInputMessage);
						}
					}


				}

				int flag = checkIfAvailable2(inputs);

				if(flag != 0) {
					okExt = true;
				}
				else {
					System.err.println(cliMessage.unavailableCellMessage);
					stdin.reset();
					inputLine = stdin.nextLine();
					String[] inputsHelper = inputLine.split(",");

					String iptln;
					iptln = moveChecks(inputLine, inputsHelper, stdin);
					inputLine = iptln;
					inputsHelper = iptln.split(",");
					inputs[0] = inputsHelper[0];
					inputs[1] = inputsHelper[1];
				}

			}
			catch(Exception e) {
				System.err.println(e + ": " + cliMessage.unavailableCellMessage);
			}

		}
		return inputLine;
	}

	/**
	 * @author Andrea Gerosa
	 * Checks the availability of a cell.
	 *
	 * @param inputs array where values of interest has been split into
	 * @return a value that tells if the chosen cell is one of the available cells.
	 */
	public int checkIfAvailable2(String[] inputs) {
		int flag = 0;
		for (LightBlock lb : service.getAvailableCells2()) {

			if (lb.getC().getX() == Integer.parseInt(inputs[0]) && lb.getC().getY() == Integer.parseInt(inputs[1])) {
				//lb.getC().getX() == Integer.parseInt(inputs[0]) && lb.getC().getY() == Integer.parseInt(inputs[1]) && lb.getLevel() == (Integer.parseInt(inputs[2]) - 1)
				//state = StateNumber.active;
				flag++;
				break;
			}
		}
		return flag;
	}

	/**
	 * @author Andrea Gerosa
	 * Checks if the level in input makes sense.
	 *
	 * @param inputs array where values of interest has been split into
	 * @return a boolean value which indicates if the chosen floor, in the chosen cell, can be built.
	 */
	public boolean levelCheck(String[] inputs) {
		for (LightBlock lb : service.getAvailableCells2()) {
			if(lb.getC().getX() == Integer.parseInt(inputs[0]) && lb.getC().getY() == Integer.parseInt(inputs[1]) && lb.getLevel() == (Integer.parseInt(inputs[2]) - 1)) {
				service.receiveBuildMessage(lb);
				return true;
			}
		}
		return  false;
	}

	/**
	 * @author Andrea Gerosa
	 * Checks if the player wants to do another match or exit the game at the end of the match.
	 * Then sends the service as an outputStream.
	 *
	 * @param inputLine line of text from stdin
	 * @param stdin standard input
	 */
	public void endGameResponse(String inputLine, Scanner stdin) {

		while(!(inputLine.equals("yes") || inputLine.equals("no"))) {
			System.out.println("Enter 'yes' or 'no'.");
			stdin.reset();
			inputLine = stdin.nextLine();
		}
		newMatch = inputLine.equals("yes");

	}

	/**
	 * @author Andrea Gerosa
	 * After a player's unexpected disconnection, checks if the player decides to do another match
	 * or exit the game.
	 * Then sends the service as an outputStream.
	 *
	 * @param inputLine line of text from stdin
	 * @param stdin standard input
	 */
	public void disconnectionResponse(String inputLine, Scanner stdin) {
		while(!(inputLine.equals("yes") || inputLine.equals("no"))) {
			System.out.println("Enter 'yes' or 'no'.");
			stdin.reset();
			inputLine = stdin.nextLine();
		}
		newMatch = inputLine.equals("yes");
	}

}
