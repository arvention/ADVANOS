/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import gui.GUI;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import protocol.QuickProtocol;

/**
 *
 * @author Arces
 */
public class Game extends Thread {

    public ArrayList<Sheep> sheepList;
    private QuickProtocol qp;
    private Timer timer;
    private GUI view;

    public Game(GUI view) {
        this.view = view;
        this.timer = new Timer(true);
        timer.scheduleAtFixedRate(new Ticker(), 0, 30);

        //this.qp = new QuickProtocol(,500, this);
    }

    @Override
    public void run() {
        while (true) {

        }
    }

    public class Ticker extends TimerTask {

        @Override
        public void run() {
            //get data from protocol hereeeeee

            //update UI hereeee UI.update(sheepList)
        }
    }

    public synchronized void addSheep(Sheep sheep) {
        sheepList.add(sheep);
    }

    public synchronized ArrayList<Sheep> getSheeps() {
        return this.sheepList;
    }

    public synchronized int generateID() {
        return sheepList.get(sheepList.size() - 1).getId() + 1;
    }

    public synchronized String getScreenShot() {
        String ss = "";
        for (Sheep s : sheepList) {
            ss += s.getId() + "," + s.getX() + "," + s.getY() + "\n"; //"id,x,y\n"
        }
        return ss;
    }
}
