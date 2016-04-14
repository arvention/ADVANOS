/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import gui.GUI;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import protocol.UDPProtocol;

/**
 *
 * @author Arces
 */
public class UDPGame {
    public ArrayList<Sheep> sheepList;
    private UDPProtocol up;
    private Timer timer;
    //private GUI gui;
    //public static UDPGame gameInstance = new UDPGame();
    
    private int id;
    private int port;
    
    public UDPGame(int id, int port) {
        this.id=id;
        this.port=port;
        //this.gui = new GUI();
        //gui.setVisible(true);
        this.sheepList = new ArrayList<>();
    }
    
    public void startProtocol() {
        try {
            this.up = new UDPProtocol(8, 5000,this.port,this.id,this);
        } catch (SocketException ex) {
            Logger.getLogger(UDPGame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(UDPGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.up.start();
        this.timer = new Timer(true);
        timer.scheduleAtFixedRate(new Ticker(), 0, 30);
    }
/*
    public static UDPGame getInstance() {
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
            try {
                //get data from protocol hereeeeee
                up.broadcast(getScreenShot());
            } catch (IOException ex) {
                Logger.getLogger(UDPGame.class.getName()).log(Level.SEVERE, null, ex);
            }
            //gui.update(sheepList);
        }
    }

    public synchronized int createSheep(int x,int y){
        int id = generateID();
        Sheep sheep = new Sheep(x,y,id);
        addSheep(sheep);
        
        return id;
    }
    
    
    public void addSheep(Sheep sheep) {
        sheepList.add(sheep);
    }
    
    public synchronized void remove(Sheep s){
        this.sheepList.remove(s);
    }
    public ArrayList<Sheep> getSheeps() {
        return this.sheepList;
    }

    public int generateID() {
        if (sheepList.isEmpty()) {
            return 1;
        } else {
            return sheepList.get(sheepList.size() - 1).getId() + 1;
        }
    }

    public byte[] getScreenShot() {
        ByteBuffer bb = ByteBuffer.allocate(1024 * 20);
        bb.putLong(System.currentTimeMillis());
        bb.putInt(this.id);
        for (int i = 0; i < sheepList.size(); i++) {
            Sheep s = sheepList.get(i);
            
            bb.putInt(s.getId());
            bb.putInt(s.getX());
            bb.putInt(s.getY());
        }
        bb.putInt(-1);
        return bb.array();
    }
}
