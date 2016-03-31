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
import threadpool.*;

/**
 *
 * @author Jet
 */
public class QuickProtocol extends Thread {
    
    
    private final int port =1108;
    private Boolean live = true;
    private ThreadPool pool;
    
    
    public QuickProtocol(int worker, int tasks){
        this.pool = new ThreadPool(worker, tasks);
        this.pool.start();
    }
    
    @Override
    public void run(){
        //InetAddress address;
        
       
        ServerSocket sSoc; 
        Socket soc=null;
        PrintWriter sender = null;
            
        
        while(live){
            try {
                sSoc = new ServerSocket(80); 
                soc =sSoc.accept();
                soc.getOutputStream();
                
                
                
                
            } catch (IOException ex) {
                System.out.println("Helllloo2");
            }
                
        }
    }
    
    public void Broadcast(){     
       //foreach sheep add in task;
        
    }
    
}
