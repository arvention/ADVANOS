/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package protocol;

/**
 *
 * @author Jet
 */
public class QuickProtocol extends Thread {
    
    
    private final int port =1108;
    private Boolean life;
    private LowLevel low;
    private HighLevel high;
    
    
    public QuickProtocol(){
        this.life = true;
        this.low = new UpdLow(this.port);
        this.high = new UpdHigh();
    }
    
    @Override
    public void run(){
        byte[] received;
        InetAddress address;
        while(this.life){
            received = low.receive();
            address = low.
            
        }
    }
    
    
    
    
    
    
    
    
    
}
