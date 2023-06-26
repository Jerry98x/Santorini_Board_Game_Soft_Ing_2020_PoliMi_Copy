package it.polimi.ingsw.PSP45.avviatori;

import it.polimi.ingsw.PSP45.client.Client;

import java.io.IOException;
import java.util.Scanner;

/**
 * @author Lorenzo Longaretti
 * @author Andrea Gerosa
 *
 * Game launcher for the cli
 *
 */
public class ClientApp
{

    /**
     * @author Lorenzo Longaretti
     * @author Andrea Gerosa
     *
     * Main method that starts the game, running the client.
     *
     * @param args
     */
    public static void main(String[] args){
        Boolean nextplay = true;

        boolean flag = true;

        String localHost = "127.0.0.1";
        String port = "12345";

        Scanner stdin = new Scanner(System.in);
        System.out.println("Would you like to connect to " + localHost + ", port " + port + "?");
        System.out.println("Write 'yes' or 'no'.");
        stdin.reset();
        String input = stdin.nextLine();
        while(!(input.equals("yes") || input.equals("no"))) {
            System.out.println("Write 'yes' or 'no'.");
            stdin.reset();
            input = stdin.nextLine();
        }

        while(nextplay){
            if(!flag) {
                System.out.println("Would you like to connect to " + localHost + ", port " + port + "?");
                System.out.println("Write 'yes' or 'no'.");
                stdin.reset();
                input = stdin.nextLine();
                while(!(input.equals("yes") || input.equals("no"))) {
                    System.out.println("Write 'yes' or 'no'.");
                    stdin.reset();
                    input = stdin.nextLine();
                }
            }
            flag = false;

            if(input.equals("yes")) {
                Client client = new Client("127.0.0.1", 12345);
                try{
                    client.run();
                    nextplay = client.isNewMatch();
                }catch (IOException e){
                    System.err.println(e.getMessage());
                }
            }
            if(input.equals("no")) {
                System.out.println("Enter the IP and the port.");

                String[] inputs = new String[2];
                String inputLine = stdin.nextLine();
                inputs[0] = inputLine;
                stdin.reset();
                inputLine = stdin.nextLine();
                inputs[1] = inputLine;


                Client client = new Client(inputs[0], Integer.parseInt(inputs[1]));
                try{
                    client.run();
                    nextplay = client.isNewMatch();
                }catch (IOException e){
                    System.err.println(e.getMessage());
                }
            }

        }
    }

}
