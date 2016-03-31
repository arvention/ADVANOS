/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import gui.GUI;
import gui.GUIController;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import protocol.QuickProtocol;

/**
 *
 * @author Arces
 */
public class Game{

    public ArrayList<Sheep> sheepList;
    private QuickProtocol qp;
    private Timer timer;
    private GUI gui;
    public static Game gameInstance = new Game();

    private Game() {
        this.gui = new GUI();
        gui.setVisible(true);
        this.sheepList = new ArrayList<>();
    }

    public void startProtocol() {
        this.qp = new QuickProtocol(8, 500, gameInstance);
        this.qp.Startpool();
        this.qp.start();
        this.timer = new Timer(true);
        timer.scheduleAtFixedRate(new Ticker(), 0, 30);
    }

    public static Game getInstance() {
        return gameInstance;
    }
/*
    @Override
    public void run() {
        while (true) {
            //do nothing
        }
    }
*/
    public class Ticker extends TimerTask {

        @Override
        public void run() {
            //get data from protocol hereeeeee
            qp.Broadcast();
            gui.update(sheepList);
        }
    }

    public synchronized void addSheep(Sheep sheep) {
        sheepList.add(sheep);
    }

    public synchronized ArrayList<Sheep> getSheeps() {
        return this.sheepList;
    }

    public synchronized int generateID() {
        if (sheepList.isEmpty()) {
            return 1;
        } else {
            return sheepList.get(sheepList.size() - 1).getId() + 1;
        }
    }

    public synchronized String getScreenShot() {
        String ss = "";
        for (Sheep s : sheepList) {
            ss += s.getId() + "," + s.getX() + "," + s.getY() + "\n"; //"id,x,y\n"
        }
        return ss;
    }
}
