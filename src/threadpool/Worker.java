/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jet
 */
public class Worker extends Thread {
 
    private ArrayBlockingQueue task_list;
    private boolean life;
    
    
    public Worker(ArrayBlockingQueue task_list){
        this.task_list = task_list;
        this.life = true;
    }
    
    @Override
    public void run(){
        while(this.life){
            try {
                Task t =(Task) this.task_list.take();
                t.work();
            } catch (InterruptedException ex) {
                Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
            }
                  
        }
    }
    
    
    public void BroadCast(){
        
        
        
        
    }
}
