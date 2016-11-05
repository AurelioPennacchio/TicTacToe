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
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aurelio
 */
public class Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(7777);
            System.out.println("Server avviato");
            Socket client1 = server.accept();
            System.out.println("Client 1 connesso");
            Socket client2 = server.accept();
            System.out.println("Client 2 connesso");
            boolean client1Turn = true;
            boolean client2Turn = false;
            BufferedReader inFromClient1 = new BufferedReader(new InputStreamReader(client1.getInputStream()));
            BufferedReader inFromClient2 = new BufferedReader(new InputStreamReader(client2.getInputStream()));
            DataOutputStream outToClient1 = new DataOutputStream(client1.getOutputStream());
            DataOutputStream outToClient2 = new DataOutputStream(client2.getOutputStream());
            while(true){
                String sentence = "tuo turno";
                String sentence2 = "aspetta che l'avversario faccia una mossa";
                if(client1Turn){
                    outToClient1.writeBytes(sentence + "\n");
                    outToClient2.writeBytes(sentence2 + "\n");
                    String coordinata1 = inFromClient1.readLine();
                    System.out.println(coordinata1);
                    String coordinata2 = inFromClient1.readLine();
                    System.out.println(coordinata2);
                    outToClient2.writeBytes(coordinata1 + "\n");
                    outToClient2.writeBytes(coordinata2 + "\n");
                    client1Turn = false;
                }
                else{
                    outToClient1.writeBytes(sentence2 + "\n");
                    outToClient2.writeBytes(sentence + "\n");
                    String coordinata1 = inFromClient2.readLine();
                    System.out.println(coordinata1);
                    String coordinata2 = inFromClient2.readLine();
                    System.out.println(coordinata2);
                    outToClient1.writeBytes(coordinata1 + "\n");
                    outToClient1.writeBytes(coordinata2 + "\n");
                    client1Turn = true;
                }
            }
        } catch (IOException ex) {
            System.out.println("Errore nella creazione del server");
            System.out.println("Chiusura del programma");
            System.out.println("Prova");
        }
    }
    
}
