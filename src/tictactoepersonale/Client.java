/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoepersonale;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aurelio
 */
public class Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            Socket clientSocket = new Socket("localhost",7777);
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            while(true){
                String sentence = inFromServer.readLine();
                if(sentence.equals("tuo turno")){
                    System.out.println("posiziona");
                    System.out.println("Scegli la prima coordinata dove posizionare il tuo segno");
                    int coo = Integer.parseInt(inFromUser.readLine());
                    String coordinata1 = String.valueOf(coo);
                    System.out.println(coordinata1);
                    System.out.println("Scegli la seconda coordinata dove posizionare il tuo segno");
                    int coo2 = Integer.parseInt(inFromUser.readLine());
                    String coordinata2 = String.valueOf(coo2);
                    System.out.println(coo2);
                    outToServer.writeBytes(coordinata1 + "\n");
                    outToServer.writeBytes(coordinata2 + "\n");
                }
                else{
                    System.out.println("Aspetta il tuo turno");
                    String coordinata1 = inFromServer.readLine();
                    String coordinata2 = inFromServer.readLine();
                    System.out.println(coordinata1);
                    System.out.println(coordinata2);
                }
                    
            }
        } catch (IOException ex) {
            System.out.println("Errore nella commessopme sul server");
        }
    }
    
}
