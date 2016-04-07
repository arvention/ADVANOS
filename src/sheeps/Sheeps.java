/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sheeps;

import gui.GUI;
import gui.GUIController;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Game;
import model.Sheep;
import model.UDPGame;
import protocol.UDPProtocol;

/**
 *
 * @author Jet
 */
public class Sheeps {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        /*
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
        */
    //    GUIController guiController = GUIController.getInstance();
      //  guiController.getGUI().setVisible(true);
       
        /*UDPProtocol up;
        try {
            up = new UDPProtocol(8,5000);
            up.start();
        } catch (SocketException ex) {
            Logger.getLogger(Sheeps.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Sheeps.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        
        //TCP-IP
        //Game g = Game.getInstance();
        //g.startProtocol();
        
        //UDP
        UDPGame g = UDPGame.getInstance();
        g.startProtocol();
        
    }
    
}
