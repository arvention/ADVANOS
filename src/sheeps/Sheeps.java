/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sheeps;

import gui.GUI;
import gui.GUIController;
import model.Game;
import model.Sheep;

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
        
        Game g = Game.getInstance();
        g.startProtocol();
        
    }
    
}
