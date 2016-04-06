/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package protocol;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import model.*;
import threadpool.*;

/**
 *
 * @author Jet
 */
public class QuickProtocol extends Thread {
    
    
    private final int port =1108;
    private Boolean live = true;
    private ThreadPool pool;
    private Game game;
    
    
    public QuickProtocol(int worker, int tasks, Game g){
        this.game = g;
        this.pool = new ThreadPool(worker, tasks);
        this.pool.start();    
    }
    
    public void Startpool(){
        this.pool.start();
    }
    
    
    @Override
    public void run(){
        //InetAddress address;
        
        int num_clients = 0;
        ServerSocket sSoc; 
        Socket soc=null;
        PrintWriter sender = null;
        Listener l;
        try {
            sSoc = new ServerSocket(port); 
        while(live){
            soc =sSoc.accept();
            l = new Listener(soc);
            l.start();    
            num_clients++;
            System.out.println("Number of clients "+ num_clients);
        }
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }
    
    public void Broadcast(){
        ArrayList<Sheep> sheeps = this.game.getSheeps();
        
        int size = sheeps.size();
        for(int i = 0 ; i< size; i++){
            this.pool.putTask((Task) sheeps.get(i));
        }
       //foreach sheep add in task;      
    }
    
}
