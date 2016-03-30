/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadpool;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jet
 */
public class ThreadPool {

    private ArrayList<Worker> workers;
    private ArrayBlockingQueue task_list;
    private Boolean live;
    private int size;
    
    public ThreadPool(int num_workers, int num_task){
        this.size = num_workers;
        this.live=  false;
        this.workers= new ArrayList<>();
        task_list = new ArrayBlockingQueue(num_task);
        
        for(int i=0; i< num_workers;i++){
            Worker w = new Worker(task_list);
            this.workers.add(w);
        }
        
    }
    
    public void start(){
        if(!live){
            for(int i=0; i<  this.size; i++){
                workers.get(i).start();
            }
            live = true;
        }
    }
    
    
    public void putTask(Task t){
        try {
            task_list.put(t);
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadPool.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
