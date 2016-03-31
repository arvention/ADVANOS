/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import protocol.QuickProtocol;

/**
 *
 * @author Arces
 */
public class Game extends Thread{
    public ArrayList<Sheep> sheepList;
    private QuickProtocol qp;
    private Timer timer;
    //UI
    
    public Game(){
        timer = new Timer(true);
        timer.scheduleAtFixedRate(new Ticker(), 0, 30);
    }
    
    @Override
    public void run(){
        while(true){
            
        }
    }
    
    public class Ticker extends TimerTask{
        @Override
        public void run() {
            //get data from protocol hereeeeee
            
            //update UI hereeee UI.update(sheepList)
        }
    }
    
    public synchronized void addSheep(Sheep sheep){
        sheepList.add(sheep);
    }
    
    public synchronized ArrayList<Sheep> getSheeps(){
        return this.sheepList;
    } 
}