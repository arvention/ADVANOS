package gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GUIController {

    private static GUIController guiController = new GUIController(new GUI());
    private GUI gui;

    private GUIController(GUI gui) {
        this.gui = gui;
        this.gui.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
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

            @Override
            public void keyReleased(KeyEvent e) {
            }

        });
    }

    public static GUIController getInstance() {
        return guiController;
    }

    public GUI getGUI() {
        return gui;
    }

    public void setGUI(GUI gui) {
        this.gui = gui;
    }
}
