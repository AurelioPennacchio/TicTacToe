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
            boolean primaLettura = true;
            String segno = null;
            Tabella tabella = new Tabella();
            while(true){
                String sentence = inFromServer.readLine();
                if(sentence.equals("tuo turno")){
                    if(primaLettura){
                        segno = "x";
                        primaLettura = false;
                    }
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
                    if(tabella.controlloPosizione(coo, coo2)){
                        tabella.inserisciPosizione(coo, coo2, segno);
                        tabella.stampaTabella();
                        if(tabella.controlloVittoria(segno)){
                            System.out.println("Hai vinto");
                            String avversario = "hai perso";
                            outToServer.writeBytes(avversario + "\n");
                            clientSocket.close();
                        }
                        else{
                            String avversario = "continua";
                            outToServer.writeBytes(avversario + "\n");
                        }
                    }
                }
                else{
                    if(primaLettura){
                        segno = "o";
                        primaLettura = false;
                    }
                    System.out.println("Aspetta il tuo turno");
                    String coordinata1 = inFromServer.readLine();
                    String coordinata2 = inFromServer.readLine();
                    if(segno.equals("x")){
                        tabella.inserisciPosizione(Integer.parseInt(coordinata1), Integer.parseInt(coordinata2), "o");
                    }
                    else{
                        tabella.inserisciPosizione(Integer.parseInt(coordinata1), Integer.parseInt(coordinata2), "x");
                    }
                    tabella.stampaTabella();
                    String messaggio = inFromServer.readLine();
                    if(messaggio.equals("hai perso")){
                        clientSocket.close();
                        System.out.println("Socket chiuso");
                        System.exit(1);
                    }
                    System.out.println(coordinata1);
                    System.out.println(coordinata2);
                }
                    
            }
        } catch (IOException ex) {
            System.out.println("Socket chiuso");
        }
    }
    
}
