/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoepersonale;

/**
 *
 * @author Sandra
 */
public class Tabella {
    private String tabella[][];
    
    public Tabella(){
        this.tabella = new String[3][3];
        inizializzaTabella();
    }
    
    public void inizializzaTabella(){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                this.tabella[i][j] = " ";            
            }
        }
    }
    
    public boolean controlloPosizione(int i, int j){
        if(i>=1 && i<=3 && j>=1 && j<=3){
            if(tabella[i-1][j-1].equals(" ")){
                return true;
            }
        }
        return false;
    }
    
    public boolean controlloVittoria(String segno){
        if(tabella[0][0].equals(segno) && tabella[0][1].equals(segno) && tabella[0][2].equals(segno)){
            return true;
        }
        if(tabella[1][0].equals(segno) && tabella[1][1].equals(segno) && tabella[1][2].equals(segno)){
            return true;
        }
        if(tabella[2][0].equals(segno) && tabella[2][1].equals(segno) && tabella[2][2].equals(segno)){
            return true;
        }
        return false;
    }
    
    public void inserisciPosizione(int i, int j, String segno){
        this.tabella[i-1][j-1] = segno;
    }
    
    public void stampaTabella(){
        System.out.println("-----");
        System.out.println(tabella[0][0] + "|" + tabella[0][1] + "|" + tabella[0][2]);
        System.out.println(tabella[1][0] + "|" + tabella[1][1] + "|" + tabella[1][2]);
        System.out.println(tabella[2][0] + "|" + tabella[2][1] + "|" + tabella[2][2]);
        System.out.println("-----");
    }
    
}
