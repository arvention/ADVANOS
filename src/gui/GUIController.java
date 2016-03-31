package gui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GUIController extends KeyAdapter{
    
    private static GUIController guiController = new GUIController(new GUI());
    private GUI gui;
    
    public GUIController(GUI gui){
        this.gui = gui;
    }
    
    public GUIController getInstance(){
        return guiController;
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
    switch (event.getKeyCode()) {
        case KeyEvent.VK_UP:
            System.out.println("UP");
            break;
        case KeyEvent.VK_DOWN:
            System.out.println("DOWN");
            break;
        case KeyEvent.VK_RIGHT:
            System.out.println("RIGHT");
            break;
        case KeyEvent.VK_LEFT:
            System.out.println("LEFT");
            break;
    }
}
    
    public GUI getGUI(){
        return gui;
    }
    
    public void setGUI(GUI gui){
        this.gui = gui;
    }
}
