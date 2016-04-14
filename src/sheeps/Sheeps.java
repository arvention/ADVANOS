/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sheeps;


import model.UDPGame;


/**
 *
 * @author Jet
 */
public class Sheeps {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
                
        //TCP-IP
        //Game g = Game.getInstance();
        //g.startProtocol();
        
        //UDP
        //UDPGame g = new UDPGame(0,1108);
        UDPGame g2 = new UDPGame(1,1109);
        //g.startProtocol();
        g2.startProtocol();
        
    }
    
}
